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
package net.sf.graphiti.ui.preferences;

import java.util.List;

import net.sf.graphiti.ui.GraphitiPlugin;
import net.sf.graphiti.util.FileLocator;

import org.eclipse.jface.preference.DirectoryFieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;
import org.osgi.framework.BundleContext;

/**
 * This class provides a preference page to store the folder containing the
 * ontologies.
 * 
 * @author Matthieu Wipliez
 * 
 */
public class GraphitiPreferences extends FieldEditorPreferencePage implements
		IWorkbenchPreferencePage {

	public GraphitiPreferences() {
		super(GRID);
		setPreferenceStore(GraphitiPlugin.getDefault().getPreferenceStore());
		setDescription("Set general Graphiti settings");
	}

	@Override
	public void createFieldEditors() {
		DirectoryFieldEditor fieldEditor = new DirectoryFieldEditor(
				PreferenceConstants.PATH, "&Ontologies folder:",
				getFieldEditorParent()) {

			protected boolean doCheckState() {
				if (super.doCheckState()) {
					String fileName = getTextControl().getText();
					fileName = fileName.trim();
					List<String> files = FileLocator.listFiles(fileName,
							"[^.]*\\.owl");
					return !files.isEmpty();
				} else {
					return false;
				}
			}

		};

		fieldEditor.setEmptyStringAllowed(false);
		fieldEditor
				.setErrorMessage("Directory must be valid and contain OWL files.");

		addField(fieldEditor);
	}

	@Override
	public void init(IWorkbench workbench) {
	}

	@Override
	public boolean performOk() {
		if (super.performOk()) {
			GraphitiPlugin plugin = GraphitiPlugin.getDefault();
			BundleContext context = plugin.getBundle().getBundleContext();
			try {
				plugin.stop(context);
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				plugin.start(context);
			} catch (Exception e) {
				e.printStackTrace();
			}

			return true;
		} else {
			return false;
		}
	}

}