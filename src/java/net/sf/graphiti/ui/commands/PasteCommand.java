package net.sf.graphiti.ui.commands;

import java.util.ArrayList;
import java.util.List;

import net.sf.graphiti.model.Graph;
import net.sf.graphiti.model.Vertex;
import net.sf.graphiti.ui.editparts.GraphEditPart;
import net.sf.graphiti.ui.editparts.VertexEditPart;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editparts.AbstractEditPart;

/**
 * Modify model when Paste tool is activated
 * 
 * @author Samuel Beaussier
 * @author Nicolas Isch
 */
public class PasteCommand extends Command {

	private List<Object> added;

	private List<AbstractEditPart> contents;

	/**
	 * The target EditPart.
	 */
	private GraphEditPart part;

	public PasteCommand(GraphEditPart part) {
		this.part = part;
	}

	@Override
	public void execute() {
		added = new ArrayList<Object>();
		Graph parentGraph = (Graph) this.part.getModel();

		for (AbstractEditPart part : contents) {
			if (part instanceof VertexEditPart) {
				VertexEditPart vertexEditPart = (VertexEditPart) part;
				Vertex vertex = (Vertex) vertexEditPart.getModel();
				vertex = (Vertex) vertex.clone();

				// Adds the cloned graph to the parent graph and the added list
				added.add(vertex);
				parentGraph.addVertex(vertex);
			}
		}
	}

	/**
	 * Defines the contents of objects to paste.
	 * 
	 * @param contents
	 */
	public void setContents(List<AbstractEditPart> contents) {
		this.contents = contents;
	}

	@Override
	public void undo() {
		Graph parentGraph = (Graph) this.part.getModel();
		for (Object model : added) {
			if (model instanceof Vertex) {
				parentGraph.removeVertex((Vertex) model);
			}
		}
	}
}
