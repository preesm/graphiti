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

import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.forms.widgets.Form;
import org.eclipse.ui.views.properties.tabbed.AbstractPropertySection;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;
import org.ietr.dftools.graphiti.model.AbstractObject;

/**
 * This class defines an abstract section.
 * 
 * @author Matthieu Wipliez
 * 
 */
public abstract class AbstractSection extends AbstractPropertySection implements
		PropertyChangeListener {

	/**
	 * This class provides a command that changes the value of the currently
	 * selected parameter.
	 * 
	 * @author Matthieu Wipliez
	 * 
	 */
	protected class ParameterChangeValueCommand extends Command {

		final private String label;

		/**
		 * Set by {@link #setValue(String, Object)}.
		 */
		private String name;

		/**
		 * The new value.
		 */
		private Object newValue;

		/**
		 * The old value.
		 */
		private Object oldValue;

		/**
		 * The property bean we're modifying.
		 */
		private AbstractObject source;

		/**
		 * Creates a new add parameter command.
		 * 
		 * @param newValue
		 *            The value.
		 */
		public ParameterChangeValueCommand(AbstractObject source, String label) {
			this.source = source;
			this.label = label;
		}

		@Override
		public void execute() {
			oldValue = source.setValue(name, newValue);
		}

		@Override
		public String getLabel() {
			return label;
		}

		/**
		 * Sets the value of the parameter whose name is given to the given
		 * value.
		 * 
		 * @param name
		 *            The parameter name.
		 * @param value
		 *            Its new value.
		 */
		public void setValue(String name, Object value) {
			this.name = name;
			this.newValue = value;
		}

		@Override
		public void undo() {
			source.setValue(name, oldValue);
		}

	}

	private Button buttonAdd;

	private Button buttonRemove;

	private Form form;

	protected String parameterName;

	private TableViewer tableViewer;

	/**
	 * Called when "Add..." is pressed.
	 */
	abstract protected void buttonAddSelected();

	/**
	 * Called when "Remove" is pressed.
	 */
	abstract protected void buttonRemoveSelected();

	@Override
	public void createControls(Composite parent,
			TabbedPropertySheetPage aTabbedPropertySheetPage) {
		super.createControls(parent, aTabbedPropertySheetPage);

		form = getWidgetFactory().createForm(parent);
		getWidgetFactory().decorateFormHeading(form);

		Composite composite = form.getBody();
		composite.setLayout(new GridLayout(2, false));
		composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
	}

	/**
	 * Creates the table component from the <code>parent</code> composite.
	 * 
	 * @param parent
	 *            The parent composite.
	 * @return The table created.
	 */
	final protected Table createTable(Composite parent) {
		// create table
		int style = SWT.BORDER | SWT.SINGLE | SWT.FULL_SELECTION
				| SWT.HIDE_SELECTION;

		final Table table = getWidgetFactory().createTable(parent, style);
		tableViewer = new TableViewer(table);

		// create buttons
		buttonAdd = getWidgetFactory().createButton(parent, "Add...", SWT.NONE);
		buttonAdd.setLayoutData(new GridData(SWT.FILL, SWT.TOP, false, false));
		buttonAdd.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				buttonAddSelected();
			}
		});

		// create buttons
		buttonRemove = getWidgetFactory().createButton(parent, "Remove",
				SWT.NONE);
		buttonRemove
				.setLayoutData(new GridData(SWT.FILL, SWT.TOP, false, false));
		buttonRemove.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				buttonRemoveSelected();
			}
		});

		return table;
	}

	@Override
	public void dispose() {
		AbstractObject model = getModel();
		if (model != null) {
			model.removePropertyChangeListener(this);
		}

		if (form != null) {
			form.dispose();
		}
	}

	/**
	 * Returns the form of this section.
	 * 
	 * @return the form of this section
	 */
	public Form getForm() {
		return form;
	}

	/**
	 * Returns the model associated with this section. May be <code>null</code>.
	 * 
	 * @return the model associated with this section
	 */
	public AbstractObject getModel() {
		if (tableViewer == null) {
			return null;
		}
		return (AbstractObject) tableViewer.getInput();
	}

	/**
	 * Returns the shell associated with the form of this section.
	 * 
	 * @return the shell associated with the form of this section
	 */
	public Shell getShell() {
		return form.getShell();
	}

	/**
	 * Returns the current selection on the table of this section. May be
	 * <code>null</code>.
	 * 
	 * @return the current selection on the table of this section
	 */
	public IStructuredSelection getTableSelection() {
		ISelection sel = tableViewer.getSelection();
		if (sel instanceof IStructuredSelection) {
			return (IStructuredSelection) sel;
		}
		return null;
	}

	/**
	 * Returns the viewer of the table of this section.
	 * 
	 * @return the viewer of the table of this section
	 */
	public TableViewer getViewer() {
		return tableViewer;
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		refresh();
	}

	@Override
	public void refresh() {
		tableViewer.refresh();
	}

	@Override
	public void setInput(IWorkbenchPart part, ISelection selection) {
		super.setInput(part, selection);

		// remove property listener on old model
		AbstractObject oldModel = getModel();
		if (oldModel != null) {
			oldModel.removePropertyChangeListener(this);
		}

		if (selection instanceof IStructuredSelection) {
			Object object = ((IStructuredSelection) selection)
					.getFirstElement();
			if (object instanceof EditPart) {
				Object editPartModel = ((EditPart) object).getModel();
				if (editPartModel instanceof AbstractObject) {
					AbstractObject model = (AbstractObject) editPartModel;

					if (model.getParameter(parameterName) == null) {
						tableViewer.getTable().setEnabled(false);
						buttonAdd.setEnabled(false);
						buttonRemove.setEnabled(false);
					} else {
						tableViewer.getTable().setEnabled(true);
						buttonAdd.setEnabled(true);
						buttonRemove.setEnabled(true);

						model.addPropertyChangeListener(this);
						tableViewer.setInput(model);
					}
				}
			}
		}
	}

	/**
	 * Sets the name of the parameter that this section uses.
	 * 
	 * @param parameterName
	 *            name of a parameter
	 */
	public void setParameterName(String parameterName) {
		this.parameterName = parameterName;
	}

}
