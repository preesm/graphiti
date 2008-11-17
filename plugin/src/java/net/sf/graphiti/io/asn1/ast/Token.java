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

/**
 * This class represents a token.
 * 
 * @author Matthieu Wipliez
 * 
 */
public class Token implements Comparable<Token> {

	/**
	 * This class enumerates the different token types.
	 * 
	 * @author Matthieu Wipliez
	 * 
	 */
	public enum TokenType {
		/**
		 * A token whose value is represented by a {@link BinaryNumber}.
		 */
		Binary,

		/**
		 * The empty token. Should not be used directly, use
		 * {@link Token#epsilon} instead.
		 */
		Epsilon,

		/**
		 * A token whose value is represented by an {@link IntegerType}.
		 */
		Integer
	}

	public static Token epsilon = new Token(TokenType.Epsilon);

	private TokenType type;

	private Object value;

	/**
	 * Creates a new token with the given token type.
	 * 
	 * @param type
	 *            A {@link TokenType}.
	 */
	public Token(TokenType type) {
		this.type = type;
	}

	@Override
	public int compareTo(Token o) {
		if (type == o.type) {
			if (type == TokenType.Binary) {
				return ((BinaryNumber) value).compareTo((BinaryNumber) o.value);
			} else {
				return 0;
			}
		} else {
			return type.compareTo(o.type);
		}
	}

	public TokenType getType() {
		return type;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	@Override
	public String toString() {
		if (this == epsilon) {
			return "epsilon";
		} else {
			return type.toString() + ": " + value;
		}
	}

}
