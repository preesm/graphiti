/**
 * 
 */
package net.sf.graphiti.parsers.operations;

import net.sf.graphiti.model.Configuration;
import net.sf.graphiti.model.Graph;
import net.sf.graphiti.transactions.IUnaryOperationSpecification;
import net.sf.graphiti.transactions.Operand;
import net.sf.graphiti.transactions.Result;

/**
 * This class provides a binary operation that adds a vertex to a graph.
 * 
 * @author Matthieu Wipliez
 * 
 */
public class CreateGraphOpSpec implements
		IUnaryOperationSpecification<Configuration, Graph> {

	@Override
	public void execute(Operand<Configuration> configuration,
			Result<Graph> result) {
		result.setContents(new Graph(configuration.getContents()));
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
