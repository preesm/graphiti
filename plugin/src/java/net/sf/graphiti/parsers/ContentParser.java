/**
 * 
 */
package net.sf.graphiti.parsers;

import java.util.Set;

import net.sf.graphiti.model.Configuration;
import net.sf.graphiti.model.Graph;
import net.sf.graphiti.model.PropertyBean;
import net.sf.graphiti.model.Vertex;
import net.sf.graphiti.ontology.OntologyFactory;
import net.sf.graphiti.ontology.parameterValues.ParameterValue;
import net.sf.graphiti.ontology.parameters.Parameter;
import net.sf.graphiti.ontology.xmlDescriptions.xmlAttributes.XMLAttribute;
import net.sf.graphiti.ontology.xmlDescriptions.xmlAttributes.otherAttributes.OtherAttribute;
import net.sf.graphiti.ontology.xmlDescriptions.xmlSchemaTypes.elements.Element;
import net.sf.graphiti.ontology.xmlDescriptions.xmlSchemaTypes.elements.VertexElement;
import net.sf.graphiti.parsers.operations.AddVertexOpSpec;
import net.sf.graphiti.parsers.operations.CreateGraphOpSpec;
import net.sf.graphiti.parsers.operations.CreateVertexOpSpec;
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

	private Result<?> lastObjectCreated;

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

	public Graph getGraph() {
		transaction.commit();
		return createGraph.getResult().getContents();
	}

	/**
	 * 
	 * @param ontAttribute
	 * @param ontAttrName
	 * @param domAttrValue
	 */
	public void parseAttribute(XMLAttribute ontAttribute, String ontAttrName,
			String domAttrValue) {
		if (ontAttribute.hasOntClass(OntologyFactory.getClassOtherAttribute())) {
			OtherAttribute attr = (OtherAttribute) ontAttribute;
			Parameter parameter = attr.hasParameter();

			if (parameter != null) {
				// will set the parameter value
				NaryOperation<Object, Void> setProperty = new NaryOperation<Object, Void>(
						new SetParameterValueOpSpec());
				setProperty.addOperand(new Operand<Object>(lastObjectCreated));
				setProperty
						.addOperand(new Operand<Object>(parameter.hasName()));
				setProperty.addOperand(new Operand<Object>(domAttrValue));
				transaction.addOperation(setProperty);
			}
		}
	}

	/**
	 * Parses the given DOM element.
	 * 
	 * @param ontElement
	 * @param domElement
	 */
	public void parseElement(Element ontElement, org.w3c.dom.Element domElement) {
		lastObjectCreated = null;

		if (ontElement instanceof VertexElement) {
			UnaryOperation<Object, Vertex> createVertex = new UnaryOperation<Object, Vertex>(
					new CreateVertexOpSpec());
			createVertex.setOperand(new Operand<Object>(new Object()));
			transaction.addOperation(createVertex);

			lastObjectCreated = createVertex.getResult();

			BinaryOperation<Graph, Vertex, Void> addVertex = new BinaryOperation<Graph, Vertex, Void>(
					new AddVertexOpSpec());
			addVertex.setOperand1(new Operand<Graph>(createGraph.getResult()));
			addVertex
					.setOperand2(new Operand<Vertex>(createVertex.getResult()));
			transaction.addOperation(addVertex);
		}

		setParameterValues(ontElement, domElement);
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
			setProperty.addOperand(new Operand<Object>(lastObjectCreated));
			setProperty.addOperand(new Operand<Object>(parameterName));
			setProperty.addOperand(new Operand<Object>(constant.hasValue()));
			transaction.addOperation(setProperty);
		}
	}
}
