package net.sf.graphiti.ui.properties;

import java.util.List;
import java.util.Map;

import net.sf.graphiti.model.AbstractObject;
import net.sf.graphiti.model.Edge;
import net.sf.graphiti.model.Graph;
import net.sf.graphiti.model.Parameter;
import net.sf.graphiti.model.Vertex;
import net.sf.graphiti.model.VertexType;

import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Table;
import org.eclipse.ui.dialogs.PropertyPage;

public class ParametersPropertyPage extends PropertyPage {

	private AbstractObject model;

	private TableViewer tableViewer;

	/**
	 * Constructor for ParametersPropertyPage.
	 */
	public ParametersPropertyPage() {
		super();
	}

	/**
	 * @see PreferencePage#createContents(Composite)
	 */
	protected Control createContents(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout();
		composite.setLayout(layout);
		GridData data = new GridData(GridData.FILL);
		data.grabExcessHorizontalSpace = true;
		composite.setLayoutData(data);

		model = (AbstractObject) getElement().getAdapter(AbstractObject.class);
		setTitle();
		createParameterTables(composite);

		return composite;
	}

	private void setTitle() {
		String name = null;
		if (model instanceof Vertex) {
			name = "\"" + model.getValue(VertexType.PARAMETER_ID) + "\"";
		} else if (model instanceof Graph) {
			name = "graph";
		} else if (model instanceof Edge) {
			name = "edge";
		}
		
		setTitle("Parameters of " + name);
		super.setDescription("bla bla");
	}

	private void createListTable(Composite parent, Parameter parameter) {
		Table table = createTable(parent);
		tableViewer = new TableViewer(table);
	}

	private void createMapTable(Composite parent, Parameter parameter) {
		Table table = createTable(parent);
		tableViewer = new TableViewer(table);
	}

	/**
	 * Creates tables for each parameter of the model.
	 * 
	 * @param parent
	 *            The parent composite.
	 */
	private void createParameterTables(Composite parent) {
		List<Parameter> parameters = model.getParameters();
		for (Parameter parameter : parameters) {
			Class<?> clasz = parameter.getType();
			if (clasz == List.class) {
				createListTable(parent, parameter);
			} else if (clasz == Map.class) {
				createMapTable(parent, parameter);
			}
		}
	}

	/**
	 * Creates the table component from the <code>parent</code> composite.
	 * 
	 * @param parent
	 *            The parent composite.
	 * @return The table created.
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

	@Override
	protected void performDefaults() {
	}

	@Override
	public boolean performOk() {
		return true;
	}

}