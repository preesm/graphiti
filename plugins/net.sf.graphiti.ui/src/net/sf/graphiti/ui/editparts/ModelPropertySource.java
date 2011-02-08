/*
 * Copyright (c) 2008-2011, IETR/INSA of Rennes
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
package net.sf.graphiti.ui.editparts;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.sf.graphiti.model.AbstractObject;
import net.sf.graphiti.model.ObjectType;
import net.sf.graphiti.model.Parameter;

import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertySource;
import org.eclipse.ui.views.properties.TextPropertyDescriptor;

/**
 * This class implements a property source for the different objects of our
 * model.
 * 
 * @author Matthieu Wipliez
 * 
 */
public class ModelPropertySource implements IPropertySource {

	private ObjectType type;

	private IPropertyDescriptor[] descs;

	private AbstractObject model;

	public ModelPropertySource(AbstractObject model) {
		this.model = model;
		this.type = model.getType();

		List<IPropertyDescriptor> descs = new ArrayList<IPropertyDescriptor>();
		for (Parameter parameter : type.getParameters()) {
			if (!(parameter.getType() == List.class || parameter.getType() == Map.class)) {
				String name = parameter.getName();
				TextPropertyDescriptor desc = new TextPropertyDescriptor(name,
						name);
				descs.add(desc);
			}
		}

		this.descs = descs.toArray(new IPropertyDescriptor[0]);
	}

	@Override
	public Object getEditableValue() {
		return null;
	}

	@Override
	public IPropertyDescriptor[] getPropertyDescriptors() {
		return descs;
	}

	@Override
	public Object getPropertyValue(Object id) {
		Object value = model.getValue((String) id);
		if (value == null) {
			value = "";
		}
		return value;
	}

	@Override
	public boolean isPropertySet(Object id) {
		Object value = model.getValue((String) id);
		Object defaultValue = model.getParameter((String) id).getDefault();
		return value != defaultValue;
	}

	@Override
	public void resetPropertyValue(Object id) {
		Object defaultValue = model.getParameter((String) id).getDefault();
		if (defaultValue == null) {
			defaultValue = "";
		}
		model.setValue((String) id, defaultValue);
	}

	@Override
	public void setPropertyValue(Object id, Object value) {
		model.setValue((String) id, value);
	}

}