package net.sf.graphiti.ui.commands;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.sf.graphiti.model.Graph;
import net.sf.graphiti.model.Vertex;
import net.sf.graphiti.ui.editparts.VertexEditPart;

import org.eclipse.gef.commands.Command;

/**
 * This class provides a command that removes vertices or ports from their
 * parent.
 * 
 * @author Samuel Beaussier
 * @author Nicolas Isch
 * @author Matthieu Wipliez
 * 
 */
public class CutCommand extends Command {

	private List<?> list;

	/**
	 * Contains the parents of each port/graph.
	 */
	private List<Graph> parents;

	@Override
	public void execute() {
		parents = new ArrayList<Graph>();

		for (Object obj : list) {
			if (obj instanceof VertexEditPart) {
				VertexEditPart part = (VertexEditPart) obj;
				Vertex vertex = (Vertex) part.getModel();
				Graph parent = vertex.getParent();

				parent.removeVertex(vertex);
				parents.add(parent);
			}
		}
	}

	/**
	 * Defines the objects list to cut
	 * 
	 * @param list
	 *            the list to set
	 */
	public void setList(List<?> list) {
		this.list = list;
	}

	@Override
	public void undo() {
		Iterator<Graph> it = parents.iterator();
		for (Object obj : list) {
			if (obj instanceof VertexEditPart) {
				VertexEditPart part = (VertexEditPart) obj;
				Vertex vertex = (Vertex) part.getModel();
				Graph parent = it.next();
				parent.addVertex(vertex);
			}
		}

		parents = null;
	}
}
