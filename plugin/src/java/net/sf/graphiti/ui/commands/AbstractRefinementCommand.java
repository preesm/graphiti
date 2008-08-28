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

import java.util.List;

import net.sf.graphiti.model.Parameter;
import net.sf.graphiti.model.Vertex;
import net.sf.graphiti.ui.editparts.VertexEditPart;

import org.eclipse.core.resources.IFile;
import org.eclipse.gef.commands.Command;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;

/**
 * This class provides a way to open a vertex refinement.
 * 
 * @author Matthieu Wipliez
 * 
 */
public abstract class AbstractRefinementCommand extends Command {

	protected IFile editedFile;

	/**
	 * The vertex selected, or <code>null</code> otherwise.
	 */
	protected Vertex vertex;

	@Override
	public void execute() {
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
	 * Returns the refinement parameter associated with the current selection.
	 * The current selection must contain exactly one {@link VertexEditPart}
	 * with a "refinement" parameter whose value is a {@link String}, otherwise
	 * <code>null</code> is returned.
	 * 
	 * @return The refinement parameter associated with the current selection,
	 *         or <code>null</code>.
	 */
	protected String getRefinement() {
		if (vertex != null) {
			Object refinement = vertex.getValue(Vertex.PARAMETER_REFINEMENT);
			if (refinement instanceof String) {
				return (String) refinement;
			}
		}

		return null;
	}

	/**
	 * Returns true if the current selection accepts a refinement parameter. The
	 * current selection must contain exactly one {@link VertexEditPart}.
	 * 
	 * @return True if the current selection can have a refinement parameter, or
	 *         <code>false</code>.
	 */
	protected boolean isRefinable() {
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
}
