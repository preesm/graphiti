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

/**
 * This class provides an abstract implementation of an {@link IOperation}. It
 * accepts an {@link IOperationSpecification} to get its number of operands and
 * presence of result.
 * 
 * @author Matthieu Wipliez
 * 
 */
public class Operation {

	/**
	 * The current number of operands. Should be updated before calling
	 * {@link #execute()}.
	 */
	private Operand[] operands;

	/**
	 * The result of this operation.
	 */
	private Result result;

	/**
	 * The specification of this operation.
	 */
	private IOperationSpecification spec;

	/**
	 * Creates a new {@link Operation} using the given operation specification.
	 * 
	 * @param spec
	 *            An {@link IOperationSpecification}.
	 */
	public Operation(IOperationSpecification spec) {
		this.spec = spec;
		operands = new Operand[] {};
	}

	/**
	 * Executes this operation.
	 */
	public void execute() {
		if (operands.length < spec.getNbOperands()) {
			throw new NotEnoughOperandsException();
		}

		if (spec.hasResult() && result == null) {
			result = new Result();
		}

		spec.execute(operands, result);
	}

	/**
	 * Returns the result of this operation.
	 * 
	 * @return A {@link Result} if this operation specification specifies it has
	 *         a result.
	 * @throws OperationHasNoResultException
	 *             If this operation has no result.
	 */
	public Result getResult() {
		if (!spec.hasResult()) {
			throw new OperationHasNoResultException();
		}

		if (result == null) {
			result = new Result();
		}

		return result;
	}

	/**
	 * Sets this operation only operand.
	 * 
	 * @param operand
	 *            An {@link Operand}.
	 */
	public void setOperand(Operand operands) {
		this.operands = new Operand[] { operands };
	}

	/**
	 * Sets this operation's operands.
	 * 
	 * @param operand1
	 *            An array of {@link Operand}.
	 */
	public void setOperands(Operand operand1, Operand operand2) {
		this.operands = new Operand[] { operand1, operand2 };
	}

	/**
	 * Sets this operation's operands.
	 * 
	 * @param operands
	 *            An array of {@link Operand}.
	 */
	public void setOperands(Operand[] operands) {
		this.operands = operands;
	}

	@Override
	public String toString() {
		String res = "[ ";
		for (Operand op : operands) {
			res += op + " ";
		}
		res += "]";

		if (spec.hasResult()) {
			Result result = getResult();
			res += " -> " + result;
		}

		return res;
	}

}
