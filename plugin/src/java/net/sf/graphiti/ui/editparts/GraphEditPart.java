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

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

import net.sf.graphiti.model.Graph;
import net.sf.graphiti.model.PropertyBean;
import net.sf.graphiti.ui.editpolicies.DeleteComponentEditPolicy;
import net.sf.graphiti.ui.editpolicies.LayoutPolicy;
import net.sf.graphiti.ui.figure.GraphFigure;

import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.geometry.Insets;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.draw2d.graph.CompoundDirectedGraphLayout;
import org.eclipse.draw2d.graph.EdgeList;
import org.eclipse.draw2d.graph.NodeList;
import org.eclipse.draw2d.graph.Subgraph;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.Request;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;

/**
 * The EditPart associated to the Graph gives methods to refresh the view when a
 * property has changed.
 * 
 * @author Samuel Beaussier
 * @author Nicolas Isch
 * @author Matthieu Wipliez
 * 
 */
public class GraphEditPart extends AbstractGraphicalEditPart implements
		PropertyChangeListener {

	/**
	 * The subgraph associated with this graph edit part. Set by
	 * {@link GraphEditPart#addNodes}.
	 */
	private Subgraph subgraph;

	@Override
	public void activate() {
		super.activate();
		((Graph) getModel()).addPropertyChangeListener(this);
	}

	/**
	 * Adds edges of this EditPart to the {@link EdgeList} of the parent.
	 * 
	 * @param edges
	 *            A list of edges in the graph.
	 */
	void addEdges(EdgeList edges) {
		for (Object child : getChildren()) {
			if (child instanceof VertexEditPart) {
				VertexEditPart part = (VertexEditPart) child;
				part.addEdges(edges);
			}
		}
	}

	/**
	 * Adds nodes of this EditPart to the {@link NodeList} of the parent. This
	 * method sets size and padding for all nodes. Subgraph have insets equal to
	 * 20 (top) and 0 otherwise.
	 * 
	 * @param nodes
	 *            A list of nodes in the graph.
	 * @param parent
	 *            If non-null, the parent subgraph.
	 */
	@SuppressWarnings("unchecked")
	void addNodes(NodeList nodes) {
		subgraph = new Subgraph(this);
		subgraph.innerPadding = new Insets(0, 0, 0, 0);
		subgraph.insets = new Insets(20, 0, 0, 0);
		Figure figure = (Figure) getFigure();
		subgraph.setSize(figure.getPreferredSize());
		subgraph.setPadding(new Insets(2, 2, 2, 2));
		nodes.add(subgraph);

		for (Object child : getChildren()) {
			if (child instanceof VertexEditPart) {
				VertexEditPart part = (VertexEditPart) child;
				part.addNodes(nodes, subgraph);
			}
		}
	}

	@Override
	protected void createEditPolicies() {
		installEditPolicy(EditPolicy.COMPONENT_ROLE,
				new DeleteComponentEditPolicy());
		installEditPolicy(EditPolicy.LAYOUT_ROLE, new LayoutPolicy());
	}

	@Override
	protected IFigure createFigure() {
		return new GraphFigure();
	}

	@Override
	public void deactivate() {
		super.deactivate();
		((Graph) getModel()).removePropertyChangeListener(this);
	}

	@Override
	public List<Object> getModelChildren() {
		Graph graph = (Graph) getModel();
		List<Object> children = new ArrayList<Object>();
		children.addAll(graph.vertexSet());
		return children;
	}

	@Override
	public void performRequest(Request req) {
	}

	public void propertyChange(PropertyChangeEvent evt) {
		if (evt.getPropertyName().equals(PropertyBean.PROPERTY_ADD)) {
			refresh();
		} else if (evt.getPropertyName().equals(PropertyBean.PROPERTY_REMOVE)) {
			refresh();
		} else if (evt.getPropertyName().equals(Graph.PARAMETER_SIZE)) {
			GraphFigure graphFigure = (GraphFigure) getFigure();
			graphFigure.setBounds((Rectangle) evt.getNewValue());
			refresh();
		}

		// PROPERTY_CONNECTION_ROUTER
		// PROPERTY_REFERENCE
		// PROPERTY_RENAME
		// PROPERTY_NB_REPEAT
		// PROPERTY_ADD_CST_GROUP
	}

	@Override
	protected void refreshVisuals() {
		Graph graph = (Graph) getModel();
		GraphFigure graphFigure = (GraphFigure) getFigure();
		graphFigure.setName((String) graph.getValue(Graph.PARAMETER_ID));

		Rectangle bounds = (Rectangle) graph.getValue(Graph.PARAMETER_SIZE);
		if (bounds != null) {
			// bounds is null when the graph is created
			graphFigure.setBounds(bounds);
		}
	}

	/**
	 * This method is called by the {@link GraphLayoutManager}, and applies back
	 * the changes of the {@link CompoundDirectedGraphLayout} algorithm to the
	 * different figures, by setting their bounds.
	 */
	void updateFigures(int direction) {
		Rectangle bounds;
		if (direction == PositionConstants.EAST) {
			bounds = new Rectangle(subgraph.x, subgraph.y, subgraph.height,
					subgraph.width);
		} else {
			// SOUTH
			bounds = new Rectangle(subgraph.x, subgraph.y, subgraph.width,
					subgraph.height);
		}

		Graph graph = (Graph) getModel();
		graph.setValue(Graph.PARAMETER_SIZE, bounds);

		for (Object child : getChildren()) {
			if (child instanceof VertexEditPart) {
				VertexEditPart part = (VertexEditPart) child;
				part.updateFigures(direction);
			}
		}
	}
}
