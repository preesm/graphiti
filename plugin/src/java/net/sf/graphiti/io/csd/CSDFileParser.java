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
package net.sf.graphiti.io.csd;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import net.sf.graphiti.io.DomHelper;
import net.sf.graphiti.io.csd.ast.CSDChar;
import net.sf.graphiti.io.csd.ast.CSDNumber;
import net.sf.graphiti.io.csd.ast.Choice;
import net.sf.graphiti.io.csd.ast.Error;
import net.sf.graphiti.io.csd.ast.Reference;
import net.sf.graphiti.io.csd.ast.Sequence;
import net.sf.graphiti.io.csd.ast.SequenceOf;
import net.sf.graphiti.io.csd.ast.Type;
import net.sf.graphiti.io.csd.ast.Variable;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 * This class provides a parser for ASN.1 descriptions that are in a particular
 * XML format.
 * 
 * @author Matthieu Wipliez
 * 
 */
public class CSDFileParser {

	public static void main(String[] args) throws Exception {
		new CSDFileParser(args[0]);
	}

	private List<Type> types;

	public CSDFileParser(String csdFile) throws ClassCastException,
			ClassNotFoundException, CSDParseException, FileNotFoundException,
			IllegalAccessException, InstantiationException {
		types = new ArrayList<Type>();

		Document doc = DomHelper.parse(new FileInputStream(csdFile));
		Element docElt = doc.getDocumentElement();
		stripText(docElt);
		parseCSD(docElt.getFirstChild());
	}

	public List<Type> getTypes() {
		return types;
	}

	private Type parseByte(Element byteElt) {
		return parseNumber(byteElt, 1);
	}

	private Type parseChar(Element charElt) {
		String name = charElt.getAttribute("name");
		String value = charElt.getAttribute("value");
		return new CSDChar(name, value);
	}

	private Choice parseChoice(Element choiceElt) throws CSDParseException {
		Choice choice = new Choice(choiceElt.getAttribute("name"));

		Node node = choiceElt.getFirstChild();
		while (node != null) {
			Type type = parseType(node);
			choice.addAlternative(type);
			node = node.getNextSibling();
		}

		return choice;
	}

	private void parseCSD(Node node) throws CSDParseException {
		while (node != null) {
			Type type = parseType(node);
			types.add(type);

			node = node.getNextSibling();
		}
	}

	private Type parseError(Element errorElt) {
		String name = errorElt.getAttribute("name");
		return new Error(name);
	}

	private Type parseInt(Element intElt) {
		return parseNumber(intElt, 4);
	}

	private Type parseLong(Element longElt) {
		return parseNumber(longElt, 8);
	}

	private Type parseNumber(Element numberElt, int length) {
		String name = numberElt.getAttribute("name");
		String value = numberElt.getAttribute("value");
		return new CSDNumber(name, length, value);
	}

	private Type parseReference(Element referenceElt) {
		String name = referenceElt.getAttribute("name");
		String refName = referenceElt.getAttribute("to");
		return new Reference(name, refName);
	}

	private Sequence parseSequence(Element sequenceElt)
			throws CSDParseException {
		Sequence sequence = new Sequence(sequenceElt.getAttribute("name"));

		Node node = sequenceElt.getFirstChild();
		while (node != null) {
			Type type = parseType(node);
			sequence.addElement(type);
			node = node.getNextSibling();
		}

		return sequence;
	}

	private SequenceOf parseSequenceOf(Element sequenceOfElt)
			throws CSDParseException {
		String name = sequenceOfElt.getAttribute("name");
		String size = sequenceOfElt.getAttribute("size");
		SequenceOf sequenceOf = new SequenceOf(name, size);

		Node node = sequenceOfElt.getFirstChild();
		Type type = parseType(node);
		sequenceOf.setType(type);

		return sequenceOf;
	}

	private Type parseShort(Element shortElt) {
		return parseNumber(shortElt, 2);
	}

	private Type parseType(Node node) throws CSDParseException {
		String nodeName = node.getNodeName();
		Type type;
		if (nodeName.equals("byte")) {
			type = parseByte((Element) node);
		} else if (nodeName.equals("choice")) {
			type = parseChoice((Element) node);
		} else if (nodeName.equals("char")) {
			type = parseChar((Element) node);
		} else if (nodeName.equals("error")) {
			type = parseError((Element) node);
		} else if (nodeName.equals("int")) {
			type = parseInt((Element) node);
		} else if (nodeName.equals("long")) {
			type = parseLong((Element) node);
		} else if (nodeName.equals("reference")) {
			type = parseReference((Element) node);
		} else if (nodeName.equals("sequence")) {
			type = parseSequence((Element) node);
		} else if (nodeName.equals("sequenceOf")) {
			type = parseSequenceOf((Element) node);
		} else if (nodeName.equals("short")) {
			type = parseShort((Element) node);
		} else if (nodeName.equals("xsl:variable")) {
			type = parseVariable((Element) node);
		} else {
			throw new CSDParseException();
		}

		// set xsl:if if not empty
		String condition = ((Element) node).getAttribute("xsl:if");
		if (!condition.isEmpty()) {
			type.setCondition(condition);
		}

		return type;
	}

	private Type parseVariable(Element variableElt) {
		String name = variableElt.getAttribute("name");
		String select = variableElt.getAttribute("select");
		return new Variable(name, select);
	}

	private void stripText(Node node) {
		if (node.getNodeType() == Node.TEXT_NODE) {
			node.getParentNode().removeChild(node);
		} else {
			node = node.getFirstChild();
			while (node != null) {
				Node nextNode = node.getNextSibling();
				stripText(node);
				node = nextNode;
			}
		}
	}

}
