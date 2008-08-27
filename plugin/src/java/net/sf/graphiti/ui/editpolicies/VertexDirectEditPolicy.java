/**
 * 
 */
package net.sf.graphiti.ui.editpolicies;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.DirectEditPolicy;
import org.eclipse.gef.requests.DirectEditRequest;

/**
 * @author mwipliez
 * 
 */
public class VertexDirectEditPolicy extends DirectEditPolicy {

	@Override
	protected Command getDirectEditCommand(DirectEditRequest request) {
		return null;
	}

	@Override
	protected void showCurrentEditValue(DirectEditRequest request) {
	}

}
