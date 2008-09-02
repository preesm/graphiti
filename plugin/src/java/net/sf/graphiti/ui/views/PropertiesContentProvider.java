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

import net.sf.graphiti.model.Edge;
import net.sf.graphiti.model.Graph;
import net.sf.graphiti.model.Parameter;
import net.sf.graphiti.model.PropertyBean;
import net.sf.graphiti.model.Vertex;
import net.sf.graphiti.ui.editparts.EdgeEditPart;
import net.sf.graphiti.ui.editparts.GraphEditPart;
import net.sf.graphiti.ui.editparts.VertexEditPart;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;

/**
 * This class defines a content provider for the table displayed in this view.
 * 
 * @author Matthieu Wipliez
 */
public class PropertiesContentProvider implements IStructuredContentProvider,
		PropertyChangeListener {

	/**
	 * When input has changed.
	 */
	public static final String INPUT_CHANGED = "inputChanged";

	/**
	 * The {@link PropertyChangeSupport} we use.
	 */
	private PropertyChangeSupport propertyChange;

	/**
	 * The object we provide content for: Graph, Vertex and Edge. We use
	 * {@link PropertyBean} because this the common type.
	 */
	private PropertyBean source;

	/**
	 * The viewer.
	 */
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
		if (source != null) {
			source.removePropertyChangeListener(this);
		}

		// remove all listeners
		for (PropertyChangeListener listener : propertyChange
				.getPropertyChangeListeners()) {
			propertyChange.removePropertyChangeListener(listener);
		}

		// nothing left
		propertyChange = null;
		source = null;
		viewer = null;
	}

	@Override
	public Object[] getElements(Object inputElement) {
		if (source instanceof Graph) {
			List<Parameter> parameters = ((Graph) source).getParameters();
			return parameters.toArray();
		} else if (source instanceof Vertex) {
			List<Parameter> parameters = ((Vertex) source).getParameters();
			return parameters.toArray();
		} else if (source instanceof Edge) {
			List<Parameter> parameters = ((Edge) source).getParameters();
			return parameters.toArray();
		} else {
			return new Object[] {};
		}
	}

	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		this.viewer = viewer;

		// remove ourselves as a listener to the previous model
		if (source instanceof PropertyBean) {
			source.removePropertyChangeListener(this);
		}

		// update the source
		PropertyBean oldSource = source;
		if (newInput instanceof GraphEditPart) {
			source = (Graph) ((GraphEditPart) newInput).getModel();
		} else if (newInput instanceof VertexEditPart) {
			source = (Vertex) ((VertexEditPart) newInput).getModel();
		} else if (newInput instanceof EdgeEditPart) {
			source = (Edge) ((EdgeEditPart) newInput).getModel();
		} else {
			source = null;
		}

		if (source != null) {
			// add ourselves as a listener, and informs registered listeners of
			// the change
			source.addPropertyChangeListener(this);
			propertyChange.firePropertyChange(INPUT_CHANGED, oldSource, source);
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

}
