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
import net.sf.graphiti.model.Vertex;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.commands.Command;

/**
 * This class provides a command that deletes a vertex.
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
		} else if (obj instanceof Edge) {
			edge = (Edge) obj;
		}
	}

	public boolean canExecute() {
		return (vertex != null || edge != null);
	}

	@Override
	public void execute() {
		if (vertex != null) {
			parent = vertex.getParent();
			parent.removeVertex(vertex);
		} else if (edge != null) {
			parent = edge.getSource().getParent();
			parent.removeEdge(edge);
		}
	}

	@Override
	public String getLabel() {
		return "Delete";
	}

	@Override
	public void undo() {
		if (vertex != null) {
			Rectangle bounds = (Rectangle) vertex
					.getValue(Vertex.PROPERTY_SIZE);
			parent.addVertex(vertex);
			vertex.setValue(Vertex.PROPERTY_SIZE, bounds);
		} else if (edge != null) {
			parent.addEdge(edge);
		}
	}
}