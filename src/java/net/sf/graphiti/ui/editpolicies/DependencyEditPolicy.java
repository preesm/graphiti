package net.sf.graphiti.ui.editpolicies;

import net.sf.graphiti.ui.commands.DeleteCommand;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.ConnectionEditPolicy;
import org.eclipse.gef.requests.GroupRequest;

/**
 * This class provides the <code>getDeleteCommand</code> method to create a
 * DeleteCommand for a dependency.
 * 
 * @author Samuel Beaussier
 * @author Nicolas Isch
 */
public class DependencyEditPolicy extends ConnectionEditPolicy {

	@Override
	protected Command getDeleteCommand(GroupRequest request) {
		DeleteCommand command = new DeleteCommand();
		command.setModel(getHost().getModel());
		return command;
	}

}