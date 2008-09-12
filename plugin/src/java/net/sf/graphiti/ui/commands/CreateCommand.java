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

import net.sf.graphiti.model.Configuration;
import net.sf.graphiti.model.Graph;
import net.sf.graphiti.model.Vertex;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.commands.Command;

/**
 * This class allows the creation of vertices.
 * 
 * @author Samuel Beaussier
 * @author Nicolas Isch
 * @author Matthieu Wipliez
 * 
 */
public class CreateCommand extends Command {

	private Rectangle bounds;

	private Object model;

	private Object newObject;

	public CreateCommand() {

	}

	@Override
	public void execute() {
		if (model instanceof Graph) {
			Graph graph = (Graph) model;

			if (newObject instanceof Vertex) {
				Vertex vertex = (Vertex) newObject;
				graph.addVertex(vertex);

				Configuration config = graph.getConfiguration();
				Integer width = (Integer) config.getVertexAttribute(vertex
						.getType(), Vertex.ATTRIBUTE_WIDTH);
				Integer height = (Integer) config.getVertexAttribute(vertex
						.getType(), Vertex.ATTRIBUTE_HEIGHT);

				if (width == null) {
					width = 50;
				}
				if (height == null) {
					height = 50;
				}

				Rectangle rect = new Rectangle(bounds);
				rect.width = width;
				rect.height = height;
				vertex.firePropertyChange(Vertex.PARAMETER_SIZE, null, rect);

			}
		}
	}

	/**
	 * Sets the initial bounds of this graph.
	 * 
	 * @param bounds
	 */
	public void setBounds(Rectangle bounds) {
		this.bounds = bounds;
	}

	/**
	 * Sets this command model.
	 * 
	 * @param model
	 *            The model to use.
	 */
	public void setModel(Object model) {
		this.model = model;
	}

	/**
	 * Sets the new object that should be added to the model.
	 * 
	 * @param newObject
	 *            the newly created object.
	 */
	public void setNewObject(Object newObject) {
		this.newObject = newObject;
	}

	@Override
	public void undo() {
		if (model instanceof Graph) {
			Graph graph = (Graph) model;

			if (newObject instanceof Vertex) {
				Vertex vertex = (Vertex) newObject;
				graph.removeVertex(vertex);
			}
		}
	}
}
