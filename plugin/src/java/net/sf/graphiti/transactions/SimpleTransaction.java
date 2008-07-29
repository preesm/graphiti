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
 * This class is an {@link ITransaction} that contains {@link IOperation}. It
 * extends {@link AbstractTransaction} with the possibility of adding
 * operations.
 * 
 * @author Matthieu Wipliez
 * 
 */
public class SimpleTransaction extends AbstractTransaction {

	private List<IOperation<?>> operations;

	/**
	 * Creates an orphan simple transaction.
	 */
	public SimpleTransaction() {
		super();
		operations = new ArrayList<IOperation<?>>();
	}

	/**
	 * Creates a simple transaction, and adds it to the given
	 * {@link NestedTransaction}.
	 * 
	 * @param parent
	 *            A {@link NestedTransaction}.
	 */
	public SimpleTransaction(NestedTransaction parent) {
		super(parent);
		operations = new ArrayList<IOperation<?>>();
	}

	/**
	 * Adds the given operation to this transaction's operation list.
	 * 
	 * @param operation
	 *            A {@link IOperation}.
	 */
	public void addOperation(IOperation<?> operation) {
		operations.add(operation);
	}

	@Override
	public void commit() {
		super.beginCommit();
		for (IOperation<?> operation : operations) {
			operation.execute();
		}
		super.commit();
	}

	@Override
	public void rollback() {
		super.rollback();
		operations.clear();
	}

	public String toString() {
		String res = "";
		for (IOperation<?> operation : operations) {
			res += operation.toString() + "\n";
		}

		return res;
	}
}
