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
package net.sf.graphiti.model;

import java.util.ArrayList;
import java.util.List;

/**
 * This class extends a property bean with the ability to store attributes and
 * elements from a DOM tree.
 * 
 * @author Jonathan Piat
 * @author Matthieu Wipliez
 * 
 */
public class DOMNode extends PropertyBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * List containing attributes from the DOM.
	 */
	private List<DOMNode> attributes;

	/**
	 * List containing elements from the DOM.
	 */
	private List<DOMNode> elements;

	/**
	 * The property name in the DOM tree.
	 */
	private String nodeName;

	/**
	 * The property value in the DOM tree.
	 */
	private String nodeValue;

	/**
	 * Creates a new DOM node.
	 */
	public DOMNode() {
		elements = new ArrayList<DOMNode>();
		attributes = new ArrayList<DOMNode>();
	}

	/**
	 * Creates a new DOM node with the given name.
	 * 
	 * @param name
	 *            The node name.
	 */
	public DOMNode(String name) {
		this();
		nodeName = name;
	}

	/**
	 * Add an attribute to this DOMNode.
	 * 
	 * @param attribute
	 *            The attribute.
	 */
	public void addDOMAttribute(DOMNode attribute) {
		attributes.add(attribute);
	}

	/**
	 * Add an element to this DOMNode.
	 * 
	 * @param element
	 *            The element.
	 */
	public void addDOMElement(DOMNode element) {
		elements.add(element);
	}

	/**
	 * Returns the list of the DOM attributes of this PropertyBean.
	 * 
	 * @return The list of the DOM attributes of this PropertyBean.
	 */
	public List<DOMNode> getDOMAttributes() {
		return new ArrayList<DOMNode>(attributes);
	}

	/**
	 * Gives this Node's attribute value
	 * 
	 * @param attributeName
	 *            the attribute's name
	 * @return the value of the attribute
	 */
	public String getDOMAttributeValue(String attributeName) {
		for (DOMNode node : attributes) {
			if (node.getNodeName().equals(attributeName)) {
				return node.getNodeValue();
			}
		}
		return null;
	}

	/**
	 * Returns the list of DOM elements in this property bean.
	 * 
	 * @return The list of DOM elements in this property bean.
	 */
	public List<DOMNode> getDOMElements() {
		return new ArrayList<DOMNode>(elements);
	}

	/**
	 * Returns the node name.
	 * 
	 * @return The node name.
	 */
	public String getNodeName() {
		return nodeName;
	}

	/**
	 * Returns the node value.
	 * 
	 * @return The node value.
	 */
	public String getNodeValue() {
		return nodeValue;
	}

	/**
	 * Sets the node name.
	 * 
	 * @param name
	 *            The new node name.
	 */
	public void setNodeName(String name) {
		this.nodeName = name;
	}

	/**
	 * Sets the node value.
	 * 
	 * @param value
	 *            The new node value.
	 */
	public void setNodeValue(String value) {
		this.nodeValue = value;
	}

	public String toString() {
		return nodeName + ": " + nodeValue + " " + getDOMAttributes() + " ";
	}

}
