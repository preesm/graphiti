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
import java.util.List;

import net.percederberg.grammatica.GrammarException;
import net.percederberg.grammatica.parser.ParserCreationException;
import net.percederberg.grammatica.parser.ParserLogException;
import net.sf.graphiti.grammar.GrammarTransformer;
import net.sf.graphiti.grammar.XsltTransformer;
import net.sf.graphiti.model.Configuration;
import net.sf.graphiti.model.FileFormat;
import net.sf.graphiti.model.Graph;

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
 * @author Administrateur
 * 
 */
public class GenericGraphFileParser {

	private List<Configuration> configurations;

	public GenericGraphFileParser(List<Configuration> configurations) {
		this.configurations = configurations;
	}

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
						// DOM LS implementation
						DOMImplementationRegistry registry = DOMImplementationRegistry
								.newInstance();
						DOMImplementationLS impl = (DOMImplementationLS) registry
								.getDOMImplementation("Core 3.0 XML 3.0 LS");

						// input
						LSInput input = impl.createLSInput();
						input.setByteStream(in);

						// parse without comments and whitespace
						LSParser builder = impl.createLSParser(
								DOMImplementationLS.MODE_SYNCHRONOUS, null);
						DOMConfiguration config = builder.getDomConfig();
						config.setParameter("comments", false);
						config
								.setParameter("element-content-whitespace",
										false);
						Document document = builder.parse(input);
						element = document.getDocumentElement();
					}

					XsltTransformer transformer = new XsltTransformer(
							transformation);
					Element target = element.getOwnerDocument().createElement(
							"dummy");
					transformer.transformDomToDom(element, target);
					element = target;
				}
			}

			return parseElement(configuration, element);
		} catch (IOException e) {
			throw new IncompatibleConfigurationFile(e);
		} catch (ParserLogException e) {
			throw new IncompatibleConfigurationFile(e);
		} catch (GrammarException e) {
			throw new IncompatibleConfigurationFile(e);
		} catch (ClassCastException e) {
			throw new IncompatibleConfigurationFile(e);
		} catch (ParserCreationException e) {
			throw new IncompatibleConfigurationFile(e);
		} catch (ClassNotFoundException e) {
			throw new IncompatibleConfigurationFile(e);
		} catch (InstantiationException e) {
			throw new IncompatibleConfigurationFile(e);
		} catch (IllegalAccessException e) {
			throw new IncompatibleConfigurationFile(e);
		}
	}

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

	private Graph parseElement(Configuration configuration, Element element)
			throws IncompatibleConfigurationFile {
		return null;
	}

}
