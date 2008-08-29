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
 * This class provides an operand to an operation. An operand may be created on
 * an arbitrary type, or on a {@link Result}.
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
	 * Returns the contents of this operand. If this operand was created on a
	 * {@link Result}, returns the contents of the result.
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
