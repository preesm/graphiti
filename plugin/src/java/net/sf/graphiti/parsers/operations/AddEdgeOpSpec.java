/**
 * 
 */
package net.sf.graphiti.parsers.operations;

import net.sf.graphiti.model.Edge;
import net.sf.graphiti.model.Graph;
import net.sf.graphiti.transactions.IOperationSpecification;
import net.sf.graphiti.transactions.Operand;
import net.sf.graphiti.transactions.Result;

/**
 * This class provides a binary operation that adds an edge to a graph.
 * 
 * @author Matthieu Wipliez
 * 
 */
public class AddEdgeOpSpec implements IOperationSpecification {

	@Override
	public void execute(Operand[] operands, Result result) {
		Graph graph = (Graph) operands[0].getContents();
		Edge edge = (Edge) operands[1].getContents();
		graph.addEdge(edge);
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
