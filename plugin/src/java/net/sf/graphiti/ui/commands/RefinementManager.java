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
package net.sf.graphiti.ui.commands;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import net.sf.graphiti.model.Parameter;
import net.sf.graphiti.model.Vertex;
import net.sf.graphiti.ui.editparts.VertexEditPart;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.QualifiedName;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.dialogs.ElementListSelectionDialog;

/**
 * @author Matthieu Wipliez
 * 
 */
public class RefinementManager {

	protected IFile editedFile;

	/**
	 * The vertex selected, or <code>null</code> otherwise.
	 */
	protected Vertex vertex;

	/**
	 * This method looks for file named "path.ext" where <code>ext</code> is one
	 * of the known extensions for refinement files.
	 * 
	 * @param path
	 *            The refinement path (without extension).
	 * @return The {@link IFile} found.
	 * @throws FileNotFoundException
	 */
	private IFile findFileWithoutExtension(IPath path)
			throws FileNotFoundException {
		IWorkspace workspace = ResourcesPlugin.getWorkspace();

		// get all possible candidates
		List<IFile> files = new ArrayList<IFile>();
		String[] fileExts = vertex.getConfiguration()
				.getRefinementFileExtensions();
		for (String fileExt : fileExts) {
			IResource resource = workspace.getRoot().findMember(
					path + "." + fileExt);
			if (resource instanceof IFile) {
				try {
					resource.setSessionProperty(new QualifiedName(
							"net.sf.graphiti", "format"), fileExt);
				} catch (CoreException e) {
					e.printStackTrace();
				}
				files.add((IFile) resource);
			}
		}

		// check the number of candidates
		if (files.isEmpty()) {
			throw new FileNotFoundException();
		} else if (files.size() == 1) {
			return files.get(0);
		} else {
			// ask the user to select one among n
			IWorkbench workbench = PlatformUI.getWorkbench();
			IWorkbenchWindow window = workbench.getActiveWorkbenchWindow();
			Shell shell = window.getShell();

			ElementListSelectionDialog dialog = new ElementListSelectionDialog(
					shell, new LabelProvider() {
						@Override
						public String getText(Object element) {
							if (element instanceof IFile) {
								return ((IFile) element).getName();
							} else {
								return element.toString();
							}
						}
					});
			dialog.setElements(files.toArray());
			dialog.setMultipleSelection(false);
			dialog.setMessage("Several candidates are available, "
					+ "please choose one below:");
			dialog.setTitle("Choose an existing file");
			if (dialog.open() == ElementListSelectionDialog.OK) {
				return (IFile) dialog.getFirstResult();
			} else {
				return null;
			}
		}
	}

	public IFile getEditedFile() {
		return editedFile;
	}

	/**
	 * Returns a refinement file name from a valid (i.e. hierarchical) vertex
	 * selection, <code>null</code> otherwise.
	 * 
	 * @return
	 * @throws FileNotFoundException
	 */
	protected IFile getIFileFromSelection() throws FileNotFoundException {
		// prevent null pointer exception when there are no refinement.
		String refinement = getRefinement();
		if (refinement == null) {
			return null;
		}

		// get the path from the refinement
		IPath path = new Path(refinement);
		if (path.isAbsolute() == false) {
			path = editedFile.getParent().getFullPath().append(path);
		}

		// deal with file extension
		if (path.getFileExtension() == null) {
			return findFileWithoutExtension(path);
		} else {
			IWorkspace workspace = ResourcesPlugin.getWorkspace();
			IResource resource = workspace.getRoot().findMember(path);
			if (resource instanceof IFile) {
				return (IFile) resource;
			} else {
				return null;
			}
		}
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
	public String getRefinement() {
		if (vertex != null) {
			Object refinement = vertex.getValue(Vertex.PARAMETER_REFINEMENT);
			if (refinement instanceof String) {
				return (String) refinement;
			}
		}

		return null;
	}

	public Vertex getVertex() {
		return vertex;
	}

	/**
	 * Returns true if the current selection accepts a refinement parameter. The
	 * current selection must contain exactly one {@link VertexEditPart}.
	 * 
	 * @return True if the current selection can have a refinement parameter, or
	 *         <code>false</code>.
	 */
	public boolean isRefinable() {
		if (vertex != null) {
			List<Parameter> parameters = vertex.getParameters();
			for (Parameter parameter : parameters) {
				if (parameter.getName().equals(Vertex.PARAMETER_REFINEMENT)) {
					return true;
				}
			}
		}

		return false;
	}

	/**
	 * Gets the file being currently edited and set the {@link #editedFile}
	 * field.
	 */
	public void setEditedFile() {
		// retrieve editor input
		IWorkbenchPage page = PlatformUI.getWorkbench()
				.getActiveWorkbenchWindow().getActivePage();
		IEditorPart editorPart = page.getActiveEditor();
		IEditorInput input = editorPart.getEditorInput();

		// it is expected that we have a IFileEditorInput
		if (input instanceof IFileEditorInput) {
			editedFile = ((IFileEditorInput) input).getFile();
		}
	}

	/**
	 * Sets the selection.
	 * 
	 * @param selection
	 */
	public void setSelection(ISelection selection) {
		// reset the previous selection.
		vertex = null;

		if (selection instanceof IStructuredSelection) {
			Object obj = ((IStructuredSelection) selection).getFirstElement();
			if (obj instanceof VertexEditPart) {
				// we are dealing with a vertex edit part
				vertex = (Vertex) ((VertexEditPart) obj).getModel();
			}
		}
	}

	public void setVertex(Vertex vertex) {
		this.vertex = vertex;
	}

}
