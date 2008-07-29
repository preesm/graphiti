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
package net.sf.graphiti.transactions.sampleImpl;

import net.sf.graphiti.transactions.BinaryOperation;
import net.sf.graphiti.transactions.NaryOperation;
import net.sf.graphiti.transactions.NestedTransaction;
import net.sf.graphiti.transactions.Operand;
import net.sf.graphiti.transactions.Result;
import net.sf.graphiti.transactions.SimpleTransaction;
import net.sf.graphiti.transactions.UnaryOperation;

/**
 * Implementation of a very simple calculator to test operations.
 * 
 * @author Matthieu Wipliez
 * 
 */
public class Calculator {

	public Calculator() {
		// Example of transaction: (1 + 2 + 3) * 4
		NestedTransaction rootTransaction = new NestedTransaction();

		// transaction (1 + 2 + 3) => operation 1 + 2 + 3
		SimpleTransaction onePlusTwoPlusThree = new SimpleTransaction(
				rootTransaction);
		NaryOperation<Integer, Integer> sumOperation = new NaryOperation<Integer, Integer>(
				new AddOperation());
		sumOperation.addOperand(new Operand<Integer>(new Integer(1)));
		sumOperation.addOperand(new Operand<Integer>(new Integer(2)));
		sumOperation.addOperand(new Operand<Integer>(new Integer(3)));

		onePlusTwoPlusThree.addOperation(sumOperation);

		// Rollback and adds again
		onePlusTwoPlusThree.rollback();
		onePlusTwoPlusThree.addOperation(sumOperation);

		// transaction (1 + 2 + 3) * 4 => (transaction 1) * 4
		SimpleTransaction timesFourTransaction = new SimpleTransaction(
				rootTransaction);

		BinaryOperation<Integer, Integer, Integer> timesFour = new BinaryOperation<Integer, Integer, Integer>(
				new TimesOperation());
		timesFour.setOperand1(new Operand<Integer>(sumOperation.getResult()));
		timesFour.setOperand2(new Operand<Integer>(new Integer(4)));

		timesFourTransaction.addOperation(timesFour);

		// commit transaction
		rootTransaction.commit();

		Result<Integer> result = timesFour.getResult();
		System.out.println(result);

		// rollback transaction onePlusTwoPlusThree
		try {
			// will print an exception
			onePlusTwoPlusThree.rollback();
		} catch (RuntimeException e) {
			e.printStackTrace();
		}

		int res = result.getContents();
		SimpleTransaction transaction = new SimpleTransaction();

		UnaryOperation<Float, Float> unOp = new UnaryOperation<Float, Float>(
				new MinusOperation());
		unOp.setOperand(new Operand<Float>((float) res));
		transaction.addOperation(unOp);
		transaction.commit();
		System.out.println(unOp.getResult());
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new Calculator();
	}

}
