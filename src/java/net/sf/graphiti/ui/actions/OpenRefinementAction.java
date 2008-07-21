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
package net.sf.graphiti.ui.actions;

import java.util.List;

import net.sf.graphiti.model.Vertex;
import net.sf.graphiti.ui.editors.GraphEditor;
import net.sf.graphiti.ui.editparts.VertexEditPart;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.gef.ui.actions.SelectionAction;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.FileEditorInput;

/**
 * This class provides a way to open a vertex refinement.
 * 
 * @author Matthieu Wipliez
 * 
 */
public class OpenRefinementAction extends SelectionAction {

	private static final String ID = "net.sf.graphiti.ui.actions.OpenRefinementAction";

	public static Object getActionId() {
		return ID;
	}

	/**
	 * Creates a OpenRefinementAction action.
	 * 
	 * @param part
	 */
	public OpenRefinementAction(IWorkbenchPart part) {
		super(part);
	}

	@Override
	protected boolean calculateEnabled() {
		IFile file = getIFileFromSelection();
		if (file == null) {
			return false;
		} else {
			return file.exists();
		}
	}

	/**
	 * Returns a refinement file name from a valid (i.e. hierarchical) vertex
	 * selection, <code>null</code> otherwise.
	 * 
	 * @return
	 */
	private IFile getIFileFromSelection() {
		List<?> objs = getSelectedObjects();
		if (objs.size() == 1) {
			Object obj = objs.get(0);
			if (obj instanceof VertexEditPart) {
				// we are dealing with a vertex edit part
				Vertex vertex = (Vertex) ((VertexEditPart) obj).getModel();
				Object refinement = vertex
						.getValue(Vertex.PARAMETER_REFINEMENT);
				if (refinement instanceof String) {
					// the vertex has a refinement
					String fileName = (String) refinement;

					// we retrieve editor input
					IWorkbenchPage page = PlatformUI.getWorkbench()
							.getActiveWorkbenchWindow().getActivePage();
					IEditorPart editorPart = page.getActiveEditor();
					IEditorInput input = editorPart.getEditorInput();
					
					if (input instanceof IFileEditorInput) {
						// and get its file name
						IFileEditorInput fileInput = (IFileEditorInput) input;
						IFile file = fileInput.getFile();

						// when file extension is different
						String fileExt = file.getFileExtension();
						if (fileExt != null) {
							if (fileName.endsWith("." + fileExt) == false) {
								fileName = fileName + "." + fileExt;
							}
						}

						// assuming same folder as parent
						IPath parentPath = file.getParent().getLocation();
						IPath path = parentPath.append(fileName);

						// get an IFile from path.
						IFile refinementFile = ResourcesPlugin.getWorkspace()
								.getRoot().getFileForLocation(path);
						
						return refinementFile;
					}
				}
			}
		}

		return null;
	}

	public String getId() {
		return ID;
	}

	@Override
	protected void init() {
		setId(getId());
		setText("Open refinement");
		setToolTipText("Open refinement");
		ISharedImages sharedImages = PlatformUI.getWorkbench()
				.getSharedImages();
		setImageDescriptor(sharedImages
				.getImageDescriptor(ISharedImages.IMG_DEF_VIEW));
		// setDisabledImageDescriptor(sharedImages
		// .getImageDescriptor(ISharedImages.IMG_));
		// setEnabled(false);
	}

	@Override
	public void run() {
		IFile file = getIFileFromSelection();

		FileEditorInput input = new FileEditorInput(file);
		try {
			IWorkbenchPage page = PlatformUI.getWorkbench()
					.getActiveWorkbenchWindow().getActivePage();
			page.openEditor(input, GraphEditor.ID);
		} catch (PartInitException e) {
			e.printStackTrace();
		}
	}
}
