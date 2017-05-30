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

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;

// TODO: Auto-generated Javadoc
/**
 * This class provides a hexagon shape.
 *
 * @author Jonathan Piat
 * @author Matthieu Wipliez
 *
 */
public class ShapeHexagon extends AbstractPolygonShape implements IShape {

  /**
   * Creates a new hexagon shape.
   */
  public ShapeHexagon() {
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.ietr.dftools.graphiti.ui.figure.shapes.AbstractPolygonShape#setDimension(org.eclipse.draw2d.geometry.Dimension)
   */
  @Override
  public void setDimension(final Dimension dim) {
    removeAllPoints();
    final int quantx = dim.width / 3;
    // int quanty = height/3 ;
    setStart(new Point(0, dim.height / 2));
    addPoint(new Point(quantx, 0));
    addPoint(new Point(2 * quantx, 0));
    addPoint(new Point(dim.width, dim.height / 2));
    addPoint(new Point(dim.width - quantx, dim.height));
    addPoint(new Point(dim.width - (2 * quantx), dim.height));
    addPoint(new Point(0, dim.height / 2));
  }

}