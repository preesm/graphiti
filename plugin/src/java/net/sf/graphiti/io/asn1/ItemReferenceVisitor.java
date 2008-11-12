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
package net.sf.graphiti.io.asn1;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.graphiti.io.asn1.ast.Constraint;
import net.sf.graphiti.io.asn1.ast.ItemReference;
import net.sf.graphiti.io.asn1.ast.Production;
import net.sf.graphiti.io.asn1.ast.Type;
import net.sf.graphiti.io.asn1.ast.TypeReference;
import net.sf.graphiti.io.asn1.builtin.PrintableString;
import net.sf.graphiti.io.asn1.builtin.UTF8String;

/**
 * This class implements the {@link ASN1Visitor} interface to solve type
 * references.
 * 
 * @author Matthieu Wipliez
 * 
 */
public class ItemReferenceVisitor implements ASN1Visitor {

	private Map<String, Type> namedTypes;

	public ItemReferenceVisitor() {
		namedTypes = new HashMap<String, Type>();
	}

	@Override
	public void visit(Constraint constraint) {
		switch (constraint.getConstraintType()) {
		case Size:
			Object size = constraint.getSize();
			if (size instanceof ItemReference) {
				visit((ItemReference) size);
			}
			break;
		case Value:
			break;
		}
	}

	public void visit(ItemReference itemRef) {
		String ref = itemRef.getReferenceName();
		Type reference;
		if (ref.equals("UTF8String")) {
			reference = new UTF8String();
		} else if (ref.equals("PrintableString")) {
			reference = new PrintableString();
		} else {
			reference = namedTypes.get(ref);
			if (reference == null) {
				throw new RuntimeException("The type \"" + ref
						+ "\" does not exist!");
			}
		}

		itemRef.setReference(reference);
		itemRef.setReferenceName(null);
	}

	@Override
	public void visit(List<Production> productions) {
		for (Production production : productions) {
			visit(production);
		}
	}

	public void visit(Production production) {
		production.accept(this);
	}

	@Override
	public void visit(Type type) {
		String name = type.getName();
		if (!name.isEmpty()) {
			namedTypes.put(name, type);
		}
		type.accept(this);
	}

	@Override
	public void visit(TypeReference typeRef) {
	}

}
