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

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Utility class.
 * 
 * @author Matthieu Wipliez
 * 
 */
public class Util {

	public static Collection<?> getCollection(AbstractObject bean,
			Parameter parameter) {
		if (parameter.getType() == List.class) {
			return getList(bean, parameter);
		} else {
			Map<Object, Object> map = getMap(bean, parameter);
			return map.entrySet();
		}
	}

	@SuppressWarnings("unchecked")
	public static List<Object> getList(AbstractObject bean, Parameter parameter) {
		String parameterName = parameter.getName();
		List<Object> list = (List<Object>) bean.getValue(parameterName);
		if (list == null) {
			list = new ArrayList<Object>();
			bean.setValueWithoutEvent(parameterName, list);
		}

		return list;
	}

	@SuppressWarnings("unchecked")
	public static Map<Object, Object> getMap(AbstractObject bean,
			Parameter parameter) {
		String parameterName = parameter.getName();
		Map<Object, Object> map = (Map<Object, Object>) bean
				.getValue(parameterName);
		if (map == null) {
			map = new HashMap<Object, Object>();
			bean.setValueWithoutEvent(parameterName, map);
		}

		return map;
	}

}
