/**
 * 
 */
package net.sf.graphiti.parsers.operations;

import net.sf.graphiti.model.Configuration;
import net.sf.graphiti.model.Graph;
import net.sf.graphiti.transactions.IOperationSpecification;
import net.sf.graphiti.transactions.Operand;
import net.sf.graphiti.transactions.Result;

/**
 * This class provides a unary operation that creates a graph with a given
 * configuration.
 * 
 * @author Matthieu Wipliez
 * 
 */
public class CreateGraphOpSpec implements IOperationSpecification {

	@Override
	public void execute(Operand[] operands, Result result) {
		Configuration configuration = (Configuration) operands[0].getContents();
		result.setContents(new Graph(configuration));
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
