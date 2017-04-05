/*******************************************************************************
 * Copyright or © or Copr. IETR/INSA - Rennes (2008 - 2017) :
 *
 * Antoine Morvan <antoine.morvan@insa-rennes.fr> (2017)
 * Clément Guy <clement.guy@insa-rennes.fr> (2014)
 * Matthieu Wipliez <matthieu.wipliez@insa-rennes.fr> (2008 - 2010)
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
package org.ietr.dftools.graphiti.ui.wizards;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.ietr.dftools.graphiti.model.Configuration;
import org.ietr.dftools.graphiti.model.Edge;
import org.ietr.dftools.graphiti.model.Graph;
import org.ietr.dftools.graphiti.model.ObjectType;
import org.ietr.dftools.graphiti.model.Vertex;
import org.ietr.dftools.graphiti.ui.editors.GraphEditor;

/**
 * This class provides a convert page for the save as wizard.
 * 
 * @author Matthieu Wipliez
 */
public class WizardConvertPage extends WizardPage implements IGraphTypeSettable {

	private List<Combo> edgeComboList;

	private Configuration newConfiguration;

	private ObjectType newGraphType;

	private Set<ObjectType> originalEdgeTypes;

	private Graph originalGraph;

	private Set<ObjectType> originalVertexTypes;

	private List<Combo> vertexComboList;

	/**
	 * Constructor for SampleNewWizardPage.
	 * 
	 * @param selection
	 */
	public WizardConvertPage(IStructuredSelection selection) {
		super("convertGraph");

		setTitle("Convert types");

		Object obj = selection.getFirstElement();
		if (obj instanceof GraphEditor) {
			GraphEditor editor = (GraphEditor) obj;
			originalGraph = editor.getContents();

			// fills the original graph, vertex and edge types
			Configuration configuration = originalGraph.getConfiguration();
			originalEdgeTypes = configuration.getEdgeTypes();
			originalVertexTypes = configuration.getVertexTypes();
		}
	}

	private void convertEdgeTypes(Graph graph,
			Map<ObjectType, ObjectType> edgeTypes) {
		Set<Edge> edges = originalGraph.edgeSet();
		for (Edge edge : edges) {
			ObjectType newType = edgeTypes.get(edge.getType());
			if (newType != null) {
				edge = new Edge(edge);
				String sourceId = (String) edge.getSource().getValue(
						ObjectType.PARAMETER_ID);
				String targetId = (String) edge.getTarget().getValue(
						ObjectType.PARAMETER_ID);
				Vertex source = graph.findVertex(sourceId);
				Vertex target = graph.findVertex(targetId);

				if (source != null && target != null) {
					edge.setSource(source);
					edge.setTarget(target);
					edge.setType(newType);
					graph.addEdge(edge);
				}
			}
		}
	}

	private void convertVertexTypes(Graph graph,
			Map<ObjectType, ObjectType> vertexTypes) {
		Set<Vertex> vertices = originalGraph.vertexSet();
		for (Vertex vertex : vertices) {
			ObjectType newType = vertexTypes.get(vertex.getType());
			if (newType != null) {
				vertex = new Vertex(vertex);
				vertex.setType(newType);
				graph.addVertex(vertex);
			}
		}
	}

	@Override
	public void createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout();
		container.setLayout(layout);

		layout.numColumns = 2;
		layout.verticalSpacing = 9;

		createExplanationLabel(container);
		createVertexTypes(container);
		createEdgeTypes(container);

		setControl(container);
		setPageComplete(true);
	}

	/**
	 * Creates a {@link Combo} for each edge type in the original configuration.
	 * 
	 * @param parent
	 *            The parent {@link Composite}.
	 */
	private void createEdgeTypes(Composite parent) {
		edgeComboList = new ArrayList<Combo>();
		for (ObjectType type : originalEdgeTypes) {
			Label label = new Label(parent, SWT.NULL);
			label.setText("Convert \"" + type.getName() + "\" to:");

			Combo edgeCombo = new Combo(parent, SWT.DROP_DOWN | SWT.READ_ONLY
					| SWT.SIMPLE);
			edgeComboList.add(edgeCombo);
		}
	}

	/**
	 * Creates a {@link Label} to tell the user what to do.
	 * 
	 * @param parent
	 *            The parent {@link Composite}.
	 */
	private void createExplanationLabel(Composite parent) {
		Label label = new Label(parent, SWT.NULL);
		label.setText("Please choose how the following vertices and edges "
				+ "shall be converted:");
		GridData data = new GridData();
		data.horizontalSpan = 2;
		label.setLayoutData(data);
	}

	/**
	 * Creates a {@link Combo} for each vertex type in the original
	 * configuration.
	 * 
	 * @param parent
	 *            The parent {@link Composite}.
	 */
	private void createVertexTypes(Composite parent) {
		vertexComboList = new ArrayList<Combo>();
		for (ObjectType type : originalVertexTypes) {
			Label label = new Label(parent, SWT.NULL);
			label.setText("Convert \"" + type.getName() + "\" to:");

			Combo vertexCombo = new Combo(parent, SWT.DROP_DOWN | SWT.READ_ONLY
					| SWT.SIMPLE);
			vertexComboList.add(vertexCombo);
		}
	}

	/**
	 * Returns a {@link Map} that maps an existing {@link EdgeType} to a new
	 * one. If the new type is not specified (i.e. left blank by the user), no
	 * mapping is inserted.
	 * 
	 * @return A {@link Map} that maps an existing {@link EdgeType} to a new
	 *         one.
	 */
	private Map<ObjectType, ObjectType> fillEdgeTypes() {
		Map<ObjectType, ObjectType> edgeTypes = new HashMap<ObjectType, ObjectType>();
		int i = 0;
		for (ObjectType type : originalEdgeTypes) {
			Combo combo = edgeComboList.get(i);
			i++;
			int index = combo.getSelectionIndex();
			if (index != -1) {
				String name = combo.getItem(index);
				ObjectType newType = newConfiguration.getEdgeType(name);
				edgeTypes.put(type, newType);
			}
		}

		return edgeTypes;
	}

	/**
	 * Returns a {@link Map} that maps an existing {@link VertexType} to a new
	 * one. If the new type is not specified (i.e. left blank by the user), no
	 * mapping is inserted.
	 * 
	 * @return A {@link Map} that maps an existing {@link VertexType} to a new
	 *         one.
	 */
	private Map<ObjectType, ObjectType> fillVertexTypes() {
		Map<ObjectType, ObjectType> vertexTypes = new HashMap<ObjectType, ObjectType>();
		int i = 0;
		for (ObjectType type : originalVertexTypes) {
			Combo combo = vertexComboList.get(i);
			i++;
			int index = combo.getSelectionIndex();
			if (index != -1) {
				String name = combo.getItem(index);
				ObjectType newType = newConfiguration.getVertexType(name);
				vertexTypes.put(type, newType);
			}
		}

		return vertexTypes;
	}

	/**
	 * Returns the converted graph.
	 * 
	 * @return The converted graph.
	 */
	public Graph getGraph() {
		// creates a new empty graph with the same properties as originalGraph
		// but configuration and type, that are overridden by newConfiguration
		// and newGraphType
		Graph graph = new Graph(originalGraph, newConfiguration, newGraphType);

		// change vertex and edge types
		Map<ObjectType, ObjectType> vertexTypes = fillVertexTypes();
		Map<ObjectType, ObjectType> edgeTypes = fillEdgeTypes();
		convertVertexTypes(graph, vertexTypes);
		convertEdgeTypes(graph, edgeTypes);

		return graph;
	}

	@Override
	public void setGraphType(Configuration configuration, ObjectType type) {
		newConfiguration = configuration;
		newGraphType = type;
		((IGraphTypeSettable) getNextPage()).setGraphType(configuration, type);

		updateDescription();
		updateVertexTypes();
		updateEdgeTypes();

		getControl().pack();
	}

	/**
	 * Updates the description of this page.
	 */
	private void updateDescription() {
		setDescription("Convert \"" + originalGraph.getType().getName()
				+ "\" to \"" + newGraphType.getName() + "\".");
	}

	/**
	 * Updates each edge combo list using the new configuration's edge types.
	 */
	private void updateEdgeTypes() {
		for (Combo edgeCombo : edgeComboList) {
			Set<ObjectType> newEdges = newConfiguration.getEdgeTypes();
			String[] items = new String[newEdges.size()];
			int i = 0;
			for (ObjectType edgeType : newEdges) {
				items[i] = edgeType.getName();
				i++;
			}
			edgeCombo.setItems(items);
			edgeCombo.select(-1);
		}
	}

	/**
	 * Updates each vertex combo list using the new configuration's vertex
	 * types.
	 */
	private void updateVertexTypes() {
		for (Combo vertexCombo : vertexComboList) {
			Set<ObjectType> newVertices = newConfiguration.getVertexTypes();
			String[] items = new String[newVertices.size()];
			int i = 0;
			for (ObjectType vertexType : newVertices) {
				items[i] = vertexType.getName();
				i++;
			}
			vertexCombo.setItems(items);
			vertexCombo.select(-1);
		}
	}

}
