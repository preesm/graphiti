/**
 * 
 */
package net.sf.graphiti.parsers.operations;

import net.sf.graphiti.model.PropertyBean;
import net.sf.graphiti.transactions.IOperationSpecification;
import net.sf.graphiti.transactions.Operand;
import net.sf.graphiti.transactions.Result;

/**
 * This class provides a 3-ary operation that sets a graph/vertex/edge property
 * value.
 * 
 * @author Matthieu Wipliez
 * 
 */
public class SetParameterValueOpSpec implements IOperationSpecification {

	@Override
	public void execute(Operand[] operands, Result result) {
		PropertyBean obj = (PropertyBean) operands[0].getContents();
		if (obj != null) {
			String name = (String) operands[1].getContents();
			Object value = operands[2].getContents();
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
