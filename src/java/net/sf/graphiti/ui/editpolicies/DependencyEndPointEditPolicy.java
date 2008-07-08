package net.sf.graphiti.ui.editpolicies;

import net.sf.graphiti.ui.figure.EdgeFigure;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.PolylineConnection;
import org.eclipse.gef.GraphicalEditPart;

/**
 * This class provides facilities to change dependencies appearance when they
 * are selected.
 * 
 * @author Samuel Beaussier
 * @author Nicolas Isch
 */
public class DependencyEndPointEditPolicy extends
		org.eclipse.gef.editpolicies.ConnectionEndpointEditPolicy {

	@Override
	protected void addSelectionHandles() {
		super.addSelectionHandles();
		getConnectionFigure().setLineWidth(EdgeFigure.selectWidth);
		getConnectionFigure().setForegroundColor(ColorConstants.blue);
	}

	/**
	 * Gives the figure that correspond to the connection
	 * 
	 * @return a PolylineConnection
	 */
	protected PolylineConnection getConnectionFigure() {
		return (PolylineConnection) ((GraphicalEditPart) getHost()).getFigure();
	}

	@Override
	protected void removeSelectionHandles() {
		super.removeSelectionHandles();
		getConnectionFigure().setLineWidth(EdgeFigure.basicWidth);
		getConnectionFigure().setForegroundColor(EdgeFigure.color);
	}

}