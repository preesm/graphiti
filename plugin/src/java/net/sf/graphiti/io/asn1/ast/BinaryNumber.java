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
public class BinaryNumber implements Comparable<BinaryNumber> {

	/**
	 * Serial ID.
	 */
	private static final long serialVersionUID = 1L;

	private byte[] bits;

	private int nbits;

	/**
	 * Creates an empty number.
	 */
	public BinaryNumber() {
		bits = new byte[0];
	}

	/**
	 * Creates a number from the given byte array.
	 * 
	 * @param bytes
	 *            A byte array.
	 */
	public BinaryNumber(byte[] bytes) {
		bits = bytes;
		nbits = bytes.length * 8;
	}

	/**
	 * Creates a number that is represented with the specified number of bits.
	 * 
	 * @param nbits
	 *            The number of bits of this number.
	 */
	public BinaryNumber(int nbits) {
		this.nbits = nbits;
	}

	@Override
	public int compareTo(BinaryNumber o) {
		return toString().compareTo(o.toString());
	}

	/**
	 * Returns the bytes of this binary number.
	 * 
	 * @return A byte array, whose length is determined from the number of bits
	 *         this binary number was created with.
	 */
	public byte[] getBytes() {
		int nbytes = nbits / 8;

		if (nbytes < bits.length) {
			// strip leading zeros
			byte[] bytes = new byte[nbytes];
			for (int i = bits.length - nbytes, j = 0; i < bits.length
					&& j < nbytes; i++, j++) {
				bytes[j] = bits[i];
			}
			return bytes;
		} else if (nbytes > bits.length) {
			// add leading zeros
			byte[] bytes = new byte[nbytes];
			int i = 0;
			while (i < nbytes - bits.length) {
				bytes[i] = 0;
				i++;
			}

			for (int j = 0; i < nbytes; i++, j++) {
				bytes[i] = bits[j];
			}
			return bytes;
		} else {
			return bits;
		}
	}

	/**
	 * Returns the value of this number as an integer. If this number value does
	 * not fit in an integer, only the lowest 32-bits are returned (as per
	 * {@link BigInteger#intValue()}).
	 * 
	 * @return The value of this number as an integer.
	 */
	public int intValue() {
		BigInteger number = new BigInteger(bits);
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
			nbits = number.bitLength();
		}

		bits = number.toByteArray();
	}

	@Override
	public String toString() {
		return new BigInteger(bits).toString(10);
	}

}
