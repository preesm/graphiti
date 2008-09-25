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
package net.sf.graphiti.ontology.impl;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.OutputStream;
import java.io.StringReader;
import java.net.URL;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMResult;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import net.percederberg.grammatica.Grammar;
import net.percederberg.grammatica.parser.Node;
import net.percederberg.grammatica.parser.Parser;
import net.percederberg.grammatica.parser.Production;
import net.percederberg.grammatica.parser.Token;
import net.percederberg.grammatica.parser.Tokenizer;
import net.sf.graphiti.ontology.OntologyFactory;
import net.sf.graphiti.ontology.Parameter;
import net.sf.graphiti.ontology.Translation;
import net.sf.graphiti.ui.GraphitiPlugin;

import org.eclipse.core.runtime.FileLocator;
import org.osgi.framework.Bundle;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.bootstrap.DOMImplementationRegistry;

import com.hp.hpl.jena.ontology.Individual;

/**
 * Implementation of Translation.
 * 
 * @author Matthieu Wipliez
 * 
 */
public class TranslationImpl extends XMLSchemaTypeImpl implements Translation {

	public TranslationImpl(Individual individual) {
		super(individual);
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
			domNode.setAttribute("value", token.getImage());
			parent.appendChild(domNode);
		}
	}

	private Element convertTreeToDom(Node root) throws Exception {
		// create document
		DOMImplementation impl = DOMImplementationRegistry.newInstance()
				.getDOMImplementation("Core 3.0 XML 3.0 LS");
		String namespace = "";
		Document document = impl
				.createDocument(namespace, root.getName(), null);

		// convert children parse nodes
		Element documentElement = document.getDocumentElement();
		int n = root.getChildCount();
		for (int i = 0; i < n; i++) {
			Node child = root.getChildAt(i);
			convertNodeToDom(documentElement, child);
		}

		return document.getDocumentElement();
	}

	@Override
	public String getString(Element element) {
		Bundle bundle = GraphitiPlugin.getDefault().getBundle();
		String xsltFilename = getStringProperty(OntologyFactory
				.getPropertyTranslationHasXmlToStringXslt());

		TransformerFactory factory = TransformerFactory.newInstance(
				"net.sf.saxon.TransformerFactoryImpl", null);
		try {
			URL url = bundle.getEntry("src/owl/" + xsltFilename);
			File file = new File(FileLocator.toFileURL(url).getPath());
			StreamSource xsltSource = new StreamSource(file);
			Transformer transformer = factory.newTransformer(xsltSource);

			OutputStream os = new ByteArrayOutputStream();
			StreamResult result = new StreamResult(os);
			DOMSource source = new DOMSource(element);
			transformer.transform(source, result);
			os.close();

			String value = os.toString();
			return value;
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	@Override
	public Parameter hasParameter() {
		return (Parameter) getIndividualProperty(OntologyFactory
				.getPropertyTranslationReferencesParameter());
	}

	@Override
	public void stringToXml(String input, Element parent) {
		Bundle bundle = GraphitiPlugin.getDefault().getBundle();
		String grammarFilename = getStringProperty(OntologyFactory
				.getPropertyTranslationHasGrammar());
		String xsltFilename = getStringProperty(OntologyFactory
				.getPropertyTranslationHasStringToXmlXslt());

		try {
			URL url = bundle.getEntry("src/owl/" + grammarFilename);
			File file = new File(FileLocator.toFileURL(url).getPath());

			// parse input
			Grammar grammar = new Grammar(file);
			Tokenizer tokenizer = grammar.createTokenizer(new StringReader(
					input));
			Parser parser = grammar.createParser(tokenizer);
			Node tree = parser.parse();

			// convert to DOM tree
			Element element = convertTreeToDom(tree);

			// transform DOM source to DOM result
			TransformerFactory factory = TransformerFactory.newInstance(
					"net.sf.saxon.TransformerFactoryImpl", null);
			url = bundle.getEntry("src/owl/" + xsltFilename);
			file = new File(FileLocator.toFileURL(url).getPath());
			StreamSource xsltSource = new StreamSource(file);
			Transformer transformer = factory.newTransformer(xsltSource);

			DOMSource source = new DOMSource(element);
			DOMResult result = new DOMResult(parent);
			transformer.transform(source, result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
