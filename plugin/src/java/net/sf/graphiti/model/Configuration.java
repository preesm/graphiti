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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * An edge type -> attributes map.
	 */
	private Map<String, Map<String, Object>> edgeAttributes;

	/**
	 * An edge type -> (parameter name list) map.
	 */
	private Map<String, Map<String, Parameter>> edgeParameters;

	/**
	 * File extensions associated with this document configuration.
	 */
	private String[] fileExtensions;

	/**
	 * A graph type -> attributes map.
	 */
	private Map<String, Map<String, Object>> graphAttributes;

	/**
	 * An graph type -> (parameter name list) map.
	 */
	private Map<String, Map<String, Parameter>> graphParameters;

	/**
	 * File formats that may be associated with a vertex refinement in this
	 * document configuration.
	 */
	private FileFormat[] refinementFileFormats;

	/**
	 * A vertex type -> attributes map.
	 */
	private Map<String, Map<String, Object>> vertexAttributes;

	/**
	 * An vertex type -> (parameter name list) map.
	 */
	private Map<String, Map<String, Parameter>> vertexParameters;

	/**
	 * Creates a new document configuration with no initial attributes or
	 * parameters.
	 * 
	 * @param ontologyUrl
	 *            The URL of the ontology this document configuration is
	 *            associated with.
	 */
	public Configuration(String ontologyUrl) {
		edgeAttributes = new HashMap<String, Map<String, Object>>();
		graphAttributes = new HashMap<String, Map<String, Object>>();
		vertexAttributes = new HashMap<String, Map<String, Object>>();

		vertexParameters = new HashMap<String, Map<String, Parameter>>();
		graphParameters = new HashMap<String, Map<String, Parameter>>();
		edgeParameters = new HashMap<String, Map<String, Parameter>>();
	}
	
	public Set<EdgeType> getEdgeTypes() {
		return new TreeSet<EdgeType>();
	}
	
	public Set<VertexType> getVertexTypes() {
		return new TreeSet<VertexType>();
	}

	/**
	 * Adds a parameter to the list associated with the given edge type.
	 * 
	 * @param edgeType
	 *            The edge type.
	 * @param parameterName
	 *            The parameter name.
	 * @param parameter
	 *            The parameter.
	 */
	public void addEdgeParameter(String edgeType, Parameter parameter) {
		addParameter(edgeParameters, edgeType, parameter);
	}

	/**
	 * Adds a parameter to the list associated with the given graph type.
	 * 
	 * @param graphType
	 *            The graph type.
	 * @param parameterName
	 *            The parameter name.
	 * @param parameter
	 *            The parameter.
	 */
	public void addGraphParameter(String graphType, Parameter parameter) {
		addParameter(graphParameters, graphType, parameter);
	}

	/**
	 * Adds a parameter named <code>parameterName</code> to the list associated
	 * with the type <code>type</code>, in the given map.
	 * 
	 * @param map
	 *            The map to look in.
	 * @param type
	 *            The type represented by a string.
	 * @param parameterName
	 *            The parameter name.
	 */
	private void addParameter(Map<String, Map<String, Parameter>> map,
			String type, Parameter parameter) {
		Map<String, Parameter> parameters = map.get(type);
		if (parameters == null) {
			parameters = new HashMap<String, Parameter>();
			map.put(type, parameters);
		}

		parameters.put(parameter.getName(), parameter);
	}

	/**
	 * Adds a parameter to the list associated with the given vertex type.
	 * 
	 * @param vertexType
	 *            The vertex type.
	 * @param parameterName
	 *            The parameter name.
	 * 
	 * @param parameter
	 *            The parameter.
	 */
	public void addVertexParameter(String vertexType, Parameter parameter) {
		addParameter(vertexParameters, vertexType, parameter);
	}

	/**
	 * Gets the attribute <code>attributeName</code> for the type
	 * <code>type</code>.
	 * 
	 * @param map
	 *            The map to look in.
	 * @param type
	 *            The type represented by a string.
	 * @param attributeName
	 *            The attribute name.
	 * @return The attribute value (or null if not set).
	 */
	private Object getAttribute(Map<String, Map<String, Object>> map,
			String type, String attributeName) {
		Map<String, Object> attributes = map.get(type);
		if (attributes == null) {
			attributes = new HashMap<String, Object>();
			map.put(type, attributes);
		}

		return attributes.get(attributeName);
	}

	/**
	 * Gets the attributes for the type <code>type</code>.
	 * 
	 * @param map
	 *            The map to look in.
	 * @param type
	 *            The type represented by a string.
	 * @return The attributes.
	 */
	private Map<String, Object> getAttributes(
			Map<String, Map<String, Object>> map, String type) {
		Map<String, Object> attributes = map.get(type);
		if (attributes == null) {
			attributes = new HashMap<String, Object>();
			map.put(type, attributes);
		}

		return attributes;
	}

	/**
	 * Returns the attribute value associated with the given edge type and
	 * attribute name.
	 * 
	 * @param edgeType
	 *            The edge type.
	 * @param attributeName
	 *            The attribute name.
	 * @return The attribute value associated.
	 */
	public Object getEdgeAttribute(String edgeType, String attributeName) {
		return getAttribute(edgeAttributes, edgeType, attributeName);
	}

	/**
	 * Returns the parameter associated with the given edge type and parameter
	 * name.
	 * 
	 * @param edgeType
	 *            The edge type.
	 * @return The parameter associated with the edge type and parameter name.
	 */
	public Parameter getEdgeParameter(String edgeType, String parameterName) {
		return getParameter(edgeParameters, edgeType, parameterName);
	}

	/**
	 * Returns the parameters names associated with the given edge type.
	 * 
	 * @param edgeType
	 *            The edge type.
	 * @return The list of parameters associated with the edge type.
	 */
	public List<Parameter> getEdgeParameters(String edgeType) {
		return getParameters(edgeParameters, edgeType);
	}

	/**
	 * @return the fileExtensions
	 */
	public String[] getFileExtensions() {
		return fileExtensions;
	}

	/**
	 * Returns the attribute value associated with the given graph type and
	 * attribute name.
	 * 
	 * @param graphType
	 *            The graph type.
	 * @param attributeName
	 *            The attribute name.
	 * @return The attribute value associated.
	 */
	public Object getGraphAttribute(String graphType, String attributeName) {
		return getAttribute(graphAttributes, graphType, attributeName);
	}

	/**
	 * Returns the parameters names associated with the given graph type.
	 * 
	 * @param graphType
	 *            The graph type.
	 * @return The list of parameters associated with the graph type.
	 */
	public List<Parameter> getGraphParameters(String graphType) {
		return getParameters(graphParameters, graphType);
	}

	/**
	 * Gets the parameter for the type <code>type</code> which has the given
	 * name.
	 * 
	 * @param map
	 *            The map to look in.
	 * @param type
	 *            The type represented by a string.
	 * @param name
	 *            The parameter name.
	 * @return The parameter.
	 */
	private Parameter getParameter(Map<String, Map<String, Parameter>> map,
			String type, String parameterName) {
		Map<String, Parameter> parameters = map.get(type);
		if (parameters == null) {
			parameters = new HashMap<String, Parameter>();
			map.put(type, parameters);
		}

		return parameters.get(parameterName);
	}

	/**
	 * Gets the parameter list for the type <code>type</code>.
	 * 
	 * @param map
	 *            The map to look in.
	 * @param type
	 *            The type represented by a string.
	 * @return The parameter list.
	 */
	private List<Parameter> getParameters(
			Map<String, Map<String, Parameter>> map, String type) {
		Map<String, Parameter> parameters = map.get(type);
		if (parameters == null) {
			parameters = new HashMap<String, Parameter>();
			map.put(type, parameters);
		}

		return new ArrayList<Parameter>(parameters.values());
	}

	/**
	 * @return the refinementFileFormats
	 */
	public FileFormat[] getRefinementFileFormats() {
		return refinementFileFormats;
	}

	/**
	 * Returns the attribute value associated with the given vertex type and
	 * attribute name.
	 * 
	 * @param vertexType
	 *            The vertex type.
	 * @param attributeName
	 *            The attribute name.
	 * @return The attribute value associated.
	 */
	public Object getVertexAttribute(String vertexType, String attributeName) {
		return getAttribute(vertexAttributes, vertexType, attributeName);
	}

	/**
	 * Returns the attributes associated with the given vertex type.
	 * 
	 * @param vertexType
	 *            The vertex type.
	 * @return The attributes associated.
	 */
	public Map<String, Object> getVertexAttributes(String vertexType) {
		return getAttributes(vertexAttributes, vertexType);
	}

	/**
	 * Returns the parameter associated with the given vertex type and parameter
	 * name.
	 * 
	 * @param vertexType
	 *            The vertex type.
	 * @return The parameters associated with the vertex type and parameter
	 *         name.
	 */
	public Parameter getVertexParameter(String vertexType, String parameterName) {
		return getParameter(vertexParameters, vertexType, parameterName);
	}

	/**
	 * Returns the parameters names associated with the given vertex type.
	 * 
	 * @param vertexType
	 *            The vertex type.
	 * @return The list of parameters associated with the vertex type.
	 */
	public List<Parameter> getVertexParameters(String vertexType) {
		return getParameters(vertexParameters, vertexType);
	}

	/**
	 * Sets the attribute <code>attributeName</code> to <code>newValue</code>
	 * for the type <code>type</code>.
	 * 
	 * @param map
	 *            The map to look in.
	 * @param type
	 *            The type represented by a string.
	 * @param attributeName
	 *            The attribute name.
	 * @param newValue
	 *            The new property value.
	 */
	private void setAttribute(Map<String, Map<String, Object>> map,
			String type, String attributeName, Object newValue) {
		Map<String, Object> attributes = map.get(type);
		if (attributes == null) {
			attributes = new HashMap<String, Object>();
			map.put(type, attributes);
		}

		attributes.put(attributeName, newValue);
	}

	/**
	 * Sets the attribute <code>attributeName</code> to <code>newValue</code>
	 * for the edge type <code>edgeType</code>.
	 * 
	 * @param edgeType
	 *            The edge type represented by a string.
	 * @param attributeName
	 *            The attribute name.
	 * @param newValue
	 *            The new property value.
	 */
	public void setEdgeAttribute(String edgeType, String attributeName,
			Object newValue) {
		setAttribute(edgeAttributes, edgeType, attributeName, newValue);
	}

	/**
	 * @param fileExtensions
	 *            the fileExtensions to set
	 */
	public void setFileExtensions(Set<String> fileExtensions) {
		this.fileExtensions = fileExtensions.toArray(new String[] {});
	}

	/**
	 * Sets the attribute <code>attributeName</code> to <code>newValue</code>
	 * for the graph type <code>graphType</code>.
	 * 
	 * @param graphType
	 *            The graph type represented by a string.
	 * @param attributeName
	 *            The attribute name.
	 * @param newValue
	 *            The new property value.
	 */
	public void setGraphAttribute(String graphType, String attributeName,
			Object newValue) {
		setAttribute(graphAttributes, graphType, attributeName, newValue);
	}

	/**
	 * @param refinementFileFormats
	 *            the refinementFileFormats to set
	 */
	public void setRefinementFileFormats(Set<FileFormat> refinementFileFormats) {
		this.refinementFileFormats = refinementFileFormats
				.toArray(new FileFormat[] {});
	}

	/**
	 * Sets the attribute <code>attributeName</code> to <code>newValue</code>
	 * for the vertex type <code>vertexType</code>.
	 * 
	 * @param vertexType
	 *            The vertex type represented by a string.
	 * @param attributeName
	 *            The attribute name.
	 * @param newValue
	 *            The new attribute value.
	 */
	public void setVertexAttribute(String vertexType, String attributeName,
			Object newValue) {
		setAttribute(vertexAttributes, vertexType, attributeName, newValue);
	}

	@Override
	public String toString() {
		return "";
	}

}
