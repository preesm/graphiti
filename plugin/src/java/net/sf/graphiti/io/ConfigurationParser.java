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

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import net.sf.graphiti.model.AbstractType;
import net.sf.graphiti.model.Configuration;
import net.sf.graphiti.model.EdgeType;
import net.sf.graphiti.model.FileFormat;
import net.sf.graphiti.model.GraphType;
import net.sf.graphiti.model.VertexType;
import net.sf.graphiti.util.FileLocator;

import org.w3c.dom.DOMConfiguration;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.bootstrap.DOMImplementationRegistry;
import org.w3c.dom.ls.DOMImplementationLS;
import org.w3c.dom.ls.LSInput;
import org.w3c.dom.ls.LSParser;

/**
 * This class parses all configuration files located in the configuration folder
 * (defined in the plug-in preferences).
 * 
 * @author Matthieu Wipliez
 * 
 */
public class ConfigurationParser {

	private List<Configuration> configurations;

	/**
	 * Creates a new configuration parser that parses all configuration files
	 * located in the configuration folder (defined in the plug-in preferences).
	 * 
	 * @see FileLocator
	 */
	public ConfigurationParser() {
		configurations = new ArrayList<Configuration>();

		// Get all *.xml files
		List<String> confFiles = FileLocator.listFiles("[^.]*\\.xml");
		for (String fileName : confFiles) {
			try {
				parse(fileName);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Returns a reference to the list of configurations parsed.
	 * 
	 * @return A reference to the list of configurations parsed.
	 */
	public List<Configuration> getConfigurations() {
		return configurations;
	}

	private void parse(String fileName) throws Exception {
		// DOM LS implementation
		DOMImplementationLS impl = (DOMImplementationLS) DOMImplementationRegistry
				.newInstance().getDOMImplementation("Core 3.0 XML 3.0 LS");

		// input
		LSInput input = impl.createLSInput();
		InputStream byteStream = new FileInputStream(fileName);
		input.setByteStream(byteStream);

		// parse without comments and whitespace
		LSParser builder = impl.createLSParser(
				DOMImplementationLS.MODE_SYNCHRONOUS, null);
		DOMConfiguration config = builder.getDomConfig();
		config.setParameter("comments", false);
		config.setParameter("element-content-whitespace", false);
		Document document = builder.parse(input);

		Configuration configuration = new Configuration(fileName);
		parseConfiguration(configuration, document.getDocumentElement());
	}

	private void parseConfiguration(Configuration configuration, Element element) {
		String namespace = element.getAttribute("namespace");
		configuration.setNamespace(namespace);

		// file extensions
		Node node = element.getFirstChild();
		node = parseFileExtensions(configuration, node);
		node = parseRefinementFileExtensions(configuration, node);
		node = parseFileFormats(configuration, node);
		node = parseGraphTypes(configuration, node);
		node = parseVertexTypes(configuration, node);
		node = parseEdgeTypes(configuration, node);
	}

	private Node parseEdgeTypes(Configuration configuration, Node node) {
		while (!node.getNodeName().equals("edgeTypes")) {
			node = node.getNextSibling();
		}

		Set<EdgeType> edgeTypes = new TreeSet<EdgeType>();
		Node child = node.getFirstChild();
		while (child != null) {
			if (child.getNodeName().equals("edgeType")) {
				String typeName = ((Element) child).getAttribute("name");
				EdgeType type = new EdgeType(typeName);
				edgeTypes.add(type);
				parseType(type, child.getFirstChild());
			}

			child = child.getNextSibling();
		}

		configuration.setEdgeTypes(edgeTypes);
		return node.getNextSibling();
	}

	private Node parseFileExtensions(Configuration configuration, Node node) {
		while (!node.getNodeName().equals("fileExtensions")) {
			node = node.getNextSibling();
		}

		Node child = node.getFirstChild();
		Set<String> fileExtensions = new TreeSet<String>();
		while (child != null) {
			if (child.getNodeName().equals("fileExtension")) {
				String fileExtension = ((Element) child).getAttribute("name");
				fileExtensions.add(fileExtension);
			}

			child = child.getNextSibling();
		}

		configuration.setFileExtensions(fileExtensions);
		return node.getNextSibling();
	}

	private void parseFileFormatExport(FileFormat format, Node node) {
		while (node != null) {
			if (node.getNodeName().equals("transformation")) {
				String fileName = ((Element) node).getAttribute("name");
				format.addExportTransformation(fileName);
			}

			node.getNextSibling();
		}
	}

	private void parseFileFormatImport(FileFormat format, Node node) {
		while (node != null) {
			if (node.getNodeName().equals("transformation")) {
				String fileName = ((Element) node).getAttribute("name");
				format.addImportTransformation(fileName);
			}

			node.getNextSibling();
		}
	}

	private Node parseFileFormats(Configuration configuration, Node node) {
		while (!node.getNodeName().equals("fileFormats")) {
			node = node.getNextSibling();
		}

		List<FileFormat> fileFormats = new ArrayList<FileFormat>();
		Node child = node.getFirstChild();
		while (child != null) {
			if (child.getNodeName().equals("fileFormat")) {
				String extension = ((Element) child).getAttribute("extension");
				FileFormat format = new FileFormat(extension);

				// import
				Node importExport = child.getFirstChild();
				while (!importExport.getNodeName().equals("import")) {
					importExport = child.getNextSibling();
				}
				parseFileFormatImport(format, importExport.getFirstChild());

				// export
				while (!importExport.getNodeName().equals("export")) {
					importExport = child.getNextSibling();
				}
				parseFileFormatExport(format, child.getFirstChild());
			}

			child = child.getNextSibling();
		}

		configuration.setFileFormats(fileFormats);
		return node.getNextSibling();
	}

	private Node parseGraphTypes(Configuration configuration, Node node) {
		while (!node.getNodeName().equals("graphTypes")) {
			node = node.getNextSibling();
		}

		Set<GraphType> graphTypes = new TreeSet<GraphType>();
		Node child = node.getFirstChild();
		while (child != null) {
			if (child.getNodeName().equals("graphType")) {
				String typeName = ((Element) child).getAttribute("name");
				GraphType type = new GraphType(typeName);
				graphTypes.add(type);
				parseType(type, child.getFirstChild());
			}

			child = child.getNextSibling();
		}

		configuration.setGraphTypes(graphTypes);
		return node.getNextSibling();
	}

	private Node parseRefinementFileExtensions(Configuration configuration,
			Node node) {
		while (!node.getNodeName().equals("fileFormats")) {
			node = node.getNextSibling();
		}

		Set<String> fileExtensions = new TreeSet<String>();
		Node child = node.getFirstChild();
		while (child != null) {
			if (child.getNodeName().equals("fileExtension")) {
				String fileExtension = ((Element) child).getAttribute("name");
				fileExtensions.add(fileExtension);
			}

			child = child.getNextSibling();
		}

		configuration.setRefinementFileExtensions(fileExtensions);
		return node.getNextSibling();
	}

	private void parseType(AbstractType type, Node node) {

	}

	private Node parseVertexTypes(Configuration configuration, Node node) {
		while (!node.getNodeName().equals("vertexTypes")) {
			node = node.getNextSibling();
		}

		Set<VertexType> vertexTypes = new TreeSet<VertexType>();
		Node child = node.getFirstChild();
		while (child != null) {
			if (child.getNodeName().equals("vertexType")) {
				String typeName = ((Element) child).getAttribute("name");
				VertexType type = new VertexType(typeName);
				vertexTypes.add(type);
				parseType(type, child.getFirstChild());
			}

			child = child.getNextSibling();
		}

		configuration.setVertexTypes(vertexTypes);
		return node.getNextSibling();
	}

}
