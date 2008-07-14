/**
 * 
 */
package net.sf.graphiti.ui.perspectives;

import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

/**
 * This class creates the layout associated with the Graphiti perspective.
 * 
 * @author Matthieu Wipliez
 * 
 */
public class GraphitiPerspectiveFactory implements IPerspectiveFactory {

	@Override
	public void createInitialLayout(IPageLayout layout) {
		// get the editor area (the other views are placed relative to this one)
		String editorArea = layout.getEditorArea();

		// put the navigator on the left
		IFolderLayout left = layout.createFolder("left", IPageLayout.LEFT,
				0.15f, editorArea);
		left.addView(IPageLayout.ID_RES_NAV);

		IFolderLayout bottom = layout.createFolder("bottom",
				IPageLayout.BOTTOM, 0.80f, editorArea);
		bottom.addView(IPageLayout.ID_PROBLEM_VIEW);
		bottom.addView(IPageLayout.ID_PROP_SHEET);

		// put the outline on the right
		IFolderLayout right = layout.createFolder("right", IPageLayout.RIGHT,
				0.85f, editorArea);
		right.addView(IPageLayout.ID_OUTLINE);
	}

}
