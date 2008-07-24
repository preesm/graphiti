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
import net.sf.graphiti.ui.editparts.VertexEditPart;

import org.eclipse.core.filesystem.EFS;
import org.eclipse.core.filesystem.IFileInfo;
import org.eclipse.core.filesystem.IFileStore;
import org.eclipse.core.filesystem.IFileSystem;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.gef.ui.actions.SelectionAction;
import org.eclipse.ui.IEditorDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorRegistry;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

/**
 * This class provides a way to open a vertex refinement.
 * 
 * @author Matthieu Wipliez
 * 
 */
public abstract class AbstractOpenRefinementAction extends SelectionAction {

	/**
	 * The vertex selected, or <code>null</code> otherwise.
	 */
	protected Vertex vertex;

	/**
	 * Creates a AbstractOpenRefinementAction action.
	 * 
	 * @param part
	 */
	protected AbstractOpenRefinementAction(IWorkbenchPart part) {
		super(part);
	}

	@Override
	protected boolean calculateEnabled() {
		return (getRefinement() != null);
	}

	/**
	 * Recursively looks down <code>store</code> for a file called
	 * <code>fileName</code> with any extension present in <code>fileExts</code>
	 * .
	 * 
	 * @param store
	 *            An {@link IFileStore} object pointing to a folder or a file.
	 * @param fileName
	 *            The filename we're looking for.
	 * @param fileExts
	 *            Available file extensions.
	 * @return An {@link IFileStore} corresponding to the file we searched, or
	 *         <code>null</code> otherwise.
	 */
	private IFileStore findMatchingFile(IFileStore store, String fileName,
			String[] fileExts) {
		try {
			IFileStore[] files = store.childStores(EFS.NONE,
					new NullProgressMonitor());
			if (files.length == 0) {
				// A leaf => a file
				for (String fileExt : fileExts) {
					String extFileName = fileName + fileExt;
					IFileInfo info = store.fetchInfo();
					if (info.getName().equals(extFileName)) {
						return store;
					}
				}
			} else {
				// Look for children
				for (IFileStore file : files) {
					store = findMatchingFile(file, fileName, fileExts);
					if (store != null) {
						return store;
					}
				}
			}
		} catch (CoreException e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * Gets an {@link IFileStore} from
	 * {@link #findMatchingFile(IFileStore, String, String[])}, and returns the
	 * associated {@link IFile}.
	 * 
	 * @param parentPath
	 *            The root path.
	 * @param fileName
	 *            The filename to look for.
	 * @param fileExts
	 *            Available file extensions.
	 * @return An {@link IFile} if found, <code>null</code> otherwise.
	 */
	private IFile findMatchingFile(IPath parentPath, String fileName,
			String[] fileExts) {
		IFileSystem system = EFS.getLocalFileSystem();
		IFileStore store = system.getStore(parentPath);
		store = findMatchingFile(store, fileName, fileExts);

		// get an IFile from the store URI.
		IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
		IFile[] files = root.findFilesForLocationURI(store.toURI());
		if (files.length >= 1) {
			return files[0];
		} else {
			return null;
		}
	}

	/**
	 * Returns a refinement file name from a valid (i.e. hierarchical) vertex
	 * selection, <code>null</code> otherwise.
	 * 
	 * @return
	 */
	protected IFile getIFileFromSelection() {
		String fileName = getRefinement();
		String[] fileExts = vertex.getDocumentConfiguration()
				.getRefinementFileExtensions();

		// retrieve editor input
		IWorkbenchPage page = PlatformUI.getWorkbench()
				.getActiveWorkbenchWindow().getActivePage();
		IEditorPart editorPart = page.getActiveEditor();
		IEditorInput input = editorPart.getEditorInput();

		// it is expected that we have a IFileEditorInput
		if (input instanceof IFileEditorInput) {
			IFileEditorInput fileInput = (IFileEditorInput) input;
			IFile file = fileInput.getFile();
			IPath parentPath = file.getParent().getLocation();

			return findMatchingFile(parentPath, fileName, fileExts);
		}

		return null;
	}

	/**
	 * Returns the refinement parameter associated with the current selection.
	 * The current selection must contain exactly one {@link VertexEditPart}
	 * with a "refinement" parameter whose value is a {@link String}, otherwise
	 * <code>null</code> is returned.
	 * 
	 * @return The refinement parameter associated with the current selection,
	 *         or <code>null</code>.
	 */
	private String getRefinement() {
		List<?> objs = getSelectedObjects();
		if (objs.size() == 1) {
			Object obj = objs.get(0);
			if (obj instanceof VertexEditPart) {
				// we are dealing with a vertex edit part
				vertex = (Vertex) ((VertexEditPart) obj).getModel();
				Object refinement = vertex
						.getValue(Vertex.PARAMETER_REFINEMENT);
				if (refinement instanceof String) {
					return (String) refinement;
				}
			}
		}

		return null;
	}

	/**
	 * Opens an editor on the given {@link IEditorInput}.
	 * 
	 * @param input
	 *            An {@link IEditorInput}.
	 */
	protected void openEditor(String fileName, IEditorInput input) {
		IWorkbench workbench = PlatformUI.getWorkbench();
		IEditorRegistry registry = workbench.getEditorRegistry();

		IEditorDescriptor[] editors = registry.getEditors(fileName);
		IEditorDescriptor editor;
		if (editors.length == 0) {
			editor = registry.getDefaultEditor(fileName);
		} else if (editors.length == 1) {
			editor = editors[0];
		} else {
			editor = editors[0];
			for (IEditorDescriptor editorN : editors) {
				System.out.println(editorN.getId());
			}
		}

		IWorkbenchPage page = PlatformUI.getWorkbench()
				.getActiveWorkbenchWindow().getActivePage();
		try {
			page.openEditor(input, editor.getId());
		} catch (PartInitException e) {
			e.printStackTrace();
		}
	}

	@Override
	public abstract void run();
}
