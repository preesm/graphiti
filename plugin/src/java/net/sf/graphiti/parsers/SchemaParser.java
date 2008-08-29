/**
 * 
 */
package net.sf.graphiti.parsers;

import java.util.Iterator;
import java.util.Set;

import net.sf.graphiti.model.Configuration;
import net.sf.graphiti.model.Graph;
import net.sf.graphiti.ontology.OntologyFactory;
import net.sf.graphiti.ontology.xmlDescriptions.attributeRestrictions.AttributeRestriction;
import net.sf.graphiti.ontology.xmlDescriptions.xmlAttributes.XMLAttribute;
import net.sf.graphiti.ontology.xmlDescriptions.xmlSchemaTypes.XMLSchemaType;
import net.sf.graphiti.ontology.xmlDescriptions.xmlSchemaTypes.complexTypes.ComplexType;
import net.sf.graphiti.ontology.xmlDescriptions.xmlSchemaTypes.complexTypes.Sequence;
import net.sf.graphiti.ontology.xmlDescriptions.xmlSchemaTypes.elements.DocumentElement;
import net.sf.graphiti.ontology.xmlDescriptions.xmlSchemaTypes.elements.Element;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.w3c.dom.Comment;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.Text;

/**
 * This class parses the given DOM document according to the schema defined in
 * the ontology.
 * 
 * @author Matthieu Wipliez
 * 
 */
public class SchemaParser {

	private Logger log;

	private ContentParser contentParser;

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
					if (correspond) {
						// TODO
						// parentElement.setValue(attrRestrictName,
						// attrRestrictValue);
					}
				}
			}

			if (ontElement.hasOntClass(OntologyFactory.getClassXMLAttribute())
					&& correspond) {
				// TODO
				// parseAttribute((XMLAttribute) ontNode, domNode,
				// parentElement);
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
	public Graph parse(DocumentElement ontDocElement,
			org.w3c.dom.Element docElement) throws NotCompatibleException {
		if (!isElementDefined(ontDocElement, docElement)) {
			log.debug("parse: element " + docElement.getNodeName()
					+ " not defined");
			throw new NotCompatibleException();
		}

		parseElement(ontDocElement, docElement);
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
	 * the given {@link ComplexType}.
	 * 
	 * @param type
	 * @param firstChild
	 * @return The first remaining child (ie not parsed yet). Can be
	 *         <code>null</code> if there are none left.
	 * @throws NotCompatibleException
	 */
	private org.w3c.dom.Element parseComplexType(ComplexType type,
			org.w3c.dom.Element firstChild) throws NotCompatibleException {
		if (type.hasOntClass(OntologyFactory.getClassSequence())) {
			return parseSequence((Sequence) type, firstChild);
		} else {
			log.debug("parseComplexType: type != Sequence");
			// TODO: all types
			throw new NotCompatibleException();
		}
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

		// check that occurs are valid.
		if (minOccurs < 0 || maxOccurs < -1
				|| (maxOccurs >= 0 && minOccurs > maxOccurs)) {
			log.debug("parseElementOccurs: wrong occurs constraints");
			throw new NotCompatibleException();
		}

		// min occurs
		int i;
		for (i = 0; i < minOccurs && child != null
				&& isElementDefined(ontElement, child); i++) {
			parseElement(ontElement, child);
			child = getNextSibling(child);
		}

		// min occurs check
		if (i < minOccurs) {
			log.debug("parseElementOccurs: minOccurs check failed");
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
			return parseComplexType((ComplexType) type, firstChild);
		} else {
			return parseElementOccurs((Element) type, firstChild);
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
