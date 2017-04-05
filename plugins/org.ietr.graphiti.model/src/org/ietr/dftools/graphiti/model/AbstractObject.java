/*******************************************************************************
 * Copyright or © or Copr. IETR/INSA - Rennes (%%DATE%%) :
 *
 * %%AUTHORS%%
 *
 * This software is a computer program whose purpose is to [describe
 * functionalities and technical features of your software].
 *
 * This software is governed by the CeCILL  license under French law and
 * abiding by the rules of distribution of free software.  You can  use, 
 * modify and/ or redistribute the software under the terms of the CeCILL
 * license as circulated by CEA, CNRS and INRIA at the following URL
 * "http://www.cecill.info". 
 *
 * As a counterpart to the access to the source code and  rights to copy,
 * modify and redistribute granted by the license, users are provided only
 * with a limited warranty  and the software's author,  the holder of the
 * economic rights,  and the successive licensors  have only  limited
 * liability. 
 *
 * In this respect, the user's attention is drawn to the risks associated
 * with loading,  using,  modifying and/or developing or reproducing the
 * software by the user in light of its specific status of free software,
 * that may mean  that it is complicated to manipulate,  and  that  also
 * therefore means  that it is reserved for developers  and  experienced
 * professionals having in-depth computer knowledge. Users are therefore
 * encouraged to load and test the software's suitability as regards their
 * requirements in conditions enabling the security of their systems and/or 
 * data to be ensured and,  more generally, to use and operate it in the 
 * same conditions as regards security. 
 *
 * The fact that you are presently reading this means that you have had
 * knowledge of the CeCILL license and that you accept its terms.
 *******************************************************************************/
package org.ietr.dftools.graphiti.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.Map.Entry;

/**
 * This class is the base class for any object in the model. It has the ability
 * to store properties. Classes may listen to property change events by
 * registering themselves using
 * {@link #addPropertyChangeListener(PropertyChangeListener)}.
 * 
 * @author Jonathan Piat
 * @author Matthieu Wipliez
 */
public abstract class AbstractObject {

	static final long serialVersionUID = 1;

	/**
	 * The hash map that stores property values.
	 */
	private Map<String, Object> properties;

	/**
	 * The utility class that makes us able to support bound properties.
	 */
	private PropertyChangeSupport propertyChange;

	/**
	 * This object's type.
	 */
	protected ObjectType type;

	/**
	 * Constructs a new property bean from the given bean.
	 * 
	 * @param bean
	 *            The source bean.
	 */
	@SuppressWarnings("unchecked")
	protected AbstractObject(AbstractObject bean) {
		propertyChange = new PropertyChangeSupport(this);
		properties = new HashMap<String, Object>();
		type = bean.type;

		Set<Entry<String, Object>> entries = bean.properties.entrySet();
		for (Entry<String, Object> entry : entries) {
			Object value = entry.getValue();
			if (value instanceof String) {
				value = new String((String) value);
			} else if (value instanceof Integer) {
				value = new Integer((Integer) value);
			} else if (value instanceof Float) {
				value = new Float((Float) value);
			} else if (value instanceof List<?>) {
				value = new ArrayList<Object>((List<?>) value);
			} else if (value instanceof Map<?, ?>) {
				value = new TreeMap<String, Object>((Map<String, Object>) value);
			}

			properties.put(entry.getKey(), value);
		}
	}

	/**
	 * Constructs a new property bean, with no initial properties set.
	 */
	public AbstractObject(ObjectType type) {
		propertyChange = new PropertyChangeSupport(this);
		properties = new HashMap<String, Object>();
		this.type = type;
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

	/**
	 * This methods calls
	 * {@link PropertyChangeSupport#firePropertyChange(String, Object, Object)}
	 * on the underlying {@link PropertyChangeSupport} without updating the
	 * value of the property <code>propertyName</code>. This method is
	 * particularly useful when a property should be fired regardless of the
	 * previous value (in case of undo/redo for example, when a same object is
	 * added, removed, and added again).
	 * 
	 * @param propertyName
	 *            The name of the property concerned.
	 * @param oldValue
	 *            The old value of the property.
	 * @param newValue
	 *            The new value of the property.
	 */
	public void firePropertyChange(String propertyName, Object oldValue,
			Object newValue) {
		propertyChange.firePropertyChange(propertyName, oldValue, newValue);
	}

	/**
	 * Returns the value of an attribute associated with this object's type and
	 * the given attribute name <code>attributeName</code>.
	 * 
	 * @param attributeName
	 *            The name of an attribute.
	 * @return The value of the attribute as an object.
	 */
	public Object getAttribute(String attributeName) {
		return type.getAttribute(attributeName);
	}

	/**
	 * Returns the parameter in this vertex type with the given name.
	 * 
	 * @param parameterName
	 *            The parameter name.
	 * @return A {@link Parameter}.
	 */
	public Parameter getParameter(String parameterName) {
		return type.getParameter(parameterName);
	}

	/**
	 * Returns a list of parameters associated with this vertex type.
	 * 
	 * @return A List of Parameters.
	 */
	public List<Parameter> getParameters() {
		return type.getParameters();
	}

	/**
	 * Returns this object's type.
	 * 
	 * @return This object's type.
	 */
	public ObjectType getType() {
		return type;
	}

	/**
	 * Returns the value of the property whose name is <code>propertyName</code>
	 * .
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
	 * Sets this object's type. This method should be called with caution, as a
	 * lot of things in the editor depend on this...
	 * 
	 * @param type
	 *            The new type.
	 */
	public void setType(ObjectType type) {
		this.type = type;
	}

	/**
	 * Sets the value of the property whose name is <code>propertyName</code> to
	 * value <code>newValue</code>, and report the property update to any
	 * registered listeners.
	 * 
	 * @param propertyName
	 *            The name of the property to set.
	 * @param newValue
	 *            The new value of the property.
	 * @return The previous value of the property.
	 */
	public Object setValue(String propertyName, Object newValue) {
		Object oldValue = properties.put(propertyName, newValue);
		propertyChange.firePropertyChange(propertyName, oldValue, newValue);
		return oldValue;
	}

	/**
	 * Sets the value of the property whose name is <code>propertyName</code> to
	 * value <code>newValue</code>, <b>without</b> reporting the property update
	 * to any registered listeners.
	 * 
	 * @param propertyName
	 *            The name of the property to set.
	 * @param newValue
	 *            The new value of the property.
	 */
	public void setValueWithoutEvent(String propertyName, Object newValue) {
		properties.put(propertyName, newValue);
	}
}
