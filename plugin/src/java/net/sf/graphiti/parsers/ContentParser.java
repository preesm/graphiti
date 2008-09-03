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
package net.sf.graphiti.parsers;

import java.util.Set;
import java.util.Stack;

import net.sf.graphiti.model.Configuration;
import net.sf.graphiti.model.Graph;
import net.sf.graphiti.model.PropertyBean;
import net.sf.graphiti.ontology.OntologyFactory;
import net.sf.graphiti.ontology.parameterValues.ParameterValue;
import net.sf.graphiti.ontology.parameters.Parameter;
import net.sf.graphiti.ontology.types.EdgeType;
import net.sf.graphiti.ontology.types.GraphType;
import net.sf.graphiti.ontology.types.Type;
import net.sf.graphiti.ontology.types.VertexType;
import net.sf.graphiti.ontology.xmlDescriptions.xmlAttributes.XMLAttribute;
import net.sf.graphiti.ontology.xmlDescriptions.xmlAttributes.edgeAttributes.EdgeAttribute;
import net.sf.graphiti.ontology.xmlDescriptions.xmlAttributes.edgeAttributes.EdgeSourceConnection;
import net.sf.graphiti.ontology.xmlDescriptions.xmlAttributes.otherAttributes.OtherAttribute;
import net.sf.graphiti.ontology.xmlDescriptions.xmlSchemaTypes.elements.EdgeElement;
import net.sf.graphiti.ontology.xmlDescriptions.xmlSchemaTypes.elements.Element;
import net.sf.graphiti.ontology.xmlDescriptions.xmlSchemaTypes.elements.GraphElement;
import net.sf.graphiti.ontology.xmlDescriptions.xmlSchemaTypes.elements.TextContentElement;
import net.sf.graphiti.ontology.xmlDescriptions.xmlSchemaTypes.elements.VertexElement;
import net.sf.graphiti.parsers.operations.AddEdgeOpSpec;
import net.sf.graphiti.parsers.operations.AddVertexOpSpec;
import net.sf.graphiti.parsers.operations.CreateEdgeOpSpec;
import net.sf.graphiti.parsers.operations.CreateGraphOpSpec;
import net.sf.graphiti.parsers.operations.CreateVertexOpSpec;
import net.sf.graphiti.parsers.operations.SetEdgeEndpointOpSpec;
import net.sf.graphiti.parsers.operations.SetValueOpSpec;
import net.sf.graphiti.transactions.Operation;
import net.sf.graphiti.transactions.Result;
import net.sf.graphiti.transactions.SimpleTransaction;

import org.eclipse.core.runtime.Assert;

/**
 * This class provides methods to parse elements and attributes.
 * 
 * @author Matthieu Wipliez
 * 
 */
public class ContentParser {

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

	/**
	 * {@link Stack#peek()} returns the current {@link Element} (between a call
	 * to <code>elementStart</code> and <code>elementEnd</code>.
	 */
	private Stack<Element> elementStack;

	/**
	 * This is set by CreateGraphOpSpec.
	 */
	private Graph graph;

	/**
	 * {@link Stack#peek()} returns the {@link Result} associated with the
	 * current element (between a call to <code>elementStart</code> and
	 * <code>elementEnd</code>.
	 */
	private Stack<Result> resultStack;

	private SimpleTransaction transaction;

	/**
	 * Creates a new {@link ContentParser} with the given {@link Configuration}.
	 * 
	 * @param configuration
	 */
	public ContentParser(Configuration configuration) {
		this.configuration = configuration;
		elementStack = new Stack<Element>();
		resultStack = new Stack<Result>();
		transaction = new SimpleTransaction();
	}

	/**
	 * Parses the given DOM element when <b>after</b> its children and
	 * attributes have been parsed.
	 * 
	 * @param ontElement
	 * @param domElement
	 */
	public void elementEnd(Element ontElement, org.w3c.dom.Element domElement) {
		// graph
		int index = findNearestElement(OntologyFactory.getClassGraphElement());
		if (index == -1) {
			return;
		}

		if (ontElement instanceof VertexElement) {
			Operation addVertex = new Operation(new AddVertexOpSpec());
			addVertex.setOperands(resultStack.get(index), resultStack.peek());
			transaction.addOperation(addVertex);
		} else if (ontElement instanceof EdgeElement) {
			Operation addEdge = new Operation(new AddEdgeOpSpec());
			addEdge.setOperands(resultStack.get(index), resultStack.peek());
			transaction.addOperation(addEdge);
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
	public void elementStart(Element ontElement, org.w3c.dom.Element domElement) {
		elementStack.push(ontElement);
		Result result;

		if (ontElement instanceof GraphElement) {
			result = parseGraphElement((GraphElement) ontElement, domElement);
		} else if (ontElement instanceof VertexElement) {
			result = parseVertexElement((VertexElement) ontElement, domElement);
		} else if (ontElement instanceof EdgeElement) {
			result = parseEdgeElement((EdgeElement) ontElement, domElement);
		} else if (ontElement instanceof TextContentElement) {
			result = parseTextContentElement((TextContentElement) ontElement,
					domElement);
		} else {
			result = new Result();
		}

		resultStack.push(result);
		setParameterValues(ontElement, domElement);
	}

	/**
	 * Returns the nearest element whose class is <code>ontClass</code>. The
	 * search is done from inner level to outer level.
	 * 
	 * @param ontClass
	 *            The ontology class.
	 * @return The index of the nearest element whose class is
	 *         <code>clasz</code>, or <code>-1</code>.
	 */
	private int findNearestElement(String ontClass) {
		for (int i = elementStack.size() - 1; i >= 0; i--) {
			if (elementStack.get(i).hasOntClass(ontClass)) {
				return i;
			}
		}

		return -1;
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
	 * Commits the top transaction, and returns the graph created.
	 * 
	 * @return The {@link Graph} created.
	 */
	public Graph getGraph() {
		transaction.commit();
		return graph;
	}

	/**
	 * Returns the object (from the resultStack) that this parameter applies to.
	 * 
	 * @param parameter
	 * @return
	 */
	private Result getParameterObject(Parameter parameter) {
		Type objectType = parameter.appliesTo();
		int index = -1;

		if (objectType instanceof EdgeType) {
			index = findNearestElement(OntologyFactory.getClassEdgeElement());
		} else if (objectType instanceof GraphType) {
			index = findNearestElement(OntologyFactory.getClassGraphElement());
		} else if (objectType instanceof VertexType) {
			index = findNearestElement(OntologyFactory.getClassVertexElement());
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
	 * Parses the given attribute.
	 * 
	 * @param ontAttribute
	 * @param ontAttrName
	 * @param domAttrValue
	 */
	public void parseAttribute(XMLAttribute ontAttribute, String domAttrValue) {
		if (ontAttribute instanceof EdgeAttribute) {
			parseEdgeAttribute((EdgeAttribute) ontAttribute, domAttrValue);
		} else {
			parseOtherAttribute((OtherAttribute) ontAttribute, domAttrValue);
		}
	}

	/**
	 * Parses the given {@link EdgeAttribute}.
	 * 
	 * @param ontAttribute
	 * @param domAttrValue
	 */
	private void parseEdgeAttribute(EdgeAttribute ontAttribute,
			String domAttrValue) {
		Operation setEdgeEndpoint = new Operation(new SetEdgeEndpointOpSpec());
		Object[] operands = new Object[4];

		// graph
		int index = findNearestElement(OntologyFactory.getClassGraphElement());
		if (index == -1) {
			return;
		}
		operands[0] = resultStack.get(index);

		// edge
		index = findNearestElement(OntologyFactory.getClassEdgeElement());
		if (index == -1) {
			return;
		}
		operands[1] = resultStack.get(index);

		// endpoint
		if (ontAttribute instanceof EdgeSourceConnection) {
			operands[2] = "source";
		} else {
			operands[2] = "target";
		}

		// value
		operands[3] = domAttrValue;
		setEdgeEndpoint.setOperands(operands);
		transaction.addOperation(setEdgeEndpoint);
	}

	/**
	 * Parses the given edge element.
	 * 
	 * @param ontElement
	 * @param domElement
	 */
	private Result parseEdgeElement(EdgeElement ontElement,
			org.w3c.dom.Element domElement) {
		Operation createEdge = new Operation(new CreateEdgeOpSpec());
		transaction.addOperation(createEdge);
		return createEdge.getResult();
	}

	/**
	 * Parses the given graph element.
	 * 
	 * @param ontElement
	 * @param domElement
	 */
	private Result parseGraphElement(GraphElement ontElement,
			org.w3c.dom.Element domElement) {
		Operation createGraph = new Operation(new CreateGraphOpSpec());
		createGraph.setOperands(this, configuration);
		transaction.addOperation(createGraph);
		return createGraph.getResult();
	}

	/**
	 * Parses the given {@link OtherAttribute}.
	 * 
	 * @param ontAttribute
	 * @param domAttrValue
	 */
	private void parseOtherAttribute(OtherAttribute ontAttribute,
			String domAttrValue) {
		Parameter parameter = ontAttribute.hasParameter();

		if (parameter != null) {
			Result result = getParameterObject(parameter);

			// will set the parameter value
			Operation setProperty = new Operation(new SetValueOpSpec());
			Object[] operands = new Object[] { result,
					parameter.hasValueType().getDataType(),
					parameter.hasName(), domAttrValue };

			setProperty.setOperands(operands);
			transaction.addOperation(setProperty);
		}
	}

	/**
	 * Parses the given text content element.
	 * 
	 * @param ontElement
	 * @param domElement
	 */
	private Result parseTextContentElement(TextContentElement ontElement,
			org.w3c.dom.Element domElement) {
		Parameter parameter = ontElement.referencesParameter();

		if (parameter != null) {
			// will set the parameter value
			Operation setProperty = new Operation(new SetValueOpSpec());
			Object[] operands = new Object[] { resultStack.peek(),
					parameter.hasValueType().getDataType(),
					parameter.hasName(), domElement.getTextContent() };

			setProperty.setOperands(operands);
			transaction.addOperation(setProperty);
		}
		return new Result();
	}

	/**
	 * Parses the given vertex element.
	 * 
	 * @param ontElement
	 * @param domElement
	 */
	private Result parseVertexElement(VertexElement ontElement,
			org.w3c.dom.Element domElement) {
		Operation createVertex = new Operation(new CreateVertexOpSpec());
		transaction.addOperation(createVertex);
		return createVertex.getResult();
	}

	/**
	 * Sets this graph to the given graph.
	 * 
	 * @param graph
	 *            A {@link Graph}.
	 */
	public void setGraph(Graph graph) {
		this.graph = graph;
	}

	/**
	 * Iterates through the parameter values associated with this ontology
	 * element, and for each of them, calls
	 * {@link PropertyBean#setValue(String, Object)} with the parameter name and
	 * value. Example of a parameter value is "Port" associated to the parameter
	 * with the name "type".
	 * 
	 * @param ontElement
	 * @param domElement
	 */
	private void setParameterValues(Element ontElement,
			org.w3c.dom.Element domElement) {
		Set<ParameterValue> attributesNodes = ontElement.hasParameterValues();
		for (ParameterValue attNode : attributesNodes) {
			ParameterValue constant = (ParameterValue) attNode;
			Parameter parameter = constant.ofParameter();
			String parameterName = parameter.hasName();

			// will set the parameter value
			Operation setProperty = new Operation(new SetValueOpSpec());
			Object[] operands = new Object[] { resultStack.peek(),
					parameter.hasValueType().getDataType(), parameterName,
					constant.hasValue() };

			setProperty.setOperands(operands);
			transaction.addOperation(setProperty);
		}
	}

	@Override
	public String toString() {
		return "Content Parser";
	}
}
