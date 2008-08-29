/**
 * 
 */
package net.sf.graphiti.parsers.operations;

import net.sf.graphiti.model.Graph;
import net.sf.graphiti.model.Vertex;
import net.sf.graphiti.transactions.IOperationSpecification;
import net.sf.graphiti.transactions.Operand;
import net.sf.graphiti.transactions.Result;

/**
 * This class provides a binary operation that adds a vertex to a graph.
 * 
 * @author Matthieu Wipliez
 * 
 */
public class AddVertexOpSpec implements IOperationSpecification {

	@Override
	public void execute(Operand[] operands, Result result) {
		Graph graph = (Graph) operands[0].getContents();
		Vertex vertex = (Vertex) operands[1].getContents();
		graph.addVertex(vertex);
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
