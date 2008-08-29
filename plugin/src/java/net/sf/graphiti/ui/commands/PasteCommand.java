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

import java.util.ArrayList;
import java.util.List;

import net.sf.graphiti.model.Graph;
import net.sf.graphiti.model.Vertex;
import net.sf.graphiti.ui.editparts.GraphEditPart;
import net.sf.graphiti.ui.editparts.VertexEditPart;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editparts.AbstractEditPart;

/**
 * Modify model when Paste tool is activated
 * 
 * @author Samuel Beaussier
 * @author Nicolas Isch
 */
public class PasteCommand extends Command {

	private List<Object> added;

	private List<AbstractEditPart> contents;

	/**
	 * The target EditPart.
	 */
	private GraphEditPart part;

	public PasteCommand(GraphEditPart part) {
		this.part = part;
	}

	@Override
	public void execute() {
		added = new ArrayList<Object>();
		Graph parentGraph = (Graph) this.part.getModel();

		for (AbstractEditPart part : contents) {
			if (part instanceof VertexEditPart) {
				VertexEditPart vertexEditPart = (VertexEditPart) part;
				Vertex vertex = (Vertex) vertexEditPart.getModel();
				vertex = new Vertex(vertex);

				Rectangle bounds = (Rectangle) vertex
						.getValue(Vertex.PARAMETER_SIZE);
				bounds.x += 20;
				bounds.y += 20;

				// Adds the cloned graph to the parent graph and the added list
				added.add(vertex);
				parentGraph.addVertex(vertex);
			}
		}
	}

	/**
	 * Defines the contents of objects to paste.
	 * 
	 * @param contents
	 */
	public void setContents(List<AbstractEditPart> contents) {
		this.contents = contents;
	}

	@Override
	public void undo() {
		Graph parentGraph = (Graph) this.part.getModel();
		for (Object model : added) {
			if (model instanceof Vertex) {
				parentGraph.removeVertex((Vertex) model);
			}
		}
	}
}
