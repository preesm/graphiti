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

import net.sf.graphiti.io.asn1.ASN1Visitable;
import net.sf.graphiti.io.asn1.ASN1Visitor;

/**
 * This class represents a constraint.
 * 
 * @author Matthieu Wipliez
 *
 */
public class Constraint implements ASN1Visitable {

	public enum ConstraintType {
		/**
		 * A size constraint type.
		 */
		Size,

		/**
		 * A value constraint type.
		 */
		Value;
	}

	private Object size;

	private ConstraintType type;

	private Object value;

	/**
	 * Creates a new constraint with the given type.
	 * 
	 * @param type
	 *            The constraint type.
	 */
	public Constraint(ConstraintType type) {
		this.type = type;
	}

	@Override
	public void accept(ASN1Visitor visitor) {
		visitor.visit(this);
	}

	/**
	 * Returns this constraint's type.
	 * 
	 * @return This constraint's type.
	 */
	public ConstraintType getConstraintType() {
		return type;
	}

	/**
	 * Returns this constraint's size. Valid only if this constraint has type
	 * {@link ConstraintType#Size}.
	 * 
	 * @return This constraint's size.
	 */
	public Object getSize() {
		return size;
	}

	/**
	 * Returns this constraint's value. Valid only if this constraint has type
	 * {@link ConstraintType#Value}.
	 * 
	 * @return This constraint's value.
	 */
	public Object getValue() {
		return value;
	}

	/**
	 * Sets the size information for this constraint. Valid only if this
	 * constraint has type {@link ConstraintType#Size}.
	 * 
	 * @param size
	 *            The size as a binary number.
	 */
	public void setSize(BinaryNumber size) {
		this.size = size;
	}

	/**
	 * Sets the value information for this constraint as a binary number. Valid
	 * only if this constraint has type {@link ConstraintType#Value}.
	 * 
	 * @param value
	 *            A binary number.
	 */
	public void setValue(BinaryNumber value) {
		this.value = value;
	}

	/**
	 * Sets the value information for this constraint as an array of binary
	 * numbers. Valid only if this constraint has type
	 * {@link ConstraintType#Value}.
	 * 
	 * @param bounds
	 *            The bounds.
	 */
	public void setValue(BinaryNumber[] bounds) {
		this.value = bounds;
	}

	/**
	 * Sets the value information for this constraint as an item reference.
	 * Valid only if this constraint has type {@link ConstraintType#Value}.
	 * 
	 * @param value
	 *            A binary number.
	 */
	public void setValue(ItemReference reference) {
		this.value = reference;
	}

	/**
	 * Sets the value information for this constraint as a string. Valid only if
	 * this constraint has type {@link ConstraintType#Value}.
	 * 
	 * @param string
	 *            A string.
	 */
	public void setValue(String string) {
		this.value = string;
	}

	@Override
	public String toString() {
		String res = "";

		switch (type) {
		case Size:
			res += "size: " + size;
			break;
		case Value:
			res += "value: " + value;
			break;
		}

		return res;
	}

}
