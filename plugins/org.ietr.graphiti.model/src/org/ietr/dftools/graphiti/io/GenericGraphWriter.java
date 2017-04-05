/*******************************************************************************
 * Copyright or © or Copr. IETR/INSA - Rennes (2008 - 2017) :
 *
 * Antoine Morvan <antoine.morvan@insa-rennes.fr> (2017)
 * Clément Guy <clement.guy@insa-rennes.fr> (2014)
 * Matthieu Wipliez <matthieu.wipliez@insa-rennes.fr> (2008 - 2011)
 *
 * This software is a computer program whose purpose is to [describe
 * functionalities and technical features of your software].
 *
 * This software is governed by the CeCILL  license under French law and
 * abiding by the rules of distribution of free software.  You can  use, 
 * modify and/ or redistribute the software under the terms of the CeCILL
 * license as circulated by CEA, CNRS and INRIA at the following URL
 * "http://www.cecill.info". 
 *
 * As a counterpart to the access to the source code and  rights to copy,
 * modify and redistribute granted by the license, users are provided only
 * with a limited warranty  and the software's author,  the holder of the
 * economic rights,  and the successive licensors  have only  limited
 * liability. 
 *
 * In this respect, the user's attention is drawn to the risks associated
 * with loading,  using,  modifying and/or developing or reproducing the
 * software by the user in light of its specific status of free software,
 * that may mean  that it is complicated to manipulate,  and  that  also
 * therefore means  that it is reserved for developers  and  experienced
 * professionals having in-depth computer knowledge. Users are therefore
 * encouraged to load and test the software's suitability as regards their
 * requirements in conditions enabling the security of their systems and/or 
 * data to be ensured and,  more generally, to use and operate it in the 
 * same conditions as regards security. 
 *
 * The fact that you are presently reading this means that you have had
 * knowledge of the CeCILL license and that you accept its terms.
 *******************************************************************************/
package org.ietr.dftools.graphiti.io;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.eclipse.draw2d.geometry.Rectangle;
import org.ietr.dftools.graphiti.model.AbstractObject;
import org.ietr.dftools.graphiti.model.Configuration;
import org.ietr.dftools.graphiti.model.Edge;
import org.ietr.dftools.graphiti.model.FileFormat;
import org.ietr.dftools.graphiti.model.Graph;
import org.ietr.dftools.graphiti.model.ObjectType;
import org.ietr.dftools.graphiti.model.Parameter;
import org.ietr.dftools.graphiti.model.Transformation;
import org.ietr.dftools.graphiti.model.Vertex;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * 
 * @author Matthieu Wipliez
 * 
 */
public class GenericGraphWriter {

	private Graph graph;

	/**
	 * Creates a writer for the given graph.
	 * 
	 * @param graph
	 *            The {@link Graph} to write.
	 */
	public GenericGraphWriter(Graph graph) {
		this.graph = graph;
	}

	/**
	 * Writes the graph associated with this writer to the given output stream.
	 * The output file absolute path is passed to the underlying stylesheet(s).
	 * 
	 * @param path
	 *            The file absolute path.
	 * @param byteStream
	 *            The {@link OutputStream} to write to.
	 */
	public void write(String path, OutputStream byteStream) {
		Element element = null;
		Configuration configuration = graph.getConfiguration();
		FileFormat format = configuration.getFileFormat();

		List<Transformation> transformations = format
				.getExportTransformations();
		try {
			for (Transformation transformation : transformations) {
				if (transformation.isXslt()) {
					if (element == null) {
						// writes graph
						element = writeGraph();
					}
					
					XsltTransformer transformer = new XsltTransformer(
							configuration.getContributorId(),
							transformation.getFileName());
					transformer.setParameter("path", path);
					element = transformer.transformDomToDom(element);
				} else {
					transformation.getInstance().transform(graph, byteStream);
					return;
				}
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

		if (format.getContentType().equals("text")) {
			String content = element.getTextContent();
			try {
				byteStream.write(content.getBytes());
			} catch (IOException e) {
				// byte stream is a byte array output stream
			}
		} else {
			DomHelper.write(element.getOwnerDocument(), byteStream);
		}
	}

	private void writeEdges(Element edgesElement) {
		Document document = edgesElement.getOwnerDocument();
		Set<Edge> edges = graph.edgeSet();
		for (Edge edge : edges) {
			Element edgeElement = document.createElement("edge");
			edgeElement.setAttribute("source", (String) edge.getSource()
					.getValue(ObjectType.PARAMETER_ID));
			edgeElement.setAttribute("target", (String) edge.getTarget()
					.getValue(ObjectType.PARAMETER_ID));
			edgeElement.setAttribute("type", edge.getType().getName());

			Element parameters = document.createElement("parameters");
			edgeElement.appendChild(parameters);
			writeParameters(edge, edge.getType(), parameters);

			edgesElement.appendChild(edgeElement);
		}
	}

	/**
	 * Writes the graph.
	 * 
	 * @return A &lt;graph&gt; element.
	 */
	private Element writeGraph() {
		Document document = DomHelper.createDocument("", "graph");
		Element graphElement = document.getDocumentElement();
		graphElement.setAttribute("type", graph.getType().getName());

		Element parameters = document.createElement("parameters");
		graphElement.appendChild(parameters);
		writeParameters(graph, graph.getType(), parameters);

		Element vertices = document.createElement("vertices");
		graphElement.appendChild(vertices);
		writeVertices(vertices);

		Element edges = document.createElement("edges");
		graphElement.appendChild(edges);
		writeEdges(edges);

		return graphElement;
	}

	private void writeParameters(AbstractObject abstractObject,
			ObjectType type, Element parametersElement) {
		Document document = parametersElement.getOwnerDocument();
		List<Parameter> parameters = type.getParameters();
		for (Parameter parameter : parameters) {
			Element parameterElement = document.createElement("parameter");
			String parameterName = parameter.getName();
			parameterElement.setAttribute("name", parameterName);

			Class<?> parameterType = parameter.getType();
			if (parameterType == List.class) {
				List<?> list = (List<?>) abstractObject.getValue(parameterName);
				if (list != null) {
					for (Object obj : list) {
						if (obj != null) {
							Element element = document.createElement("element");
							element.setAttribute("value", obj.toString());
							parameterElement.appendChild(element);
						}
					}
					parametersElement.appendChild(parameterElement);
				}
			} else if (parameterType == Map.class) {
				Map<?, ?> map = (Map<?, ?>) abstractObject
						.getValue(parameterName);
				if (map != null) {
					for (Entry<?, ?> entry : map.entrySet()) {
						Object key = entry.getKey();
						Object value = entry.getValue();
						if (key != null && value != null) {
							Element entryElt = document.createElement("entry");
							entryElt.setAttribute("key", key.toString());
							entryElt.setAttribute("value", value.toString());
							parameterElement.appendChild(entryElt);
						}
					}
					parametersElement.appendChild(parameterElement);
				}
			} else {
				Object value = abstractObject.getValue(parameterName);
				if (value != null) {
					parameterElement.setAttribute("value", value.toString());
					parametersElement.appendChild(parameterElement);
				}
			}
		}
	}

	private void writeVertices(Element verticesElement) {
		Document document = verticesElement.getOwnerDocument();
		Set<Vertex> vertices = graph.vertexSet();
		for (Vertex vertex : vertices) {
			Element vertexElement = document.createElement("vertex");
			vertexElement.setAttribute("type", vertex.getType().getName());

			// add layout information
			Rectangle bounds = (Rectangle) vertex
					.getValue(Vertex.PROPERTY_SIZE);
			vertexElement.setAttribute("x", String.valueOf(bounds.x));
			vertexElement.setAttribute("y", String.valueOf(bounds.y));

			// and parameters
			Element parameters = document.createElement("parameters");
			vertexElement.appendChild(parameters);
			writeParameters(vertex, vertex.getType(), parameters);

			verticesElement.appendChild(vertexElement);
		}
	}

}
