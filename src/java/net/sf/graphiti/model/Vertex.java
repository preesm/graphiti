/**
 * 
 */
package net.sf.graphiti.model;

/**
 * This class represents a vertex.
 * 
 * @author Matthieu Wipliez
 * 
 */
public class Vertex extends DOMNode implements Cloneable {

	/**
	 * String for the "color" attribute. Defines the vertex color.
	 */
	public static final String ATTRIBUTE_COLOR = "color";

	/**
	 * String for the "height" attribute. Defines the vertex height.
	 */
	public static final String ATTRIBUTE_HEIGHT = "height";

	/**
	 * String for the "shape" attribute. Defines the vertex shape.
	 */
	public static final String ATTRIBUTE_SHAPE = "shape";

	/**
	 * String for the "width" attribute. Defines the vertex width.
	 */
	public static final String ATTRIBUTE_WIDTH = "width";

	/**
	 * String for the "id" parameter. Defines the vertex id.
	 */
	public static final String PARAMETER_ID = "id";

	/**
	 * String for the "size" parameter. Defines the vertex size.
	 */
	public static final String PARAMETER_SIZE = "size";

	/**
	 * String for the "type" parameter. Defines the vertex type.
	 */
	public static final String PARAMETER_TYPE = "type";

	/**
	 * String for the "destination vertex" property. Set when a vertex becomes
	 * the destination of a dependency.
	 */
	public static final String PROPERTY_DST_VERTEX = "destination vertex";

	/**
	 * String for the "source vertex" property. Set when a vertex becomes the
	 * source of a dependency.
	 */
	public static final String PROPERTY_SRC_VERTEX = "source vertex";

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The parent graph of this vertex.
	 */
	Graph parent;

	/**
	 * The document containing this vertex
	 */
	GraphitiDocument parentDocument;

	/**
	 * Creates a new Vertex with no type.
	 * 
	 * @param doc
	 *            The vertex document.
	 *            
	 */
	public Vertex(GraphitiDocument doc) {
		this.setValue(PARAMETER_TYPE, "");
		parentDocument = doc;
	}

	/**
	 * Creates a vertex with the given type.
	 * 
	 * @param document
	 *            The vertex document.
	 * @param type
	 *            The vertex type.
	 */
	public Vertex(GraphitiDocument document, String type) {
		this.setValue(PARAMETER_TYPE, type);
		parentDocument = document;
	}

	public Object clone() {
		try {
			return super.clone();
		} catch (CloneNotSupportedException e) {
			return null;
		}
	}

	/**
	 * Returns the parent {@link Graph} of this Vertex.
	 * 
	 * @return The parent {@link Graph} of this Vertex.
	 */
	public Graph getParent() {
		return parent;
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
	 * Returns this vertex's type.
	 * 
	 * @return This vertex's type.
	 */
	public String getType() {
		return (String) this.getValue(PARAMETER_TYPE);
	}

	public String toString() {
		return getType() + " : " + getNodeName();
	}

}
