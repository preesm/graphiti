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
package net.sf.graphiti.parsers.operations;

import java.util.List;
import java.util.Map;

import net.sf.graphiti.model.Parameter;
import net.sf.graphiti.model.PropertyBean;
import net.sf.graphiti.model.Util;
import net.sf.graphiti.transactions.IOperationSpecification;
import net.sf.graphiti.transactions.Result;

/**
 * This class provides a 3-ary operation that sets a graph/vertex/edge property
 * value. Operands: object (PropertyBean), parameter and value.
 * 
 * @author Matthieu Wipliez
 * 
 */
public class SetValueOpSpec implements IOperationSpecification {

	private static SetValueOpSpec instance;

	/**
	 * Returns the single instance of this {@link SetValueOpSpec}.
	 * 
	 * @return The single instance of this {@link SetValueOpSpec}.
	 */
	public static SetValueOpSpec getInstance() {
		if (instance == null) {
			instance = new SetValueOpSpec();
		}

		return instance;
	}

	private Object lastKey;

	/**
	 * Creates a new set value operation specification.
	 */
	private SetValueOpSpec() {

	}

	@Override
	public void execute(Object[] operands, Result result) {
		PropertyBean bean = (PropertyBean) operands[0];
		if (bean != null) {
			Parameter parameter = (Parameter) operands[1];
			Object value = operands[2];

			Class<?> clasz = parameter.getType();
			if (clasz == List.class) {
				List<Object> list = Util.getList(bean, parameter);
				list.add(value);
			} else if (clasz == Map.class) {
				if (lastKey == null) {
					lastKey = value;
				} else {
					Map<Object, Object> map = Util.getMap(bean, parameter);
					map.put(lastKey, value);
					lastKey = null;
				}
			} else {
				if (clasz == Float.class) {
					try {
						value = new Float((String) value);
					} catch (NumberFormatException e) {
						value = new Float(0.0);
					}
				} else if (clasz == Integer.class) {
					try {
						value = new Integer((String) value);
					} catch (NumberFormatException e) {
						value = new Integer(0);
					}
				} else if (clasz == String.class) {
					// just to enforce the String constraint
					value = (String) value;
				}

				bean.setValue(parameter.getName(), value);
			}
		}
	}

	@Override
	public String getName() {
		return "set value";
	}

	@Override
	public int getNbOperands() {
		return 3;
	}

	@Override
	public boolean hasResult() {
		return false;
	}

}
