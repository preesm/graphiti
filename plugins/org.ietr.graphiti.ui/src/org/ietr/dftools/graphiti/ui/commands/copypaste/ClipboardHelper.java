package org.ietr.dftools.graphiti.ui.commands.copypaste;

import java.util.List;
import org.eclipse.jface.util.LocalSelectionTransfer;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.dnd.Transfer;
import org.ietr.dftools.graphiti.model.Vertex;
import org.ietr.dftools.graphiti.ui.actions.GraphitiClipboard;

/**
 *
 * @author anmorvan
 *
 */
public interface ClipboardHelper {

  /**
   *
   */
  public static void populateClipboard(final List<Vertex> vertices) {
    // prepare transfer
    final LocalSelectionTransfer transfer = LocalSelectionTransfer.getTransfer();
    final Object[] verticesArray = vertices.toArray();
    transfer.setSelection(new StructuredSelection(verticesArray));

    // put in clipboard
    final Object[] data = new Object[] { verticesArray };
    final Transfer[] transfers = new Transfer[] { transfer };
    GraphitiClipboard.getInstance().setContents(data, transfers);
  }
}
