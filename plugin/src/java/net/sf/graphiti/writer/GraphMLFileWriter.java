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
import java.util.Iterator;
import java.util.Set;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import net.sf.graphiti.model.Edge;
import net.sf.graphiti.model.Graph;
import net.sf.graphiti.model.Vertex;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * This class provides a GraphML file writer.
 * 
 * @author Alexandre Romana
 * 
 */
public class GraphMLFileWriter {

	public static String GraphML_NS = "http://graphml.graphdrawing.org/xmlns";

	/**
	 * Writes the given graph to a file whose name is given.
	 * 
	 * @param baseGraph
	 *            The {@link Graph} to write.
	 * @param filename
	 *            The target file name.
	 */
	public static final void dumpFile(Graph baseGraph, String filename) {
		// Create a new document.
		Document doc = null;
		try {
			doc = DocumentBuilderFactory.newInstance().newDocumentBuilder()
					.newDocument();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}

		// Create and add a root element.
		Element root = doc.createElement("graphml");
		doc.appendChild(root);
		root.setAttribute("xmlns", GraphML_NS);
		root.setAttribute("xmlns:xsi",
				"http://www.w3.org/2001/XMLSchema-instance");
		root.setAttribute("xsi:schemaLocation",
				"http://graphml.graphdrawing.org/xmlns/1.0/graphml.xsd");

		// Setting keys

		// edge_prod
		Element edge_prod = doc.createElement("key");
		Element description = doc.createElement("desc");
		description.setTextContent("production rate on the edge");
		edge_prod.appendChild(description);
		edge_prod.setAttribute("attr.name", "edge_prod");
		edge_prod.setAttribute("attr.type", "int");
		edge_prod.setAttribute("for", "edge");
		edge_prod.setAttribute("id", "k0");
		root.appendChild(edge_prod);

		// edge_delay
		Element edge_delay = doc.createElement("key");
		Element description2 = doc.createElement("desc");
		description2.setTextContent("delay on the edge");
		edge_delay.appendChild(description2);
		edge_delay.setAttribute("attr.name", "edge_delay");
		edge_delay.setAttribute("attr.type", "int");
		edge_delay.setAttribute("for", "edge");
		edge_delay.setAttribute("id", "k1");
		root.appendChild(edge_delay);

		// edge_cons
		Element edge_cons = doc.createElement("key");
		Element description3 = doc.createElement("desc");
		description3.setTextContent("consumption rate on the edge");
		edge_cons.appendChild(description3);
		edge_cons.setAttribute("attr.name", "edge_cons");
		edge_cons.setAttribute("attr.type", "int");
		edge_cons.setAttribute("for", "edge");
		edge_cons.setAttribute("id", "k2");
		root.appendChild(edge_cons);

		// name
		Element name = doc.createElement("key");
		Element description4 = doc.createElement("desc");
		description4.setTextContent("vertex name");
		name.appendChild(description4);
		name.setAttribute("attr.name", "name");
		name.setAttribute("attr.type", "string");
		name.setAttribute("for", "node");
		name.setAttribute("id", "k3");
		root.appendChild(name);

		// graph
		Element graph = doc.createElement("graph");
		graph.setAttribute("edgedefault", "directed");

		Set<Vertex> vertices = baseGraph.vertexSet();
		for (Iterator<Vertex> iterator = vertices.iterator(); iterator
				.hasNext();) {
			Vertex vertex = (Vertex) iterator.next();
			Element node = doc.createElement("node");
			node.setAttribute("id", vertex.getValue("id").toString());
			 Element data = doc.createElement("data");
			 data.setAttribute("key","k3");
			 data.setTextContent(vertex.getValue("id").toString());
			 node.appendChild(data);
			graph.appendChild(node);
		}

		Set<Edge> edges = baseGraph.edgeSet();
		for (Iterator<Edge> iterator = edges.iterator(); iterator.hasNext();) {
			Edge edge = (Edge) iterator.next();
			Element edgeElement = doc.createElement("edge");
			edgeElement.setAttribute("source", edge.getSource().getValue("id")
					.toString());
			edgeElement.setAttribute("target", edge.getTarget().getValue("id")
					.toString());

			Element data1 = doc.createElement("data");
			data1.setAttribute("key", "k0");
			data1.setTextContent("1"); // FIXME : what should I use?
			edgeElement.appendChild(data1);
			Element data2 = doc.createElement("data");
			data2.setAttribute("key", "k1");
			data2.setTextContent("0");// FIXME : what should I use?
			edgeElement.appendChild(data2);
			Element data3 = doc.createElement("data");
			data3.setAttribute("key", "k2");
			data3.setTextContent("1");// FIXME : what should I use?
			edgeElement.appendChild(data3);

			graph.appendChild(edgeElement);
		}

		root.appendChild(graph);

		// Output the document.
		try {
			Transformer transformer = TransformerFactory.newInstance()
					.newTransformer();
			transformer
					.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
			transformer.setOutputProperty(OutputKeys.METHOD, "xml");
			transformer.setOutputProperty(OutputKeys.ENCODING, "ISO-8859-1");
			// we want to pretty format the XML output
			transformer.setOutputProperty(
					"{http://xml.apache.org/xslt}indent-amount", "4");
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.transform(new DOMSource(doc), new StreamResult(
					new File(filename)));
		} catch (TransformerConfigurationException e) {
			e.printStackTrace();
		} catch (TransformerException e) {
			e.printStackTrace();
		} catch (TransformerFactoryConfigurationError e) {
			e.printStackTrace();
		}
	}

}
