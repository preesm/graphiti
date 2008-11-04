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
import java.util.Map.Entry;

import net.sf.graphiti.model.Edge;
import net.sf.graphiti.ui.figure.shapes.IShape;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.GridData;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.Polyline;
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

	private Map<String, Label> inputPorts;

	private Label labelId;

	private Label labelToolTip;

	private Map<String, Label> outputPorts;

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
		inputPorts = new TreeMap<String, Label>();
		outputPorts = new TreeMap<String, Label>();

		// necessary for adjustSize
		setFont(font);

		setLayoutManager(new XYLayout());
		initLabels();
		initShape(shape, color, dimension);
	}

	/**
	 * Adds an input port with the given name.
	 * 
	 * @param portName
	 *            The port name.
	 */
	public void addInputPort(String portName) {
		if (portName != null && !portName.isEmpty()) {
			inputPorts.put(portName, null);
		}
	}

	/**
	 * Adds an output port with the given name.
	 * 
	 * @param portName
	 *            The port name.
	 */
	public void addOutputPort(String portName) {
		if (portName != null && !portName.isEmpty()) {
			outputPorts.put(portName, null);
		}
	}

	/**
	 * Adjusts the size of this figure according to its id and ports.
	 */
	@SuppressWarnings("unchecked")
	public void adjustSize() {
		List<IFigure> children = new ArrayList<IFigure>((List<IFigure>) shape
				.getChildren());
		children.remove(labelId);
		for (IFigure child : children) {
			shape.remove(child);
		}

		// update the figure ports
		updatePorts();

		// set the shape dimension (if polyline), and our size.
		if (shape instanceof Polyline) {
			shape.setDimension(shape.getPreferredSize());
		}
		setSize(shape.getPreferredSize());
	}

	/**
	 * Returns the label for the input port whose name is given.
	 * 
	 * @param portName
	 *            The input port name.
	 * @return Its label.
	 */
	public Label getInputPortLabel(String portName) {
		return inputPorts.get(portName);
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
	 * Returns the label for the output port whose name is given.
	 * 
	 * @param portName
	 *            The output port name.
	 * @return Its label.
	 */
	public Label getOutputPortLabel(String portName) {
		return outputPorts.get(portName);
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

	public ConnectionAnchor getSourceAnchor() {
		return shape.getConnectionAnchor(this, null, true);
	}

	public ConnectionAnchor getSourceAnchor(Edge edge) {
		String portName = (String) edge.getValue(Edge.PARAMETER_SOURCE_PORT);
		return shape.getConnectionAnchor(this, portName, true);
	}

	public ConnectionAnchor getTargetAnchor() {
		return shape.getConnectionAnchor(this, null, false);
	}

	public ConnectionAnchor getTargetAnchor(Edge edge) {
		String portName = (String) edge.getValue(Edge.PARAMETER_TARGET_PORT);
		return shape.getConnectionAnchor(this, portName, false);
	}

	/**
	 * Initializes the labels.
	 */
	private void initLabels() {
		// Label id
		labelId = new Label();
		labelId.setForegroundColor(ColorConstants.black);

		// Sets the tool tip and adds it so that it is visible
		labelToolTip = new Label();
		labelToolTip.setForegroundColor(ColorConstants.black);
		setToolTip(labelToolTip);
	}

	/**
	 * Initializes the shape and adds it to this figure.
	 * 
	 * @param shape
	 *            An {@link IShape}.
	 * @param color
	 *            Its {@link Color}.
	 * @param dimension
	 *            Its {@link Dimension}.
	 */
	private void initShape(IShape shape, Color color, Dimension dimension) {
		// Sets shape properties and size
		this.shape = shape;
		shape.setBackgroundColor(color);
		shape.setDimension(new Dimension(dimension.width, dimension.height));

		// Adds shape to this, and label to shape.
		add(shape, new Rectangle(0, 0, -1, -1));
		GridData data = new GridData(SWT.CENTER, SWT.CENTER, true, true);
		data.horizontalSpan = 2;
		shape.add(labelId, data);
	}

	/**
	 * Resets the ports sets.
	 */
	public void resetPorts() {
		inputPorts.clear();
		outputPorts.clear();
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

	/**
	 * Adds label for all ports of this figure in the grid layout.
	 */
	private void updatePorts() {
		Iterator<Entry<String, Label>> itInput = inputPorts.entrySet()
				.iterator();
		Iterator<Entry<String, Label>> itOutput = outputPorts.entrySet()
				.iterator();

		// we iterate on input ports first
		while (itInput.hasNext()) {
			if (itOutput.hasNext()) {
				// If the output port iterator has at least one output port, we
				// add both.
				Entry<String, Label> entry = itInput.next();
				Label label = new Label(entry.getKey());
				entry.setValue(label);
				shape.add(label, new GridData(SWT.BEGINNING, SWT.CENTER, false,
						false));

				entry = itOutput.next();
				label = new Label(entry.getKey());
				entry.setValue(label);
				shape.add(label,
						new GridData(SWT.END, SWT.CENTER, false, false));
			} else {
				// Otherwise, we add only the input port with an horizontal span
				// equal to 2
				Entry<String, Label> entry = itInput.next();
				Label label = new Label(entry.getKey());
				entry.setValue(label);
				GridData data = new GridData(SWT.BEGINNING, SWT.CENTER, false,
						false);
				data.horizontalSpan = 2;
				shape.add(label, data);
			}
		}

		// Finally, we proceed to add any remaining output port with an
		// horizontal span of 2.
		while (itOutput.hasNext()) {
			Entry<String, Label> entry = itOutput.next();
			Label label = new Label(entry.getKey());
			entry.setValue(label);
			GridData data = new GridData(SWT.END, SWT.CENTER, false, false);
			data.horizontalSpan = 2;
			shape.add(label, data);
		}
	}

	@Override
	protected boolean useLocalCoordinates() {
		return true;
	}
}
