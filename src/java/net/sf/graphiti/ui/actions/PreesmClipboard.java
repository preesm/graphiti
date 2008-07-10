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
package net.sf.graphiti.ui.actions;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import org.eclipse.gef.ui.actions.Clipboard;

/**
 * @author stage02
 */
public class PreesmClipboard extends Clipboard {

	protected static PreesmClipboard _instance = new PreesmClipboard();

	/**
	 * The event name used for {@link PreesmClipboard#fireContentsSet()}
	 */
	public static final String CONTENTS_SET_EVENT = "ContentsSet";

	/**
	 * Get the default Clipboard
	 * 
	 * @return - The default Clipboard
	 */
	public static Clipboard getDefault() {
		return _instance;
	}

	private PropertyChangeSupport listeners = new PropertyChangeSupport(this);

	/**
	 * Do not allow direct instantiation of a Clipboard
	 */
	@SuppressWarnings("deprecation")
	private PreesmClipboard() {
		super();
	}

	/**
	 * Add a {@link PropertyChangeListener} to this Clipboard
	 * 
	 * @param l
	 */
	public void addPropertyChangeListener(PropertyChangeListener l) {
		listeners.addPropertyChangeListener(l);
	}

	/**
	 * Fires a {@link PropertyChangeEvent} anytime the contents of the
	 * <code>Clipboard</code> are set.
	 * 
	 */
	protected void fireContentsSet() {
		PropertyChangeEvent event = new PropertyChangeEvent(this,
				CONTENTS_SET_EVENT, null, getContents());
		listeners.firePropertyChange(event);
	}

	/**
	 * Remove a {@link PropertyChangeListener} to this Clipboard
	 * 
	 * @param l
	 */
	public void removePropertyChangeListener(PropertyChangeListener l) {
		listeners.removePropertyChangeListener(l);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.ui.actions.Clipboard#setContents(java.lang.Object)
	 */
	@Override
	public void setContents(Object contents) {
		super.setContents(contents);
		fireContentsSet();
	}
}
