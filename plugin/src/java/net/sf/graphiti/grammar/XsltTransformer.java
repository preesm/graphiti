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

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.OutputStream;

import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMResult;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import net.sf.graphiti.util.FileLocator;

import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.bootstrap.DOMImplementationRegistry;

/**
 * @author Matthieu Wipliez
 * 
 */
public class XsltTransformer {

	private Transformer transformer;

	public XsltTransformer(String fileName) {
		File file = FileLocator.getFile(fileName);
		TransformerFactory factory = TransformerFactory.newInstance(
				"net.sf.saxon.TransformerFactoryImpl", null);
		try {
			StreamSource xsltSource = new StreamSource(file);
			transformer = factory.newTransformer(xsltSource);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void transformDomToDom(Element source, Node target) {
		Source xmlSource = new DOMSource(source);
		Result outputTarget = new DOMResult(target);
		try {
			transformer.transform(xmlSource, outputTarget);
		} catch (TransformerException e) {
			e.printStackTrace();
		}
	}

	public Element transformDomToDom(Element source, String rootElementName) {
		try {
			// create document
			DOMImplementation impl = DOMImplementationRegistry.newInstance()
					.getDOMImplementation("Core 3.0 XML 3.0 LS");
			Document document = impl.createDocument("", rootElementName, null);
			document.removeChild(document.getDocumentElement());

			transformDomToDom(source, document);

			return document.getDocumentElement();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public String transformDomToString(Element element) {
		if (transformer == null) {
			return "";
		} else {
			try {
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
	}

}
