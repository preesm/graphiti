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

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayDeque;

import net.sf.graphiti.io.asn1.ast.BinaryNumber;
import net.sf.graphiti.io.asn1.ast.BitString;
import net.sf.graphiti.io.asn1.ast.Choice;
import net.sf.graphiti.io.asn1.ast.Constraint;
import net.sf.graphiti.io.asn1.ast.ConstraintList;
import net.sf.graphiti.io.asn1.ast.IntegerType;
import net.sf.graphiti.io.asn1.ast.ItemReference;
import net.sf.graphiti.io.asn1.ast.Production;
import net.sf.graphiti.io.asn1.ast.Sequence;
import net.sf.graphiti.io.asn1.ast.SequenceOf;
import net.sf.graphiti.io.asn1.ast.Token;
import net.sf.graphiti.io.asn1.ast.Type;
import net.sf.graphiti.io.asn1.ast.TypeReference;
import net.sf.graphiti.io.asn1.ast.Token.TokenType;
import net.sf.graphiti.io.asn1.builtin.LongUTF8String;
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
		tree = new ParseNode(production.getName());
		lastNode.push(tree);

		debug("Parsing " + production.getName());
		production.getType().accept(this);
	}

	private void begin(Type type) {
		ParseNode node = new ParseNode(type);
		debug("Parsing " + node.getName());
		lastNode.peek().addChild(node);
		lastNode.push(node);
	}

	private void beginTypeReference(TypeReference typeRef) {
		ParseNode node = new ParseNode(typeRef.getReferenceName());
		debug("Parsing " + node.getName());
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

	private boolean isValid(Type type) {
		// 16 bytes = sizeof(GUID). Should be large enough.
		in.mark(16);

		for (Token token : type.getFirst()) {
			if (token == Token.epsilon) {
				return true;
			} else {
				if (token.getType() == TokenType.Binary) {
					BinaryNumber nb = (BinaryNumber) token.getValue();
					byte[] expected = nb.getBytes();
					try {
						byte[] actual = new byte[expected.length];
						in.read(actual);
						in.reset();
						if (byteEquals(actual, expected)) {
							return true;
						}
					} catch (IOException e) {
						e.printStackTrace();
					}
				} else {
					throw new RuntimeException("TODO");
				}
			}
		}

		return false;
	}

	@Override
	public void visit(BitString bitString) {
		begin(bitString);

		Constraint value = bitString.getValue();
		Object obj = value.getValue();
		if (obj instanceof BinaryNumber) {
			BinaryNumber nb = (BinaryNumber) obj;
			byte[] expected = nb.getBytes();
			try {
				byte[] actual = new byte[expected.length];
				in.read(actual);
				if (byteEquals(actual, expected)) {
					lastNode.peek().setValue(nb);
				} else {
					throw new RuntimeException("Parse error");
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		end();
	}

	@Override
	public void visit(Choice choice) {
		begin(choice);

		for (Type type : choice.getAlternatives()) {
			if (isValid(type)) {
				type.accept(this);
				end();
			}
		}

		throw new RuntimeException("Parse error: no alternatives left");
	}

	@Override
	public void visit(IntegerType type) {
		begin(type);

		ConstraintList constraints = type.getConstraintList();
		Constraint size = constraints.getFirstSizeConstraint();
		if (size == null) {
			throw new RuntimeException("TODO");
		} else {
			// integer whose size is given
			BinaryNumber nb = (BinaryNumber) size.getSize();
			int nbytes = nb.intValue() / 8;
			byte[] integer = new byte[nbytes];
			try {
				in.read(integer);
			} catch (IOException e) {
				e.printStackTrace();
			}

			String str = new BinaryNumber(integer).toString();
			Object value;
			switch (nbytes) {
			case 1:
				value = new Byte(str);
				break;
			case 2:
				value = new Short(str);
				break;
			case 4:
				value = new Integer(str);
				break;
			case 8:
				value = new Long(str);
				break;
			default:
				throw new RuntimeException(
						"Integer size must be 1, 2, 4 or 8 bytes.");
			}

			lastNode.peek().setValue(value);
		}

		end();
	}

	@Override
	public void visit(LongUTF8String string) {
		throw new RuntimeException("TODO");
	}

	@Override
	public void visit(PrintableString string) {
		throw new RuntimeException("TODO");
	}

	@Override
	public void visit(Sequence sequence) {
		begin(sequence);

		for (Type type : sequence.getElements()) {
			type.accept(this);
		}

		end();
	}

	@Override
	public void visit(SequenceOf sequenceOf) {
		begin(sequenceOf);

		Type type = sequenceOf.getType();
		Constraint size = sequenceOf.getSize();
		if (size == null) {
			// unlimited sequence
			try {
				while (true) {
					type.accept(this);
				}
			} catch (Throwable t) {
				t.printStackTrace();
			}
		} else {
			// limited sequence
			Object seqSize = size.getSize();
			if (seqSize instanceof ItemReference) {
				ItemReference ref = (ItemReference) seqSize;
				ParseNode node = lastNode.peek();
				Object value = findValue(ref.getReferenceName(), node);
				for (int i = 0; i < Integer.parseInt(value.toString()); i++) {
					type.accept(this);
				}
			} else {
				throw new RuntimeException("TODO");
			}
		}

		end();
	}

	/**
	 * Returns the value of the first parse node whose name equals
	 * <code>nodeName</code>.
	 * 
	 * @param nodeName
	 *            The name of the node we're looking for.
	 * @param node
	 *            The root node.
	 * @return The node's value or <code>null</code>.
	 */
	private Object findValue(String nodeName, ParseNode node) {
		if (node.getName().equals(nodeName)) {
			return node.getValue();
		} else {
			for (ParseNode child : node.getChildren()) {
				Object value = findValue(nodeName, child);
				if (value != null) {
					return value;
				}
			}

			ParseNode parent = node.getParent();
			for (ParseNode child : parent.getChildren()) {
				if (parent != node) {
					Object value = findValue(nodeName, child);
					if (value != null) {
						return value;
					}
				}
			}
		}

		return null;
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
		DataInputStream dis = new DataInputStream(in);
		try {
			String str = dis.readUTF();
			lastNode.peek().setValue(str);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
