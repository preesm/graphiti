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

import net.sf.graphiti.model.Graph;
import net.sf.graphiti.ui.editors.GraphEditor;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

/**
 * This class provides a page for the "Export to GraphML" wizard.
 * 
 * @author Matthieu Wipliez
 */
public class GraphMLExportWizardPage extends WizardPage {

	private Graph graph;

	private ISelection selection;

	private Text textDestinationFile;

	/**
	 * Constructor for SampleNewWizardPage.
	 * 
	 * @param selection
	 */
	public GraphMLExportWizardPage(ISelection selection) {
		super("wizardPage");
		setTitle("GraphML");
		setDescription("Export the active Graphiti graph to a GraphML file "
				+ "on the local file system.");

		this.selection = selection;
	}

	private void createBrowse(Composite parent) {
		Button button = new Button(parent, SWT.PUSH);
		button.setText("Browse...");
		button.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				handleBrowse();
			}
		});
	}

	@Override
	public void createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NULL);
		GridLayout layout = new GridLayout();
		container.setLayout(layout);

		layout.numColumns = 3;
		layout.verticalSpacing = 9;

		createDestinationFileName(container);
		createBrowse(container);
		initialize();

		dialogChanged();
		setControl(container);
	}

	private void createDestinationFileName(Composite parent) {
		Label label = new Label(parent, SWT.NULL);
		label.setText("&Destination file name:");

		textDestinationFile = new Text(parent, SWT.BORDER | SWT.SINGLE);
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		textDestinationFile.setLayoutData(gd);
		textDestinationFile.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				dialogChanged();
			}
		});
	}

	/**
	 * Ensures that both text fields are set.
	 */
	private void dialogChanged() {
		String fileName = getDestinationFileName();
		if (fileName.length() == 0) {
			updateStatus("Destination file name must be specified");
			return;
		}

		updateStatus(null);
	}

	/**
	 * Returns the destination file name.
	 * 
	 * @return The destination file name.
	 */
	public String getDestinationFileName() {
		return textDestinationFile.getText();
	}

	/**
	 * Returns the source graph opened in the editor.
	 * 
	 * @return The source graph opened in the editor.
	 */
	public Graph getGraph() {
		return graph;
	}

	/**
	 * Uses the standard container selection dialog to choose the new value for
	 * the container field.
	 */
	private void handleBrowse() {
		FileDialog dialog = new FileDialog(getShell(), SWT.SAVE);
		dialog.setOverwrite(true);
		String fileName = dialog.open();
		if (fileName != null) {
			textDestinationFile.setText(fileName);
		}
	}

	/**
	 * Tests if the current workbench selection is a suitable container to use.
	 */
	private void initialize() {
		if (selection != null && selection.isEmpty() == false
				&& selection instanceof IStructuredSelection) {
			IStructuredSelection ssel = (IStructuredSelection) selection;
			if (ssel.size() == 1) {
				Object obj = ssel.getFirstElement();
				if (obj instanceof GraphEditor) {
					GraphEditor editor = (GraphEditor) obj;
					graph = editor.getContents();
				}
			}
		}
	}

	private void updateStatus(String message) {
		setErrorMessage(message);
		setPageComplete(message == null);
	}
}