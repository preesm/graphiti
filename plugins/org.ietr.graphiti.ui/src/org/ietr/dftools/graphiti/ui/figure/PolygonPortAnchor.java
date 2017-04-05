/*******************************************************************************
 * Copyright or © or Copr. IETR/INSA - Rennes (%%DATE%%) :
 *
 * %%AUTHORS%%
 *
 * This software is a computer program whose purpose is to [describe
 * functionalities and technical features of your software].
 *
 * This software is governed by the CeCILL  license under French law and
 * abiding by the rules of distribution of free software.  You can  use, 
 * modify and/ or redistribute the software under the terms of the CeCILL
 * license as circulated by CEA, CNRS and INRIA at the following URL
 * "http://www.cecill.info". 
 *
 * As a counterpart to the access to the source code and  rights to copy,
 * modify and redistribute granted by the license, users are provided only
 * with a limited warranty  and the software's author,  the holder of the
 * economic rights,  and the successive licensors  have only  limited
 * liability. 
 *
 * In this respect, the user's attention is drawn to the risks associated
 * with loading,  using,  modifying and/or developing or reproducing the
 * software by the user in light of its specific status of free software,
 * that may mean  that it is complicated to manipulate,  and  that  also
 * therefore means  that it is reserved for developers  and  experienced
 * professionals having in-depth computer knowledge. Users are therefore
 * encouraged to load and test the software's suitability as regards their
 * requirements in conditions enabling the security of their systems and/or 
 * data to be ensured and,  more generally, to use and operate it in the 
 * same conditions as regards security. 
 *
 * The fact that you are presently reading this means that you have had
 * knowledge of the CeCILL license and that you accept its terms.
 *******************************************************************************/
package org.ietr.dftools.graphiti.ui.figure;

import org.eclipse.draw2d.AbstractConnectionAnchor;
import org.eclipse.draw2d.Polygon;
import org.eclipse.draw2d.geometry.Geometry;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;

/**
 * This class provides a connection anchor for polygons.
 * 
 * @author Jonathan Piat
 * @author Matthieu Wipliez
 * 
 */
public class PolygonPortAnchor extends AbstractConnectionAnchor {

	private PortAnchorReferenceManager mgr;

	/**
	 * Creates a new polygon port anchor.
	 * 
	 * @param figure
	 *            The owning vertex figure.
	 * @param portName
	 *            The port name associated with this connection anchor.
	 * @param isOutput
	 *            Whether the connection is input (false) or output (true).
	 */
	public PolygonPortAnchor(VertexFigure figure, String portName,
			boolean isOutput) {
		super(figure);
		mgr = new PortAnchorReferenceManager(figure, portName, isOutput);
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
	@Override
	public Point getLocation(Point reference) {
		Polygon owner;
		if (getOwner() instanceof VertexFigure) {
			owner = (Polygon) getOwner().getChildren().get(0);
		} else {
			throw new NullPointerException();
		}

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
				int xAnchor;
				if (run == 0) {
					// Line equation: x = center.x
					xAnchor = center.x;
				} else {
					// Line equation: y = ax + b = px + d =>
					// x = (d - b) / (a - p)
					float rise = (reference.y - center.y);
					float a = rise / run;
					float b = center.y - a * center.x;
					xAnchor = (int) ((d - b) / (a - p));
				}

				// yAnchor is just y = px + d
				int yAnchor = (int) (p * xAnchor + d);
				return new Point(xAnchor, yAnchor);
			}
		}

		// Should never happen
		return center;
	}

	@Override
	public Point getReferencePoint() {
		Point reference = mgr.getReferencePoint(this);
		if (reference == null) {
			return super.getReferencePoint();
		} else {
			return reference;
		}
	}

}
