/**
 * 
 */
package net.sf.graphiti.writer;

import java.io.OutputStream;
import java.util.List;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import net.sf.graphiti.model.DOMNode;
import net.sf.graphiti.model.DocumentConfiguration;
import net.sf.graphiti.model.Edge;
import net.sf.graphiti.model.Graph;
import net.sf.graphiti.model.GraphitiDocument;
import net.sf.graphiti.model.Vertex;
import net.sf.graphiti.ontology.OntologyFactory;
import net.sf.graphiti.ontology.nodes.ParserNode;
import net.sf.graphiti.ontology.nodes.ParserParameterNode;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.Text;

/**
 * @author mwipliez
 * 
 */
public class GenericGraphFileWriter {

	private GraphitiDocument document;

	private Document domDocument;

	/**
	 * Creates a new generic graph file writer, with the given document.
	 * 
	 * @param document
	 *            The document to write.
	 */
	public GenericGraphFileWriter(GraphitiDocument document) {
		this.document = document;
	}

	/**
	 * Creates a DOM element with the given parser node name. The element
	 * obtained is given attributes, both by exploring the available parser node
	 * attributes and the DOMNode attributes.
	 * 
	 * @param parserNode
	 *            The reference parser node.
	 * @param node
	 *            The source DOMNode element.
	 * @param domParentNode
	 *            The target parent DOM element node.
	 * @return The element created.
	 */
	private Element createElement(ParserNode parserNode, DOMNode node,
			Node domParentNode) {
		Element element = domDocument.createElement(parserNode.hasName());
		domParentNode.appendChild(element);

		Set<ParserParameterNode> attributes = parserNode.hasAttributeNode();
		for (ParserParameterNode attribute : attributes) {
			String attrName = attribute.hasName();
			Object attrValue = node.getValue(attrName);
			if (attrValue != null) {
				element.setAttribute(attrName, attrValue.toString());
			}
		}

		return element;
	}

	/**
	 * Fills the DOM document in, using the ontology factory. This method starts
	 * to write the nodes defined in the ontology, and then adds DOM
	 * information.
	 * 
	 * @param factory
	 */
	private void fillDocument(OntologyFactory factory) {
		Set<ParserNode> rootNodes = (Set<ParserNode>) factory
				.getParserRootNodes();

		writeNode(rootNodes, document, domDocument);
	}

	/**
	 * Sets the DOM node <code>domNode</code> attributes from the
	 * {@link DOMNode} <code>element</code>.
	 * 
	 * @param domNode
	 *            The target DOM node.
	 * @param element
	 *            The source DOMNode element.
	 */
	private void setDOMNodes(Element domNode, DOMNode element) {
		// Sets attributes
		List<DOMNode> attributes = element.getAttributes();
		for (DOMNode attribute : attributes) {
			domNode.setAttribute(attribute.getNodeName(), attribute
					.getNodeValue());
		}

		// And children
		List<DOMNode> elements = element.getElements();
		for (DOMNode child : elements) {
			// Do not recurse on graphs, vertices or edges.
			if (child instanceof Graph || child instanceof Vertex
					|| child instanceof Edge) {
				continue;
			}

			String childName = child.getNodeName();
			if (childName.equals("#text")) {
				Text node = domDocument.createTextNode(child.getNodeValue());
				domNode.appendChild(node);
			} else {
				Element domChild = domDocument.createElement(childName);
				domNode.appendChild(domChild);

				setDOMNodes(domChild, child);
			}
		}
	}

	/**
	 * Writes the document this writer was created with.
	 * 
	 * @param out
	 *            The output stream.
	 */
	public void write(OutputStream out) {
		DocumentConfiguration configuration = document
				.getDocumentConfiguration();
		OntologyFactory factory = configuration.getOntologyFactory();
		try {
			DocumentBuilder builder = DocumentBuilderFactory.newInstance()
					.newDocumentBuilder();
			domDocument = builder.newDocument();

			// Fills the DOM document
			fillDocument(factory);

			// Set up the output transformer
			TransformerFactory transfac = TransformerFactory.newInstance();
			Transformer trans = transfac.newTransformer();
			trans.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
			trans.setOutputProperty(OutputKeys.INDENT, "yes");

			// Print the DOM node
			StreamResult result = new StreamResult(out);
			DOMSource source = new DOMSource(domDocument);
			trans.transform(source, result);
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (TransformerConfigurationException e) {
			e.printStackTrace();
		} catch (TransformerException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Writes the DOMNode node.
	 * 
	 * @param parserNodes
	 *            available node in the ontology for this element
	 * @param node
	 *            The source DOMNode element.
	 * @param domParentNode
	 *            The target parent DOM element node.
	 */
	private void writeNode(Set<ParserNode> parserNodes, DOMNode node,
			Node domParentNode) {
		for (ParserNode parserNode : parserNodes) {
			if (parserNode
					.hasOntClass(OntologyFactory.getClassParserRootNode())) {
				// Document
				Element element = createElement(parserNode, node, domParentNode);

				GraphitiDocument doc = (GraphitiDocument) node;
				writeNode(parserNode.hasChildrenNode(), doc.getGraph(), element);
				setDOMNodes(element, node);
			} else if (parserNode.hasOntClass(OntologyFactory
					.getClassGraphNode())) {
				// Graph
				Element element = createElement(parserNode, node, domParentNode);
				Graph graph = (Graph) node;

				// vertices
				Set<Vertex> vertices = graph.vertexSet();
				for (Vertex vertex : vertices) {
					writeNode(parserNode.hasChildrenNode(), vertex, element);
				}

				// edges
				Set<Edge> edges = graph.edgeSet();
				for (Edge edge : edges) {
					writeNode(parserNode.hasChildrenNode(), edge, element);
				}

				// Adds DOM nodes
				setDOMNodes(element, node);
			} else if (parserNode.hasOntClass(OntologyFactory
					.getClassVertexNode())
					&& node instanceof Vertex) {
				// Vertex
				Element element = createElement(parserNode, node, domParentNode);
				setDOMNodes(element, node);
			} else if (parserNode.hasOntClass(OntologyFactory
					.getClassEdgeNode())
					&& node instanceof Edge) {
				// Edge
				Element element = createElement(parserNode, node, domParentNode);
				setDOMNodes(element, node);
			}
		}
	}
}
