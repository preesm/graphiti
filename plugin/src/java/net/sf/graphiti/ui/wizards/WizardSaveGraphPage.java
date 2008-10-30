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
package net.sf.graphiti.ui.wizards;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import javax.xml.transform.TransformerException;

import net.sf.graphiti.io.GenericGraphWriter;
import net.sf.graphiti.model.Configuration;
import net.sf.graphiti.model.Graph;
import net.sf.graphiti.model.GraphType;
import net.sf.graphiti.ui.GraphitiPlugin;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.dialogs.WizardNewFileCreationPage;

/**
 * This class provides a page for the save as graph wizard.
 * 
 * @author Matthieu Wipliez
 */
public class WizardSaveGraphPage extends WizardNewFileCreationPage implements
		IGraphTypeSettable {

	private Graph graph;

	/**
	 * Constructor for {@link WizardSaveGraphPage}.
	 * 
	 * @param selection
	 *            The current resource selection.
	 */
	public WizardSaveGraphPage(IStructuredSelection selection) {
		super("saveGraph", selection);

		setTitle("Choose file name and parent folder");
	}

	/**
	 * Displays an error message with the given exception.
	 * 
	 * @param message
	 *            A description of the error.
	 * @param exception
	 *            An exception.
	 */
	private void errorMessage(String message, Throwable exception) {
		IWorkbench workbench = PlatformUI.getWorkbench();
		IWorkbenchWindow window = workbench.getActiveWorkbenchWindow();
		Shell shell = window.getShell();

		IStatus status = new Status(IStatus.ERROR, GraphitiPlugin.PLUGIN_ID,
				message, exception);
		ErrorDialog.openError(shell, "Save error",
				"The file could not be saved.", status, IStatus.ERROR);
	}

	@Override
	public InputStream getInitialContents() {
		// retrieve the IFile so we can get its location
		final IPath containerPath = getContainerFullPath();
		IPath filePath = containerPath.append(getFileName());
		IFile file = ResourcesPlugin.getWorkspace().getRoot().getFile(filePath);

		// writes graph
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		GenericGraphWriter writer = new GenericGraphWriter(graph);
		try {
			writer.write(file.getLocation().toString(), out);
			return new ByteArrayInputStream(out.toByteArray());
		} catch (ClassCastException e) {
			errorMessage(
					"There was a problem with the creation of a DOM document.",
					e);
		} catch (ClassNotFoundException e) {
			errorMessage("A DOM class could not be found.", e);
		} catch (IllegalAccessException e) {
			errorMessage("A DOM class could not be accessed.", e);
		} catch (InstantiationException e) {
			errorMessage("A DOM class could not be instantiated.", e);
		} catch (TransformerException e) {
			errorMessage("An unrecoverable error occurred during "
					+ "the course of the transformation.", e);
		}

		return null;
	}

	/**
	 * Sets a new graph for this page.
	 * 
	 * @param graph
	 *            A {@link Graph}.
	 */
	public void setGraph(Graph graph) {
		this.graph = graph;
	}

	@Override
	public void setGraphType(Configuration configuration, GraphType type) {
		// create an empty graph, may be overridden
		graph = new Graph(configuration, type);

		String fileExt = configuration.getFileFormat().getFileExtension();
		setFileName("New " + type.getName() + "." + fileExt);
	}

}