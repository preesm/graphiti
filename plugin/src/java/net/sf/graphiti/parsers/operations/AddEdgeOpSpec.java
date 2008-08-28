/**
 * 
 */
package net.sf.graphiti.parsers.operations;

import net.sf.graphiti.model.Edge;
import net.sf.graphiti.model.Graph;
import net.sf.graphiti.transactions.IBinaryOperationSpecification;
import net.sf.graphiti.transactions.Operand;
import net.sf.graphiti.transactions.Result;

/**
 * This class provides a binary operation that adds a vertex to a graph.
 * 
 * @author Matthieu Wipliez
 * 
 */
public class AddEdgeOpSpec implements
		IBinaryOperationSpecification<Graph, Edge, Void> {

	@Override
	public void execute(Operand<Graph> graph, Operand<Edge> edge,
			Result<Void> result) {
		graph.getContents().addEdge(edge.getContents());
	}

	@Override
	public int getNbOperands() {
		return 2;
	}

	@Override
	public boolean hasResult() {
		return false;
	}

}
