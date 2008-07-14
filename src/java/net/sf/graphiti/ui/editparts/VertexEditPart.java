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
import java.util.Set;

import net.sf.graphiti.model.DocumentConfiguration;
import net.sf.graphiti.model.Graph;
import net.sf.graphiti.model.Vertex;
import net.sf.graphiti.ui.editpolicies.DeleteComponentEditPolicy;
import net.sf.graphiti.ui.editpolicies.LayoutPolicy;
import net.sf.graphiti.ui.editpolicies.NodeEditPolicy;
import net.sf.graphiti.ui.figure.VertexFigure;

import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Insets;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.draw2d.graph.CompoundDirectedGraphLayout;
import org.eclipse.draw2d.graph.Edge;
import org.eclipse.draw2d.graph.EdgeList;
import org.eclipse.draw2d.graph.Node;
import org.eclipse.draw2d.graph.NodeList;
import org.eclipse.draw2d.graph.Subgraph;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.NodeEditPart;
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
public class VertexEditPart extends AbstractGraphicalEditPart implements
		PropertyChangeListener, NodeEditPart {

	/**
	 * This attribute is set by {@link GraphEditPart#addNodes}.
	 */
	private Node node;

	@Override
	public void activate() {
		super.activate();
		((Vertex) getModel()).addPropertyChangeListener(this);
	}

	/**
	 * Adds edges of this EditPart to the {@link EdgeList} of the parent.
	 * 
	 * @param edges
	 */
	@SuppressWarnings("unchecked")
	void addEdges(EdgeList edges) {
		List connections = new ArrayList<EdgeEditPart>();
		connections.addAll(getSourceConnections());
		// connections.addAll(getTargetConnections());

		for (Object connection : connections) {
			if (connection instanceof EdgeEditPart) {
				EdgeEditPart dependency = (EdgeEditPart) connection;
				VertexEditPart source = (VertexEditPart) dependency.getSource();
				VertexEditPart target = (VertexEditPart) dependency.getTarget();

				if ((source != null && target != null) && (source != target)) {
					Edge edge = new Edge(source.node, target.node);
					edges.add(edge);
				}
			}
		}
	}

	/**
	 * Adds this node to its parent. In the future will also add its children if
	 * it has any.
	 * 
	 * @param nodes
	 *            The list of {@link Node} in the Draw2D Graph.
	 * @param parent
	 *            Its parent subgraph.
	 */
	@SuppressWarnings("unchecked")
	void addNodes(NodeList nodes, Subgraph parent) {
		node = new Node(this, parent);
		nodes.add(node);

		// Graphical stuff
		Figure figure = (Figure) getFigure();
		node.setSize(figure.getPreferredSize());
		node.setPadding(new Insets(20, 20, 20, 20));
	}

	@Override
	protected void createEditPolicies() {
		installEditPolicy(EditPolicy.COMPONENT_ROLE,
				new DeleteComponentEditPolicy());
		installEditPolicy(EditPolicy.LAYOUT_ROLE, new LayoutPolicy());
		installEditPolicy(EditPolicy.GRAPHICAL_NODE_ROLE, new NodeEditPolicy());
	}

	@Override
	protected IFigure createFigure() {
		Vertex vertex = (Vertex) getModel();
		DocumentConfiguration config = vertex.getParentDocument()
				.getDocumentConfiguration();

		VertexFigure figure = new VertexFigure(config, vertex.getType());
		String name = (String) vertex.getValue("name");
		if (name == null) {
			name = (String) vertex.getValue("id");
			if (name == null)
				name = "";
		}
		figure.setName(name);
		return figure;
	}

	@Override
	public void deactivate() {
		super.deactivate();
		((Vertex) getModel()).removePropertyChangeListener(this);
	}

	@Override
	@SuppressWarnings("unchecked")
	protected List getModelSourceConnections() {
		if (getModel() instanceof Vertex) {
			Vertex vertex = (Vertex) getModel();
			Graph parent = vertex.getParent();

			// we get the *output* dependencies of vertex
			Set<net.sf.graphiti.model.Edge> edges = parent
					.outgoingEdgesOf(vertex);
			List dependencies = new ArrayList(edges);
			return dependencies;
		}

		return null;
	}

	@Override
	@SuppressWarnings("unchecked")
	protected List getModelTargetConnections() {
		if (getModel() instanceof Vertex) {
			Vertex vertex = (Vertex) getModel();
			Graph parent = vertex.getParent();

			// we get the *input* dependencies of vertex
			Set<net.sf.graphiti.model.Edge> edges = parent
					.incomingEdgesOf(vertex);
			List dependencies = new ArrayList(edges);
			return dependencies;
		}

		return null;
	}

	@Override
	public ConnectionAnchor getSourceConnectionAnchor(
			ConnectionEditPart connEditPart) {
		return ((VertexFigure) getFigure()).getConnectionAnchor();
	}

	@Override
	public ConnectionAnchor getSourceConnectionAnchor(Request request) {
		return ((VertexFigure) getFigure()).getConnectionAnchor();
	}

	@Override
	public ConnectionAnchor getTargetConnectionAnchor(
			ConnectionEditPart connEditPart) {
		return ((VertexFigure) getFigure()).getConnectionAnchor();
	}

	@Override
	public ConnectionAnchor getTargetConnectionAnchor(Request request) {
		return ((VertexFigure) getFigure()).getConnectionAnchor();
	}

	public void propertyChange(PropertyChangeEvent evt) {
		if (evt.getPropertyName().equals(Vertex.PARAMETER_ID)) {
			VertexFigure figure = (VertexFigure) getFigure();
			figure.setName((String) evt.getNewValue());
			// refresh();
		} else if (evt.getPropertyName().equals(Vertex.PARAMETER_SIZE)) {
			VertexFigure vertexFigure = (VertexFigure) getFigure();
			vertexFigure.setBounds((Rectangle) evt.getNewValue());
			refresh();
		} else {
			refresh();
		}
	}

	@Override
	protected void refreshVisuals() {
		Vertex vertex = (Vertex) getModel();
		Rectangle bounds = (Rectangle) vertex.getValue(Vertex.PARAMETER_SIZE);
		if (bounds != null) {
			// bounds is null when the vertex is created
			getFigure().setBounds(bounds);
		}
	}

	/**
	 * This method is called by the {@link GraphLayoutManager}, and applies back
	 * the changes of the {@link CompoundDirectedGraphLayout} algorithm to the
	 * different figures, by setting their bounds.
	 */
	void updateFigures() {
		Vertex vertex = (Vertex) getModel();
		Rectangle bounds = new Rectangle(node.x, node.y, node.width,
				node.height);
		vertex.setValue(Vertex.PARAMETER_SIZE, bounds);
	}
}
