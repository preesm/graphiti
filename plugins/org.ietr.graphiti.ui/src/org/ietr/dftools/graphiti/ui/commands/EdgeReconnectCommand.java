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
package org.ietr.dftools.graphiti.ui.commands;

import org.eclipse.gef.commands.Command;
import org.ietr.dftools.graphiti.model.Edge;
import org.ietr.dftools.graphiti.model.Graph;
import org.ietr.dftools.graphiti.model.ObjectType;
import org.ietr.dftools.graphiti.model.Vertex;
import org.ietr.dftools.graphiti.ui.commands.refinement.PortChooser;

/**
 * This class provides a Command that reconnects a dependency. Reconnection is a
 * bit trickier than creation, since we must remember the previous dependency.
 * We inherit from EdgeCreateCommand so we just need to store the previous
 * dependency and parent graph, while keeping most of the original behavior.
 * 
 * @author Matthieu Wipliez
 */
public class EdgeReconnectCommand extends Command {

	/**
	 * The edge is stored as an attribute so it can be used both in the
	 * <code>execute</code> and <code>undo</code> methods.
	 */
	private Edge edge;

	/**
	 * The parentGraph is stored as an attribute so it can be used both in the
	 * <code>execute</code> and <code>undo</code> methods.
	 */
	private Graph parentGraph;

	private Edge previousEdge;

	private Vertex source;

	private Vertex target;

	@Override
	public void execute() {
		// Disconnect
		parentGraph = source.getParent();
		parentGraph.removeEdge(previousEdge);

		// Clone edge and assign ports
		edge = new Edge(previousEdge);

		if (edge.getSource() != source) {
			edge.setSource(source);

			String connection = edge.getSource().getValue(
					ObjectType.PARAMETER_ID)
					+ " - "
					+ edge.getTarget().getValue(ObjectType.PARAMETER_ID);
			PortChooser portChooser = new PortChooser(connection);
			if (edge.getParameter(ObjectType.PARAMETER_SOURCE_PORT) != null) {
				edge.setValue(ObjectType.PARAMETER_SOURCE_PORT,
						portChooser.getSourcePort(source));
			}
		} else if (edge.getTarget() != target) {
			edge.setTarget(target);

			String connection = edge.getSource().getValue(
					ObjectType.PARAMETER_ID)
					+ " - "
					+ edge.getTarget().getValue(ObjectType.PARAMETER_ID);
			PortChooser portChooser = new PortChooser(connection);
			if (edge.getParameter(ObjectType.PARAMETER_TARGET_PORT) != null) {
				edge.setValue(ObjectType.PARAMETER_TARGET_PORT,
						portChooser.getTargetPort(target));
			}
		}

		parentGraph.addEdge(edge);
	}

	/**
	 * Sets the original dependency (before it is reconnected).
	 * 
	 * @param edge
	 *            The edge.
	 */
	public void setOriginalEdge(Edge edge) {
		this.previousEdge = edge;

		// We also set these because we do not know which one will be set by the
		// EdgeGraphicalNodeEditPolicy (ie if getReconnectSourceCommand or
		// getReconnectTargetCommand is called)
		source = edge.getSource();
		target = edge.getTarget();
	}

	/**
	 * Sets the source of the dependency to create/reconnect.
	 * 
	 * @param source
	 *            The dependency source as a Port.
	 */
	public void setSource(Vertex source) {
		this.source = source;
	}

	/**
	 * Sets the target of the dependency to create/reconnect.
	 * 
	 * @param target
	 *            The dependency target as a Port.
	 */
	public void setTarget(Vertex target) {
		this.target = target;
	}

	@Override
	public void undo() {
		parentGraph.removeEdge(edge);
		parentGraph.addEdge(previousEdge);
	}
}