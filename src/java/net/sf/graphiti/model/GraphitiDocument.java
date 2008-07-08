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
