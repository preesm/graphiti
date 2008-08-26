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

import net.sf.graphiti.model.Vertex;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.NewWizardAction;
import org.eclipse.ui.dialogs.ElementTreeSelectionDialog;
import org.eclipse.ui.model.WorkbenchContentProvider;
import org.eclipse.ui.model.WorkbenchLabelProvider;

/**
 * This class provides a way to create a vertex refinement.
 * 
 * @author Matthieu Wipliez
 * 
 */
public class SetRefinementAction extends AbstractRefinementAction {

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

	private static final String ID = "net.sf.graphiti.ui.actions.SetRefinementAction";

	private IFile editedFile;

	/**
	 * Returns this action identifier.
	 * 
	 * @return This action identifier.
	 */
	public static String getActionId() {
		return ID;
	}

	/**
	 * Creates a {@link SetRefinementAction} action.
	 * 
	 * @param part
	 */
	public SetRefinementAction(IWorkbenchPart part) {
		super(part);
	}

	public boolean calculateEnabled() {
		return isRefinable();
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
	public String getId() {
		return ID;
	}

	@Override
	protected void init() {
		setId(getId());
		setText("Set/Update Refinement");
		setToolTipText("Set/Update Refinement");
	}

	@Override
	public void run() {
		IWorkbench workbench = PlatformUI.getWorkbench();
		IWorkbenchWindow window = workbench.getActiveWorkbenchWindow();
		IWorkbenchPage page = window.getActivePage();
		Shell shell = window.getShell();

		// saves the current editor input
		IEditorInput input = page.getActiveEditor().getEditorInput();
		if (input instanceof IFileEditorInput) {
			editedFile = ((IFileEditorInput) input).getFile();
		}

		// prompts the user for a file
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
	 * Sets the "refinement" parameter of the selected vertex to the location of
	 * file. This method automatically uses relative or absolute form depending
	 * on the location of file compared to {@link #editedFile}.
	 * 
	 * @param file
	 */
	private void setRefinement(IFile file) {
		IPath refinement = null;
		IPath editedFilePath = editedFile.getParent().getFullPath();
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
				// go up, and then down
				int max = editedFilePath.segmentCount();
				String path = "";
				for (int i = 0; i < max - n; i++) {
					path += "../";
				}
				path += createdFilePath.removeFirstSegments(n);
				refinement = new Path(path);
			}
		}

		refinement = refinement.append(file.getName());
		vertex.setValue(Vertex.PARAMETER_REFINEMENT, refinement.toString());
	}

	/**
	 * Ask the user to choose an existing file to refine the selected vertex.
	 * 
	 * @param shell
	 *            The active window's {@link Shell}.
	 */
	private void useExistingFile(Shell shell) {
		//TODO: implement a IFile validator
		ElementTreeSelectionDialog tree = new ElementTreeSelectionDialog(shell,
				WorkbenchLabelProvider.getDecoratingWorkbenchLabelProvider(),
				new WorkbenchContentProvider());
		tree.setAllowMultiple(false);

		IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
		tree.setInput(root);
		tree.setTitle("Choose existing file");
		if (tree.open() == Window.OK) {
			Object result = tree.getFirstResult();
			if (result instanceof IFile) {
				setRefinement((IFile) result);
			}
		}
	}
}
