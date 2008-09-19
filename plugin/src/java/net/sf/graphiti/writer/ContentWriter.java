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
package net.sf.graphiti.writer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.Map.Entry;

import net.sf.graphiti.model.Configuration;
import net.sf.graphiti.model.Edge;
import net.sf.graphiti.model.Graph;
import net.sf.graphiti.model.PropertyBean;
import net.sf.graphiti.model.Vertex;
import net.sf.graphiti.ontology.AttributeRestriction;
import net.sf.graphiti.ontology.EdgeAttribute;
import net.sf.graphiti.ontology.EdgeSourceConnection;
import net.sf.graphiti.ontology.Element;
import net.sf.graphiti.ontology.OtherAttribute;
import net.sf.graphiti.ontology.Parameter;
import net.sf.graphiti.ontology.TextContentElement;
import net.sf.graphiti.ontology.XMLAttribute;
import net.sf.graphiti.transactions.Operation;
import net.sf.graphiti.transactions.Result;
import net.sf.graphiti.transactions.SimpleTransaction;
import net.sf.graphiti.writer.operations.AddDocumentFragmentOpSpec;
import net.sf.graphiti.writer.operations.CreateDocumentElementOpSpec;
import net.sf.graphiti.writer.operations.CreateElementOpSpec;
import net.sf.graphiti.writer.operations.SetAttributeOpSpec;
import net.sf.graphiti.writer.operations.SetTextContentOpSpec;

import org.eclipse.core.runtime.Assert;
import org.w3c.dom.Document;

/**
 * This class provides methods to write elements and attributes.
 * 
 * @author Matthieu Wipliez
 * 
 */
public class ContentWriter {

	/**
	 * This class provides a checkpoint for this content writer.
	 * 
	 * @author Matthieu Wipliez
	 * 
	 */
	class Checkpoint {

		/**
		 * The stack depth.
		 */
		private int depth;

		/**
		 * The transaction index.
		 */
		private int transactionIndex;

		/**
		 * Creates a new Checkpoint that saves the current state of this content
		 * writer.
		 */
		public Checkpoint() {
			depth = resultStack.size();
			transactionIndex = transaction.size();
		}

	}

	private Result documentElement;

	private Object lastKey;

	private Map<Object, Object> parametersBasket;

	/**
	 * {@link Stack#peek()} returns the {@link Result} associated with the
	 * current element (between a call to <code>elementStart</code> and
	 * <code>elementEnd</code>.
	 */
	private Stack<Result> resultStack;

	private SimpleTransaction transaction;

	/**
	 * Creates a new {@link ContentWriter}.
	 */
	public ContentWriter() {
		parametersBasket = new HashMap<Object, Object>();
		resultStack = new Stack<Result>();
		transaction = new SimpleTransaction();
	}

	/**
	 * Adds the given document fragment children to the output tree.
	 * 
	 * @param domDocFragment
	 *            A documentFragment element containing elements to add to the
	 *            output tree.
	 */
	public void addDocumentFragment(org.w3c.dom.Element domDocFragment) {
		Operation addElement = new Operation(new AddDocumentFragmentOpSpec());
		addElement.setOperands(resultStack.peek(), domDocFragment);
		transaction.addOperation(addElement);
	}

	/**
	 * Commit the underlying transaction.
	 * 
	 * @return The DOM documentElement created.
	 */
	public Document commit() {
		transaction.commit();
		Object contents = documentElement.getContents();
		org.w3c.dom.Element element = (org.w3c.dom.Element) contents;
		return element.getOwnerDocument();
	}

	/**
	 * Parses the given DOM element when <b>after</b> its children and
	 * attributes have been parsed.
	 * 
	 * @param ontElement
	 * @param domElement
	 * @throws EmptyBasketException
	 */
	public void elementEnd(Element ontElement, Object context)
			throws EmptyBasketException {
		if (ontElement instanceof TextContentElement) {
			writeTextContentElement((TextContentElement) ontElement, context);
		}

		resultStack.pop();
	}

	/**
	 * Parses the given DOM element when <b>before</b> its children and
	 * attributes are parsed.
	 * 
	 * @param ontElement
	 * @param domElement
	 */
	public void elementStart(Element ontElement, Object context) {
		// create the operation to create an element
		String elementName = ontElement.hasName();
		Operation op;
		if (resultStack.isEmpty()) {
			op = new Operation(new CreateDocumentElementOpSpec());
			Configuration configuration = ((Graph) context).getConfiguration();
			String ns = configuration.getOntologyFactory().getNamespace();
			op.setOperands(ns, elementName);
			documentElement = op.getResult();
		} else {
			op = new Operation(new CreateElementOpSpec());
			op.setOperands(resultStack.peek(), elementName);
		}

		// add the operation to transaction, the result to the stack
		transaction.addOperation(op);
		resultStack.push(op.getResult());
	}

	/**
	 * Returns a new checkpoint.
	 * 
	 * @return
	 */
	public Checkpoint getCheckpoint() {
		return new Checkpoint();
	}

	@SuppressWarnings("unchecked")
	private String getParameterValue(Parameter parameter, Object context)
			throws EmptyBasketException {
		String parameterName = parameter.hasName();
		String key = context.toString() + parameterName;
		Object value = ((PropertyBean) context).getValue(parameterName);

		Class<?> clasz = parameter.hasValueType().getDataType();
		if (clasz == List.class) {
			if (value == null) {
				throw new EmptyBasketException();
			}

			List<?> list = (List<?>) parametersBasket.get(key);
			if (list == null) {
				list = new ArrayList<Object>((List<?>) value);
				parametersBasket.put(key, list);
			}

			if (list.isEmpty()) {
				throw new EmptyBasketException();
			}
			return list.remove(0).toString();
		} else if (clasz == Map.class) {
			if (value == null) {
				throw new EmptyBasketException();
			}

			Map<Object, Object> map = (Map<Object, Object>) parametersBasket
					.get(key);
			if (map == null) {
				map = new HashMap<Object, Object>((Map<?, ?>) value);
				parametersBasket.put(key, map);
			}

			if (map.isEmpty()) {
				throw new EmptyBasketException();
			}

			Iterator<Entry<Object, Object>> it = map.entrySet().iterator();
			Entry<Object, Object> entry = it.next();
			if (lastKey == null) {
				lastKey = entry.getKey().toString();
				return lastKey.toString();
			} else {
				String stringValue = map.remove(lastKey).toString();
				lastKey = null;
				return stringValue;
			}
		} else {
			if (value == null) {
				value = "";
			}
		}

		return value.toString();
	}

	/**
	 * Go back to the given checkpoint.
	 * 
	 * @param checkpoint
	 */
	public void loadCheckpoint(Checkpoint checkpoint) {
		Assert.isTrue(resultStack.size() >= checkpoint.depth);
		Assert.isTrue(transaction.size() >= checkpoint.transactionIndex);

		for (int i = resultStack.size() - 1; i >= checkpoint.depth; i--) {
			resultStack.remove(i);
		}

		for (int i = transaction.size() - 1; i >= checkpoint.transactionIndex; i--) {
			transaction.remove(i);
		}
	}

	/**
	 * Sets the given attribute in the given context.
	 * 
	 * @param ontAttribute
	 *            The {@link XMLAttribute}.
	 * @param context
	 *            The context.
	 * @throws EmptyBasketException
	 */
	public void setAttribute(XMLAttribute ontAttribute, Object context)
			throws EmptyBasketException {
		if (ontAttribute instanceof EdgeAttribute) {
			setEdgeAttribute((EdgeAttribute) ontAttribute, context);
		} else {
			setOtherAttribute((OtherAttribute) ontAttribute, context);
		}
	}

	/**
	 * Sets an attribute restriction in the given context.
	 * 
	 * @param attr
	 *            An attribute restriction (name="value").
	 * @param context
	 *            The context.
	 */
	public void setAttributeRestriction(AttributeRestriction attr,
			Object context) {
		Operation setAttribute = new Operation(new SetAttributeOpSpec());
		setAttribute.setOperands(resultStack.peek(), attr.hasName(), attr
				.hasValue());
		transaction.addOperation(setAttribute);
	}

	/**
	 * Sets the given edge attribute in the given context.
	 * 
	 * @param ontAttribute
	 *            An {@link EdgeAttribute} (source or destination).
	 * @param context
	 *            The context.
	 */
	private void setEdgeAttribute(EdgeAttribute ontAttribute, Object context) {
		// endpoint
		Vertex endpoint;
		if (ontAttribute instanceof EdgeSourceConnection) {
			endpoint = ((Edge) context).getSource();
		} else {
			endpoint = ((Edge) context).getTarget();
		}

		String ontAttrName = ontAttribute.hasName();
		Operation setAttribute = new Operation(new SetAttributeOpSpec());
		setAttribute.setOperands(resultStack.peek(), ontAttrName, endpoint
				.getValue(Vertex.PARAMETER_ID));
		transaction.addOperation(setAttribute);
	}

	/**
	 * Sets an attribute in the given context.
	 * 
	 * @param ontAttribute
	 *            An {@link OtherAttribute}.
	 * @param context
	 *            The context.
	 * @throws EmptyBasketException
	 */
	private void setOtherAttribute(OtherAttribute ontAttribute, Object context)
			throws EmptyBasketException {
		String ontAttrName = ontAttribute.hasName();
		Parameter parameter = ontAttribute.hasParameter();
		if (parameter != null) {
			Operation setAttribute = new Operation(new SetAttributeOpSpec());
			String attrValue = getParameterValue(parameter, context);
			setAttribute
					.setOperands(resultStack.peek(), ontAttrName, attrValue);
			transaction.addOperation(setAttribute);
		}
	}

	@Override
	public String toString() {
		return "Content Writer";
	}

	private void writeTextContentElement(TextContentElement ontElement,
			Object context) throws EmptyBasketException {
		Parameter parameter = ontElement.referencesParameter();
		if (parameter != null) {
			String value = getParameterValue(parameter, context);
			Operation setText = new Operation(new SetTextContentOpSpec());
			setText.setOperands(resultStack.peek(), value);
			transaction.addOperation(setText);
		}
	}
}
