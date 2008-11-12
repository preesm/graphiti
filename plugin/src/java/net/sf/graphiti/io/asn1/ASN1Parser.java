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
package net.sf.graphiti.io.asn1;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import javax.xml.transform.TransformerException;

import net.percederberg.grammatica.GrammarException;
import net.percederberg.grammatica.parser.ParserCreationException;
import net.percederberg.grammatica.parser.ParserLogException;
import net.sf.graphiti.io.GrammarTest;
import net.sf.graphiti.io.GrammarTransformer;
import net.sf.graphiti.io.XsltTransformer;
import net.sf.graphiti.io.asn1.ast.Production;

import org.w3c.dom.Element;

/**
 * @author Matthieu Wipliez
 * 
 */
public class ASN1Parser {

	public static void main(String[] args) {
		if (args.length != 4) {
			if (args.length == 3) {
				GrammarTest.main(args);
				return;
			} else {
				String message = "Usage: ASN1Parser <asn1.grammar file name> "
						+ "<asn1.xslt file name> "
						+ "<ASN.1 syntax description file name> "
						+ "<binary input file name>\n"
						+ "Usage: ASN1Parser <asn1.grammar file name> "
						+ "<output XML file name>";
				System.err.println(message);
				return;
			}
		}

		String grammarFileName = args[0];
		String xsltFileName = args[1];
		String syntaxFileName = args[2];
		String binaryFileName = args[3];
		try {
			System.out.println("parsing " + syntaxFileName);
			ASN1Parser parser = new ASN1Parser(grammarFileName, xsltFileName,
					syntaxFileName);
			System.out.println("done");
			parser.parse(binaryFileName);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private List<Production> productions;

	/**
	 * Creates a new ASN.1 parser. It needs the file name of the ASN.1 grammar
	 * (path to "asn1.grammar"), the file name of the ASN.1 concrete syntax to
	 * abstract syntax XSLT (path to "asn1.xslt"), and the file name of the
	 * ASN.1 description.
	 * 
	 * @param asn1Grammar
	 *            File name, absolute or relative, pointing to "asn1.grammar".
	 * @param asn1Xslt
	 *            File name, absolute or relative, pointing to "asn1.xslt".
	 * @param fileName
	 *            File name of the ASN.1 description.
	 * @throws ClassCastException
	 *             If any specified class does not implement
	 *             DOMImplementationSource
	 * @throws ClassNotFoundException
	 *             If any specified class can not be found
	 * @throws GrammarException
	 *             if the grammar wasn't valid
	 * @throws IllegalAccessException
	 *             If the default constructor of a specified class is not
	 *             accessible
	 * @throws InstantiationException
	 *             If any specified class is an interface or abstract class
	 * @throws IOException
	 *             if the grammar file couldn't be read
	 * @throws ParserCreationException
	 *             if the parser couldn't be initialized correctly
	 * @throws ParserLogException
	 *             if the grammar file couldn't be parsed correctly
	 * @throws TransformerException
	 *             If an unrecoverable error occurs during the course of the
	 *             transformation.
	 */
	public ASN1Parser(String asn1Grammar, String asn1Xslt, String fileName)
			throws ClassCastException, ClassNotFoundException,
			GrammarException, IllegalAccessException, InstantiationException,
			IOException, ParserCreationException, ParserLogException,
			TransformerException {
		GrammarTransformer grammarTr = new GrammarTransformer(asn1Grammar);
		XsltTransformer xsltTr = new XsltTransformer(asn1Xslt);
		Element source = grammarTr.parse(new FileReader(fileName));
		Element description = xsltTr.transformDomToDom(source);
		configure(description);
	}

	/**
	 * Configure this parser according to the rules defined by the given
	 * description.
	 * 
	 * @param definition
	 *            A DOM element.
	 */
	private void configure(Element definition) {
		ASN1GrammarParser parser = new ASN1GrammarParser();
		productions = parser.parseProductions(definition);
		prepare();
	}

	/**
	 * Parses a binary file using the ASN.1 syntax description.
	 * 
	 * @param fileName
	 *            File name of the binary input file.
	 */
	public void parse(String fileName) {
		try {
			FileInputStream in = new FileInputStream(fileName);
			BufferedInputStream is = new BufferedInputStream(in);
			System.out.println(is.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Prepare this parser by resolving references.
	 */
	private void prepare() {
		TypeReferenceVisitor typeRefVisitor = new TypeReferenceVisitor();
		typeRefVisitor.visit(productions);

		ItemReferenceVisitor itemRefVisitor = new ItemReferenceVisitor();
		itemRefVisitor.visit(productions);
	}

}
