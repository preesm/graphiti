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
package net.sf.graphiti.io.csd;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.percederberg.grammatica.parser.Production;
import net.sf.graphiti.io.csd.ast.CSDNumber;
import net.sf.graphiti.io.csd.ast.CSDVisitor;
import net.sf.graphiti.io.csd.ast.Choice;
import net.sf.graphiti.io.csd.ast.Error;
import net.sf.graphiti.io.csd.ast.ForEach;
import net.sf.graphiti.io.csd.ast.LongUTF8String;
import net.sf.graphiti.io.csd.ast.Reference;
import net.sf.graphiti.io.csd.ast.Sequence;
import net.sf.graphiti.io.csd.ast.SequenceOf;
import net.sf.graphiti.io.csd.ast.Type;
import net.sf.graphiti.io.csd.ast.UTF8String;
import net.sf.graphiti.io.csd.ast.Variable;

/**
 * This class implements the {@link CSDVisitor} interface to solve type
 * references.
 * 
 * @author Matthieu Wipliez
 * 
 */
public class TypeReferenceVisitor implements CSDVisitor {

	private Map<String, Type> types;

	/**
	 * Creates a new type reference visitor on the given production list.
	 * 
	 * @param productions
	 *            A {@link List}&lt;{@link Production}&gt;
	 */
	public TypeReferenceVisitor(List<Type> types) {
		this.types = new HashMap<String, Type>();
		for (Type type : types) {
			this.types.put(type.getName(), type);
		}

		try {
			for (Type type : types) {
				type.accept(this);
			}
		} catch (CSDParseException e) {
			// never happens in this visitor
		}
	}

	@Override
	public void visit(Choice choice) throws CSDParseException {
		for (Type type : choice.getAlternatives()) {
			type.accept(this);
		}
	}

	@Override
	public void visit(CSDNumber csdNumber) {
	}

	@Override
	public void visit(Error error) {
	}

	@Override
	public void visit(ForEach forEach) throws CSDParseException {
		forEach.getType().accept(this);
	}

	@Override
	public void visit(LongUTF8String utf8String) {
	}

	@Override
	public void visit(Reference typeRef) {
		String ref = typeRef.getReferenceName();
		Type reference;
		if (ref.equals("LongUTF8String")) {
			reference = new LongUTF8String(typeRef.getName());
		} else if (ref.equals("UTF8String")) {
			reference = new UTF8String(typeRef.getName());
		} else {
			Type type = types.get(ref);
			if (type == null) {
				throw new RuntimeException("The type \"" + ref
						+ "\" does not exist!");
			} else {
				reference = type;
			}
		}

		typeRef.setReference(reference);
	}

	@Override
	public void visit(Sequence sequence) throws CSDParseException {
		for (Type type : sequence.getElements()) {
			type.accept(this);
		}
	}

	@Override
	public void visit(SequenceOf sequenceOf) throws CSDParseException {
		sequenceOf.getType().accept(this);
	}

	@Override
	public void visit(UTF8String utf8String) {
	}

	@Override
	public void visit(Variable variable) {
	}

}
