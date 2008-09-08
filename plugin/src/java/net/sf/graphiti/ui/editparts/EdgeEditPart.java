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

import net.sf.graphiti.model.Edge;
import net.sf.graphiti.model.Graph;
import net.sf.graphiti.model.Vertex;
import net.sf.graphiti.ui.editpolicies.DependencyEditPolicy;
import net.sf.graphiti.ui.editpolicies.DependencyEndPointEditPolicy;
import net.sf.graphiti.ui.figure.EdgeFigure;

import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.editparts.AbstractConnectionEditPart;

/**
 * The EditPart associated to the Dependency gives methods to refresh the view
 * when a property has changed.
 * 
 * @author Samuel Beaussier
 * @author Nicolas Isch
 * 
 */
public class EdgeEditPart extends AbstractConnectionEditPart implements
		PropertyChangeListener {

	@Override
	public void activate() {
		super.activate();
		((Edge) getModel()).addPropertyChangeListener(this);
	}

	@Override
	protected void createEditPolicies() {
		installEditPolicy(EditPolicy.CONNECTION_ENDPOINTS_ROLE,
				new DependencyEndPointEditPolicy());
		installEditPolicy(EditPolicy.CONNECTION_ROLE,
				new DependencyEditPolicy());
		// refreshBendpointEditPolicy();
	}

	/**
	 * Creates a new DependencyGef figure
	 */
	@Override
	protected IFigure createFigure() {
		Edge model = (Edge) getModel();
		return new EdgeFigure(model);
	}

	@Override
	public void deactivate() {
		super.deactivate();
		((Edge) getModel()).removePropertyChangeListener(this);
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		// bendpoints, connection router properties
		EdgeFigure figure = (EdgeFigure) getFigure();
		figure.refresh(evt.getPropertyName(), evt.getNewValue());

		// get source, target and graph of model
		Edge model = (Edge) getModel();
		Vertex source = model.getSource();
		Vertex target = model.getTarget();
		Graph graph = source.getParent();

		// remove and add all edges of source and target
		// I know this is not the most beautiful way to have proper anchor placement
		// but honestly I don't care :)
		List<Edge> edges = new ArrayList<Edge>();
		edges.addAll(graph.outgoingEdgesOf(source));
		edges.addAll(graph.incomingEdgesOf(target));
		for (Edge edge : edges) {
			graph.removeEdge(edge);
			graph.addEdge(edge);
		}
	}

	void updateFigures(int direction) {
		ConnectionAnchor anchor = ((EdgeFigure) getFigure()).getSourceAnchor();
		((VertexConnectionAnchor) anchor).setDirection(direction);

		anchor = ((EdgeFigure) getFigure()).getTargetAnchor();
		((VertexConnectionAnchor) anchor).setDirection(direction);
	}
}