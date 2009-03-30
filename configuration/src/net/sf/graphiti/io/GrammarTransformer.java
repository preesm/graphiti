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
package net.sf.graphiti.io;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

import org.antlr.runtime.ANTLRInputStream;
import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CharStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.Lexer;
import org.antlr.runtime.Parser;
import org.antlr.runtime.TokenStream;
import org.antlr.runtime.tree.Tree;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * This class provides methods to create a grammar from a given file, and parse
 * an input to produce a concrete syntax DOM tree.
 * 
 * @author Matthieu Wipliez
 * 
 */
public class GrammarTransformer {

	private Class<?> lexer;

	private Class<?> parser;

	private String startRule;

	/**
	 * Creates a new grammar transformer using the grammar with the given file
	 * name.
	 * 
	 * @param folder
	 *            The folder where the grammar classes are located.
	 * @param name
	 *            The grammar name.
	 * @param startRule
	 *            The start rule.
	 * @throws GrammarException
	 *             if the grammar wasn't valid
	 * @throws IOException
	 *             if the grammar file couldn't be read
	 * @throws ClassNotFoundException
	 * @throws ParserLogException
	 *             if the grammar file couldn't be parsed correctly
	 */
	public GrammarTransformer(String folder, String name, String startRule)
			throws IOException, ClassNotFoundException {
		this.startRule = startRule;

		File lexerFile = new File(folder);
		URL[] urls = new URL[] { lexerFile.toURI().toURL() };
		URLClassLoader loader = new URLClassLoader(urls);

		lexer = loader.loadClass(name + "Lexer");
		parser = loader.loadClass(name + "Parser");
	}

	/**
	 * Converts the given parse node to XML and appends it to the DOM parent
	 * element. The conversion is as follows: a production is translated to an
	 * element with the same name, and its children are appended to it. A token
	 * is translated to a "token" element, with a "name" attribute containing
	 * the token name, and its text content containing the token image.
	 * 
	 * @param parent
	 *            The parent element.
	 * @param node
	 *            The parse node.
	 */
	private void convertNodeToDom(Element parent, Tree node) {
		Document document = parent.getOwnerDocument();
		String text = node.getText();

		int n = node.getChildCount();
		if (n == 0) {
			parent.setTextContent(text);
		} else {
			Element domNode = document.createElement(text);
			parent.appendChild(domNode);
			for (int i = 0; i < n; i++) {
				Tree child = node.getChild(i);
				convertNodeToDom(domNode, child);
			}
		}
	}

	/**
	 * Creates a DOM document with a document element named after the root parse
	 * node, and calls {@link #convertNodeToDom(Element, Tree)} on its children.
	 * 
	 * @param root
	 *            The root parse node.
	 * @return The document element of the newly created document.
	 * @throws ClassCastException
	 *             If any specified class does not implement
	 *             DOMImplementationSource
	 * @throws ClassNotFoundException
	 *             If any specified class can not be found
	 * @throws IllegalAccessException
	 *             If the default constructor of a specified class is not
	 *             accessible
	 * @throws InstantiationException
	 *             If any specified class is an interface or abstract class
	 */
	private Element convertTreeToDom(Tree root) throws ClassCastException,
			ClassNotFoundException, IllegalAccessException,
			InstantiationException {
		// create document
		Document document = DomHelper.createDocument("", root.getText());

		// convert children parse nodes
		Element documentElement = document.getDocumentElement();
		int n = root.getChildCount();
		for (int i = 0; i < n; i++) {
			Tree child = root.getChild(i);
			convertNodeToDom(documentElement, child);
		}

		return document.getDocumentElement();
	}

	/**
	 * Parses an input readable by using the given reader.
	 * 
	 * @param reader
	 *            A {@link Reader}.
	 * @return The DOM document element of the parsed file XML representation.
	 * @throws ClassCastException
	 *             If any specified class does not implement
	 *             DOMImplementationSource
	 * @throws ClassNotFoundException
	 *             If any specified class can not be found
	 * @throws GrammarException
	 *             if the tokenizer couldn't be created or initialized correctly
	 * @throws IllegalAccessException
	 *             If the default constructor of a specified class is not
	 *             accessible
	 * @throws InstantiationException
	 *             If any specified class is an interface or abstract class
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws InvocationTargetException
	 * @throws IllegalArgumentException
	 * @throws ParserCreationException
	 *             if the parser couldn't be initialized correctly
	 * @throws ParserLogException
	 *             if the input couldn't be parsed correctly
	 */
	public Element parse(ANTLRStringStream stream) throws ClassCastException,
			ClassNotFoundException, IllegalAccessException,
			InstantiationException, SecurityException, NoSuchMethodException,
			IllegalArgumentException, InvocationTargetException {
		Constructor<?> ctor = lexer.getConstructor(CharStream.class);
		Lexer lexerInst = (Lexer) ctor.newInstance(stream);

		// create the token buffer
		CommonTokenStream tokens = new CommonTokenStream(lexerInst);

		ctor = parser.getConstructor(TokenStream.class);
		Parser parserInst = (Parser) ctor.newInstance(tokens);

		Method start = parser.getMethod(startRule);
		Object returnObj = start.invoke(parserInst);
		Tree tree = (Tree) returnObj.getClass().getMethod("getTree").invoke(
				returnObj);
		return convertTreeToDom(tree);
	}

	/**
	 * Parses an input readable by using the given reader.
	 * 
	 * @param text
	 *            A {@link String}.
	 * @return The DOM document element of the parsed file XML representation.
	 * @throws ClassCastException
	 *             If any specified class does not implement
	 *             DOMImplementationSource
	 * @throws ClassNotFoundException
	 *             If any specified class can not be found
	 * @throws InstantiationException
	 *             If any specified class is an interface or abstract class
	 * @throws IllegalAccessException
	 *             If the default constructor of a specified class is not
	 *             accessible
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 * @throws IllegalArgumentException
	 * @throws SecurityException
	 */
	public Element parseString(String text) throws ClassCastException,
			ClassNotFoundException, InstantiationException,
			IllegalAccessException, SecurityException,
			IllegalArgumentException, NoSuchMethodException,
			InvocationTargetException {
		return parse(new ANTLRStringStream(text));
	}

	public Element parse(InputStream in) throws ClassCastException,
			ClassNotFoundException, InstantiationException,
			IllegalAccessException, SecurityException,
			IllegalArgumentException, NoSuchMethodException,
			InvocationTargetException, IOException {
		return parse(new ANTLRInputStream(in));
	}
}
