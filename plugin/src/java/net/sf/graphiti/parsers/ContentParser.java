/**
 * 
 */
package net.sf.graphiti.parsers;

import net.sf.graphiti.model.Configuration;
import net.sf.graphiti.model.Graph;
import net.sf.graphiti.model.Vertex;
import net.sf.graphiti.ontology.xmlDescriptions.xmlSchemaTypes.elements.Element;
import net.sf.graphiti.ontology.xmlDescriptions.xmlSchemaTypes.elements.VertexElement;
import net.sf.graphiti.parsers.operations.AddVertexOpSpec;
import net.sf.graphiti.parsers.operations.CreateGraphOpSpec;
import net.sf.graphiti.parsers.operations.CreateVertexOpSpec;
import net.sf.graphiti.transactions.BinaryOperation;
import net.sf.graphiti.transactions.Operand;
import net.sf.graphiti.transactions.SimpleTransaction;
import net.sf.graphiti.transactions.UnaryOperation;

/**
 * @author mwipliez
 * 
 */
public class ContentParser {

	private SimpleTransaction transaction;

	private UnaryOperation<Configuration, Graph> createGraph;

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
	 * Parses the given DOM element.
	 * 
	 * @param ontElement
	 * @param domElement
	 */
	public void parseElement(Element ontElement, org.w3c.dom.Element domElement) {
		if (ontElement instanceof VertexElement) {
			UnaryOperation<Object, Vertex> createVertex = new UnaryOperation<Object, Vertex>(
					new CreateVertexOpSpec());
			createVertex.setOperand(new Operand<Object>(new Object()));
			transaction.addOperation(createVertex);

			BinaryOperation<Graph, Vertex, Void> addVertex = new BinaryOperation<Graph, Vertex, Void>(
					new AddVertexOpSpec());
			addVertex.setOperand1(new Operand<Graph>(createGraph.getResult()));
			addVertex
					.setOperand2(new Operand<Vertex>(createVertex.getResult()));
			transaction.addOperation(addVertex);
		}
	}

	public Graph getGraph() {
		transaction.commit();
		return createGraph.getResult().getContents();
	}
}
