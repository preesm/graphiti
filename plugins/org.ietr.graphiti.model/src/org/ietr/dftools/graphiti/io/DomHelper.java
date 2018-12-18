/**
 * Copyright or © or Copr. IETR/INSA - Rennes (2008 - 2018) :
 *
 * Antoine Morvan <antoine.morvan@insa-rennes.fr> (2017 - 2018)
 * Clément Guy <clement.guy@insa-rennes.fr> (2014)
 * Matthieu Wipliez <matthieu.wipliez@insa-rennes.fr> (2008 - 2011)
 *
 * This software is a computer program whose purpose is to help prototyping
 * parallel applications using dataflow formalism.
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
 */
package org.ietr.dftools.graphiti.io;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.apache.xml.serialize.OutputFormat;
import org.apache.xml.serialize.XMLSerializer;
import org.ietr.dftools.graphiti.GraphitiException;
import org.w3c.dom.DOMConfiguration;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.bootstrap.DOMImplementationRegistry;
import org.w3c.dom.ls.DOMImplementationLS;
import org.w3c.dom.ls.LSInput;
import org.w3c.dom.ls.LSParser;

/**
 * This class provides various methods to reduce the amount of copy/paste when dealing with DOM.
 *
 * @author Matthieu Wipliez
 *
 */
public class DomHelper {

  private DomHelper() {
    // disallow instantiation
  }

  /**
   * Creates a new document with the given namespace and document element.
   *
   * @param namespaceURI
   *          The document namespace, may be <code>""</code>.
   * @param qualifiedName
   *          The document element name
   * @return the created document
   */
  public static Document createDocument(final String namespaceURI, final String qualifiedName) {
    final DOMImplementation impl = DomHelper.getDOMImplementation();
    return impl.createDocument(namespaceURI, qualifiedName, null);
  }

  /**
   * Returns a DOM implementation that has the following features:
   * <ul>
   * <li>Core 3.0</li>
   * <li>XML 3.0</li>
   * <li>LS</li>
   * </ul>
   *
   * @return A {@link DOMImplementation} object that can be cast to {@link DOMImplementationLS}.
   */
  public static DOMImplementation getDOMImplementation() {
    try {
      final DOMImplementationRegistry registry = DOMImplementationRegistry.newInstance();
      return registry.getDOMImplementation("Core 3.0 XML 3.0 LS");
    } catch (final Exception e) {
      throw new GraphitiException("Could not get DOM Implementation", e);
    }
  }

  /**
   * Returns the first child of <code>node</code> that has the given name, or <code>null</code>.
   *
   * @param node
   *          A node.
   * @param name
   *          The name of the node we are looking for.
   * @return The first node whose name matches, or <code>null</code>.
   */
  public static Node getFirstChildNamed(final Node node, final String name) {
    return DomHelper.getFirstSiblingNamed(node.getFirstChild(), name);
  }

  /**
   * Returns the first sibling of <code>node</code>, or <code>node</code> itself, which has the given name. If none is
   * found, the function returns <code>null</code>.
   *
   * @param node
   *          A node.
   * @param name
   *          The name of the node we are looking for.
   * @return The first node whose name matches, or <code>null</code>.
   */
  public static Node getFirstSiblingNamed(Node node, final String name) {
    while ((node != null) && !node.getNodeName().equals(name)) {
      node = node.getNextSibling();
    }

    return node;
  }

  /**
   * Parses the input stream and returns a DOM {@link Document}.
   *
   * @param byteStream
   *          The input stream.
   * @return The {@link Document} parsed from the input.
   */
  public static Document parse(final InputStream byteStream) {
    // input
    final DOMImplementationLS impl = (DOMImplementationLS) DomHelper.getDOMImplementation();
    final LSInput input = impl.createLSInput();
    input.setByteStream(byteStream);

    // parse without comments and whitespace
    final LSParser builder = impl.createLSParser(DOMImplementationLS.MODE_SYNCHRONOUS, null);
    final DOMConfiguration config = builder.getDomConfig();
    config.setParameter("comments", false);
    config.setParameter("element-content-whitespace", false);

    // returns the document parsed from the input
    return builder.parse(input);
  }

  /**
   * Writes the given document to the given output stream.
   *
   * @param document
   *          A DOM document.
   * @param byteStream
   *          The {@link OutputStream} to write to.
   */
  public static void write(final Document document, final OutputStream byteStream) {
    final OutputFormat format = new OutputFormat(document, "UTF-8", true);
    format.setIndent(4);
    format.setLineSeparator("\n");
    format.setLineWidth(65);

    final XMLSerializer serializer = new XMLSerializer(byteStream, format);
    try {
      serializer.serialize(document);
    } catch (final IOException e) {
      throw new GraphitiException("Could not write Graph", e);
    }
  }

}
