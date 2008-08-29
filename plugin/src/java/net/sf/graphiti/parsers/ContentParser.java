/**
 * 
 */
package net.sf.graphiti.parsers;

import java.util.Set;
import java.util.Stack;

import net.sf.graphiti.model.Configuration;
import net.sf.graphiti.model.Graph;
import net.sf.graphiti.model.PropertyBean;
import net.sf.graphiti.ontology.parameterValues.ParameterValue;
import net.sf.graphiti.ontology.parameters.Parameter;
import net.sf.graphiti.ontology.xmlDescriptions.xmlAttributes.XMLAttribute;
import net.sf.graphiti.ontology.xmlDescriptions.xmlAttributes.edgeAttributes.EdgeAttribute;
import net.sf.graphiti.ontology.xmlDescriptions.xmlAttributes.edgeAttributes.EdgeSourceConnection;
import net.sf.graphiti.ontology.xmlDescriptions.xmlAttributes.otherAttributes.OtherAttribute;
import net.sf.graphiti.ontology.xmlDescriptions.xmlSchemaTypes.elements.EdgeElement;
import net.sf.graphiti.ontology.xmlDescriptions.xmlSchemaTypes.elements.Element;
import net.sf.graphiti.ontology.xmlDescriptions.xmlSchemaTypes.elements.VertexElement;
import net.sf.graphiti.parsers.operations.AddEdgeOpSpec;
import net.sf.graphiti.parsers.operations.AddVertexOpSpec;
import net.sf.graphiti.parsers.operations.CreateEdgeOpSpec;
import net.sf.graphiti.parsers.operations.CreateGraphOpSpec;
import net.sf.graphiti.parsers.operations.CreateVertexOpSpec;
import net.sf.graphiti.parsers.operations.SetEdgeEndpointOpSpec;
import net.sf.graphiti.parsers.operations.SetParameterValueOpSpec;
import net.sf.graphiti.transactions.Operand;
import net.sf.graphiti.transactions.Operation;
import net.sf.graphiti.transactions.Result;
import net.sf.graphiti.transactions.SimpleTransaction;

/**
 * @author mwipliez
 * 
 */
public class ContentParser {

	private Operation createGraph;

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
	 * Creates a new {@link ContentParser} with the given {@link Configuration}.
	 * 
	 * @param configuration
	 */
	public ContentParser(Configuration configuration) {
		this.transaction = new SimpleTransaction();
		elementStack = new Stack<Element>();
		resultStack = new Stack<Result>();

		createGraph = new Operation(new CreateGraphOpSpec());
		createGraph.setOperand(new Operand(configuration));
		transaction.addOperation(createGraph);
	}

	/**
	 * Parses the given DOM element when <b>after</b> its children and
	 * attributes have been parsed.
	 * 
	 * @param ontElement
	 * @param domElement
	 */
	public void elementEnd(Element ontElement, org.w3c.dom.Element domElement) {
		if (ontElement instanceof VertexElement) {
			Operation addVertex = new Operation(new AddVertexOpSpec());
			addVertex.setOperands(new Operand(createGraph.getResult()),
					new Operand(resultStack.peek()));
			transaction.addOperation(addVertex);
		} else if (ontElement instanceof EdgeElement) {
			Operation addEdge = new Operation(new AddEdgeOpSpec());
			addEdge.setOperands(new Operand(createGraph.getResult()),
					new Operand(resultStack.peek()));
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

		if (ontElement instanceof VertexElement) {
			result = parseVertexElement(ontElement, domElement);
		} else if (ontElement instanceof EdgeElement) {
			result = parseEdgeElement(ontElement, domElement);
		} else {
			result = new Result();
		}

		resultStack.push(result);
		setParameterValues(ontElement, domElement);
	}

	/**
	 * Commits the top transaction, and returns the graph created.
	 * 
	 * @return The {@link Graph} created.
	 */
	public Graph getGraph() {
		transaction.commit();
		return (Graph) createGraph.getResult().getContents();
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
		if (elementStack.peek() instanceof EdgeElement) {
			Operation setEdgeEndpoint = new Operation(
					new SetEdgeEndpointOpSpec());
			Operand[] operands = new Operand[4];
			setEdgeEndpoint.setOperands(operands);

			operands[0] = new Operand(createGraph.getResult());
			operands[1] = new Operand(resultStack.peek());

			if (ontAttribute instanceof EdgeSourceConnection) {
				operands[2] = new Operand("source");
			} else {
				operands[2] = new Operand("destination");
			}

			operands[3] = new Operand(domAttrValue);

			transaction.addOperation(setEdgeEndpoint);
		}
	}

	/**
	 * Parses the given edge element.
	 * 
	 * @param ontElement
	 * @param domElement
	 */
	private Result parseEdgeElement(Element ontElement,
			org.w3c.dom.Element domElement) {
		Operation createEdge = new Operation(new CreateEdgeOpSpec());
		transaction.addOperation(createEdge);
		return createEdge.getResult();
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
			// will set the parameter value
			Operation setProperty = new Operation(new SetParameterValueOpSpec());
			Operand[] operands = new Operand[3];
			setProperty.setOperands(operands);

			operands[0] = new Operand(resultStack.peek());
			operands[1] = new Operand(parameter.hasName());
			operands[2] = new Operand(domAttrValue);

			transaction.addOperation(setProperty);
		}

	}

	/**
	 * Parses the given vertex element.
	 * 
	 * @param ontElement
	 * @param domElement
	 */
	private Result parseVertexElement(Element ontElement,
			org.w3c.dom.Element domElement) {
		Operation createVertex = new Operation(new CreateVertexOpSpec());
		transaction.addOperation(createVertex);
		return createVertex.getResult();
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
			String parameterName = constant.ofParameter().hasName();

			// will set the parameter value
			Operation setProperty = new Operation(new SetParameterValueOpSpec());
			Operand[] operands = new Operand[3];
			setProperty.setOperands(operands);

			operands[0] = new Operand(resultStack.peek());
			operands[1] = new Operand(parameterName);
			operands[2] = new Operand(constant.hasValue());

			transaction.addOperation(setProperty);
		}
	}
}
