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
package org.ietr.dftools.graphiti.ui.figure.shapes;

/**
 * This class provides a circle shape.
 *
 * @author Jonathan Piat
 * @author Matthieu Wipliez
 *
 */
public class ShapeFactory {

	public static IShape createShape(final String name) {
		IShape shape = null;
		if ("circle".equals(name)) {
			shape = new ShapeCircle();
		} else if ("hexagon".equals(name)) {
			shape = new ShapeHexagon();
		} else if ("lozenge".equals(name)) {
			shape = new ShapeLozenge();
		} else if ("roundedBox".equals(name)) {
			shape = new ShapeRoundedBox();
		} else if ("triangle".equals(name)) {
			shape = new ShapeTriangle();
		} else {
			throw new IllegalArgumentException("Invalid shape name: " + name);
		}

		return shape;
	}

}
