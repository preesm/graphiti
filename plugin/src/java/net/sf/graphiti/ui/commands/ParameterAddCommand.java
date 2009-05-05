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

import net.sf.graphiti.model.AbstractObject;

import org.eclipse.gef.commands.Command;

/**
 * This class provides a command that adds a parameter to the currently selected
 * object(s).
 * 
 * @author Matthieu Wipliez
 * 
 */
public class ParameterAddCommand extends Command {

	private List<Object> list;

	private Map<Object, Object> map;

	private AbstractObject source;

	private String value;

	/**
	 * Creates a new add parameter command.
	 * 
	 * @param value
	 *            The value.
	 */
	public ParameterAddCommand(AbstractObject source, String value) {
		this.source = source;
		this.value = value;
	}

	@Override
	public void execute() {
		if (list == null) {
			map.put(value, "");
			source.firePropertyChange("", null, map);
		} else {
			list.add(value);
			source.firePropertyChange("", null, list);
		}
	}

	@Override
	public String getLabel() {
		return "Add parameter";
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

	@Override
	public void undo() {
		if (list == null) {
			map.remove(value);
			source.firePropertyChange("", null, map);
		} else {
			list.remove(value);
			source.firePropertyChange("", null, list);
		}
	}
}
