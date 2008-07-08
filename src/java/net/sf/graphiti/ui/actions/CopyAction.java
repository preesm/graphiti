package net.sf.graphiti.ui.actions;

import net.sf.graphiti.ui.editparts.GraphitiDocumentEditPart;

import org.eclipse.gef.ui.actions.SelectionAction;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.ActionFactory;

/**
 * Action called when copy tool is required
 * 
 * @author Samuel Beaussier
 * @author Nicolas Isch
 * 
 */
public class CopyAction extends SelectionAction {

	/**
	 * Constructs a CopyAction.
	 * 
	 * @param part
	 */
	public CopyAction(IWorkbenchPart part) {
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
		setId(ActionFactory.COPY.getId());
		setText("Copy");
		setToolTipText("Copy");
		ISharedImages sharedImages = PlatformUI.getWorkbench()
				.getSharedImages();
		setImageDescriptor(sharedImages
				.getImageDescriptor(ISharedImages.IMG_TOOL_COPY));
		setDisabledImageDescriptor(sharedImages
				.getImageDescriptor(ISharedImages.IMG_TOOL_COPY_DISABLED));
		setEnabled(false);
	}

	@Override
	public void run() {
		PreesmClipboard.getDefault().setContents(getSelectedObjects());
	}

}
