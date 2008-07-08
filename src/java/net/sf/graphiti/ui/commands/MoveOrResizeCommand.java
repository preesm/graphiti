package net.sf.graphiti.ui.commands;

import net.sf.graphiti.model.PropertyBean;
import net.sf.graphiti.model.Vertex;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.commands.Command;

/**
 * This class executes a command that moves a graph.
 * 
 * @author Samuel Beaussier
 * @author Nicolas Isch
 * @author Matthieu Wipliez
 */
public class MoveOrResizeCommand extends Command {

	private Rectangle bounds;

	private PropertyBean model;

	private Rectangle oldLayout;

	@Override
	public void execute() {
		model.setValue(Vertex.PARAMETER_SIZE, new Rectangle(bounds));
	}

	public void setConstraint(Rectangle rect) {
		this.bounds = rect;
	}

	public void setModel(Object model) {
		this.model = (PropertyBean) model;
		this.oldLayout = (Rectangle) this.model.getValue(Vertex.PARAMETER_SIZE);
	}

	@Override
	public void undo() {
		model.setValue(Vertex.PARAMETER_SIZE, oldLayout);
	}
}
