package net.sf.graphiti.ui.propertysource;

import java.util.List;

import net.sf.graphiti.model.PropertyBean;

import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertySource;
import org.eclipse.ui.views.properties.TextPropertyDescriptor;

public class PropertyBeanPropertySource implements IPropertySource {

	private PropertyBean model;

	public PropertyBeanPropertySource(PropertyBean bean) {
		model = bean;
	}

	@Override
	public Object getEditableValue() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IPropertyDescriptor[] getPropertyDescriptors() {
		List<String> propertyNames = model.getProperties();
		IPropertyDescriptor[] properties = new IPropertyDescriptor[propertyNames
				.size()];
		int i = 0;
		for (String name : propertyNames) {
			TextPropertyDescriptor propertyDescriptor = new TextPropertyDescriptor(
					name, name);
			properties[i] = propertyDescriptor;
			i++;
		}
		return properties;
	}

	@Override
	public Object getPropertyValue(Object id) {
		Object value = model.getValue((String) id);
		if (value == null || value instanceof String) {
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
		// TODO Auto-generated method stub

	}

}
