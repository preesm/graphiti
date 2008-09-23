/**
 * 
 */
package net.sf.graphiti.parsers;

import org.w3c.dom.Attr;
import org.w3c.dom.Comment;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

/**
 * 
 * 
 * @author Matthieu Wipliez
 * 
 */
public class DomHelper {

	/**
	 * Returns the first child of <code>element</code> which is a
	 * {@link org.w3c.dom.Element} (not {@link Text}, not {@link Comment}, etc.)
	 * 
	 * @param element
	 *            The parent DOM element.
	 * @return The first {@link org.w3c.dom.Element} child of
	 *         <code>element</code>.
	 */
	public static org.w3c.dom.Element getFirstChild(org.w3c.dom.Element element) {
		Node node = element.getFirstChild();
		while (node != null && node.getNodeType() != Node.ELEMENT_NODE) {
			node = node.getNextSibling();
		}

		return (org.w3c.dom.Element) node;
	}

	/**
	 * Returns the next sibling of <code>element</code> which is a
	 * {@link org.w3c.dom.Element} (not {@link Text}, not {@link Comment}, etc.)
	 * 
	 * @param element
	 *            A DOM element.
	 * @return The first {@link org.w3c.dom.Element} sibling of
	 *         <code>element</code>.
	 */
	public static org.w3c.dom.Element getNextSibling(org.w3c.dom.Element element) {
		Node node = element.getNextSibling();
		while (node != null && node.getNodeType() != Node.ELEMENT_NODE) {
			node = node.getNextSibling();
		}

		return (org.w3c.dom.Element) node;
	}

	/**
	 * Equivalent to node.isEqualNode(child), except it's working.
	 * 
	 * @param node
	 * @param child
	 * @return
	 */
	public static boolean isEqualNode(Node node, Node child) {
		if (node.getNodeType() != child.getNodeType()) {
			return false;
		}

		if (node.getNodeType() == Node.ATTRIBUTE_NODE) {
			Attr nodeAttr = (Attr) node;
			Attr childAttr = (Attr) child;
			if (nodeAttr.getName().equals(childAttr.getName())
					&& nodeAttr.getValue().equals(childAttr.getValue())) {
				return true;
			}
		} else if (node.getNodeType() == Node.TEXT_NODE) {
			return node.getNodeValue().equals(child.getNodeValue());
		} else if (node.getNodeType() == Node.ELEMENT_NODE) {
			// attributes
			NamedNodeMap nodeAttrs = node.getAttributes();
			NamedNodeMap childAttrs = child.getAttributes();
			if (nodeAttrs.getLength() != childAttrs.getLength()) {
				return false;
			}

			boolean res = true;
			for (int i = 0; i < nodeAttrs.getLength() && res; i++) {
				res = isEqualNode(nodeAttrs.item(i), childAttrs.item(i));
			}

			// children
			NodeList nodeChildren = node.getChildNodes();
			NodeList childChildren = child.getChildNodes();
			if (nodeChildren.getLength() != childChildren.getLength()) {
				return false;
			}

			for (int i = 0; i < nodeChildren.getLength() && res; i++) {
				res = isEqualNode(nodeChildren.item(i), childChildren.item(i));
			}

			return res;
		}

		return false;
	}

	public static Node stripWhitespace(Node node) {
		if (node.getNodeType() == Node.TEXT_NODE) {
			String value = node.getNodeValue();
			if (value.trim().isEmpty()) {
				return null;
			}
		} else {
			NodeList children = node.getChildNodes();
			for (int i = 0; i < children.getLength(); i++) {
				Node child = children.item(i);
				Node newChild = stripWhitespace(child);
				if (newChild == null) {
					node.removeChild(child);
				}
			}
		}

		return node;
	}
}
