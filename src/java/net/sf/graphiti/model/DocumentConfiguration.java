/**
 * 
 */
package net.sf.graphiti.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.graphiti.ontology.OntologyFactory;

/**
 * This class provides the configuration for a GraphitiDocument. A configuration
 * is defined by an ontology, and contains attributes and parameters that are
 * defined for graphs, vertices and edges.
 * <p>
 * Attributes are specified for classes of objects: for example, all vertices of
 * type T will have an attribute A which has the value V. Examples of such
 * attributes for vertices are "shape" or "color".
 * </p>
 * <p>
 * Parameters are specified for each instance of graph/vertex/edge.
 * </p>
 * 
 * @author Matthieu Wipliez
 * 
 */
public class DocumentConfiguration {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * An edge type -> attributes map.
	 */
	private Map<String, PropertyBean> edgeAttributes;

	/**
	 * An edge type -> (parameter name list) map.
	 */
	private Map<String, List<ParameterProperty>> edgeParameters;

	/**
	 * A graph type -> attributes map.
	 */
	private Map<String, PropertyBean> graphAttributes;

	/**
	 * An graph type -> (parameter name list) map.
	 */
	private Map<String, List<ParameterProperty>> graphParameters;

	/**
	 * The ontology factory this document configuration is associated with.
	 */
	private OntologyFactory ontologyFactory;

	/**
	 * The URL of the ontology this document configuration is associated with.
	 */
	private String ontologyUrl;

	/**
	 * A vertex type -> attributes map.
	 */
	private Map<String, PropertyBean> vertexAttributes;

	/**
	 * An vertex type -> (parameter name list) map.
	 */
	private Map<String, List<ParameterProperty>> vertexParameters;

	/**
	 * Creates a new document configuration with no initial attributes or
	 * parameters.
	 * 
	 * @param ontologyUrl
	 *            The URL of the ontology this document configuration is
	 *            associated with.
	 */
	public DocumentConfiguration(String ontologyUrl) {
		this.ontologyUrl = ontologyUrl;
		ontologyFactory = new OntologyFactory(ontologyUrl);

		edgeAttributes = new HashMap<String, PropertyBean>();
		graphAttributes = new HashMap<String, PropertyBean>();
		vertexAttributes = new HashMap<String, PropertyBean>();

		vertexParameters = new HashMap<String, List<ParameterProperty>>();
		graphParameters = new HashMap<String, List<ParameterProperty>>();
		edgeParameters = new HashMap<String, List<ParameterProperty>>();
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
	public void addEdgeParameter(String edgeType, ParameterProperty parameter) {
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
	public void addGraphParameter(String graphType, ParameterProperty parameter) {
		addParameter(graphParameters, graphType, parameter);
	}

	/**
	 * Adds a parameter named <code>parameterName</code> to the list
	 * associated with the type <code>type</code>, in the given map.
	 * 
	 * @param map
	 *            The map to look in.
	 * @param type
	 *            The type represented by a string.
	 * @param parameterName
	 *            The parameter name.
	 */
	private void addParameter(Map<String, List<ParameterProperty>> map,
			String type, ParameterProperty parameter) {
		List<ParameterProperty> parameters = map.get(type);
		if (parameters == null) {
			parameters = new ArrayList<ParameterProperty>();
			map.put(type, parameters);
		}

		parameters.add(parameter);
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
	public void addVertexParameter(String vertexType,
			ParameterProperty parameter) {
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
	private Object getAttribute(Map<String, PropertyBean> map, String type,
			String attributeName) {
		PropertyBean attributes = map.get(type);
		if (attributes == null) {
			attributes = new PropertyBean();
			map.put(type, attributes);
		}

		return attributes.getValue(attributeName);
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
	 * Returns the parameters names associated with the given edge type.
	 * 
	 * @param edgeType
	 *            The edge type.
	 * @return The list of parameters associated with the edge type.
	 */
	public List<ParameterProperty> getEdgeParameters(String edgeType) {
		return getParameters(edgeParameters, edgeType);
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
	public List<ParameterProperty> getGraphParameters(String graphType) {
		return getParameters(graphParameters, graphType);
	}

	/**
	 * Returns the ontology factory this document is associated with.
	 * 
	 * @return The ontology factory this document is associated with.
	 */
	public OntologyFactory getOntologyFactory() {
		return ontologyFactory;
	}

	/**
	 * Returns the URL of the ontology this document configuration is associated
	 * with.
	 * 
	 * @return The URL of the ontology this document configuration is associated
	 *         with.
	 */
	public String getOntologyUrl() {
		return ontologyUrl;
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
	private List<ParameterProperty> getParameters(
			Map<String, List<ParameterProperty>> map, String type) {
		List<ParameterProperty> parameters = map.get(type);
		if (parameters == null) {
			parameters = new ArrayList<ParameterProperty>();
			map.put(type, parameters);
		}

		return parameters;
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
	 * Returns the parameters names associated with the given vertex type.
	 * 
	 * @param vertexType
	 *            The vertex type.
	 * @return The list of parameters associated with the vertex type.
	 */
	public List<ParameterProperty> getVertexParameters(String vertexType) {
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
	private void setAttribute(Map<String, PropertyBean> map, String type,
			String attributeName, Object newValue) {
		PropertyBean attributes = map.get(type);
		if (attributes == null) {
			attributes = new PropertyBean();
			map.put(type, attributes);
		}

		attributes.setValue(attributeName, newValue);
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

}
