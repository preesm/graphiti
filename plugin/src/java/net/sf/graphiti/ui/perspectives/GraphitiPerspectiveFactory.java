/*
 * Copyright (c) 2008, IETR/INSA of Rennes
 * All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 * 
 *   * Redistributions of source code must retain the above copyright notice,
 *     this list of conditions and the following disclaimer.
 *   * Redistributions in binary form must reproduce the above copyright notice,
 *     this list of conditions and the following disclaimer in the documentation
 *     and/or other materials provided with the distribution.
 *   * Neither the name of the IETR/INSA of Rennes nor the names of its
 *     contributors may be used to endorse or promote products derived from this
 *     software without specific prior written permission.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT,
 * STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY
 * WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF
 * SUCH DAMAGE.
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
				0.2f, editorArea);
		left.addView(IPageLayout.ID_RES_NAV);

		IFolderLayout bottom = layout.createFolder("bottom",
				IPageLayout.BOTTOM, 0.80f, editorArea);
		bottom.addView(IPageLayout.ID_PROBLEM_VIEW);
		bottom.addView(VertexParametersView.ID_VERTEX_PARAMETERS);

		// put the outline on the right
		IFolderLayout right = layout.createFolder("right", IPageLayout.RIGHT,
				0.8f, editorArea);
		right.addView(IPageLayout.ID_OUTLINE);
	}

}
