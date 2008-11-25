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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import net.sf.graphiti.io.DomHelper;
import net.sf.graphiti.io.csd.ast.AttachData;
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
import net.sf.saxon.dom.NodeWrapper;
import net.sf.saxon.om.ValueRepresentation;
import net.sf.saxon.s9api.DocumentBuilder;
import net.sf.saxon.s9api.Processor;
import net.sf.saxon.s9api.QName;
import net.sf.saxon.s9api.SaxonApiException;
import net.sf.saxon.s9api.XPathCompiler;
import net.sf.saxon.s9api.XPathExecutable;
import net.sf.saxon.s9api.XPathSelector;
import net.sf.saxon.s9api.XdmAtomicValue;
import net.sf.saxon.s9api.XdmEmptySequence;
import net.sf.saxon.s9api.XdmItem;
import net.sf.saxon.s9api.XdmNode;
import net.sf.saxon.s9api.XdmSequenceIterator;
import net.sf.saxon.s9api.XdmValue;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class CSDParser implements CSDVisitor {

	public static void main(String[] args) throws Exception {
		new CSDParser(args[0], args[1]);

		// ObjectInputStream oin = new ObjectInputStream(new FileInputStream(
		// args[1]));
		// Object obj = oin.readObject();
		// System.out.println(obj);
	}

	private XPathCompiler compiler;

	private DocumentBuilder docBuilder;

	private Document document;

	private RandomAccessFile in;

	private String indent;

	private ArrayDeque<Element> nodeStack;

	private Map<QName, XdmValue> variables;

	public CSDParser(String csdFile, String binFile) throws ClassCastException,
			ClassNotFoundException, CSDFileParseException,
			IllegalAccessException, InstantiationException, IOException {
		indent = "";
		variables = new HashMap<QName, XdmValue>();

		// parse the Concrete Syntax Description
		CSDFileParser fileParser = new CSDFileParser(csdFile);
		List<Type> types = fileParser.getTypes();
		TypeReferenceVisitor visitor = new TypeReferenceVisitor(types);
		Type entry = visitor.getType(fileParser.getEntryPoint());
		new FirstSetVisitor(types);

		// initialize XPath compiler
		Processor processor = new Processor(false);
		compiler = processor.newXPathCompiler();
		docBuilder = processor.newDocumentBuilder();

		// create result document
		document = DomHelper.createDocument("", "ConcreteSyntaxTree");

		// initialize node stack
		nodeStack = new ArrayDeque<Element>();
		nodeStack.push(document.getDocumentElement());

		// let's go!
		in = new RandomAccessFile(binFile, "r");
		try {
			entry.accept(this);
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

	private XdmValue evaluateXPath(String expression) {
		try {
			Element context = nodeStack.peek();
			XPathExecutable exe = compiler.compile(expression);
			XPathSelector selector = exe.load();

			// set variables
			for (Entry<QName, XdmValue> var : variables.entrySet()) {
				QName name = var.getKey();
				XdmValue value = var.getValue();
				selector.setVariable(name, value);
			}

			// set context and evaluate
			XdmNode node = docBuilder.wrap(context);
			selector.setContextItem(node);
			XdmValue value = selector.evaluate();
			return value;
		} catch (SaxonApiException e) {
			e.printStackTrace();
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	private boolean evaluateXPathBoolean(String expression) {
		XdmValue value = evaluateXPath(expression);
		if (value instanceof XdmAtomicValue) {
			try {
				return ((XdmAtomicValue) value).getBooleanValue();
			} catch (SaxonApiException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	private int evaluateXPathInt(String expression) {
		XdmValue value = evaluateXPath(expression);
		if (value instanceof XdmAtomicValue) {
			try {
				return (int) ((XdmAtomicValue) value).getLongValue();
			} catch (SaxonApiException e) {
				e.printStackTrace();
			}
		}

		return 0;
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
	public void visit(AttachData data) throws CSDParseException {
		begin(data);
		Element dataElt = nodeStack.peek();

		XdmValue value = evaluateXPath(data.getSelect());
		if (value instanceof XdmAtomicValue) {
			XdmAtomicValue atomic = (XdmAtomicValue) value;
			dataElt.setAttribute("value", atomic.getStringValue());
		} else {
			XdmSequenceIterator it = value.iterator();
			while (it.hasNext()) {
				XdmItem item = it.next();
				ValueRepresentation repr = item.getUnderlyingValue();
				if (repr instanceof NodeWrapper) {
					NodeWrapper wrapper = (NodeWrapper) repr;
					Element element = (Element) wrapper.getUnderlyingNode();
					if (dataElt.getParentNode() != element) {
						dataElt.appendChild(element);
					}
				} else {
					System.err.println("oops");
				}
			}
		}

		end();
	}

	@Override
	public void visit(Choice choice) throws CSDParseException {
		begin(choice);

		for (Type type : choice.getAlternatives()) {
			if (type instanceof Variable) {
				type.accept(this);
			} else if (type instanceof AttachData) {
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
		XdmValue value = evaluateXPath(select);
		XdmSequenceIterator it = value.iterator();
		while (it.hasNext()) {
			// declare a context variable whose value is item
			XdmItem item = it.next();
			QName qName = new QName("context");
			compiler.declareVariable(qName);
			variables.put(qName, item);

			// visit the type
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
		QName qName = new QName(varName);
		String select = variable.getSelect();

		// if the variable is not declared, declare it and set its value to the
		// empty sequence
		if (!variables.containsKey(qName)) {
			compiler.declareVariable(qName);
			variables.put(qName, XdmEmptySequence.getInstance());
		}

		XdmValue value = evaluateXPath(select);
		variables.put(qName, value);
	}

}
