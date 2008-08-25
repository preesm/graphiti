/**
 * 
 */
package net.sf.graphiti.ui.perspectives;

import net.sf.graphiti.ui.views.VertexParametersView;

import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveDescriptor;
import org.eclipse.ui.IPerspectiveFactory;
import org.eclipse.ui.IPerspectiveRegistry;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.PlatformUI;

/**
 * This class creates the layout associated with the Graphiti perspective.
 * 
 * @author Matthieu Wipliez
 * 
 */
public class GraphitiPerspectiveFactory implements IPerspectiveFactory {

	public static final String ID = "net.sf.graphiti.ui.perspective";

	/**
	 * Returns a perspective descriptor of the perspective associated with this
	 * factory.
	 * 
	 * @return An {@link IPerspectiveDescriptor} for this perspective (whose id
	 *         is "net.sf.graphiti.ui.perspective").
	 */
	public static IPerspectiveDescriptor getPerspectiveDescriptor() {
		IWorkbench workbench = PlatformUI.getWorkbench();
		IPerspectiveRegistry registry = workbench.getPerspectiveRegistry();
		return registry.findPerspectiveWithId(ID);
	}

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
		bottom.addView(VertexParametersView.ID_VERTEX_PARAMETERS);

		// put the outline on the right
		IFolderLayout right = layout.createFolder("right", IPageLayout.RIGHT,
				0.85f, editorArea);
		right.addView(IPageLayout.ID_OUTLINE);
	}

}
