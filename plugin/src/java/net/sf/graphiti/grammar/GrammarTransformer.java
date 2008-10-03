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
package net.sf.graphiti.grammar;

import java.io.File;
import java.io.IOException;
import java.io.Reader;

import net.percederberg.grammatica.Grammar;
import net.percederberg.grammatica.GrammarException;
import net.percederberg.grammatica.parser.Node;
import net.percederberg.grammatica.parser.Parser;
import net.percederberg.grammatica.parser.ParserCreationException;
import net.percederberg.grammatica.parser.ParserLogException;
import net.percederberg.grammatica.parser.Production;
import net.percederberg.grammatica.parser.Token;
import net.percederberg.grammatica.parser.Tokenizer;
import net.sf.graphiti.util.FileLocator;

import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.bootstrap.DOMImplementationRegistry;

/**
 * This class provides methods to create a grammar from a given file, and parse
 * an input to produce a concrete syntax DOM tree.
 * 
 * @author Matthieu Wipliez
 * 
 */
public class GrammarTransformer {

	private Grammar grammar;

	/**
	 * Creates a new grammar transformer using the grammar with the given file
	 * name.
	 * 
	 * @param fileName
	 *            The grammar file name.
	 * @throws IOException
	 *             if the grammar file couldn't be read
	 * @throws ParserLogException
	 *             if the grammar file couldn't be parsed correctly
	 * @throws GrammarException
	 *             if the grammar wasn't valid
	 */
	public GrammarTransformer(String fileName) throws IOException,
			ParserLogException, GrammarException {
		File file = FileLocator.getFile(fileName);
		grammar = new Grammar(file);
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
	 * @param parseNode
	 *            The parse node.
	 */
	private void convertNodeToDom(Element parent, Node parseNode) {
		Document document = parent.getOwnerDocument();
		if (parseNode instanceof Production) {
			Element domNode = document.createElement(parseNode.getName());
			parent.appendChild(domNode);

			int n = parseNode.getChildCount();
			for (int i = 0; i < n; i++) {
				Node child = parseNode.getChildAt(i);
				convertNodeToDom(domNode, child);
			}
		} else {
			Token token = (Token) parseNode;
			Element domNode = document.createElement("token");
			domNode.setAttribute("name", token.getName());
			domNode.setTextContent(token.getImage());
			parent.appendChild(domNode);
		}
	}

	/**
	 * Creates a DOM document with a document element named after the root parse
	 * node, and calls {@link #convertNodeToDom(Element, Node)} on its children.
	 * 
	 * @param root
	 *            The root parse node.
	 * @return The document element of the newly created document.
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
	 */
	private Element convertTreeToDom(Node root) throws ClassCastException,
			ClassNotFoundException, InstantiationException,
			IllegalAccessException {
		// create document
		DOMImplementation impl = DOMImplementationRegistry.newInstance()
				.getDOMImplementation("Core 3.0 XML 3.0 LS");
		Document document = impl.createDocument("", root.getName(), null);

		// convert children parse nodes
		Element documentElement = document.getDocumentElement();
		int n = root.getChildCount();
		for (int i = 0; i < n; i++) {
			Node child = root.getChildAt(i);
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
	 * @throws GrammarException
	 *             if the tokenizer couldn't be created or initialized correctly
	 * @throws ParserCreationException
	 *             if the parser couldn't be initialized correctly
	 * @throws ParserLogException
	 *             if the input couldn't be parsed correctly
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
	 */
	public Element parse(Reader reader) throws GrammarException,
			ParserCreationException, ParserLogException, ClassCastException,
			ClassNotFoundException, InstantiationException,
			IllegalAccessException {
		Tokenizer tokenizer = grammar.createTokenizer(reader);
		Parser parser = grammar.createParser(tokenizer);
		Node tree = parser.parse();
		return convertTreeToDom(tree);
	}

}
