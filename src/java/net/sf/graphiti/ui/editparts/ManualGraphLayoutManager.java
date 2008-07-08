/*******************************************************************************
 * Copyright (c) 2000, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package net.sf.graphiti.ui.editparts;

import java.util.Iterator;
import java.util.List;

import org.eclipse.draw2d.AbstractHintLayout;
import org.eclipse.draw2d.AbstractLayout;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.ScrollPane;
import org.eclipse.draw2d.StackLayout;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;

/**
 * Figures using the StackLayout as their layout manager have their children
 * placed on top of one another. Order of placement is determined by the order
 * in which the children were added, first child added placed on the bottom.
 */
public class ManualGraphLayoutManager extends StackLayout {

	/**
	 * Returns the minimum size required by the input container. This is the
	 * size of the largest child of the container, as all other children fit
	 * into this size.
	 * 
	 * @see AbstractHintLayout#calculateMinimumSize(IFigure, int, int)
	 */
	@SuppressWarnings("unchecked")
	protected Dimension calculateMinimumSize(IFigure figure, int wHint,
			int hHint) {
		if (wHint > -1)
			wHint = Math.max(0, wHint - figure.getInsets().getWidth());
		if (hHint > -1)
			hHint = Math.max(0, hHint - figure.getInsets().getHeight());
		Dimension d = new Dimension();
		List children = figure.getChildren();
		IFigure child;
		for (int i = 0; i < children.size(); i++) {
			child = (IFigure) children.get(i);
			if (!isObservingVisibility() || child.isVisible())
				d.union(child.getMinimumSize(wHint, hHint));
		}

		d.expand(figure.getInsets().getWidth(), figure.getInsets().getHeight());
		d.union(getBorderPreferredSize(figure));
		return d;

	}

	/**
	 * Calculates and returns the preferred size of the given figure. This is
	 * the union of the preferred sizes of the widest and the tallest of all its
	 * children.
	 * 
	 * @see AbstractLayout#calculatePreferredSize(IFigure, int, int)
	 */
	@SuppressWarnings("unchecked")
	protected Dimension calculatePreferredSize(IFigure figure, int wHint,
			int hHint) {
		if (wHint > -1)
			wHint = Math.max(0, wHint - figure.getInsets().getWidth());
		if (hHint > -1)
			hHint = Math.max(0, hHint - figure.getInsets().getHeight());
		Dimension d = new Dimension();
		List children = figure.getChildren();
		IFigure child;
		for (int i = 0; i < children.size(); i++) {
			child = (IFigure) children.get(i);
			if (!isObservingVisibility() || child.isVisible())
				d.union(child.getPreferredSize(wHint, hHint));
		}

		d.expand(figure.getInsets().getWidth(), figure.getInsets().getHeight());
		d.union(getBorderPreferredSize(figure));
		return d;
	}

	/**
	 * Returns the origin for the given figure.
	 * 
	 * @param parent
	 *            the figure whose origin is requested
	 * @return the origin
	 */
	public Point getOrigin(IFigure parent) {
		return parent.getClientArea().getLocation();
	}

	/**
	 * @see org.eclipse.draw2d.LayoutManager#layout(IFigure)
	 */
	@SuppressWarnings("unchecked")
	public void layout(IFigure parent) {
		Rectangle r = parent.getClientArea();
		List children = parent.getChildren();
		IFigure child;
		for (int i = 0; i < children.size(); i++) {
			child = (IFigure) children.get(i);
			if (child instanceof ScrollPane) {
				child.setBounds(r);
			}
		}

		Iterator it = children.iterator();
		Point offset = getOrigin(parent);
		IFigure f;
		while (it.hasNext()) {
			f = (IFigure) it.next();
			Rectangle bounds = (Rectangle) getConstraint(f);
			if (bounds == null)
				continue;

			if (bounds.width == -1 || bounds.height == -1) {
				Dimension preferredSize = f.getPreferredSize(bounds.width,
						bounds.height);
				bounds = bounds.getCopy();
				if (bounds.width == -1)
					bounds.width = preferredSize.width;
				if (bounds.height == -1)
					bounds.height = preferredSize.height;
			}
			bounds = bounds.getTranslated(offset);
			f.setBounds(bounds);
		}
	}

	// public void layout(IFigure parent) {
	// Iterator children = parent.getChildren().iterator();
	// Point offset = getOrigin(parent);
	// IFigure f;
	// while (children.hasNext()) {
	// f = (IFigure)children.next();
	// Rectangle bounds = (Rectangle)getConstraint(f);
	// if (bounds == null) continue;
	//
	// if (bounds.width == -1 || bounds.height == -1) {
	// Dimension preferredSize = f.getPreferredSize(bounds.width,
	// bounds.height);
	// bounds = bounds.getCopy();
	// if (bounds.width == -1)
	// bounds.width = preferredSize.width;
	// if (bounds.height == -1)
	// bounds.height = preferredSize.height;
	// }
	// bounds = bounds.getTranslated(offset);
	// f.setBounds(bounds);
	// }
	// }

}
