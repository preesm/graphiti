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

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.math.BigInteger;
import java.util.ArrayDeque;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.xml.namespace.QName;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import javax.xml.xpath.XPathFactoryConfigurationException;
import javax.xml.xpath.XPathVariableResolver;

import net.sf.graphiti.io.DomHelper;
import net.sf.graphiti.io.csd.ast.CSDNumber;
import net.sf.graphiti.io.csd.ast.CSDVisitor;
import net.sf.graphiti.io.csd.ast.Choice;
import net.sf.graphiti.io.csd.ast.Error;
import net.sf.graphiti.io.csd.ast.ForEach;
import net.sf.graphiti.io.csd.ast.LongUTF8String;
import net.sf.graphiti.io.csd.ast.Reference;
import net.sf.graphiti.io.csd.ast.Sequence;
import net.sf.graphiti.io.csd.ast.SequenceOf;
import net.sf.graphiti.io.csd.ast.Type;
import net.sf.graphiti.io.csd.ast.UTF8String;
import net.sf.graphiti.io.csd.ast.Variable;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class CSDParser implements CSDVisitor, XPathVariableResolver {

	public static void main(String[] args) throws Exception {
		new CSDParser(args[0], args[1]);
	}

	private Document document;

	private XPathFactory factory;

	private RandomAccessFile in;

	private ArrayDeque<Element> nodeStack;

	private Map<String, Object> variables;

	public CSDParser(String csdFile, String binFile) throws ClassCastException,
			ClassNotFoundException, CSDFileParseException,
			IllegalAccessException, InstantiationException, IOException,
			XPathFactoryConfigurationException, XPathExpressionException {
		variables = new TreeMap<String, Object>();

		// parse the Concrete Syntax Description
		List<Type> types = new CSDFileParser(csdFile).getTypes();
		new TypeReferenceVisitor(types);

		// initialize XPath factory
		factory = XPathFactory.newInstance(
				XPathFactory.DEFAULT_OBJECT_MODEL_URI,
				"net.sf.saxon.xpath.XPathFactoryImpl", null);
		factory.setXPathVariableResolver(this);

		// create result document
		document = DomHelper.createDocument("", "ConcreteSyntaxTree");

		// initialize node stack
		nodeStack = new ArrayDeque<Element>();
		nodeStack.push(document.getDocumentElement());

		// let's go!
		in = new RandomAccessFile(binFile, "r");
		try {
			types.get(0).accept(this);
		} catch (CSDParseException e) {
			printParseTree();
			e.printStackTrace();
		}
	}

	private Element begin(Type type) throws CSDParseException {
		String condition = type.getCondition();
		if (!condition.isEmpty()) {
			if (!evaluateXPathBoolean(condition)) {
				throw new CSDParseException(condition + " evaluated to false");
			}
		}
		
		Element typeElt = document.createElement(type.getName());
		nodeStack.peek().appendChild(typeElt);
		nodeStack.push(typeElt);
		return typeElt;
	}

	private void end() {
		nodeStack.pop();
	}

	private Object evaluateXPath(String expression, QName returnType) {
		XPath path = factory.newXPath();
		try {
			Element context = nodeStack.peek();
			Object result = path.evaluate(expression, context, returnType);
			return result;
		} catch (XPathExpressionException e) {
			e.printStackTrace();
			return null;
		}
	}

	private boolean evaluateXPathBoolean(String expression) {
		return (Boolean) evaluateXPath(expression, XPathConstants.BOOLEAN);
	}

	private int evaluateXPathInt(String expression) {
		return ((Double) evaluateXPath(expression, XPathConstants.NUMBER))
				.intValue();
	}

	private NodeList evaluateXPathNodeList(String expression) {
		return (NodeList) evaluateXPath(expression, XPathConstants.NODESET);
	}

	private String evaluateXPathString(String expression) {
		return (String) evaluateXPath(expression, XPathConstants.STRING);
	}

	private void printParseTree() {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		DomHelper.write(document, bos);
		System.out.println(bos.toString());
	}

	@Override
	public Object resolveVariable(QName variableName) {
		Object value = variables.get(variableName.getLocalPart());
		return value;
	}

	private void revert(Element elt) {
		// pop elements
		Element stackElt;
		do {
			stackElt = nodeStack.pop();
		} while (stackElt != elt);

		// remove children
		Node node = elt.getFirstChild();
		while (node != null) {
			elt.removeChild(node);
			node = node.getNextSibling();
		}

		// remove itself
		elt.getParentNode().removeChild(elt);
	}

	@Override
	public void visit(Choice choice) throws CSDParseException {
		Element choiceElt = begin(choice);

		try {
			long fp = in.getFilePointer();
			for (Type type : choice.getAlternatives()) {
				try {
					type.accept(this);
					if (type instanceof Variable) {
						continue;
					}
					end();
					return;
				} catch (CSDParseException e) {
					// something wrong when parsing this type: wrong rule
					in.seek(fp);
				}
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		revert(choiceElt);
		throw new CSDParseException("Parse error");
	}

	@Override
	public void visit(CSDNumber csdNumber) throws CSDParseException {
		Element csdElt = begin(csdNumber);

		int length = csdNumber.getLength();
		byte[] bytes = new byte[length];
		try {
			in.read(bytes);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		if (csdNumber.hasValue() && !csdNumber.match(bytes)) {
			revert(csdElt);
			throw new CSDParseException(new MatchException());
		}

		// register the variable and set the value
		String strValue = new BigInteger(1, bytes).toString();
		System.out.println("Setting number value of: " + csdNumber + " to: "
				+ strValue);
		nodeStack.peek().setAttribute("value", strValue);

		end();
	}

	@Override
	public void visit(Error error) {
		throw new RuntimeException("TODO");
	}

	@Override
	public void visit(ForEach forEach) throws CSDParseException {
		printParseTree();

		begin(forEach);
		String select = forEach.getSelect();
		Type type = forEach.getType();
		NodeList list = evaluateXPathNodeList(select);
		int n = list.getLength();
		for (int i = 0; i < n; i++) {
			Element context = (Element) list.item(i);
			variables.put("context", context);
			type.accept(this);
		}

		end();
	}

	@Override
	public void visit(LongUTF8String utf8String) {
		throw new RuntimeException("TODO");
	}

	@Override
	public void visit(Reference reference) throws CSDParseException {
		reference.getReference().accept(this);
	}

	@Override
	public void visit(Sequence sequence) throws CSDParseException {
		Element element = begin(sequence);
		try {
			for (Type type : sequence.getElements()) {
				type.accept(this);
			}
		} catch (CSDParseException e) {
			revert(element);
			throw e;
		}
		end();
	}

	@Override
	public void visit(SequenceOf sequenceOf) throws CSDParseException {
		begin(sequenceOf);
		String size = sequenceOf.getSize();
		Type type = sequenceOf.getType();
		if (size.isEmpty()) {
			try {
				while (true) {
					type.accept(this);
				}
			} catch (CSDParseException e) {
			}
		} else {
			int n = evaluateXPathInt(size);
			for (int i = 0; i < n; i++) {
				type.accept(this);
			}
		}

		end();
	}

	@Override
	public void visit(UTF8String utf8String) throws CSDParseException {
		begin(utf8String);
		try {
			String strValue = in.readUTF();
			System.out.println("Parsing UTF-8: \"" + strValue + "\"");
			nodeStack.peek().setAttribute("value", strValue);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		end();
	}

	@Override
	public void visit(Variable variable) {
		String varName = variable.getName();
		String select = variable.getSelect();
		String value = evaluateXPathString(select);
		variables.put(varName, value);
	}

}
