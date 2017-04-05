/*******************************************************************************
 * Copyright or © or Copr. IETR/INSA - Rennes (2008 - 2017) :
 *
 * Antoine Morvan <antoine.morvan@insa-rennes.fr> (2017)
 * Clément Guy <clement.guy@insa-rennes.fr> (2014)
 * Matthieu Wipliez <matthieu.wipliez@insa-rennes.fr> (2008 - 2010)
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
package org.ietr.dftools.graphiti.ui.editpolicies;

import org.eclipse.draw2d.PolylineConnection;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.editpolicies.ConnectionEndpointEditPolicy;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Device;

/**
 * This class provides facilities to change dependencies appearance when they
 * are selected.
 * 
 * @author Samuel Beaussier
 * @author Nicolas Isch
 */
public class DependencyEndPointEditPolicy extends ConnectionEndpointEditPolicy {

	private Color color;

	@Override
	protected void addSelectionHandles() {
		super.addSelectionHandles();
		PolylineConnection connection = getConnectionFigure();
		color = connection.getForegroundColor();
		Device device = color.getDevice();
		int red = (255 - color.getRed()) / 2;
		int green = (255 - color.getGreen()) / 2;
		int blue = (255 - color.getBlue()) / 2;
		connection.setForegroundColor(new Color(device, red, green, blue));
	}

	/**
	 * Gives the figure that correspond to the connection
	 * 
	 * @return a PolylineConnection
	 */
	protected PolylineConnection getConnectionFigure() {
		return (PolylineConnection) ((GraphicalEditPart) getHost()).getFigure();
	}

	@Override
	protected void removeSelectionHandles() {
		super.removeSelectionHandles();
		PolylineConnection connection = getConnectionFigure();
		if (color != null) {
			connection.setForegroundColor(color);
		}
	}

}
