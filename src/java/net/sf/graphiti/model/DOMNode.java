/**
 * 
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
	public void addAttribute(DOMNode attribute) {
		attributes.add(attribute);
	}

	/**
	 * Add an element to this DOMNode.
	 * 
	 * @param element
	 *            The element.
	 */
	public void addElement(DOMNode element) {
		elements.add(element);
	}

	/**
	 * Returns the list of the DOM attributes of this PropertyBean.
	 * 
	 * @return The list of the DOM attributes of this PropertyBean.
	 */
	public List<DOMNode> getAttributes() {
		return new ArrayList<DOMNode>(attributes);
	}

	/**
	 * Gives this Node's attribute value
	 * @param attributeName the attribute's name
	 * @return the value of the attribute
	 */
	public String getAttributeValue(String attributeName){
		for(DOMNode node : attributes){
			if(node.getNodeName().equals(attributeName)){
				return node.getNodeValue();
			}
		}
		return null ;
	}

	/**
	 * Returns the list of DOM elements in this property bean.
	 * 
	 * @return The list of DOM elements in this property bean.
	 */
	public List<DOMNode> getElements() {
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
		return nodeName + ": " + nodeValue + " " + getAttributes() + " ";
	}

}
