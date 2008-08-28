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

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import net.sf.graphiti.model.Configuration;
import net.sf.graphiti.model.Graph;
import net.sf.graphiti.ontology.types.GraphType;
import net.sf.graphiti.ui.GraphitiPlugin;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Path;
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
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.dialogs.ContainerSelectionDialog;

/**
 * This class provides a page for the new graph wizard.
 * 
 * @author Matthieu Wipliez
 */
public class NewGraphWizardPage extends WizardPage {

	private Map<String, Configuration> graphTypes;

	private Combo listGraphTypes;

	private ISelection selection;

	private Text textContainer;

	private Text textFile;

	/**
	 * Constructor for SampleNewWizardPage.
	 * 
	 * @param selection
	 */
	public NewGraphWizardPage(ISelection selection) {
		super("wizardPage");
		setTitle("Choose graph type");
		setDescription("Create a new graph.");
		this.selection = selection;

		fillGraphTypes();
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

	private void createContainer(Composite parent) {
		Label label = new Label(parent, SWT.NULL);
		label.setText("&Container:");

		textContainer = new Text(parent, SWT.BORDER | SWT.SINGLE);
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		textContainer.setLayoutData(gd);
		textContainer.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				dialogChanged();
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

		createContainer(container);
		createBrowse(container);
		createFilename(container);
		createGraphTypes(container);

		initialize();
		dialogChanged();
		setControl(container);
	}

	private void createFilename(Composite parent) {
		Label label = new Label(parent, SWT.NULL);
		label.setText("&File name:");

		textFile = new Text(parent, SWT.BORDER | SWT.SINGLE);
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		textFile.setLayoutData(gd);
		textFile.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				dialogChanged();
			}
		});

		new Label(parent, SWT.NULL);
	}

	private void createGraphTypes(Composite parent) {
		Label label = new Label(parent, SWT.NULL);
		label.setText("&Graph type:");

		listGraphTypes = new Combo(parent, SWT.DROP_DOWN | SWT.READ_ONLY
				| SWT.SIMPLE);
		Set<String> typeNames = new TreeSet<String>(this.graphTypes.keySet());
		String[] items = typeNames.toArray(new String[] {});
		listGraphTypes.setItems(items);
		listGraphTypes.select(0);
	}

	/**
	 * Ensures that both text fields are set.
	 */

	private void dialogChanged() {
		IResource container = ResourcesPlugin.getWorkspace().getRoot()
				.findMember(new Path(getContainerName()));
		String fileName = getFileName();

		if (getContainerName().length() == 0) {
			updateStatus("File container must be specified");
			return;
		}

		if (container == null
				|| (container.getType() & (IResource.PROJECT | IResource.FOLDER)) == 0) {
			updateStatus("File container must exist");
			return;
		}

		if (!container.isAccessible()) {
			updateStatus("Project must be writable");
			return;
		}

		if (fileName.length() == 0) {
			updateStatus("File name must be specified");
			return;
		}

		if (fileName.replace('\\', '/').indexOf('/', 1) > 0) {
			updateStatus("File name must be valid");
			return;
		}

		updateStatus(null);
	}

	private void fillGraphTypes() {
		Configuration rootConfig = GraphitiPlugin.getDefault()
				.getConfiguration();
		graphTypes = new HashMap<String, Configuration>();
		for (Configuration config : rootConfig.getConfigurationList(true)) {
			Set<GraphType> graphTypes = config.getOntologyFactory()
					.getGraphTypes();
			for (GraphType type : graphTypes) {
				this.graphTypes.put(type.hasName(), config);
			}
		}
	}

	public String getContainerName() {
		return textContainer.getText();
	}

	public String getFileName() {
		return textFile.getText();
	}

	/**
	 * Creates a new {@link Graph} from the given graph type and configuration.
	 * 
	 * @return A new {@link Graph}.
	 */
	public Graph getNewGraph() {
		int index = listGraphTypes.getSelectionIndex();
		String graphType = listGraphTypes.getItem(index);
		Configuration config = graphTypes.get(graphType);
		Graph graph = new Graph(config);
		return graph;
	}

	/**
	 * Uses the standard container selection dialog to choose the new value for
	 * the container field.
	 */

	private void handleBrowse() {
		ContainerSelectionDialog dialog = new ContainerSelectionDialog(
				getShell(), ResourcesPlugin.getWorkspace().getRoot(), false,
				"Select new file container");
		if (dialog.open() == ContainerSelectionDialog.OK) {
			Object[] result = dialog.getResult();
			if (result.length == 1) {
				textContainer.setText(((Path) result[0]).toString());
			}
		}
	}

	/**
	 * Tests if the current workbench selection is a suitable container to use.
	 */
	private void initialize() {
		if (selection != null && selection.isEmpty() == false
				&& selection instanceof IStructuredSelection) {
			IStructuredSelection ssel = (IStructuredSelection) selection;
			if (ssel.size() > 1)
				return;
			Object obj = ssel.getFirstElement();
			if (obj instanceof IResource) {
				IContainer container;
				if (obj instanceof IContainer)
					container = (IContainer) obj;
				else
					container = ((IResource) obj).getParent();
				textContainer.setText(container.getFullPath().toString());
			}
		}
	}

	private void updateStatus(String message) {
		setErrorMessage(message);
		setPageComplete(message == null);
	}
}