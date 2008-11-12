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

import java.util.ArrayList;

import net.sf.graphiti.io.asn1.ast.Constraint.ConstraintType;

/**
 * This class extends {@link ArrayList} with constraint-specific features.
 * 
 * @author Matthieu Wipliez
 * 
 */
public class ConstraintList extends ArrayList<Constraint> {

	/**
	 * Serial ID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Creates a new empty list of constraints.
	 */
	public ConstraintList() {
		super();
	}

	/**
	 * Returns the first constraint of type {@link ConstraintType#Size}.
	 * 
	 * @return The first constraint of type {@link ConstraintType#Size}.
	 */
	public Constraint getFirstSizeConstraint() {
		for (Constraint constraint : this) {
			if (constraint.getConstraintType() == ConstraintType.Size) {
				return constraint;
			}
		}

		return null;
	}

	/**
	 * Returns the first constraint of type {@link ConstraintType#Value}.
	 * 
	 * @return The first constraint of type {@link ConstraintType#Value}.
	 */
	public Constraint getFirstValueConstraint() {
		for (Constraint constraint : this) {
			if (constraint.getConstraintType() == ConstraintType.Value) {
				return constraint;
			}
		}

		return null;
	}

}
