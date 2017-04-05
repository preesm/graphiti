/*******************************************************************************
 * Copyright or © or Copr. IETR/INSA - Rennes (2008 - 2017) :
 *
 * Antoine Morvan <antoine.morvan@insa-rennes.fr> (2017)
 * Clément Guy <clement.guy@insa-rennes.fr> (2014 - 2015)
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
package org.ietr.dftools.graphiti.ui.properties;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.dialogs.IInputValidator;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.CellLabelProvider;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;
import org.ietr.dftools.graphiti.model.AbstractObject;
import org.ietr.dftools.graphiti.ui.editors.GraphEditor;

/**
 * This class defines a list section.
 * 
 * @author Matthieu Wipliez
 * 
 */
public class ListSection extends AbstractSection {

	/**
	 * This class is a {@link CellLabelProvider} for a list.
	 * 
	 * @author Matthieu Wipliez
	 */
	private class ListCellLabelProvider extends CellLabelProvider {

		@Override
		public void update(ViewerCell cell) {
			if (cell.getColumnIndex() == 0) {
				cell.setText((String) cell.getElement());
			}
		}

	}

	/**
	 * This class defines a content provider for a list.
	 * 
	 * @author Matthieu Wipliez
	 */
	private class ListContentProvider implements IStructuredContentProvider {

		@Override
		public void dispose() {
		}

		@Override
		public Object[] getElements(Object inputElement) {
			AbstractObject model = (AbstractObject) inputElement;
			java.util.List<?> list = (java.util.List<?>) model
					.getValue(parameterName);
			return list.toArray();
		}

		@Override
		public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		}

	}

	/**
	 * This class provides {@link EditingSupport} for lists.
	 * 
	 * @author Matthieu Wipliez
	 * 
	 */
	private class ListEditingSupport extends EditingSupport {

		private TextCellEditor editor;

		/**
		 * Creates a new {@link ListEditingSupport} on the given column viewer
		 * and table.
		 * 
		 * @param viewer
		 * @param table
		 */
		public ListEditingSupport(ColumnViewer viewer, Table table) {
			super(viewer);
			editor = new TextCellEditor(table);
		}

		@Override
		protected boolean canEdit(Object element) {
			return true;
		}

		@Override
		protected CellEditor getCellEditor(Object element) {
			return editor;
		}

		@Override
		protected Object getValue(Object element) {
			return element.toString();
		}

		@Override
		@SuppressWarnings("unchecked")
		protected void setValue(Object element, Object value) {
			AbstractObject model = (AbstractObject) getViewer().getInput();
			java.util.List<Object> oldList = (java.util.List<Object>) model
					.getValue(parameterName);
			int index = oldList.indexOf(element);
			if (index == -1 || oldList.get(index).equals(value)) {
				return;
			}

			java.util.List<Object> newList = new ArrayList<Object>(oldList);
			newList.set(index, value);

			IWorkbenchPart part = getPart();
			if (part instanceof GraphEditor) {
				ParameterChangeValueCommand command = new ParameterChangeValueCommand(
						model, "Change list value");
				command.setValue(parameterName, newList);
				((GraphEditor) part).executeCommand(command);
			}
		}

	}

	@Override
	@SuppressWarnings("unchecked")
	protected void buttonAddSelected() {
		AbstractObject model = getModel();

		String dialogTitle = "New value";
		String dialogMessage = "Please enter a value:";
		String initialValue = "";
		InputDialog dialog = new InputDialog(getShell(), dialogTitle,
				dialogMessage, initialValue, new IInputValidator() {

					@Override
					public String isValid(String newText) {
						return newText.isEmpty() ? "" : null;
					}

				});

		if (dialog.open() == Window.OK) {
			List<Object> oldList = (List<Object>) model.getValue(parameterName);
			List<Object> newList = new ArrayList<Object>(oldList);
			newList.add(dialog.getValue());

			IWorkbenchPart part = getPart();
			if (part instanceof GraphEditor) {
				ParameterChangeValueCommand command = new ParameterChangeValueCommand(
						model, "Add element to list");
				command.setValue(parameterName, newList);
				((GraphEditor) part).executeCommand(command);
			}
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	protected void buttonRemoveSelected() {
		IStructuredSelection ssel = getTableSelection();
		if (ssel != null && !ssel.isEmpty()) {
			Object obj = ssel.getFirstElement();

			AbstractObject model = getModel();
			List<Object> oldList = (List<Object>) model.getValue(parameterName);
			List<Object> newList = new ArrayList<Object>(oldList);
			newList.remove(obj);

			IWorkbenchPart part = getPart();
			if (part instanceof GraphEditor) {
				ParameterChangeValueCommand command = new ParameterChangeValueCommand(
						model, "Remove element from list");
				command.setValue(parameterName, newList);
				((GraphEditor) part).executeCommand(command);
			}
		}
	}

	@Override
	public void createControls(Composite parent,
			TabbedPropertySheetPage aTabbedPropertySheetPage) {
		super.createControls(parent, aTabbedPropertySheetPage);

		createListTable(getForm().getBody());
	}

	private void createListTable(Composite parent) {
		Table table = createTable(parent);

		// spans on 4 vertical cells
		GridData data = new GridData(SWT.FILL, SWT.FILL, false, true, 1, 4);
		table.setLayoutData(data);

		createShiftButtons(parent, table);

		TableViewer tableViewer = getViewer();
		tableViewer.setContentProvider(new ListContentProvider());
		tableViewer.setLabelProvider(new ListCellLabelProvider());

		// 1st column
		TableColumn column = new TableColumn(table, SWT.NONE);
		column.setText("Value");
		column.setWidth(200);

		// first column
		TableViewerColumn tvc = new TableViewerColumn(tableViewer, column);
		tvc.setLabelProvider(new ListCellLabelProvider());

		// editing support
		EditingSupport editing = new ListEditingSupport(tvc.getViewer(),
				tableViewer.getTable());
		tvc.setEditingSupport(editing);
	}

	private void createShiftButtons(Composite parent, final Table table) {
		// create buttons
		final Button shiftUp = getWidgetFactory().createButton(parent, "Up",
				SWT.NONE);
		final Button shiftDown = getWidgetFactory().createButton(parent,
				"Down", SWT.NONE);

		shiftUp.setLayoutData(new GridData(SWT.FILL, SWT.TOP, false, true));
		shiftUp.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				shiftValue(-1);
				updateButtonsState(table, shiftUp, shiftDown);
			}

		});

		// create buttons
		shiftDown.setLayoutData(new GridData(SWT.FILL, SWT.TOP, false, true));
		shiftDown.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				shiftValue(1);
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

	@SuppressWarnings("unchecked")
	private void shiftValue(int offset) {
		IStructuredSelection ssel = getTableSelection();
		if (ssel != null && !ssel.isEmpty()) {
			Object obj = ssel.getFirstElement();

			AbstractObject model = getModel();
			List<Object> oldList = (List<Object>) model.getValue(parameterName);
			List<Object> newList = new ArrayList<Object>(oldList);

			int index = newList.indexOf(obj);
			Object element = newList.remove(index);
			newList.add(index + offset, element);

			IWorkbenchPart part = getPart();
			if (part instanceof GraphEditor) {
				ParameterChangeValueCommand command = new ParameterChangeValueCommand(
						model, "Move element");
				command.setValue(parameterName, newList);
				((GraphEditor) part).executeCommand(command);
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
