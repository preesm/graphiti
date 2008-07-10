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
package net.sf.graphiti.ui.propertysource;

import java.util.List;

import net.sf.graphiti.model.DocumentConfiguration;
import net.sf.graphiti.model.Parameter;
import net.sf.graphiti.model.PropertyBean;
import net.sf.graphiti.model.Vertex;

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ICellEditorValidator;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertySource;
import org.eclipse.ui.views.properties.TextPropertyDescriptor;

public class PropertyBeanPropertySource implements IPropertySource {

	private Vertex vertex;

	public PropertyBeanPropertySource(Vertex vertex) {
		this.vertex = vertex;
	}

	@Override
	public Object getEditableValue() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IPropertyDescriptor[] getPropertyDescriptors() {
		PropertyBean attributes = vertex.getAttributes();
		List<Parameter> parameters = vertex.getParameters();

		List<String> attributeNames = attributes.getProperties();
		int totalSize = attributeNames.size();
		totalSize += parameters.size();
		totalSize++;

		TextPropertyDescriptor[] properties = new TextPropertyDescriptor[totalSize];

		TextPropertyDescriptor propertyDescriptor = new TextPropertyDescriptor(
				new SpecialProperty(), "Add a new parameter...");
		propertyDescriptor.setCategory("Parameters");

		properties[0] = propertyDescriptor;

		int i = 1;
		for (String attributeName : attributeNames) {
			propertyDescriptor = new TextPropertyDescriptor(
					new AttributeProperty(attributeName), attributeName);
			propertyDescriptor.setCategory("Attributes");

			propertyDescriptor.setValidator(new ICellEditorValidator() {

				@Override
				public String isValid(Object value) {
					return "Attributes cannot be edited.";
				}

			});

			properties[i] = propertyDescriptor;
			i++;
		}

		for (Parameter parameter : parameters) {
			propertyDescriptor = new TextPropertyDescriptor(
					new ParameterProperty(parameter.getName()), parameter
							.getName());
			propertyDescriptor.setCategory("Parameters");

			// Composite composite = PlatformUI.getWorkbench()
			// .getActiveWorkbenchWindow().getShell();
			// CellEditor toto =
			// propertyDescriptor.createPropertyEditor(composite);
			// if (toto instanceof TextCellEditor) {
			// TextCellEditor toto2 = (TextCellEditor) toto;
			// toto2.setStyle(toto2.getStyle() | SWT.MULTI);
			// }
			
			properties[i] = propertyDescriptor;
			i++;
		}

		return properties;
	}

	@Override
	public Object getPropertyValue(Object id) {
		Object value;
		IPropertyType propertyType = (IPropertyType) id;
		if (propertyType.isAttribute()) {
			String attributeName = propertyType.getId();
			value = vertex.getAttribute(attributeName);
		} else if (propertyType.isParameter()) {
			String parameterName = propertyType.getId();
			value = vertex.getValue(parameterName);
		} else {
			value = "";
		}

		if (value == null) {
			return "";
		} else if (value instanceof String) {
			return value;
		} else {
			return value.toString();
		}
	}

	@Override
	public boolean isPropertySet(Object id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void resetPropertyValue(Object id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setPropertyValue(Object id, Object value) {
		IPropertyType propertyType = (IPropertyType) id;
		if (propertyType.isAttribute()) {
		} else if (propertyType.isParameter()) {
			String parameterName = propertyType.getId();
			vertex.setValue(parameterName, value);
		} else {
			String name = (String) value;
			DocumentConfiguration config = vertex.getParentDocument()
					.getDocumentConfiguration();
			config.addVertexParameter(vertex.getType(), new Parameter(name));
		}
	}

}
