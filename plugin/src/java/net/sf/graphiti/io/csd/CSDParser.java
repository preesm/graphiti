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
import net.sf.graphiti.io.csd.ast.Token;
import net.sf.graphiti.io.csd.ast.Type;
import net.sf.graphiti.io.csd.ast.UTF8String;
import net.sf.graphiti.io.csd.ast.Variable;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class CSDParser implements CSDVisitor, XPathVariableResolver {

	public static void main(String[] args) throws Exception {
		new CSDParser(args[0], args[1]);

		// ObjectInputStream oin = new ObjectInputStream(new FileInputStream(
		// args[1]));
		// Object obj = oin.readObject();
		// System.out.println(obj);
	}

	private Document document;

	private XPathFactory factory;

	private RandomAccessFile in;

	private String indent;

	private ArrayDeque<Element> nodeStack;

	private Map<String, Object> variables;

	public CSDParser(String csdFile, String binFile) throws ClassCastException,
			ClassNotFoundException, CSDFileParseException,
			IllegalAccessException, InstantiationException, IOException,
			XPathFactoryConfigurationException, XPathExpressionException {
		indent = "";
		variables = new TreeMap<String, Object>();

		// parse the Concrete Syntax Description
		List<Type> types = new CSDFileParser(csdFile).getTypes();
		new TypeReferenceVisitor(types);
		new FirstSetVisitor(types);

		// initialize XPath factory
		factory = XPathFactory.newInstance(
				XPathFactory.DEFAULT_OBJECT_MODEL_URI,
				"net.sf.saxon.xpath.XPathFactoryImpl", null);
		// factory.setXPathFunctionResolver(this);
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
		} catch (Throwable e) {
			e.printStackTrace();
		}

		printParseTree();
		long fp = in.getFilePointer();
		System.out.println(fp);
	}

	private void begin(Type type) throws CSDParseException {
		String condition = type.getCondition();
		if (!condition.isEmpty()) {
			if (!evaluateXPathBoolean(condition)) {
				throw new CSDParseException(condition + " evaluated to false");
			}
		}

		Element typeElt = document.createElement(type.getName());
		nodeStack.peek().appendChild(typeElt);
		nodeStack.push(typeElt);

		System.out.println(indent + "Parsing " + typeElt.getNodeName());
		indent += "  ";
	}

	private void end() {
		Element node = nodeStack.pop();
		indent = indent.substring(2);
		System.out.println(indent + "Finished parsing " + node.getNodeName());
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

	/**
	 * Returns true if the given type is a valid alternative. This method is a
	 * helper for {@link #visit(Choice)}.
	 * 
	 * @param type
	 *            A {@link Type}.
	 * @return True if the given type is a valid alternative, false otherwise.
	 */
	private boolean isValid(Type type) {
		// test condition
		String condition = type.getCondition();
		if (!condition.isEmpty()) {
			return evaluateXPathBoolean(condition);
		}

		// if no token specified, assume it's ok
		if (type.getFirst().isEmpty()) {
			return true;
		}

		// test FIRST
		try {
			long fp = in.getFilePointer();

			for (Token token : type.getFirst()) {
				if (token == Token.epsilon) {
					return true;
				} else {
					CSDNumber csdNumber = token.getValue();
					int length = csdNumber.getLength();
					byte[] bytes = new byte[length];
					in.read(bytes);
					in.seek(fp);

					if (csdNumber.hasValue() && csdNumber.match(bytes)) {
						return true;
					}
				}
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		return false;
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

	@Override
	public void visit(Choice choice) throws CSDParseException {
		begin(choice);

		for (Type type : choice.getAlternatives()) {
			if (type instanceof Variable) {
				type.accept(this);
			} else if (isValid(type)) {
				type.accept(this);
				end();
				return;
			}
		}

		throw new CSDParseException("Parse error");
	}

	@Override
	public void visit(CSDNumber csdNumber) throws CSDParseException {
		begin(csdNumber);

		int length = csdNumber.getLength();
		byte[] bytes = new byte[length];
		try {
			in.read(bytes);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		if (csdNumber.hasValue() && !csdNumber.match(bytes)) {
			throw new CSDParseException("oops");
		}

		// register the variable and set the value
		String strValue = csdNumber.stringOfValue(new BigInteger(1, bytes));
		System.out.println(indent + csdNumber + " to: " + strValue);
		nodeStack.peek().setAttribute("value", strValue);

		end();
	}

	@Override
	public void visit(Error error) {
		throw new RuntimeException("TODO");
	}

	@Override
	public void visit(ForEach forEach) throws CSDParseException {
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
		begin(sequence);
		for (Type type : sequence.getElements()) {
			type.accept(this);
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
			System.out.println(indent + "Parsing UTF-8: \"" + strValue + "\"");
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
