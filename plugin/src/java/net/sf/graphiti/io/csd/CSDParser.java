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

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.xml.namespace.QName;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import javax.xml.xpath.XPathFactoryConfigurationException;
import javax.xml.xpath.XPathVariableResolver;

import net.sf.graphiti.io.csd.ast.CSDChar;
import net.sf.graphiti.io.csd.ast.CSDNumber;
import net.sf.graphiti.io.csd.ast.CSDVisitor;
import net.sf.graphiti.io.csd.ast.Choice;
import net.sf.graphiti.io.csd.ast.Error;
import net.sf.graphiti.io.csd.ast.LongUTF8String;
import net.sf.graphiti.io.csd.ast.Reference;
import net.sf.graphiti.io.csd.ast.Sequence;
import net.sf.graphiti.io.csd.ast.SequenceOf;
import net.sf.graphiti.io.csd.ast.Type;
import net.sf.graphiti.io.csd.ast.UTF8String;
import net.sf.graphiti.io.csd.ast.Variable;

public class CSDParser implements CSDVisitor, XPathVariableResolver {

	public static void main(String[] args) throws Exception {
		new CSDParser(args[0], args[1]);
	}

	private XPathFactory factory;

	private BufferedInputStream in;

	private Map<String, String> variables;

	public CSDParser(String csdFile, String binFile) throws ClassCastException,
			ClassNotFoundException, CSDParseException, IllegalAccessException,
			InstantiationException, IOException,
			XPathFactoryConfigurationException, XPathExpressionException {
		variables = new TreeMap<String, String>();
		
		List<Type> types = new CSDFileParser(csdFile).getTypes();
		new TypeReferenceVisitor(types);

		factory = XPathFactory.newInstance(
				XPathFactory.DEFAULT_OBJECT_MODEL_URI,
				"net.sf.saxon.xpath.XPathFactoryImpl", null);
		factory.setXPathVariableResolver(this);

		FileInputStream fis = new FileInputStream(binFile);
		in = new BufferedInputStream(fis);
		types.get(0).accept(this);
	}

	/**
	 * Returns true if the given arrays are equal.
	 * 
	 * @param actual
	 *            A byte array read from {@link #in}.
	 * @param expected
	 *            A byte array expected.
	 * @return True if the given arrays are equal, false otherwise.
	 */
	private boolean byteEquals(byte[] actual, byte[] expected) {
		for (int i = 0; i < actual.length; i++) {
			if (actual[i] != expected[i]) {
				return false;
			}
		}

		return true;
	}
	
	/**
	 * Reads bytes and check if they match the given binary number's bytes.
	 * Beware that it is the caller's responsibility to call
	 * {@link InputStream#mark(int)}, not this function's.
	 * 
	 * @param nb
	 *            A binary number.
	 * @return True if the bytes read match, false otherwise.
	 * @throws IOException
	 *             If {@link InputStream#read()} fails.
	 */
	private boolean readBytesMatch() throws IOException {
		byte[] expected = new byte[] {};
		byte[] actual = new byte[expected.length];
		in.read(actual);
		return byteEquals(actual, expected);
	}

	@Override
	public Object resolveVariable(QName variableName) {
		return variables.get(variableName.getLocalPart());
	}

	@Override
	public void visit(Choice choice) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(CSDChar csdChar) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(CSDNumber csdNumber) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(Error error) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(LongUTF8String utf8String) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(Reference reference) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(Sequence sequence) {
	}

	@Override
	public void visit(SequenceOf sequenceOf) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(UTF8String utf8String) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(Variable variable) {
		// TODO Auto-generated method stub

	}

}
