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
package net.sf.graphiti.writer.operations;

import net.sf.graphiti.transactions.IOperationSpecification;
import net.sf.graphiti.transactions.Result;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

/**
 * Adds the children of a document fragment to the output tree. Operands: DOM
 * parent element, document fragment.
 * 
 * @author Matthieu Wipliez
 * 
 */
public class AddDocumentFragmentOpSpec implements IOperationSpecification {

	private void appendChild(Element parent, Node node) {
		if (node.getNodeType() == Node.ATTRIBUTE_NODE) {
			Attr childAttr = (Attr) node;
			parent.setAttribute(childAttr.getName(), childAttr.getValue());
		} else if (node.getNodeType() == Node.TEXT_NODE) {
			parent.setTextContent(node.getNodeValue());
		} else if (node.getNodeType() == Node.ELEMENT_NODE) {
			Document document = parent.getOwnerDocument();
			Element created = document.createElement(node.getNodeName());
			parent.appendChild(created);

			// attributes
			NamedNodeMap attrs = node.getAttributes();
			for (int i = 0; i < attrs.getLength(); i++) {
				appendChild(created, attrs.item(i));
			}

			// children
			Node child = node.getFirstChild();
			while (child != null) {
				appendChild(created, child);
				child = child.getNextSibling();
			}
		}
	}

	@Override
	public void execute(Object[] operands, Result result) {
		Element parent = (Element) operands[0];
		Element docFragment = (Element) operands[1];

		Node child = docFragment.getFirstChild();
		while (child != null) {
			appendChild(parent, child);
			child = child.getNextSibling();
		}
	}

	@Override
	public String getName() {
		return "add document fragment";
	}

	@Override
	public int getNbOperands() {
		return 2;
	}

	@Override
	public boolean hasResult() {
		return false;
	}

}
