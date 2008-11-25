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

import java.util.List;
import java.util.Set;

import net.sf.graphiti.io.csd.ast.AttachData;
import net.sf.graphiti.io.csd.ast.CSDNumber;
import net.sf.graphiti.io.csd.ast.CSDVisitor;
import net.sf.graphiti.io.csd.ast.Choice;
import net.sf.graphiti.io.csd.ast.Error;
import net.sf.graphiti.io.csd.ast.ForEach;
import net.sf.graphiti.io.csd.ast.LongUTF8String;
import net.sf.graphiti.io.csd.ast.Reference;
import net.sf.graphiti.io.csd.ast.Sequence;
import net.sf.graphiti.io.csd.ast.SequenceOf;
import net.sf.graphiti.io.csd.ast.Token;
import net.sf.graphiti.io.csd.ast.Type;
import net.sf.graphiti.io.csd.ast.UTF8String;
import net.sf.graphiti.io.csd.ast.Variable;

/**
 * This class implements the {@link CSDVisitor} interface to compute the FIRST
 * set for all grammar symbols.
 * 
 * @author Matthieu Wipliez
 * 
 */
public class FirstSetVisitor implements CSDVisitor {

	/**
	 * Creates a new FIRST set computer visitor on the given production list.
	 * 
	 * @param types
	 *            A {@link List}&lt;{@link Type}&gt;
	 */
	public FirstSetVisitor(List<Type> types) {
		for (Type type : types) {
			try {
				type.accept(this);
			} catch (CSDParseException e) {
			}
		}
	}

	@Override
	public void visit(Choice choice) throws CSDParseException {
		// X -> Y11 Y12 ... Y1k | Y21 Y22 ... Y2k | ... | Yn1 Yn2 ... Ynk
		for (Type type : choice.getAlternatives()) {
			type.accept(this);
		}

		Set<Token> firstX = choice.getFirst();
		for (Type type : choice.getAlternatives()) {
			firstX.addAll(type.getFirst());
		}
	}

	@Override
	public void visit(CSDNumber csdNumber) throws CSDParseException {
		if (csdNumber.hasValue()) {
			csdNumber.getFirst().add(new Token(csdNumber));
		}
	}

	@Override
	public void visit(Error error) throws CSDParseException {
	}

	@Override
	public void visit(ForEach forEach) throws CSDParseException {
		Type type = forEach.getType();
		type.accept(this);
		forEach.getFirst().addAll(type.getFirst());
	}

	@Override
	public void visit(LongUTF8String utf8String) throws CSDParseException {
	}

	@Override
	public void visit(Reference reference) throws CSDParseException {
		Type type = reference.getReference();
		Set<Token> first = type.getFirst();
		if (first.isEmpty()) {
			type.accept(this);
		}

		reference.getFirst().addAll(first);
	}

	@Override
	public void visit(Sequence sequence) throws CSDParseException {
		// X -> Y1 Y2 ... Yk
		Set<Token> firstX = sequence.getFirst();
		for (Type yi : sequence.getElements()) {
			yi.accept(this);

			Set<Token> firstYi = yi.getFirst();
			if (firstYi.contains(Token.epsilon)) {
				for (Token nb : firstYi) {
					if (nb != Token.epsilon) {
						firstX.add(nb);
					}
				}
			} else {
				// epsilon was in Y1..Yi-1
				firstX.addAll(firstYi);
				return;
			}
		}

		// if we got here, epsilon was in every Yi
		firstX.add(Token.epsilon);
	}

	@Override
	public void visit(SequenceOf sequenceOf) throws CSDParseException {
		Type type = sequenceOf.getType();
		type.accept(this);
		sequenceOf.getFirst().addAll(type.getFirst());
	}

	@Override
	public void visit(UTF8String utf8String) throws CSDParseException {
	}

	@Override
	public void visit(Variable variable) throws CSDParseException {
	}

	@Override
	public void visit(AttachData data) {
	}

}
