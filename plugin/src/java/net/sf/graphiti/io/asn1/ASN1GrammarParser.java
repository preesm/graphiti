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
import net.sf.graphiti.io.asn1.ast.IntegerType;
import net.sf.graphiti.io.asn1.ast.ItemReference;
import net.sf.graphiti.io.asn1.ast.Production;
import net.sf.graphiti.io.asn1.ast.Sequence;
import net.sf.graphiti.io.asn1.ast.SequenceOf;
import net.sf.graphiti.io.asn1.ast.Type;
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
	 * Returns a choice from the given node.
	 * 
	 * @param node
	 *            The first child of a &lt;choice&gt; node.
	 * @return A {@link Choice}.
	 */
	private Choice parseChoice(Node node) {
		Choice choice = new Choice();
		while (node != null) {
			if (node.getNodeName().equals("alternative")) {
				choice.addAlternative(parseType(node.getFirstChild()));
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
					Constraint constraint = parseConstraintSize(element);
					constraints.add(constraint);
				} else if (element.getAttribute("type").equals("value")) {
					Constraint constraint = parseConstraintValue(element);
					constraints.add(constraint);
				}
			}

			node = node.getNextSibling();
		}

		return constraints;
	}

	/**
	 * Returns a size constraint parsed from the given element.
	 * 
	 * @param element
	 *            A &lt;constraint type="size"&gt; element.
	 * @return A {@link Constraint}.
	 */
	private Constraint parseConstraintSize(Element element) {
		Constraint constraint = new Constraint(ConstraintType.Size);
		Node size = DomHelper.getFirstChildNamed(element, "number");
		if (size == null) {
			Node node = DomHelper.getFirstChildNamed(element, "identifier");
			if (node != null) {
				constraint.setSize(parseIdentifier((Element) node));
			}
		} else {
			constraint.setSize(parseNumber((Element) size));
		}

		return constraint;
	}

	/**
	 * Returns a value constraint parsed from the given element.
	 * 
	 * @param element
	 *            A &lt;constraint type="value"&gt; element.
	 * @return A {@link Constraint}.
	 */
	private Constraint parseConstraintValue(Element element) {
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

		return constraint;
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
	 * @return An {@link IntegerType}.
	 */
	private IntegerType parseInteger(String name, Node node) {
		Node constraints = DomHelper.getFirstSiblingNamed(node, "constraints");
		IntegerType integer = new IntegerType(name);
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
	private Production parseProduction(Element element) {
		String name = element.getAttribute("name");
		Production production = new Production(name);
		production.setType(parseType(element.getFirstChild()));
		return production;
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
	 * Returns a sequence from the given node.
	 * 
	 * @param node
	 *            The first child of a &lt;sequence&gt; node.
	 * @return A {@link Sequence}.
	 */
	private Sequence parseSequence(Node node) {
		Sequence sequence = new Sequence();
		while (node != null) {
			if (node.getNodeName().equals("element")) {
				sequence.addElement(parseType(node.getFirstChild()));
			}

			node = node.getNextSibling();
		}

		return sequence;
	}

	/**
	 * Returns a "sequence of" from the given node.
	 * 
	 * @param node
	 *            The first child of a &lt;sequenceOf&gt; node.
	 * @return A {@link SequenceOf}.
	 */
	private SequenceOf parseSequenceOf(Node node) {
		SequenceOf sequence = new SequenceOf();

		// set size if available
		ConstraintList ct = parseConstraints(node);
		Constraint size = ct.getFirstSizeConstraint();
		if (size != null) {
			sequence.setSize(size);
		}

		// set sequence type
		sequence.setType(parseType(node));
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
	 * Parses the given DOM node and returns a type.
	 * 
	 * @param node
	 *            A DOM element, either &lt;bitString&gt;, &lt;choice&gt;,
	 *            &lt;integer&gt;, &lt;sequence&gt;, &lt;sequenceOf&gt;,
	 *            &lt;type&gt;.
	 * @return A {@link Type}.
	 */
	private Type parseType(Node node) {
		while (node != null) {
			if (node.getNodeName().equals("bitString")) {
				String name = ((Element) node).getAttribute("name");
				return parseBitString(name, node.getFirstChild());
			} else if (node.getNodeName().equals("choice")) {
				return parseChoice(node.getFirstChild());
			} else if (node.getNodeName().equals("integer")) {
				String name = ((Element) node).getAttribute("name");
				return parseInteger(name, node.getFirstChild());
			} else if (node.getNodeName().equals("sequence")) {
				return parseSequence(node.getFirstChild());
			} else if (node.getNodeName().equals("sequenceOf")) {
				return parseSequenceOf(node.getFirstChild());
			} else if (node.getNodeName().equals("type")) {
				String name = ((Element) node).getAttribute("name");
				return parseTypeReference(name, (Element) node);
			}

			node = node.getNextSibling();
		}

		return null;
	}

	/**
	 * Returns a type reference to a type with the given name from the given
	 * node.
	 * 
	 * @param name
	 *            The type referenced name.
	 * @param node
	 *            A &lt;type&gt; element.
	 * @return A {@link TypeReference}.
	 */
	private TypeReference parseTypeReference(String name, Element type) {
		TypeReference typeRef = new TypeReference("");
		typeRef.setReferenceName(type.getAttribute("name"));
		Node constraints = DomHelper.getFirstSiblingNamed(type, "constraints");
		if (constraints != null) {
			ConstraintList ct = parseConstraints(constraints.getFirstChild());
			typeRef.setConstraints(ct);
		}
		return typeRef;
	}
}
