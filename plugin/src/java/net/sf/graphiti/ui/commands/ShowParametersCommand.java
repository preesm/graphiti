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

import net.sf.graphiti.model.AbstractObject;
import net.sf.graphiti.model.Edge;
import net.sf.graphiti.model.Graph;
import net.sf.graphiti.model.Vertex;
import net.sf.graphiti.model.VertexType;

import org.eclipse.gef.commands.Command;
import org.eclipse.jface.preference.PreferenceDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.window.IShellProvider;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.dialogs.PropertyDialogAction;

/**
 * This class provides a command that adds a parameter to the currently selected
 * object(s).
 * 
 * @author Matthieu Wipliez
 * 
 */
public class ShowParametersCommand extends Command implements IShellProvider,
		ISelectionProvider {

	private ISelection selection;

	private final Shell shell;

	/**
	 * Creates a new add parameter command.
	 * 
	 * @param value
	 *            The value.
	 */
	public ShowParametersCommand(Shell shell) {
		this.shell = shell;
	}

	@Override
	public void addSelectionChangedListener(ISelectionChangedListener listener) {
	}

	@Override
	public void execute() {
		PropertyDialogAction action = new PropertyDialogAction(this, this);
		if (action.isApplicableForSelection()) {
			PreferenceDialog dialog = action.createDialog();
			if (dialog != null) {
				setTitle(dialog.getShell());
				dialog.open();
			}
		}
	}

	@Override
	public String getLabel() {
		return "Show parameters";
	}

	@Override
	public ISelection getSelection() {
		return selection;
	}

	@Override
	public Shell getShell() {
		return shell;
	}

	@Override
	public void removeSelectionChangedListener(
			ISelectionChangedListener listener) {
	}

	public void setSelection(ISelection selection) {
		this.selection = selection;
	}

	private void setTitle(Shell shell) {
		String name = null;
		IStructuredSelection sel = (IStructuredSelection) selection;
		AbstractObject model = (AbstractObject) sel.getFirstElement();
		if (model instanceof Vertex) {
			name = "\"" + model.getValue(VertexType.PARAMETER_ID) + "\"";
		} else if (model instanceof Graph) {
			name = "graph";
		} else if (model instanceof Edge) {
			name = "edge";
		}

		shell.setText("Parameters of " + name);
	}

	@Override
	public void undo() {
	}
}
