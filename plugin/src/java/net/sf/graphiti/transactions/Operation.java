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
 * This class provides an operation. It accepts an
 * {@link IOperationSpecification} to get its number of operands and presence of
 * result.
 * 
 * @author Matthieu Wipliez
 * 
 */
public class Operation {

	/**
	 * This class provides an operand to an operation. An operand may be created
	 * on an arbitrary type, or on a {@link Result}.
	 * 
	 * @author Matthieu Wipliez
	 * 
	 */
	public class Operand {

		private Object contents;

		private Result result;

		public Operand() {
			contents = null;
			result = null;
		}

		/**
		 * Creates a new {@link Operand} with the given contents. Note that the
		 * behavior of this operand will depend on whether
		 * <code>(contents <b>instanceof</b> Result)</code> is true or not.
		 * 
		 * @param contents
		 */
		public Operand(Object contents) {
			if (contents instanceof Result) {
				this.contents = null;
				this.result = (Result) contents;
			} else {
				this.contents = contents;
				result = null;
			}
		}

		/**
		 * Returns the contents of this operand. If this operand was created on
		 * a {@link Result}, returns the contents of the result.
		 * 
		 * @return An {@link Object}, possibly <code>null</code>.
		 */
		public Object getContents() {
			if (result == null) {
				return contents;
			} else {
				return result.getContents();
			}
		}

		public String toString() {
			if (contents == null) {
				if (result == null) {
					return "<no contents>";
				} else {
					return result.toString();
				}
			} else {
				return contents.toString();
			}
		}

	}

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

		spec.execute(fromOperandsArray(operands), result);
	}

	/**
	 * Converts the given {@link Operand} array to an object array. Each object
	 * is obtained using the {@link Operand#getContents()} method on each
	 * operand.
	 * 
	 * @param operands
	 *            An array of operands.
	 * @return An array of objects.
	 */
	private Object[] fromOperandsArray(Operand[] operands) {
		Object[] objects = new Object[operands.length];
		for (int i = 0; i < operands.length; i++) {
			objects[i] = operands[i].getContents();
		}
		return objects;
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
	 *            An {@link Object}.
	 */
	public void setOperand(Object operand) {
		this.operands = toOperandsArray(new Object[] { operand });
	}

	/**
	 * Sets this operation's two operands.
	 * 
	 * @param operand1
	 *            The first operand.
	 * @param operand2
	 *            The second operand.
	 */
	public void setOperands(Object operand1, Object operand2) {
		this.operands = toOperandsArray(new Object[] { operand1, operand2 });
	}

	/**
	 * Sets this operation's operands.
	 * 
	 * @param operands
	 *            An array of {@link Object}.
	 */
	public void setOperands(Object[] operands) {
		this.operands = toOperandsArray(operands);
	}

	/**
	 * Converts the given {@link Object} array to an operand array. Each operand
	 * is obtained by wrapping each object in an {@link Operand}.
	 * 
	 * @param operands
	 *            An array of objects.
	 * @return An array of operands.
	 */
	private Operand[] toOperandsArray(Object[] objects) {
		Operand[] operands = new Operand[objects.length];
		for (int i = 0; i < objects.length; i++) {
			operands[i] = new Operand(objects[i]);
		}
		return operands;
	}

	@Override
	public String toString() {
		String res = spec.getName() + ": [ ";
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
