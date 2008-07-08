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
