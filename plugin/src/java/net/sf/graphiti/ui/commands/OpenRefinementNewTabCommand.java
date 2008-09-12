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

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IEditorDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorRegistry;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.dialogs.EditorSelectionDialog;
import org.eclipse.ui.dialogs.ElementListSelectionDialog;
import org.eclipse.ui.part.FileEditorInput;

/**
 * This class provides a way to open a vertex refinement.
 * 
 * @author Matthieu Wipliez
 * 
 */
public class OpenRefinementNewTabCommand extends AbstractRefinementCommand {

	/**
	 * Creates a {@link OpenRefinementNewTabCommand} action.
	 */
	public OpenRefinementNewTabCommand() {
	}

	@Override
	public boolean canExecute() {
		return (getRefinement() != null);
	}

	@Override
	public void execute() {
		super.execute();

		try {
			IFile file = getIFileFromSelection();
			if (file != null) {
				FileEditorInput input = new FileEditorInput(file);
				openEditor(file.toString(), input);
			}
		} catch (FileNotFoundException e) {
			MessageDialog.openError(null, "Could not open refinement",
					"File not found or invalid: " + getRefinement());
		}
	}

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

	/**
	 * Returns a refinement file name from a valid (i.e. hierarchical) vertex
	 * selection, <code>null</code> otherwise.
	 * 
	 * @return
	 * @throws FileNotFoundException
	 */
	private IFile getIFileFromSelection() throws FileNotFoundException {
		// get the path from the refinement
		IPath path = new Path(getRefinement());
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
				throw new FileNotFoundException();
			}
		}
	}

	/**
	 * Opens an editor on the given {@link IEditorInput}.
	 * 
	 * @param input
	 *            An {@link IEditorInput}.
	 */
	private void openEditor(String fileName, IEditorInput input) {
		IWorkbench workbench = PlatformUI.getWorkbench();
		IWorkbenchWindow window = workbench.getActiveWorkbenchWindow();
		IWorkbenchPage page = window.getActivePage();
		IEditorRegistry registry = workbench.getEditorRegistry();

		// matching editors
		IEditorDescriptor[] editors = registry.getEditors(fileName);
		IEditorDescriptor editor;
		if (editors.length == 0) {
			editor = registry.getDefaultEditor(fileName);

			// if no editor found, use the default text editor
			if (editor == null) {
				final String editorId = "org.eclipse.ui.DefaultTextEditor";
				editor = registry.findEditor(editorId);
			}
		} else if (editors.length == 1) {
			editor = editors[0];
		} else {
			editor = pickEditor(registry, editors, window.getShell());
			if (editor == null) {
				return;
			}
		}

		// opens the editor
		try {
			page.openEditor(input, editor.getId());
		} catch (PartInitException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Show the user an {@link EditorSelectionDialog}, where all editors minus
	 * the applicable ones are filtered.
	 * 
	 * @param registry
	 *            The {@link IEditorRegistry} of the workbench.
	 * @param editors
	 *            An array of {@link IEditorDescriptor} suitable for the
	 *            refinement file.
	 * @param shell
	 *            The active window's shell.
	 * @return The selected editor, or <code>null</code>.
	 */
	private IEditorDescriptor pickEditor(IEditorRegistry registry,
			IEditorDescriptor[] editors, Shell shell) {
		List<IEditorDescriptor> filteredEditors = new ArrayList<IEditorDescriptor>();

		// retrieve all editors in the platform
		IExtensionRegistry extReg = Platform.getExtensionRegistry();
		IConfigurationElement[] elements = extReg
				.getConfigurationElementsFor("org.eclipse.ui.editors");
		for (IConfigurationElement element : elements) {
			String editorId = element.getAttribute("id");
			IEditorDescriptor editor = registry.findEditor(editorId);
			filteredEditors.add(editor);
		}

		// removes given editors from the filter
		for (IEditorDescriptor editor : editors) {
			filteredEditors.remove(editor);
		}

		// opens the dialog
		EditorSelectionDialog dialog = new EditorSelectionDialog(shell);
		dialog.setBlockOnOpen(true);
		dialog.setEditorsToFilter(filteredEditors
				.toArray(new IEditorDescriptor[] {}));

		// if the user cancels, don't open any editor.
		if (dialog.open() == EditorSelectionDialog.CANCEL) {
			return null;
		}

		return dialog.getSelectedEditor();
	}
}
