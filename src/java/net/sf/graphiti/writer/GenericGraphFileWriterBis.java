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
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

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
import net.sf.graphiti.ontology.nodes.parameters.PropertyBeanParameter;
import net.sf.graphiti.ontology.parameters.Parameter;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class GenericGraphFileWriterBis {

	private GraphitiDocument document;

	private Document domDocument;

	private boolean sourceWritten = false;

	/**
	 * Creates a new generic graph file writer, with the given document.
	 * 
	 * @param document
	 *            The document to write.
	 */
	public GenericGraphFileWriterBis(GraphitiDocument document) {
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
	private Element createElement(ParserNode parserNode, Node domParentNode) {
		Element element = domDocument.createElement(parserNode.hasName());
		domParentNode.appendChild(element);
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
		for (ParserNode root : rootNodes) {
			writeNode(root, document, domDocument);
		}
	}

	public void setXmlns(String ns) {
		((Element) domDocument.getFirstChild())
				.setAttribute("xmlns", ns);
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
			DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
			builderFactory.setNamespaceAware(false);
			builderFactory.setValidating(false);
			DocumentBuilder builder = builderFactory
					.newDocumentBuilder();
			domDocument = builder.newDocument();

			// Fills the DOM document
			fillDocument(factory);
			if (domDocument.getNamespaceURI() == null
					|| domDocument.getNamespaceURI().equals("") ) {
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
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void writeCorrespondingNode(Set<ParserNode> nodes, DOMNode element,
			Node parentNode) {
		List<DOMNode> treated = new ArrayList<DOMNode>();
		List<ParserNode> parserNodes = new ArrayList<ParserNode>(nodes);
		while (parserNodes.size() > 0) {
			ParserNode node = parserNodes.get(0);
			if (node.hasPrecedenceNode() != null) {
				if (parserNodes.contains(node.hasPrecedenceNode())) {
					node = node.hasPrecedenceNode();
				}
			}
			parserNodes.remove(node);
			if (node.hasOntClass(OntologyFactory.getClassGraphNode())) {
				if (element instanceof GraphitiDocument) {
					writeNode(node, ((GraphitiDocument) element).getGraph(),
							parentNode);
					treated.add(((GraphitiDocument) element).getGraph());
				}
			} else if (node.hasOntClass(OntologyFactory.getClassVertexNode())) {
				if (element instanceof GraphitiDocument) {
					for (Vertex vertex : ((GraphitiDocument) element)
							.getGraph().vertexSet()) {
						if (node.hasName().equals(vertex.getNodeName())) {
							writeNode(node, vertex, parentNode);
							treated.add(vertex);
						}
					}
				} else if (element instanceof Graph) {
					for (Vertex vertex : ((Graph) element).vertexSet()) {
						if (node.hasName().equals(vertex.getNodeName())) {
							writeNode(node, vertex, parentNode);
							treated.add(vertex);
						}
					}
				}
			} else if (node.hasOntClass(OntologyFactory.getClassSkipNode())) {
				boolean isTreated = false;
				for (DOMNode childNode : element.getDOMElements()) {
					if (childNode.getNodeName().equals(node.hasName())) {
						writeNode(node, element, parentNode);
						isTreated = true;
					}
				}
				if (!isTreated) {
					writeNode(node, element, parentNode);
				}
				treated.add(element);
			} else if (node.hasOntClass(OntologyFactory.getClassEdgeNode())) {
				if (element instanceof GraphitiDocument) {
					for (Edge edge : ((GraphitiDocument) element).getGraph()
							.edgeSet()) {
						writeNode(node, edge, parentNode);
						treated.add(edge);
					}
				} else if (element instanceof Graph) {
					for (Edge edge : ((Graph) element).edgeSet()) {
						writeNode(node, edge, parentNode);
						treated.add(edge);
					}
				}
			} else {
				writeNode(node, element, parentNode);
			}

		}
		for (DOMNode childElement : element.getDOMElements()) {
			if (!childElement.getNodeName().equals("#text")
					&& childElement.getClass().equals(DOMNode.class)) {
				Element newElt = domDocument.createElement(childElement
						.getNodeName());
				if (childElement.getNodeValue() != null) {
					newElt.setTextContent(childElement.getNodeValue());
				}
				for (DOMNode attribute : childElement.getDOMAttributes()) {
					newElt.setAttribute(attribute.getNodeName(), attribute
							.getNodeValue());
				}
				parentNode.appendChild(newElt);
				writeCorrespondingNode(new TreeSet<ParserNode>(), childElement,
						newElt);
			} 
		}

	}

	private void writeNode(ParserNode node, DOMNode element, Node parentNode) {
		Element newElement = createElement(node, parentNode);
		for (DOMNode attrNode : element.getDOMAttributes()) {
			if (attrNode.getClass().equals(DOMNode.class)) {
				newElement.setAttribute(attrNode.getNodeName(), attrNode
						.getNodeValue());
			}
		}
		if (node.hasOntClass(OntologyFactory.getClassPropertyBeanParameter())) {
			PropertyBeanParameter param = (PropertyBeanParameter) node;
			newElement.setTextContent((String) element.getValue(param
					.hasParameter().hasName()));
		}
		for (ParserParameterNode attr : node.hasAttributeNode()) {
			if (attr.hasOntClass(OntologyFactory
					.getClassPropertyBeanParameter())
					&& (!attr.hasOntClass(OntologyFactory
							.getClassConstantParameter()))) {
				PropertyBeanParameter beanParam = (PropertyBeanParameter) attr;
				Parameter param = beanParam.hasParameter();
				Object val = element.getValue(param.hasName());
				if (val != null) {
					newElement
							.setAttribute(beanParam.hasName(), val.toString());
				}
			} else if (attr.hasOntClass(OntologyFactory
					.getClassEdgeParameterNode())) {
				if (attr.hasOntClass(OntologyFactory
						.getClassEdgeSourceConnection())) {
					newElement.setAttribute(attr.hasName(),
							(String) ((Edge) element).getSource()
									.getValue("id"));
				} else if (attr.hasOntClass(OntologyFactory
						.getClassEdgeTargetConnection())) {
					newElement.setAttribute(attr.hasName(),
							(String) ((Edge) element).getTarget()
									.getValue("id"));
				} else if (attr.hasOntClass(OntologyFactory
						.getClassEdgeConnection())) {
					if (sourceWritten) {
						newElement.setAttribute(attr.hasName(),
								(String) ((Edge) element).getSource().getValue(
										"id"));
						sourceWritten = false;
					} else {
						newElement.setAttribute(attr.hasName(),
								(String) ((Edge) element).getTarget().getValue(
										"id"));
						sourceWritten = true;
					}
				}
			}
		}
		writeCorrespondingNode(node.hasChildrenNode(), element, newElement);
	}

}
