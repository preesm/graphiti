package net.sf.graphiti.ui.commands;

import net.sf.graphiti.model.Edge;
import net.sf.graphiti.model.Graph;
import net.sf.graphiti.model.Vertex;

import org.eclipse.gef.commands.Command;

/**
 * This class provides a Command that creates a dependency. Source and target
 * are set when they are connected.
 * 
 * @author Samuel Beaussier
 * @author Nicolas Isch
 * @author Matthieu Wipliez
 */
public class DependencyCreateCommand extends Command {

	/**
	 * The parentGraph is stored as an attribute so it can be used both in the
	 * <code>execute</code> and <code>undo</code> methods.
	 */
	protected Graph parentGraph;

	/**
	 * The path is stored as an attribute so it can be used both in the
	 * <code>execute</code> and <code>undo</code> methods.
	 */
	protected Edge path;

	protected Vertex source;

	protected Vertex target;

	public DependencyCreateCommand() {

	}

	@Override
	public boolean canExecute() {
		return (source.equals(target) == false);
	}

	@Override
	public void execute() {
		parentGraph = source.getParent();
		if (path == null) {
			path = new Edge(source, target);
		}
		parentGraph.addEdge(path);
	}

	/**
	 * Sets the source of the dependency to create/reconnect.
	 * 
	 * @param source
	 *            The dependency source as a Port.
	 */
	public void setSource(Vertex source) {
		this.source = source;
	}

	/**
	 * Sets the target of the dependency to create/reconnect.
	 * 
	 * @param target
	 *            The dependency target as a Port.
	 */
	public void setTarget(Vertex target) {
		this.target = target;
	}

	@Override
	public void undo() {
		parentGraph.removeEdge(path);
	}
}
