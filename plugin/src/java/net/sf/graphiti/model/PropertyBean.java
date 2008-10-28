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
package net.sf.graphiti.model;

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
 * This class provides the ability to store properties. Classes may listen to
 * property change events by registering themselves using
 * {@link #addPropertyChangeListener(PropertyChangeListener)}.
 * 
 * @author Jonathan Piat
 * @author Matthieu Wipliez
 */
public class PropertyBean {

	/**
	 * String for the "child added" property. Set when a vertex or a port is
	 * added to a vertex.
	 */
	public static final String PROPERTY_ADD = "child added";

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

	/**
	 * Constructs a new property bean, with no initial properties set.
	 */
	public PropertyBean() {
		propertyChange = new PropertyChangeSupport(this);
		properties = new HashMap<String, Object>();
	}

	/**
	 * Constructs a new property bean from the given bean.
	 * 
	 * @param bean
	 *            The source bean.
	 */
	@SuppressWarnings("unchecked")
	protected PropertyBean(PropertyBean bean) {
		propertyChange = new PropertyChangeSupport(this);
		properties = new HashMap<String, Object>();
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
	 * Gives this PropertyBean properties keySet
	 * 
	 * @return a List of property Names
	 */
	public List<String> getProperties() {
		return new ArrayList<String>(properties.keySet());
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
	 * Sets the value of the property whose name is <code>propertyName</code> to
	 * value <code>newValue</code>, and report the property update to any
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
