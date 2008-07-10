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

import net.sf.graphiti.model.DocumentConfiguration;
import net.sf.graphiti.model.Vertex;
import net.sf.graphiti.ontology.enums.Shapes;
import net.sf.graphiti.ui.figure.shapes.IShape;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.IFigure;
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

	private ConnectionAnchor anchor;

	private Label labelName;

	private Label labelToolTip;

	private IFigure shape;

	/**
	 * Creates a new VertexFigure using the given document configuration and
	 * vertex type.
	 * 
	 * @param config
	 * @param vertexType
	 */
	public VertexFigure(DocumentConfiguration config, String vertexType) {
		// Get bounds
		if (config.getVertexAttribute(vertexType, "height") != null
				&& config.getVertexAttribute(vertexType, "width") != null) {
			int width = (Integer) config
					.getVertexAttribute(vertexType, "width");
			int height = (Integer) config.getVertexAttribute(vertexType,
					"height");
			bounds = new Rectangle(0, 0, width, height);
		} else {
			bounds = new Rectangle(0, 0, 50, 50);
		}

		// TODO: what to do? height, width or parameter_size?
		// Rectangle bounds = (Rectangle)
		// vertex.getValue(Vertex.PARAMETER_SIZE);

		// Get color
		Color color = ColorConstants.gray;
		if (config.getVertexAttribute(vertexType, Vertex.ATTRIBUTE_COLOR) != null) {
			color = (Color) config.getVertexAttribute(vertexType,
					Vertex.ATTRIBUTE_COLOR);
		}

		// Get shape
		if (config.getVertexAttribute(vertexType, Vertex.ATTRIBUTE_SHAPE) != null) {
			shape = ((Shapes) config.getVertexAttribute(vertexType,
					Vertex.ATTRIBUTE_SHAPE)).getShape();
			((IShape) shape).setColor(color);
		}

		// Creates labels
		labelName = new Label();
		labelToolTip = new Label();

		// Sets Layout Manager
		XYLayout layout = new XYLayout();
		setLayoutManager(layout);

		// Sets the tool tip and adds it so that it is visible
		labelToolTip.setForegroundColor(ColorConstants.black);
		setToolTip(labelToolTip);
		add(labelToolTip);

		// Adds label name
		labelName.setForegroundColor(ColorConstants.black);
		add(labelName);

		setBorder(null);

		if (shape != null) {
			((IShape) shape).setDimension(new Dimension(bounds.width,
					bounds.height));
			this.add(this.shape, new Rectangle(0, 0, bounds.width,
					bounds.height));
			anchor = ((IShape) this.shape).getConnectionAnchor();
		}

	}

	/**
	 * 
	 * @return anchor
	 */
	public ConnectionAnchor getConnectionAnchor() {
		return anchor;
	}

	/**
	 * Returns the size of this VertexFigure.
	 * 
	 * @return The size of this VertexFigure.
	 */
	public Dimension getPreferredSize(int w, int h) {
		Rectangle rectangle = labelName.getTextBounds();
		int x = rectangle.width + 10;
		int y = rectangle.height + 2;
		Dimension prefSize = new Dimension(x, y);
		Dimension defaultSize = new Dimension(50, 50);
		prefSize.union(defaultSize);
		return prefSize;
	}

	/**
	 * The name to set
	 * 
	 * @param text
	 */
	public void setName(String text) {
		if (shape != null) {
			((IShape) this.shape).setName(text);
		} else {
			labelName.setText(text);
			this.setConstraint(labelName, new Rectangle(
					this.getBounds().height / 2, this.getBounds().width / 2,
					-1, -1));
		}
		// labelToolTip.setText(text);
		// setConstraint(labelToolTip, new Rectangle(0, 0, -1, -1));
	}

	@Override
	protected boolean useLocalCoordinates() {
		return true;
	}
}
