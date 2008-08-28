/**
 * 
 */
package net.sf.graphiti.parsers.operations;

import net.sf.graphiti.model.Vertex;
import net.sf.graphiti.transactions.IUnaryOperationSpecification;
import net.sf.graphiti.transactions.Operand;
import net.sf.graphiti.transactions.Result;

/**
 * This class provides a binary operation that adds a vertex to a graph.
 * 
 * @author Matthieu Wipliez
 * 
 */
public class CreateVertexOpSpec implements
		IUnaryOperationSpecification<Object, Vertex> {

	@Override
	public void execute(Operand<Object> noop, Result<Vertex> result) {
		result.setContents(new Vertex());
	}

	@Override
	public int getNbOperands() {
		return 1;
	}

	@Override
	public boolean hasResult() {
		return true;
	}

}
