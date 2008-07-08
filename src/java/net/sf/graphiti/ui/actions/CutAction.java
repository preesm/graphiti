package net.sf.graphiti.ui.actions;

import java.util.ArrayList;
import java.util.List;

import net.sf.graphiti.ui.commands.CutCommand;
import net.sf.graphiti.ui.editparts.GraphitiDocumentEditPart;

import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.gef.ui.actions.SelectionAction;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.ActionFactory;

/**
 * Action called when cut tool is required
 * 
 * @author Samuel Beaussier
 * @author Nicolas Isch
 * 
 */
public class CutAction extends SelectionAction {

	/**
	 * Construct a CutAction.
	 * 
	 * @param part
	 */
	public CutAction(IWorkbenchPart part) {
		super(part);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.ui.actions.WorkbenchPartAction#calculateEnabled()
	 */
	@Override
	protected boolean calculateEnabled() {
		// enabled when at least one object is selected, and it is not a
		// GraphitiDocumentEditPart
		return ((getSelectedObjects().isEmpty() == false) && (getSelectedObjects()
				.size() == 1 && (getSelectedObjects().get(0) instanceof GraphitiDocumentEditPart)) == false);
	}

	@Override
	protected void init() {
		setId(ActionFactory.CUT.getId());
		setText("Cut");
		setToolTipText("Cut");
		ISharedImages sharedImages = PlatformUI.getWorkbench()
				.getSharedImages();
		setImageDescriptor(sharedImages
				.getImageDescriptor(ISharedImages.IMG_TOOL_CUT));
		setDisabledImageDescriptor(sharedImages
				.getImageDescriptor(ISharedImages.IMG_TOOL_CUT_DISABLED));
		setEnabled(false);
	}

	@Override
	@SuppressWarnings("unchecked")
	public void run() {
		if (calculateEnabled()) {
			PreesmClipboard.getDefault().setContents(getSelectedObjects());
		}
		CutCommand command = new CutCommand();
		List<AbstractGraphicalEditPart> l = new ArrayList<AbstractGraphicalEditPart>(
				getSelectedObjects());
		command.setList(l);
		execute(command);
	}
}
