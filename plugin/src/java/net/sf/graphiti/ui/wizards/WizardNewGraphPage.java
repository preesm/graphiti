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
package net.sf.graphiti.ui.wizards;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import net.sf.graphiti.io.GenericGraphFileWriter;
import net.sf.graphiti.model.Configuration;
import net.sf.graphiti.model.Graph;
import net.sf.graphiti.model.GraphType;

import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.dialogs.WizardNewFileCreationPage;

/**
 * This class provides a page for the new graph wizard.
 * 
 * @author Matthieu Wipliez
 */
public class WizardNewGraphPage extends WizardNewFileCreationPage {

	private Configuration configuration;

	private GraphType graphType;

	/**
	 * Constructor for WizardNewGraphPage.
	 * 
	 * @param selection
	 *            The current resource selection.
	 */
	public WizardNewGraphPage(IStructuredSelection selection) {
		super("newGraph", selection);

		setTitle("Choose file name and parent folder");
		setDescription("Create a new graph.");
	}

	@Override
	public InputStream getInitialContents() {
		Graph graph = new Graph(configuration, graphType);

		ByteArrayOutputStream out = new ByteArrayOutputStream();
		GenericGraphFileWriter writer = new GenericGraphFileWriter(graph);
		writer.write(out);
		return new ByteArrayInputStream(out.toByteArray());
	}

	/**
	 * Sets the configuration for this page.
	 * 
	 * @param configuration
	 *            The configuration to use.
	 */
	public void setConfiguration(Configuration configuration) {
		this.configuration = configuration;
	}

	/**
	 * Sets the configuration for this page.
	 * 
	 * @param configuration
	 *            The configuration to use.
	 */
	public void setGraphType(GraphType graphType) {
		this.graphType = graphType;
	}

}