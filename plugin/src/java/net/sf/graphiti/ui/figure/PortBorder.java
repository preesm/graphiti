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
package net.sf.graphiti.ui.figure;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.LineBorder;
import org.eclipse.draw2d.geometry.Insets;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.Rectangle;

/**
 * This class provides a border for ports, extending LineBorder with the ability
 * to draw connectors.
 * 
 * @author Samuel Beaussier
 * @author Nicolas Isch
 * @author Matthieu Wipliez
 * 
 */
public class PortBorder extends LineBorder {

	private static PointList bottomConnector = new PointList();

	private static PointList connector = new PointList();

	private static Insets insets = new Insets(0, 0, 0, 0);

	static {
		connector.addPoint(0, 2);
		connector.addPoint(0, -1);
		connector.addPoint(1, -2);
		connector.addPoint(5, -2);
		connector.addPoint(5, 1);
		connector.addPoint(1, 1);

		bottomConnector.addPoint(-1, 2);
		bottomConnector.addPoint(-1, -1);
		bottomConnector.addPoint(-2, -2);
		bottomConnector.addPoint(-6, -2);
		bottomConnector.addPoint(-6, 1);
		bottomConnector.addPoint(-2, 1);
	}

	/**
	 * Creates a new PortBorder.
	 * 
	 * @param direction
	 *            The direction of the Port (input, output)
	 */
	public PortBorder(String direction) {
	}

	/**
	 * Draw the connectors on the rectangle rect.
	 * 
	 * @param graphics
	 *            the graphics
	 * @param rect
	 *            the rectangle reference
	 */
	private void drawConnectors(Graphics graphics, Rectangle rect) {
		int x1 = rect.x, y1, bottom = x1 + rect.width;
		graphics.setBackgroundColor(ColorConstants.blue);

		y1 = rect.y + rect.height / 2;

		// Draw the connectors
		graphics.setForegroundColor(ColorConstants.black);
		connector.translate(x1, y1);
		graphics.fillPolygon(connector);
		graphics.drawPolygon(connector);
		connector.translate(-x1, -y1);

		graphics.setForegroundColor(ColorConstants.black);
		bottomConnector.translate(bottom, y1);
		graphics.fillPolygon(bottomConnector);
		graphics.drawPolygon(bottomConnector);
		bottomConnector.translate(-bottom, -y1);
	}

	/**
	 * Returns insets of figure
	 * 
	 * @return insets
	 */
	public Insets getInsets(IFigure figure) {
		return insets;
	}

	public void paint(IFigure figure, Graphics graphics, Insets insets) {
		super.paint(figure, graphics, insets);
		drawConnectors(graphics, figure.getBounds().getCropped(insets));
	}
}
