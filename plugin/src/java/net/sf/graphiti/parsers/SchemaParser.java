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
package net.sf.graphiti.parsers;

import java.util.Iterator;
import java.util.Set;

import net.sf.graphiti.model.Configuration;
import net.sf.graphiti.model.Graph;
import net.sf.graphiti.ontology.AttributeRestriction;
import net.sf.graphiti.ontology.Choice;
import net.sf.graphiti.ontology.ComplexType;
import net.sf.graphiti.ontology.DocumentFragment;
import net.sf.graphiti.ontology.Element;
import net.sf.graphiti.ontology.OntologyFactory;
import net.sf.graphiti.ontology.Sequence;
import net.sf.graphiti.ontology.XMLAttribute;
import net.sf.graphiti.ontology.XMLSchemaType;
import net.sf.graphiti.parsers.ContentParser.Checkpoint;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.w3c.dom.Comment;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

/**
 * This class parses the given DOM document according to the schema defined in
 * the ontology.
 * 
 * @author Matthieu Wipliez
 * 
 */
public class SchemaParser {

	private ContentParser contentParser;

	private Logger log;

	/**
	 * Creates a new schema parser using the given configuration.
	 * 
	 * @param configuration
	 */
	public SchemaParser(Configuration configuration) {
		log = Logger.getLogger(SchemaParser.class);
		log.setLevel(Level.ALL);
		contentParser = new ContentParser(configuration);
	}

	/**
	 * Checks that occurs are valid.
	 * 
	 * @param minOccurs
	 * @param maxOccurs
	 * @throws NotCompatibleException
	 */
	private void checkOccurs(int minOccurs, int maxOccurs)
			throws NotCompatibleException {
		if (minOccurs < 0 || maxOccurs < -1
				|| (maxOccurs >= 0 && minOccurs > maxOccurs)) {
			log.debug("parseElementOccurs: wrong occurs constraints");
			throw new NotCompatibleException();
		}
	}

	/**
	 * Returns the first child of <code>element</code> which is a
	 * {@link org.w3c.dom.Element} (not {@link Text}, not {@link Comment}, etc.)
	 * 
	 * @param element
	 *            The parent DOM element.
	 * @return The first {@link org.w3c.dom.Element} child of
	 *         <code>element</code>.
	 */
	private org.w3c.dom.Element getFirstChild(org.w3c.dom.Element element) {
		Node node = element.getFirstChild();
		while (node != null && node.getNodeType() != Node.ELEMENT_NODE) {
			node = node.getNextSibling();
		}

		return (org.w3c.dom.Element) node;
	}

	/**
	 * Returns the next sibling of <code>element</code> which is a
	 * {@link org.w3c.dom.Element} (not {@link Text}, not {@link Comment}, etc.)
	 * 
	 * @param element
	 *            A DOM element.
	 * @return The first {@link org.w3c.dom.Element} sibling of
	 *         <code>element</code>.
	 */
	private org.w3c.dom.Element getNextSibling(org.w3c.dom.Element element) {
		Node node = element.getNextSibling();
		while (node != null && node.getNodeType() != Node.ELEMENT_NODE) {
			node = node.getNextSibling();
		}

		return (org.w3c.dom.Element) node;
	}

	/**
	 * Returns true if the element <code>domElement</code> fits the profile
	 * defined by <code>ontElement</code>: name, attribute restriction.
	 * 
	 * @param ontElement
	 * @param domElement
	 * @return True if the element <code>domElement</code> fits the profile
	 *         defined by <code>ontElement</code>: name, attribute restriction.
	 */
	private boolean isElementDefined(Element ontElement,
			org.w3c.dom.Element domElement) {
		boolean correspond = false;

		// If the DOM element has the same name as this ontology element
		if (ontElement.hasName().equals(domElement.getNodeName())) {
			// We apply attribute restrictions (if it has any).
			Iterator<AttributeRestriction> it = ontElement
					.hasAttributeRestriction().iterator();
			NamedNodeMap attributes = domElement.getAttributes();

			correspond = true;
			while (it.hasNext() && correspond) {
				AttributeRestriction attrRestrict = it.next();
				String attrRestrictName = attrRestrict.hasName();
				Node node = attributes.getNamedItem(attrRestrictName);

				if (node == null) {
					// The DOM has no attribute with the same name as our
					// attribute restriction.
					correspond = false;
				} else {
					// The DOM node has an attribute that matches our attribute
					// restriction. It corresponds if the value is the same.
					String attrRestrictValue = attrRestrict.hasValue();
					correspond &= node.getNodeValue().equals(attrRestrictValue);
				}
			}
		}

		return correspond;
	}

	/**
	 * This method parses the given DOM document element according to the
	 * <code>ontDocElement</code>, and returns a {@link Graph} if successful.
	 * 
	 * @param ontDocElement
	 * @param docElement
	 * @return
	 * @throws NotCompatibleException
	 */
	public Graph parse(XMLSchemaType ontDocElement,
			org.w3c.dom.Element docElement) throws NotCompatibleException {
		if (ontDocElement.hasOntClass(OntologyFactory.getClassComplexType())) {
			parseComplexTypeOccurs((ComplexType) ontDocElement, docElement);
		} else {
			if (!isElementDefined((Element) ontDocElement, docElement)) {
				throw new NotCompatibleException();
			}

			parseElement((Element) ontDocElement, docElement);
		}

		return contentParser.getGraph();
	}

	/**
	 * Parses the attributes of <code>domElement</code> according to
	 * <code>ontElement</code>.
	 * 
	 * @param ontElement
	 * @param domElement
	 * @throws NotCompatibleException
	 *             If an attribute is defined in the ontology but not present in
	 *             the DOM.
	 */
	private void parseAttributes(Element ontElement,
			org.w3c.dom.Element domElement) throws NotCompatibleException {
		Set<XMLAttribute> attributes = ontElement.hasAttributes();
		for (XMLAttribute ontAttribute : attributes) {
			String ontAttrName = ontAttribute.hasName();
			String domAttrValue = domElement.getAttribute(ontAttrName);
			if (domAttrValue == null) {
				log.debug("parseAttributes: attribute " + ontAttrName
						+ " not present");
				throw new NotCompatibleException();
			} else {
				contentParser.parseAttribute(ontAttribute, domAttrValue);
			}
		}
	}

	/**
	 * Parses the children (<code>firstChild</code> is the first of them) using
	 * the given {@link Choice}.
	 * 
	 * @param choice
	 * @param firstChild
	 * @return The first remaining child (ie not parsed yet). Can be
	 *         <code>null</code> if there are none left.
	 * @throws NotCompatibleException
	 */
	private org.w3c.dom.Element parseChoice(Choice choice,
			org.w3c.dom.Element firstChild) throws NotCompatibleException {
		Checkpoint checkpoint = contentParser.getCheckpoint();
		for (XMLSchemaType type : choice.hasElements()) {
			try {
				org.w3c.dom.Element child = parseSchemaType(type, firstChild);
				return child;
			} catch (NotCompatibleException e) {
				contentParser.loadCheckpoint(checkpoint);
			}
		}

		throw new NotCompatibleException();
	}

	/**
	 * Parses child with the given {@link ComplexType}.
	 * 
	 * @param type
	 * @param child
	 * @return
	 * @throws NotCompatibleException
	 */
	private org.w3c.dom.Element parseComplexType(ComplexType type,
			org.w3c.dom.Element child) throws NotCompatibleException {
		if (type instanceof Sequence) {
			return parseSequence((Sequence) type, child);
		} else if (type instanceof Choice) {
			return parseChoice((Choice) type, child);
		} else {
			log.debug("parseComplexType: type = All");
			throw new NotCompatibleException();
		}
	}

	/**
	 * Parses the children (<code>firstChild</code> is the first of them) using
	 * the given {@link ComplexType}.
	 * 
	 * @param type
	 * @param firstChild
	 * @return The first remaining child (ie not parsed yet). Can be
	 *         <code>null</code> if there are none left.
	 * @throws NotCompatibleException
	 */
	private org.w3c.dom.Element parseComplexTypeOccurs(ComplexType type,
			org.w3c.dom.Element firstChild) throws NotCompatibleException {
		org.w3c.dom.Element child = firstChild;
		Checkpoint checkpoint = contentParser.getCheckpoint();
		int minOccurs = type.minOccurs();
		int maxOccurs = type.maxOccurs();
		checkOccurs(minOccurs, maxOccurs);

		// min occurs
		int i = 0;
		try {
			for (; i < minOccurs; i++) {
				child = parseComplexType(type, child);
			}
		} catch (NotCompatibleException e) {
		}

		// min occurs check
		if (i < minOccurs) {
			contentParser.loadCheckpoint(checkpoint);
			throw new NotCompatibleException();
		}

		// max occurs
		checkpoint = contentParser.getCheckpoint();
		if (maxOccurs > -1) {
			try {
				for (; i < maxOccurs && child != null; i++) {
					child = parseComplexType(type, child);
				}
			} catch (NotCompatibleException e) {
			}
		} else {
			// parses all children
			try {
				while (child != null) {
					child = parseComplexType(type, child);
				}
			} catch (NotCompatibleException e) {
			}
		}

		return child;
	}

	/**
	 * Parses child with the given {@link DocumentFragment}.
	 * 
	 * @param docFragment
	 * @param firstChild
	 * @return
	 * @throws NotCompatibleException
	 */
	private org.w3c.dom.Element parseDocumentFragmentOccurs(
			DocumentFragment docFragment, org.w3c.dom.Element firstChild)
			throws NotCompatibleException {
		org.w3c.dom.Element child = firstChild;
		Checkpoint checkpoint = contentParser.getCheckpoint();
		int minOccurs = docFragment.minOccurs();
		int maxOccurs = docFragment.maxOccurs();
		org.w3c.dom.Element domDocFragment = docFragment.hasXMLContents();

		// min occurs
		int i = 0;
		try {
			for (; i < minOccurs; i++) {
				child = parseDocumentFragment(domDocFragment, child);
			}
		} catch (NotCompatibleException e) {
		}

		// min occurs check
		if (i < minOccurs) {
			contentParser.loadCheckpoint(checkpoint);
			throw new NotCompatibleException();
		}

		// max occurs
		checkpoint = contentParser.getCheckpoint();
		if (maxOccurs > -1) {
			try {
				for (; i < maxOccurs && child != null; i++) {
					child = parseDocumentFragment(domDocFragment, child);
				}
			} catch (NotCompatibleException e) {
			}
		} else {
			// parses all children
			try {
				while (child != null) {
					child = parseDocumentFragment(domDocFragment, child);
				}
			} catch (NotCompatibleException e) {
			}
		}

		return child;
	}

	/**
	 * Checks that child equals the given element, obtained from an ontology
	 * DocumentFragment.
	 * 
	 * @param domDocFragment
	 *            The reference {@link org.w3c.dom.Element}.
	 * @param firstChild
	 *            An {@link org.w3c.dom.Element} from the input DOM.
	 * @return The sibling of child if successful.
	 * @throws NotCompatibleException
	 *             If child is different from domDocFragment.
	 */
	private org.w3c.dom.Element parseDocumentFragment(
			org.w3c.dom.Element domDocFragment, org.w3c.dom.Element firstChild)
			throws NotCompatibleException {
		org.w3c.dom.Element child = firstChild;
		Node node = domDocFragment.getFirstChild();
		while (node != null && node.isEqualNode(stripWhitespaces(child))) {
			node = node.getNextSibling();
			child = getNextSibling(child);
		}

		if (node == null) {
			// contents validated against the whole document fragment
			return child;
		} else {
			throw new NotCompatibleException();
		}
	}

	/**
	 * Removes whitespace (including "\n") nodes.
	 * 
	 * @param node
	 * @param child
	 * @return
	 */
	private Node stripWhitespaces(Node node) {
		if (node.getNodeType() == Node.TEXT_NODE) {
			String value = node.getNodeValue();
			if (value.trim().isEmpty()) {
				return null;
			}
		} else {
			NodeList children = node.getChildNodes();
			for (int i = 0; i < children.getLength(); i++) {
				Node child = children.item(i);
				Node newChild = stripWhitespaces(child);
				if (newChild == null) {
					node.removeChild(child);
				} else {
					node.replaceChild(newChild, child);
				}
			}
		}
		
		return node;
	}

	/**
	 * Parses the given <code>domElement</code> with the information given by
	 * <code>ontElement</code>.
	 * 
	 * @param ontElement
	 * @param domElement
	 * @return True if the element is defined, false otherwise.
	 * @throws NotCompatibleException
	 */
	private void parseElement(Element ontElement, org.w3c.dom.Element domElement)
			throws NotCompatibleException {
		contentParser.elementStart(ontElement, domElement);

		parseAttributes(ontElement, domElement);

		XMLSchemaType type = ontElement.hasSchemaType();
		if (type != null) {
			org.w3c.dom.Element firstChild = getFirstChild(domElement);
			parseSchemaType(type, firstChild);
		}

		contentParser.elementEnd(ontElement, domElement);
	}

	/**
	 * Parses elements starting with <code>child</code>, and then its siblings.
	 * This method ensures that the occurs checks on <code>ontElement</code> are
	 * enforced.
	 * 
	 * @param ontElement
	 * @param child
	 * @return The first remaining child (ie not parsed yet). Can be
	 *         <code>null</code> if there are none left.
	 * @throws NotCompatibleException
	 */
	private org.w3c.dom.Element parseElementOccurs(Element ontElement,
			org.w3c.dom.Element child) throws NotCompatibleException {
		int minOccurs = ontElement.minOccurs();
		int maxOccurs = ontElement.maxOccurs();
		checkOccurs(minOccurs, maxOccurs);

		// min occurs
		int i;
		for (i = 0; i < minOccurs && child != null
				&& isElementDefined(ontElement, child); i++) {
			parseElement(ontElement, child);
			child = getNextSibling(child);
		}

		// min occurs check
		if (i < minOccurs) {
			throw new NotCompatibleException();
		}

		// max occurs
		if (maxOccurs > -1) {
			for (; i < maxOccurs && child != null
					&& isElementDefined(ontElement, child); i++) {
				parseElement(ontElement, child);
				child = getNextSibling(child);
			}
		} else {
			// parses all children
			while (child != null && isElementDefined(ontElement, child)) {
				parseElement(ontElement, child);
				child = getNextSibling(child);
			}
		}

		return child;
	}

	/**
	 * Parses the children (<code>firstChild</code> is the first of them) using
	 * the given schema type.
	 * 
	 * @param ontElement
	 * @param firstChild
	 * @return The first remaining child (ie not parsed yet). Can be
	 *         <code>null</code> if there are none left.
	 * @throws NotCompatibleException
	 */
	private org.w3c.dom.Element parseSchemaType(XMLSchemaType type,
			org.w3c.dom.Element firstChild) throws NotCompatibleException {
		if (type.hasOntClass(OntologyFactory.getClassComplexType())) {
			return parseComplexTypeOccurs((ComplexType) type, firstChild);
		} else if (type.hasOntClass(OntologyFactory.getClassElement())) {
			return parseElementOccurs((Element) type, firstChild);
		} else {
			return parseDocumentFragmentOccurs((DocumentFragment) type,
					firstChild);
		}
	}

	/**
	 * Parses the children (<code>firstChild</code> is the first of them) using
	 * the given {@link Sequence}.
	 * 
	 * @param sequence
	 * @param firstChild
	 * @return The first remaining child (ie not parsed yet). Can be
	 *         <code>null</code> if there are none left.
	 * @throws NotCompatibleException
	 */
	private org.w3c.dom.Element parseSequence(Sequence sequence,
			org.w3c.dom.Element firstChild) throws NotCompatibleException {
		org.w3c.dom.Element child = firstChild;
		for (XMLSchemaType type : sequence.hasElements()) {
			child = parseSchemaType(type, child);
		}
		return child;
	}

}
