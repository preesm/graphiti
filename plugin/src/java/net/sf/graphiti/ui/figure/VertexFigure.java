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

	/**
	 * Returns the shape of this figure.
	 * 
	 * @return The shape of this figure.
	 */
	public IShape getShape() {
		return shape;
	}

	/**
	 * Returns the connection source anchor, i.e. where connections start.
	 * 
	 * @return The {@link ConnectionAnchor} of the underlying shape.
	 */
	public ConnectionAnchor getSourceAnchor() {
		return shape.getConnectionAnchor(this, null, true);
	}

	/**
	 * Returns the connection source anchor, i.e. where connections start.
	 * 
	 * @param edge
	 *            The edge model of the connection. Allows the figure to
	 *            retrieve the source port.
	 * @return The {@link ConnectionAnchor} of the underlying shape.
	 */
	public ConnectionAnchor getSourceAnchor(Edge edge) {
		String portName = (String) edge.getValue(Edge.PARAMETER_SOURCE_PORT);
		return shape.getConnectionAnchor(this, portName, true);
	}

	/**
	 * Returns the connection target anchor, i.e. where connections end.
	 * 
	 * @return The {@link ConnectionAnchor} of the underlying shape.
	 */
	public ConnectionAnchor getTargetAnchor() {
		return shape.getConnectionAnchor(this, null, false);
	}

	/**
	 * Returns the connection target anchor, i.e. where connections end.
	 * 
	 * @param edge
	 *            The edge model of the connection. Allows the figure to
	 *            retrieve the target port.
	 * @return The {@link ConnectionAnchor} of the underlying shape.
	 */
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
	 * Adds a label for the given entry. The entry key is a port name used in
	 * the label, and the entry value is updated to the newly-created label.
	 * 
	 * @param entry
	 *            An entry of {@link #inputPorts} or {@link #outputPorts}.
	 * @param horizontalAlignment
	 *            The horizontal alignment: {@link SWT#BEGINNING},
	 *            {@link SWT#CENTER} or {@link SWT#END}.
	 * @param horizontalSpan
	 *            The horizontal span, <code>1</code> or <code>2</code>.
	 */
	private void updatePortLabel(Entry<String, Label> entry,
			int horizontalAlignment, int horizontalSpan) {
		Label label = new Label(entry.getKey());
		entry.setValue(label);
		GridData data = new GridData(horizontalAlignment, SWT.CENTER, false,
				false);
		data.horizontalSpan = horizontalSpan;
		shape.add(label, data);
	}

	/**
	 * Adds label for all ports of this figure in the grid layout.
	 */
	private void updatePorts() {
		// prepare port lists
		List<Entry<String, Label>> inputPortList = new ArrayList<Entry<String, Label>>(
				inputPorts.entrySet());
		List<Entry<String, Label>> outputPortList = new ArrayList<Entry<String, Label>>(
				outputPorts.entrySet());
		List<Entry<String, Label>> portList = new ArrayList<Entry<String, Label>>();

		// ports having the same name are taken from input/outputPortList and
		// put in portList
		for (Entry<String, Label> inputPort : inputPorts.entrySet()) {
			if (outputPorts.containsKey(inputPort.getKey())) {
				portList.add(inputPort);
				outputPortList.remove(inputPort);
				inputPortList.remove(inputPort);
			}
		}

		// now we can go on with the process
		updatePortsFromLists(inputPortList, outputPortList, portList);
	}

	/**
	 * Adds labels from the ports of the given lists.
	 * 
	 * @param inputPortList
	 *            The list of input ports.
	 * @param outputPortList
	 *            The list of output ports.
	 * @param portList
	 *            The list of undirected ports/ports having the same name.
	 */
	private void updatePortsFromLists(List<Entry<String, Label>> inputPortList,
			List<Entry<String, Label>> outputPortList,
			List<Entry<String, Label>> portList) {
		Iterator<Entry<String, Label>> itInput = inputPortList.iterator();
		Iterator<Entry<String, Label>> itOutput = outputPortList.iterator();

		// we iterate on input ports first
		while (itInput.hasNext()) {
			if (itOutput.hasNext()) {
				// If the output port iterator has at least one output port, we
				// add both.
				updatePortLabel(itInput.next(), SWT.BEGINNING, 1);
				updatePortLabel(itOutput.next(), SWT.END, 1);
			} else {
				// Otherwise, we add only the input port with an horizontal span
				// equal to 2
				updatePortLabel(itInput.next(), SWT.BEGINNING, 2);
			}
		}

		// Adds any remaining output port with an horizontal span of 2.
		while (itOutput.hasNext()) {
			updatePortLabel(itOutput.next(), SWT.END, 2);
		}

		// Adds undirected ports with an horizontal span of 2.
		for (Entry<String, Label> entry : portList) {
			updatePortLabel(entry, SWT.CENTER, 2);
			String portName = entry.getKey();
			Label label = entry.getValue();
			inputPorts.put(portName, label);
			outputPorts.put(portName, label);
		}
	}

	@Override
	protected boolean useLocalCoordinates() {
		return true;
	}
}
