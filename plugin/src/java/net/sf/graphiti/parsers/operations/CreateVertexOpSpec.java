/**
 * 
 */
package net.sf.graphiti.parsers.operations;

import net.sf.graphiti.model.Vertex;
import net.sf.graphiti.transactions.IOperationSpecification;
import net.sf.graphiti.transactions.Operand;
import net.sf.graphiti.transactions.Result;

/**
 * This class provides a zero-parameter operation that creates a vertex.
 * 
 * @author Matthieu Wipliez
 * 
 */
public class CreateVertexOpSpec implements IOperationSpecification {

	@Override
	public void execute(Operand[] operands, Result result) {
		result.setContents(new Vertex());
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
