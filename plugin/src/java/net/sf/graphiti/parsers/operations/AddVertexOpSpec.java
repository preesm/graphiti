/**
 * 
 */
package net.sf.graphiti.parsers.operations;

import net.sf.graphiti.model.Graph;
import net.sf.graphiti.model.Vertex;
import net.sf.graphiti.transactions.IBinaryOperationSpecification;
import net.sf.graphiti.transactions.Operand;
import net.sf.graphiti.transactions.Result;

/**
 * This class provides a binary operation that adds a vertex to a graph.
 * 
 * @author Matthieu Wipliez
 * 
 */
public class AddVertexOpSpec implements
		IBinaryOperationSpecification<Graph, Vertex, Void> {

	@Override
	public void execute(Operand<Graph> graph, Operand<Vertex> vertex,
			Result<Void> result) {
		graph.getContents().addVertex(vertex.getContents());
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
