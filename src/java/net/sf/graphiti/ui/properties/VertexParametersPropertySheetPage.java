/*******************************************************************************
 * Copyright (c) 2000, 2008 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *     Gunnar Wagenknecht - fix for bug 21756 [PropertiesView] property view sorting
 *******************************************************************************/

package net.sf.graphiti.ui.properties;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.jface.viewers.ILazyTreePathContentProvider;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.TreePath;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.ISaveablePart;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.part.Page;
import org.eclipse.ui.views.properties.IPropertySheetPage;
import org.eclipse.ui.views.properties.IPropertySource;

/**
 * The standard implementation of property sheet page which presents a table of
 * property names and values obtained from the current selection in the active
 * workbench part.
 * <p>
 * This page obtains the information about what properties to display from the
 * current selection (which it tracks).
 * </p>
 * <p>
 * The model for this page is a hierarchy of <code>IPropertySheetEntry</code>.
 * The page may be configured with a custom model by setting the root entry.
 * <p>
 * If no root entry is set then a default model is created which uses the
 * <code>IPropertySource</code> interface to obtain the properties of the
 * current selection. This requires that the selected objects provide an
 * <code>IPropertySource</code> adapter (or implement
 * <code>IPropertySource</code> directly). This restiction can be overcome by
 * providing this page with an <code>IPropertySourceProvider</code>. If
 * supplied, this provider will be used by the default model to obtain a
 * property source for the current selection
 * </p>
 * <p>
 * This class may be instantiated; it is not intended to be subclassed.
 * </p>
 * 
 * @see IPropertySource
 * @noextend This class is not intended to be subclassed by clients.
 */
public class VertexParametersPropertySheetPage extends Page implements IPropertySheetPage,
		IAdaptable {

	private TreeViewer tree;
	
	/**
	 * Creates a new property sheet page.
	 */
	public VertexParametersPropertySheetPage() {
		super();
		// PropertySheetViewer
		// PropertySheetPage
	}

	/*
	 * (non-Javadoc) Method declared on <code>IPage</code>.
	 */
	public void createControl(Composite parent) {
        tree = new TreeViewer(parent);
        tree.setContentProvider(new ILazyTreePathContentProvider() {

			@Override
			public TreePath[] getParents(Object element) {
				return null;
			}

			@Override
			public void updateChildCount(TreePath treePath,
					int currentChildCount) {
				tree.setChildCount(treePath, 3);
			}

			@Override
			public void updateElement(TreePath parentPath, int index) {
			}

			@Override
			public void updateHasChildren(TreePath path) {
			}

			@Override
			public void dispose() {
			}

			@Override
			public void inputChanged(Viewer viewer, Object oldInput,
					Object newInput) {
			}
        	
        });
        
        tree.setInput("t1");
	}

	/**
	 * The <code>PropertySheetPage</code> implementation of this
	 * <code>IPage</code> method disposes of this page's entries.
	 */
	public void dispose() {
		super.dispose();
	}

	/**
	 * The <code>PropertySheetPage</code> implementation of this
	 * <code>IAdaptable</code> method handles the <code>ISaveablePart</code>
	 * adapter by delegating to the source part.
	 * 
	 * @since 3.2
	 */
	@SuppressWarnings("unchecked")
	public Object getAdapter(Class adapter) {
		if (ISaveablePart.class.equals(adapter)) {
			return getSaveablePart();
		}
		return null;
	}

	/**
	 * Returns an <code>ISaveablePart</code> that delegates to the source part
	 * for the current page if it implements <code>ISaveablePart</code>, or
	 * <code>null</code> otherwise.
	 * 
	 * @return an <code>ISaveablePart</code> or <code>null</code>
	 * @since 3.2
	 */
	protected ISaveablePart getSaveablePart() {
		return null;
	}

	@Override
	public Control getControl() {
		return tree.getControl();
	}

	@Override
	public void setFocus() {
		
	}

	@Override
	public void selectionChanged(IWorkbenchPart part, ISelection selection) {
		tree.refresh();
	}

	

}
