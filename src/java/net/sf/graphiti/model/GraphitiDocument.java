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
 * This class is the root class of everything. It contains a graph, and
 * properties that are graph, vertex and edge agnostic.
 * 
 * @author Jonathan Piat
 * @author Matthieu Wipliez
 * 
 */
public class GraphitiDocument extends DOMNode {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private DocumentConfiguration configuration;

	private Graph graph;

	/**
	 * Creates an empty document.
	 * 
	 * @param configuration
	 *            The configuration to use with this document
	 * 
	 */
	public GraphitiDocument(DocumentConfiguration configuration) {
		graph = new Graph(this);
		this.configuration = configuration;
	}

	/**
	 * Creates a document with a graph.
	 * 
	 * @param graph
	 *            The graph.
	 * @param configuration
	 *            The configuration to use with this document
	 */
	public GraphitiDocument(DocumentConfiguration configuration, Graph graph) {
		this.graph = graph;
		this.configuration = configuration;
	}

	/**
	 * Returns this document configuration.
	 * 
	 * @return This document configuration.
	 */
	public DocumentConfiguration getDocumentConfiguration() {
		return configuration;
	}

	/**
	 * Returns the graph owned by this document.
	 * 
	 * @return The graph owned by this document.
	 */
	public Graph getGraph() {
		return graph;
	}

	/**
	 * 
	 * @param document
	 */
	public void setDocumentConfiguration(DocumentConfiguration document) {
		configuration = document;
	}

	/**
	 * Sets this document's graph
	 * 
	 * @param graph
	 */
	public void setGraph(Graph graph) {
		this.graph = graph;
	}

}
