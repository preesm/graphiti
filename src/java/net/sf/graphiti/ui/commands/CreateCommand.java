package net.sf.graphiti.ui.commands;

import net.sf.graphiti.model.DocumentConfiguration;
import net.sf.graphiti.model.Graph;
import net.sf.graphiti.model.GraphitiDocument;
import net.sf.graphiti.model.Vertex;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.commands.Command;

/**
 * This class allows the creation of vertices.
 * 
 * @author Samuel Beaussier
 * @author Nicolas Isch
 * @author Matthieu Wipliez
 * 
 */
public class CreateCommand extends Command {

	private Rectangle bounds;

	private Object model;

	private Object newObject;

	public CreateCommand() {

	}

	@Override
	public void execute() {
		if (model instanceof Graph) {
			Graph graph = (Graph) model;

			if (newObject instanceof Vertex) {
				Vertex vertex = (Vertex) newObject;
				graph.addVertex(vertex);

				GraphitiDocument doc = graph.getParentDocument();
				DocumentConfiguration config = doc.getDocumentConfiguration();
				Integer width = (Integer) config.getVertexAttribute(vertex
						.getType(), Vertex.ATTRIBUTE_WIDTH);
				Integer height = (Integer) config.getVertexAttribute(vertex
						.getType(), Vertex.ATTRIBUTE_HEIGHT);

				if (width == null) {
					width = 50;
				}
				if (height == null) {
					height = 50;
				}

				Rectangle rect = new Rectangle(bounds);
				rect.width = width;
				rect.height = height;
				vertex.setValue(Vertex.PARAMETER_SIZE, null, rect);
			}
		}
	}

	/**
	 * Sets the initial bounds of this graph.
	 * 
	 * @param bounds
	 */
	public void setBounds(Rectangle bounds) {
		this.bounds = bounds;
	}

	/**
	 * Sets this command model.
	 * 
	 * @param model
	 *            The model to use.
	 */
	public void setModel(Object model) {
		this.model = model;
	}

	/**
	 * Sets the new object that should be added to the model.
	 * 
	 * @param newObject
	 *            the newly created object.
	 */
	public void setNewObject(Object newObject) {
		this.newObject = newObject;
	}

	@Override
	public void undo() {
		if (model instanceof Graph) {
			Graph graph = (Graph) model;

			if (newObject instanceof Vertex) {
				Vertex vertex = (Vertex) newObject;
				graph.removeVertex(vertex);
			}
		}
	}
}
