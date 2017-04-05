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
package org.ietr.dftools.graphiti.ui.commands.copyPaste;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.commands.Command;
import org.eclipse.jface.dialogs.IInputValidator;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.ietr.dftools.graphiti.model.Graph;
import org.ietr.dftools.graphiti.model.ObjectType;
import org.ietr.dftools.graphiti.model.Vertex;
import org.ietr.dftools.graphiti.ui.editparts.GraphEditPart;

/**
 * This class provides a command that pastes vertices from the clipboard.
 * 
 * @author Samuel Beaussier
 * @author Nicolas Isch
 * @author Matthieu Wipliez
 * 
 */
public class PasteCommand extends Command {

	private List<Object> added;

	private boolean dirty;

	/**
	 * The target EditPart.
	 */
	private Graph graph;

	private List<Vertex> vertices;

	/**
	 * Creates a new paste command with the given target part and vertices.
	 * 
	 * @param target
	 *            The target part.
	 * @param vertices
	 *            A list of vertices to cut.
	 */
	public PasteCommand(GraphEditPart target, List<Vertex> vertices) {
		this.graph = (Graph) target.getModel();
		this.vertices = vertices;
	}

	private String checkVertexId(Graph graph, Vertex vertex) {
		String id = (String) vertex.getValue(ObjectType.PARAMETER_ID);
		Vertex existing = graph.findVertex(id);
		if (existing != vertex) {
			id = getVertexId(id);
			if (id != null) {
				vertex.setValue(ObjectType.PARAMETER_ID, id);
			}
		}

		return id;
	}

	@Override
	public void execute() {

	}

	/**
	 * Returns a vertex identifier.
	 * 
	 * @param initialValue
	 *            The initial id.
	 * @return A unique vertex identifier, or <code>null</code>.
	 */
	private String getVertexId(String initialValue) {
		IWorkbench workbench = PlatformUI.getWorkbench();
		IWorkbenchWindow window = workbench.getActiveWorkbenchWindow();
		Shell shell = window.getShell();

		InputDialog dialog = new InputDialog(shell, "New vertex",
				"Please enter a vertex identifier", initialValue,
				new IInputValidator() {

					@Override
					public String isValid(String vertexId) {
						if (vertexId.isEmpty()) {
							return "";
						}

						if (graph != null) {
							Vertex vertex = graph.findVertex(vertexId);
							if (vertex != null) {
								return "A vertex already exists with the same identifier";
							}
						}

						return null;
					}

				});
		int res = dialog.open();
		if (res == Window.OK) {
			String value = dialog.getValue();
			if (value == null || value.isEmpty()) {
				return null;
			} else {
				return value;
			}
		} else {
			return null;
		}
	}

	public boolean isDirty() {
		return dirty;
	}

	public void run() {
		added = new ArrayList<Object>();

		for (Vertex vertex : vertices) {
			// check id
			if (checkVertexId(graph, vertex) != null) {
				// Adds the cloned graph to the parent graph and the added list
				added.add(vertex);
				Rectangle previousBounds = (Rectangle) vertex
						.getValue(Vertex.PROPERTY_SIZE);
				graph.addVertex(vertex);
				Rectangle bounds = (Rectangle) vertex
						.getValue(Vertex.PROPERTY_SIZE);

				Rectangle newBounds = new Rectangle(previousBounds.x
						+ previousBounds.width + 10, previousBounds.y
						+ previousBounds.height + 10, bounds.width,
						bounds.height);
				vertex
						.firePropertyChange(Vertex.PROPERTY_SIZE, null,
								newBounds);

				dirty = true;
			}
		}
	}

	@Override
	public void undo() {
		for (Object model : added) {
			if (model instanceof Vertex) {
				graph.removeVertex((Vertex) model);
			}
		}
	}
}
