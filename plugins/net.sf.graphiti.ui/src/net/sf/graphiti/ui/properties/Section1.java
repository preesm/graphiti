package net.sf.graphiti.ui.properties;

import net.sf.graphiti.model.AbstractObject;
import net.sf.graphiti.model.ObjectType;

import org.eclipse.gef.EditPart;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.views.properties.tabbed.AbstractPropertySection;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

public class Section1 extends AbstractPropertySection {

	private Text labelText;

	private List list;

	public void createControls(Composite parent,
			TabbedPropertySheetPage aTabbedPropertySheetPage) {
		super.createControls(parent, aTabbedPropertySheetPage);
		/*
		 * Composite composite = getWidgetFactory()
		 * .createFlatFormComposite(parent); FormData data;
		 * 
		 * labelText = getWidgetFactory().createText(composite, "");
		 * //$NON-NLS-1$ data = new FormData(); data.left = new
		 * FormAttachment(0, STANDARD_LABEL_WIDTH); data.right = new
		 * FormAttachment(100, 0); data.top = new FormAttachment(0,
		 * ITabbedPropertyConstants.VSPACE); labelText.setLayoutData(data);
		 * 
		 * CLabel labelLabel = getWidgetFactory() .createCLabel(composite,
		 * "Label:"); //$NON-NLS-1$ data = new FormData(); data.left = new
		 * FormAttachment(0, 0); data.right = new FormAttachment(labelText,
		 * -ITabbedPropertyConstants.HSPACE); data.top = new
		 * FormAttachment(labelText, 0, SWT.CENTER);
		 * labelLabel.setLayoutData(data);
		 */
		
		parent = getWidgetFactory().createFlatFormComposite(parent);

		//ScrolledForm form = getWidgetFactory().createScrolledForm(parent);
		//form.setText("Sample form");
		//form.getBody().setLayout(new GridLayout());
		getWidgetFactory().createButton(parent, "Checkbox", SWT.CHECK);
		list = getWidgetFactory().createList(parent, SWT.SINGLE);
	}

	public void setInput(IWorkbenchPart part, ISelection selection) {
		super.setInput(part, selection);
		if (selection instanceof IStructuredSelection) {
			Object object = ((IStructuredSelection) selection)
					.getFirstElement();
			if (object instanceof EditPart) {
				Object model = ((EditPart) object).getModel();
				if (model instanceof AbstractObject) {
					list.add((String) ((AbstractObject) model)
							.getValue(ObjectType.PARAMETER_ID));
				}
			}
		}
	}

	public void refresh() {
		String.valueOf(labelText);
	}

}
