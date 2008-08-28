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
 * This class provides a basic implementation of an {@link ITransaction}. It
 * contains one transaction's parent, and makes sure a transaction is only
 * committed once.
 * 
 * @author Matthieu Wipliez
 * 
 */
public abstract class AbstractTransaction implements ITransaction {

	private boolean isCommitted;

	protected NestedTransaction parent;

	/**
	 * Creates a new abstract transaction with no parent.
	 */
	public AbstractTransaction() {
		this(null);
	}

	/**
	 * Creates a new abstract transaction with the given
	 * {@link NestedTransaction} as a parent.
	 * 
	 * @param parent
	 *            The parent nested transaction.
	 */
	public AbstractTransaction(NestedTransaction parent) {
		isCommitted = false;
		this.parent = parent;
		if (parent != null) {
			parent.addTransaction(this);
		}
	}

	/**
	 * Checks if this transaction has not already been committed.
	 */
	public void beginCommit() {
		if (isCommitted) {
			throw new AlreadyCommittedException();
		}
	}

	@Override
	public void commit() {
		isCommitted = true;
	}

	@Override
	public void rollback() {
		if (isCommitted) {
			throw new AlreadyCommittedException();
		}
	}
}
