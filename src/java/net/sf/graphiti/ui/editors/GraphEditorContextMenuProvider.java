package net.sf.graphiti.ui.editors;

import org.eclipse.gef.ContextMenuProvider;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.ui.actions.ActionRegistry;
import org.eclipse.gef.ui.actions.GEFActionConstants;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.ui.actions.ActionFactory;

/**
 * Build the context menu which is opened when right click with mouse
 * 
 * @author Samuel Beaussier & Nicolas Isch
 */
public class GraphEditorContextMenuProvider extends ContextMenuProvider {

	private ActionRegistry actionRegistry;

	/**
	 * Create context menu associating a viewer and a register
	 * 
	 * @param viewer
	 * @param registry
	 */
	public GraphEditorContextMenuProvider(EditPartViewer viewer,
			ActionRegistry registry) {
		super(viewer);
		setActionRegistry(registry);
	}

	@Override
	public void buildContextMenu(IMenuManager menu) {
		IAction action;

		GEFActionConstants.addStandardActionGroups(menu);

		action = getActionRegistry().getAction(ActionFactory.UNDO.getId());
		menu.appendToGroup(GEFActionConstants.GROUP_UNDO, action);

		action = getActionRegistry().getAction(ActionFactory.REDO.getId());
		menu.appendToGroup(GEFActionConstants.GROUP_UNDO, action);

		action = getActionRegistry().getAction(ActionFactory.CUT.getId());
		menu.appendToGroup(GEFActionConstants.GROUP_COPY, action);

		action = getActionRegistry().getAction(ActionFactory.COPY.getId());
		menu.appendToGroup(GEFActionConstants.GROUP_COPY, action);

		action = getActionRegistry().getAction(ActionFactory.PASTE.getId());
		menu.appendToGroup(GEFActionConstants.GROUP_COPY, action);

		action = getActionRegistry().getAction(ActionFactory.DELETE.getId());
		menu.appendToGroup(GEFActionConstants.GROUP_EDIT, action);

		// action =
		// getActionRegistry().getAction(OrphanizeAction.getActionId());
		// menu.appendToGroup(GEFActionConstants.GROUP_EDIT, action);
		//
		// action = getActionRegistry().getAction(RouteAction.getActionId());
		// menu.appendToGroup(GEFActionConstants.GROUP_EDIT, action);
		//
		// action = getActionRegistry().getAction(
		// AutomaticLayoutAction.getActionId());
		// menu.appendToGroup(GEFActionConstants.GROUP_EDIT, action);
	}

	/**
	 * @return the actionRegistry
	 * @uml.property name="actionRegistry"
	 */
	private ActionRegistry getActionRegistry() {
		return actionRegistry;
	}

	/**
	 * @param actionRegistry
	 *            the actionRegistry to set
	 * @uml.property name="actionRegistry"
	 */
	private void setActionRegistry(ActionRegistry registry) {
		actionRegistry = registry;
	}

}
