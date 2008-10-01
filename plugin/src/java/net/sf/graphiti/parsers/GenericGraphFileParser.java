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
package net.sf.graphiti.parsers;

import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

import net.sf.graphiti.model.Configuration;
import net.sf.graphiti.model.Graph;
import net.sf.graphiti.ontology.OntologyFactory;
import net.sf.graphiti.ontology.XMLSchemaType;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.w3c.dom.DOMConfiguration;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.bootstrap.DOMImplementationRegistry;
import org.w3c.dom.ls.DOMImplementationLS;
import org.w3c.dom.ls.LSInput;
import org.w3c.dom.ls.LSParser;

/**
 * This class provides a generic graph file parser.
 * 
 * @author Jonathan Piat
 * @author Matthieu Wipliez
 * 
 */
public class GenericGraphFileParser {

	private Configuration configuration;

	/**
	 * Creates a generic graph parser using the given document configuration
	 * tree.
	 * 
	 * @param configuration
	 *            The root of the {@link Configuration} tree.
	 */
	public GenericGraphFileParser(Configuration configuration) {
		this.configuration = configuration;
	}

	/**
	 * Tries to parse file with every configuration in the {@link Configuration}
	 * tree. This method tries first by the most specialized ontologies, ie by
	 * the leafs of the tree.
	 * 
	 * @param file
	 *            The file to parse.
	 * @param configuration
	 *            A {@link Configuration}. When called by {@link #parse(IFile)},
	 *            this is the root of the document configuration tree.
	 * @return True if <code>file</code> could be parsed with
	 *         <code>configuration</code>, <code>false</code> otherwise.
	 */
	private Graph parse(Document document, Configuration configuration) {
		List<Configuration> children = configuration
				.getConfigurationList(false);
		if (children.isEmpty()) {
			// We have a leaf, try to parse
			return parseWithConfiguration(document, configuration);
		} else {
			// We try the children
			Iterator<Configuration> it = children.iterator();
			Graph isParsed = null;
			while (it.hasNext() && (isParsed == null)) {
				isParsed = parse(document, it.next());
			}

			// And then ourselves
			if (isParsed == null) {
				isParsed = parseWithConfiguration(document, configuration);
			}

			return isParsed;
		}
	}

	public Graph parse(Element element) throws IncompatibleConfigurationFile {
		// Parses the DOM
		Graph graph = parse(element.getOwnerDocument(), configuration);
		if (graph == null) {
			throw new IncompatibleConfigurationFile("The document "
					+ element.getOwnerDocument().getDocumentURI()
					+ " could not be parsed.");
		} else {
			return graph;
		}
	}

	/**
	 * Parses the given {@link IFile} with the semantics defined in the
	 * configuration file.
	 * 
	 * @param file
	 *            The file to parse.
	 * @return The new {@link GraphitiDocument}.
	 * @throws IncompatibleConfigurationFile
	 *             If the given file could not be parsed with any of the
	 *             document configurations.
	 */
	public Graph parse(IFile file) throws IncompatibleConfigurationFile {
		try {
			// DOM LS implementation
			DOMImplementationLS impl = (DOMImplementationLS) DOMImplementationRegistry
					.newInstance().getDOMImplementation("Core 3.0 XML 3.0 LS");

			// input
			LSInput input = impl.createLSInput();
			InputStream is = file.getContents(true);
			input.setByteStream(is);

			// parse without comments and whitespace
			LSParser builder = impl.createLSParser(
					DOMImplementationLS.MODE_SYNCHRONOUS, null);
			DOMConfiguration config = builder.getDomConfig();
			config.setParameter("comments", false);
			config.setParameter("element-content-whitespace", false);
			Document document = builder.parse(input);

			// Parses the DOM
			Graph graph = parse(document, configuration);
			if (graph == null) {
				throw new IncompatibleConfigurationFile("The file "
						+ file.getName() + " could not be parsed.");
			} else {
				return graph;
			}
		} catch (ClassCastException e) {
			throw new IncompatibleConfigurationFile(e);
		} catch (ClassNotFoundException e) {
			throw new IncompatibleConfigurationFile(e);
		} catch (InstantiationException e) {
			throw new IncompatibleConfigurationFile(e);
		} catch (IllegalAccessException e) {
			throw new IncompatibleConfigurationFile(e);
		} catch (CoreException e) {
			throw new IncompatibleConfigurationFile(e);
		}
	}

	/**
	 * Parses the given DOM using the semantics defined in the configuration
	 * file.
	 * 
	 * @param document
	 *            The DOM to parse.
	 * @return True if document could be parsed, false otherwise.
	 */
	private Graph parseWithConfiguration(Document document,
			Configuration configuration) {
		Element docElement = document.getDocumentElement();

		// Retrieves the document element
		OntologyFactory factory = configuration.getOntologyFactory();
		XMLSchemaType ontDocElement = factory.getDocumentElement();
		if (ontDocElement != null) {
			SchemaParser parser = new SchemaParser(configuration);

			try {
				return parser.parse(ontDocElement, docElement);
			} catch (NotCompatibleException e) {
				// if not compatible, we simply return null.
			}
		}

		return null;
	}
}
