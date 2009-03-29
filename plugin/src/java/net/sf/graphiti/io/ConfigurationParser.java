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
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import net.sf.graphiti.model.AbstractType;
import net.sf.graphiti.model.Configuration;
import net.sf.graphiti.model.EdgeType;
import net.sf.graphiti.model.FileFormat;
import net.sf.graphiti.model.GraphType;
import net.sf.graphiti.model.Parameter;
import net.sf.graphiti.model.ParameterPosition;
import net.sf.graphiti.model.VertexType;
import net.sf.graphiti.ui.figure.shapes.IShape;
import net.sf.graphiti.ui.figure.shapes.ShapeCircle;
import net.sf.graphiti.ui.figure.shapes.ShapeHexagon;
import net.sf.graphiti.ui.figure.shapes.ShapeLozenge;
import net.sf.graphiti.ui.figure.shapes.ShapeRoundedBox;
import net.sf.graphiti.ui.figure.shapes.ShapeTriangle;
import net.sf.graphiti.util.FileLocator;

import org.eclipse.swt.graphics.Color;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

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
				System.err.println(fileName);
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

	/**
	 * Parses a configuration file whose name is given.
	 * 
	 * @param fileName
	 *            The configuration file name.
	 * @throws Exception
	 *             If anything wrong occurs.
	 */
	private void parse(String fileName) throws Exception {
		InputStream byteStream = new FileInputStream(fileName);
		Document document = DomHelper.parse(byteStream);
		parseConfiguration(fileName, document.getDocumentElement());
	}

	/**
	 * Parse the "color" attribute for the given type.
	 * 
	 * @param type
	 *            A type.
	 * @param element
	 *            An element.
	 */
	private void parseAttributeColor(AbstractType type, Element element) {
		int red = Integer.parseInt(element.getAttribute("red"));
		int green = Integer.parseInt(element.getAttribute("green"));
		int blue = Integer.parseInt(element.getAttribute("blue"));
		Color color = new Color(null, red, green, blue);
		type.addAttribute(AbstractType.ATTRIBUTE_COLOR, color);
	}

	/**
	 * Parses the attributes for the given edge type.
	 * 
	 * @param type
	 *            The type whose attributes are being specified.
	 * @param node
	 *            A child node of &lt;attributes&gt;.
	 */
	private void parseAttributes(EdgeType type, Node node) {
		while (node != null) {
			String nodeName = node.getNodeName();
			if (nodeName.equals("color")) {
				parseAttributeColor(type, (Element) node);
			} else if (nodeName.equals("directed")) {
				Element element = (Element) node;
				boolean directed = Boolean.parseBoolean(element
						.getAttribute("value"));
				type.addAttribute(EdgeType.ATTRIBUTE_DIRECTED, directed);
			}

			node = node.getNextSibling();
		}
	}

	/**
	 * Parses the attributes for the given vertex type.
	 * 
	 * @param type
	 *            The type whose attributes are being specified.
	 * @param node
	 *            A child node of &lt;attributes&gt;.
	 */
	private void parseAttributes(VertexType type, Node node) {
		while (node != null) {
			String nodeName = node.getNodeName();
			if (nodeName.equals("color")) {
				parseAttributeColor(type, (Element) node);
			} else if (nodeName.equals("shape")) {
				String shapeName = ((Element) node).getAttribute("name");
				IShape shape = null;
				if (shapeName.equals("circle")) {
					shape = new ShapeCircle();
				} else if (shapeName.equals("hexagon")) {
					shape = new ShapeHexagon();
				} else if (shapeName.equals("lozenge")) {
					shape = new ShapeLozenge();
				} else if (shapeName.equals("roundedBox")) {
					shape = new ShapeRoundedBox();
				} else if (shapeName.equals("triangle")) {
					shape = new ShapeTriangle();
				}
				type.addAttribute(VertexType.ATTRIBUTE_SHAPE, shape);
			} else if (nodeName.equals("size")) {
				Element element = (Element) node;
				int width = Integer.parseInt(element.getAttribute("width"));
				int height = Integer.parseInt(element.getAttribute("height"));
				type.addAttribute(VertexType.ATTRIBUTE_WIDTH, width);
				type.addAttribute(VertexType.ATTRIBUTE_HEIGHT, height);
			}

			node = node.getNextSibling();
		}
	}

	/**
	 * Parses the given document element of a configuration file into a
	 * {@link Configuration}, which is then added to {@link #configurations}.
	 * 
	 * @param fileName
	 *            The configuration file name.
	 * @param element
	 *            The root element of the configuration file.
	 */
	private void parseConfiguration(String fileName, Element element) {
		Configuration configuration = new Configuration(fileName);

		// parse different sections
		Node node = element.getFirstChild();
		node = parseFileFormat(configuration, node);
		node = parseRefinementFileExtensions(configuration, node);
		node = parseGraphTypes(configuration, node);
		node = parseVertexTypes(configuration, node);
		node = parseEdgeTypes(configuration, node);

		// if nothing has failed
		configurations.add(configuration);
	}

	/**
	 * Parses the edge types.
	 * 
	 * @param configuration
	 *            The configuration to fill.
	 * @param node
	 *            A child node of configuration.
	 * @return The node following &lt;edgeTypes&gt;
	 */
	private Node parseEdgeTypes(Configuration configuration, Node node) {
		node = DomHelper.getFirstSiblingNamed(node, "edgeTypes");
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

	/**
	 * Parses the file format defined by this configuration.
	 * 
	 * @param configuration
	 *            The configuration to fill.
	 * @param node
	 *            A child node of configuration.
	 * @return The node following &lt;fileFormat&gt;
	 */
	private Node parseFileFormat(Configuration configuration, Node node) {
		node = DomHelper.getFirstSiblingNamed(node, "fileFormat");
		Element element = (Element) node;

		// file format
		String extension = element.getAttribute("extension");
		String type = element.getAttribute("type");
		FileFormat format = new FileFormat(extension, type);
		configuration.setFileFormat(format);

		// import/export
		Node child = node.getFirstChild();
		child = DomHelper.getFirstSiblingNamed(child, "import");
		parseFileFormatImport(format, child.getFirstChild());
		child = DomHelper.getFirstSiblingNamed(child, "export");
		parseFileFormatExport(format, child.getFirstChild());

		return node.getNextSibling();
	}

	/**
	 * Parses the file format exports.
	 * 
	 * @param format
	 *            The file format to fill.
	 * @param node
	 *            A child node of &lt;exports&gt;.
	 */
	private void parseFileFormatExport(FileFormat format, Node node) {
		while (node != null) {
			if (node.getNodeName().equals("transformation")) {
				String fileName = ((Element) node).getAttribute("name");
				format.addExportTransformation(fileName);
			}

			node = node.getNextSibling();
		}
	}

	/**
	 * Parses the file format imports.
	 * 
	 * @param format
	 *            The file format to fill.
	 * @param node
	 *            A child node of &lt;imports&gt;.
	 */
	private void parseFileFormatImport(FileFormat format, Node node) {
		while (node != null) {
			if (node.getNodeName().equals("transformation")) {
				String type = ((Element) node).getAttribute("type");
				if (type.equals("grammar")) {
					String folder = ((Element) node).getAttribute("folder");
					String name = ((Element) node).getAttribute("name");
					String startRule = ((Element) node)
							.getAttribute("startRule");
					format.addImportGrammarTransformation(folder, name,
							startRule);
				} else if (type.equals("xslt")) {
					String file = ((Element) node).getAttribute("file");
					if (file.isEmpty()) {
						throw new IllegalArgumentException(
								"The \"file\" attribute should not be empty!");
					}
					format.addImportXsltTransformation(file);
				} else {
					throw new IllegalArgumentException("Unknown type: " + type);
				}
			}

			node = node.getNextSibling();
		}
	}

	/**
	 * Parses the graph types.
	 * 
	 * @param configuration
	 *            The configuration to fill.
	 * @param node
	 *            A child node of configuration.
	 * @return The node following &lt;graphTypes&gt;
	 */
	private Node parseGraphTypes(Configuration configuration, Node node) {
		node = DomHelper.getFirstSiblingNamed(node, "graphTypes");
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

	/**
	 * Parses a parameter default value.
	 * 
	 * @param parameterType
	 *            The class of the parameter.
	 * @param child
	 *            The &lt;parameter&gt; element.
	 * @return An object, either a {@link List}, a {@link Map}, an
	 *         {@link Integer}, a {@link Float}, a {@link Boolean}, or a
	 *         {@link String}.
	 */
	private Object parseParameter(Class<?> parameterType, Element child) {
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
				if (element.getNodeName().equals("entry")) {
					String key = ((Element) element).getAttribute("key");
					String value = ((Element) element).getAttribute("value");
					map.put(key, value);
				}

				element = element.getNextSibling();
			}

			return map;
		} else {
			Element element = (Element) child;
			String value = element.getAttribute("default");
			if (parameterType == Integer.class) {
				return Integer.valueOf(value);
			} else if (parameterType == Float.class) {
				return Float.valueOf(value);
			} else if (parameterType == Boolean.class) {
				return Boolean.valueOf(value);
			} else if (parameterType == String.class) {
				return value;
			} else {
				return value;
			}
		}
	}

	/**
	 * Parses the parameters for the given type.
	 * 
	 * @param type
	 *            The type whose parameters are being specified.
	 * @param node
	 *            A child node of &lt;parameters&gt;.
	 */
	private void parseParameters(AbstractType type, Node node) {
		while (node != null) {
			if (node.getNodeName().equals("parameter")) {
				Element element = (Element) node;
				String parameterName = element.getAttribute("name");
				String typeName = element.getAttribute("type");
				Class<?> clz = String.class;
				try {
					clz = Class.forName(typeName);
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}

				// creates the parameter
				ParameterPosition position = null;
				Object value = parseParameter(clz, element);
				Parameter parameter = new Parameter(parameterName, value,
						position, clz);

				// adds the parameter to the type
				type.addParameter(parameter);
			}

			node = node.getNextSibling();
		}
	}

	/**
	 * Parses the refinement file extensions.
	 * 
	 * @param configuration
	 *            The configuration to fill.
	 * @param node
	 *            A child node of configuration.
	 * @return The node following &lt;refinementFileExtensions&gt;
	 */
	private Node parseRefinementFileExtensions(Configuration configuration,
			Node node) {
		node = DomHelper.getFirstSiblingNamed(node, "refinementFileExtensions");
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

	/**
	 * Parses a type.
	 * 
	 * @param configuration
	 *            The configuration to fill.
	 * @param node
	 *            A child node of a type element (one of &lt;graphType&gt;,
	 *            &lt;vertexType&gt; or &lt;edgeType&gt;).
	 */
	private void parseType(AbstractType type, Node node) {
		node = DomHelper.getFirstSiblingNamed(node, "attributes");
		if (type instanceof EdgeType) {
			parseAttributes((EdgeType) type, node.getFirstChild());
		} else if (type instanceof VertexType) {
			parseAttributes((VertexType) type, node.getFirstChild());
		}
		node = DomHelper.getFirstSiblingNamed(node, "parameters");
		parseParameters(type, node.getFirstChild());
	}

	/**
	 * Parses the vertex types.
	 * 
	 * @param configuration
	 *            The configuration to fill.
	 * @param node
	 *            A child node of configuration.
	 * @return The node following &lt;vertexTypes&gt;
	 */
	private Node parseVertexTypes(Configuration configuration, Node node) {
		node = DomHelper.getFirstSiblingNamed(node, "vertexTypes");
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
