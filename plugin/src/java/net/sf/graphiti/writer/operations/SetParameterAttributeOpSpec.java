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
package net.sf.graphiti.writer.operations;

import net.sf.graphiti.model.Edge;
import net.sf.graphiti.model.Graph;
import net.sf.graphiti.model.Vertex;
import net.sf.graphiti.ontology.parameters.Parameter;
import net.sf.graphiti.ontology.types.EdgeType;
import net.sf.graphiti.ontology.types.GraphType;
import net.sf.graphiti.ontology.types.Type;
import net.sf.graphiti.ontology.types.VertexType;
import net.sf.graphiti.transactions.IOperationSpecification;
import net.sf.graphiti.transactions.Result;

import org.w3c.dom.Element;

/**
 * Sets an attribute based on a given parameter. Operands: DOM element, context,
 * attribute name, parameter.
 * 
 * @author Matthieu Wipliez
 * 
 */
public class SetParameterAttributeOpSpec implements IOperationSpecification {

	@Override
	public void execute(Object[] operands, Result result) {
		Element element = (Element) operands[0];
		Object context = operands[1];
		String attrName = (String) operands[2];
		Parameter parameter = (Parameter) operands[3];

		Type objectType = parameter.appliesTo();
		String parameterName = parameter.hasName();
		Object value;

		if (objectType instanceof GraphType) {
			value = ((Graph) context).getValue(parameterName);
		} else if (objectType instanceof VertexType) {
			value = ((Vertex) context).getValue(parameterName);
		} else if (objectType instanceof EdgeType) {
			value = ((Edge) context).getValue(parameterName);
		} else {
			return;
		}

		if (value == null) {
			value = "";
		}
		
		element.setAttribute(attrName, value.toString());
	}

	@Override
	public String getName() {
		return "set parameter attribute";
	}

	@Override
	public int getNbOperands() {
		return 4;
	}

	@Override
	public boolean hasResult() {
		return false;
	}

}