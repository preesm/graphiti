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

import net.sf.graphiti.model.Edge;
import net.sf.graphiti.model.Graph;

/**
 * This class provides a Command that reconnects a dependency. Reconnection is a
 * bit trickier than creation, since we must remember the previous dependency.
 * We inherit from EdgeCreateCommand so we just need to store the previous
 * dependency and parent graph, while keeping most of the original behavior.
 * 
 * @author Matthieu Wipliez
 */
public class EdgeReconnectCommand extends EdgeCreateCommand {

	private Graph previousParentGraph;

	private Edge previousPath;

	@Override
	public void execute() {
		previousParentGraph = previousPath.getSource().getParent();
		parentGraph = source.getParent();

		// Disconnect
		previousParentGraph.removeEdge(previousPath);

		// Reconnect
		edge = new Edge(source, target);
		parentGraph.addEdge(edge);
	}

	/**
	 * Sets the original dependency (before it is reconnected).
	 * 
	 * @param edge
	 *            The dependency.
	 */
	public void setOriginalDependency(Edge path) {
		this.previousPath = path;

		// We also set these because we do not know which one will be set by the
		// EdgeGraphicalNodeEditPolicy (ie if getReconnectSourceCommand or
		// getReconnectTargetCommand is called)
		source = path.getSource();
		target = path.getTarget();
	}

	@Override
	public void undo() {
		parentGraph.removeEdge(edge);
		previousParentGraph.addEdge(previousPath);
	}
}