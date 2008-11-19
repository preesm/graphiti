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
import java.nio.charset.Charset;
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

	private InputStream in;

	private String indent;

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

		indent = "";
		debug("Parsing " + production.getName());
		production.getType().accept(this);
	}

	/**
	 * Should be called at the beginning of any visit function other than
	 * {@link #visit(TypeReference)}. Creates a new {@link ParseNode} with the
	 * given type.
	 * 
	 * @param type
	 *            A {@link Type} other than {@link TypeReference}.
	 */
	private void begin(Type type) {
		beginNode(new ParseNode(type));
	}

	/**
	 * Adds the given parse node to {@link #lastNode}'s children, pushes it to
	 * {@link #lastNode}, and prints debugging information.
	 * 
	 * @param node
	 *            A {@link ParseNode}.
	 */
	private void beginNode(ParseNode node) {
		debug("Parsing " + node.getName());
		indent += "  ";
		lastNode.peek().addChild(node);
		lastNode.push(node);
	}

	/**
	 * Should be called at the beginning of {@link #visit(TypeReference)}.
	 * Creates a new {@link ParseNode} whose name is the name of the type
	 * referenced by <code>typeRef</code>.
	 * 
	 * @param typeRef
	 *            A {@link TypeReference}.
	 */
	private void beginTypeReference(TypeReference typeRef) {
		beginNode(new ParseNode(typeRef.getReferenceName()));
	}

	/**
	 * Returns true if the given arrays are equal.
	 * 
	 * @param actual
	 *            A byte array read from {@link #in}.
	 * @param expected
	 *            A byte array expected.
	 * @return True if the given arrays are equal, false otherwise.
	 */
	private boolean byteEquals(byte[] actual, byte[] expected) {
		for (int i = 0; i < actual.length; i++) {
			if (actual[i] != expected[i]) {
				return false;
			}
		}

		return true;
	}

	/**
	 * Prints the given message.
	 * 
	 * @param message
	 *            A {@link String}.
	 */
	private void debug(String message) {
		System.out.println(indent + message);
	}

	/**
	 * Pops the last parsed node pushed onto {@link #lastNode}, and prints
	 * debugging information.
	 */
	private void end() {
		ParseNode node = lastNode.pop();
		indent = indent.substring(2);
		debug("Finished parsing " + node.getName());
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

	/**
	 * Returns true if the given type is a valid alternative. This method is a
	 * helper for {@link #visit(Choice)}.
	 * 
	 * @param type
	 *            A {@link Type}.
	 * @return True if the given type is a valid alternative, false otherwise.
	 */
	private boolean isValid(Type type) {
		// 16 bytes = sizeof(GUID). Should be large enough.
		in.mark(16);

		for (Token token : type.getFirst()) {
			if (token == Token.epsilon) {
				return true;
			} else {
				if (token.getType() == TokenType.Binary) {
					BinaryNumber nb = (BinaryNumber) token.getValue();
					try {
						boolean res = readBytesMatch(nb);
						in.reset();
						if (res) {
							return true;
						}
					} catch (IOException e) {
						throw new RuntimeException(e);
					}
				} else {
					throw new RuntimeException("TODO");
				}
			}
		}

		return false;
	}

	/**
	 * Reads bytes and check if they match the given binary number's bytes.
	 * Beware that it is the caller's responsibility to call
	 * {@link InputStream#mark(int)}, not this function's.
	 * 
	 * @param nb
	 *            A binary number.
	 * @return True if the bytes read match, false otherwise.
	 * @throws IOException
	 *             If {@link InputStream#read()} fails.
	 */
	private boolean readBytesMatch(BinaryNumber nb) throws IOException {
		byte[] expected = nb.getBytes();
		byte[] actual = new byte[expected.length];
		in.read(actual);
		return byteEquals(actual, expected);
	}

	@Override
	public void visit(BitString bitString) {
		begin(bitString);

		Constraint value = bitString.getValue();
		Object obj = value.getValue();
		if (obj instanceof BinaryNumber) {
			BinaryNumber nb = (BinaryNumber) obj;
			try {
				if (readBytesMatch(nb)) {
					lastNode.peek().setValue(nb);
					debug(nb.toHexString());
				} else {
					throw new RuntimeException("Parse error");
				}
			} catch (IOException e) {
				throw new RuntimeException(e);
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
				return;
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
				throw new RuntimeException(e);
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
		ConstraintList constraints = string.getConstraintList();
		for (Constraint constraint : constraints) {
			Object value = constraint.getValue();
			if (value instanceof String) {
				String expected = (String) value;
				in.mark(expected.length());
				byte[] chars = new byte[expected.length()];
				try {
					in.read(chars);
					String actual = new String(chars, Charset
							.forName("US-ASCII"));
					if (actual.equals(expected)) {
						lastNode.peek().setValue(expected);
						return;
					} else {
						in.reset();
					}
				} catch (IOException e) {
					throw new RuntimeException(e);
				}
			}
		}

		throw new RuntimeException("Unexpected string");
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
			debug("UTF8String: " + str);
			lastNode.peek().setValue(str);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

}
