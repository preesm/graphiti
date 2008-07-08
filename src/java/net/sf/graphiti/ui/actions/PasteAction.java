package net.sf.graphiti.ui.actions;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

import net.sf.graphiti.ui.commands.PasteCommand;
import net.sf.graphiti.ui.editparts.GraphEditPart;

import org.eclipse.gef.editparts.AbstractEditPart;
import org.eclipse.gef.ui.actions.SelectionAction;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.ActionFactory;

/**
 * Action when paste tool is required
 * 
 * @author Samuel Beaussier
 * @author Nicolas Isch
 * 
 */
public class PasteAction extends SelectionAction implements
		PropertyChangeListener {

	/**
	 * Constructor for PasteAction.
	 * 
	 * @param editor
	 */
	public PasteAction(IWorkbenchPart editor) {
		super(editor);
	}

	@Override
	protected boolean calculateEnabled() {
		// Enabled if there is one selected object which is a GraphEditPart, and
		// the clipboard is not empty
		List<?> selection = getSelectedObjects();
		return (selection != null && selection.size() == 1
				&& selection.get(0) instanceof GraphEditPart
				&& getClipboardContents() != null && getClipboardContents()
				.isEmpty() == false);
	}

	@SuppressWarnings("unchecked")
	protected List<AbstractEditPart> getClipboardContents() {
		return (List<AbstractEditPart>) PreesmClipboard.getDefault()
				.getContents();
	}

	/**
	 * @see org.eclipse.gef.ui.actions.EditorPartAction#init()
	 */
	@Override
	protected void init() {
		setId(ActionFactory.PASTE.getId());
		setText("Paste");
		setToolTipText("Paste");
		ISharedImages sharedImages = PlatformUI.getWorkbench()
				.getSharedImages();
		setImageDescriptor(sharedImages
				.getImageDescriptor(ISharedImages.IMG_TOOL_PASTE));
		setDisabledImageDescriptor(sharedImages
				.getImageDescriptor(ISharedImages.IMG_TOOL_PASTE_DISABLED));
		setEnabled(false);
		PreesmClipboard._instance.addPropertyChangeListener(this);
	}

	public void propertyChange(PropertyChangeEvent evt) {
		if (evt.getPropertyName().equals(PreesmClipboard.CONTENTS_SET_EVENT))
			setEnabled(calculateEnabled());
	}

	/**
	 * Executes a new {@link PasteCommand}.
	 */
	@Override
	public void run() {
		GraphEditPart part = (GraphEditPart) getSelectedObjects().get(0);
		PasteCommand command = new PasteCommand(part);
		command.setContents(getClipboardContents());
		execute(command);
	}
}
