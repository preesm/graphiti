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

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.xml.transform.TransformerException;

import net.percederberg.grammatica.GrammarException;
import net.percederberg.grammatica.parser.ParserCreationException;
import net.percederberg.grammatica.parser.ParserLogException;
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
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.dialogs.ListDialog;
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

	private class ConfigurationContentProvider implements
			IStructuredContentProvider {

		@Override
		public void dispose() {
		}

		@Override
		public Object[] getElements(Object inputElement) {
			return ((List<?>) inputElement).toArray();
		}

		@Override
		public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		}
	}

	public class ConfigurationLabelProvider extends LabelProvider {

		@Override
		public String getText(Object element) {
			Configuration configuration = (Configuration) element;
			Set<GraphType> types = configuration.getGraphTypes();
			String res = "";
			int i = 0;
			int n = types.size();
			for (GraphType type : types) {
				res += type.getName();
				if (i < n - 1) {
					res += ", ";
				}
				i++;
			}

			return res;
		}
	}

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
	 * Checks that every vertex in <code>graph</code> has layout information. If
	 * it is the case, the {@link Graph#PROPERTY_HAS_LAYOUT} is set to
	 * <code>true</code>. Otherwise it is set to false.
	 * 
	 * @param graph
	 */
	private void checkLayout(Graph graph) {
		Set<Vertex> vertices = graph.vertexSet();
		for (Vertex vertex : vertices) {
			if (vertex.getValue(Vertex.PROPERTY_SIZE) == null) {
				graph.setValue(Graph.PROPERTY_HAS_LAYOUT, Boolean.FALSE);
				return;
			}
		}

		graph.setValue(Graph.PROPERTY_HAS_LAYOUT, Boolean.TRUE);
	}

	/**
	 * With a given configuration and file format, try to parse the file
	 * contents given as a byte input stream.
	 * 
	 * @param configuration
	 *            A supposedly valid configuration for the given contents.
	 * @param path
	 *            The file absolute path.
	 * @param in
	 *            The contents.
	 * @return A {@link Graph} if successful.
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
	private Graph parse(Configuration configuration, String path, InputStream in)
			throws ClassCastException, ClassNotFoundException,
			GrammarException, IllegalAccessException, InstantiationException,
			IOException, ParserCreationException, ParserLogException,
			TransformerException {
		FileFormat format = configuration.getFileFormat();
		List<String> transformations = format.getImportTransformations();
		Element element = null;
		if (transformations.isEmpty()) {
			element = DomHelper.parse(in).getDocumentElement();
		} else {
			for (String transformation : transformations) {
				if (transformation.endsWith(".grammar")) {
					GrammarTransformer transformer = new GrammarTransformer(
							transformation);
					element = transformer.parse(new InputStreamReader(in));
				} else if (transformation.endsWith(".xslt")) {
					// fills the element from the input stream
					if (element == null) {
						element = DomHelper.parse(in).getDocumentElement();
					}

					XsltTransformer transformer = new XsltTransformer(
							transformation);
					transformer.setParameter("path", path);
					element = transformer.transformDomToDom(element);
				}
			}
		}

		return parseGraph(configuration, element);
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
		// finds all suitable configurations
		String fileExt = file.getFileExtension();
		List<Configuration> configurations = new ArrayList<Configuration>();
		for (Configuration configuration : this.configurations) {
			FileFormat format = configuration.getFileFormat();
			if (format.getFileExtension().equals(fileExt)) {
				configurations.add(configuration);
			}
		}

		Configuration configuration;
		if (configurations.isEmpty()) {
			throw new IncompatibleConfigurationFile(
					"No configuration could parse the file");
		} else if (configurations.size() == 1) {
			configuration = configurations.get(0);
		} else {
			boolean res = true;
			do {
				configuration = pickConfiguration(configurations);

				if (configuration == null) {
					throw new IncompatibleConfigurationFile(
							"No configuration was chosen.");
				}

				try {
					return parse(configuration, file.getLocation().toString(),
							file.getContents());
				} catch (Throwable e) {
					String fileName = "Could not parse \"" + file.getName()
							+ "\"";
					String message = "The file could not be parsed with the chosen configuration. "
							+ "Would you like to try with another configuration?";
					res = MessageDialog.openConfirm(null, fileName, message);
				}
			} while (res);

			throw new IncompatibleConfigurationFile(
					"No suitable configuration could be found.");
		}

		// parse with the configuration
		try {
			return parse(configuration, file.getLocation().toString(), file
					.getContents());
		} catch (ClassCastException e) {
			throw new IncompatibleConfigurationFile(
					"There was a problem with the creation of a DOM document",
					e);
		} catch (ClassNotFoundException e) {
			throw new IncompatibleConfigurationFile(
					"A DOM class could not be found", e);
		} catch (GrammarException e) {
			throw new IncompatibleConfigurationFile("A grammar was not valid",
					e);
		} catch (IllegalAccessException e) {
			throw new IncompatibleConfigurationFile(
					"A DOM class could not be accessed", e);
		} catch (InstantiationException e) {
			throw new IncompatibleConfigurationFile(
					"A DOM class could not be instantiated", e);
		} catch (IOException e) {
			throw new IncompatibleConfigurationFile(
					"The file could not be read", e);
		} catch (ParserCreationException e) {
			throw new IncompatibleConfigurationFile(
					"The parser could not be created", e);
		} catch (ParserLogException e) {
			throw new IncompatibleConfigurationFile(
					"There was a problem with the parser", e);
		} catch (TransformerException e) {
			throw new IncompatibleConfigurationFile(
					"An unrecoverable error occurred during "
							+ "the course of the transformation.", e);
		} catch (CoreException e) {
			throw new IncompatibleConfigurationFile(
					"Could not obtain the file contents.", e);
		} catch (Throwable e) {
			throw new IncompatibleConfigurationFile(
					"The file could not be parsed with the matching configuration",
					e);
		}
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
	private Graph parseGraph(Configuration configuration, Element element) {
		String typeName = element.getAttribute("type");
		GraphType type = configuration.getGraphType(typeName);
		Graph graph = new Graph(configuration, type);

		// parse different sections
		Node node = element.getFirstChild();
		node = parseParameters(graph, type, node);
		node = parseVertices(graph, node);
		node = parseEdges(graph, node);

		checkLayout(graph);

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
				if (element.getNodeName().equals("entry")) {
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
			} else if (parameterType == String.class) {
				if (value.isEmpty()) {
					return null;
				} else {
					return value;
				}
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

				// set layout information if present
				String xAttr = ((Element) child).getAttribute("x");
				String yAttr = ((Element) child).getAttribute("y");
				if (!xAttr.isEmpty() && !yAttr.isEmpty()) {
					int x = Integer.parseInt(xAttr);
					int y = Integer.parseInt(yAttr);
					vertex.setValue(Vertex.PROPERTY_SIZE, new Rectangle(x, y,
							0, 0));
				}

				parseParameters(vertex, type, child.getFirstChild());
				graph.addVertex(vertex);
			}

			child = child.getNextSibling();
		}

		return node.getNextSibling();
	}

	/**
	 * Asks the user to pick one configuration among n.
	 * 
	 * @param configurations
	 *            A list of configurations.
	 * @return A configuration, or <code>null</code>.
	 */
	private Configuration pickConfiguration(List<Configuration> configurations) {
		IWorkbench workbench = PlatformUI.getWorkbench();
		Shell shell = workbench.getActiveWorkbenchWindow().getShell();

		ListDialog dialog = new ListDialog(shell);
		dialog.setAddCancelButton(false);
		dialog.setBlockOnOpen(true);
		dialog.setContentProvider(new ConfigurationContentProvider());
		dialog.setLabelProvider(new ConfigurationLabelProvider());
		dialog.setInput(configurations);
		dialog.setMessage("Please pick a configuration below:");
		dialog.setTitle("Choose a configuration");
		dialog.open();

		Object[] result = dialog.getResult();
		if (result == null) {
			return null;
		} else {
			return (Configuration) result[0];
		}
	}

}
