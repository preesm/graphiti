package net.sf.graphiti.ui.commands;

import net.sf.graphiti.model.Graph;
import net.sf.graphiti.model.Vertex;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.commands.Command;

/**
 * This class allows the user to add vertices and ports to another vertex.
 * 
 * @author Matthieu Wipliez
 * 
 */
public class AddCommand extends Command {

	private Object child;

	private Point moveDelta;

	private Graph oldParent;

	private Object parent;

	public AddCommand() {

	}

	@Override
	public boolean canExecute() {
		return (parent instanceof Graph);
	}

	@Override
	public void execute() {
		Graph parentGraph = (Graph) parent;
		if (child instanceof Vertex) {
			Vertex vertex = (Vertex) child;
			oldParent = vertex.getParent();
			oldParent.removeVertex(vertex);
			parentGraph.addVertex(vertex);

			Rectangle bounds = (Rectangle) vertex
					.getValue(Vertex.PARAMETER_SIZE);
			bounds = new Rectangle(bounds);
			bounds.x += moveDelta.x;
			bounds.y += moveDelta.y;
			vertex.setValue(Vertex.PARAMETER_SIZE, bounds);
		}
	}

	/**
	 * Sets the child that should be added to the parent.
	 * 
	 * @param child
	 *            the child.
	 */
	public void setChild(Object child) {
		this.child = child;
	}

	/**
	 * Sets the amount of pixels the object has moved.
	 * 
	 * @param moveDelta
	 */
	public void setMoveDelta(Point moveDelta) {
		this.moveDelta = moveDelta;
	}

	/**
	 * Sets the parent.
	 * 
	 * @param parent
	 *            The parent to use.
	 */
	public void setParent(Object parent) {
		this.parent = parent;
	}

	@Override
	public void undo() {
		Graph parentGraph = (Graph) parent;
		if (child instanceof Vertex) {
			Vertex vertex = (Vertex) child;
			parentGraph.removeVertex(vertex);
			oldParent.addVertex(vertex);
		}
	}
}
