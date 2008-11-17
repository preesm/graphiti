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

import net.sf.graphiti.io.asn1.ASN1Visitor;

/**
 * This class provides methods to manipulate a referenceName to a type. The type
 * referenced may be builtin or specified by the user.
 * 
 * @author Matthieu Wipliez
 * 
 */
public class TypeReference extends Type {

	private ConstraintList constraints;

	private Type reference;

	private String referenceName;

	/**
	 * Creates a new type referenceName with the given name.
	 * 
	 * @param name
	 *            The item name.
	 */
	public TypeReference(String name) {
		super(name);
		constraints = new ConstraintList();
	}

	@Override
	public void accept(ASN1Visitor visitor) {
		visitor.visit(this);
	}

	/**
	 * Returns this type reference's constraint list.
	 * 
	 * @return This type reference's constraint list.
	 */
	public ConstraintList getConstraintList() {
		return constraints;
	}

	/**
	 * Returns the type referenced.
	 * 
	 * @return The type referenced.
	 */
	public Type getReference() {
		return reference;
	}

	/**
	 * Returns the type name referenced.
	 * 
	 * @return The type name referenced.
	 */
	public String getReferenceName() {
		return referenceName;
	}

	/**
	 * Sets this {@link TypeReference}'s constraints.
	 * 
	 * @param constraints
	 *            A {@link ConstraintList}.
	 */
	public void setConstraintList(ConstraintList constraints) {
		this.constraints = constraints;
	}

	/**
	 * Sets the type referenced by this {@link TypeReference}.
	 * 
	 * @param reference
	 *            The type as a {@link Production}.
	 */
	public void setReference(Type reference) {
		this.reference = reference;
	}

	/**
	 * Sets the name of the type referenced by this {@link TypeReference}.
	 * 
	 * @param referenceName
	 *            The type name.
	 */
	public void setReferenceName(String referenceName) {
		this.referenceName = referenceName;
	}

	@Override
	public String toString() {
		return super.toString() + ": " + constraints + " -> " + referenceName;
	}

}
