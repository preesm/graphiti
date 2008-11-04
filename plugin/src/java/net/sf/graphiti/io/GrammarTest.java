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

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.io.StringReader;

import net.percederberg.grammatica.parser.Analyzer;

import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.ls.DOMImplementationLS;
import org.w3c.dom.ls.LSOutput;
import org.w3c.dom.ls.LSSerializer;

/**
 * This class provides a way to test a grammar on a given input file.
 * 
 * @author Matthieu Wipliez
 * 
 */
public class GrammarTest extends Analyzer {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		if (args.length < 3) {
			System.err.println("Usage: GrammarTest <grammar file name> "
					+ "<output XML file name> "
					+ "<input file name or input file pattern> ");
			return;
		}

		String grammarFileName = args[0];
		String outputFileName = args[1];
		try {
			for (int i = 2; i < args.length; i++) {
				String fileName = args[i];
				System.out.println("parsing " + fileName);
				new GrammarTest(grammarFileName, new FileReader(fileName),
						outputFileName);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private Document document;

	/**
	 * Builds a new {@link GrammarTest}.
	 * 
	 * @param grammarFileName
	 *            The grammar absolute file name.
	 * @param reader
	 *            A {@link Reader}, like {@link StringReader} or
	 *            {@link InputStreamReader}.
	 * @param outputFileName
	 *            The absolute file name to write the output to.
	 * @throws Exception
	 */
	public GrammarTest(String grammarFileName, Reader reader,
			String outputFileName) throws Exception {
		Element element = new GrammarTransformer(grammarFileName).parse(reader);
		document = element.getOwnerDocument();
		outputXml(outputFileName);
	}

	/**
	 * Writes the {@link #document} created by a {@link GrammarTransformer} to a
	 * file named <code>outputFileName</code>.
	 * 
	 * @param outputFileName
	 *            The absolute file name to write the output to.
	 * @throws FileNotFoundException
	 */
	private void outputXml(String outputFileName) throws FileNotFoundException {
		// Gets the DOM implementation of document
		DOMImplementation impl = document.getImplementation();
		DOMImplementationLS implLS = (DOMImplementationLS) impl;

		LSOutput output = implLS.createLSOutput();
		OutputStream out = new FileOutputStream(outputFileName);
		output.setByteStream(out);

		LSSerializer serializer = implLS.createLSSerializer();
		serializer.getDomConfig().setParameter("format-pretty-print", true);
		serializer.write(document, output);
	}

}
