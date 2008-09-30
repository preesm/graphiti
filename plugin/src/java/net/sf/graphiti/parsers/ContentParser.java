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
import net.sf.graphiti.ontology.EdgeAttribute;
import net.sf.graphiti.ontology.EdgeElement;
import net.sf.graphiti.ontology.EdgeSourceConnection;
import net.sf.graphiti.ontology.EdgeType;
import net.sf.graphiti.ontology.Element;
import net.sf.graphiti.ontology.GraphElement;
import net.sf.graphiti.ontology.GraphType;
import net.sf.graphiti.ontology.OtherAttribute;
import net.sf.graphiti.ontology.Parameter;
import net.sf.graphiti.ontology.ParameterSource;
import net.sf.graphiti.ontology.ParameterValue;
import net.sf.graphiti.ontology.TextContentElement;
import net.sf.graphiti.ontology.Type;
import net.sf.graphiti.ontology.VertexElement;
import net.sf.graphiti.ontology.VertexType;
import net.sf.graphiti.ontology.XMLAttribute;
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
import net.sf.graphiti.writer.ContentWriter;

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
		 * Index of the last edge created.
		 */
		private int edgeIndex;

		/**
		 * Index of the last graph created.
		 */
		private int graphIndex;

		/**
		 * The transaction index.
		 */
		private int transactionIndex;

		/**
		 * Index of the last vertex created.
		 */
		private int vertexIndex;

		/**
		 * Creates a new Checkpoint that saves the current state of this content
		 * parser.
		 */
		public Checkpoint() {
			depth = elementStack.size();
			this.edgeIndex = ContentParser.this.edgeIndex;
			this.graphIndex = ContentParser.this.graphIndex;
			transactionIndex = transaction.size();
			this.vertexIndex = ContentParser.this.vertexIndex;
		}

	}

	/**
	 * The configuration this content parser was created with.
	 */
	private Configuration configuration;

	/**
	 * Index of the last edge created.
	 */
	private int edgeIndex;

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
	 * Index of the last graph created.
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
	 * Index of the last vertex created.
	 */
	private int vertexIndex;

	/**
	 * Creates a new {@link ContentWriter} with the given {@link Configuration}.
	 * 
	 * @param configuration
	 */
	public ContentParser(Configuration configuration) {
		this.configuration = configuration;
		edgeIndex = -1;
		elementStack = new Stack<Element>();
		graphIndex = -1;
		resultStack = new Stack<Result>();
		transaction = new SimpleTransaction();
		vertexIndex = -1;
	}

	/**
	 * Adds a {@link SetValueOpSpec} operation to the transaction.
	 * 
	 * @param parameter
	 *            The parameter.
	 * @param value
	 *            Its value.
	 */
	public void addValue(Parameter parameter, String value) {
		if (parameter != null) {
			// will set the parameter value
			Operation setProperty = new Operation(SetValueOpSpec.getInstance());
			setProperty.setOperands(getParameterObject(parameter),
					new net.sf.graphiti.model.Parameter(parameter), value);
			transaction.addOperation(setProperty);
		}
	}

	/**
	 * Parses the given DOM element when <b>after</b> its children and
	 * attributes have been parsed.
	 * 
	 * @param ontElement
	 * @param domElement
	 */
	public void elementEnd(Element ontElement, org.w3c.dom.Element domElement) {
		if (ontElement instanceof GraphElement) {
			graphIndex = -1;
		} else if (ontElement instanceof VertexElement) {
			Operation addVertex = new Operation(new AddVertexOpSpec());
			Result result = resultStack.peek();
			addVertex.setOperands(resultStack.get(graphIndex), result);
			transaction.addOperation(addVertex);
			vertexIndex = -1;
		} else if (ontElement instanceof EdgeElement) {
			Operation addEdge = new Operation(new AddEdgeOpSpec());
			Result result = resultStack.peek();
			addEdge.setOperands(resultStack.get(graphIndex), result);
			transaction.addOperation(addEdge);
			edgeIndex = -1;
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
		Result result = null;

		if (ontElement instanceof GraphElement) {
			result = parseGraphElement((GraphElement) ontElement, domElement);
			graphIndex = resultStack.size();
		} else if (ontElement instanceof VertexElement) {
			result = parseVertexElement((VertexElement) ontElement, domElement);
			vertexIndex = resultStack.size();
		} else if (ontElement instanceof EdgeElement) {
			result = parseEdgeElement((EdgeElement) ontElement, domElement);
			edgeIndex = resultStack.size();
		} else if (ontElement instanceof TextContentElement) {
			Parameter parameter = ((TextContentElement) ontElement)
					.referencesParameter();
			addValue(parameter, domElement.getTextContent());
		}

		if (result == null) {
			result = new Result();
		}

		resultStack.push(result);
		setParameterValues(ontElement, domElement);
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

		edgeIndex = checkpoint.edgeIndex;
		graphIndex = checkpoint.graphIndex;
		vertexIndex = checkpoint.vertexIndex;
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
			Parameter parameter = ((OtherAttribute) ontAttribute)
					.hasParameter();
			if (parameter != null) {
				addValue(parameter, domAttrValue);
			}
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

		operands[0] = resultStack.get(graphIndex);
		operands[1] = resultStack.get(edgeIndex);

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
			ParameterValue paValue = (ParameterValue) attNode;
			ParameterSource source = paValue.hasSource();

			// only set parameter if dealing with current element
			if (source == null || source.isCurrentElement()) {
				Parameter parameter = paValue.ofParameter();

				// will set the parameter value
				Operation setProperty = new Operation(SetValueOpSpec
						.getInstance());
				net.sf.graphiti.model.Parameter modelParameter = new net.sf.graphiti.model.Parameter(
						parameter);
				setProperty.setOperands(resultStack.peek(), modelParameter,
						paValue.hasValue());
				transaction.addOperation(setProperty);
			}
		}
	}

	@Override
	public String toString() {
		return "Content Parser";
	}
}
