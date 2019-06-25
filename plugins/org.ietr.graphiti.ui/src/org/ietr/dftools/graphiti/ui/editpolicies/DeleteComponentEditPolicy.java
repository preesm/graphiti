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
package org.ietr.dftools.graphiti.ui.editpolicies;

import java.util.List;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.editpolicies.ComponentEditPolicy;
import org.eclipse.gef.requests.GroupRequest;
import org.ietr.dftools.graphiti.ui.commands.DeleteCommand;
import org.ietr.dftools.graphiti.ui.editparts.EdgeEditPart;
import org.ietr.dftools.graphiti.ui.editparts.VertexEditPart;

/**
 * This class overrides the createDeleteCommand to return a command that can delete a vertex.
 *
 * @author Samuel Beaussier
 * @author Nicolas Isch
 * @author Matthieu Wipliez
 */
public class DeleteComponentEditPolicy extends ComponentEditPolicy {

  /*
   * (non-Javadoc)
   *
   * @see org.eclipse.gef.editpolicies.ComponentEditPolicy#createDeleteCommand(org.eclipse.gef.requests.GroupRequest)
   */
  @Override
  protected Command createDeleteCommand(final GroupRequest deleteRequest) {
    if (getHost() instanceof VertexEditPart) {
      final VertexEditPart part = (VertexEditPart) getHost();
      final List<?> incoming = part.getSourceConnections();
      final List<?> outgoing = part.getTargetConnections();
      if (!incoming.isEmpty() || !outgoing.isEmpty()) {
        final CompoundCommand compound = new CompoundCommand();
        for (final Object obj : incoming) {
          final DeleteCommand command = new DeleteCommand(((EdgeEditPart) obj).getModel());
          compound.add(command);
        }

        for (final Object obj : outgoing) {
          final DeleteCommand command = new DeleteCommand(((EdgeEditPart) obj).getModel());
          compound.add(command);
        }

        final DeleteCommand command = new DeleteCommand(getHost().getModel());
        compound.add(command);

        return compound;
      }
    }

    return new DeleteCommand(getHost().getModel());
  }

}
