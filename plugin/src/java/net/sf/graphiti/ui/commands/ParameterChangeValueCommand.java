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
package net.sf.graphiti.ui.commands;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import net.sf.graphiti.model.PropertyBean;

import org.eclipse.gef.commands.Command;

/**
 * This class provides a command that changes the value of the currently
 * selected parameter.
 * 
 * @author Matthieu Wipliez
 * 
 */
public class ParameterChangeValueCommand extends Command {

	/**
	 * Set by {@link #setEntry(Entry, Object)}.
	 */
	private Entry<Object, Object> entry;

	/**
	 * Set by {@link #setList(List, int, Object)}.
	 */
	private int index;

	/**
	 * Set by {@link #setList(List, int, Object)}.
	 */
	private List<Object> list;

	/**
	 * Set by {@link #setMap(Map, Object, Object)}.
	 */
	private Map<Object, Object> map;

	/**
	 * Set by {@link #setValue(String, Object)}.
	 */
	private String name;

	/**
	 * Set by {@link #setMap(Map, Object, Object)}.
	 */
	private Object newKey;

	/**
	 * The new value.
	 */
	private Object newValue;

	/**
	 * Set by {@link #setMap(Map, Object, Object)}.
	 */
	private Object oldKey;

	/**
	 * The old value.
	 */
	private Object oldValue;

	/**
	 * The property bean we're modifying.
	 */
	private PropertyBean source;

	/**
	 * Creates a new add parameter command.
	 * 
	 * @param newValue
	 *            The value.
	 */
	public ParameterChangeValueCommand(PropertyBean source) {
		this.source = source;
	}

	@Override
	public void execute() {
		if (entry != null) {
			oldValue = entry.setValue(newValue);
			source.firePropertyChange("", null, map);
		} else if (list != null) {
			oldValue = list.set(index, newValue);
			source.firePropertyChange("", null, list);
		} else if (map != null) {
			Object value = map.remove(oldKey);
			map.put(newKey, value);
			source.firePropertyChange("", null, map);
		} else {
			// simple parameter
			oldValue = source.setValue(name, newValue);
		}
	}

	@Override
	public String getLabel() {
		return "Change parameter value";
	}

	/**
	 * Updates the "value" field of the given entry to the given value.
	 * 
	 * @param entry
	 *            An entry.
	 * @param value
	 *            A new value for the entry's value field.
	 */
	public void setEntry(Entry<Object, Object> entry, Object value) {
		this.entry = entry;
		this.newValue = value;
	}

	/**
	 * Updates the value of the element at the given index in the given list.
	 * 
	 * @param list
	 *            A list of objects.
	 * @param index
	 *            The index of the element to be updated.
	 * @param value
	 *            The new value.
	 */
	public void setList(List<Object> list, int index, Object value) {
		this.list = list;
		this.index = index;
		this.newValue = value;
	}

	/**
	 * Updates a key name in the given map.
	 * 
	 * @param map
	 *            A map from objects to objects.
	 * @param oldKey
	 *            The previous key.
	 * @param newKey
	 *            The new key.
	 */
	public void setMap(Map<Object, Object> map, Object oldKey, Object newKey) {
		this.map = map;
		this.oldKey = oldKey;
		this.newKey = newKey;
	}

	/**
	 * Sets the value of the parameter whose name is given to the given value.
	 * 
	 * @param name
	 *            The parameter name.
	 * @param value
	 *            Its new value.
	 */
	public void setValue(String name, Object value) {
		this.name = name;
		this.newValue = value;
	}

	@Override
	public void undo() {
		if (entry != null) {
			entry.setValue(oldValue);
			source.firePropertyChange("", null, map);
		} else if (list != null) {
			list.set(index, oldValue);
			source.firePropertyChange("", null, list);
		} else if (map != null) {
			Object value = map.remove(newKey);
			map.put(oldKey, value);
			source.firePropertyChange("", null, map);
		} else {
			// simple parameter
			source.setValue(name, oldValue);
		}
	}
}
