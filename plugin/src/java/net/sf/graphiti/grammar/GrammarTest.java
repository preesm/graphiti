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

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;

import net.percederberg.grammatica.parser.Analyzer;

import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
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
		String input = "\"\\\"D:/Projets/MPEG/RVC/cvs_rvc/rvc_repos/RVC/Eclipse_Simulator/Software/MPEG4_SP_Decoder_RVC/data/foreman_qcif_30.yuv\"";
		try {
			new GrammarTest(grammarFileName, input);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private Document document;

	public GrammarTest(String grammarFileName, String input)
			throws MalformedURLException, FileNotFoundException {
		Element element = new GrammarTransformer(new URL("file:///" + ""))
				.parse(input);
		document = element.getOwnerDocument();
		outputXml();
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
