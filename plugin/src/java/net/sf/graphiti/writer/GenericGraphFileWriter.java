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

import java.io.OutputStream;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import net.sf.graphiti.model.Configuration;
import net.sf.graphiti.model.Graph;
import net.sf.graphiti.ontology.OntologyFactory;

import org.w3c.dom.Document;

/**
 * This class provides a generic graph file writer.
 * 
 * @author Jonathan Piat
 * @author Matthieu Wipliez
 * 
 */
public class GenericGraphFileWriter {

	private Graph graph;

	/**
	 * Creates a new generic graph file writer, with the given graph.
	 * 
	 * @param graph
	 *            The graph to write.
	 */
	public GenericGraphFileWriter(Graph graph) {
		this.graph = graph;
	}

	/**
	 * Writes the graph this writer was created with.
	 * 
	 * @param out
	 *            The output stream.
	 */
	public void write(OutputStream out) {
		Configuration configuration = graph.getConfiguration();
		OntologyFactory factory = configuration.getOntologyFactory();
		try {
			// Fills the DOM graph
			SchemaWriter writer = new SchemaWriter(graph);
			Document domDocument = writer.write(factory.getDocumentElement());

			// Set up the output transformer
			TransformerFactory transfac = TransformerFactory.newInstance();
			Transformer trans = transfac.newTransformer();

			trans.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
			trans.setOutputProperty(OutputKeys.INDENT, "yes");
			trans.setOutputProperty(OutputKeys.METHOD, "xml");
			trans.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
			trans.setOutputProperty(
					"{http://xml.apache.org/xslt}indent-amount", "4");

			// Print the DOM node
			StreamResult result = new StreamResult(out);
			DOMSource source = new DOMSource(domDocument);
			trans.transform(source, result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
