package net.sf.graphiti.ui.editpolicies;

import net.sf.graphiti.model.Edge;
import net.sf.graphiti.model.Vertex;
import net.sf.graphiti.ui.commands.DependencyCreateCommand;
import net.sf.graphiti.ui.commands.DependencyReconnectCommand;
import net.sf.graphiti.ui.editparts.VertexEditPart;
import net.sf.graphiti.ui.figure.EdgeFigure;

import org.eclipse.draw2d.Connection;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.GraphicalNodeEditPolicy;
import org.eclipse.gef.requests.CreateConnectionRequest;
import org.eclipse.gef.requests.ReconnectRequest;

/**
 * This class provides methods that deal with creations and connections of
 * dependencies. When the user clicks a source port, the method
 * <code>getConnectionCreateCommand</code> is first called. When they click
 * the destination port, <code>getConnectionCompleteCommand</code> is called.
 * 
 * <code>getReconnectSourceCommand</code> and
 * <code>getReconnectTargetCommand</code> are called when the user reconnects
 * one end of a dependency (they have previously disconnected).
 * 
 * @author Samuel Beaussier
 * @author Nicolas Isch
 * @author Matthieu Wipliez
 */
public class NodeEditPolicy extends GraphicalNodeEditPolicy {

	@Override
	protected Connection createDummyConnection(Request req) {
		EdgeFigure conn = new EdgeFigure(null);
		return conn;
	}

	@Override
	protected Command getConnectionCompleteCommand(
			CreateConnectionRequest request) {
		DependencyCreateCommand command = (DependencyCreateCommand) request
				.getStartCommand();
		VertexEditPart vertexEditPart = (VertexEditPart) request
				.getTargetEditPart();
		command.setTarget((Vertex) (vertexEditPart.getModel()));
		return command;
	}

	@Override
	protected Command getConnectionCreateCommand(CreateConnectionRequest request) {
		DependencyCreateCommand command = new DependencyCreateCommand();
		VertexEditPart vertexEditPart = (VertexEditPart) request
				.getTargetEditPart();
		command.setSource((Vertex) (vertexEditPart.getModel()));
		request.setStartCommand(command);
		return command;
	}

	@Override
	protected Command getReconnectSourceCommand(ReconnectRequest request) {
		DependencyReconnectCommand command = new DependencyReconnectCommand();
		command.setOriginalDependency((Edge) request.getConnectionEditPart()
				.getModel());
		VertexEditPart vertexEditPart = (VertexEditPart) getHost();
		command.setSource((Vertex) vertexEditPart.getModel());
		return command;
	}

	@Override
	protected Command getReconnectTargetCommand(ReconnectRequest request) {
		DependencyReconnectCommand command = new DependencyReconnectCommand();
		command.setOriginalDependency((Edge) request.getConnectionEditPart()
				.getModel());
		VertexEditPart vertexEditPart = (VertexEditPart) getHost();
		command.setTarget((Vertex) vertexEditPart.getModel());
		return command;
	}
}
