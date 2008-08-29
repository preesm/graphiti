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
 * This class provides a 4-ary operation that sets an edge source or
 * destination. Parameters: graph, edge, endpoint type ("source" or "target"),
 * vertexId.
 * 
 * @author Matthieu Wipliez
 * 
 */
public class SetEdgeEndpointOpSpec implements IOperationSpecification {

	@Override
	public void execute(Operand[] operands, Result result) {
		Graph graph = (Graph) operands[0].getContents();
		Edge edge = (Edge) operands[1].getContents();
		if (edge != null) {
			String type = (String) operands[2].getContents();
			String vertexId = (String) operands[3].getContents();

			if (type.equals("source")) {
				edge.setSource(graph.findVertex(vertexId));
			} else {
				edge.setTarget(graph.findVertex(vertexId));
			}
		}
	}

	@Override
	public int getNbOperands() {
		return 4;
	}

	@Override
	public boolean hasResult() {
		return false;
	}

}
