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
package net.sf.graphiti.ui.properties;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.Map.Entry;

import net.sf.graphiti.model.AbstractObject;
import net.sf.graphiti.model.Parameter;

import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.ui.dialogs.PropertyPage;

/**
 * This class provides a property page for parameters.
 * 
 * @author Matthieu Wipliez
 * 
 */
public class ParametersPropertyPage extends PropertyPage {

	private AbstractObject model;

	private Map<String, Object> newValues;

	/**
	 * Constructor for ParametersPropertyPage.
	 */
	public ParametersPropertyPage() {
		super();
	}

	/**
	 * @see PreferencePage#createContents(Composite)
	 */
	protected Control createContents(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout();
		composite.setLayout(layout);
		GridData data = new GridData(GridData.FILL);
		data.grabExcessHorizontalSpace = true;
		composite.setLayoutData(data);

		model = (AbstractObject) getElement().getAdapter(AbstractObject.class);
		setTitle("Parameters");
		createParameterTables(composite);

		return composite;
	}

	private void createListTable(Composite parent, Object value) {
		Table table = createTable(parent);
		TableViewer tableViewer = new TableViewer(table);
		tableViewer.setContentProvider(new ListContentProvider());

		// 1st column
		TableColumn column = new TableColumn(table, SWT.CENTER, 0);
		column.setText("Value");
		column.setWidth(100);

		// first column
		TableViewerColumn tvc = new TableViewerColumn(tableViewer, column);
		tvc.setLabelProvider(new ListCellLabelProvider());

		// set input
		tableViewer.setInput(value);

		// editing support
		// must be done after an input is set for the table viewer
		EditingSupport editing = new ListEditingSupport(tvc.getViewer(), table);
		tvc.setEditingSupport(editing);
	}

	private void createMapTable(Composite parent, Object value) {
		Table table = createTable(parent);
		TableViewer tableViewer = new TableViewer(table);
		tableViewer.setContentProvider(new MapContentProvider());

		MapCellLabelProvider labelProvider = new MapCellLabelProvider();

		// 1st column
		TableColumn column = new TableColumn(table, SWT.CENTER, 0);
		column.setText("Name");
		column.setWidth(100);

		TableViewerColumn tvc1 = new TableViewerColumn(tableViewer, column);
		tvc1.setLabelProvider(labelProvider);

		// 2nd column
		column = new TableColumn(table, SWT.LEFT, 1);
		column.setText("Value");
		column.setWidth(300);

		TableViewerColumn tvc2 = new TableViewerColumn(tableViewer, column);
		tvc2.setLabelProvider(labelProvider);

		// set input, must be done after label providers are set
		tableViewer.setInput(value);

		// editing support for first and second column
		// must be done after an input is set for the table viewer
		tvc1.setEditingSupport(new MapNameEditingSupport(tvc1.getViewer(),
				table));
		tvc2.setEditingSupport(new MapValueEditingSupport(tvc2.getViewer(),
				table));
	}

	@SuppressWarnings("unchecked")
	private void createNewValues() {
		newValues = new HashMap<String, Object>();

		List<Parameter> parameters = model.getParameters();
		for (Parameter parameter : parameters) {
			Class<?> clasz = parameter.getType();
			if (clasz == List.class) {
				Object value = model.getValue(parameter.getName());
				value = new ArrayList<Object>((List<Object>) value);
				newValues.put(parameter.getName(), value);
			} else if (clasz == Map.class) {
				Object value = model.getValue(parameter.getName());
				value = new TreeMap<Object, Object>((Map<Object, Object>) value);
				newValues.put(parameter.getName(), value);
			}
		}
	}

	/**
	 * Creates tables for each parameter of the model. Copies the lists/maps
	 * beforehand, because we only want them to be modified by performOk.
	 * 
	 * @param parent
	 *            The parent composite.
	 */
	private void createParameterTables(Composite parent) {
		createNewValues();

		for (Object value : newValues.values()) {
			if (value instanceof List<?>) {
				createListTable(parent, value);
			} else if (value instanceof Map<?, ?>) {
				createMapTable(parent, value);
			}
		}
	}

	/**
	 * Creates the table component from the <code>parent</code> composite.
	 * 
	 * @param parent
	 *            The parent composite.
	 * @return The table created.
	 */
	private Table createTable(Composite parent) {
		int style = SWT.SINGLE | SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL
				| SWT.FULL_SELECTION | SWT.HIDE_SELECTION;

		Table table = new Table(parent, style);

		GridData gridData = new GridData(GridData.FILL_BOTH);
		gridData.horizontalSpan = 3;
		gridData.grabExcessVerticalSpace = true;
		table.setLayoutData(gridData);

		table.setLinesVisible(true);
		table.setHeaderVisible(true);

		return table;
	}

	@Override
	protected void performDefaults() {
	}

	@Override
	public boolean performOk() {
		for (Entry<String, Object> entry : newValues.entrySet()) {
			String parameterName = entry.getKey();
			model.setValue(parameterName, entry.getValue());
		}

		return true;
	}

}
