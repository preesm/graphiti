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
 * This class provides the specification of a unary operation that has different
 * possible types for its operand and its result.
 * 
 * @author Matthieu Wipliez
 * 
 */
public class UnaryOperation<O, R> extends AbstractOperation<R> implements
		IOperation<R> {

	private Operand<O> operand;

	/**
	 * Creates a new {@link UnaryOperation} using the given operation
	 * specification.
	 * 
	 * @param spec
	 *            An {@link IUnaryOperationSpecification}.
	 */
	public UnaryOperation(IUnaryOperationSpecification<O, R> spec) {
		super(spec);
	}

	@SuppressWarnings("unchecked")
	public void execute() {
		nbOperands = (operand != null) ? 1 : 0;
		super.execute();
		((IUnaryOperationSpecification<O, R>) spec).execute(operand, result);
	}

	public String operandsToString() {
		return "[ " + operand + " ]";
	}

	/**
	 * Sets this unary operation only operand.
	 * 
	 * @param operand
	 *            An {@link Operand}.
	 */
	public void setOperand(Operand<O> operand) {
		this.operand = operand;
	}

}
