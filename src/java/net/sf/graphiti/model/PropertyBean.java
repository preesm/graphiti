package net.sf.graphiti.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;

import net.sf.graphiti.ui.propertysource.PropertyBeanPropertySource;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.ui.views.properties.IPropertySource;

/**
 * This class provides the ability to store properties. Classes may listen to
 * property change events by registering themselves using
 * {@link #addPropertyChangeListener(PropertyChangeListener)}.
 * 
 * @author Jonathan Piat
 * @author Matthieu Wipliez
 */
public class PropertyBean extends Observable implements Cloneable,
		Serializable, IAdaptable {
	/**
	 * String for the "child added" property. Set when a vertex or a port is
	 * added to a vertex.
	 */
	public static final String PROPERTY_ADD = "child added";

	/**
	 * String for the "name" property. Set when the name of a vertex or a port
	 * changes. This includes when it is first set.
	 */
	public static final String PROPERTY_NAME = "name";

	/**
	 * String for the "child removed" property. Set when a vertex or a port is
	 * removed from a vertex.
	 */
	public static final String PROPERTY_REMOVE = "child removed";

	static final long serialVersionUID = 1;

	/**
	 * The hash map that stores property values.
	 */
	private Map<String, Object> properties;

	/**
	 * The utility class that makes us able to support bound properties.
	 */
	private PropertyChangeSupport propertyChange;

	private IPropertySource propertySource;

	/**
	 * Constructs a new property bean, with no initial properties set.
	 */
	public PropertyBean() {
		propertyChange = new PropertyChangeSupport(this);
		properties = new HashMap<String, Object>();
	}

	/**
	 * Add the listener <code>listener</code> to the registered listeners.
	 * 
	 * @param listener
	 *            The PropertyChangeListener to add.
	 */
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		propertyChange.addPropertyChangeListener(listener);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Object getAdapter(Class adapter) {
		if (adapter == IPropertySource.class) {
			if (propertySource == null)
				propertySource = new PropertyBeanPropertySource(this);
			return propertySource;
		}
		return null;
	}

	/**
	 * Gives this PropertyBean properties keySet
	 * 
	 * @return a List of property Names
	 */
	public List<String> getProperties() {
		return new ArrayList<String>(properties.keySet());
	}

	/**
	 * Returns the value of the property whose name is <code>propertyName</code>.
	 * 
	 * @param propertyName
	 *            The name of the property to retrieve.
	 * @return The value of the property.
	 */
	public Object getValue(String propertyName) {
		return properties.get(propertyName);
	}

	/**
	 * Remove the listener listener from the registered listeners.
	 * 
	 * @param listener
	 *            The listener to remove.
	 */
	public void removePropertyChangeListener(PropertyChangeListener listener) {
		propertyChange.removePropertyChangeListener(listener);
	}

	/**
	 * Sets the value of the property whose name is <code>propertyName</code>
	 * to value <code>newValue</code>, and report the property update to any
	 * registered listeners.
	 * 
	 * @param propertyName
	 *            The name of the property to set.
	 * @param newValue
	 *            The new value of the property.
	 */
	public void setValue(String propertyName, Object newValue) {
		Object oldValue = properties.get(propertyName);
		properties.put(propertyName, newValue);
		propertyChange.firePropertyChange(propertyName, oldValue, newValue);
	}

	/**
	 * Sets the value of the property whose name is <code>propertyName</code>
	 * to value <code>newValue</code>, and report the property update to any
	 * registered listeners. This method allows the caller to specify the the
	 * property's <code>oldValue</code>, thus overriding the value stored in
	 * the properties map. This may be of use when a property should be fired
	 * regardless of the previous value (in case of undo/redo for example, when
	 * a same object is added, removed, and added again).
	 * 
	 * @param propertyName
	 *            The name of the property to set.
	 * @param oldValue
	 *            The old value of the property.
	 * @param newValue
	 *            The new value of the property.
	 */
	public void setValue(String propertyName, Object oldValue, Object newValue) {
		properties.put(propertyName, newValue);
		propertyChange.firePropertyChange(propertyName, oldValue, newValue);
	}
}
