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
import net.sf.graphiti.io.asn1.Constraint.ConstraintType;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class ASN1GrammarParser {

	private Item parseBitString(String name, Node node) {
		Node constraintsElt = DomHelper.getFirstSiblingNamed(node,
				"constraints");
		ConstraintList ct = parseConstraints(constraintsElt.getFirstChild());
		return new BitString(name, ct.getFirstValueConstraint().getValue());
	}

	private Choice parseChoice(String name, Node node) {
		Choice choice = new Choice(name);
		while (node != null) {
			if (node.getNodeName().equals("alternative")) {
				choice.addAlternative(parseElement((Element) node));
			}

			node = node.getNextSibling();
		}

		return choice;
	}

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

	private void parseConstraintSize(ConstraintList constraints, Element element) {
		Constraint constraint = new Constraint(ConstraintType.Size);
		Node size = DomHelper.getFirstChildNamed(element, "number");
		if (size != null) {
			BinaryNumber number = parseNumber((Element) size);
			constraint.setSize(number.intValue());
			constraints.add(constraint);
		}
	}

	private void parseConstraintValue(ConstraintList constraints,
			Element element) {
		Constraint constraint = new Constraint(ConstraintType.Value);
		NodeList numbers = element.getElementsByTagName("number");
		if (numbers.getLength() == 0) {
			NodeList strings = element.getElementsByTagName("string");
			Element value = (Element) strings.item(0);
			constraint.setString(parseString(value));
		} else if (numbers.getLength() == 1) {
			// one value
			Element numberElt = (Element) numbers.item(0);
			constraint.setValue(parseNumber(numberElt));
		} else if (numbers.getLength() == 2) {
			// a range from to
			Element lower = (Element) numbers.item(0);
			Element upper = (Element) numbers.item(1);
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
	 * @return An {@link Item}.
	 */
	private Item parseElement(Element domElement) {
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

	private Item parseInteger(String name, Node node) {
		Node constraints = DomHelper.getFirstSiblingNamed(node, "constraints");
		IntegerElement integer = new IntegerElement(name);
		ConstraintList ct = parseConstraints(constraints.getFirstChild());
		integer.setConstraints(ct);
		return integer;
	}

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

	private Sequence parseSequence(String name, Node node) {
		Sequence sequence = new Sequence(name);
		while (node != null) {
			if (node.getNodeName().equals("element")) {
				sequence.addElement(parseElement((Element) node));
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
		Element element = (Element) node;
		name = element.getAttribute("name");
		sequence.setType(parseType(name, node));

		return sequence;
	}

	private String parseString(Element stringElt) {
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
