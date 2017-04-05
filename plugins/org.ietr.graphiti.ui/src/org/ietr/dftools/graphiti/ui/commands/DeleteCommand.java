/*******************************************************************************
 * Copyright or © or Copr. IETR/INSA - Rennes (2008 - 2017) :
 *
 * Antoine Morvan <antoine.morvan@insa-rennes.fr> (2017)
 * Clément Guy <clement.guy@insa-rennes.fr> (2014)
 * Matthieu Wipliez <matthieu.wipliez@insa-rennes.fr> (2008 - 2011)
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
package org.ietr.dftools.graphiti.ui.commands;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.commands.Command;
import org.ietr.dftools.graphiti.model.Edge;
import org.ietr.dftools.graphiti.model.Graph;
import org.ietr.dftools.graphiti.model.Vertex;

/**
 * This class provides a command that deletes a vertex. NOTE: this command can
 * delete a vertex OR an edge. Also, if an edge has already been deleted and a
 * DeleteCommand is issued on it, execute() won't do anything because the edge
 * does not have a parent anymore at this point.
 * 
 * @author Samuel Beaussier
 * @author Nicolas Isch
 * @author Matthieu Wipliez
 * 
 */
public class DeleteCommand extends Command {

	private Edge edge;

	private Graph parent;

	private Vertex vertex;

	/**
	 * Creates a new delete command with the selected object.
	 * 
	 * @param obj
	 *            An object to delete.
	 */
	public DeleteCommand(Object obj) {
		if (obj instanceof Vertex) {
			vertex = (Vertex) obj;
			parent = vertex.getParent();
		} else if (obj instanceof Edge) {
			edge = (Edge) obj;
			parent = edge.getParent();
		}
	}

	/**
	 * Adds a vertex to the parent graph and sets its size.
	 * 
	 * @param vertex
	 *            a vertex
	 */
	private void addVertex(Vertex vertex) {
		Rectangle bounds = (Rectangle) vertex.getValue(Vertex.PROPERTY_SIZE);
		parent.addVertex(vertex);
		vertex.setValue(Vertex.PROPERTY_SIZE, bounds);
	}

	@Override
	public boolean canExecute() {
		return (parent != null && (vertex != null || edge != null));
	}

	@Override
	public void execute() {
		if (parent != null) {
			if (vertex != null) {
				parent.removeVertex(vertex);
			} else if (edge != null) {
				parent.removeEdge(edge);
			}
		}
	}

	@Override
	public String getLabel() {
		return "Delete";
	}

	@Override
	public void undo() {
		if (parent != null) {
			if (vertex != null) {
				addVertex(vertex);
			} else if (edge != null) {
				addVertex(edge.getSource());
				addVertex(edge.getTarget());
				parent.addEdge(edge);
			}
		}
	}

}
