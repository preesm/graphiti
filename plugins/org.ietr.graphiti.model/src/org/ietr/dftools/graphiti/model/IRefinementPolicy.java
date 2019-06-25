/**
 * Copyright or © or Copr. IETR/INSA - Rennes (2008 - 2019) :
 *
 * Antoine Morvan [antoine.morvan@insa-rennes.fr] (2017 - 2019)
 * Clément Guy [clement.guy@insa-rennes.fr] (2014)
 * Matthieu Wipliez [matthieu.wipliez@insa-rennes.fr] (2008 - 2010)
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

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IPath;

/**
 * This interface defines the policy for refining vertices.
 *
 * @author Matthieu Wipliez
 *
 */
public interface IRefinementPolicy {

  /**
   * Returns the value of the refinement parameter associated with the given vertex.
   *
   * @param vertex
   *          a vertex
   * @return the value of the refinement parameter associated with the given vertex, or <code>null</code>
   */
  IPath getRefinement(Vertex vertex);

  /**
   * Returns the file that corresponds to the refinement associated with the given vertex.
   *
   * @param vertex
   *          a vertex
   * @return the file that corresponds to the refinement associated with the given vertex, or <code>null</code>
   */
  IFile getRefinementFile(Vertex vertex);

  /**
   * Returns <code>true</code> if the given vertex accepts a refinement parameter.
   *
   * @param vertex
   *          a vertex
   * @return <code>true</code> if the given vertex accepts a refinement parameter.
   */
  boolean isRefinable(Vertex vertex);

  /**
   * Gets a new refinement by asking the user.
   *
   * @param vertex
   *          a vertex
   * @return <code>true</code> if the refinement changed, <code>false</code> otherwise
   */
  IPath getNewRefinement(Vertex vertex);

  /**
   * Sets the refinement of this vertex to the given value.
   *
   * @param vertex
   *          a vertex
   * @param refinement
   *          a refinement
   * @return the previous value of the refinement
   */
  IPath setRefinement(Vertex vertex, IPath refinement);

}
