/*******************************************************************************
 * Copyright (c) 2000, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package net.sf.graphiti.ui.commands;

import net.sf.graphiti.model.Vertex;

import org.eclipse.gef.commands.Command;

/**
 * Command to rename Activities.
 * 
 * @author Daniel Lee
 */
public class RenameVertexCommand extends Command {

	private String id;

	private String previousId;

	private Vertex vertex;

	/**
	 * Creates a new RenameVertexCommand for the given vertex.
	 * 
	 * @param vertex
	 *            The {@link Vertex}.
	 */
	public RenameVertexCommand(Vertex vertex) {
		this.vertex = vertex;
		previousId = (String) vertex.getValue(Vertex.PARAMETER_ID);
	}

	@Override
	public void execute() {
		vertex.setValue(Vertex.PARAMETER_ID, id);
	}

	/**
	 * Sets the new Activity name
	 * 
	 * @param string
	 *            the new name
	 */
	public void setName(String string) {
		id = string;
	}

	@Override
	public void undo() {
		vertex.setValue(Vertex.PARAMETER_ID, previousId);
	}

}
