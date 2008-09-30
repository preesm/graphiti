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

import java.util.List;

/**
 * This class represents an edge.
 * 
 * @author Jonathan Piat
 * @author Matthieu Wipliez
 * 
 */
public class Edge extends PropertyBean {

	/**
	 * String for the "source port" parameter. Defines the edge source port (if
	 * any).
	 */
	public static final String PARAMETER_SOURCE_PORT = "source port";

	/**
	 * String for the "target port" parameter. Defines the edge target port (if
	 * any).
	 */
	public static final String PARAMETER_TARGET_PORT = "target port";

	/**
	 * String for the "type" parameter. Defines the edge type.
	 */
	public static final String PARAMETER_TYPE = "type";

	/**
	 * Serial ID.
	 */
	private static final long serialVersionUID = 863045957077215818L;

	private Vertex source;

	private Vertex target;

	/**
	 * Creates a new unconnected edge.
	 */
	public Edge() {
		this("");
	}

	/**
	 * Creates a new edge which is a copy of the given edge.
	 * 
	 * @param edge
	 *            The source edge.
	 */
	public Edge(Edge edge) {
		super(edge);
	}

	/**
	 * Creates a new unconnected edge with the given type.
	 * 
	 * @param type
	 *            The edge type.
	 */
	public Edge(String type) {
		setValue(PARAMETER_TYPE, type);
	}

	/**
	 * Creates an edge with the specified source and target.
	 * 
	 * @param source
	 *            The source vertex.
	 * @param target
	 *            The target vertex.
	 */
	public Edge(Vertex source, Vertex target) {
		this.source = source;
		this.target = target;
	}

	/**
	 * Returns the configuration associated with this edge.
	 * 
	 * @return A {@link Configuration}.
	 */
	public Configuration getConfiguration() {
		if (source != null) {
			return source.getConfiguration();
		} else if (target != null) {
			return target.getConfiguration();
		} else {
			return null;
		}
	}

	/**
	 * Returns the parameter associated with this edge type and the given
	 * parameter name.
	 * 
	 * @param parameterName
	 *            The parameter name.
	 * @return The parameter.
	 */
	public Parameter getParameter(String parameterName) {
		Configuration configuration = getConfiguration();
		return configuration.getEdgeParameter(getType(), parameterName);
	}

	/**
	 * Returns a list of parameters associated with this edge type.
	 * 
	 * @return A List of Parameters.
	 */
	public List<Parameter> getParameters() {
		Configuration configuration = getConfiguration();
		return configuration.getEdgeParameters(getType());
	}

	/**
	 * Returns this edge's source.
	 * 
	 * @return This edge's source.
	 */
	public Vertex getSource() {
		return source;
	}

	/**
	 * Returns this edge's target.
	 * 
	 * @return This edge's target.
	 */
	public Vertex getTarget() {
		return target;
	}

	/**
	 * Returns this edge's type.
	 * 
	 * @return This edge's type.
	 */
	public String getType() {
		return (String) this.getValue(PARAMETER_TYPE);
	}

	/**
	 * Sets this edge's source.
	 * 
	 * @param source
	 *            A {@link Vertex}.
	 */
	public void setSource(Vertex source) {
		this.source = source;
	}

	/**
	 * Sets this edge's target.
	 * 
	 * @param target
	 *            A {@link Vertex}.
	 */
	public void setTarget(Vertex target) {
		this.target = target;
	}

	@Override
	public String toString() {
		return getType() + ": " + getSource() + " - " + getTarget() + " "
				+ super.hashCode();
	}
}
