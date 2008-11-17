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

import java.nio.charset.Charset;
import java.util.List;
import java.util.Set;

import net.sf.graphiti.io.asn1.ast.BinaryNumber;
import net.sf.graphiti.io.asn1.ast.BitString;
import net.sf.graphiti.io.asn1.ast.Choice;
import net.sf.graphiti.io.asn1.ast.Constraint;
import net.sf.graphiti.io.asn1.ast.ConstraintList;
import net.sf.graphiti.io.asn1.ast.IntegerType;
import net.sf.graphiti.io.asn1.ast.Production;
import net.sf.graphiti.io.asn1.ast.Sequence;
import net.sf.graphiti.io.asn1.ast.SequenceOf;
import net.sf.graphiti.io.asn1.ast.Token;
import net.sf.graphiti.io.asn1.ast.Type;
import net.sf.graphiti.io.asn1.ast.TypeReference;
import net.sf.graphiti.io.asn1.ast.Constraint.ConstraintType;
import net.sf.graphiti.io.asn1.ast.Token.TokenType;
import net.sf.graphiti.io.asn1.builtin.PrintableString;
import net.sf.graphiti.io.asn1.builtin.UTF8String;

/**
 * This class implements the {@link LL1Visitor} interface to compute the FIRST
 * set for all grammar symbols.
 * 
 * @author Matthieu Wipliez
 * 
 */
public class FirstSetVisitor extends NopVisitor {

	/**
	 * Creates a new FIRST set computer visitor on the given production list.
	 * 
	 * @param productions
	 *            A {@link List}&lt;{@link Production}&gt;
	 */
	public FirstSetVisitor(List<Production> productions) {
		for (Production production : productions) {
			production.getType().accept(this);
		}
	}

	@Override
	public void visit(BitString bitString) {
		Constraint value = bitString.getValue();
		Object obj = value.getValue();
		if (obj instanceof BinaryNumber) {
			BinaryNumber nb = (BinaryNumber) obj;
			Token token = new Token(TokenType.Binary);
			token.setValue(nb);
			bitString.getFirst().add(token);
		}
	}

	@Override
	public void visit(Choice choice) {
		// X -> Y11 Y12 ... Y1k | Y21 Y22 ... Y2k | ... | Yn1 Yn2 ... Ynk
		super.visit(choice);

		Set<Token> firstX = choice.getFirst();
		for (Type type : choice.getAlternatives()) {
			firstX.addAll(type.getFirst());
		}
	}

	@Override
	public void visit(IntegerType type) {
		Token token = new Token(TokenType.Integer);
		token.setValue(type);
		type.getFirst().add(token);
	}

	@Override
	public void visit(PrintableString string) {
		Set<Token> first = string.getFirst();

		ConstraintList constraints = string.getConstraintList();
		for (Constraint constraint : constraints) {
			if (constraint.getConstraintType() == ConstraintType.Value) {
				Object value = constraint.getValue();
				if (value instanceof String) {
					Token token = new Token(TokenType.Binary);
					String str = (String) value;
					byte[] bytes = str.getBytes(Charset.forName("US-ASCII"));
					BinaryNumber nb = new BinaryNumber(bytes);
					token.setValue(nb);
					first.add(token);
				}
			}
		}
	}

	@Override
	public void visit(Sequence sequence) {
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
	public void visit(SequenceOf sequenceOf) {
		super.visit(sequenceOf);

		sequenceOf.getFirst().addAll(sequenceOf.getType().getFirst());
	}

	@Override
	public void visit(TypeReference typeRef) {
		Type type = typeRef.getReference();
		Set<Token> first = type.getFirst();
		if (first.isEmpty()) {
			type.accept(this);
		}

		typeRef.getFirst().addAll(first);
	}

	@Override
	public void visit(UTF8String string) {
	}

}
