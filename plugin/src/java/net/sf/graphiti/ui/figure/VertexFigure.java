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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import net.sf.graphiti.ui.figure.shapes.IShape;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.GridData;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.XYLayout;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;

/**
 * This class provides a figure for vertices.
 * 
 * @author Samuel Beaussier
 * @author Nicolas Isch
 * @author Matthieu Wipliez
 */
public class VertexFigure extends Figure {

	private Map<String, Rectangle> inputPorts;

	private Label labelId;

	private Label labelToolTip;

	private Map<String, Rectangle> outputPorts;

	private IShape shape;

	/**
	 * Creates a new VertexFigure using the given vertex model.
	 * 
	 * @param dimension
	 * @param color
	 * @param shape
	 */
	public VertexFigure(Font font, Dimension dimension, Color color,
			IShape shape) {
		// necessary for adjustSize
		setFont(font);

		// ports
		inputPorts = new TreeMap<String, Rectangle>();
		outputPorts = new TreeMap<String, Rectangle>();

		// Get bounds
		bounds = new Rectangle(0, 0, dimension.width, dimension.height);
		setBounds(bounds);

		// Sets Layout Manager
		setLayoutManager(new XYLayout());

		// Label id
		labelId = new Label();
		labelId.setFont(font);
		labelId.setForegroundColor(ColorConstants.black);

		// Sets the tool tip and adds it so that it is visible
		labelToolTip = new Label();
		labelToolTip.setForegroundColor(ColorConstants.black);
		setToolTip(labelToolTip);

		// Sets shape properties and size
		this.shape = shape;
		shape.setBackgroundColor(color);
		shape.setDimension(new Dimension(bounds.width, bounds.height));

		// Adds shape to this, and label to shape.
		add(shape, new Rectangle(0, 0, -1, -1));
		GridData data = new GridData(SWT.CENTER, SWT.CENTER, true, true);
		data.horizontalSpan = 2;
		shape.add(labelId, data);
	}

	/**
	 * Adds an input port with the given name.
	 * 
	 * @param portName
	 *            The port name.
	 */
	public void addInputPort(String portName) {
		inputPorts.put(portName, null);
	}

	/**
	 * Adds an output port with the given name.
	 * 
	 * @param portName
	 *            The port name.
	 */
	public void addOutputPort(String portName) {
		outputPorts.put(portName, null);
	}

	/**
	 * Adjusts the size of this figure according to its id and ports.
	 */
	public void adjustSize() {
		Iterator<String> itInput = inputPorts.keySet().iterator();
		Iterator<String> itOutput = outputPorts.keySet().iterator();
		while (itInput.hasNext()) {
			if (itOutput.hasNext()) {
				Label label = new Label(itInput.next());
				shape.add(label, new GridData(SWT.BEGINNING, SWT.CENTER, true,
						true));

				label = new Label(itOutput.next());
				shape.add(label, new GridData(SWT.END, SWT.CENTER, true, true));
			} else {
				Label label = new Label(itInput.next());
				GridData data = new GridData(SWT.BEGINNING, SWT.CENTER, true, true);
				data.horizontalSpan = 2;
				shape.add(label, data);
			}
		}

		while (itOutput.hasNext()) {
			Label label = new Label(itOutput.next());
			GridData data = new GridData(SWT.END, SWT.CENTER, true, true);
			data.horizontalSpan = 2;
			shape.add(label, data);
		}

		Dimension size = shape.getPreferredSize();
		shape.setSize(size);
		setConstraint(shape, new Rectangle(0, 0, size.width, size.height));
		setSize(size);
	}

	/**
	 * Clears the input ports.
	 */
	@SuppressWarnings("unchecked")
	public void clearInputPorts() {
		inputPorts.clear();
		List<IFigure> children = new ArrayList<IFigure>(shape.getChildren());
		for (IFigure child : children) {
			if (child != labelId) {
				shape.remove(child);
			}
		}
	}

	/**
	 * Clears the output ports.
	 */
	@SuppressWarnings("unchecked")
	public void clearOutputPorts() {
		outputPorts.clear();
		List<IFigure> children = (List<IFigure>) shape.getChildren();
		for (IFigure child : children) {
			if (child != labelId) {
				shape.remove(child);
			}
		}
	}

	/**
	 * Returns the id label object.
	 * 
	 * @return A {@link Label}.
	 */
	public Label getLabelId() {
		return labelId;
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
	public void setId(String text) {
		labelId.setText(text);
		labelToolTip.setText(text);
	}

	@Override
	protected boolean useLocalCoordinates() {
		return true;
	}
}
