/**
 * 
 */
package net.sf.graphiti.parsers.operations;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sf.graphiti.model.Edge;
import net.sf.graphiti.model.Graph;
import net.sf.graphiti.model.Vertex;
import net.sf.graphiti.transactions.INaryOperationSpecification;
import net.sf.graphiti.transactions.Operand;
import net.sf.graphiti.transactions.Result;

/**
 * This class provides a n-ary operation that sets an edge source or
 * destination. Parameters: graph, edge, endpoint type ("source" or
 * "destination"), value (vertex id).
 * 
 * @author Matthieu Wipliez
 * 
 */
public class SetEdgeEndpointOpSpec implements
		INaryOperationSpecification<Object, Void> {

	@Override
	public void execute(List<Operand<Object>> operands, Result<Void> result) {
		Graph graph = (Graph) operands.get(0).getContents();
		Edge edge = (Edge) operands.get(1).getContents();
		if (edge != null) {
			String type = (String) operands.get(2).getContents();
			String value = (String) operands.get(3).getContents();

			// set to map
			Set<Vertex> vertices = graph.vertexSet();
			Map<String, Vertex> map = new HashMap<String, Vertex>();
			for (Vertex vertex : vertices) {
				map.put((String) vertex.getValue(Vertex.PARAMETER_ID), vertex);
			}

			if (type.equals("source")) {
				// sets the source
				edge.setSource(map.get(value));
			} else {
				// sets the destination
				edge.setTarget(map.get(value));
			}
		}
	}

	@Override
	public int getNbOperands() {
		return 3;
	}

	@Override
	public boolean hasResult() {
		return false;
	}

}
