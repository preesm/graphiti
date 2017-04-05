/*******************************************************************************
 * Copyright or © or Copr. IETR/INSA - Rennes (%%DATE%%) :
 *
 * %%AUTHORS%%
 *
 * This software is a computer program whose purpose is to [describe
 * functionalities and technical features of your software].
 *
 * This software is governed by the CeCILL  license under French law and
 * abiding by the rules of distribution of free software.  You can  use, 
 * modify and/ or redistribute the software under the terms of the CeCILL
 * license as circulated by CEA, CNRS and INRIA at the following URL
 * "http://www.cecill.info". 
 *
 * As a counterpart to the access to the source code and  rights to copy,
 * modify and redistribute granted by the license, users are provided only
 * with a limited warranty  and the software's author,  the holder of the
 * economic rights,  and the successive licensors  have only  limited
 * liability. 
 *
 * In this respect, the user's attention is drawn to the risks associated
 * with loading,  using,  modifying and/or developing or reproducing the
 * software by the user in light of its specific status of free software,
 * that may mean  that it is complicated to manipulate,  and  that  also
 * therefore means  that it is reserved for developers  and  experienced
 * professionals having in-depth computer knowledge. Users are therefore
 * encouraged to load and test the software's suitability as regards their
 * requirements in conditions enabling the security of their systems and/or 
 * data to be ensured and,  more generally, to use and operate it in the 
 * same conditions as regards security. 
 *
 * The fact that you are presently reading this means that you have had
 * knowledge of the CeCILL license and that you accept its terms.
 *******************************************************************************/
package org.ietr.dftools.graphiti.ui.properties;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE;
import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertySheetPage;
import org.eclipse.ui.views.properties.IPropertySource;
import org.eclipse.ui.views.properties.PropertySheet;
import org.eclipse.ui.views.properties.PropertySheetPage;
import org.eclipse.ui.views.properties.TextPropertyDescriptor;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;
import org.ietr.dftools.graphiti.model.AbstractObject;
import org.ietr.dftools.graphiti.model.Edge;
import org.ietr.dftools.graphiti.model.Graph;
import org.ietr.dftools.graphiti.model.ObjectType;
import org.ietr.dftools.graphiti.model.Parameter;
import org.ietr.dftools.graphiti.model.Vertex;
import org.ietr.dftools.graphiti.ui.commands.ParameterChangeValueCommand;
import org.ietr.dftools.graphiti.ui.editors.GraphEditor;

/**
 * This class implements a property source for the different objects of our
 * model.
 * 
 * @author Matthieu Wipliez
 * 
 */
public class ModelPropertySource implements IPropertySource,
		PropertyChangeListener {

	private IPropertyDescriptor[] descs;

	private boolean doRefresh;

	private AbstractObject model;

	private ObjectType type;

	public ModelPropertySource(AbstractObject model) {
		this.model = model;
		model.addPropertyChangeListener(this);
		this.type = model.getType();

		List<IPropertyDescriptor> descs = new ArrayList<IPropertyDescriptor>();
		for (Parameter parameter : type.getParameters()) {
			if (!(parameter.getType() == List.class || parameter.getType() == Map.class)) {
				String name = parameter.getName();
				TextPropertyDescriptor desc = new TextPropertyDescriptor(name,
						name);
				descs.add(desc);
			}
		}

		this.descs = descs.toArray(new IPropertyDescriptor[0]);
	}

	@Override
	public Object getEditableValue() {
		return null;
	}

	@Override
	public IPropertyDescriptor[] getPropertyDescriptors() {
		return descs;
	}

	@Override
	public Object getPropertyValue(Object id) {
		Object value = model.getValue((String) id);
		if (value == null) {
			return "";
		}
		return String.valueOf(value);
	}

	@Override
	public boolean isPropertySet(Object id) {
		Object value = model.getValue((String) id);
		Object defaultValue = model.getParameter((String) id).getDefault();
		return value != defaultValue;
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if (!doRefresh) {
			return;
		}

		IWorkbench workbench = PlatformUI.getWorkbench();
		IWorkbenchPage page = workbench.getActiveWorkbenchWindow()
				.getActivePage();

		// show properties
		try {
			IViewPart part = page.showView(IPageLayout.ID_PROP_SHEET);
			if (part instanceof PropertySheet) {
				IPropertySheetPage propPage = (IPropertySheetPage) ((PropertySheet) part)
						.getCurrentPage();
				if (propPage instanceof PropertySheetPage) {
					((PropertySheetPage) propPage).refresh();
				} else if (propPage instanceof TabbedPropertySheetPage) {
					((TabbedPropertySheetPage) propPage).refresh();
				}
			}
		} catch (PartInitException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void resetPropertyValue(Object id) {
		Object defaultValue = model.getParameter((String) id).getDefault();
		if (defaultValue == null) {
			defaultValue = "";
		}
		model.setValue((String) id, defaultValue);
	}

	@Override
	public void setPropertyValue(Object id, Object value) {
		Graph graph;
		if (model instanceof Vertex) {
			graph = ((Vertex) model).getParent();
		} else if (model instanceof Edge) {
			graph = ((Edge) model).getParent();
		} else {
			graph = (Graph) model;
		}

		IWorkbench workbench = PlatformUI.getWorkbench();
		IWorkbenchPage page = workbench.getActiveWorkbenchWindow()
				.getActivePage();
		try {
			IEditorPart part = IDE.openEditor(page, graph.getFile());
			if (part instanceof GraphEditor) {
				String parameterName = (String) id;

				// only update value if it is different than before
				Object oldValue = model.getValue(parameterName);
				String str = (String) value;
				if (oldValue == null && str.isEmpty()
						|| String.valueOf(oldValue).equals(value)) {
					return;
				}

				ParameterChangeValueCommand command = new ParameterChangeValueCommand(
						model, "Change value");
				Class<?> parameterType = model.getParameter((String) id)
						.getType();
				if (str.isEmpty()) {
					// get default value
					value = model.getParameter(parameterName).getDefault();
				} else {
					try {
						if (parameterType == Integer.class) {
							value = Integer.valueOf(str);
						} else if (parameterType == Float.class) {
							value = Float.valueOf(str);
						} else if (parameterType == Boolean.class) {
							if (!"true".equals(value) && !"false".equals(value)) {
								throw new IllegalArgumentException();
							}
							value = Boolean.valueOf(str);
						}
					} catch (RuntimeException e) {
						value = "invalid \"" + value + "\" value for "
								+ parameterType.getSimpleName();
					}
				}
				command.setValue(parameterName, value);
				doRefresh = false;
				((GraphEditor) part).executeCommand(command);
				doRefresh = true;
			}
		} catch (PartInitException e) {
			e.printStackTrace();
		}
	}

}
