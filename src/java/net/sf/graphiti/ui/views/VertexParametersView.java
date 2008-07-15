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

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

import net.sf.graphiti.model.Parameter;
import net.sf.graphiti.model.Vertex;
import net.sf.graphiti.ui.editparts.VertexEditPart;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerSorter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.ViewPart;

/**
 * 
 * @author Matthieu Wipliez
 */
public class VertexParametersView extends ViewPart implements
		ISelectionListener {

	class NameSorter extends ViewerSorter {
	}

	/**
	 * This class defines a content provider for the table displayed in this
	 * view.
	 * 
	 */
	private class VertexParametersContentProvider implements
			IStructuredContentProvider, PropertyChangeListener {

		@Override
		public void dispose() {
			if (vertex != null) {
				vertex.removePropertyChangeListener(this);
			}
		}

		@Override
		public Object[] getElements(Object inputElement) {
			if (vertex == null) {
				return new Object[] {};
			} else {
				List<Parameter> parameters = vertex.getParameters();
				return parameters.toArray();
			}
		}

		@Override
		public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
			if (vertex instanceof Vertex) {
				vertex.removePropertyChangeListener(this);
			}

			if (newInput instanceof VertexEditPart) {
				vertex = (Vertex) ((VertexEditPart) newInput).getModel();
				vertex.addPropertyChangeListener(this);
			} else {
				vertex = null;
			}
		}

		@Override
		public void propertyChange(PropertyChangeEvent evt) {
		}

	}

	/**
	 * This class defines a label provider for the content obtained from
	 * {@link VertexParametersContentProvider}.
	 * 
	 */
	private class VertexParametersLabelProvider implements ITableLabelProvider {

		@Override
		public void addListener(ILabelProviderListener listener) {
		}

		@Override
		public void dispose() {
		}

		@Override
		public Image getColumnImage(Object element, int columnIndex) {
			return null;
		}

		@Override
		public String getColumnText(Object element, int columnIndex) {
			if (element instanceof Parameter) {
				Parameter parameter = (Parameter) element;
				if (columnIndex == 0) {
					return parameter.getName();
				} else {
					return (String) vertex.getValue(parameter.getName());
				}
			} else {
				return null;
			}
		}

		@Override
		public boolean isLabelProperty(Object element, String property) {
			return false;
		}

		@Override
		public void removeListener(ILabelProviderListener listener) {
		}

	}

	public static final String ID_VERTEX_PARAMETERS = "net.sf.graphiti.ui.views.VertexParametersView";

	private Action action1;

	private Action action2;

	private Action doubleClickAction;

	/**
	 * The {@link Composite} that contains all the children.
	 */
	private Composite panel;

	private TableViewer tableViewer;

	private Vertex vertex;

	/**
	 * The constructor.
	 */
	public VertexParametersView() {
		// PropertySheetPage
	}

	/**
	 * 
	 */
	private void contributeToActionBars() {
		IActionBars bars = getViewSite().getActionBars();
		fillLocalPullDown(bars.getMenuManager());
		fillLocalToolBar(bars.getToolBarManager());
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

	@Override
	public void createPartControl(Composite parent) {
		getSite().getPage().addSelectionListener(this);
		
		panel = new Composite(parent, 0);

		GridData gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL
				| GridData.FILL_BOTH);
		panel.setLayoutData(gridData);

		GridLayout layout = new GridLayout(3, false);
		layout.marginWidth = 4;
		panel.setLayout(layout);

		// Create the table
		Table table = createTable(panel);

		// Create buttons
		createButtons(panel);

		// Creates the table viewer on the table.
		tableViewer = new TableViewer(table);
		tableViewer.setContentProvider(new VertexParametersContentProvider());
		tableViewer.setLabelProvider(new VertexParametersLabelProvider());

		// Create the help context id for the viewer's control
		PlatformUI.getWorkbench().getHelpSystem().setHelp(
				tableViewer.getControl(), "net.sf.graphiti.viewer");
		makeActions();
		hookContextMenu();
		hookDoubleClickAction();
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
		column.setWidth(20);

		// Add listener to column so tasks are sorted by description when
		// clicked
		column.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				// tableViewer.setSorter(new ExampleTaskSorter(
				// ExampleTaskSorter.DESCRIPTION));
			}
		});

		// 2nd column
		column = new TableColumn(table, SWT.LEFT, 1);
		column.setText("Value");
		column.setWidth(400);

		return table;
	}

	public void dispose() {
		// remove ourselves as a selection listener
        getSite().getPage().removeSelectionListener(this);
        super.dispose();
	}

	private void fillContextMenu(IMenuManager manager) {
		manager.add(action1);
		manager.add(action2);
		// Other plug-ins can contribute there actions here
		manager.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));
	}

	private void fillLocalPullDown(IMenuManager manager) {
		manager.add(action1);
		manager.add(new Separator());
		manager.add(action2);
	}

	private void fillLocalToolBar(IToolBarManager manager) {
		manager.add(action1);
		manager.add(action2);
	}

	private void hookContextMenu() {
		MenuManager menuMgr = new MenuManager("#PopupMenu");
		menuMgr.setRemoveAllWhenShown(true);
		menuMgr.addMenuListener(new IMenuListener() {
			public void menuAboutToShow(IMenuManager manager) {
				VertexParametersView.this.fillContextMenu(manager);
			}
		});
		Menu menu = menuMgr.createContextMenu(tableViewer.getControl());
		tableViewer.getControl().setMenu(menu);
		getSite().registerContextMenu(menuMgr, tableViewer);
	}

	private void hookDoubleClickAction() {
		tableViewer.addDoubleClickListener(new IDoubleClickListener() {
			public void doubleClick(DoubleClickEvent event) {
				doubleClickAction.run();
			}
		});
	}

	private void makeActions() {
		action1 = new Action() {
			public void run() {
				showMessage("Action 1 executed");
			}
		};
		action1.setText("Action 1");
		action1.setToolTipText("Action 1 tooltip");
		action1.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages()
				.getImageDescriptor(ISharedImages.IMG_OBJS_INFO_TSK));

		action2 = new Action() {
			public void run() {
				showMessage("Action 2 executed");
			}
		};
		action2.setText("Action 2");
		action2.setToolTipText("Action 2 tooltip");
		action2.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages()
				.getImageDescriptor(ISharedImages.IMG_OBJS_INFO_TSK));
		doubleClickAction = new Action() {
			public void run() {
				ISelection selection = tableViewer.getSelection();
				Object obj = ((IStructuredSelection) selection)
						.getFirstElement();
				showMessage("Double-click detected on " + obj.toString());
			}
		};
	}

	/**
	 * Passing the focus request to the viewer's control.
	 */
	public void setFocus() {
		tableViewer.getControl().setFocus();
	}

	private void showMessage(String message) {
		MessageDialog.openInformation(tableViewer.getControl().getShell(),
				"Sample View", message);
	}

	@Override
	public void selectionChanged(IWorkbenchPart part, ISelection selection) {
		// we ignore our own selection or null selection
		if (part == this || selection == null) {
			return;
		}
		
		if (selection.isEmpty() == false) {
			if (selection instanceof IStructuredSelection) {
				IStructuredSelection structSel = (IStructuredSelection) selection;
				tableViewer.setInput(structSel.getFirstElement());
			}
		}
	}
}