/*******************************************************************************
 * Copyright (c) 2000, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package net.sf.graphiti.ui.figure;

import org.eclipse.draw2d.AbstractConnectionAnchor;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.ScalableFigure;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PrecisionPoint;
import org.eclipse.draw2d.geometry.Rectangle;

/**
 * Indicates the position where to create Anchors.
 * 
 * @author Samuel Beaussier
 * @author Nicolas Isch
 * 
 */
public class FixedConnectionAnchor extends AbstractConnectionAnchor {

	private Point place;

	/**
	 * Connect the Anchor to its owner
	 * 
	 * @param owner The parent figure of this Anchor
	 * @param place the position in the parent Figure
	 */
	public FixedConnectionAnchor(Figure owner, Point place) {
		super(owner);
		this.place = place;
	}

	/**
	 * 
	 * @param figure
	 */
	public void ancestorMoved(Figure figure) {
		if (figure instanceof ScalableFigure) {
			return;
		}
		super.ancestorMoved(figure);
	}

	public Point getLocation(Point reference) {
		Rectangle r = getOwner().getBounds();
		if (place == null) {
			place = new Point(0, 0);
		}
		int x = r.x + place.x * (r.width - 3);
		int y = r.y + place.y * r.height / 2;
		Point p = new PrecisionPoint(x, y);
		getOwner().translateToAbsolute(p);
		return p;
	}

	@Override
	public Point getReferencePoint() {
		return getLocation(null);
	}
}