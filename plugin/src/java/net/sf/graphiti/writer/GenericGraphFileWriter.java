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

import java.io.OutputStream;

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

import net.sf.graphiti.model.Configuration;
import net.sf.graphiti.model.Graph;
import net.sf.graphiti.ontology.OntologyFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * This class provides a generic graph file writer.
 * 
 * @author Jonathan Piat
 * @author Matthieu Wipliez
 * 
 */
public class GenericGraphFileWriter {

	private Graph document;

	private Document domDocument;

	// private boolean sourceWritten = false;

	/**
	 * Creates a new generic graph file writer, with the given document.
	 * 
	 * @param document
	 *            The document to write.
	 */
	public GenericGraphFileWriter(Graph graph) {
		this.document = graph;
	}

	/**
	 * Creates a DOM element with the given parser node name. The element
	 * obtained is given attributes, both by exploring the available parser node
	 * attributes and the DOMNode attributes.
	 * 
	 * @param ontologyElement
	 *            The reference parser node.
	 * @param node
	 *            The source DOMNode element.
	 * @param domParentNode
	 *            The target parent DOM element node.
	 * @return The element created.
	 */
//	private Element createElement(
//			net.sf.graphiti.ontology.xmlDescriptions.xmlSchemaTypes.elements.Element ontologyElement,
//			Node domParentNode) {
//		Element element = domDocument.createElement(ontologyElement.hasName());
//		domParentNode.appendChild(element);
//		return element;
//	}

	/**
	 * Fills the DOM document in, using the ontology factory. This method starts
	 * to write the nodes defined in the ontology, and then adds DOM
	 * information.
	 * 
	 * @param factory
	 */
	private void fillDocument(OntologyFactory factory) {
		// DocumentElement ontDocElement = factory.getDocumentElement();
		// writeNode(ontDocElement, document, domDocument);
	}

//	boolean isNodeInstance(
//			DOMNode inst,
//			net.sf.graphiti.ontology.xmlDescriptions.xmlSchemaTypes.elements.Element ontNode) {
//		boolean isNodeInstance = true;
//		if (inst.getClass().equals(DOMNode.class)) {
//			return false;
//		}
//		if (inst.getNodeName() != null) {
//			isNodeInstance &= inst.getNodeName().equals(ontNode.hasName());
//		}
//		Set<ParameterValue> pvs = ontNode.hasParameterValues();
//		for (ParameterValue pv : pvs) {
//			isNodeInstance &= inst.getValue(pv.ofParameter().hasName()).equals(
//					pv.hasValue());
//		}
//		Set<AttributeRestriction> aRestrictions = ontNode
//				.hasAttributeRestriction();
//		for (AttributeRestriction pR : aRestrictions) {
//			Object val;
//			if (inst.getValue(pR.hasName()) == null) {
//				if (inst.getDOMAttributeValue(pR.hasName()) == null) {
//					return false;
//				} else {
//					val = inst.getDOMAttributeValue(pR.hasName());
//				}
//			} else {
//				val = inst.getValue(pR.hasName());
//			}
//			isNodeInstance &= val.equals(pR.hasValue());
//		}
//		return isNodeInstance;
//	}

	/**
	 * Sets the xmlns (to use when no xmlns has been set)
	 * 
	 * @param ns
	 *            The name space to set
	 */
	private void setXmlns(String ns) {
		((Element) domDocument.getFirstChild()).setAttribute("xmlns", ns);
	}

	/**
	 * Writes the document this writer was created with.
	 * 
	 * @param out
	 *            The output stream.
	 */
	public void write(OutputStream out) {
		Configuration configuration = document.getDocumentConfiguration();
		OntologyFactory factory = configuration.getOntologyFactory();
		try {
			DocumentBuilderFactory builderFactory = DocumentBuilderFactory
					.newInstance();
			builderFactory.setNamespaceAware(false);
			builderFactory.setValidating(false);
			DocumentBuilder builder = builderFactory.newDocumentBuilder();
			domDocument = builder.newDocument();

			// Fills the DOM document
			fillDocument(factory);
			if (domDocument.getNamespaceURI() == null
					|| domDocument.getNamespaceURI().equals("")) {
				setXmlns("http://default.0ns");
			}

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
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Choose the element to write has defined in the ontology
	 * 
	 * @param nodes
	 *            The available nodes in the ontology
	 * @param element
	 *            The current DOMElement
	 * @param parentNode
	 *            The parent node in the dom tree
	 */
//	private void writeCorrespondingNode(
//			Set<net.sf.graphiti.ontology.xmlDescriptions.xmlSchemaTypes.elements.Element> nodes,
//			DOMNode element, Node parentNode) {
//		List<DOMNode> treated = new ArrayList<DOMNode>();
//		List<net.sf.graphiti.ontology.xmlDescriptions.xmlSchemaTypes.elements.Element> ontologyElements = new ArrayList<net.sf.graphiti.ontology.xmlDescriptions.xmlSchemaTypes.elements.Element>(
//				nodes);
//		// while elements are to be written
//		// while (ontologyElements.size() > 0) {
//		// // obtain the current element
//		// net.sf.graphiti.ontology.xmlDescriptions.xmlSchemaTypes.elements.
//		// Element node = ontologyElements
//		// .get(0);
//		// // check if elements needs to be written previously
//		// while (node.hasPrecedenceElement() != null
//		// && ontologyElements.contains(node.hasPrecedenceElement())) {
//		// // There is element to write before writting the current element
//		// // and that haven't been written
//		// node = node.hasPrecedenceElement();
//		// }
//		// // remove the node from the node to write
//		// ontologyElements.remove(node);
//		//
//		// // treat the element considering its class
//		// if (node.hasOntClass(OntologyFactory.getClassGraphElement())) {
//		// if (element instanceof GraphitiDocument) {
//		// writeNode(node, ((GraphitiDocument) element).getGraph(),
//		// parentNode);
//		// treated.add(((GraphitiDocument) element).getGraph());
//		// }
//		// } else if (node
//		// .hasOntClass(OntologyFactory.getClassVertexElement())) {
//		// if (element instanceof GraphitiDocument) {
//		// for (Vertex vertex : ((GraphitiDocument) element)
//		// .getGraph().vertexSet()) {
//		// if (isNodeInstance(vertex, node)) {
//		// writeNode(node, vertex, parentNode);
//		// treated.add(vertex);
//		// }
//		// }
//		// } else if (element instanceof Graph) {
//		// for (Vertex vertex : ((Graph) element).vertexSet()) {
//		// if (isNodeInstance(vertex, node)) {
//		// writeNode(node, vertex, parentNode);
//		// treated.add(vertex);
//		// }
//		// }
//		// }
//		// } else if (node.hasOntClass(OntologyFactory.getClassSkipElement())) {
//		// boolean isTreated = false;
//		// for (DOMNode childNode : element.getDOMElements()) {
//		// if (isNodeInstance(childNode, node)) {
//		// writeNode(node, childNode, parentNode);
//		// isTreated = true;
//		// }
//		// }
//		// treated.add(element);
//		// } else if (node.hasOntClass(OntologyFactory.getClassEdgeElement())) {
//		// if (element instanceof GraphitiDocument) {
//		// for (Edge edge : ((GraphitiDocument) element).getGraph()
//		// .edgeSet()) {
//		// writeNode(node, edge, parentNode);
//		// treated.add(edge);
//		// }
//		// } else if (element instanceof Graph) {
//		// for (Edge edge : ((Graph) element).edgeSet()) {
//		// writeNode(node, edge, parentNode);
//		// treated.add(edge);
//		// }
//		// }
//		// } else {
//		// writeNode(node, element, parentNode);
//		// }
//		//
//		// }
//
//		for (DOMNode childElement : element.getDOMElements()) {
//			if (!childElement.getNodeName().equals("#text")
//					&& childElement.getClass().equals(DOMNode.class)) {
//				Element newElt = domDocument.createElement(childElement
//						.getNodeName());
//				if (childElement.getNodeValue() != null) {
//					newElt.setTextContent(childElement.getNodeValue());
//				}
//				for (DOMNode attribute : childElement.getDOMAttributes()) {
//					newElt.setAttribute(attribute.getNodeName(), attribute
//							.getNodeValue());
//				}
//				parentNode.appendChild(newElt);
//				writeCorrespondingNode(
//						new TreeSet<net.sf.graphiti.ontology.xmlDescriptions.xmlSchemaTypes.elements.Element>(),
//						childElement, newElt);
//			}
//		}
//
//	}

	/**
	 * Write the given node
	 * 
	 * @param node
	 *            The Ontology node
	 * @param element
	 *            The DOMElement to write
	 * @param parentNode
	 *            The parent node is the DOM tree
	 */
//	private void writeNode(
//			net.sf.graphiti.ontology.xmlDescriptions.xmlSchemaTypes.elements.Element node,
//			DOMNode element, Node parentNode) {
//		DOMNode trueElement;
//
//		// if the element to write is a SkipDOMNode this means that this node
//		// attributes values come from the upper level
//		if (element instanceof SkipDOMNode) {
//			trueElement = ((SkipDOMNode) element).getTrueNode();
//		} else {
//			trueElement = element;
//		}
//		Element newElement = createElement(node, parentNode);
//		for (AttributeRestriction restricts : node.hasAttributeRestriction()) {
//			newElement.setAttribute(restricts.hasName(), restricts.hasValue());
//		}
//
//		// write all the attribute which are not described in the ontology
//		/*
//		 * for (DOMNode attrNode : element.getDOMAttributes()) { if
//		 * (attrNode.getClass().equals(DOMNode.class)) {
//		 * newElement.setAttribute(attrNode.getNodeName(), attrNode
//		 * .getNodeValue()); } }
//		 */
//
//		// If node is an instance of OtherAttributes
//		if (node.hasOntClass(OntologyFactory.getClassOtherAttribute())) {
//			OtherAttribute param = (OtherAttribute) node;
//			newElement.setTextContent((String) trueElement.getValue(param
//					.hasParameter().hasName()));
//		}
//
//		// Write the node attributes
//		for (XMLAttribute attr : node.hasAttributes()) {
//			if (attr.hasOntClass(OntologyFactory.getClassOtherAttribute())
//					&& (!attr.hasOntClass(OntologyFactory
//							.getClassParameterValue()))) {
//				OtherAttribute beanParam = (OtherAttribute) attr;
//				Parameter param = beanParam.hasParameter();
//				Object val = trueElement.getValue(param.hasName());
//				if (val != null) {
//					newElement
//							.setAttribute(beanParam.hasName(), val.toString());
//				}
//			} else if (attr
//					.hasOntClass(OntologyFactory.getClassEdgeAttribute())) {
//				if (attr.hasOntClass(OntologyFactory
//						.getClassEdgeSourceConnection())) {
//					newElement.setAttribute(attr.hasName(),
//							(String) ((Edge) trueElement).getSource().getValue(
//									"id"));
//				} else if (attr.hasOntClass(OntologyFactory
//						.getClassEdgeTargetConnection())) {
//					newElement.setAttribute(attr.hasName(),
//							(String) ((Edge) trueElement).getTarget().getValue(
//									"id"));
//				} else if (attr.hasOntClass(OntologyFactory
//						.getClassEdgeConnection())) {
//					if (!sourceWritten) {
//						newElement.setAttribute(attr.hasName(),
//								(String) ((Edge) trueElement).getSource()
//										.getValue("id"));
//						sourceWritten = true;
//					} else {
//						newElement.setAttribute(attr.hasName(),
//								(String) ((Edge) trueElement).getTarget()
//										.getValue("id"));
//						sourceWritten = false;
//					}
//				}
//			}
//		}
//		// Write the children of this node
//		// writeCorrespondingNode(node.hasElementChildren(), element,
//		// newElement);
//	}

}
