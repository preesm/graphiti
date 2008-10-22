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
package net.sf.graphiti.ui.commands;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import net.percederberg.grammatica.GrammarException;
import net.percederberg.grammatica.parser.ParserCreationException;
import net.percederberg.grammatica.parser.ParserLogException;
import net.sf.graphiti.grammar.GrammarTransformer;
import net.sf.graphiti.grammar.XsltTransformer;
import net.sf.graphiti.model.FileFormat;
import net.sf.graphiti.model.Graph;
import net.sf.graphiti.model.Vertex;
import net.sf.graphiti.ui.GraphitiPlugin;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.QualifiedName;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.dialogs.ListDialog;
import org.w3c.dom.Element;

/**
 * This class provides facilities to prompt the user for a source port or target
 * port by parsing the refinement (if any), or by asking the user to enter a
 * port name.
 * 
 * @author Matthieu Wipliez
 * 
 */
public class PortChooser {

	private class PortContentProvider implements IStructuredContentProvider {

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

	private String connection;

	private RefinementManager manager;

	/**
	 * Creates a new port chooser using the given refinement manager.
	 * 
	 * @param manager
	 *            The refinement manager.
	 * @param connection
	 *            The title of the connection, as "source - target".
	 */
	public PortChooser(RefinementManager manager, String connection) {
		this.connection = connection;
		this.manager = manager;
	}

	/**
	 * Displays a {@link ListDialog} filled with the given port list, asking the
	 * user to choose one. The <code>edgePort</code> string is used to identify
	 * the port (ie source or target) to choose.
	 * 
	 * @param ports
	 *            A list of port names.
	 * @param edgePort
	 *            A string identifying the port ("source port" or
	 *            "target port").
	 * @return The name of the chosen port.
	 */
	private String choosePort(List<String> ports, String edgePort) {
		IWorkbench workbench = PlatformUI.getWorkbench();
		IWorkbenchWindow window = workbench.getActiveWorkbenchWindow();
		Shell shell = window.getShell();

		ListDialog dialog = new ListDialog(shell);
		dialog.setContentProvider(new PortContentProvider());
		dialog.setLabelProvider(new LabelProvider() {

			@Override
			public String getText(Object element) {
				return (String) element;
			}

		});
		dialog.setAddCancelButton(false);
		dialog.setInput(ports);
		dialog.setMessage("Please choose a " + edgePort + ":");
		dialog.setTitle("Connection: " + connection);
		dialog.open();

		if (dialog.getResult() == null || dialog.getResult().length == 0) {
			return null;
		} else {
			return (String) dialog.getResult()[0];
		}
	}

	/**
	 * Displays an error message with the given exception.
	 * 
	 * @param message
	 *            A description of the error.
	 * @param exception
	 *            An exception.
	 */
	private void errorMessage(String message, Throwable exception) {
		IWorkbench workbench = PlatformUI.getWorkbench();
		IWorkbenchWindow window = workbench.getActiveWorkbenchWindow();
		Shell shell = window.getShell();

		IStatus status = new Status(IStatus.ERROR, GraphitiPlugin.PLUGIN_ID,
				message, exception);
		ErrorDialog dialog = new ErrorDialog(shell, "Error", message, status,
				IStatus.ERROR);
		dialog.open();
	}

	/**
	 * Returns a port name from the current vertex (set by getSourcePort or
	 * getTargetPort).
	 * 
	 * @param edgePort
	 *            The label to use when prompting the user to choose
	 *            ("source port" or "target port").
	 * @param portType
	 *            The port type to check in the refinement: "Input port" or
	 *            "Output port".
	 * @return A port name if found, <code>null</code> otherwise.
	 */
	private String getPort(String edgePort, String portType) {
		IFile sourceFile = null;
		try {
			sourceFile = manager.getIFileFromSelection();
		} catch (FileNotFoundException e) {
		}

		// open the refinement
		if (sourceFile != null) {
			List<String> ports = getPorts(sourceFile, portType);
			if (!ports.isEmpty()) {
				return choosePort(ports, edgePort);
			}
		}

		return null;
	}

	/**
	 * Prompts the user for an arbitrary port name using a simple
	 * {@link InputDialog}. The <code>portName</code> parameter indicates the
	 * role of the given port.
	 * 
	 * @param portName
	 *            "source port" or "target port".
	 * @return The port name, or <code>null</code> if the user entered a blank
	 *         port name (equivalent to "no port").
	 */
	private String getPortName(String portName) {
		IWorkbench workbench = PlatformUI.getWorkbench();
		IWorkbenchWindow window = workbench.getActiveWorkbenchWindow();
		Shell shell = window.getShell();

		InputDialog dialog = new InputDialog(shell,
				"Connection: " + connection, "Please enter a " + portName
						+ " name:", "", null);
		dialog.open();
		String value = dialog.getValue();
		if (value.isEmpty()) {
			return null;
		} else {
			return value;
		}
	}

	/**
	 * Returns the list of ports read from the given file that have the given
	 * port type.
	 * 
	 * @param sourceFile
	 * @param portType
	 * @return
	 */
	private List<String> getPorts(IFile sourceFile, String portType) {
		// refinement graph
		Graph graph = null;

		// get file format
		FileFormat format = null;
		try {
			format = (FileFormat) sourceFile
					.getSessionProperty(new QualifiedName("net.sf.graphiti",
							"format"));
		} catch (CoreException e) {
			e.printStackTrace();
		}

		// parse according to format
		if (format != null) {
			graph = parseRefinement(format, sourceFile);
		}

		// get ports from graph
		if (graph == null) {
			return new ArrayList<String>();
		} else {
			Set<Vertex> vertices = graph.vertexSet();
			List<String> ports = new ArrayList<String>();
			for (Vertex vertex : vertices) {
				if (vertex.getType().equals(portType)) {
					String id = (String) vertex.getValue(Vertex.PARAMETER_ID);
					ports.add(id);
				}
			}
			return ports;
		}
	}

	/**
	 * Returns a port from the given vertex.
	 * 
	 * @param source
	 *            The edge's source vertex.
	 * @return A port name.
	 */
	public String getSourcePort(Vertex source) {
		manager.setVertex(source);
		String port = getPort("source port", "Output port");
		if (port == null) {
			return getPortName("source port");
		} else {
			return port;
		}
	}

	/**
	 * Returns a port from the given vertex.
	 * 
	 * @param target
	 *            The edge's target vertex.
	 * @return A port name.
	 */
	public String getTargetPort(Vertex target) {
		manager.setVertex(target);
		String port = getPort("target port", "Input port");
		if (port == null) {
			return getPortName("target port");
		} else {
			return port;
		}
	}

	/**
	 * Parses the given file using the given file format. The file format can
	 * contain a grammar, in which case the file is parsed with Grammatica,
	 * transformed using XSLT, and parsed with Graphiti. Otherwise, no
	 * pre-parsing takes place, and the file is simply parsed with Graphiti.
	 * 
	 * @param format
	 *            The file format.
	 * @param sourceFile
	 *            The file to parse
	 * @return A graph (or <code>null</code> if there was a parsing problem).
	 */
	private Graph parseRefinement(FileFormat format, IFile sourceFile) {
		Graph graph = null;
		String grammar = format.hasGrammar();
		GenericGraphFileParser parser = new GenericGraphFileParser(
				GraphitiPlugin.getDefault().getConfiguration());

		if (grammar.isEmpty()) {
			try {
				graph = parser.parse(sourceFile);
			} catch (IncompatibleConfigurationFile e) {
				errorMessage("The graph could not be parsed", e);
			}
		} else {

			// parse and transform
			try {
				InputStream is = sourceFile.getContents();
				Element source = new GrammarTransformer(grammar)
						.parse(new InputStreamReader(is));
				XsltTransformer tr = new XsltTransformer(format.hasXslt());
				Element target = tr.transformDomToDom(source, "dummy");
				graph = parser.parse(target);
			} catch (CoreException e) {
				errorMessage("Could not obtain the file contents", e);
			} catch (ClassCastException e) {
				errorMessage(
						"There was a problem with the creation of a DOM document",
						e);
			} catch (GrammarException e) {
				errorMessage("The grammar \"" + grammar + "\" was not valid", e);
			} catch (ParserCreationException e) {
				errorMessage("The parser could not be created", e);
			} catch (ParserLogException e) {
				errorMessage("There was a problem with the parser", e);
			} catch (ClassNotFoundException e) {
				errorMessage("A DOM class could not be found", e);
			} catch (InstantiationException e) {
				errorMessage("A DOM class could not be instantiated", e);
			} catch (IllegalAccessException e) {
				errorMessage("A DOM class could not be accessed", e);
			} catch (IOException e) {
				errorMessage("The file could not be read", e);
			} catch (IncompatibleConfigurationFile e) {
				errorMessage("The graph could not be parsed", e);
			}
		}

		return graph;
	}
}
