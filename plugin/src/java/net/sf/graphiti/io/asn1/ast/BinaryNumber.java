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
package net.sf.graphiti.io.asn1.ast;

import java.math.BigInteger;
import java.util.BitSet;

/**
 * This class provides methods to store a number in a non-specific base (binary,
 * octal, decimal, hexadecimal, whatnot...) using a {@link BitSet}.
 * 
 * @author Matthieu Wipliez
 * 
 */
public class BinaryNumber {

	/**
	 * Serial ID.
	 */
	private static final long serialVersionUID = 1L;

	private BitSet bits;

	private int nbits;

	/**
	 * Creates an empty number.
	 */
	public BinaryNumber() {
		bits = new BitSet();
	}

	/**
	 * Creates a number that is represented with the specified number of bits.
	 * 
	 * @param nbits
	 *            The number of bits of this number.
	 */
	public BinaryNumber(int nbits) {
		this.nbits = nbits;
		bits = new BitSet(nbits);
	}

	/**
	 * Returns the value of this number as an integer. If this number value does
	 * not fit in an integer, only the lowest 32-bits are returned (as per
	 * {@link BigInteger#intValue()}).
	 * 
	 * @return The value of this number as an integer.
	 */
	public int intValue() {
		BigInteger number = new BigInteger(toString(), 2);
		return number.intValue();
	}

	/**
	 * Sets the value of this number.
	 * 
	 * @param value
	 *            The value as a string.
	 * @param radix
	 *            The radix.
	 */
	public void setValue(String value, int radix) {
		BigInteger number = new BigInteger(value, radix);
		if (nbits == 0) {
			// initialize nbits if not previously set.
			nbits = number.bitCount();
		}

		// set the bit set's bits.
		for (int i = 0; i < nbits; i++) {
			boolean n = number.testBit(i);
			bits.set(i, n);
		}
	}

	@Override
	public String toString() {
		String res = "";
		for (int i = nbits - 1; i >= 0; i--) {
			if (bits.get(i)) {
				res += "1";
			} else {
				res += "0";
			}
		}

		return res;
	}

}
