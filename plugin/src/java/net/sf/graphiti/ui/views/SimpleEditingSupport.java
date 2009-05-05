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

import net.sf.graphiti.model.Parameter;
import net.sf.graphiti.model.PropertyBean;
import net.sf.graphiti.ui.commands.ParameterChangeValueCommand;
import net.sf.graphiti.ui.editors.GraphEditor;

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.swt.widgets.Table;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;

/**
 * This class provides {@link EditingSupport} for the "value" column.
 * 
 * @author Matthieu Wipliez
 * 
 */
public class SimpleEditingSupport extends EditingSupport implements
		PropertyChangeListener {

	private TextCellEditor editor;

	/**
	 * The source we provide editing support for.
	 */
	private PropertyBean source;

	/**
	 * Creates a new {@link SimpleEditingSupport} on the given column viewer and
	 * table.
	 * 
	 * @param viewer
	 * @param table
	 */
	public SimpleEditingSupport(ColumnViewer viewer, Table table) {
		super(viewer);
		editor = new TextCellEditor(table);
	}

	@Override
	protected boolean canEdit(Object element) {
		return true;
	}

	@Override
	protected CellEditor getCellEditor(Object element) {
		return editor;
	}

	@Override
	protected Object getValue(Object element) {
		if (element instanceof Parameter) {
			Parameter parameter = (Parameter) element;
			Object value = (Object) source.getValue(parameter.getName());
			if (value == null) {
				value = "";
			}
			return value.toString();
		} else {
			return "";
		}
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if (evt.getPropertyName().equals(SimpleContentProvider.INPUT_CHANGED)) {
			source = (PropertyBean) evt.getNewValue();
		}
	}

	@Override
	protected void setValue(Object element, Object value) {
		if (element instanceof Parameter) {
			Parameter parameter = (Parameter) element;
			ParameterChangeValueCommand command = new ParameterChangeValueCommand(
					source);
			command.setValue(parameter.getName(), value);

			IWorkbench workbench = PlatformUI.getWorkbench();
			IWorkbenchWindow window = workbench.getActiveWorkbenchWindow();
			IEditorPart part = window.getActivePage().getActiveEditor();
			if (part instanceof GraphEditor) {
				((GraphEditor) part).executeCommand(command);
			}
		}
	}

}