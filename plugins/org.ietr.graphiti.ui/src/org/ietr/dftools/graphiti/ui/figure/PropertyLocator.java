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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.ConnectionLocator;
import org.eclipse.draw2d.geometry.Point;
import org.ietr.dftools.graphiti.model.ParameterPosition;

/**
 * This class provides a connection locator.
 * 
 * @author Jonathan Piat
 * 
 */
public class PropertyLocator extends ConnectionLocator {

	private ParameterPosition pos;

	private HashMap<Connection, List<PropertyLocator>> positions = new HashMap<Connection, List<PropertyLocator>>();

	public PropertyLocator(Connection c, ParameterPosition p) {
		super(c);
		if (positions.get(c) == null) {
			List<PropertyLocator> list = new ArrayList<PropertyLocator>();
			positions.put(c, list);
		}
		pos = p;
		positions.get(c).add(this);
	}

	@Override
	protected Point getReferencePoint() {
		Connection conn = getConnection();
		List<PropertyLocator> listOfProperties = positions.get(conn);
		int maxIndex = listOfProperties.indexOf(this);
		int dec = 5;
		for (int i = 0; i < maxIndex; i++) {
			if (listOfProperties.get(i).pos == this.pos) {
				dec += 10;
			}
		}

		int xdirec = 0;
		int ydirec = 0;
		Point p = Point.SINGLETON;
		Point f = conn.getPoints().getFirstPoint();
		Point l = conn.getPoints().getLastPoint();

		if (l.x > f.x) {
			xdirec = 1;
		} else {
			xdirec = -1;
		}

		if (l.y > f.y) {
			ydirec = 1;
		} else {
			ydirec = -1;
		}
		if (pos.equals(ParameterPosition.West)
				|| pos.equals(ParameterPosition.NorthWest)
				|| pos.equals(ParameterPosition.SouthWest)) {
			Point refP = conn.getPoints().getFirstPoint().getCopy();
			conn.getParent().translateToAbsolute(refP);
			p.setLocation(refP.x + (dec * xdirec), refP.y + (dec * ydirec));
		} else if (pos.equals(ParameterPosition.East)
				|| pos.equals(ParameterPosition.NorthEast)
				|| pos.equals(ParameterPosition.SouthEast)) {
			Point refP = conn.getPoints().getLastPoint().getCopy();
			conn.getParent().translateToAbsolute(refP);
			p.setLocation(refP.x - (dec * xdirec), refP.y - (dec * ydirec));
		} else {
			Point refP = conn.getPoints().getMidpoint().getCopy();
			conn.getParent().translateToAbsolute(refP);
			p.setLocation(refP.x - (dec * xdirec), refP.y - (dec * ydirec));
		}

		return p;
	}

}
