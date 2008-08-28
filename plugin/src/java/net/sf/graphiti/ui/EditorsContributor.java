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
package net.sf.graphiti.ui;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import net.sf.graphiti.model.Configuration;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IContributor;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.spi.RegistryContributor;
import org.osgi.framework.BundleContext;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * This class adds an editor contribution based on the file extensions declared
 * in the configuration passed at construction time.
 * 
 * @author Matthieu Wipliez
 * 
 */
public class EditorsContributor {

	/**
	 * Creates a new contributor to the "org.eclipse.ui.editors" extension
	 * point.
	 * 
	 * @param context
	 *            The GraphitiPlugin#start bundle context.
	 * @param configuration
	 *            The configuration obtained from the ontologies.
	 * @throws CoreException
	 *             Whenever something bad happens.
	 */
	public EditorsContributor(BundleContext context, Configuration configuration)
			throws CoreException {
		addContribution(context, configuration);
	}

	/**
	 * Adds a contribution to the extension registry using a plugin.xml-like
	 * stream generated by getExtensionInputStream().
	 */
	@SuppressWarnings("restriction")
	private void addContribution(BundleContext context,
			Configuration configuration) {
		IExtensionRegistry registry = Platform.getExtensionRegistry();

		InputStream is = getExtensionInputStream(configuration);
		long id = context.getBundle().getBundleId();
		IContributor contributor = new RegistryContributor(Long.toString(id),
				"net.sf.graphiti", null, null);
		boolean persist = false;
		String name = "Graphiti editor";
		ResourceBundle translationBundle = null;
		Object token = ((org.eclipse.core.internal.registry.ExtensionRegistry) registry)
				.getTemporaryUserToken();

		registry.addContribution(is, contributor, persist, name,
				translationBundle, token);
	}

	/**
	 * Add an extension to <code>org.eclipse.ui.editors</code>.
	 * 
	 * @param document
	 *            The DOM document.
	 * @param root
	 *            The DOM document element.
	 */
	private void addExtensions(Configuration configuration, Document document,
			Element root) {
		// add all file extensions to a set (in case one extension is present
		// several times, such as .xml)
		Set<String> extensionSet = new HashSet<String>();
		for (Configuration config : configuration.getConfigurationList(true)) {
			String[] fileExts = config.getFileExtensions();
			for (String fileExt : fileExts) {
				extensionSet.add(fileExt);
			}
		}

		// convert to string
		String[] extensionArray = extensionSet.toArray(new String[] {});
		String extensions = "";
		for (int i = 0; i < extensionArray.length - 1; i++) {
			extensions += extensionArray[i] + ", ";
		}
		extensions += extensionArray[extensionArray.length - 1];

		// create the XML fragment: extension element
		Element extension = document.createElement("extension");
		extension.setAttribute("point", "org.eclipse.ui.editors");
		root.appendChild(extension);

		// editor element
		Element editor = document.createElement("editor");
		editor.setAttribute("class", "net.sf.graphiti.ui.editors.GraphEditor");
		editor.setAttribute("contributorClass",
				"net.sf.graphiti.ui.editors.GraphActionBarContributor");
		editor.setAttribute("default", "false");
		editor.setAttribute("extensions", extensions);
		editor.setAttribute("icon", "icons/graphiti.gif");
		editor.setAttribute("id", "net.sf.graphiti.ui.editors.GraphEditor");
		editor.setAttribute("name", "Graphiti graph editor");

		extension.appendChild(editor);
	}

	/**
	 * Creates a DOM document, fills it with extension points, and returns it as
	 * a byte array input stream.
	 * 
	 * @return A {@link ByteArrayInputStream}.
	 */
	private InputStream getExtensionInputStream(Configuration configuration) {
		// Gets the document builder factory
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();

			// Root element
			Document document = builder.newDocument();
			Element root = document.createElement("plugin");
			document.appendChild(root);

			// Adds extensions
			addExtensions(configuration, document, root);

			// Set up the output transformer
			TransformerFactory transfac = TransformerFactory.newInstance();
			Transformer trans = transfac.newTransformer();
			trans.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
			trans.setOutputProperty(OutputKeys.INDENT, "yes");

			// Print the DOM node
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			StreamResult result = new StreamResult(bos);
			DOMSource source = new DOMSource(document);
			trans.transform(source, result);

			return new ByteArrayInputStream(bos.toByteArray());
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (TransformerException e) {
			e.printStackTrace();
		}

		return null;
	}

}
