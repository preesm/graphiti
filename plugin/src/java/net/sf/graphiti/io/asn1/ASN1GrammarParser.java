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
package net.sf.graphiti.io.asn1;

import java.util.ArrayList;
import java.util.List;

import net.sf.graphiti.io.DomHelper;
import net.sf.graphiti.io.asn1.ast.BinaryNumber;
import net.sf.graphiti.io.asn1.ast.BitString;
import net.sf.graphiti.io.asn1.ast.Choice;
import net.sf.graphiti.io.asn1.ast.Constraint;
import net.sf.graphiti.io.asn1.ast.ConstraintList;
import net.sf.graphiti.io.asn1.ast.IntegerItem;
import net.sf.graphiti.io.asn1.ast.Item;
import net.sf.graphiti.io.asn1.ast.ItemReference;
import net.sf.graphiti.io.asn1.ast.Production;
import net.sf.graphiti.io.asn1.ast.Sequence;
import net.sf.graphiti.io.asn1.ast.SequenceOf;
import net.sf.graphiti.io.asn1.ast.TypeReference;
import net.sf.graphiti.io.asn1.ast.Constraint.ConstraintType;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * This class provides a parser for ASN.1 descriptions that are in a particular
 * XML format.
 * 
 * @author Matthieu Wipliez
 * 
 */
public class ASN1GrammarParser {

	/**
	 * Returns a bit string (with the given name) from the given node.
	 * 
	 * @param name
	 *            The bit string name.
	 * @param node
	 *            The first child of a &lt;bitString&gt; node.
	 * @return A {@link BitString}.
	 */
	private BitString parseBitString(String name, Node node) {
		Node constraintsElt = DomHelper.getFirstSiblingNamed(node,
				"constraints");
		ConstraintList ct = parseConstraints(constraintsElt.getFirstChild());
		return new BitString(name, ct.getFirstValueConstraint());
	}

	/**
	 * Returns a choice (with the given name) from the given node.
	 * 
	 * @param name
	 *            The choice name.
	 * @param node
	 *            The first child of a &lt;choice&gt; node.
	 * @return A {@link Choice}.
	 */
	private Choice parseChoice(String name, Node node) {
		Choice choice = new Choice(name);
		while (node != null) {
			if (node.getNodeName().equals("alternative")) {
				choice.addAlternative(parseItem((Element) node));
			}

			node = node.getNextSibling();
		}

		return choice;
	}

	/**
	 * Returns a constraint list from the given node.
	 * 
	 * @param node
	 *            The first sibling of a &lt;constraint&gt; node.
	 * @return A {@link ConstraintList}, possibly empty.
	 */
	private ConstraintList parseConstraints(Node node) {
		ConstraintList constraints = new ConstraintList();
		while (node != null) {
			if (node.getNodeName().equals("constraint")) {
				Element element = (Element) node;
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

	/**
	 * Adds a size constraint parsed from the given element to the given
	 * constraint list.
	 * 
	 * @param constraints
	 *            A {@link ConstraintList}.
	 * @param element
	 *            A &lt;constraint type="size"&gt; element.
	 */
	private void parseConstraintSize(ConstraintList constraints, Element element) {
		Constraint constraint = new Constraint(ConstraintType.Size);
		Node size = DomHelper.getFirstChildNamed(element, "number");
		if (size != null) {
			constraint.setSize(parseNumber((Element) size));
			constraints.add(constraint);
		}
	}

	/**
	 * Adds a value constraint parsed from the given element to the given
	 * constraint list.
	 * 
	 * @param constraints
	 *            A {@link ConstraintList}.
	 * @param element
	 *            A &lt;constraint type="value"&gt; element.
	 */
	private void parseConstraintValue(ConstraintList constraints,
			Element element) {
		Constraint constraint = new Constraint(ConstraintType.Value);
		NodeList numbers = element.getElementsByTagName("number");
		if (numbers.getLength() == 0) {
			Node node = element.getFirstChild();
			while (node != null) {
				if (node.getNodeName().equals("identifier")) {
					constraint.setValue(parseIdentifier((Element) node));
					break;
				} else if (node.getNodeName().equals("string")) {
					constraint.setValue(parseString((Element) node));
					break;
				}
			}
		} else if (numbers.getLength() == 1) {
			// one value
			Element numberElt = (Element) numbers.item(0);
			constraint.setValue(parseNumber(numberElt));
		} else if (numbers.getLength() == 2) {
			// a range from to
			Element lower = (Element) numbers.item(0);
			Element upper = (Element) numbers.item(1);
			BinaryNumber[] bounds = new BinaryNumber[] { parseNumber(lower),
					parseNumber(upper) };
			constraint.setValue(bounds);
		}

		constraints.add(constraint);
	}

	/**
	 * Parses the given DOM element and returns one of:
	 * <ul>
	 * <li>{@link BitString}</li>
	 * <li>{@link IntegerItem}</li>
	 * <li>{@link TypeReference}</li>
	 * </ul>
	 * 
	 * @param domElement
	 *            A DOM element, either &lt;alternative&gt; or &lt;element&gt;
	 * @return An {@link Item}.
	 */
	private Item parseItem(Element domElement) {
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

	/**
	 * Returns an item reference from the given element.
	 * 
	 * @param stringElt
	 *            An &lt;identifier&gt; element.
	 * @return A {@link String}.
	 */
	private ItemReference parseIdentifier(Element identifier) {
		return new ItemReference(identifier.getAttribute("value"));
	}

	/**
	 * Returns an integer (with the given name) from the given node.
	 * 
	 * @param name
	 *            The choice name.
	 * @param node
	 *            The first child of a &lt;integer&gt; node.
	 * @return An {@link IntegerItem}.
	 */
	private IntegerItem parseInteger(String name, Node node) {
		Node constraints = DomHelper.getFirstSiblingNamed(node, "constraints");
		IntegerItem integer = new IntegerItem(name);
		ConstraintList ct = parseConstraints(constraints.getFirstChild());
		integer.setConstraints(ct);
		return integer;
	}

	/**
	 * Returns a number from the given element.
	 * 
	 * @param numberElt
	 *            A &lt;number&gt; element.
	 * @return A {@link BinaryNumber}.
	 */
	private BinaryNumber parseNumber(Element numberElt) {
		String length = numberElt.getAttribute("length");
		BinaryNumber number = null;
		if (length.isEmpty()) {
			number = new BinaryNumber();
		} else {
			number = new BinaryNumber(Integer.parseInt(length));
		}

		if (numberElt.getAttribute("type").equals("decimal")) {
			number.setValue(numberElt.getAttribute("value"), 10);
		} else if (numberElt.getAttribute("type").equals("hexadecimal")) {
			number.setValue(numberElt.getAttribute("value"), 16);
		}

		return number;
	}

	/**
	 * Returns a production from the given element.
	 * 
	 * @param production
	 *            A &lt;production&gt; element.
	 * @return A {@link Production}.
	 */
	private Production parseProduction(Element production) {
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

	/**
	 * Returns a list of productions from the given node.
	 * 
	 * @param definition
	 *            A &lt;definition&gt; node.
	 * @return A {@link List}&lt;{@link Production}&gt;.
	 */
	public List<Production> parseProductions(Node definition) {
		List<Production> productions = new ArrayList<Production>();

		Node node = definition.getFirstChild();
		while (node != null) {
			if (node.getNodeName().equals("production")) {
				productions.add(parseProduction((Element) node));
			}

			node = node.getNextSibling();
		}

		return productions;
	}

	/**
	 * Returns a sequence (with the given name) from the given node.
	 * 
	 * @param name
	 *            The sequence name.
	 * @param node
	 *            The first child of a &lt;sequence&gt; node.
	 * @return A {@link Sequence}.
	 */
	private Sequence parseSequence(String name, Node node) {
		Sequence sequence = new Sequence(name);
		while (node != null) {
			if (node.getNodeName().equals("element")) {
				sequence.addElement(parseItem((Element) node));
			}

			node = node.getNextSibling();
		}

		return sequence;
	}

	/**
	 * Returns a "sequence of" (with the given name) from the given node.
	 * 
	 * @param name
	 *            The "sequence of" name.
	 * @param node
	 *            The first child of a &lt;sequenceOf&gt; node.
	 * @return A {@link SequenceOf}.
	 */
	private SequenceOf parseSequenceOf(String name, Node node) {
		SequenceOf sequence = new SequenceOf(name);

		// set size if available
		ConstraintList ct = parseConstraints(node);
		Constraint size = ct.getFirstSizeConstraint();
		if (size != null) {
			sequence.setSize(size);
		}

		// set sequence type
		node = DomHelper.getFirstSiblingNamed(node, "type");
		Element element = (Element) node;
		name = element.getAttribute("name");
		sequence.setType(parseType(name, node));

		return sequence;
	}

	/**
	 * Returns a string from the given element.
	 * 
	 * @param stringElt
	 *            A &lt;string&gt; element.
	 * @return A {@link String}.
	 */
	private String parseString(Element stringElt) {
		return new String(stringElt.getAttribute("value"));
	}

	/**
	 * Returns a type reference (with the given name) from the given node.
	 * 
	 * @param name
	 *            The type reference name.
	 * @param node
	 *            The first child of a &lt;type&gt; node.
	 * @return A {@link TypeReference}.
	 */
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
