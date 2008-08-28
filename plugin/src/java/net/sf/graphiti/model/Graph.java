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
package net.sf.graphiti.model;

import java.util.Calendar;
import java.util.Set;

import org.jgrapht.graph.AbstractBaseGraph;
import org.jgrapht.graph.DirectedMultigraph;
import org.jgrapht.graph.Multigraph;

/**
 * This class is an attributed multigraph (allowing more than one connection
 * between any two vertices). It may be directed or not, as specified at
 * creation.
 * 
 * @author Jonathan Piat
 * @author Matthieu Wipliez
 * 
 */
public class Graph extends PropertyBean {

	public static final String PARAMETER_ID = "id";

	/**
	 * String for the "size" parameter. Defines the vertex size.
	 */
	public static final String PARAMETER_SIZE = "size";

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Returns a unique identifier. Actually this is just the current time minus
	 * the current calendar with minute, second and millisecond field zeroed.
	 * 
	 * @return A unique identifier.
	 */
	public static String generateUniqueId() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		long ref = calendar.getTimeInMillis();

		return Long.toString(System.currentTimeMillis() - ref);
	}

	/**
	 * The configuration associated with this graph.
	 */
	Configuration configuration;

	private AbstractBaseGraph<Vertex, Edge> graph;

	/**
	 * Creates a new directed graph.
	 * 
	 * @param config
	 *            The configuration to use with this graph.
	 */
	public Graph(Configuration config) {
		this(config, true);
	}

	/**
	 * Creates a new graph, directed or not.
	 * 
	 * @param directed
	 *            Specifies whether the graph should be directed or not.
	 * @param config
	 *            The configuration to use with this graph.
	 */
	public Graph(Configuration config, boolean directed) {
		configuration = config;
		if (directed) {
			graph = new DirectedMultigraph<Vertex, Edge>(Edge.class);
		} else {
			graph = new Multigraph<Vertex, Edge>(Edge.class);
		}
	}

	/**
	 * 
	 * @param edge
	 *            The edge to add
	 * @return
	 */
	public boolean addEdge(Edge edge) {
		Vertex source = edge.getSource();
		Vertex target = edge.getTarget();
		boolean res = graph.addEdge(source, target, edge);
		source.firePropertyChange(Vertex.PROPERTY_SRC_VERTEX, null, source);
		target.firePropertyChange(Vertex.PROPERTY_DST_VERTEX, null, target);
		return res;
	}

	/**
	 * @see AbstractBaseGraph#addVertex(Vertex)
	 * @param child
	 *            The vertex to add
	 * @return true if the vertex is added, false if command failed
	 */
	public boolean addVertex(Vertex child) {
		boolean res = graph.addVertex(child);
		child.parent = this;
		firePropertyChange(PropertyBean.PROPERTY_ADD, null, child);
		return res;
	}

	/**
	 * @see AbstractBaseGraph#edgeSet()
	 * @return
	 */
	public Set<Edge> edgeSet() {
		return graph.edgeSet();
	}

	/**
	 * Returns the document configuration associated with this Graph.
	 * 
	 * @return The document configuration associated with this Graph.
	 */
	public Configuration getDocumentConfiguration() {
		return configuration;
	}

	/**
	 * @see AbstractBaseGraph#incomingEdgesOf(Vertex)
	 * @param vertex
	 * @return
	 */
	public Set<Edge> incomingEdgesOf(Vertex vertex) {
		return graph.incomingEdgesOf(vertex);
	}

	/**
	 * @see AbstractBaseGraph#outgoingEdgesOf(Vertex)
	 * @param vertex
	 * @return
	 */
	public Set<Edge> outgoingEdgesOf(Vertex vertex) {
		return graph.outgoingEdgesOf(vertex);
	}

	/**
	 * @see AbstractBaseGraph#removeEdge(Edge)
	 * @param edge
	 * @return
	 */
	public boolean removeEdge(Edge edge) {
		Vertex source = edge.getSource();
		Vertex target = edge.getTarget();
		boolean res = graph.removeEdge(edge);
		source.firePropertyChange(Vertex.PROPERTY_SRC_VERTEX, source, null);
		target.firePropertyChange(Vertex.PROPERTY_DST_VERTEX, target, null);
		return res;
	}

	/**
	 * @see AbstractBaseGraph#removeVertex(Graph)
	 * @param child
	 * @return
	 */
	public boolean removeVertex(Vertex child) {
		boolean res = graph.removeVertex(child);
		child.parent = null;
		firePropertyChange(PropertyBean.PROPERTY_REMOVE, null, child);
		return res;
	}

	/**
	 * @see AbstractBaseGraph#vertexSet()
	 * @return
	 */
	public Set<Vertex> vertexSet() {
		return graph.vertexSet();
	}

}
