package net.sf.graphiti.ui.editparts;

import net.sf.graphiti.model.Edge;
import net.sf.graphiti.model.Graph;
import net.sf.graphiti.model.GraphitiDocument;
import net.sf.graphiti.model.Vertex;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartFactory;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;

/**
 * This class is an implementation of an {@link EditPartFactory}. It creates an
 * EditPart associated with a context and model given.
 * 
 * @author Samuel Beaussier
 * @author Nicolas Isch
 * @author Matthieu Wipliez
 * 
 */
public class EditPartFactoryImpl implements EditPartFactory {

	public EditPartFactoryImpl() {
		// Empty constructor
	}

	public EditPart createEditPart(EditPart context, Object model) {
		AbstractGraphicalEditPart child = null;

		if (model instanceof GraphitiDocument) {
			child = new GraphitiDocumentEditPart();
		} else if (model instanceof Graph) {
			child = new GraphEditPart();
		} else if (model instanceof Vertex) {
			child = new VertexEditPart();
		} else if (model instanceof Edge) {
			child = new EdgeEditPart();
		}

		if (child != null) {
			child.setModel(model);
		}

		return child;
	}
}
