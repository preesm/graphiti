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
package net.sf.graphiti.ui.editparts;

import net.sf.graphiti.ui.figure.VertexFigure;
import net.sf.graphiti.ui.figure.shapes.IShape;

import org.eclipse.draw2d.AbstractConnectionAnchor;
import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;

/**
 * @author mwipliez
 * 
 */
public class VertexConnectionAnchor extends AbstractConnectionAnchor {

	private int anchorNumber;

	private int direction;

	private boolean isTarget;

	private int nbAnchors;

	/**
	 * Creates a new vertex connection anchor with the given direction, way and
	 * owner.
	 * 
	 * @param direction
	 *            The direction, {@link PositionConstants#EAST} or
	 *            {@link PositionConstants#SOUTH}.
	 * @param isTarget
	 *            False if the owner is a source, true if the owner is a target.
	 * @param anchorNumber
	 *            The anchor number (between 0 and nbAnchors - 1).
	 * @param nbAnchors
	 *            The number of anchors of the vertex edit part.
	 * @param owner
	 *            The owner vertex figure.
	 */
	public VertexConnectionAnchor(int direction, boolean isTarget,
			int anchorNumber, int nbAnchors, VertexFigure owner) {
		super(owner);

		this.direction = direction;
		this.isTarget = isTarget;
		this.anchorNumber = anchorNumber;
		this.nbAnchors = nbAnchors;
	}

	@Override
	public Point getLocation(Point reference) {
		VertexFigure figure = (VertexFigure) getOwner();
		IShape shape = figure.getShape();
		ConnectionAnchor anchor = shape.getConnectionAnchor();
		reference = getNewReference();
		return anchor.getLocation(reference);
	}

	/**
	 * Returns a new reference point for this anchor. This method returns a
	 * point on the circle whose ray is determined from the bounds. The point
	 * returned is determined by its angle according to a ratio between
	 * {@link #anchorNumber} and {@link #nbAnchors}.
	 * 
	 * @return A {@link Point} that can be used as reference to get an anchor's
	 *         location.
	 */
	public Point getNewReference() {
		Point center = getReferencePoint();

		// step 1: get an angle.
		double ratio = (float) (anchorNumber + 1) / (float) (nbAnchors + 1);
		double angle = ratio * Math.PI;
		if (direction == PositionConstants.EAST) {
			if (isTarget) {
				angle = 3.0 * Math.PI / 2.0 - angle;
			} else {
				angle -= Math.PI / 2.0;
			}
		} else {
			if (isTarget) {
				angle += Math.PI;
			} else {
				angle = Math.PI - angle;
			}
		}

		// step 2: get a point.
		Rectangle bounds = getOwner().getBounds();
		double ray = Math.sqrt(bounds.width * bounds.width + bounds.height
				* bounds.height);
		double x = ray * Math.cos(angle);
		double y = ray * Math.sin(angle);

		return new Point(center.x + x, center.y + y);
	}

	@Override
	public Point getReferencePoint() {
		return super.getReferencePoint();
	}

	/**
	 * Sets this anchor direction to the given direction.
	 * 
	 * @param direction
	 *            Either {@link PositionConstants#EAST} or
	 *            {@link PositionConstants#SOUTH}.
	 */
	public void setDirection(int direction) {
		this.direction = direction;
	}

}
