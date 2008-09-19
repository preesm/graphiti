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

import java.util.Collection;

import net.sf.graphiti.model.Util;

import org.eclipse.jface.viewers.Viewer;

/**
 * This class defines a content provider for the table displayed in a complex
 * property view. The source is a {@link ComplexSource}.
 * 
 * @author Matthieu Wipliez
 */
public class ComplexContentProvider extends AbstractContentProvider {

	@Override
	public void dispose() {
		if (source != null) {
			ComplexSource source = (ComplexSource) this.source;
			source.bean.removePropertyChangeListener(this);
		}
		super.dispose();
	}

	@Override
	public Object[] getElements(Object inputElement) {
		ComplexSource source = (ComplexSource) this.source;
		Collection<?> collection = Util.getCollection(source.bean,
				source.parameter);
		return collection.toArray();
	}

	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		super.inputChanged(viewer, oldInput, newInput);
		ComplexSource source = (ComplexSource) this.source;

		// remove ourselves as a listener to the previous model
		if (source != null) {
			source.bean.removePropertyChangeListener(this);
		}

		// update the source
		ComplexSource oldSource = source;
		if (newInput instanceof ComplexSource) {
			source = (ComplexSource) newInput;
			this.source = source;
		}

		if (source != null) {
			// add ourselves as a listener, and informs registered listeners of
			// the change
			source.bean.addPropertyChangeListener(this);
			firePropertyChange(INPUT_CHANGED, oldSource, source);
		}
	}

}
