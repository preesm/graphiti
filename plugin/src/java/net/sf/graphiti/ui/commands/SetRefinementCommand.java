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

import java.util.ArrayList;
import java.util.List;

import net.sf.graphiti.model.Vertex;
import net.sf.graphiti.ontology.FileFormat;
import net.sf.graphiti.ui.GraphitiPlugin;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.gef.commands.Command;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.NewWizardAction;
import org.eclipse.ui.dialogs.ElementTreeSelectionDialog;
import org.eclipse.ui.dialogs.ISelectionStatusValidator;
import org.eclipse.ui.model.WorkbenchContentProvider;
import org.eclipse.ui.model.WorkbenchLabelProvider;

/**
 * This class provides a way to create a vertex refinement.
 * 
 * @author Matthieu Wipliez
 * 
 */
public class SetRefinementCommand extends Command {

	/**
	 * This class provides a listener for the
	 * {@link IResourceChangeEvent#POST_BUILD} event.
	 * 
	 * @author Matthieu Wipliez
	 * 
	 */
	private final class NewFileListener implements IResourceChangeListener {

		/**
		 * Returns the first {@link IResource} added to the workspace.
		 * 
		 * @param delta
		 *            The {@link IResourceDelta} obtained from an
		 *            {@link IResourceChangeEvent}.
		 * @return The first {@link IResource} added to the workspace.
		 */
		private IResource findAddedFile(IResourceDelta delta) {
			IResourceDelta[] deltas = delta
					.getAffectedChildren(IResourceDelta.CHANGED);
			if (deltas.length == 0) {
				deltas = delta.getAffectedChildren(IResourceDelta.ADDED);
				return deltas[0].getResource();
			} else {
				return findAddedFile(deltas[0]);
			}
		}

		@Override
		public void resourceChanged(IResourceChangeEvent event) {
			IResource resource = findAddedFile(event.getDelta());
			if (resource instanceof IFile) {
				setRefinement((IFile) resource);
			}
		}
	}

	private RefinementManager manager;

	/**
	 * Creates a {@link SetRefinementCommand} action.
	 */
	public SetRefinementCommand() {
		manager = new RefinementManager();
	}

	@Override
	public boolean canExecute() {
		return manager.isRefinable();
	}

	/**
	 * Execute the {@link NewWizardAction}, and listens for resource change in
	 * the workspace to find out the file added before calling
	 * {@link #setRefinement(IWorkbenchPage, IFile)} on it.
	 * 
	 * @param shell
	 *            The active window's {@link Shell}.
	 * @param page
	 *            The current {@link IWorkbenchPage}.
	 */
	private void createNewFile(Shell shell) {
		IWorkbench workbench = PlatformUI.getWorkbench();
		NewWizardAction action = new NewWizardAction(workbench
				.getActiveWorkbenchWindow());

		IWorkspace workspace = ResourcesPlugin.getWorkspace();
		IResourceChangeListener listener = new NewFileListener();
		workspace.addResourceChangeListener(listener,
				IResourceChangeEvent.POST_BUILD);
		action.run();
		workspace.removeResourceChangeListener(listener);
	}

	@Override
	public void execute() {
		manager.setEditedFile();

		IWorkbench workbench = PlatformUI.getWorkbench();
		IWorkbenchWindow window = workbench.getActiveWorkbenchWindow();
		Shell shell = window.getShell();

		// prompts the user to choose a file
		final String message = "The selected vertex can be refined by an existing "
				+ "file, or by a new file you can create.";
		MessageDialog dialog = new MessageDialog(shell,
				"Set/Update Refinement", null, message, MessageDialog.QUESTION,
				new String[] { "Use an existing file", "Create a new file" }, 0);
		int index = dialog.open();
		if (index == 0) {
			useExistingFile(shell);
		} else if (index == 1) {
			createNewFile(shell);
		}
	}

	/**
	 * Checks that the given file name is unique with respect to the known file
	 * extensions. If it is the case, it is returned with no extension.
	 * Otherwise, it is returned "as is".
	 * 
	 * @param path
	 *            The full path to the given file.
	 * @param name
	 *            The original file name, with an extension.
	 * @return Either the unmodified file name, or the file name with no
	 *         extension.
	 */
	private String getFileNameWithoutExtension(IPath path, String name) {
		IPath fileNoExt = new Path(name).removeFileExtension();
		IPath absolutePath = path.append(fileNoExt);
		IWorkspace workspace = ResourcesPlugin.getWorkspace();

		// get all possible candidates
		List<IFile> files = new ArrayList<IFile>();
		FileFormat[] fileExts = manager.getVertex().getConfiguration()
				.getRefinementFileFormats();
		for (FileFormat fileExt : fileExts) {
			IResource resource = workspace.getRoot().findMember(
					absolutePath + "." + fileExt.hasFileExtension());
			if (resource instanceof IFile) {
				files.add((IFile) resource);
			}
		}

		if (files.size() == 1) {
			return fileNoExt.toString();
		} else {
			// use the original name
			return name;
		}
	}

	/**
	 * Returns the refinement value corresponding to the given file. This method
	 * automatically uses relative or absolute form depending on the location of
	 * file compared to {@link #editedFile}.
	 * 
	 * @param file
	 *            The file refinining the selected vertex.
	 * @return A {@link String} with the refinement value.
	 */
	private String getRefinementValue(IFile file) {
		IPath refinement = null;
		IPath editedFilePath = manager.getEditedFile().getParent()
				.getFullPath();
		IPath createdFilePath = file.getParent().getFullPath();

		int n = editedFilePath.matchingFirstSegments(createdFilePath);
		if (n == 0) {
			// no common path segments: absolute path
			refinement = createdFilePath;
		} else {
			// common path segments: using a relative path
			if (editedFilePath.isPrefixOf(createdFilePath)) {
				// just remove the common segments
				refinement = createdFilePath.removeFirstSegments(n);
			} else {
				// go up
				int max = editedFilePath.segmentCount();
				String path = "";
				for (int i = 0; i < max - n; i++) {
					path += "../";
				}
				// and then down
				path += createdFilePath.removeFirstSegments(n);
				refinement = new Path(path);
			}
		}

		String fileName = getFileNameWithoutExtension(createdFilePath, file
				.getName());
		refinement = refinement.append(fileName);
		return refinement.toString();
	}

	/**
	 * Sets the "refinement" parameter of the selected vertex to the location of
	 * file.
	 * 
	 * @param file
	 */
	private void setRefinement(IFile file) {
		String refinement = getRefinementValue(file);
		manager.getVertex().setValue(Vertex.PARAMETER_REFINEMENT, refinement);
	}

	/**
	 * @see RefinementManager#setSelection(ISelection)
	 */
	public void setSelection(ISelection selection) {
		manager.setSelection(selection);
	}

	/**
	 * Ask the user to choose an existing file to refine the selected vertex.
	 * 
	 * @param shell
	 *            The active window's {@link Shell}.
	 */
	private void useExistingFile(Shell shell) {
		ElementTreeSelectionDialog tree = new ElementTreeSelectionDialog(shell,
				WorkbenchLabelProvider.getDecoratingWorkbenchLabelProvider(),
				new WorkbenchContentProvider());
		tree.setAllowMultiple(false);
		tree.setInput(ResourcesPlugin.getWorkspace().getRoot());
		tree.setMessage("Please select an existing file:");
		tree.setTitle("Choose an existing file");
		tree.setValidator(new ISelectionStatusValidator() {

			@Override
			public IStatus validate(Object[] selection) {
				if (selection.length == 1) {
					if (selection[0] instanceof IFile) {
						IFile file = (IFile) selection[0];
						String message = "Vertex refinement: "
								+ getRefinementValue(file);
						return new Status(Status.OK, GraphitiPlugin.PLUGIN_ID,
								message);
					}
				}

				return new Status(Status.ERROR, GraphitiPlugin.PLUGIN_ID,
						"Only files can be selected, not folders nor projects");
			}

		});

		// opens the dialog
		if (tree.open() == Window.OK) {
			setRefinement((IFile) tree.getFirstResult());
		}
	}
}
