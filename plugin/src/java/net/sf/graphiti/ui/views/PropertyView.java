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
import net.sf.graphiti.model.Vertex;
import net.sf.graphiti.ui.editparts.EdgeEditPart;
import net.sf.graphiti.ui.editparts.GraphEditPart;
import net.sf.graphiti.ui.editparts.VertexEditPart;

import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerComparator;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IViewReference;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

/**
 * This class exposes the Graphiti version of property view. It is a derivative
 * work from the SampleView provided by Eclipse, with the addition of proper
 * table viewer support for graph variable declarations, vertex parameters, and
 * edge properties.
 * 
 * @author Matthieu Wipliez
 */
public class PropertyView extends AbstractPropertyView {

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

	public static final String ID = "net.sf.graphiti.ui.views.PropertyView";

	/**
	 * Creates the table viewer using the given table.
	 * 
	 * @param table
	 *            A {@link Table}.
	 */
	protected void createTableViewer(Table table) {
		// 1st column
		TableColumn column = new TableColumn(table, SWT.CENTER, 0);
		column.setText("Name");
		column.setWidth(100);

		// 2nd column
		column = new TableColumn(table, SWT.LEFT, 1);
		column.setText("Value");
		column.setWidth(300);
		
		// Creates the table viewer on the table.
		tableViewer = new TableViewer(table);

		// content provider
		SimpleContentProvider contentProvider = new SimpleContentProvider();
		tableViewer.setContentProvider(contentProvider);

		// Sort by parameter name
		tableViewer.setComparator(new NameSorter());

		// cell label provider
		SimpleCellLabelProvider provider = new SimpleCellLabelProvider();
		contentProvider.addPropertyChangeListener(provider);

		// first column
		column = table.getColumn(0);
		TableViewerColumn tvc = new TableViewerColumn(tableViewer, column);
		tvc.setLabelProvider(provider);

		// second column
		column = table.getColumn(1);
		tvc = new TableViewerColumn(tableViewer, column);
		tvc.setLabelProvider(provider);

		// editing support for second column
		SimpleEditingSupport editing = new SimpleEditingSupport(tvc
				.getViewer(), table);
		contentProvider.addPropertyChangeListener(editing);
		tvc.setEditingSupport(editing);
	}

	/**
	 * Displays a new {@link ComplexPropertyView} for the given parameter on
	 * the given page.
	 * 
	 * @param page
	 * @param parameter
	 */
	private void displayView(IWorkbenchPage page, Object object) {
		List<Parameter> parameters = null;
		if (object instanceof Graph) {
			parameters = ((Graph) object).getParameters();
		} else if (object instanceof Vertex) {
			parameters = ((Vertex) object).getParameters();
		} else if (object instanceof Edge) {
			parameters = ((Edge) object).getParameters();
		}

		// parameters will *never* be null here.
		for (Parameter parameter : parameters) {
			Class<?> type = parameter.getType();
			if (type == Map.class || type == List.class) {
				String parameterName = parameter.getName();
				try {
					IViewPart part = page.showView(ComplexPropertyView.ID,
							parameterName, IWorkbenchPage.VIEW_VISIBLE);
					ComplexPropertyView view = (ComplexPropertyView) part;
					view.selectionChanged(object, parameter);
				} catch (PartInitException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * If the object selected is an instance of {@link GraphEditPart},
	 * {@link VertexEditPart} or {@link EdgeEditPart}, return the model
	 * associated. Otherwise, return null.
	 * 
	 * @param object
	 *            The object selected.
	 * @return The model of the object selected if it is a valid edit part.
	 */
	private Object getModel(Object object) {
		if (object instanceof GraphEditPart) {
			return ((GraphEditPart) object).getModel();
		} else if (object instanceof VertexEditPart) {
			return ((VertexEditPart) object).getModel();
		} else if (object instanceof EdgeEditPart) {
			return ((EdgeEditPart) object).getModel();
		} else {
			return null;
		}
	}

	@Override
	public void selectionChanged(Object object) {
		tableViewer.setInput(object);
		
		Object selectedObject = getModel(object);

		// active page. Sometimes null, hence the test we do.
		IWorkbenchPage page = PlatformUI.getWorkbench()
				.getActiveWorkbenchWindow().getActivePage();
		if (page == null) {
			return;
		}

		// hide all contextual property views
		IViewReference[] views = page.getViewReferences();
		for (IViewReference view : views) {
			if (view.getId().equals(ComplexPropertyView.ID)) {
				page.hideView(view);
			}
		}

		// displays new contextual property views if needed
		if (selectedObject != null) {
			displayView(page, selectedObject);
		}

		// bring us back to top
		page.bringToTop(this);
	}
}