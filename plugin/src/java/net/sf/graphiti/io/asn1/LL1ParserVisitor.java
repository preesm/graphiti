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

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayDeque;

import net.sf.graphiti.io.asn1.ast.BinaryNumber;
import net.sf.graphiti.io.asn1.ast.BitString;
import net.sf.graphiti.io.asn1.ast.Choice;
import net.sf.graphiti.io.asn1.ast.Constraint;
import net.sf.graphiti.io.asn1.ast.IntegerType;
import net.sf.graphiti.io.asn1.ast.Production;
import net.sf.graphiti.io.asn1.ast.Sequence;
import net.sf.graphiti.io.asn1.ast.SequenceOf;
import net.sf.graphiti.io.asn1.ast.Type;
import net.sf.graphiti.io.asn1.ast.TypeReference;
import net.sf.graphiti.io.asn1.builtin.PrintableString;
import net.sf.graphiti.io.asn1.builtin.UTF8String;

/**
 * This class extends the {@link NopVisitor} class to parse an input stream.
 * 
 * @author Matthieu Wipliez
 * 
 */
public class LL1ParserVisitor extends NopVisitor {

	private static void debug(String message) {
		System.out.println(message);
	}

	private InputStream in;

	private ArrayDeque<ParseNode> lastNode;

	private String productionName;

	private ParseNode tree;

	/**
	 * Creates a new LL(1) parser visitor on the given production.
	 * 
	 * @param in
	 *            The {@link InputStream} containing bytes.
	 * @param production
	 *            The {@link Production} to start parsing with.
	 */
	public LL1ParserVisitor(InputStream in, Production production) {
		this.in = in;
		lastNode = new ArrayDeque<ParseNode>();
		productionName = production.getName();
		tree = new ParseNode("root");
		lastNode.push(tree);
		debug("Parsing " + productionName);
		production.getType().accept(this);
	}

	private void beginSequence(Type type) {
		ParseNode node = new ParseNode(productionName);
		productionName = type.getName();
		lastNode.peek().addChild(node);
		lastNode.push(node);
	}

	private void beginTypeReference(TypeReference typeRef) {
		ParseNode node = new ParseNode(typeRef.getReferenceName());
		productionName = typeRef.getName();
		lastNode.peek().addChild(node);
		lastNode.push(node);
	}

	private boolean byteEquals(byte[] actual, byte[] expected) {
		for (int i = 0; i < actual.length; i++) {
			if (actual[i] != expected[i]) {
				return false;
			}
		}

		return true;
	}

	private void end() {
		lastNode.pop();
	}

	@Override
	public void visit(BitString bitString) {
		Constraint value = bitString.getValue();
		Object obj = value.getValue();
		if (obj instanceof BinaryNumber) {
			BinaryNumber nb = (BinaryNumber) obj;
			byte[] expected = nb.getBytes();
			try {
				byte[] actual = new byte[expected.length];
				in.read(actual);
				if (byteEquals(actual, expected)) {
					ParseNode node = new ParseNode(bitString);
					node.setValue(nb);
					lastNode.peek().addChild(node);
				} else {
					throw new RuntimeException("Parse error");
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void visit(Choice choice) {
		throw new RuntimeException("TODO");
	}

	@Override
	public void visit(IntegerType type) {
		throw new RuntimeException("TODO");
	}

	@Override
	public void visit(PrintableString string) {
		throw new RuntimeException("TODO");
	}

	@Override
	public void visit(Sequence sequence) {
		beginSequence(sequence);

		for (Type type : sequence.getElements()) {
			type.accept(this);
		}

		end();
	}

	@Override
	public void visit(SequenceOf sequenceOf) {
		beginSequence(sequenceOf);

		Constraint size = sequenceOf.getSize();
		if (size == null) {
			// unlimited sequence
			Type type = sequenceOf.getType();
			try {
				while (true) {
					type.accept(this);
				}
			} catch (Throwable t) {
				t.printStackTrace();
			}
		} else {
			throw new RuntimeException("TODO");
		}

		end();
	}

	@Override
	public void visit(TypeReference typeRef) {
		beginTypeReference(typeRef);

		Type type = typeRef.getReference();
		type.accept(this);

		end();
	}

	@Override
	public void visit(UTF8String string) {
		throw new RuntimeException("TODO");
	}

}
