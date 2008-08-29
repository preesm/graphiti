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


/**
 * This class provides a generic graph file parser.
 * 
 * @author Jonathan Piat
 * @author Matthieu Wipliez
 * 
 */
public class GenericGraphFileParserBackup {

//	private static final String IS_PORT = "isPort";
//
//	private Configuration configuration;
//
//	private Logger log;
//
//	private HashMap<Node, Object> nodeToObj = new HashMap<Node, Object>();
//
//	private HashMap<Element, List<PropertyBean>> ontDomInstances = new HashMap<Element, List<PropertyBean>>();

	/**
	 * Creates a generic graph parser using the given document configuration
	 * tree.
	 * 
	 * @param configuration
	 *            The root of the {@link Configuration} tree.
	 */
//	public GenericGraphFileParserBackup(Configuration configuration) {
//		this.configuration = configuration;
//	}

//	private void addOntDomInstance(Element node, PropertyBean bean) {
//		List<PropertyBean> beans;
//		if ((beans = ontDomInstances.get(node)) == null) {
//			beans = new ArrayList<PropertyBean>();
//			ontDomInstances.put(node, beans);
//		}
//		beans.add(bean);
//	}

//	private PropertyBean getElementFromClass(String ontClass, String refVal) {
//		for (Element refType : ontDomInstances.keySet()) {
//			if (refType.hasOntClass(ontClass)) {
//				List<PropertyBean> references = ontDomInstances.get(refType);
//				if (references != null) {
//					for (int i = 0; i < references.size(); i++) {
//						PropertyBean ref = references.get(i);
//						if (ref.getValue(Graph.PARAMETER_ID) instanceof String
//								&& ref.getValue(Graph.PARAMETER_ID).equals(
//										refVal)) {
//							return ref;
//						}
//					}
//				}
//			}
//		}
//		return null;
//	}

//	private PropertyBean getReference(EdgeAttribute node, String refVal) {
//		for (Element refType : node.isReferenceTo()) {
//			List<PropertyBean> references = ontDomInstances.get(refType);
//			if (references != null) {
//				for (int i = 0; i < references.size(); i++) {
//					PropertyBean ref = references.get(i);
//					if (ref.getValue(Graph.PARAMETER_ID) instanceof String
//							&& ref.getValue(Graph.PARAMETER_ID).equals(refVal)) {
//						return ref;
//					}
//				}
//			}
//		}
//		return null;
//	}

	/**
	 * Parses a the DOM implementation of a parameter defined in the ontology
	 * 
	 * @param ontNode
	 *            the ontology node
	 * @param attribute
	 *            the DOM node to parse
	 * @param parentElement
	 *            the parent OntologyNode
	 */
//	private void parseAttribute(XMLAttribute ontNode, Node attribute,
//			PropertyBean parentElement) {
//		PropertyBean trueParentElement;
//		// if the element to write is a SkipDOMNode this means that this node
//		// attributes values come from the upper level
//		if (parentElement instanceof SkipDOMNode) {
//			trueParentElement = ((SkipDOMNode) parentElement).getTrueNode();
//		} else {
//			trueParentElement = parentElement;
//		}
//		// if this parameter is an EdgeAttribute
//		if (ontNode.hasOntClass(OntologyFactory.getClassEdgeAttribute())) {
//			PropertyBean ref = getReference((EdgeAttribute) ontNode, attribute
//					.getNodeValue());
//			if (trueParentElement instanceof Edge
//					&& ontNode.hasOntClass(OntologyFactory
//							.getClassEdgeAttribute())) {
//				Edge edge = (Edge) trueParentElement;
//				String nodeName = attribute.getNodeName();
//				String nodeValue = attribute.getNodeValue();
//				edge.setValue(nodeName, nodeValue);
//				setEdgeConnection(ref, (EdgeAttribute) ontNode, edge);
//			}
//		} else {
//			// if this parameter is an OtherAttribute
//			if (attribute != null) {
//				String value = attribute.getNodeValue();
//				if (value == null) {
//					value = attribute.getTextContent();
//				}
//				// if
//				// (ontNode.hasOntClass(OntologyFactory.getClassIdParameter()))
//				// {
//				// parentElement.setValue(Graph.PARAMETER_ID, value);
//				// } else {
//				OtherAttribute beanNode = (OtherAttribute) ontNode;
//				trueParentElement.setValue(beanNode.hasParameter().hasName(),
//						value);
//				// }
//			}
//		}
//	}

//	private void setEdgeConnection(PropertyBean ref,
//			EdgeAttribute connectionType, Edge edge) {
//		 Vertex connection;
//		if (ref == null || ref instanceof Graph) {
//			connection = new Vertex(edge.getParentDocument());
//			connection.setValue(IS_PORT, true);
//		} else {
//			connection = (Vertex) ref;
//		}
//		if (connectionType.hasOntClass(OntologyFactory
//				.getClassEdgeSourceConnection())) {
//			edge.setSource(connection);
//		} else if (connectionType.hasOntClass(OntologyFactory
//				.getClassEdgeTargetConnection())) {
//			edge.setTarget(connection);
//		} else if (connectionType.hasOntClass(OntologyFactory
//				.getClassEdgeConnection())) {
//			if (edge.getSource() == null) {
//				edge.setSource(connection);
//			} else if (edge.getTarget() == null) {
//				edge.setTarget(connection);
//			}
//		}
//	}

//	private void treatEdge(Edge edge, Graph parentGraph) {
//		if (edge.getSource().getValue(IS_PORT) != null
//				&& (Boolean) edge.getSource().getValue(IS_PORT)) {
//			Vertex trueSource = (Vertex) getElementFromClass(OntologyFactory
//					.getClassVertexElement(), (String) edge
//					.getValue(Edge.SRC_PORT_NAME));
//			if (trueSource != null) {
//				edge.setSource(trueSource);
//			} else {
//				edge.getSource().setValue(PropertyBean.PROPERTY_NAME,
//						(String) edge.getValue(Edge.SRC_PORT_NAME));
//				edge.getSource().setValue(Graph.PARAMETER_ID,
//						(String) edge.getValue(Edge.SRC_PORT_NAME));
//				parentGraph.addVertex(edge.getSource());
//			}
//		}
//
//		if (edge.getTarget().getValue(IS_PORT) != null
//				&& (Boolean) edge.getTarget().getValue(IS_PORT)) {
//			Vertex trueTarget = (Vertex) getElementFromClass(OntologyFactory
//					.getClassVertexElement(), (String) edge
//					.getValue(Edge.DST_PORT_NAME));
//			if (trueTarget != null) {
//				edge.setTarget(trueTarget);
//			} else {
//				edge.getTarget().setValue(PropertyBean.PROPERTY_NAME,
//						(String) edge.getValue(Edge.SRC_PORT_NAME));
//				edge.getTarget().setValue(Graph.PARAMETER_ID,
//						(String) edge.getValue(Edge.DST_PORT_NAME));
//				parentGraph.addVertex(edge.getTarget());
//			}
//		}
//	}
}
