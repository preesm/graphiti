/*******************************************************************************
 * Copyright or © or Copr. IETR/INSA - Rennes (%%DATE%%) :
 *
 * %%AUTHORS%%
 *
 * This software is a computer program whose purpose is to [describe
 * functionalities and technical features of your software].
 *
 * This software is governed by the CeCILL  license under French law and
 * abiding by the rules of distribution of free software.  You can  use, 
 * modify and/ or redistribute the software under the terms of the CeCILL
 * license as circulated by CEA, CNRS and INRIA at the following URL
 * "http://www.cecill.info". 
 *
 * As a counterpart to the access to the source code and  rights to copy,
 * modify and redistribute granted by the license, users are provided only
 * with a limited warranty  and the software's author,  the holder of the
 * economic rights,  and the successive licensors  have only  limited
 * liability. 
 *
 * In this respect, the user's attention is drawn to the risks associated
 * with loading,  using,  modifying and/or developing or reproducing the
 * software by the user in light of its specific status of free software,
 * that may mean  that it is complicated to manipulate,  and  that  also
 * therefore means  that it is reserved for developers  and  experienced
 * professionals having in-depth computer knowledge. Users are therefore
 * encouraged to load and test the software's suitability as regards their
 * requirements in conditions enabling the security of their systems and/or 
 * data to be ensured and,  more generally, to use and operate it in the 
 * same conditions as regards security. 
 *
 * The fact that you are presently reading this means that you have had
 * knowledge of the CeCILL license and that you accept its terms.
 *******************************************************************************/
package org.ietr.dftools.graphiti.ui.wizards;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.dialogs.WizardNewFileCreationPage;
import org.ietr.dftools.graphiti.io.GenericGraphWriter;
import org.ietr.dftools.graphiti.model.Configuration;
import org.ietr.dftools.graphiti.model.Graph;
import org.ietr.dftools.graphiti.model.ObjectType;
import org.ietr.dftools.graphiti.ui.GraphitiUiPlugin;

/**
 * This class provides a page for the save as graph wizard.
 * 
 * @author Matthieu Wipliez
 */
public class WizardSaveGraphPage extends WizardNewFileCreationPage implements
		IGraphTypeSettable {

	private String fileName;

	private Graph graph;

	/**
	 * Constructor for {@link WizardSaveGraphPage}.
	 * 
	 * @param selection
	 *            The current resource selection.
	 */
	public WizardSaveGraphPage(IStructuredSelection selection) {
		super("saveGraph", selection);

		// if the selection is a file, gets its file name and removes its
		// extension. Otherwise, let fileName be null.
		Object obj = selection.getFirstElement();
		if (obj instanceof IFile) {
			IFile file = (IFile) obj;
			String ext = file.getFileExtension();
			fileName = file.getName();
			int idx = fileName.indexOf(ext);
			if (idx != -1) {
				fileName = fileName.substring(0, idx - 1);
			}
		}

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

		IStatus status = new Status(IStatus.ERROR, GraphitiUiPlugin.PLUGIN_ID,
				message, exception);
		ErrorDialog.openError(shell, "Save error",
				"The file could not be saved.", status, IStatus.ERROR);
	}

	@Override
	public InputStream getInitialContents() {
		// set graph name
		String fileName = getFileName();
		if (fileName != null) {
			IPath filePath = new Path(fileName).removeFileExtension();
			graph.setValue(ObjectType.PARAMETER_ID, filePath.toString());
		}

		// retrieve the IFile so we can get its location
		final IPath containerPath = getContainerFullPath();
		IPath filePath = containerPath.append(getFileName());
		IFile file = ResourcesPlugin.getWorkspace().getRoot().getFile(filePath);
		graph.setFileName(filePath);

		// writes graph
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		GenericGraphWriter writer = new GenericGraphWriter(graph);
		try {
			writer.write(file.getLocation().toString(), out);
			return new ByteArrayInputStream(out.toByteArray());
		} catch (RuntimeException e) {
			errorMessage("Exception", e);
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
		Configuration configuration = graph.getConfiguration();
		ObjectType type = graph.getType();
		String fileExt = configuration.getFileFormat().getFileExtension();
		setFileExtension(fileExt);
		if (fileName == null) {
			setFileName("New " + type.getName() + "." + fileExt);
		} else {
			setFileName(fileName + "." + fileExt);
		}
	}

	@Override
	public void setGraphType(Configuration configuration, ObjectType type) {
		// create an empty graph, may be overridden
		graph = new Graph(configuration, type, true);

		String fileExt = configuration.getFileFormat().getFileExtension();
		if (fileName == null) {
			setFileName("New " + type.getName() + "." + fileExt);
		} else {
			setFileName(fileName + "." + fileExt);
		}
	}

}