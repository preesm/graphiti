package net.sf.graphiti.io.asn1;

import java.util.ArrayList;
import java.util.List;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import net.sf.graphiti.io.DomHelper;

import org.w3c.dom.Node;

public class ASN1GrammarParser {

	XPathExpression constraintValue;

	public ASN1GrammarParser() {
		XPathFactory factory = XPathFactory.newInstance();
		XPath path = factory.newXPath();
		try {
			constraintValue = path
					.compile("constraints/constraint[@type = 'value'][1]");
		} catch (XPathExpressionException e) {
			e.printStackTrace();
		}
	}

	private Choice parseChoice(String name, Node node) {
		Choice choice = new Choice(name);
		while (node != null) {
			if (node.getNodeName().equals("alternative")) {
				choice.addAlternative(parseElement((org.w3c.dom.Element) node));
			}

			node = node.getNextSibling();
		}

		return choice;
	}

	/**
	 * Parses the given DOM element and returns one of:
	 * <ul>
	 * <li>BitString</li>
	 * <li>Integer</li>
	 * <li>...</li>
	 * </ul>
	 * 
	 * @param domElement
	 *            A DOM element, either &lt;alternative&gt; or &lt;element&gt;
	 * @return An {@link Element}.
	 */
	private Element parseElement(org.w3c.dom.Element domElement) {
		String name = domElement.getAttribute("name");
		Node node = domElement.getFirstChild();
		while (node != null) {
			if (node.getNodeName().equals("bitString")) {
				return parseBitString(name, node.getFirstChild());
			}

			node = node.getNextSibling();
		}

		return null;
	}

	private Element parseBitString(String name, Node node) {
		Node constraints = DomHelper.getFirstSiblingNamed(node, "constraints");
		node = constraints.getFirstChild();
		while (node != null) {
			if (node.getNodeName().equals("constraint")) {
				org.w3c.dom.Element element = (org.w3c.dom.Element) node;
				if (element.getAttribute("type").equals("value")) {
					Node child = node.getFirstChild();
					while (child != null) {
						if (child.getNodeName().equals("number")) {
							Number number = parseNumber((org.w3c.dom.Element) child);
							return new BitString(name, number);
						}

						child = child.getNextSibling();
					}
				}
			}

			node = node.getNextSibling();
		}

		return null;
	}

	private Number parseNumber(org.w3c.dom.Element numberElt) {
		int length = Integer.parseInt(numberElt.getAttribute("length"));
		Number number = new Number(length);
		if (numberElt.getAttribute("type").equals("decimal")) {
			number.setValue(numberElt.getAttribute("value"), 10);
		} else if (numberElt.getAttribute("type").equals("hexadecimal")) {
			number.setValue(numberElt.getAttribute("value"), 16);
		}
		return number;
	}

	private Production parseProduction(org.w3c.dom.Element production) {
		String name = production.getAttribute("name");
		Node node = production.getFirstChild();
		while (node != null) {
			if (node.getNodeName().equals("choice")) {
				return parseChoice(name, node.getFirstChild());
			} else if (node.getNodeName().equals("sequence")) {
				return parseSequence(name, node.getFirstChild());
			} else if (node.getNodeName().equals("sequenceOf")) {
				return parseSequenceOf(name, node.getFirstChild());
			} else {
				node = node.getNextSibling();
			}
		}

		return null;
	}

	public List<Production> parseProductions(Node definition) {
		List<Production> productions = new ArrayList<Production>();

		Node node = definition.getFirstChild();
		while (node != null) {
			if (node.getNodeName().equals("production")) {
				productions.add(parseProduction((org.w3c.dom.Element) node));
			}

			node = node.getNextSibling();
		}

		return productions;
	}

	private Sequence parseSequence(String name, Node node) {
		Sequence sequence = new Sequence(name);
		while (node != null) {
			if (node.getNodeName().equals("element")) {
				sequence.addElement(parseElement((org.w3c.dom.Element) node));
			}

			node = node.getNextSibling();
		}

		return sequence;
	}

	private SequenceOf parseSequenceOf(String name, Node node) {
		SequenceOf sequence = new SequenceOf(name);
		while (node != null) {
			if (node.getNodeName().equals("type")) {

			}

			node = node.getNextSibling();
		}

		return sequence;
	}
}
