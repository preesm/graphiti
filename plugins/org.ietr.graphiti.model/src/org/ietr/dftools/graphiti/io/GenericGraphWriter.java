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

// TODO: Auto-generated Javadoc
/**
 * The Class GenericGraphWriter.
 *
 * @author Matthieu Wipliez
 */
public class GenericGraphWriter {

  /** The graph. */
  private final Graph graph;

  /**
   * Creates a writer for the given graph.
   *
   * @param graph
   *          The {@link Graph} to write.
   */
  public GenericGraphWriter(final Graph graph) {
    this.graph = graph;
  }

  /**
   * Writes the graph associated with this writer to the given output stream. The output file absolute path is passed to the underlying stylesheet(s).
   *
   * @param path
   *          The file absolute path.
   * @param byteStream
   *          The {@link OutputStream} to write to.
   */
  public void write(final String path, final OutputStream byteStream) {
    Element element = null;
    final Configuration configuration = this.graph.getConfiguration();
    final FileFormat format = configuration.getFileFormat();

    final List<Transformation> transformations = format.getExportTransformations();
    try {
      for (final Transformation transformation : transformations) {
        if (transformation.isXslt()) {
          if (element == null) {
            // writes graph
            element = writeGraph();
          }

          final XsltTransformer transformer = new XsltTransformer(configuration.getContributorId(), transformation.getFileName());
          transformer.setParameter("path", path);
          element = transformer.transformDomToDom(element);
        } else {
          transformation.getInstance().transform(this.graph, byteStream);
          return;
        }
      }
    } catch (final Exception e) {
      throw new RuntimeException(e);
    }

    if (format.getContentType().equals("text")) {
      final String content = element.getTextContent();
      try {
        byteStream.write(content.getBytes());
      } catch (final IOException e) {
        // byte stream is a byte array output stream
      }
    } else {
      DomHelper.write(element.getOwnerDocument(), byteStream);
    }
  }

  /**
   * Write edges.
   *
   * @param edgesElement
   *          the edges element
   */
  private void writeEdges(final Element edgesElement) {
    final Document document = edgesElement.getOwnerDocument();
    final Set<Edge> edges = this.graph.edgeSet();
    for (final Edge edge : edges) {
      final Element edgeElement = document.createElement("edge");
      edgeElement.setAttribute("source", (String) edge.getSource().getValue(ObjectType.PARAMETER_ID));
      edgeElement.setAttribute("target", (String) edge.getTarget().getValue(ObjectType.PARAMETER_ID));
      edgeElement.setAttribute("type", edge.getType().getName());

      final Element parameters = document.createElement("parameters");
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
    final Document document = DomHelper.createDocument("", "graph");
    final Element graphElement = document.getDocumentElement();
    graphElement.setAttribute("type", this.graph.getType().getName());

    final Element parameters = document.createElement("parameters");
    graphElement.appendChild(parameters);
    writeParameters(this.graph, this.graph.getType(), parameters);

    final Element vertices = document.createElement("vertices");
    graphElement.appendChild(vertices);
    writeVertices(vertices);

    final Element edges = document.createElement("edges");
    graphElement.appendChild(edges);
    writeEdges(edges);

    return graphElement;
  }

  /**
   * Write parameters.
   *
   * @param abstractObject
   *          the abstract object
   * @param type
   *          the type
   * @param parametersElement
   *          the parameters element
   */
  private void writeParameters(final AbstractObject abstractObject, final ObjectType type, final Element parametersElement) {
    final Document document = parametersElement.getOwnerDocument();
    final List<Parameter> parameters = type.getParameters();
    for (final Parameter parameter : parameters) {
      final Element parameterElement = document.createElement("parameter");
      final String parameterName = parameter.getName();
      parameterElement.setAttribute("name", parameterName);

      final Class<?> parameterType = parameter.getType();
      if (parameterType == List.class) {
        final List<?> list = (List<?>) abstractObject.getValue(parameterName);
        if (list != null) {
          for (final Object obj : list) {
            if (obj != null) {
              final Element element = document.createElement("element");
              element.setAttribute("value", obj.toString());
              parameterElement.appendChild(element);
            }
          }
          parametersElement.appendChild(parameterElement);
        }
      } else if (parameterType == Map.class) {
        final Map<?, ?> map = (Map<?, ?>) abstractObject.getValue(parameterName);
        if (map != null) {
          for (final Entry<?, ?> entry : map.entrySet()) {
            final Object key = entry.getKey();
            final Object value = entry.getValue();
            if ((key != null) && (value != null)) {
              final Element entryElt = document.createElement("entry");
              entryElt.setAttribute("key", key.toString());
              entryElt.setAttribute("value", value.toString());
              parameterElement.appendChild(entryElt);
            }
          }
          parametersElement.appendChild(parameterElement);
        }
      } else {
        final Object value = abstractObject.getValue(parameterName);
        if (value != null) {
          parameterElement.setAttribute("value", value.toString());
          parametersElement.appendChild(parameterElement);
        }
      }
    }
  }

  /**
   * Write vertices.
   *
   * @param verticesElement
   *          the vertices element
   */
  private void writeVertices(final Element verticesElement) {
    final Document document = verticesElement.getOwnerDocument();
    final Set<Vertex> vertices = this.graph.vertexSet();
    for (final Vertex vertex : vertices) {
      final Element vertexElement = document.createElement("vertex");
      vertexElement.setAttribute("type", vertex.getType().getName());

      // add layout information
      final Rectangle bounds = (Rectangle) vertex.getValue(Vertex.PROPERTY_SIZE);
      vertexElement.setAttribute("x", String.valueOf(bounds.x));
      vertexElement.setAttribute("y", String.valueOf(bounds.y));

      // and parameters
      final Element parameters = document.createElement("parameters");
      vertexElement.appendChild(parameters);
      writeParameters(vertex, vertex.getType(), parameters);

      verticesElement.appendChild(vertexElement);
    }
  }

}