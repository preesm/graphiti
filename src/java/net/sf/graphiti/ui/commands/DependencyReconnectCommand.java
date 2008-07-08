package net.sf.graphiti.ui.commands;

import net.sf.graphiti.model.Edge;
import net.sf.graphiti.model.Graph;

/**
 * This class provides a Command that reconnects a dependency. Reconnection is a
 * bit trickier than creation, since we must remember the previous dependency.
 * We inherit from DependencyCreateCommand so we just need to store the previous
 * dependency and parent graph, while keeping most of the original behavior.
 * 
 * @author Matthieu Wipliez
 */
public class DependencyReconnectCommand extends DependencyCreateCommand {

	private Graph previousParentGraph;

	private Edge previousPath;

	@Override
	public void execute() {
		previousParentGraph = previousPath.getSource().getParent();
		parentGraph = source.getParent();

		// Disconnect
		previousParentGraph.removeEdge(previousPath);

		// Reconnect
		path = new Edge(source, target);
		parentGraph.addEdge(path);
	}

	/**
	 * Sets the original dependency (before it is reconnected).
	 * 
	 * @param path
	 *            The dependency.
	 */
	public void setOriginalDependency(Edge path) {
		this.previousPath = path;

		// We also set these because we do not know which one will be set by the
		// NodeEditPolicy (ie if getReconnectSourceCommand or
		// getReconnectTargetCommand is called)
		source = path.getSource();
		target = path.getTarget();
	}

	@Override
	public void undo() {
		parentGraph.removeEdge(path);
		previousParentGraph.addEdge(previousPath);
	}
}