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
package net.sf.graphiti.ui.views;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.List;

import net.sf.graphiti.model.Parameter;
import net.sf.graphiti.model.Vertex;
import net.sf.graphiti.ui.editparts.VertexEditPart;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;

/**
 * This class defines a content provider for the table displayed in this view.
 * 
 * @author Matthieu Wipliez
 */
public class PropertiesContentProvider implements
		IStructuredContentProvider, PropertyChangeListener {

	public static final String INPUT_CHANGED = "inputChanged";

	private PropertyChangeSupport propertyChange;

	/**
	 * The vertex we provide content for.
	 */
	private Vertex vertex;

	private Viewer viewer;

	/**
	 * Creates a new content provider.
	 */
	public PropertiesContentProvider() {
		propertyChange = new PropertyChangeSupport(this);
	}

	/**
	 * Add the listener <code>listener</code> to the registered listeners.
	 * 
	 * @param listener
	 *            The PropertyChangeListener to add.
	 */
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		propertyChange.addPropertyChangeListener(listener);
	}

	@Override
	public void dispose() {
		if (vertex != null) {
			vertex.removePropertyChangeListener(this);
		}
	}

	@Override
	public Object[] getElements(Object inputElement) {
		if (vertex == null) {
			return new Object[] {};
		} else {
			List<Parameter> parameters = vertex.getParameters();
			return parameters.toArray();
		}
	}

	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		this.viewer = viewer;

		// remove ourselves as a listener to the previous model
		if (vertex instanceof Vertex) {
			vertex.removePropertyChangeListener(this);
		}

		if (newInput instanceof VertexEditPart) {
			Vertex oldVertex = vertex;

			// set the vertex attribute, and add ourselves as a listener
			vertex = (Vertex) ((VertexEditPart) newInput).getModel();
			vertex.addPropertyChangeListener(this);
			
			// informs registered listeners of the change
			propertyChange.firePropertyChange(INPUT_CHANGED, oldVertex, vertex);
		} else {
			// reset the selection
			vertex = null;
		}
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if (viewer != null) {
			viewer.refresh();
		}
		
		// Forward the event to registered listeners
		propertyChange.firePropertyChange(evt);
	}

	/**
	 * Sets the vertex we provide content for.
	 * 
	 * @param vertex
	 *            The new {@link Vertex} to provide content for.
	 */
	public void setVertex(Vertex vertex) {
		this.vertex = vertex;
	}

}
