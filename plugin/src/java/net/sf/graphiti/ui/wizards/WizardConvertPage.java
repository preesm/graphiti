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

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import net.sf.graphiti.model.Configuration;
import net.sf.graphiti.model.EdgeType;
import net.sf.graphiti.model.Graph;
import net.sf.graphiti.model.GraphType;
import net.sf.graphiti.model.VertexType;
import net.sf.graphiti.ui.editors.GraphEditor;

import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

/**
 * This class provides a convert page for the save as wizard.
 * 
 * @author Matthieu Wipliez
 */
public class WizardConvertPage extends WizardPage implements IGraphTypeSettable {

	private Configuration configuration;

	private List<Combo> edgeComboList;

	private GraphType newGraphType;

	private Set<EdgeType> originalEdgeTypes;

	private GraphType originalGraphType;

	private Set<VertexType> originalVertexTypes;

	private List<Combo> vertexComboList;

	/**
	 * Constructor for SampleNewWizardPage.
	 * 
	 * @param selection
	 */
	public WizardConvertPage(IStructuredSelection selection) {
		super("convertGraph");

		setTitle("Convert types");
		setDescription("Prout");

		Object obj = selection.getFirstElement();
		if (obj instanceof GraphEditor) {
			GraphEditor editor = (GraphEditor) obj;
			Graph graph = editor.getContents();

			Configuration configuration = graph.getConfiguration();
			originalEdgeTypes = configuration.getEdgeTypes();
			originalGraphType = graph.getType();
			originalVertexTypes = configuration.getVertexTypes();
		}
	}

	@Override
	public void createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout();
		container.setLayout(layout);

		layout.numColumns = 2;
		layout.verticalSpacing = 9;

		createGraphTypes(container);
		createVertexTypes(container);
		createEdgeTypes(container);

		setControl(container);
		setPageComplete(false);
	}

	private void createEdgeTypes(Composite parent) {
		edgeComboList = new ArrayList<Combo>();
		for (EdgeType type : originalEdgeTypes) {
			Label label = new Label(parent, SWT.NULL);
			label.setText("Convert \"" + type.getName() + "\" to:");

			Combo edgeCombo = new Combo(parent, SWT.DROP_DOWN | SWT.READ_ONLY
					| SWT.SIMPLE);
			edgeComboList.add(edgeCombo);
		}
	}

	private void createGraphTypes(Composite parent) {
		Label label = new Label(parent, SWT.NULL);
		label.setText("Please choose how the following vertices and edges "
				+ "shall be converted:");
		GridData data = new GridData();
		data.horizontalSpan = 2;
		label.setLayoutData(data);
	}

	private void createVertexTypes(Composite parent) {
		vertexComboList = new ArrayList<Combo>();
		for (VertexType type : originalVertexTypes) {
			Label label = new Label(parent, SWT.NULL);
			label.setText("Convert \"" + type.getName() + "\" to:");

			Combo vertexCombo = new Combo(parent, SWT.DROP_DOWN | SWT.READ_ONLY
					| SWT.SIMPLE);
			vertexComboList.add(vertexCombo);
		}
	}

	@Override
	public void setGraphType(Configuration configuration, GraphType type) {
		this.configuration = configuration;
		this.newGraphType = type;
		((IGraphTypeSettable) getNextPage()).setGraphType(configuration, type);

		updateGraphType();
		updateVertexTypes();
		updateEdgeTypes();

		getControl().pack();
	}

	private void updateEdgeTypes() {
		for (Combo edgeCombo : edgeComboList) {
			Set<EdgeType> newEdges = configuration.getEdgeTypes();
			String[] items = new String[newEdges.size()];
			int i = 0;
			for (EdgeType edgeType : newEdges) {
				items[i] = edgeType.getName();
				i++;
			}
			edgeCombo.setItems(items);
			edgeCombo.select(-1);
		}
	}

	private void updateGraphType() {
		setDescription("Convert \"" + originalGraphType.getName() + "\" to \""
				+ newGraphType.getName() + "\".");
	}

	private void updateVertexTypes() {
		for (Combo vertexCombo : vertexComboList) {
			Set<VertexType> newVertices = configuration.getVertexTypes();
			String[] items = new String[newVertices.size()];
			int i = 0;
			for (VertexType vertexType : newVertices) {
				items[i] = vertexType.getName();
				i++;
			}
			vertexCombo.setItems(items);
			vertexCombo.select(-1);
		}
	}
}