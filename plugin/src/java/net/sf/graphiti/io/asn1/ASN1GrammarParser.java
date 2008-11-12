package net.sf.graphiti.io.asn1;

import java.util.ArrayList;
import java.util.List;

import net.sf.graphiti.io.DomHelper;
import net.sf.graphiti.io.asn1.Constraint.ConstraintType;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class ASN1GrammarParser {

	private Element parseBitString(String name, Node node) {
		Node constraintsElt = DomHelper.getFirstSiblingNamed(node,
				"constraints");
		ConstraintList ct = parseConstraints(constraintsElt.getFirstChild());
		return new BitString(name, ct.getFirstValueConstraint().getValue());
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

	private ConstraintList parseConstraints(Node node) {
		ConstraintList constraints = new ConstraintList();
		while (node != null) {
			if (node.getNodeName().equals("constraint")) {
				org.w3c.dom.Element element = (org.w3c.dom.Element) node;
				if (element.getAttribute("type").equals("size")) {
					parseConstraintSize(constraints, element);
				} else if (element.getAttribute("type").equals("value")) {
					parseConstraintValue(constraints, element);
				}
			}

			node = node.getNextSibling();
		}

		return constraints;
	}

	private void parseConstraintSize(ConstraintList constraints,
			org.w3c.dom.Element element) {
		Constraint constraint = new Constraint(ConstraintType.Size);
		Node size = DomHelper.getFirstChildNamed(element, "number");
		if (size != null) {
			Number number = parseNumber((org.w3c.dom.Element) size);
			constraint.setSize(number.intValue());
			constraints.add(constraint);
		}
	}

	private void parseConstraintValue(ConstraintList constraints,
			org.w3c.dom.Element element) {
		Constraint constraint = new Constraint(ConstraintType.Value);
		NodeList numbers = element.getElementsByTagName("number");
		if (numbers.getLength() == 0) {
			NodeList strings = element.getElementsByTagName("string");
			org.w3c.dom.Element value = (org.w3c.dom.Element) strings.item(0);
			constraint.setString(parseString(value));
		} else if (numbers.getLength() == 1) {
			// one value
			org.w3c.dom.Element numberElt = (org.w3c.dom.Element) numbers
					.item(0);
			constraint.setValue(parseNumber(numberElt));
		} else if (numbers.getLength() == 2) {
			// a range from to
			org.w3c.dom.Element lower = (org.w3c.dom.Element) numbers.item(0);
			org.w3c.dom.Element upper = (org.w3c.dom.Element) numbers.item(1);
			constraint.setLowerBound(parseNumber(lower));
			constraint.setUpperBound(parseNumber(upper));
		}

		constraints.add(constraint);
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
			} else if (node.getNodeName().equals("integer")) {
				return parseInteger(name, node.getFirstChild());
			} else if (node.getNodeName().equals("type")) {
				return parseType(name, node.getFirstChild());
			}

			node = node.getNextSibling();
		}

		return null;
	}

	private Element parseInteger(String name, Node node) {
		Node constraints = DomHelper.getFirstSiblingNamed(node, "constraints");
		IntegerElement integer = new IntegerElement(name);
		ConstraintList ct = parseConstraints(constraints.getFirstChild());
		integer.setConstraints(ct);
		return integer;
	}

	private Number parseNumber(org.w3c.dom.Element numberElt) {
		Number number = null;
		if (numberElt.getAttribute("type").equals("decimal")) {
			number = new Number(32);
			number.setValue(numberElt.getAttribute("value"), 10);
		} else if (numberElt.getAttribute("type").equals("hexadecimal")) {
			int length = Integer.parseInt(numberElt.getAttribute("length"));
			number = new Number(length);
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

		// set size if available
		ConstraintList ct = parseConstraints(node);
		Constraint size = ct.getFirstSizeConstraint();
		if (size != null) {
			sequence.setSize(size.getValue().intValue());
		}

		// set sequence type
		node = DomHelper.getFirstSiblingNamed(node, "type");
		org.w3c.dom.Element element = (org.w3c.dom.Element) node;
		name = element.getAttribute("name");
		sequence.setType(parseType(name, node));

		return sequence;
	}

	private String parseString(org.w3c.dom.Element stringElt) {
		return new String(stringElt.getAttribute("value"));
	}

	private TypeReference parseType(String name, Node node) {
		TypeReference type = new TypeReference(name);
		Node constraints = DomHelper.getFirstSiblingNamed(node, "constraints");
		if (constraints != null) {
			ConstraintList ct = parseConstraints(constraints.getFirstChild());
			type.setConstraints(ct);
		}
		return type;
	}
}
