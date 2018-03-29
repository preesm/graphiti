/**
 * Copyright or © or Copr. IETR/INSA - Rennes (2008 - 2018) :
 *
 * Antoine Morvan <antoine.morvan@insa-rennes.fr> (2017 - 2018)
 * Clément Guy <clement.guy@insa-rennes.fr> (2014)
 * Matthieu Wipliez <matthieu.wipliez@insa-rennes.fr> (2008 - 2010)
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
package org.ietr.dftools.graphiti.ui.commands.refinement;

import org.eclipse.core.runtime.IPath;
import org.eclipse.gef.commands.Command;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.ietr.dftools.graphiti.model.IRefinementPolicy;
import org.ietr.dftools.graphiti.model.Vertex;
import org.ietr.dftools.graphiti.ui.editparts.VertexEditPart;

/**
 * This class provides a way to create a vertex refinement.
 *
 * @author Matthieu Wipliez
 *
 */
public class SetRefinementCommand extends Command {

  /** The refinement. */
  private IPath refinement;

  /** The vertex. */
  private Vertex vertex;

  /** The policy. */
  private IRefinementPolicy policy;

  /*
   * (non-Javadoc)
   *
   * @see org.eclipse.gef.commands.Command#canExecute()
   */
  @Override
  public boolean canExecute() {
    if (this.vertex == null) {
      return false;
    } else {
      return this.policy.isRefinable(this.vertex);
    }
  }

  /*
   * (non-Javadoc)
   *
   * @see org.eclipse.gef.commands.Command#execute()
   */
  @Override
  public void execute() {
    // save old value of refinement in refinement
    // allows execute() to be executed by undo()
    this.refinement = this.policy.setRefinement(this.vertex, this.refinement);
  }

  /*
   * (non-Javadoc)
   *
   * @see org.eclipse.gef.commands.Command#getLabel()
   */
  @Override
  public String getLabel() {
    return "Set refinement";
  }

  /**
   * Runs this command, but do not actually change the refinement. This will be done by execute.
   *
   * @return true, if successful
   */
  public boolean run() {
    final IPath vertexRefinement = this.policy.getRefinement(this.vertex);
    final IPath newRefinement = this.policy.getNewRefinement(this.vertex);
    if ((newRefinement != null) && !newRefinement.equals(vertexRefinement)) {
      this.refinement = newRefinement;
      return true;
    }

    return false;
  }

  /**
   * Sets the selection.
   *
   * @param selection
   *          the new selection
   * @see RefinementManager#setSelection(ISelection)
   */
  public void setSelection(final ISelection selection) {
    if (selection instanceof IStructuredSelection) {
      final Object obj = ((IStructuredSelection) selection).getFirstElement();
      if (obj instanceof VertexEditPart) {
        // we are dealing with a vertex edit part
        this.vertex = (Vertex) ((VertexEditPart) obj).getModel();
        this.policy = this.vertex.getConfiguration().getRefinementPolicy();
      }
    }
  }

  /*
   * (non-Javadoc)
   *
   * @see org.eclipse.gef.commands.Command#undo()
   */
  @Override
  public void undo() {
    execute();
  }

}
