package net.sf.graphiti.model;

import org.eclipse.gef.requests.CreationFactory;

/**
 * Gives methods to create new vertices.
 * 
 * @author Samuel Beaussier
 * @author Nicolas Isch
 * @author Matthieu Wipliez
 * 
 */
public class VertexCreationFactory implements CreationFactory {

	private GraphitiDocument document;

	private String type;

	/**
	 * Create a new vertex creation factory.
	 * 
	 * @param document
	 * @param type
	 */
	public VertexCreationFactory(GraphitiDocument document, String type) {
		this.document = document;
		this.type = type;
	}

	@Override
	public Object getNewObject() {
		return new Vertex(document, type);
	}

	@Override
	public Object getObjectType() {
		return Vertex.class;
	}
}
