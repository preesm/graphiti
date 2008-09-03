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

import net.sf.graphiti.model.Edge;
import net.sf.graphiti.model.Graph;
import net.sf.graphiti.model.Parameter;
import net.sf.graphiti.model.PropertyBean;
import net.sf.graphiti.model.Vertex;
import net.sf.graphiti.ui.GraphitiPlugin;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerComparator;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.ui.IActionBars;

/**
 * This class is a {@link PropertyView} that is shown or not according to the
 * selection.
 * 
 * @author Matthieu Wipliez
 */
public class ContextualPropertyView extends AbstractPropertyView {

	/**
	 * Sorts parameters by name.
	 * 
	 * @author Matthieu Wipliez
	 * 
	 */
	private class NameSorter extends ViewerComparator {

		@Override
		public int compare(Viewer viewer, Object e1, Object e2) {
			Parameter p1 = (Parameter) e1;
			Parameter p2 = (Parameter) e2;

			return p1.getName().compareTo(p2.getName());
		}
	}

	public static final String ID = "net.sf.graphiti.ui.views.ContextualPropertyView";

	private Action actionAdd;

	private Action actionDelete;

	/**
	 * Description of the selected object.
	 */
	private String objDesc;
	
	private Parameter parameter;
	
	private PropertyBean source;

	@SuppressWarnings("unchecked")
	private void addValue() {
		Object obj = source.getValue(parameter.getName());
		if (obj instanceof List<?>) {
			((List<Object>) obj).add("new value");
		} else {
			((Map<Object, Object>) obj).put("new key", "new value");
		}
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

		// content provider
		ComplexPropertiesContentProvider contentProvider = new ComplexPropertiesContentProvider();
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

		// doubleClickAction = new Action() {
		// public void run() {
		// ISelection selection = tableViewer.getSelection();
		// Object obj = ((IStructuredSelection) selection)
		// .getFirstElement();
		// showMessage("Double-click detected on " + obj.toString());
		// }
		// };
	}

	private void removeValue() {
		
	}


	@Override
	protected void selectionChanged(Object object) {
		tableViewer.setInput(object);
	}
	
	/**
	 * Sets this part's name, its content description, and the tooltips of the
	 * two actions from the given object and parameter.
	 * 
	 * @param object
	 * @param parameter
	 */
	public void selectionChanged(Object object, Parameter parameter) {
		source = (PropertyBean) object;
		this.parameter = parameter;
		if (object instanceof Graph) {
			objDesc = "graph";
		} else if (object instanceof Vertex) {
			objDesc = "vertex";
		} else if (object instanceof Edge) {
			objDesc = "edge";
		}

		String parameterName = parameter.getName();
		String description = parameterName + " of the selected " + objDesc;
		super.setContentDescription(description);
		super.setPartName(parameterName);

		actionAdd.setToolTipText("Adds a parameter to this " + objDesc);
		actionDelete.setToolTipText("Removes a parameter from this " + objDesc);
	}
}