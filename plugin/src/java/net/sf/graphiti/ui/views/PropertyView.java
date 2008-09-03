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

import net.sf.graphiti.model.Parameter;
import net.sf.graphiti.ui.GraphitiPlugin;
import net.sf.graphiti.ui.editparts.GraphEditPart;
import net.sf.graphiti.ui.editparts.VertexEditPart;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerComparator;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.ViewPart;

/**
 * This class exposes the Graphiti version of property view. It is a derivative
 * work from the SampleView provided by Eclipse, with the addition of proper
 * table viewer support for graph variable declarations, vertex parameters, and
 * edge properties.
 * 
 * @author Matthieu Wipliez
 */
public class PropertyView extends ViewPart implements ISelectionListener {

	private class NameSorter extends ViewerComparator {

		@Override
		public int compare(Viewer viewer, Object e1, Object e2) {
			Parameter p1 = (Parameter) e1;
			Parameter p2 = (Parameter) e2;

			return p1.getName().compareTo(p2.getName());
		}
	}

	public static final String ID_PROPERTY_VIEW = "net.sf.graphiti.ui.views.PropertyView";

	private Action actionAdd;

	private Action actionDelete;

	/**
	 * The {@link Composite} that contains all the children.
	 */
	private Composite panel;

	private TableViewer tableViewer;

	/**
	 * The constructor.
	 */
	public PropertyView() {
	}

	/**
	 * Contributes to pull down and tool bar menu managers.
	 */
	private void contributeToActionBars() {
		IActionBars bars = getViewSite().getActionBars();
		contributeToPullDown(bars.getMenuManager());
		contributeToToolBar(bars.getToolBarManager());
	}

	/**
	 * Contributes to the context menu manager.
	 * 
	 * @param manager
	 *            The context menu manager.
	 */
	private void contributeToContextMenu(IMenuManager manager) {
		manager.add(actionAdd);
		manager.add(actionDelete);
		// Other plug-ins can contribute there actions here
		manager.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));
	}

	/**
	 * Contributes to the pull down menu manager.
	 * 
	 * @param manager
	 *            The pull down menu manager.
	 */
	private void contributeToPullDown(IMenuManager manager) {
		manager.add(actionAdd);
		manager.add(actionDelete);
	}

	/**
	 * Contributes to the tool bar menu manager.
	 * 
	 * @param manager
	 *            The tool bar menu manager.
	 */
	private void contributeToToolBar(IToolBarManager manager) {
		manager.add(actionAdd);
		manager.add(actionDelete);
	}

	/**
	 * Creates the Add and Delete buttons on the parent {@link Composite}.
	 * 
	 * @param parent
	 *            The parent {@link Composite}.
	 */
	private void createButtons(Composite parent) {
		Button buttonAdd = new Button(parent, SWT.PUSH);
		buttonAdd.setText("Add");
		buttonAdd.setLayoutData(new GridData());

		Button buttonDelete = new Button(parent, SWT.PUSH);
		buttonDelete.setText("Delete");
		buttonDelete.setLayoutData(new GridData());
	}

	/**
	 * Creates a panel on the parent, sets the layout, and creates the different
	 * UI components.
	 * 
	 * @param parent
	 *            The parent {@link Composite}.
	 */
	private void createComponents(Composite parent) {
		panel = new Composite(parent, 0);

		GridData gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL
				| GridData.FILL_BOTH);
		panel.setLayoutData(gridData);

		GridLayout layout = new GridLayout(3, false);
		layout.marginWidth = 4;
		panel.setLayout(layout);

		Table table = createTable(panel);
		createButtons(panel);
		createTableViewer(table);
	}

	@Override
	public void createPartControl(Composite parent) {
		// Listens to selectionChanged event.
		getSite().getPage().addSelectionListener(this);

		createComponents(parent);

		// Create the help context id for the viewer's control
		PlatformUI.getWorkbench().getHelpSystem().setHelp(
				tableViewer.getControl(), "net.sf.graphiti.viewer");
		makeActions();
		hookContextMenu();
		contributeToActionBars();
	}

	/**
	 * Creates the table component from the <code>parent</code>
	 * {@link Composite}.
	 * 
	 * @param parent
	 *            The {@link Composite} parent.
	 * @return The {@link Table} created.
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

		// 1st column
		TableColumn column = new TableColumn(table, SWT.CENTER, 0);
		column.setText("Name");
		column.setWidth(100);

		// 2nd column
		column = new TableColumn(table, SWT.LEFT, 1);
		column.setText("Value");
		column.setWidth(300);

		return table;
	}

	/**
	 * Creates the table viewer using the given table.
	 * 
	 * @param table
	 *            A {@link Table}.
	 */
	private void createTableViewer(Table table) {
		// Creates the table viewer on the table.
		tableViewer = new TableViewer(table);

		// content provider
		PropertiesContentProvider contentProvider = new PropertiesContentProvider();
		tableViewer.setContentProvider(contentProvider);

		// Sort by parameter name
		tableViewer.setComparator(new NameSorter());

		// cell label provider
		PropertiesCellLabelProvider provider = new PropertiesCellLabelProvider();
		contentProvider.addPropertyChangeListener(provider);

		// first column
		TableColumn column = table.getColumn(0);
		TableViewerColumn tvc = new TableViewerColumn(tableViewer, column);
		tvc.setLabelProvider(provider);

		// second column
		column = table.getColumn(1);
		tvc = new TableViewerColumn(tableViewer, column);
		tvc.setLabelProvider(provider);

		// editing support for second column
		PropertiesEditingSupport editing = new PropertiesEditingSupport(tvc
				.getViewer(), table);
		contentProvider.addPropertyChangeListener(editing);
		tvc.setEditingSupport(editing);
	}

	@Override
	public void dispose() {
		// remove ourselves as a selection listener
		getSite().getPage().removeSelectionListener(this);
		super.dispose();
	}

	private void hookContextMenu() {
		MenuManager menuMgr = new MenuManager("#PopupMenu");
		menuMgr.setRemoveAllWhenShown(true);
		menuMgr.addMenuListener(new IMenuListener() {
			public void menuAboutToShow(IMenuManager manager) {
				PropertyView.this.contributeToContextMenu(manager);
			}
		});
		Menu menu = menuMgr.createContextMenu(tableViewer.getControl());
		tableViewer.getControl().setMenu(menu);
		getSite().registerContextMenu(menuMgr, tableViewer);
	}

	/**
	 * Creates the "add" and "delete" actions.
	 */
	private void makeActions() {
		actionAdd = new Action() {
			public void run() {
				showMessage("Action 1 executed");
			}
		};
		actionAdd.setText("Add parameter");
		actionAdd.setToolTipText("Adds a parameter to this vertex");
		actionAdd.setImageDescriptor(GraphitiPlugin
				.getImageDescriptor("icons/add_obj.gif"));

		actionDelete = new Action() {
			public void run() {
				showMessage("Action 2 executed");
			}
		};
		actionDelete.setText("Remove parameter");
		actionDelete.setToolTipText("Removes a parameter from this vertex");
		actionDelete.setImageDescriptor(GraphitiPlugin
				.getImageDescriptor("icons/remove_obj.gif"));

		// doubleClickAction = new Action() {
		// public void run() {
		// ISelection selection = tableViewer.getSelection();
		// Object obj = ((IStructuredSelection) selection)
		// .getFirstElement();
		// showMessage("Double-click detected on " + obj.toString());
		// }
		// };
	}

	@Override
	public void selectionChanged(IWorkbenchPart part, ISelection selection) {
		// we ignore our own selection or null selection
		if (part == this || selection == null) {
			return;
		}

		// enable parameters addition/removal or not
		boolean flag = false;

		if (selection.isEmpty() == false) {
			if (selection instanceof IStructuredSelection) {
				IStructuredSelection structSel = (IStructuredSelection) selection;
				Object obj = structSel.getFirstElement();
				tableViewer.setInput(obj);

				flag = (obj instanceof GraphEditPart || obj instanceof VertexEditPart);
			}
		}

		actionAdd.setEnabled(flag);
		actionDelete.setEnabled(flag);
	}

	/**
	 * Passing the focus request to the viewer's control.
	 */
	@Override
	public void setFocus() {
		tableViewer.getControl().setFocus();
	}

	private void showMessage(String message) {
		MessageDialog.openInformation(tableViewer.getControl().getShell(),
				"Sample View", message);
	}
}