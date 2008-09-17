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
import org.eclipse.gef.GraphicalEditPart;
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

	private Graph parent;

	private GraphicalEditPart part;

	/**
	 * Creates a new delete command with the selected object.
	 * 
	 * @param model
	 *            An object to delete.
	 */
	public DeleteCommand(GraphicalEditPart part) {
		this.part = part;
	}

	public boolean canExecute() {
		Object model = part.getModel();
		return (model instanceof Edge || model instanceof Vertex);
	}

	@Override
	public void execute() {
		Object model = part.getModel();
		if (model instanceof Vertex) {
			Vertex vertex = (Vertex) model;
			parent = vertex.getParent();
			parent.removeVertex(vertex);
		} else if (model instanceof Edge) {
			Edge edge = (Edge) model;
			parent = edge.getSource().getParent();
			parent.removeEdge(edge);
		}
	}

	@Override
	public void undo() {
		Object model = part.getModel();
		if (model instanceof Vertex) {
			Vertex vertex = (Vertex) model;
			parent.addVertex(vertex);

			Rectangle bounds = (Rectangle) vertex
					.getValue(Vertex.PROPERTY_SIZE);
			vertex.firePropertyChange(Vertex.PROPERTY_SIZE, null, bounds);
		} else if (model instanceof Edge) {
			Edge edge = (Edge) model;
			parent.addEdge(edge);
		}
	}
}