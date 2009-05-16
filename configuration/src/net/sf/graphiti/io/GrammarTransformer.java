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

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.graphiti.configuration.ui.Activator;

import org.antlr.runtime.ANTLRInputStream;
import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.Parser;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.tree.CommonErrorNode;
import org.antlr.runtime.tree.Tree;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.osgi.framework.Bundle;
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

	private static Map<String, IAntlrProxy> proxies = new HashMap<String, IAntlrProxy>();

	private IAntlrProxy proxy;

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
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 * @throws ParserLogException
	 *             if the grammar file couldn't be parsed correctly
	 */
	public GrammarTransformer(String folder, String name, String startRule)
			throws IOException, ClassNotFoundException, InstantiationException,
			IllegalAccessException {
		this.startRule = startRule;

		if (proxies.containsKey(name)) {
			proxy = proxies.get(name);
		} else {
			ClassLoader parentLoader = Thread.currentThread()
					.getContextClassLoader();

			Bundle bundle = Activator.getDefault().getBundle();
			IPath path = new Path("bin").append(folder).append(name + ".class");
			URL url = FileLocator.find(bundle, path, null);
			url = FileLocator.toFileURL(url);

			URLClassLoader loader = new URLClassLoader(new URL[] { url },
					parentLoader);

			proxy = (IAntlrProxy) loader.loadClass(name).newInstance();
			proxies.put(name, proxy);
		}
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
	 */
	private Element convertTreeToDom(Tree root) throws Exception {
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
	 */
	public Element parse(ANTLRStringStream stream) throws Exception {
		Parser parser = proxy.createParser(stream);

		Method start = parser.getClass().getMethod(startRule);
		Object returnObj = start.invoke(parser);
		Tree tree = (Tree) returnObj.getClass().getMethod("getTree").invoke(
				returnObj);

		int numErrors = parser.getNumberOfSyntaxErrors();
		if (numErrors > 0) {
			List<String> errorMessages = new ArrayList<String>();
			reportErrors(parser, errorMessages, tree);
			throw new Exception(numErrors + " errors found when parsing: "
					+ errorMessages);
		}

		return convertTreeToDom(tree);
	}

	public Element parse(InputStream in) throws Exception {
		return parse(new ANTLRInputStream(in));
	}

	/**
	 * Parses an input readable by using the given reader.
	 * 
	 * @param text
	 *            A {@link String}.
	 * @return The DOM document element of the parsed file XML representation.
	 */
	public Element parseString(String text) throws Exception {
		return parse(new ANTLRStringStream(text));
	}

	private void reportErrors(Parser parser, List<String> msgs, Tree node) {
		int n = node.getChildCount();
		if (n == 0) {
			if (node instanceof CommonErrorNode) {
				RecognitionException e = ((CommonErrorNode) node).trappedException;
				String msg = parser.getErrorHeader(e) + ", ";
				msg += parser.getErrorMessage(e, parser.getTokenNames());
				msgs.add(msg);
			}
		} else {
			for (int i = 0; i < n; i++) {
				Tree child = node.getChild(i);
				reportErrors(parser, msgs, child);
			}
		}
	}
}
