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

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 * This class provides the configuration for a {@link Graph}. A configuration is
 * defined by an ontology, and contains attributes and parameters that are
 * defined for the graph, vertices and edges.
 * <p>
 * Attributes are specified for classes of objects: for example, all vertices of
 * type T will have an attribute A which has the value V. Examples of such
 * attributes for vertices are "shape" or "color".
 * </p>
 * <p>
 * Parameters are specified for each instance of graph/vertex/edge. Examples of
 * parameters are position or id.
 * </p>
 * 
 * @author Matthieu Wipliez
 * 
 */
public class Configuration {

	/**
	 * Serial version UID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * A edge type name -> edge type object map.
	 */
	private Map<String, EdgeType> edgeTypes;

	/**
	 * The file format associated with this configuration.
	 */
	private FileFormat fileFormat;

	/**
	 * The configuration absolute file name.
	 */
	private String fileName;

	/**
	 * A graph type name -> graph type object map.
	 */
	private Map<String, GraphType> graphTypes;

	/**
	 * The XML namespace of the file format.
	 */
	private String namespace;

	/**
	 * Refinement file extensions associated with this document configuration.
	 */
	private String[] refinementFileExtensions;

	/**
	 * A vertex type name -> vertex type object map.
	 */
	private Map<String, VertexType> vertexTypes;

	/**
	 * Creates a new document configuration with no initial attributes or
	 * parameters.
	 * 
	 * @param fileName
	 *            The name of the file this document configuration is associated
	 *            with.
	 */
	public Configuration(String fileName) {
		this.fileName = fileName;
		edgeTypes = new HashMap<String, EdgeType>();
		graphTypes = new HashMap<String, GraphType>();
		vertexTypes = new HashMap<String, VertexType>();
	}

	/**
	 * Returns the edge type whose name matches the given name.
	 * 
	 * @param name
	 *            The name of the edge type we're looking for.
	 * @return The relevant edge type.
	 */
	public EdgeType getEdgeType(String name) {
		return edgeTypes.get(name);
	}

	/**
	 * Returns the edge types.
	 * 
	 * @return A set of edge types.
	 */
	public Set<EdgeType> getEdgeTypes() {
		return new TreeSet<EdgeType>(edgeTypes.values());
	}

	/**
	 * Returns the file format associated with this configuration.
	 * 
	 * @return A {@link FileFormat} associated with this configuration.
	 */
	public FileFormat getFileFormat() {
		return fileFormat;
	}

	/**
	 * Returns the graph type whose name matches the given name.
	 * 
	 * @param name
	 *            The name of the graph type we're looking for.
	 * @return The relevant graph type.
	 */
	public GraphType getGraphType(String name) {
		return graphTypes.get(name);
	}

	/**
	 * Returns the graph types.
	 * 
	 * @return A set of graph types.
	 */
	public Set<GraphType> getGraphTypes() {
		return new TreeSet<GraphType>(graphTypes.values());
	}

	/**
	 * @return the refinement file extensions
	 */
	public String[] getRefinementFileExtensions() {
		return refinementFileExtensions;
	}

	/**
	 * Returns the vertex type whose name matches the given name.
	 * 
	 * @param name
	 *            The name of the vertex type we're looking for.
	 * @return The relevant vertex type.
	 */
	public VertexType getVertexType(String name) {
		return vertexTypes.get(name);
	}

	/**
	 * Returns the vertex types.
	 * 
	 * @return A set of vertex types.
	 */
	public Set<VertexType> getVertexTypes() {
		return new TreeSet<VertexType>(vertexTypes.values());
	}

	/**
	 * Sets the edge types for this configuration.
	 * 
	 * @param edgeTypes
	 *            A set of {@link EdgeType}.
	 */
	public void setEdgeTypes(Set<EdgeType> edgeTypes) {
		for (EdgeType type : edgeTypes) {
			this.edgeTypes.put(type.getName(), type);
		}
	}

	/**
	 * Sets the file format associated with this configuration.
	 * 
	 * @param format
	 *            A {@link FileFormat}.
	 */
	public void setFileFormat(FileFormat format) {
		fileFormat = format;
	}

	/**
	 * Sets the graph types for this configuration.
	 * 
	 * @param graphTypes
	 *            A set of {@link GraphType}.
	 */
	public void setGraphTypes(Set<GraphType> graphTypes) {
		for (GraphType type : graphTypes) {
			this.graphTypes.put(type.getName(), type);
		}
	}

	/**
	 * Sets this configuration's namespace.
	 * 
	 * @param namespace
	 *            A namespace URI, or <code>""</code>.
	 */
	public void setNamespace(String namespace) {
		this.namespace = namespace;
	}

	/**
	 * Sets the file extensions a refinement may have.
	 * 
	 * @param refinementFileExtensions
	 *            A Set of strings representing file extensions.
	 */
	public void setRefinementFileExtensions(Set<String> refinementFileExtensions) {
		this.refinementFileExtensions = refinementFileExtensions
				.toArray(new String[] {});
	}

	/**
	 * Sets the vertex types for this configuration.
	 * 
	 * @param vertexTypes
	 *            A set of {@link VertexType}.
	 */
	public void setVertexTypes(Set<VertexType> vertexTypes) {
		for (VertexType type : vertexTypes) {
			this.vertexTypes.put(type.getName(), type);
		}
	}

	@Override
	public String toString() {
		return "[" + fileName + "] " + namespace;
	}

}
