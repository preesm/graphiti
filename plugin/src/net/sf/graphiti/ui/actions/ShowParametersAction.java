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

import net.sf.graphiti.ui.commands.ShowParametersCommand;
import net.sf.graphiti.ui.commands.refinement.OpenRefinementNewTabCommand;
import net.sf.graphiti.ui.editparts.EdgeEditPart;
import net.sf.graphiti.ui.editparts.GraphEditPart;
import net.sf.graphiti.ui.editparts.VertexEditPart;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.ui.actions.SelectionAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.IWorkbenchPart;

/**
 * This class provides a way to open a vertex refinement.
 * 
 * @author Matthieu Wipliez
 * 
 */
public class ShowParametersAction extends SelectionAction {

	private static final String ID = "net.sf.graphiti.ui.actions.ShowParametersAction";

	/**
	 * Returns this action identifier.
	 * 
	 * @return This action identifier.
	 */
	public static String getActionId() {
		return ID;
	}

	/**
	 * Creates a {@link OpenRefinementNewTabCommand} action.
	 * 
	 * @param part
	 */
	public ShowParametersAction(IWorkbenchPart part) {
		super(part);
	}

	public boolean calculateEnabled() {
		// enabled when at least one object is selected
		ISelection selection = getSelection();
		if (selection instanceof IStructuredSelection) {
			IStructuredSelection ssel = (IStructuredSelection) selection;
			if (ssel.isEmpty()) {
				return false;
			} else {
				Object obj = ssel.getFirstElement();
				return (obj instanceof EdgeEditPart
						|| obj instanceof GraphEditPart || obj instanceof VertexEditPart);
			}
		} else {
			return false;
		}
	}

	@Override
	public String getId() {
		return ID;
	}

	@Override
	protected void init() {
		setId(getId());
		setText("Show parameters");
		setToolTipText("Show parameters");
	}

	@Override
	public void run() {
		IWorkbenchPart part = getWorkbenchPart();
		ShowParametersCommand command = new ShowParametersCommand(part
				.getSite().getShell());

		IStructuredSelection selection = (IStructuredSelection) getSelection();

		Object sel = selection.getFirstElement();
		selection = new StructuredSelection(((EditPart) sel).getModel());
		command.setSelection(selection);

		command.run();
		if (command.isDirty()) {
			execute(command);
		}
	}
}
