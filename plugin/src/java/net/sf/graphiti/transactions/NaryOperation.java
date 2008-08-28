/*
 * Copyright (c) 2008, IETR/INSA of Rennes
 * All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 * 
 *   * Redistributions of source code must retain the above copyright notice,
 *     this list of conditions and the following disclaimer.
 *   * Redistributions in binary form must reproduce the above copyright notice,
 *     this list of conditions and the following disclaimer in the documentation
 *     and/or other materials provided with the distribution.
 *   * Neither the name of the IETR/INSA of Rennes nor the names of its
 *     contributors may be used to endorse or promote products derived from this
 *     software without specific prior written permission.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT,
 * STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY
 * WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF
 * SUCH DAMAGE.
 */
package net.sf.graphiti.transactions;

import java.util.ArrayList;
import java.util.List;

/**
 * This class provides the specification of a n-ary operation that has the same
 * type for all its operands, and a possibly different type for the result.
 * 
 * @author Matthieu Wipliez
 * 
 */
public class NaryOperation<S, T> extends AbstractOperation<T> implements
		IOperation<T> {

	private List<Operand<S>> operands;

	public NaryOperation(INaryOperationSpecification<S, T> spec) {
		super(spec);
		operands = new ArrayList<Operand<S>>();
	}

	/**
	 * Adds the given operand to this {@link NaryOperation}'s operand list.
	 * 
	 * @param operand
	 *            An {@link Operand}.
	 */
	public void addOperand(Operand<S> operand) {
		operands.add(operand);
	}

	@SuppressWarnings("unchecked")
	public void execute() {
		nbOperands = operands.size();
		super.execute();
		((INaryOperationSpecification<S, T>) spec).execute(operands, result);
	}

	public String operandsToString() {
		String res = "[ ";
		for (Operand<S> op : operands) {
			res += op + " ";
		}
		res += "]";

		return res;
	}

}
