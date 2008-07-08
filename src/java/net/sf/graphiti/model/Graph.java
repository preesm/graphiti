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
public class Graph extends DOMNode {

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

	private AbstractBaseGraph<Vertex, Edge> graph;

	/**
	 * The document containing this vertex
	 */
	GraphitiDocument parentDocument;

	/**
	 * Creates a new graph, directed or not.
	 * 
	 * @param directed
	 *            Specifies whether the graph should be directed or not.
	 * @param doc
	 *            The parent document.
	 */
	public Graph(boolean directed, GraphitiDocument doc) {
		if (directed) {
			graph = new DirectedMultigraph<Vertex, Edge>(Edge.class);
		} else {
			graph = new Multigraph<Vertex, Edge>(Edge.class);
		}
		parentDocument = doc;
	}

	/**
	 * Creates a new directed graph.
	 * 
	 * @param doc
	 *            The parent document.
	 */
	public Graph(GraphitiDocument doc) {
		this(true, doc);
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
		source.setValue(Vertex.PROPERTY_SRC_VERTEX, null, source);
		target.setValue(Vertex.PROPERTY_DST_VERTEX, null, target);
		return res;
	}

	/**
	 * @see AbstractBaseGraph#addVertex(Vertex)
	 * @param child The vertex to add
	 * @return true if the vertex is added, false if command failed
	 */
	public boolean addVertex(Vertex child) {
		boolean res = graph.addVertex(child);
		child.parent = this;
		setValue(PropertyBean.PROPERTY_ADD, null, child);
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
	 * Give this parent document
	 * 
	 * @return
	 */
	public GraphitiDocument getParentDocument() {
		return parentDocument;
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
		source.setValue(Vertex.PROPERTY_SRC_VERTEX, source, null);
		target.setValue(Vertex.PROPERTY_DST_VERTEX, target, null);
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
		setValue(PropertyBean.PROPERTY_REMOVE, null, child);
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
