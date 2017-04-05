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
package org.ietr.dftools.graphiti.ui.figure;

import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.tools.CellEditorLocator;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Text;
import org.ietr.dftools.graphiti.model.Vertex;

/**
 * This class implements {@link CellEditorLocator} to edit a {@link Vertex}'s
 * id. It is based on Daniel Lee's implementation for the flow example.
 * 
 * @author Daniel Lee
 * @author Matthieu Wipliez
 */
public class VertexCellEditorLocator implements CellEditorLocator {

	private VertexFigure vertexFigure;

	/**
	 * Creates a new VertexCellEditorLocator for the given vertexFigure
	 * 
	 * @param figure
	 *            the figure
	 */
	public VertexCellEditorLocator(VertexFigure figure) {
		vertexFigure = figure;
	}

	/**
	 * @see CellEditorLocator#relocate(org.eclipse.jface.viewers.CellEditor)
	 */
	@Override
	public void relocate(CellEditor celleditor) {
		Text text = (Text) celleditor.getControl();
		Point pref;
		if (text.getText().isEmpty()) {
			pref = new Point(13, 13);
		} else {
			pref = text.computeSize(-1, -1);
		}

		Label label = vertexFigure.getLabelId();
		Rectangle labelBounds = label.getBounds().getCopy();
		label.translateToAbsolute(labelBounds);

		Rectangle figureBounds = vertexFigure.getBounds().getCopy();
		vertexFigure.translateToAbsolute(figureBounds);
		int start = (figureBounds.width - pref.x) / 2;

		text.setBounds(figureBounds.x + start, labelBounds.y, pref.x, pref.y);
	}

}
