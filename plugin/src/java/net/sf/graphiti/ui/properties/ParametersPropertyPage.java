package net.sf.graphiti.ui.properties;

import java.util.List;

import net.sf.graphiti.model.Parameter;
import net.sf.graphiti.model.Vertex;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.dialogs.PropertyPage;

public class ParametersPropertyPage extends PropertyPage {

	private IAdaptable model;

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

		model = getElement();
		createParameters(composite);

		return composite;
	}

	private void createParameters(Composite composite) {
		Vertex vertex = (Vertex) model.getAdapter(Vertex.class);
		if (vertex != null) {
			List<Parameter> parameters = vertex.getParameters();
		}
	}

	protected void performDefaults() {
	}

	public boolean performOk() {
		return true;
	}

}