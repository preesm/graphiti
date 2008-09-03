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

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;

/**
 * This class defines a content provider for the table displayed in this view.
 * 
 * @author Matthieu Wipliez
 */
public abstract class AbstractPropertiesContentProvider implements
		IStructuredContentProvider, PropertyChangeListener {

	/**
	 * When input has changed.
	 */
	public static final String INPUT_CHANGED = "inputChanged";

	/**
	 * The {@link PropertyChangeSupport} we use.
	 */
	private PropertyChangeSupport propertyChange;

	/**
	 * The object we provide content for.
	 */
	protected Object source;

	/**
	 * The viewer.
	 */
	private Viewer viewer;

	/**
	 * Creates a new content provider.
	 */
	public AbstractPropertiesContentProvider() {
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

	/**
	 * @see PropertyChangeSupport#firePropertyChange(String, Object, Object)
	 */
	protected void firePropertyChange(String propertyName, Object oldValue,
			Object newValue) {
		propertyChange.firePropertyChange(propertyName, oldValue, newValue);
	}

	@Override
	public abstract Object[] getElements(Object inputElement);

	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		this.viewer = viewer;
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
