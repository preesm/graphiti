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

import java.util.Stack;

import net.sf.graphiti.model.Configuration;
import net.sf.graphiti.model.Edge;
import net.sf.graphiti.model.Vertex;
import net.sf.graphiti.ontology.parameters.Parameter;
import net.sf.graphiti.ontology.types.EdgeType;
import net.sf.graphiti.ontology.types.GraphType;
import net.sf.graphiti.ontology.types.Type;
import net.sf.graphiti.ontology.types.VertexType;
import net.sf.graphiti.ontology.xmlDescriptions.attributeRestrictions.AttributeRestriction;
import net.sf.graphiti.ontology.xmlDescriptions.xmlAttributes.XMLAttribute;
import net.sf.graphiti.ontology.xmlDescriptions.xmlAttributes.edgeAttributes.EdgeAttribute;
import net.sf.graphiti.ontology.xmlDescriptions.xmlAttributes.edgeAttributes.EdgeSourceConnection;
import net.sf.graphiti.ontology.xmlDescriptions.xmlAttributes.otherAttributes.OtherAttribute;
import net.sf.graphiti.ontology.xmlDescriptions.xmlSchemaTypes.elements.EdgeElement;
import net.sf.graphiti.ontology.xmlDescriptions.xmlSchemaTypes.elements.Element;
import net.sf.graphiti.ontology.xmlDescriptions.xmlSchemaTypes.elements.GraphElement;
import net.sf.graphiti.ontology.xmlDescriptions.xmlSchemaTypes.elements.TextContentElement;
import net.sf.graphiti.ontology.xmlDescriptions.xmlSchemaTypes.elements.VertexElement;
import net.sf.graphiti.transactions.Operation;
import net.sf.graphiti.transactions.Result;
import net.sf.graphiti.transactions.SimpleTransaction;
import net.sf.graphiti.writer.operations.CreateElementOpSpec;
import net.sf.graphiti.writer.operations.SetAttributeOpSpec;
import net.sf.graphiti.writer.operations.SetParameterAttributeOpSpec;
import net.sf.graphiti.writer.operations.SetTextContentOpSpec;

import org.eclipse.core.runtime.Assert;
import org.w3c.dom.Document;

/**
 * This class provides methods to parse elements and attributes.
 * 
 * @author Matthieu Wipliez
 * 
 */
public class ContentWriter {

	/**
	 * This class provides a checkpoint for this content parser.
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
		 * parser.
		 */
		public Checkpoint() {
			depth = elementStack.size();
			transactionIndex = transaction.size();
		}

	}

	/**
	 * The configuration this content parser was created with.
	 */
	private Configuration configuration;

	private Document document;

	/**
	 * Index of the last edge element created.
	 */
	private int edgeIndex;

	/**
	 * {@link Stack#peek()} returns the current {@link Element} (between a call
	 * to <code>elementStart</code> and <code>elementEnd</code>.
	 */
	private Stack<Element> elementStack;

	/**
	 * Index of the last graph element created.
	 */
	private int graphIndex;

	/**
	 * {@link Stack#peek()} returns the {@link Result} associated with the
	 * current element (between a call to <code>elementStart</code> and
	 * <code>elementEnd</code>.
	 */
	private Stack<Result> resultStack;

	private SimpleTransaction transaction;

	/**
	 * Index of the last vertex element created.
	 */
	private int vertexIndex;

	/**
	 * Creates a new {@link ContentWriter} with the given {@link Configuration}.
	 * 
	 * @param configuration
	 */
	public ContentWriter(Configuration configuration, Document document) {
		this.configuration = configuration;
		this.document = document;
		elementStack = new Stack<Element>();
		resultStack = new Stack<Result>();
		transaction = new SimpleTransaction();
	}

	/**
	 * Commit the underlying transaction.
	 */
	public void commit() {
		transaction.commit();
	}

	/**
	 * Parses the given DOM element when <b>after</b> its children and
	 * attributes have been parsed.
	 * 
	 * @param ontElement
	 * @param domElement
	 */
	public void elementEnd(Element ontElement, Object context) {
		if (ontElement instanceof GraphElement) {
			graphIndex = -1;
		} else if (ontElement instanceof VertexElement) {
			vertexIndex = -1;
		} else if (ontElement instanceof EdgeElement) {
			edgeIndex = -1;
		} else if (ontElement instanceof TextContentElement) {
			writeTextContentElement((TextContentElement) ontElement, context);
		}

		elementStack.pop();
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
		elementStack.push(ontElement);

		// update index
		if (ontElement instanceof GraphElement) {
			graphIndex = resultStack.size();
		} else if (ontElement instanceof VertexElement) {
			vertexIndex = resultStack.size();
		} else if (ontElement instanceof EdgeElement) {
			edgeIndex = resultStack.size();
		}

		// create the operation to create an element
		Operation createElement = new Operation(new CreateElementOpSpec());
		String elementName = ontElement.hasName();
		if (resultStack.isEmpty()) {
			createElement.setOperands(document, elementName);
		} else {
			createElement.setOperands(resultStack.peek(), elementName);
		}

		// add to transaction
		transaction.addOperation(createElement);

		// add the result to the stack
		Result result = createElement.getResult();
		resultStack.push(result);
	}

	/**
	 * Returns a new checkpoint.
	 * 
	 * @return
	 */
	public Checkpoint getCheckpoint() {
		return new Checkpoint();
	}

	/**
	 * Returns the object (from the resultStack) that this parameter applies to.
	 * 
	 * @param parameter
	 *            The parameter.
	 * @return A {@link Result} from the stack.
	 */
	private Result getParameterObject(Parameter parameter) {
		Type objectType = parameter.appliesTo();
		int index = -1;

		if (objectType instanceof EdgeType) {
			index = edgeIndex;
		} else if (objectType instanceof GraphType) {
			index = graphIndex;
		} else if (objectType instanceof VertexType) {
			index = vertexIndex;
		}

		if (index == -1) {
			return resultStack.peek();
		} else {
			return resultStack.get(index);
		}
	}

	/**
	 * Go back to the given checkpoint.
	 * 
	 * @param checkpoint
	 */
	public void loadCheckpoint(Checkpoint checkpoint) {
		Assert.isTrue(elementStack.size() >= checkpoint.depth);
		Assert.isTrue(transaction.size() >= checkpoint.transactionIndex);

		for (int i = elementStack.size() - 1; i >= checkpoint.depth; i--) {
			elementStack.remove(i);
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
	 */
	public void setAttribute(XMLAttribute ontAttribute, Object context) {
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
	 */
	private void setOtherAttribute(OtherAttribute ontAttribute, Object context) {
		String ontAttrName = ontAttribute.hasName();
		Parameter parameter = ontAttribute.hasParameter();
		if (parameter != null) {
			Operation setAttribute = new Operation(
					new SetParameterAttributeOpSpec());
			Result obj = getParameterObject(parameter);
			setAttribute.setOperands(obj, context, ontAttrName, parameter);
			transaction.addOperation(setAttribute);
		}
	}

	@Override
	public String toString() {
		return "Content Writer";
	}

	private void writeTextContentElement(TextContentElement ontElement,
			Object context) {
		Parameter parameter = ontElement.referencesParameter();
		if (parameter != null) {
			Operation setText = new Operation(new SetTextContentOpSpec());
			setText.setOperands(resultStack.peek(), context, parameter);
			transaction.addOperation(setText);
		}
	}
}
