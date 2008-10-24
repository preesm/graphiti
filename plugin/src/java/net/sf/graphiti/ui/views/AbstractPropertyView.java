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

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.ui.ISelectionListener;
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
public abstract class AbstractPropertyView extends ViewPart implements
		ISelectionListener {

	/**
	 * The {@link Composite} that contains all the children.
	 */
	private Composite panel;

	protected TableViewer tableViewer;

	/**
	 * Create actions on this property view. The default implementation does
	 * nothing.
	 */
	protected void createActions() {
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
		createTableViewer(table);
	}

	@Override
	public void createPartControl(Composite parent) {
		// Listens to selectionChanged event.
		getSite().getPage().addSelectionListener(this);

		createComponents(parent);
		createActions();

		// Create the help context id for the viewer's control
		PlatformUI.getWorkbench().getHelpSystem().setHelp(
				tableViewer.getControl(), "net.sf.graphiti.viewer");
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

		return table;
	}

	/**
	 * Creates the table viewer using the given table.
	 * 
	 * @param table
	 *            A {@link Table}.
	 */
	protected abstract void createTableViewer(Table table);

	@Override
	public void dispose() {
		// remove ourselves as a selection listener
		getSite().getPage().removeSelectionListener(this);
		panel.dispose();
		super.dispose();
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
				Object object = structSel.getFirstElement();
				selectionChanged(object);
			}
		}
	}

	/**
	 * This method is called when a new object is selected. The default
	 * implementation does nothing.
	 * 
	 * @param object
	 *            The new selected object.
	 */
	protected void selectionChanged(Object object) {
	}

	/**
	 * Passing the focus request to the viewer's control.
	 */
	@Override
	public void setFocus() {
		tableViewer.getControl().setFocus();
	}
}