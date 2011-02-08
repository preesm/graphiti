/*
 * Copyright (c) 2011, IETR/INSA of Rennes
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
package net.sf.graphiti.ui.properties;

import net.sf.graphiti.model.AbstractObject;
import net.sf.graphiti.model.ObjectType;

import org.eclipse.gef.EditPart;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.forms.widgets.Form;
import org.eclipse.ui.views.properties.tabbed.AbstractPropertySection;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

/**
 * This class defines a map section.
 * 
 * @author Matthieu Wipliez
 * 
 */
public class MapSection extends AbstractPropertySection {

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
		
		Form form = getWidgetFactory().createForm(parent);
		getWidgetFactory().decorateFormHeading(form);
		form.setText("Sample form");
		form.getBody().setLayout(new GridLayout());
		getWidgetFactory().createButton(form.getBody(), "Checkbox", SWT.CHECK);
		list = getWidgetFactory().createList(form.getBody(), SWT.SINGLE);
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
