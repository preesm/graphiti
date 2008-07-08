package net.sf.graphiti.ui.commands;

import net.sf.graphiti.model.Edge;
import net.sf.graphiti.model.Graph;
import net.sf.graphiti.model.Vertex;

import org.eclipse.gef.commands.Command;

/**
 * Gives methods to delete graphs, ports and dependencies
 * 
 * @author Samuel Beaussier & Nicolas Isch
 * 
 */
public class DeleteCommand extends Command {

	private Object model;

	private Graph parent;

	public boolean canExecute() {
		return (model instanceof Edge || model instanceof Vertex);
	}

	@Override
	public void execute() {
		if (model instanceof Vertex) {
			Vertex vertex = (Vertex) model;
			parent = vertex.getParent();
			parent.removeVertex(vertex);
		} else if (model instanceof Edge) {
			Edge edge = (Edge) model;
			parent = edge.getSource().getParent();
			parent.removeEdge(edge);
		}
	}

	/**
	 * 
	 * @param model
	 *            the model to set
	 */
	public void setModel(Object model) {
		this.model = model;
	}

	@Override
	public void undo() {
		if (model instanceof Vertex) {
			Vertex vertex = (Vertex) model;
			parent.addVertex(vertex);
		} else if (model instanceof Edge) {
			Edge edge = (Edge) model;
			parent.addEdge(edge);
		}
	}
}