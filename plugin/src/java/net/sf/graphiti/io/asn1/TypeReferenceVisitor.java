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

import net.sf.graphiti.io.asn1.ast.Production;
import net.sf.graphiti.io.asn1.ast.Type;
import net.sf.graphiti.io.asn1.ast.TypeReference;
import net.sf.graphiti.io.asn1.builtin.LongUTF8String;
import net.sf.graphiti.io.asn1.builtin.PrintableString;
import net.sf.graphiti.io.asn1.builtin.UTF8String;

/**
 * This class implements the {@link ASN1Visitor} interface to solve type
 * references.
 * 
 * @author Matthieu Wipliez
 * 
 */
public class TypeReferenceVisitor extends NopVisitor {

	private Map<String, Production> productions;

	/**
	 * Creates a new type reference visitor on the given production list.
	 * 
	 * @param productions
	 *            A {@link List}&lt;{@link Production}&gt;
	 */
	public TypeReferenceVisitor(List<Production> productions) {
		this.productions = new HashMap<String, Production>();
		for (Production production : productions) {
			this.productions.put(production.getName(), production);
		}

		for (Production production : productions) {
			production.getType().accept(this);
		}
	}

	@Override
	public void visit(TypeReference typeRef) {
		String ref = typeRef.getReferenceName();
		Type reference;
		if (ref.equals("LongUTF8String")) {
			LongUTF8String utf8 = new LongUTF8String();
			utf8.setConstraintList(typeRef.getConstraintList());
			reference = utf8;
		} else if (ref.equals("PrintableString")) {
			PrintableString str = new PrintableString();
			str.setConstraintList(typeRef.getConstraintList());
			reference = str;
		} else if (ref.equals("UTF8String")) {
			UTF8String utf8 = new UTF8String();
			utf8.setConstraintList(typeRef.getConstraintList());
			reference = utf8;
		} else {
			Production production = productions.get(ref);
			if (production == null) {
				throw new RuntimeException("The production \"" + ref
						+ "\" does not exist!");
			} else {
				reference = production.getType();
			}
		}

		typeRef.setReference(reference);
	}

}
