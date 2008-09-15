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

import net.sf.graphiti.ui.figure.shapes.IShape;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.XYLayout;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.graphics.Color;

/**
 * This class provides a figure for vertices.
 * 
 * @author Samuel Beaussier
 * @author Nicolas Isch
 * @author Matthieu Wipliez
 */
public class VertexFigure extends Figure {

	private Label labelToolTip;

	private IShape shape;

	/**
	 * Creates a new VertexFigure using the given vertex model.
	 * 
	 * @param dimension
	 * @param color
	 * @param shape
	 */
	public VertexFigure(Dimension dimension, Color color, IShape shape) {
		// Get bounds
		bounds = new Rectangle(0, 0, dimension.width, dimension.height);
		setBounds(bounds);
		setOpaque(true);

		// Sets Layout Manager
		setLayoutManager(new XYLayout());

		// Sets the tool tip and adds it so that it is visible
		labelToolTip = new Label();
		labelToolTip.setForegroundColor(ColorConstants.black);
		setToolTip(labelToolTip);

		if (shape != null) {
			this.shape = shape;
			shape.setColor(color);
			shape.setDimension(new Dimension(bounds.width, bounds.height));
			add(shape, new Rectangle(0, 0, bounds.width, bounds.height));
		}
	}

	public Label getLabelId() {
		return shape.getLabel();
	}

	/**
	 * Returns the size of this VertexFigure.
	 * 
	 * @return The size of this VertexFigure.
	 */
	public Dimension getPreferredSize(int w, int h) {
		return getBounds().getSize();
	}

	public IShape getShape() {
		return shape;
	}

	public void setBounds(Rectangle rect) {
		if (shape != null) {
			shape.setDimension(rect.getSize());
			setConstraint(shape, new Rectangle(0, 0, rect.width, rect.height));
		}

		super.setBounds(rect);
	}

	/**
	 * The name to set
	 * 
	 * @param text
	 */
	public void setName(String text) {
		if (shape != null) {
			shape.setName(text);
		}

		labelToolTip.setText(text);
	}

	@Override
	protected boolean useLocalCoordinates() {
		return true;
	}
}
