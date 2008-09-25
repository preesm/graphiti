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
package net.sf.graphiti.writer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.StringReader;

import net.percederberg.grammatica.Grammar;
import net.percederberg.grammatica.GrammarException;
import net.percederberg.grammatica.parser.Analyzer;
import net.percederberg.grammatica.parser.Node;
import net.percederberg.grammatica.parser.Parser;
import net.percederberg.grammatica.parser.ParserCreationException;
import net.percederberg.grammatica.parser.ParserLogException;
import net.percederberg.grammatica.parser.Production;
import net.percederberg.grammatica.parser.Token;
import net.percederberg.grammatica.parser.Tokenizer;

import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.bootstrap.DOMImplementationRegistry;
import org.w3c.dom.ls.DOMImplementationLS;
import org.w3c.dom.ls.LSOutput;
import org.w3c.dom.ls.LSSerializer;

/**
 * @author Administrateur
 * 
 */
public class GrammarTest extends Analyzer {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String grammarFileName = "D:\\repositories\\graphiti-editor\\plugin\\"
				+ "src\\owl\\xnlExpr.grammar";
		String input = "(1 + 2 + 3) * 4";
		try {
			new GrammarTest(grammarFileName, input);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private Document document;

	public GrammarTest(String grammarFileName, String input)
			throws FileNotFoundException, ParserLogException, GrammarException,
			ClassCastException, ClassNotFoundException, InstantiationException,
			IllegalAccessException {
		File file = new File(grammarFileName);
		Grammar grammar = new Grammar(file);
		Tokenizer tokenizer = grammar.createTokenizer(new StringReader(input));
		Parser parser = grammar.createParser(tokenizer);
		try {
			Node tree = parser.parse();
			document = createDocument(tree.getName());
			convertTreeToDom(document.getDocumentElement(), tree);
			outputXml();
		} catch (ParserCreationException e) {
			e.printStackTrace();
		}
	}

	private void convertNodeToDom(Element parent, Node parseNode) {
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
			domNode.setAttribute("value", token.getImage());
			parent.appendChild(domNode);

			int n = parseNode.getChildCount();
			for (int i = 0; i < n; i++) {
				Node child = parseNode.getChildAt(i);
				convertNodeToDom(domNode, child);
			}
		}
	}

	private void convertTreeToDom(Element documentElement, Node root) {
		int n = root.getChildCount();
		for (int i = 0; i < n; i++) {
			Node child = root.getChildAt(i);
			convertNodeToDom(documentElement, child);
		}
	}

	private Document createDocument(String rootRuleName)
			throws ClassCastException, ClassNotFoundException,
			InstantiationException, IllegalAccessException {
		DOMImplementation impl = DOMImplementationRegistry.newInstance()
				.getDOMImplementation("Core 3.0 XML 3.0 LS");
		String namespace = "";
		Document domDocument = impl.createDocument(namespace, rootRuleName,
				null);
		return domDocument;
	}

	private void outputXml() throws FileNotFoundException {
		// Gets the DOM implementation of document
		DOMImplementation impl = document.getImplementation();
		DOMImplementationLS implLS = (DOMImplementationLS) impl;

		LSOutput output = implLS.createLSOutput();
		OutputStream out = new FileOutputStream(
				"D:\\repositories\\graphiti-editor\\plugin\\src\\owl\\test.xml");
		output.setByteStream(out);

		LSSerializer serializer = implLS.createLSSerializer();
		serializer.getDomConfig().setParameter("format-pretty-print", true);
		serializer.write(document, output);
	}

}
