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

import net.sf.graphiti.io.csd.CSDParseException;

/**
 * This class represents the ASN.1 SEQUENCE OF production. In opposition to
 * SEQUENCE, it specifies a repetition of elements of a certain type. The number
 * of repetitions may be specified.
 * 
 * @author Matthieu Wipliez
 * 
 */
public class SequenceOf extends Type {

	private String size;

	private Type type;

	/**
	 * Creates a new empty {@link SequenceOf}.
	 */
	public SequenceOf(String name, String size) {
		super(name);
		this.size = size;
	}

	@Override
	public void accept(CSDVisitor visitor) throws CSDParseException {
		visitor.visit(this);
	}

	public String getSize() {
		return size;
	}

	/**
	 * Returns the type of this "sequence of".
	 * 
	 * @return The type of this "sequence of".
	 */
	public Type getType() {
		return type;
	}

	/**
	 * Sets this {@link SequenceOf}'s type.
	 * 
	 * @param type
	 *            The type represented as a {@link Type}.
	 */
	public void setType(Type type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return super.toString() + ": " + size + " * " + type;
	}

}