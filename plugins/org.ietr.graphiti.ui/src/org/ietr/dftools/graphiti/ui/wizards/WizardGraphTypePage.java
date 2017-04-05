/*******************************************************************************
 * Copyright or © or Copr. IETR/INSA - Rennes (2008 - 2017) :
 *
 * Antoine Morvan <antoine.morvan@insa-rennes.fr> (2017)
 * Clément Guy <clement.guy@insa-rennes.fr> (2014)
 * Matthieu Wipliez <matthieu.wipliez@insa-rennes.fr> (2008 - 2011)
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

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.IWizard;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.ietr.dftools.graphiti.GraphitiModelPlugin;
import org.ietr.dftools.graphiti.model.Configuration;
import org.ietr.dftools.graphiti.model.ObjectType;

/**
 * This class provides a page for the new graph wizard.
 * 
 * @author Matthieu Wipliez
 */
public class WizardGraphTypePage extends WizardPage {

	private Map<ObjectType, Configuration> graphTypeConfigurations;

	private Map<String, ObjectType> graphTypeNames;

	private Combo listGraphTypes;

	/**
	 * Constructor for SampleNewWizardPage.
	 * 
	 * @param selection
	 */
	public WizardGraphTypePage(IStructuredSelection selection) {
		super("graphType");

		setTitle("Choose graph type");
		fillGraphTypes();
	}

	@Override
	public void createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout();
		container.setLayout(layout);

		layout.numColumns = 2;
		layout.verticalSpacing = 9;

		createGraphTypes(container);

		setControl(container);
		setPageComplete(false);
	}

	/**
	 * Creates the {@link #listGraphTypes} component from the graph types in
	 * {@link #graphTypes}.
	 * 
	 * @param parent
	 *            The parent Composite.
	 */
	private void createGraphTypes(Composite parent) {
		Label label = new Label(parent, SWT.NULL);
		label.setText("&Graph type:");

		listGraphTypes = new Combo(parent, SWT.DROP_DOWN | SWT.READ_ONLY
				| SWT.SIMPLE);
		Set<String> typeNames = new TreeSet<String>();
		typeNames.addAll(graphTypeNames.keySet());

		String[] items = typeNames.toArray(new String[] {});
		listGraphTypes.setItems(items);
		listGraphTypes.select(-1);

		listGraphTypes.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				setPageComplete(true);

				IWizard wizard = getWizard();
				IWizardPage page = wizard.getNextPage(WizardGraphTypePage.this);
				updateSelection((IGraphTypeSettable) page);
			}

		});
	}

	/**
	 * Fills the {@link #graphTypes} attribute using the default configuration.
	 */
	private void fillGraphTypes() {
		Collection<Configuration> configurations = GraphitiModelPlugin
				.getDefault().getConfigurations();
		graphTypeConfigurations = new HashMap<ObjectType, Configuration>();
		graphTypeNames = new HashMap<String, ObjectType>();

		for (Configuration configuration : configurations) {
			Set<ObjectType> graphTypes = configuration.getGraphTypes();
			for (ObjectType type : graphTypes) {
				if (!configuration.getFileFormat().getExportTransformations()
						.isEmpty()) {
					// only add graph types that can be exported

					graphTypeConfigurations.put(type, configuration);
					String fileExt = configuration.getFileFormat()
							.getFileExtension();
					graphTypeNames.put(type.getName() + " (*." + fileExt + ")",
							type);
				}
			}
		}
	}

	/**
	 * Calls {@link IGraphTypeSettable#setGraphType(Configuration, GraphType)}
	 * on the given page with the selected graph type and associated
	 * configuration.
	 * 
	 * @param page
	 *            An {@link IGraphTypeSettable} page.
	 */
	private void updateSelection(IGraphTypeSettable page) {
		int index = listGraphTypes.getSelectionIndex();
		String graphType = listGraphTypes.getItem(index);

		ObjectType type = graphTypeNames.get(graphType);
		Configuration configuration = graphTypeConfigurations.get(type);
		page.setGraphType(configuration, type);
	}

}
