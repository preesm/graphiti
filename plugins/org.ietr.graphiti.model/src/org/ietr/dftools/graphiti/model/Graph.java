/*******************************************************************************
 * Copyright or © or Copr. IETR/INSA - Rennes (2009 - 2017) :
 *
 * Antoine Morvan <antoine.morvan@insa-rennes.fr> (2017)
 * Clément Guy <clement.guy@insa-rennes.fr> (2014)
 * Matthieu Wipliez <matthieu.wipliez@insa-rennes.fr> (2009 - 2011)
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
package org.ietr.dftools.graphiti.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.jgrapht.graph.AbstractBaseGraph;
import org.jgrapht.graph.DirectedMultigraph;
import org.jgrapht.graph.Multigraph;

/**
 * This class is an attributed multigraph (allowing more than one connection
 * between any two vertices). It may be directed or not, as specified at
 * creation.
 * 
 * @author Jonathan Piat
 * @author Matthieu Wipliez
 * 
 */
public class Graph extends AbstractObject {

	/**
	 * String for the "child added" property. Set when a vertex or a port is
	 * added to a vertex.
	 */
	public static final String PROPERTY_ADD = "child added";

	/**
	 * String for the "hasLayout" property. This is a boolean property
	 * indicating if the graph has layout information or not. If it has none,
	 * the graph should be automatically laid out.
	 */
	public static final String PROPERTY_HAS_LAYOUT = "has layout";

	/**
	 * String for the "child removed" property. Set when a vertex or a port is
	 * removed from a vertex.
	 */
	public static final String PROPERTY_REMOVE = "child removed";

	/**
	 * The configuration associated with this graph.
	 */
	private Configuration configuration;

	private IPath fileName;

	private AbstractBaseGraph<Vertex, Edge> graph;

	private Map<String, Vertex> vertices;

	/**
	 * Creates a new graph, directed or not.
	 * 
	 * @param configuration
	 *            The configuration to use with this graph.
	 * @param type
	 *            The graph type.
	 * @param directed
	 *            Specifies whether the graph should be directed or not.
	 */
	public Graph(Configuration configuration, ObjectType type, boolean directed) {
		super(type);

		this.configuration = configuration;
		if (directed) {
			graph = new DirectedMultigraph<Vertex, Edge>(Edge.class);
		} else {
			graph = new Multigraph<Vertex, Edge>(Edge.class);
		}
		vertices = new HashMap<String, Vertex>();

		// set default values
		List<Parameter> parameters = type.getParameters();
		for (Parameter parameter : parameters) {
			setValue(parameter.getName(), parameter.getDefault());
		}
		
		// initially, no layout
		setValue(PROPERTY_HAS_LAYOUT, false);
	}

	/**
	 * Creates a new empty graph with the same properties as the source graph
	 * but configuration and type, that are overridden by the supplied
	 * parameters.
	 * 
	 * @param graph
	 *            The source graph.
	 * @param configuration
	 *            The configuration to use with this graph.
	 * @param type
	 *            The graph type.
	 */
	public Graph(Graph graph, Configuration configuration, ObjectType type) {
		super(graph);
		this.configuration = configuration;
		if (graph.isDirected()) {
			this.graph = new DirectedMultigraph<Vertex, Edge>(Edge.class);
		} else {
			this.graph = new Multigraph<Vertex, Edge>(Edge.class);
		}
		this.vertices = new HashMap<String, Vertex>();
		this.type = type;
	}

	/**
	 * 
	 * @param edge
	 *            The edge to add
	 * @return
	 */
	public boolean addEdge(Edge edge) {
		Vertex source = edge.getSource();
		Vertex target = edge.getTarget();
		boolean res = graph.addEdge(source, target, edge);
		source.firePropertyChange(Vertex.PROPERTY_SRC_VERTEX, null, source);
		target.firePropertyChange(Vertex.PROPERTY_DST_VERTEX, null, target);
		return res;
	}

	/**
	 * @see AbstractBaseGraph#addVertex(Vertex)
	 * @param child
	 *            The vertex to add
	 * @return true if the vertex is added, false if command failed
	 */
	public boolean addVertex(Vertex child) {
		boolean res = graph.addVertex(child);
		if (res) {
			// only updates this graph and fires property change if vertex not
			// already present in the graph

			child.parent = this;

			vertices.put((String) child.getValue(ObjectType.PARAMETER_ID),
					child);

			firePropertyChange(PROPERTY_ADD, null, child);
		}
		return res;
	}

	/**
	 * Changes the identifier of the given vertex.
	 * 
	 * @param vertex
	 *            A vertex.
	 * @param id
	 *            Its new id.
	 */
	void changeVertexId(Vertex vertex, String id) {
		String oldId = (String) vertex.getValue(ObjectType.PARAMETER_ID);
		if (oldId != null && id != null && !oldId.equals(id)) {
			vertices.remove(oldId);
			vertices.put(id, vertex);
		}
	}

	/**
	 * @see AbstractBaseGraph#edgeSet()
	 * @return
	 */
	public Set<Edge> edgeSet() {
		return graph.edgeSet();
	}

	/**
	 * Finds and returns the vertex of the graph with the given vertex id.
	 * 
	 * @param vertexId
	 *            The vertex id.
	 * @return The vertex with the given id, or <code>null</code> if not found.
	 */
	public Vertex findVertex(String vertexId) {
		return vertices.get(vertexId);
	}

	/**
	 * Returns the configuration associated with this Graph.
	 * 
	 * @return The configuration associated with this Graph.
	 */
	public Configuration getConfiguration() {
		return configuration;
	}

	/**
	 * Returns the file in which this graph is defined.
	 * 
	 * @return the file in which this graph is defined
	 */
	public IFile getFile() {
		IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
		IFile file = root.getFile(fileName);
		return file;
	}

	/**
	 * Returns the name of the file in which this graph is defined.
	 * 
	 * @return the name of the file in which this graph is defined
	 */
	public IPath getFileName() {
		return fileName;
	}

	/**
	 * @see AbstractBaseGraph#incomingEdgesOf(Vertex)
	 * @param vertex
	 * @return
	 */
	public Set<Edge> incomingEdgesOf(Vertex vertex) {
		return graph.incomingEdgesOf(vertex);
	}

	/**
	 * Returns true if this graph is directed.
	 * 
	 * @return True if this graph is directed, false otherwise.
	 */
	public boolean isDirected() {
		return graph instanceof DirectedMultigraph<?, ?>;
	}

	/**
	 * @see AbstractBaseGraph#outgoingEdgesOf(Vertex)
	 * @param vertex
	 * @return
	 */
	public Set<Edge> outgoingEdgesOf(Vertex vertex) {
		return graph.outgoingEdgesOf(vertex);
	}

	/**
	 * @see AbstractBaseGraph#removeEdge(Edge)
	 * @param edge
	 * @return
	 */
	public boolean removeEdge(Edge edge) {
		Vertex source = edge.getSource();
		Vertex target = edge.getTarget();
		boolean res = graph.removeEdge(edge);
		source.firePropertyChange(Vertex.PROPERTY_SRC_VERTEX, source, null);
		target.firePropertyChange(Vertex.PROPERTY_DST_VERTEX, target, null);
		return res;
	}

	/**
	 * @see AbstractBaseGraph#removeVertex(Graph)
	 * @param child
	 * @return
	 */
	public boolean removeVertex(Vertex child) {
		boolean res = graph.removeVertex(child);
		child.parent = null;

		vertices.remove(child.getValue(ObjectType.PARAMETER_ID));

		firePropertyChange(PROPERTY_REMOVE, null, child);
		return res;
	}

	/**
	 * Sets the name of the file in which this graph is defined.
	 * 
	 * @param fileName
	 *            a file name
	 */
	public void setFileName(IPath fileName) {
		this.fileName = fileName;
	}

	@Override
	public String toString() {
		return type.getName();
	}

	/**
	 * @see AbstractBaseGraph#vertexSet()
	 * @return
	 */
	public Set<Vertex> vertexSet() {
		return graph.vertexSet();
	}

}
