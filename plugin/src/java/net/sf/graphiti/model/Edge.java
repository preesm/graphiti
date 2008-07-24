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

/**
 * This class represents an edge.
 * 
 * @author Jonathan Piat
 * @author Matthieu Wipliez
 * 
 */
public class Edge extends DOMNode {

	public static final String DST_PORT_NAME = "dstPortName";

	/**
	 * 
	 */
	private static final long serialVersionUID = 863045957077215818L;

	public static final String SRC_PORT_NAME = "srcPortName";

	/**
	 * The document containing this vertex
	 */
	private GraphitiDocument parentDocument;

	private Vertex source;

	private Vertex target;

	/**
	 * Creates a new un-connected edge.
	 * 
	 * @param doc
	 *            The parent Document.
	 */
	public Edge(GraphitiDocument doc) {
		parentDocument = doc;
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
		parentDocument = source.parentDocument;
	}

	/**
	 * Returns this parent document.
	 * 
	 * @return This parent document.
	 */
	public GraphitiDocument getParentDocument() {
		return parentDocument;
	}

	public Vertex getSource() {
		return source;
	}

	public Vertex getTarget() {
		return target;
	}

	public void setSource(Vertex source) {
		this.source = source;
	}

	public void setTarget(Vertex target) {
		this.target = target;
	}
}