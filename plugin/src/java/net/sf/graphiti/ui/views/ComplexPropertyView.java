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
package net.sf.graphiti.ui.views;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import net.sf.graphiti.model.Edge;
import net.sf.graphiti.model.Graph;
import net.sf.graphiti.model.Parameter;
import net.sf.graphiti.model.PropertyBean;
import net.sf.graphiti.model.Util;
import net.sf.graphiti.model.Vertex;
import net.sf.graphiti.ui.GraphitiPlugin;
import net.sf.graphiti.ui.commands.AddParameterCommand;
import net.sf.graphiti.ui.commands.RemoveParameterCommand;
import net.sf.graphiti.ui.editors.GraphEditor;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.dialogs.IInputValidator;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerComparator;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;

/**
 * This class is a {@link PropertyView} that shows complex properties (ie List
 * or Map).
 * 
 * @author Matthieu Wipliez
 */
public class ComplexPropertyView extends AbstractPropertyView {

	/**
	 * Sorts parameters by name.
	 * 
	 * @author Matthieu Wipliez
	 * 
	 */
	private class NameSorter extends ViewerComparator {

		@Override
		@SuppressWarnings("unchecked")
		public int compare(Viewer viewer, Object e1, Object e2) {
			if (e1 instanceof String) {
				String s1 = (String) e1;
				String s2 = (String) e2;

				return s1.compareTo(s2);
			} else if (e1 instanceof Entry<?, ?>) {
				String s1 = (String) ((Entry<Object, Object>) e1).getKey();
				String s2 = (String) ((Entry<Object, Object>) e2).getKey();

				return s1.compareTo(s2);
			} else {
				return 0;
			}
		}
	}

	public static final String ID = "net.sf.graphiti.ui.views.ComplexPropertyView";

	/**
	 * The add action.
	 */
	private Action actionAdd;

	/**
	 * The delete action.
	 */
	private Action actionDelete;

	/**
	 * The article before the parameter name.
	 */
	private String article;

	/**
	 * Description of the selected object.
	 */
	private String objDesc;

	/**
	 * The parameter name.
	 */
	private String parameterName;

	/**
	 * The source of this complex property view.
	 */
	private ComplexSource source;

	/**
	 * Adds one or two columns to the given {@link Table} depending on the
	 * source's parameter type.
	 * 
	 * @param table
	 *            The table.
	 */
	private void addColumns(Table table) {
		if (source.parameter.getType() == List.class) {
			// 1st column
			TableColumn column = new TableColumn(table, SWT.CENTER, 0);
			column.setText("Value");
			column.setWidth(100);
		} else {
			// 1st column
			TableColumn column = new TableColumn(table, SWT.CENTER, 0);
			column.setText("Name");
			column.setWidth(100);

			// 2nd column
			column = new TableColumn(table, SWT.LEFT, 1);
			column.setText("Value");
			column.setWidth(300);
		}
	}

	/**
	 * Adds a new value to the current table. If our source parameter type is
	 * List, we add "new value", else we add "new key" = "new value".
	 */
	private void addValue() {
		IWorkbench workbench = PlatformUI.getWorkbench();
		IWorkbenchWindow window = workbench.getActiveWorkbenchWindow();
		Shell parentShell = window.getShell();

		String dialogTitle = "New " + parameterName;
		String dialogMessage = "Please enter " + article + " " + parameterName
				+ ":";
		String initialValue = "";
		InputDialog dialog = new InputDialog(parentShell, dialogTitle,
				dialogMessage, initialValue, new IInputValidator() {

					@Override
					public String isValid(String newText) {
						return newText.isEmpty() ? "" : null;
					}

				});

		if (dialog.open() == InputDialog.OK) {
			AddParameterCommand command = new AddParameterCommand(source.bean,
					dialog.getValue());
			if (source.parameter.getType() == List.class) {
				List<Object> list = Util.getList(source.bean, source.parameter);
				command.setList(list);
			} else {
				Map<Object, Object> map = Util.getMap(source.bean,
						source.parameter);
				command.setMap(map);
			}

			IEditorPart part = window.getActivePage().getActiveEditor();
			if (part instanceof GraphEditor) {
				((GraphEditor) part).executeCommand(command);
			}
		}
	}

	/**
	 * Configures our table viewer by adding columns and setting content
	 * handlers.
	 */
	private void configureTableViewer() {
		Table table = tableViewer.getTable();

		// table viewer comparator
		tableViewer.setComparator(new NameSorter());

		// content provider
		ComplexContentProvider contentProvider = new ComplexContentProvider();
		tableViewer.setContentProvider(contentProvider);

		// columns
		addColumns(table);

		// content handlers
		if (source.parameter.getType() == List.class) {
			setListContentHandler(table, contentProvider);
		} else if (source.parameter.getType() == Map.class) {
			setMapContentHandler(table, contentProvider);
		}

		tableViewer.setInput(source);
	}

	/**
	 * Contributes to pull down and tool bar menu managers.
	 */
	private void contributeToActionBars() {
		IActionBars bars = getViewSite().getActionBars();

		IMenuManager manager = bars.getMenuManager();
		manager.add(actionAdd);
		manager.add(actionDelete);

		IToolBarManager toolbar = bars.getToolBarManager();
		toolbar.add(actionAdd);
		toolbar.add(actionDelete);
	}

	@Override
	public void createActions() {
		makeActions();
		contributeToActionBars();
	}

	/**
	 * Creates the table viewer using the given table.
	 * 
	 * @param table
	 *            A {@link Table}.
	 */
	protected void createTableViewer(Table table) {
		// Creates the table viewer on the table.
		tableViewer = new TableViewer(table);
	}

	/**
	 * Creates the "add" and "delete" actions.
	 */
	private void makeActions() {
		actionAdd = new Action() {
			public void run() {
				addValue();
			}
		};
		actionAdd.setText("Add parameter");
		actionAdd.setImageDescriptor(GraphitiPlugin
				.getImageDescriptor("icons/add_obj.gif"));

		actionDelete = new Action() {
			public void run() {
				removeValue();
			}
		};
		actionDelete.setText("Remove parameter");
		actionDelete.setImageDescriptor(GraphitiPlugin
				.getImageDescriptor("icons/remove_obj.gif"));
	}

	/**
	 * Removes the selected value from the current table.
	 */
	@SuppressWarnings("unchecked")
	private void removeValue() {
		ISelection sel = tableViewer.getSelection();
		if (sel instanceof IStructuredSelection) {
			IStructuredSelection ssel = (IStructuredSelection) sel;
			Object obj = ssel.getFirstElement();

			IWorkbench workbench = PlatformUI.getWorkbench();
			IWorkbenchWindow window = workbench.getActiveWorkbenchWindow();

			RemoveParameterCommand command = new RemoveParameterCommand(
					source.bean);
			if (source.parameter.getType() == List.class) {
				List<Object> list = Util.getList(source.bean, source.parameter);
				command.setValue(obj);
				command.setList(list);
			} else {
				Map<Object, Object> map = Util.getMap(source.bean,
						source.parameter);
				command.setValue(((Entry<Object, Object>) obj).getKey());
				command.setMap(map);
			}

			IEditorPart part = window.getActivePage().getActiveEditor();
			if (part instanceof GraphEditor) {
				((GraphEditor) part).executeCommand(command);
			}
		}
	}

	@Override
	protected void selectionChanged(Object object) {
	}

	/**
	 * Sets this part's name, its content description, and the tooltips of the
	 * two actions from the given object and parameter.
	 * 
	 * @param object
	 * @param parameter
	 */
	public void selectionChanged(Object object, Parameter parameter) {
		source = new ComplexSource((PropertyBean) object, parameter);
		configureTableViewer();

		if (object instanceof Graph) {
			objDesc = "graph";
		} else if (object instanceof Vertex) {
			objDesc = "vertex";
		} else if (object instanceof Edge) {
			objDesc = "edge";
		}

		// uppercase first letter
		parameterName = parameter.getName();
		char[] chars = (parameterName + "s").toCharArray();
		char firstLetter = chars[0];
		chars[0] = Character.toUpperCase(chars[0]);
		String uppercasedParamName = new String(chars);

		// part name and description
		String description = uppercasedParamName + " of the selected "
				+ objDesc;
		super.setContentDescription(description);
		super.setPartName(uppercasedParamName);

		// set the article
		article = "a";
		if (firstLetter == 'a' || firstLetter == 'e' || firstLetter == 'i'
				|| firstLetter == 'o') {
			article += "n";
		}

		// action tool-tips.
		actionAdd.setToolTipText("Adds " + article + " " + parameterName
				+ " to this " + objDesc);
		actionDelete.setToolTipText("Removes " + article + " " + parameterName
				+ " from this " + objDesc);
	}

	/**
	 * Sets the content handler: cell label provider and editing support.
	 * 
	 * @param table
	 * @param provider
	 */
	private void setListContentHandler(Table table,
			ComplexContentProvider provider) {
		// first column
		TableColumn column = table.getColumn(0);
		TableViewerColumn tvc = new TableViewerColumn(tableViewer, column);

		// cell label provider
		tvc.setLabelProvider(new ComplexCellLabelProvider());

		// editing support for first column
		ComplexValueEditingSupport editing = new ComplexValueEditingSupport(tvc
				.getViewer(), table);
		provider.addPropertyChangeListener(editing);
		tvc.setEditingSupport(editing);
	}

	/**
	 * Sets the content handler: cell label provider and editing support.
	 * 
	 * @param table
	 * @param provider
	 */
	private void setMapContentHandler(Table table,
			ComplexContentProvider provider) {
		ComplexCellLabelProvider labelProvider = new ComplexCellLabelProvider();

		// first column
		TableColumn column = table.getColumn(0);
		TableViewerColumn tvc = new TableViewerColumn(tableViewer, column);
		tvc.setLabelProvider(labelProvider);

		// editing support for first column
		ComplexNameEditingSupport nameEditing = new ComplexNameEditingSupport(
				tvc.getViewer(), table);
		provider.addPropertyChangeListener(nameEditing);
		tvc.setEditingSupport(nameEditing);

		// second column
		column = table.getColumn(1);
		tvc = new TableViewerColumn(tableViewer, column);
		tvc.setLabelProvider(labelProvider);

		// editing support for second column
		ComplexValueEditingSupport valueEditing = new ComplexValueEditingSupport(
				tvc.getViewer(), table);
		provider.addPropertyChangeListener(valueEditing);
		tvc.setEditingSupport(valueEditing);
	}
}