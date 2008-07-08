package net.sf.graphiti.ui.editpolicies;

import net.sf.graphiti.ui.commands.DeleteCommand;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.ComponentEditPolicy;
import org.eclipse.gef.requests.GroupRequest;

/**
 * Provide a method which launch a command to delete a graph or a port
 * 
 * @author Samuel Beaussier & Nicolas Isch
 */

public class DeleteComponentEditPolicy extends ComponentEditPolicy {

	@Override
	protected Command createDeleteCommand(GroupRequest deleteRequest) {
		DeleteCommand command = new DeleteCommand();
		command.setModel(getHost().getModel());
		return command;
	}
}
