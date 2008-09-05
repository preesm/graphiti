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
import net.sf.graphiti.ontology.xmlDescriptions.xmlAttributes.XMLAttribute;
import net.sf.graphiti.ontology.xmlDescriptions.xmlAttributes.edgeAttributes.EdgeAttribute;
import net.sf.graphiti.ontology.xmlDescriptions.xmlAttributes.edgeAttributes.EdgeSourceConnection;
import net.sf.graphiti.ontology.xmlDescriptions.xmlAttributes.otherAttributes.OtherAttribute;
import net.sf.graphiti.ontology.xmlDescriptions.xmlSchemaTypes.elements.Element;
import net.sf.graphiti.transactions.Operation;
import net.sf.graphiti.transactions.Result;
import net.sf.graphiti.transactions.SimpleTransaction;
import net.sf.graphiti.writer.operations.CreateElementOpSpec;
import net.sf.graphiti.writer.operations.SetAttributeOpSpec;
import net.sf.graphiti.writer.operations.SetEdgeAttributeOpSpec;

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
	 * {@link Stack#peek()} returns the current {@link Element} (between a call
	 * to <code>elementStart</code> and <code>elementEnd</code>.
	 */
	private Stack<Element> elementStack;

	/**
	 * {@link Stack#peek()} returns the {@link Result} associated with the
	 * current element (between a call to <code>elementStart</code> and
	 * <code>elementEnd</code>.
	 */
	private Stack<Result> resultStack;

	private SimpleTransaction transaction;

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

		Operation createElement = new Operation(new CreateElementOpSpec());
		String elementName = ontElement.hasName();
		if (resultStack.isEmpty()) {
			createElement.setOperands(document, elementName);
		} else {
			createElement.setOperands(resultStack.peek(), elementName);
		}

		transaction.addOperation(createElement);
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

	@Override
	public String toString() {
		return "Content Writer";
	}

	/**
	 * Parses the given attribute.
	 * 
	 * @param ontAttribute
	 * @param ontAttrName
	 * @param domAttrValue
	 */
	public void writeAttribute(XMLAttribute ontAttribute, Object context) {
		if (ontAttribute instanceof EdgeAttribute) {
			writeEdgeAttribute((EdgeAttribute) ontAttribute, context);
		} else {
			writeOtherAttribute((OtherAttribute) ontAttribute, context);
		}
	}

	private void writeEdgeAttribute(EdgeAttribute ontAttribute, Object context) {
		// endpoint
		Vertex endpoint;
		if (ontAttribute instanceof EdgeSourceConnection) {
			endpoint = ((Edge) context).getSource();
		} else {
			endpoint = ((Edge) context).getTarget();
		}

		String ontAttrName = ontAttribute.hasName();
		Operation setAttribute = new Operation(new SetEdgeAttributeOpSpec());
		setAttribute.setOperands(resultStack.peek(), ontAttrName, endpoint
				.getValue(Vertex.PARAMETER_ID));
		transaction.addOperation(setAttribute);
	}

	private void writeOtherAttribute(OtherAttribute ontAttribute, Object context) {
		String ontAttrName = ontAttribute.hasName();
		Parameter parameter = ontAttribute.hasParameter();
		if (parameter != null) {
			Operation setAttribute = new Operation(new SetAttributeOpSpec());
			setAttribute.setOperands(resultStack.peek(), context, ontAttrName,
					parameter);
			transaction.addOperation(setAttribute);
		}
	}
}
