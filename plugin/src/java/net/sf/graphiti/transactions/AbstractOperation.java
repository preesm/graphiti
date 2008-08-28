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
public abstract class AbstractOperation<T> implements IOperation<T> {

	/**
	 * The current number of operands. Should be updated before calling
	 * {@link #execute()}.
	 */
	protected int nbOperands;

	/**
	 * The result of this operation.
	 */
	protected Result<T> result;

	/**
	 * The specification of this operation.
	 */
	final protected IOperationSpecification spec;

	/**
	 * Creates a new {@link AbstractOperation} using the given operation
	 * specification.
	 * 
	 * @param spec
	 *            An {@link IOperationSpecification}.
	 */
	public AbstractOperation(IOperationSpecification spec) {
		this.spec = spec;
	}

	/**
	 * This implementation does not actually execute anything, it merely checks
	 * the operation <b>can</b> be executed.
	 */
	@Override
	public void execute() {
		if (nbOperands < spec.getNbOperands()) {
			throw new NotEnoughOperandsException();
		}

		if (spec.hasResult() && result == null) {
			result = new Result<T>();
		}
	}

	@Override
	public Result<T> getResult() {
		if (!spec.hasResult()) {
			throw new OperationHasNoResultException();
		}

		if (result == null) {
			result = new Result<T>();
		}

		return result;
	}

	/**
	 * This function must be implemented by subclasses to return a string
	 * representation of the operation operands.
	 * 
	 * @return A string representation of the operation operands.
	 */
	protected abstract String operandsToString();

	public String toString() {
		String res = operandsToString();

		if (spec.hasResult()) {
			Result<T> result = getResult();
			res += " -> " + result;
		}

		return res;
	}

}
