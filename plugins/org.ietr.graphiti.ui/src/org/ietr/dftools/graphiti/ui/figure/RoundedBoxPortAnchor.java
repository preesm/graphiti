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

import org.eclipse.draw2d.ChopboxAnchor;
import org.eclipse.draw2d.geometry.Point;

/**
 * This class provides a connection anchor for rounded boxes.
 * 
 * @author Jonathan Piat
 * @author Matthieu Wipliez
 * 
 */
public class RoundedBoxPortAnchor extends ChopboxAnchor {

	private PortAnchorReferenceManager mgr;

	/**
	 * Creates a new rounded box port anchor.
	 * 
	 * @param figure
	 *            The owning vertex figure.
	 * @param portName
	 *            The port name associated with this connection anchor.
	 * @param isOutput
	 *            Whether the connection is input (false) or output (true).
	 */
	public RoundedBoxPortAnchor(VertexFigure figure, String portName,
			boolean isOutput) {
		super(figure);
		mgr = new PortAnchorReferenceManager(figure, portName, isOutput);
	}

	@Override
	public Point getLocation(Point reference) {
		Point mgrReference = mgr.getReferencePoint(this);
		if (mgrReference == null) {
			return super.getLocation(reference);
		} else {
			return mgrReference;
		}
	}

	@Override
	public Point getReferencePoint() {
		Point reference = mgr.getReferencePoint(this);
		if (reference == null) {
			return super.getReferencePoint();
		} else {
			return reference;
		}
	}

}
