package net.sf.graphiti.ui.adapters;

import net.sf.graphiti.model.PropertyBean;
import net.sf.graphiti.ui.propertysource.PropertyBeanPropertySource;

import org.eclipse.core.runtime.IAdapterFactory;

public class GraphitiAdapterFactory implements IAdapterFactory {

	@SuppressWarnings("unchecked")
	@Override
	public Object getAdapter(Object adaptableObject, Class adapterType) {
		if (adaptableObject instanceof PropertyBean) {
			return new PropertyBeanPropertySource(
					(PropertyBean) adaptableObject);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Class[] getAdapterList() {
		Class[] adapters = new Class[1];
		adapters[1] = PropertyBeanPropertySource.class;
		return adapters;
	}

}
