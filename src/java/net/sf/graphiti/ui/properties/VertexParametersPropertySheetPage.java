package net.sf.graphiti.ui.properties;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

import net.sf.graphiti.model.Parameter;
import net.sf.graphiti.model.Vertex;
import net.sf.graphiti.ui.editparts.VertexEditPart;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.ui.ISaveablePart;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.part.Page;
import org.eclipse.ui.views.properties.IPropertySheetPage;

/**
 * Some code is taken from Laurent Gauthier (e-mail: lgauthier@opnworks.com)
 * TableViewer tutorial.
 * 
 * @author Matthieu Wipliez
 */
public class VertexParametersPropertySheetPage extends Page implements
		IPropertySheetPage, IAdaptable {

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
	 * The {@link Composite} that contains all the children.
	 */
	private Composite panel;

	private TableViewer tableViewer;

	private Vertex vertex;

	/**
	 * Creates a new property sheet page.
	 */
	public VertexParametersPropertySheetPage() {
		super();
		// PropertySheetViewer
		// PropertySheetPage
		// PropertySheet
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
	public void createControl(Composite parent) {
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

	@Override
	public Control getControl() {
		return panel;
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
	public void selectionChanged(IWorkbenchPart part, ISelection selection) {
		if (selection.isEmpty() == false) {
			if (selection instanceof IStructuredSelection) {
				IStructuredSelection structSel = (IStructuredSelection) selection;
				tableViewer.setInput(structSel.getFirstElement());
			}
		}
	}

	@Override
	public void setFocus() {

	}

}
