/**
 * 
 */
package net.sf.graphiti.parsers;

import java.util.Set;

import net.sf.graphiti.model.Configuration;
import net.sf.graphiti.model.Edge;
import net.sf.graphiti.model.Graph;
import net.sf.graphiti.model.PropertyBean;
import net.sf.graphiti.model.Vertex;
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
import net.sf.graphiti.transactions.BinaryOperation;
import net.sf.graphiti.transactions.NaryOperation;
import net.sf.graphiti.transactions.Operand;
import net.sf.graphiti.transactions.Result;
import net.sf.graphiti.transactions.SimpleTransaction;
import net.sf.graphiti.transactions.UnaryOperation;

/**
 * @author mwipliez
 * 
 */
public class ContentParser {

	private UnaryOperation<Configuration, Graph> createGraph;

	/**
	 * This contains the {@link Result} associated with the current element
	 * (between a call to {@link #elementStart(Element, org.w3c.dom.Element)}
	 * and {@link #elementEnd(Element, org.w3c.dom.Element)}). May be
	 * <code>null</code>.
	 */
	private Result<?> currentElementResult;

	private Element currentOntElement;

	private SimpleTransaction transaction;

	/**
	 * Creates a new {@link ContentParser} with the given {@link Configuration}.
	 * 
	 * @param configuration
	 */
	public ContentParser(Configuration configuration) {
		this.transaction = new SimpleTransaction();
		createGraph = new UnaryOperation<Configuration, Graph>(
				new CreateGraphOpSpec());
		createGraph.setOperand(new Operand<Configuration>(configuration));
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
		} else if (ontElement instanceof EdgeElement) {
			BinaryOperation<Graph, Edge, Void> addEdge = new BinaryOperation<Graph, Edge, Void>(
					new AddEdgeOpSpec());
			addEdge.setOperand1(new Operand<Graph>(createGraph.getResult()));
			addEdge.setOperand2(new Operand<Edge>(currentElementResult));
			transaction.addOperation(addEdge);
		}

		currentElementResult = null;
		currentOntElement = null;
	}

	/**
	 * Parses the given DOM element when <b>before</b> its children and
	 * attributes are parsed.
	 * 
	 * @param ontElement
	 * @param domElement
	 */
	public void elementStart(Element ontElement, org.w3c.dom.Element domElement) {
		currentOntElement = ontElement;

		if (ontElement instanceof VertexElement) {
			parseVertexElement(ontElement, domElement);
		} else if (ontElement instanceof EdgeElement) {
			parseEdgeElement(ontElement, domElement);
		}

		setParameterValues(ontElement, domElement);
	}

	public Graph getGraph() {
		transaction.commit();
		return createGraph.getResult().getContents();
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
		if (currentOntElement instanceof EdgeElement) {
			NaryOperation<Object, Void> setEdgeEndpoint = new NaryOperation<Object, Void>(
					new SetEdgeEndpointOpSpec());
			setEdgeEndpoint.addOperand(new Operand<Object>(createGraph
					.getResult()));
			setEdgeEndpoint
					.addOperand(new Operand<Object>(currentElementResult));

			if (ontAttribute instanceof EdgeSourceConnection) {
				setEdgeEndpoint.addOperand(new Operand<Object>("source"));
			} else {
				setEdgeEndpoint.addOperand(new Operand<Object>("destination"));
			}

			setEdgeEndpoint.addOperand(new Operand<Object>(domAttrValue));
			transaction.addOperation(setEdgeEndpoint);
		}
	}

	/**
	 * Parses the given edge element.
	 * 
	 * @param ontElement
	 * @param domElement
	 */
	private void parseEdgeElement(Element ontElement,
			org.w3c.dom.Element domElement) {
		UnaryOperation<Object, Edge> createEdge = new UnaryOperation<Object, Edge>(
				new CreateEdgeOpSpec());
		transaction.addOperation(createEdge);

		currentElementResult = createEdge.getResult();
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
			NaryOperation<Object, Void> setProperty = new NaryOperation<Object, Void>(
					new SetParameterValueOpSpec());
			setProperty.addOperand(new Operand<Object>(currentElementResult));
			setProperty.addOperand(new Operand<Object>(parameter.hasName()));
			setProperty.addOperand(new Operand<Object>(domAttrValue));
			transaction.addOperation(setProperty);
		}

	}

	/**
	 * Parses the given vertex element.
	 * 
	 * @param ontElement
	 * @param domElement
	 */
	private void parseVertexElement(Element ontElement,
			org.w3c.dom.Element domElement) {
		UnaryOperation<Object, Vertex> createVertex = new UnaryOperation<Object, Vertex>(
				new CreateVertexOpSpec());
		transaction.addOperation(createVertex);

		currentElementResult = createVertex.getResult();

		BinaryOperation<Graph, Vertex, Void> addVertex = new BinaryOperation<Graph, Vertex, Void>(
				new AddVertexOpSpec());
		addVertex.setOperand1(new Operand<Graph>(createGraph.getResult()));
		addVertex.setOperand2(new Operand<Vertex>(createVertex.getResult()));
		transaction.addOperation(addVertex);
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
			NaryOperation<Object, Void> setProperty = new NaryOperation<Object, Void>(
					new SetParameterValueOpSpec());
			setProperty.addOperand(new Operand<Object>(currentElementResult));
			setProperty.addOperand(new Operand<Object>(parameterName));
			setProperty.addOperand(new Operand<Object>(constant.hasValue()));
			transaction.addOperation(setProperty);
		}
	}
}
