/**
 * 
 */
package net.sf.graphiti.parsers.operations;

import java.util.List;

import net.sf.graphiti.model.PropertyBean;
import net.sf.graphiti.transactions.INaryOperationSpecification;
import net.sf.graphiti.transactions.Operand;
import net.sf.graphiti.transactions.Result;

/**
 * This class provides a binary operation that adds a vertex to a graph.
 * 
 * @author Matthieu Wipliez
 * 
 */
public class SetParameterValueOpSpec implements
		INaryOperationSpecification<Object, Void> {

	@Override
	public void execute(List<Operand<Object>> operands, Result<Void> result) {
		PropertyBean obj = (PropertyBean) operands.get(0).getContents();
		if (obj != null) {
			String name = (String) operands.get(1).getContents();
			Object value = operands.get(2).getContents();
			obj.setValue(name, value);
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
