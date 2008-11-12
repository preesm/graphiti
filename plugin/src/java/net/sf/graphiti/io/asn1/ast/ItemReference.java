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
 * This class makes referenceName to an existing named item.
 * 
 * @author Matthieu Wipliez
 * 
 */
public class ItemReference {

	private Type reference;

	private String referenceName;

	/**
	 * Creates a new item referenceName.
	 * 
	 * @param referenceName
	 *            The referenced item's name.
	 */
	public ItemReference(String referenceName) {
		this.referenceName = referenceName;
	}

	/**
	 * Returns this item reference's name.
	 * 
	 * @return This item reference's name.
	 */
	public String getReferenceName() {
		return referenceName;
	}

	/**
	 * Sets this item's reference.
	 * 
	 * @param reference
	 *            A {@link Type}.
	 */
	public void setReference(Type reference) {
		this.reference = reference;
	}

	/**
	 * Sets this item's reference name.
	 * 
	 * @param referenceName
	 *            A {@link String}.
	 */
	public void setReferenceName(String referenceName) {
		this.referenceName = referenceName;
	}

	@Override
	public String toString() {
		return (reference != null ? reference.getName() : referenceName);
	}
}
