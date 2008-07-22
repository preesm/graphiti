/**
 * 
 */
package net.sf.graphiti.ui.editors;

import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorMatchingStrategy;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.IInPlaceEditorInput;

/**
 * @author mwipliez
 *
 */
public class GraphEditorMatchingStrategy implements IEditorMatchingStrategy {

	/* (non-Javadoc)
	 * @see org.eclipse.ui.IEditorMatchingStrategy#matches(org.eclipse.ui.IEditorReference, org.eclipse.ui.IEditorInput)
	 */
	@Override
	public boolean matches(IEditorReference editorRef, IEditorInput input) {
		if (input instanceof IInPlaceEditorInput) {
			return true;
		} else {
			return false;
		}
	}

}
