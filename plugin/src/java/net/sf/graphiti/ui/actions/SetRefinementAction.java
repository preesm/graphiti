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
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
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

	private static final String ID = "net.sf.graphiti.ui.actions.SetRefinementAction";

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
	 * Use an existing file to refine the selected vertex.
	 * 
	 * @param shell
	 * @param page
	 */
	private void chooseFile(Shell shell, IWorkbenchPage page) {
		ElementTreeSelectionDialog tree = new ElementTreeSelectionDialog(shell,
				WorkbenchLabelProvider.getDecoratingWorkbenchLabelProvider(),
				new WorkbenchContentProvider());
		tree.setAllowMultiple(false);

		IEditorInput input = page.getActiveEditor().getEditorInput();
		if (input instanceof IFileEditorInput) {
			tree.setInput(((IFileEditorInput) input).getFile().getProject());
			tree.setTitle("Choose existing file");
			if (tree.open() == Window.OK) {
				Object result = tree.getFirstResult();
				if (result instanceof IFile) {
					IFile file = (IFile) result;
					String name = file.getName();
					String ext = file.getFileExtension();
					if (ext != null) {
						name = name.substring(0, name.lastIndexOf(ext) - 1);
					}

					vertex.setValue(Vertex.PARAMETER_REFINEMENT, name);
				}
			}
		}
	}

	@Override
	public String getId() {
		return ID;
	}

	@Override
	protected void init() {
		setId(getId());
		setText("Set Refinement");
		setToolTipText("Set Refinement");
	}

	@Override
	public void run() {
		IWorkbench workbench = PlatformUI.getWorkbench();
		IWorkbenchWindow window = workbench.getActiveWorkbenchWindow();
		IWorkbenchPage page = window.getActivePage();
		Shell shell = window.getShell();

		final String message = "The selected vertex can be refined by an existing "
				+ "file, or by a new file you can create.";
		MessageDialog dialog = new MessageDialog(shell, "Set Refinement", null,
				message, MessageDialog.QUESTION, new String[] {
						"Create a new file", "Use an existing file" }, 0);
		int index = dialog.open();
		if (index == 0) {
			// create
			FileDialog fileDlg = new FileDialog(window.getShell(), SWT.SAVE);
			String fileName = fileDlg.open();
			MessageDialog.openInformation(null, "File name", fileName);
		} else if (index == 1) {
			// use existing
			chooseFile(shell, page);
		}
	}
}
