/**
 * 
 */
package net.sf.graphiti.parsers.operations;

import net.sf.graphiti.model.Edge;
import net.sf.graphiti.transactions.IUnaryOperationSpecification;
import net.sf.graphiti.transactions.Operand;
import net.sf.graphiti.transactions.Result;

/**
 * This class provides a unary operation that creates an edge.
 * 
 * @author Matthieu Wipliez
 * 
 */
public class CreateEdgeOpSpec implements
		IUnaryOperationSpecification<Object, Edge> {

	@Override
	public void execute(Operand<Object> noop, Result<Edge> result) {
		result.setContents(new Edge());
	}

	@Override
	public int getNbOperands() {
		return 0;
	}

	@Override
	public boolean hasResult() {
		return true;
	}

}
