/**
 * Copyright or © or Copr. IETR/INSA - Rennes (2008 - 2019) :
 *
 * Antoine Morvan [antoine.morvan@insa-rennes.fr] (2017 - 2019)
 * Clément Guy [clement.guy@insa-rennes.fr] (2014)
 * Matthieu Wipliez [matthieu.wipliez@insa-rennes.fr] (2008 - 2011)
 *
 * This software is a computer program whose purpose is to help prototyping
 * parallel applications using dataflow formalism.
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
 */
package org.ietr.dftools.graphiti.model;

import org.ietr.dftools.graphiti.io.ITransformation;

/**
 * This class defines a transformation described with XSLT or with a Java class.
 *
 * @author Matthieu Wipliez
 *
 */
public class Transformation {

  /** The file name. */
  private String fileName;

  /** The instance. */
  private ITransformation instance;

  /** The xslt. */
  private final boolean xslt;

  /**
   * Instantiates a new transformation.
   *
   * @param fileName
   *          the file name
   */
  public Transformation(final String fileName) {
    this.xslt = true;
    this.fileName = fileName;
  }

  /**
   * Instantiates a new transformation.
   *
   * @param instance
   *          the instance
   */
  public Transformation(final ITransformation instance) {
    this.xslt = false;
    this.instance = instance;
  }

  /**
   * Gets the file name.
   *
   * @return the file name
   */
  public String getFileName() {
    return this.fileName;
  }

  /**
   * Gets the single instance of Transformation.
   *
   * @return single instance of Transformation
   */
  public ITransformation getInstance() {
    return this.instance;
  }

  /**
   * Checks if is xslt.
   *
   * @return true, if is xslt
   */
  public boolean isXslt() {
    return this.xslt;
  }

}
