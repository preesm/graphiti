/*
 * Copyright (c) 2008, IETR/INSA of Rennes
 * All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 * 
 *   * Redistributions of source code must retain the above copyright notice,
 *     this list of conditions and the following disclaimer.
 *   * Redistributions in binary form must reproduce the above copyright notice,
 *     this list of conditions and the following disclaimer in the documentation
 *     and/or other materials provided with the distribution.
 *   * Neither the name of the IETR/INSA of Rennes nor the names of its
 *     contributors may be used to endorse or promote products derived from this
 *     software without specific prior written permission.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT,
 * STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY
 * WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF
 * SUCH DAMAGE.
 */
package net.sf.graphiti.ui.figure.shapes;

import org.eclipse.draw2d.AbstractConnectionAnchor;
import org.eclipse.draw2d.ChopboxAnchor;
import org.eclipse.draw2d.Polygon;
import org.eclipse.draw2d.geometry.Geometry;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.Rectangle;

/**
 * This class provides a connection anchor for polygons.
 * 
 * @author Jonathan Piat
 * @author Matthieu Wipliez
 * 
 */
public class PolygonConnectionAnchor extends AbstractConnectionAnchor {

	/**
	 * Constructs a new PolygonConnectionAnchor with the given <i>owner</i>
	 * figure.
	 * 
	 * @param owner
	 *            The owner figure
	 */
	public PolygonConnectionAnchor(Polygon owner) {
		super(owner);
	}

	/**
	 * Returns <code>true</code> if the other anchor has the same owner and box.
	 * 
	 * @param obj
	 *            the other anchor
	 * @return <code>true</code> if equal
	 */
	public boolean equals(Object obj) {
		if (obj instanceof ChopboxAnchor) {
			PolygonConnectionAnchor other = (PolygonConnectionAnchor) obj;
			return other.getOwner() == getOwner()
					&& other.getBox().equals(getBox());
		}
		return false;
	}

	/**
	 * Returns the bounds of this PolygonConnectionAnchor's owner. Subclasses
	 * can override this method to adjust the box the anchor can be placed on.
	 * For instance, the owner figure may have a drop shadow that should not be
	 * included in the box.
	 * 
	 * @return The bounds of this PolygonConnectionAnchor's owner
	 */
	protected Rectangle getBox() {
		return getOwner().getBounds();
	}

	/**
	 * Gets a Rectangle from {@link #getBox()} and returns the Point where a
	 * line from the center of the Rectangle to the Point <i>reference</i>
	 * intersects the Rectangle.
	 * 
	 * @param reference
	 *            The reference point
	 * @return The anchor location
	 */
	public Point getLocation(Point reference) {
		Polygon owner = ((Polygon) getOwner());
		Point center = getReferencePoint();
		if (reference.x == center.x && reference.y == center.y) {
			return center;
		}

		// The line run
		float run = (reference.x - center.x);

		PointList pointList = owner.getPoints();
		for (int i = 0; i < pointList.size() - 1; i++) {
			Point start = pointList.getPoint(i);
			Point end = pointList.getPoint(i + 1);

			// Translate from relative to absolute coordinates
			owner.translateToAbsolute(start);
			owner.translateToAbsolute(end);

			// Check intersection
			if (Geometry.linesIntersect(center.x, center.y, reference.x,
					reference.y, start.x, start.y, end.x, end.y)) {
				float p = ((float) (start.y - end.y))
						/ ((float) (start.x - end.x));
				float d = start.y - p * start.x;

				// Compute xAnchor
				float xAnchor;
				if (run == 0) {
					// Line equation: x = center.x
					xAnchor = center.x;
				} else {
					// Line equation: y = ax + b = px + d =>
					// x = (d - b) / (a - p)
					float rise = (reference.y - center.y);
					float a = rise / run;
					float b = center.y - a * center.x;
					xAnchor = (d - b) / (a - p);
				}

				// yAnchor is just y = px + d
				float yAnchor = p * xAnchor + d;
				return new Point(xAnchor, yAnchor);
			}
		}

		// Should never happen
		return center;
	}

	/**
	 * Returns the anchor's reference point. In the case of the
	 * PolygonConnectionAnchor, this is the center of the anchor's owner.
	 * 
	 * @return The reference point
	 */
	public Point getReferencePoint() {
		Point ref = getBox().getCenter();
		getOwner().translateToAbsolute(ref);
		return ref;
	}

	/**
	 * The owning figure's hashcode is used since equality is approximately
	 * based on the owner.
	 * 
	 * @return the hash code.
	 */
	public int hashCode() {
		if (getOwner() != null)
			return getOwner().hashCode();
		else
			return super.hashCode();
	}

}
