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

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import net.sf.graphiti.model.AbstractType;
import net.sf.graphiti.model.Configuration;
import net.sf.graphiti.model.Edge;
import net.sf.graphiti.model.EdgeType;
import net.sf.graphiti.model.FileFormat;
import net.sf.graphiti.model.Graph;
import net.sf.graphiti.model.GraphType;
import net.sf.graphiti.model.Parameter;
import net.sf.graphiti.model.PropertyBean;
import net.sf.graphiti.model.Vertex;
import net.sf.graphiti.model.VertexType;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 * This class provides a generic graph parser. Generic means that it can parse
 * any format (text-based or XML-based) that contains a graph.
 * 
 * @author Matthieu Wipliez
 * 
 */
public class GenericGraphParser {

	private List<Configuration> configurations;

	/**
	 * Creates a new parser using the given configuration list.
	 * 
	 * @param configurations
	 *            A {@link List} of {@link Configuration}s.
	 */
	public GenericGraphParser(List<Configuration> configurations) {
		this.configurations = configurations;
	}

	/**
	 * With a given configuration and file format, try to parse the file
	 * contents given as a byte input stream.
	 * 
	 * @param configuration
	 *            A supposedly valid configuration for the given contents.
	 * @param format
	 *            A supposedly valid file format for the given contents.
	 * @param in
	 *            The contents.
	 * @return A {@link Graph} if successful.
	 * @throws IncompatibleConfigurationFile
	 *             If the configuration or file format is not compatible.
	 */
	private Graph parse(Configuration configuration, FileFormat format,
			InputStream in) throws IncompatibleConfigurationFile {
		List<String> transformations = format.getImportTransformations();
		if (transformations.isEmpty()) {
			throw new IncompatibleConfigurationFile(
					"No import transformations defined");
		}

		try {
			Element element = null;
			for (String transformation : transformations) {
				if (transformation.endsWith(".grammar")) {
					GrammarTransformer transformer = new GrammarTransformer(
							transformation);
					element = transformer.parse(new InputStreamReader(in));
				} else if (transformation.endsWith(".xslt")) {
					if (element == null) {
						// fills the element from the input stream
						Document document = DomHelper.parse(in);
						element = document.getDocumentElement();
					}

					XsltTransformer transformer = new XsltTransformer(
							transformation);
					element = transformer.transformDomToDom(element);
				}
			}

			return parseGraph(configuration, element);
		} catch (Exception e) {
			e.printStackTrace();
			throw new IncompatibleConfigurationFile(e);
		}
	}

	/**
	 * Parses the given {@link IFile} and returns a graph. The file is parsed as
	 * follows:
	 * <ol>
	 * <li>Iterate through the configurations.</li>
	 * <li>In each configuration, for each file format matching the file
	 * extension, try to parse the file with the given configuration.</li>
	 * <li>If parsing fails, go to step 2.</li>
	 * </ol>
	 * 
	 * @param file
	 * @return
	 * @throws IncompatibleConfigurationFile
	 */
	public Graph parse(IFile file) throws IncompatibleConfigurationFile {
		String fileExt = file.getFileExtension();
		for (Configuration configuration : configurations) {
			List<FileFormat> formats = configuration.getFileFormats(fileExt);
			for (FileFormat format : formats) {
				try {
					try {
						return parse(configuration, format, file.getContents());
					} catch (CoreException e) {
						throw new IncompatibleConfigurationFile(e);
					}
				} catch (IncompatibleConfigurationFile e) {
				}
			}
		}

		throw new IncompatibleConfigurationFile(
				"No configuration could parse the file");
	}

	/**
	 * Parses the edges.
	 * 
	 * @param graph
	 *            The graph to add edges to.
	 * @param node
	 *            A child node of &lt;graph&gt;.
	 * @return The node following &lt;edges&gt;.
	 */
	private Node parseEdges(Graph graph, Node node) {
		Configuration configuration = graph.getConfiguration();
		node = DomHelper.getFirstSiblingNamed(node, "edges");
		Node child = node.getFirstChild();
		while (child != null) {
			if (child.getNodeName().equals("edge")) {
				Element element = (Element) child;

				String typeName = element.getAttribute("type");
				EdgeType type = configuration.getEdgeType(typeName);

				String sourceId = element.getAttribute("source");
				Vertex source = graph.findVertex(sourceId);

				String targetId = element.getAttribute("target");
				Vertex target = graph.findVertex(targetId);

				Edge edge = new Edge(type, source, target);
				parseParameters(edge, type, child.getFirstChild());
				graph.addEdge(edge);
			}

			child = child.getNextSibling();
		}

		return node.getNextSibling();
	}

	/**
	 * Parses the graph.
	 * 
	 * @param configuration
	 *            The configuration to create a graph with.
	 * @param element
	 *            The &lt;graph&gt; element.
	 * @return A newly-created graph with the given configuration.
	 */
	private Graph parseGraph(Configuration configuration, Element element)
			throws IncompatibleConfigurationFile {
		String typeName = element.getAttribute("type");
		GraphType type = configuration.getGraphType(typeName);
		Graph graph = new Graph(configuration, type);

		// parse different sections
		Node node = element.getFirstChild();
		node = parseParameters(graph, type, node);
		node = parseVertices(graph, node);
		node = parseEdges(graph, node);

		return graph;
	}

	/**
	 * Parses a parameter.
	 * 
	 * @param parameter
	 *            The parameter we got from the configuration.
	 * @param child
	 *            The &lt;parameter&gt; element.
	 * @return An object, either a {@link List}, a {@link Map}, an
	 *         {@link Integer}, a {@link Float}, a {@link Boolean}, or a
	 *         {@link String}.
	 */
	private Object parseParameter(Parameter parameter, Element child) {
		Class<?> parameterType = parameter.getType();
		if (parameterType == List.class) {
			List<String> list = new ArrayList<String>();
			Node element = child.getFirstChild();
			while (element != null) {
				if (element.getNodeName().equals("element")) {
					String eltValue = ((Element) element).getAttribute("value");
					list.add(eltValue);
				}

				element = element.getNextSibling();
			}

			return list;
		} else if (parameterType == Map.class) {
			Map<String, String> map = new TreeMap<String, String>();
			Node element = child.getFirstChild();
			while (element != null) {
				if (element.getNodeName().equals("pair")) {
					String key = ((Element) element).getAttribute("key");
					String value = ((Element) element).getAttribute("value");
					map.put(key, value);
				}

				element = element.getNextSibling();
			}

			return map;
		} else {
			String value = ((Element) child).getAttribute("value");
			if (parameterType == Integer.class) {
				return Integer.valueOf(value);
			} else if (parameterType == Float.class) {
				return Float.valueOf(value);
			} else if (parameterType == Boolean.class) {
				return Boolean.valueOf(value);
			} else {
				return value;
			}
		}
	}

	/**
	 * Parses the parameters and set the properties of the given
	 * <code>propertyBean</code>, that has the given type.
	 * 
	 * @param propertyBean
	 *            The target property bean.
	 * @param type
	 *            The type of <code>propertyBean</code>.
	 * @param node
	 *            A previous sibling of &lt;parameters&gt;, or
	 *            &lt;parameters&gt; itself.
	 * @return The node following &lt;parameters&gt;.
	 */
	private Node parseParameters(PropertyBean propertyBean, AbstractType type,
			Node node) {
		node = DomHelper.getFirstSiblingNamed(node, "parameters");

		Node child = node.getFirstChild();
		while (child != null) {
			if (child.getNodeName().equals("parameter")) {
				Element element = ((Element) child);
				String parameterName = element.getAttribute("name");
				Parameter parameter = type.getParameter(parameterName);
				Object value = parseParameter(parameter, element);
				propertyBean.setValue(parameterName, value);
			}

			child = child.getNextSibling();
		}

		return node.getNextSibling();
	}

	/**
	 * Parses the vertices.
	 * 
	 * @param graph
	 *            The graph to add vertices to.
	 * @param node
	 *            A child node of &lt;graph&gt;.
	 * @return The node following &lt;vertices&gt;.
	 */
	private Node parseVertices(Graph graph, Node node) {
		Configuration configuration = graph.getConfiguration();
		node = DomHelper.getFirstSiblingNamed(node, "vertices");
		Node child = node.getFirstChild();
		while (child != null) {
			if (child.getNodeName().equals("vertex")) {
				String typeName = ((Element) child).getAttribute("type");
				VertexType type = configuration.getVertexType(typeName);
				Vertex vertex = new Vertex(type);
				parseParameters(vertex, type, child.getFirstChild());
				graph.addVertex(vertex);
			}

			child = child.getNextSibling();
		}

		return node.getNextSibling();
	}

}
