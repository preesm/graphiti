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
import java.io.Reader;
import java.net.URL;

import net.percederberg.grammatica.Grammar;
import net.percederberg.grammatica.parser.Node;
import net.percederberg.grammatica.parser.Parser;
import net.percederberg.grammatica.parser.Production;
import net.percederberg.grammatica.parser.Token;
import net.percederberg.grammatica.parser.Tokenizer;

import org.eclipse.core.runtime.FileLocator;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.bootstrap.DOMImplementationRegistry;

/**
 * @author Matthieu Wipliez
 * 
 */
public class GrammarTransformer {

	private Grammar grammar;

	public GrammarTransformer(String fileName) {
		try {
			File file = new File(fileName);
			grammar = new Grammar(file);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public GrammarTransformer(URL grammarUrl) {
		try {
			File file = new File(FileLocator.toFileURL(grammarUrl).getPath());
			grammar = new Grammar(file);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

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

	private Element convertTreeToDom(Node root) throws Exception {
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

	public Element parse(Reader reader) {
		try {
			Tokenizer tokenizer = grammar.createTokenizer(reader);
			Parser parser = grammar.createParser(tokenizer);
			Node tree = parser.parse();
			return convertTreeToDom(tree);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
