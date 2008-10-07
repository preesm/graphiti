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

import net.sf.graphiti.model.PropertyBean;

import org.eclipse.gef.commands.Command;

/**
 * This class provides a command that removes a parameter to the currently
 * selected object(s).
 * 
 * @author Matthieu Wipliez
 * 
 */
public class RemoveParameterCommand extends Command {

	private List<Object> list;

	private Map<Object, Object> map;

	private int oldListIndex;

	private Object oldMapValue;

	private PropertyBean source;

	private Object value;

	/**
	 * Creates a new remove parameter command.
	 * 
	 * @param value
	 *            The value.
	 */
	public RemoveParameterCommand(PropertyBean source) {
		this.source = source;
	}

	@Override
	public void execute() {
		if (list == null) {
			oldMapValue = map.remove(value);
			source.firePropertyChange("", null, map);
		} else {
			oldListIndex = list.indexOf(value);
			list.remove(value);
			source.firePropertyChange("", null, list);
		}
	}

	@Override
	public String getLabel() {
		return "Remove parameter";
	}

	/**
	 * Sets the list to add a parameter to.
	 * 
	 * @param list
	 *            A {@link List}.
	 */
	public void setList(List<Object> list) {
		this.list = list;
	}

	/**
	 * Sets the map to add a parameter to.
	 * 
	 * @param map
	 *            A {@link Map}.
	 */
	public void setMap(Map<Object, Object> map) {
		this.map = map;
	}

	/**
	 * Sets the value to remove.
	 * 
	 * @param value
	 *            A {@link String}.
	 */
	public void setValue(Object value) {
		this.value = value;
	}

	@Override
	public void undo() {
		if (list == null) {
			map.put(value, oldMapValue);
			source.firePropertyChange("", null, map);
		} else {
			list.add(oldListIndex, value);
			source.firePropertyChange("", null, list);
		}
	}
}
