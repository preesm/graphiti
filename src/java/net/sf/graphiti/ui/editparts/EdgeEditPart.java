package net.sf.graphiti.ui.editparts;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import net.sf.graphiti.model.Edge;
import net.sf.graphiti.ui.editpolicies.DependencyEditPolicy;
import net.sf.graphiti.ui.editpolicies.DependencyEndPointEditPolicy;
import net.sf.graphiti.ui.figure.EdgeFigure;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.RoutingAnimator;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.editparts.AbstractConnectionEditPart;

/**
 * The EditPart associated to the Dependency gives methods to refresh the view
 * when a property has changed.
 * 
 * @author Samuel Beaussier
 * @author Nicolas Isch
 * 
 */
public class EdgeEditPart extends AbstractConnectionEditPart implements
		PropertyChangeListener {

	public static EdgeFigure createNewBendableWire(Edge wire) {
		EdgeFigure conn = new EdgeFigure(wire);
		conn.setLineWidth(15);
		conn.addRoutingListener(RoutingAnimator.getDefault());
		return conn;
	}

	private IFigure connectionFigure;

	@Override
	public void activate() {
		super.activate();
		Edge model = (Edge) getModel();
		model.addPropertyChangeListener(this);
	}

	@Override
	protected void createEditPolicies() {
		installEditPolicy(EditPolicy.CONNECTION_ENDPOINTS_ROLE,
				new DependencyEndPointEditPolicy());
		installEditPolicy(EditPolicy.CONNECTION_ROLE,
				new DependencyEditPolicy());
		// refreshBendpointEditPolicy();
	}

	/**
	 * Creates a new DependencyGef figure
	 */
	@Override
	protected IFigure createFigure() {
		if (connectionFigure == null) {
			Edge model = (Edge) getModel();
			connectionFigure = new EdgeFigure(model);
		}
		return connectionFigure;
	}

	@Override
	public void deactivate() {
		super.deactivate();
		Edge model = (Edge) getModel();
		model.removePropertyChangeListener(this);
	}

	public void propertyChange(PropertyChangeEvent evt) {
		// bendpoints, connection router properties
	}

	// private void refreshBendpointEditPolicy() {
	// if (getConnectionFigure().getConnectionRouter() instanceof
	// ManhattanConnectionRouter)
	// installEditPolicy(EditPolicy.CONNECTION_BENDPOINTS_ROLE, null);
	// else
	// installEditPolicy(EditPolicy.CONNECTION_BENDPOINTS_ROLE,
	// new DependencyBendpointEditPolicy());
	// }

	/**
	 * Updates the bendpoints, based on the model.
	 */
	protected void refreshBendpoints() {
//		 if (getConnectionFigure().getConnectionRouter() instanceof
//		 ManhattanConnectionRouter)
//		 return;
//		 List<Bendpoint> modelConstraint = ((Edge) getModel())
//		 .getBendpoints();
//		 List<RelativeBendpoint> figureConstraint = new
//		 ArrayList<RelativeBendpoint>();
//		 for (int i = 0; i < modelConstraint.size(); i++) {
//		 DependencyBendpoint wbp = (DependencyBendpoint) modelConstraint
//		 .get(i);
//		 RelativeBendpoint rbp = new RelativeBendpoint(getConnectionFigure());
//		 rbp.setRelativeDimensions(wbp.getFirstRelativeDimension(), wbp
//		 .getSecondRelativeDimension());
//		 rbp.setWeight((i + 1) / ((float) modelConstraint.size() + 1));
//		 figureConstraint.add(rbp);
//		 }
//		 getConnectionFigure().setRoutingConstraint(figureConstraint);
	}
}