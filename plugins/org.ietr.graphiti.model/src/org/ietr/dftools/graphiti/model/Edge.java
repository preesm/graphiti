/*******************************************************************************
 * Copyright or © or Copr. IETR/INSA - Rennes (%%DATE%%) :
 *
 * %%AUTHORS%%
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

import java.util.List;

/**
 * This class represents an edge.
 * 
 * @author Jonathan Piat
 * @author Matthieu Wipliez
 * 
 */
public class Edge extends AbstractObject {

	/**
	 * This edge's source.
	 */
	private Vertex source;

	/**
	 * This edge's target.
	 */
	private Vertex target;

	/**
	 * Creates a new edge which is a copy of the given edge.
	 * 
	 * @param edge
	 *            The source edge.
	 */
	public Edge(Edge edge) {
		super(edge);
		source = edge.source;
		target = edge.target;
		type = edge.type;
	}

	/**
	 * Creates a new unconnected edge with the given type.
	 * 
	 * @param type
	 *            The edge type.
	 */
	public Edge(ObjectType type) {
		super(type);

		// set default values
		List<Parameter> parameters = type.getParameters();
		for (Parameter parameter : parameters) {
			setValue(parameter.getName(), parameter.getDefault());
		}
	}

	/**
	 * Creates an edge with the given type and the specified source and target.
	 * 
	 * @param type
	 *            The edge type.
	 * @param source
	 *            The source vertex.
	 * @param target
	 *            The target vertex.
	 */
	public Edge(ObjectType type, Vertex source, Vertex target) {
		super(type);
		this.source = source;
		this.target = target;
	}

	/**
	 * Returns the configuration associated with this edge.
	 * 
	 * @return A {@link Configuration}.
	 */
	public Configuration getConfiguration() {
		if (source != null) {
			return source.getConfiguration();
		} else if (target != null) {
			return target.getConfiguration();
		} else {
			return null;
		}
	}

	/**
	 * Returns the graph that contains this edge.
	 * 
	 * @return the graph that contains this edge
	 */
	public Graph getParent() {
		Graph parent = source.getParent();
		if (parent == null) {
			parent = target.getParent();
		}

		return parent;
	}

	/**
	 * Returns this edge's source.
	 * 
	 * @return This edge's source.
	 */
	public Vertex getSource() {
		return source;
	}

	/**
	 * Returns this edge's target.
	 * 
	 * @return This edge's target.
	 */
	public Vertex getTarget() {
		return target;
	}

	/**
	 * Sets this edge's source.
	 * 
	 * @param source
	 *            A {@link Vertex}.
	 */
	public void setSource(Vertex source) {
		this.source = source;
	}

	/**
	 * Sets this edge's target.
	 * 
	 * @param target
	 *            A {@link Vertex}.
	 */
	public void setTarget(Vertex target) {
		this.target = target;
	}

	@Override
	public String toString() {
		return getType() + ": " + getSource() + " - " + getTarget() + " "
				+ super.hashCode();
	}
}
