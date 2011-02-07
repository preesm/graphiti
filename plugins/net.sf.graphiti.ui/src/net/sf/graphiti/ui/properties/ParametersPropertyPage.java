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
import net.sf.graphiti.ui.GraphitiUiPlugin;

import org.eclipse.jface.dialogs.IInputValidator;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ControlAdapter;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
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

	private Map<String, TableViewer> tblViewers;

	/**
	 * Constructor for ParametersPropertyPage.
	 */
	public ParametersPropertyPage() {
		super();
	}

	@SuppressWarnings("unchecked")
	private void addListValue(String name) {
		TableViewer viewer = tblViewers.get(name);
		List<Object> list = (List<Object>) viewer.getInput();

		String dialogTitle = "New " + name;
		String dialogMessage = "Please enter " + name + ":";
		String initialValue = "";
		InputDialog dialog = new InputDialog(getShell(), dialogTitle,
				dialogMessage, initialValue, new IInputValidator() {

					@Override
					public String isValid(String newText) {
						return newText.isEmpty() ? "" : null;
					}

				});

		if (dialog.open() == InputDialog.OK) {
			list.add(dialog.getValue());
			viewer.refresh();
		}
	}

	@SuppressWarnings("unchecked")
	private void addMapValue(String name) {
		TableViewer viewer = tblViewers.get(name);
		Map<Object, Object> map = (Map<Object, Object>) viewer.getInput();

		String dialogTitle = "New " + name;
		String dialogMessage = "Please enter " + name + ":";
		String initialValue = "";
		InputDialog dialog = new InputDialog(getShell(), dialogTitle,
				dialogMessage, initialValue, new IInputValidator() {

					@Override
					public String isValid(String newText) {
						return newText.isEmpty() ? "" : null;
					}

				});

		if (dialog.open() == InputDialog.OK) {
			map.put(dialog.getValue(), "");
			viewer.refresh();
		}
	}

	/**
	 * @see PreferencePage#createContents(Composite)
	 */
	protected Control createContents(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);

		GridLayout layout = new GridLayout(2, false);
		composite.setLayout(layout);
		GridData data = new GridData(GridData.FILL, GridData.FILL, true, true);
		layout.marginWidth = 0;
		layout.marginHeight = 0;
		composite.setLayoutData(data);

		model = (AbstractObject) getElement().getAdapter(AbstractObject.class);
		setTitle("Parameters");
		createParameterTables(composite);

		return composite;
	}

	private void createListTable(Composite parent, String name, Object value,
			boolean isFirst) {
		final Table table = createTable(parent, name, isFirst, true);
		TableViewer tableViewer = new TableViewer(table);
		tableViewer.setContentProvider(new ListContentProvider());

		tblViewers.put(name, tableViewer);

		// 1st column
		final TableColumn column = new TableColumn(table, SWT.NONE);
		column.setText("Value");
		table.addControlListener(new ControlAdapter() {

			@Override
			public void controlResized(ControlEvent e) {
				Rectangle bounds = table.getClientArea();
				column.setWidth(bounds.width);
			}

		});

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

	private void createMapTable(Composite parent, String name, Object value,
			boolean isFirst) {
		final Table table = createTable(parent, name, isFirst, false);
		TableViewer tableViewer = new TableViewer(table);
		tableViewer.setContentProvider(new MapContentProvider());

		tblViewers.put(name, tableViewer);

		MapCellLabelProvider labelProvider = new MapCellLabelProvider();

		// 1st column
		final TableColumn column1 = new TableColumn(table, SWT.NONE, 0);
		column1.setText("Name");

		// 2nd column
		final TableColumn column2 = new TableColumn(table, SWT.NONE, 1);
		column2.setText("Value");

		// resizing
		table.addControlListener(new ControlAdapter() {

			@Override
			public void controlResized(ControlEvent e) {
				Rectangle bounds = table.getClientArea();
				column1.setWidth(bounds.width / 2);
				column2.setWidth(bounds.width / 2);
			}

		});

		TableViewerColumn tvc1 = new TableViewerColumn(tableViewer, column1);
		tvc1.setLabelProvider(labelProvider);

		TableViewerColumn tvc2 = new TableViewerColumn(tableViewer, column2);
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

	/**
	 * Creates tables for each parameter of the model. Copies the lists/maps
	 * beforehand, because we only want them to be modified by performOk.
	 * 
	 * @param parent
	 *            The parent composite.
	 */
	private void createParameterTables(Composite parent) {
		newValues = new HashMap<String, Object>();
		tblViewers = new HashMap<String, TableViewer>();

		initNewValues();

		boolean isFirst = true;

		for (Entry<String, Object> entry : newValues.entrySet()) {
			Object value = entry.getValue();
			if (value instanceof List<?>) {
				createListTable(parent, entry.getKey(), value, isFirst);
			} else if (value instanceof Map<?, ?>) {
				createMapTable(parent, entry.getKey(), value, isFirst);
			}

			isFirst = false;
		}
	}

	private void createShiftButtons(Composite parent, final String name,
			final Table table) {
		// create buttons
		final Button shiftUp = new Button(parent, SWT.NONE);
		final Button shiftDown = new Button(parent, SWT.NONE);

		shiftUp.setImage(GraphitiUiPlugin.getImage("icons/up_obj.gif"));
		shiftUp.setLayoutData(new GridData(SWT.LEFT, SWT.TOP, false, false));
		shiftUp.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				shiftValue(name, -1);
				updateButtonsState(table, shiftUp, shiftDown);
			}

		});

		// create buttons
		shiftDown.setImage(GraphitiUiPlugin.getImage("icons/down_obj.gif"));
		shiftDown.setLayoutData(new GridData(SWT.LEFT, SWT.TOP, false, false));
		shiftDown.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				shiftValue(name, 1);
				updateButtonsState(table, shiftUp, shiftDown);
			}

		});

		table.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				updateButtonsState(table, shiftUp, shiftDown);
			}

		});
	}

	/**
	 * Creates the table component from the <code>parent</code> composite.
	 * 
	 * @param parent
	 *            The parent composite.
	 * @return The table created.
	 */
	private Table createTable(Composite parent, final String name,
			boolean isFirst, boolean isList) {
		// add label
		Label label = new Label(parent, SWT.NONE);
		label.setText(name);
		GridData data = new GridData(SWT.LEFT, SWT.TOP, false, false, 2, 1);
		if (!isFirst) {
			data.verticalIndent = 10;
		}
		label.setLayoutData(data);

		// create table
		int style = SWT.SINGLE | SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL
				| SWT.FULL_SELECTION | SWT.HIDE_SELECTION;

		final Table table = new Table(parent, style);
		table.setLinesVisible(true);
		table.setHeaderVisible(true);

		// spans on n vertical cells
		int n = isList ? 4 : 2;
		data = new GridData(SWT.FILL, SWT.FILL, true, true, 1, n);
		data.horizontalIndent = 10;
		table.setLayoutData(data);

		// create buttons
		Button button = new Button(parent, SWT.NONE);
		button.setImage(GraphitiUiPlugin.getImage("icons/add_obj.gif"));
		button.setLayoutData(new GridData(SWT.LEFT, SWT.TOP, false, false));
		if (isList) {
			button.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					addListValue(name);
				}
			});
		} else {
			button.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					addMapValue(name);
				}
			});
		}

		// create buttons
		button = new Button(parent, SWT.NONE);
		button.setImage(GraphitiUiPlugin.getImage("icons/remove_obj.gif"));
		button.setLayoutData(new GridData(SWT.LEFT, SWT.TOP, false, false));
		if (isList) {
			button.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					removeListValue(name);
				}
			});
		} else {
			button.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					removeMapValue(name);
				}
			});
		}

		if (isList) {
			createShiftButtons(parent, name, table);
		}

		return table;
	}

	/**
	 * Initializes the newValues table from the values of model's parameters.
	 */
	@SuppressWarnings("unchecked")
	private void initNewValues() {
		List<Parameter> parameters = model.getParameters();
		for (Parameter parameter : parameters) {
			Class<?> clasz = parameter.getType();
			if (clasz == List.class) {
				String name = parameter.getName();
				List<Object> list = (List<Object>) newValues.get(name);
				if (list == null) {
					list = new ArrayList<Object>();
					newValues.put(name, list);
				} else {
					list.clear();
				}

				List<Object> value = (List<Object>) model.getValue(name);
				list.addAll(value);
			} else if (clasz == Map.class) {
				String name = parameter.getName();
				Map<Object, Object> map = (Map<Object, Object>) newValues
						.get(name);
				if (map == null) {
					map = new TreeMap<Object, Object>();
					newValues.put(name, map);
				} else {
					map.clear();
				}

				Map<Object, Object> value = (Map<Object, Object>) model
						.getValue(name);
				map.putAll(value);
			}
		}
	}

	@Override
	protected void performDefaults() {
		initNewValues();

		for (Entry<String, TableViewer> entry : tblViewers.entrySet()) {
			entry.getValue().setInput(newValues.get(entry.getKey()));
		}
	}

	@Override
	public boolean performOk() {
		for (Entry<String, Object> entry : newValues.entrySet()) {
			String parameterName = entry.getKey();
			model.setValue(parameterName, entry.getValue());
		}

		return true;
	}

	@SuppressWarnings("unchecked")
	private void removeListValue(String name) {
		TableViewer viewer = tblViewers.get(name);
		ISelection sel = viewer.getSelection();
		if (!sel.isEmpty() && sel instanceof IStructuredSelection) {
			IStructuredSelection ssel = (IStructuredSelection) sel;
			Object obj = ssel.getFirstElement();

			Object input = viewer.getInput();
			List<Object> list = (List<Object>) input;
			list.remove(obj);

			viewer.refresh();
		}
	}

	@SuppressWarnings("unchecked")
	private void removeMapValue(String name) {
		TableViewer viewer = tblViewers.get(name);
		ISelection sel = viewer.getSelection();
		if (!sel.isEmpty() && sel instanceof IStructuredSelection) {
			IStructuredSelection ssel = (IStructuredSelection) sel;
			Object obj = ssel.getFirstElement();

			Object input = viewer.getInput();
			Map<Object, Object> map = (Map<Object, Object>) input;
			map.remove(((Entry<Object, Object>) obj).getKey());

			viewer.refresh();
		}
	}

	@SuppressWarnings("unchecked")
	private void shiftValue(String name, int offset) {
		TableViewer viewer = tblViewers.get(name);
		ISelection sel = viewer.getSelection();
		if (!sel.isEmpty() && sel instanceof IStructuredSelection) {
			IStructuredSelection ssel = (IStructuredSelection) sel;
			Object obj = ssel.getFirstElement();

			Object input = viewer.getInput();
			if (input instanceof List<?>) {
				List<Object> list = (List<Object>) input;
				int index = list.indexOf(obj);
				Object element = list.remove(index);
				list.add(index + offset, element);

				viewer.refresh();
			}
		}
	}

	private void updateButtonsState(Table table, Button shiftUp,
			Button shiftDown) {
		int index = table.getSelectionIndex();
		shiftUp.setEnabled(index > 0);
		shiftDown.setEnabled(index < table.getItemCount() - 1);
	}

}
