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
package net.sf.graphiti.io.csd.ast;

import java.math.BigInteger;

import net.sf.graphiti.io.csd.CSDParseException;

public class CSDNumber extends Type {

	private boolean hasValue;

	private int length;

	private BigInteger value;

	public CSDNumber(String name, int length, String value) {
		super(name);
		this.length = length;
		hasValue = !value.isEmpty();
		if (hasValue) {
			setValue(value);
		}
	}

	@Override
	public void accept(CSDVisitor visitor) throws CSDParseException {
		visitor.visit(this);
	}

	public int getLength() {
		return length;
	}

	public BigInteger getValue() {
		return value;
	}

	public boolean hasValue() {
		return hasValue;
	}

	public boolean match(byte[] bytes) {
		return new BigInteger(1, bytes).equals(value);
	}

	private void setValue(String strValue) {
		if (strValue.charAt(0) == '\'' && strValue.charAt(2) == '\'') {
			// char
			String intValue = Integer.toString((int) strValue.charAt(1));
			value = new BigInteger(intValue, 10);
		} else if (strValue.equals("0")) {
			// decimal 0
			value = new BigInteger("0");
		} else if (strValue.startsWith("0x") || strValue.startsWith("0X")) {
			// hexadecimal
			value = new BigInteger(strValue.substring(2), 16);
		} else if (strValue.startsWith("0")) {
			// octal
			value = new BigInteger(strValue.substring(1), 8);
		} else {
			// decimal
			value = new BigInteger(strValue, 10);
		}
	}

	public String stringOfValue(BigInteger value) {
		return value.toString();
	}

	public String toString() {
		return super.toString() + ": byte[" + length + "]"
				+ (hasValue ? " = " + stringOfValue(value) : "");
	}

}
