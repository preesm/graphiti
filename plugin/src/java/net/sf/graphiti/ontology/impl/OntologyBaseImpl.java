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
package net.sf.graphiti.ontology.impl;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import net.sf.graphiti.ontology.OntologyFactory;

import com.hp.hpl.jena.ontology.DatatypeProperty;
import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.ObjectProperty;
import com.hp.hpl.jena.ontology.OntResource;
import com.hp.hpl.jena.rdf.model.Literal;
import com.hp.hpl.jena.rdf.model.NodeIterator;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.util.iterator.ExtendedIterator;

/**
 * This class provides several methods to retrieve property values and also
 * maintains a hash table (filled when the class is loaded) that associates
 * ontology classes ({@link String}s that represent URIs) to concrete classes.
 * 
 * @author Matthieu Wipliez
 * 
 */
public abstract class OntologyBaseImpl {

	private static Map<String, Class<?>> classes = new HashMap<String, Class<?>>();

	static {
		// DataTypes
		classes.put(OntologyFactory.getClassDataType(), DataTypeImpl.class);

		// Enums
		classes.put(OntologyFactory.getClassShapes(), ShapesImpl.class);
		classes.put(OntologyFactory.getClassPosition(), PositionImpl.class);

		// FigureAttribute
		classes.put(OntologyFactory.getClassColorAttribute(),
				ColorAttributeImpl.class);
		classes.put(OntologyFactory.getClassFigureAttribute(),
				FigureAttributeImpl.class);
		classes.put(OntologyFactory.getClassShapeAttribute(),
				ShapeAttributeImpl.class);

		// Parameter
		classes.put(OntologyFactory.getClassEdgeParameter(),
				EdgeParameterImpl.class);
		classes.put(OntologyFactory.getClassGraphParameter(),
				GraphParameterImpl.class);
		classes.put(OntologyFactory.getClassParameter(), ParameterImpl.class);
		classes.put(OntologyFactory.getClassVertexParameter(),
				VertexParameterImpl.class);

		// ParameterValue
		classes.put(OntologyFactory.getClassParameterValue(),
				ParameterValueImpl.class);

		// Types
		classes.put(OntologyFactory.getClassEdgeType(), EdgeTypeImpl.class);
		classes.put(OntologyFactory.getClassGraphType(), GraphTypeImpl.class);
		classes.put(OntologyFactory.getClassType(), TypeImpl.class);
		classes.put(OntologyFactory.getClassVertexType(), VertexTypeImpl.class);

		// XML Description

		// AttributeRestriction
		classes.put(OntologyFactory.getClassAttributeRestriction(),
				AttributeRestrictionImpl.class);

		// XML attribute
		classes.put(OntologyFactory.getClassXMLAttribute(),
				XMLAttributeImpl.class);

		// OtherAttribute
		classes.put(OntologyFactory.getClassOtherAttribute(),
				OtherAttributeImpl.class);

		// EdgeAttribute
		classes.put(OntologyFactory.getClassEdgeAttribute(),
				EdgeAttributeImpl.class);
		classes.put(OntologyFactory.getClassEdgeConnection(),
				EdgeConnectionImpl.class);
		classes.put(OntologyFactory.getClassEdgeSourceConnection(),
				EdgeSourceConnectionImpl.class);
		classes.put(OntologyFactory.getClassEdgeTargetConnection(),
				EdgeTargetConnectionImpl.class);

		// XML Schema Type
		classes.put(OntologyFactory.getClassXMLSchemaType(),
				XMLSchemaTypeImpl.class);

		// Complex Type
		classes.put(OntologyFactory.getClassComplexType(),
				ComplexTypeImpl.class);
		classes.put(OntologyFactory.getClassAll(), AllImpl.class);
		classes.put(OntologyFactory.getClassChoice(), ChoiceImpl.class);
		classes.put(OntologyFactory.getClassOrderedChoice(),
				OrderedChoiceImpl.class);
		classes.put(OntologyFactory.getClassSequence(), SequenceImpl.class);

		// Element
		classes.put(OntologyFactory.getClassEdgeElement(),
				EdgeElementImpl.class);
		classes.put(OntologyFactory.getClassElement(), ElementImpl.class);
		classes.put(OntologyFactory.getClassGraphElement(),
				GraphElementImpl.class);
		classes.put(OntologyFactory.getClassTextContentElement(),
				TextContentElementImpl.class);
		classes.put(OntologyFactory.getClassVertexElement(),
				VertexElementImpl.class);
	}

	protected OntResource resource;

	/**
	 * Creates a new {@link OntologyBaseImpl} using the given
	 * {@link OntResource}.
	 * 
	 * @param resource
	 */
	protected OntologyBaseImpl(OntResource resource) {
		this.resource = resource;
	}

	/**
	 * Converts the given node to a boolean.
	 * 
	 * @param node
	 *            A {@link RDFNode}.
	 * @return A boolean.
	 */
	private boolean convertBoolean(RDFNode node) {
		if (node != null && node.canAs(Literal.class)) {
			Literal lit = (Literal) node.as(Literal.class);
			return lit.getBoolean();
		} else {
			return false;
		}
	}

	/**
	 * Converts the {@link RDFNode} node to an {@link Object}.
	 * 
	 * @param node
	 *            The node to convert.
	 * @return An object, or <code>null</code> if <code>node == null</code> or
	 *         if the conversion did not succeed.
	 */
	private Object convertIndividual(RDFNode node) {
		if (node != null && node.canAs(Individual.class)) {
			Individual individual = (Individual) node.as(Individual.class);
			try {
				Class<?> clasz = classes.get(individual.getOntClass().getURI());
				Constructor<?> constructors[] = clasz.getConstructors();
				return constructors[0].newInstance(individual);
			} catch (InstantiationException e) {
				throw new IllegalArgumentException(e);
			} catch (IllegalAccessException e) {
				throw new IllegalArgumentException(e);
			} catch (InvocationTargetException e) {
				throw new IllegalArgumentException(e);
			}
		}

		return null;
	}

	/**
	 * Converts the individuals accessible using the <code>it</code> iterator to
	 * the correct class.
	 * 
	 * @param it
	 *            An ExtendedIterator to a list of individuals.
	 * @return A set of objects.
	 */
	protected Set<?> convertIndividuals(ExtendedIterator it) {
		Set<Object> set = new TreeSet<Object>();
		while (it.hasNext()) {
			RDFNode node = (RDFNode) it.next();
			set.add(convertIndividual(node));
		}

		return set;
	}

	/**
	 * Converts the given node to an int.
	 * 
	 * @param node
	 *            A {@link RDFNode}.
	 * @return An int.
	 */
	private int convertInteger(RDFNode node) {
		if (node != null && node.canAs(Literal.class)) {
			Literal lit = (Literal) node.as(Literal.class);
			return lit.getInt();
		} else {
			return 0;
		}
	}

	/**
	 * Converts the given node to a string.
	 * 
	 * @param node
	 *            A {@link RDFNode}.
	 * @return A {@link String}.
	 */
	private String convertString(RDFNode node) {
		if (node != null && node.canAs(Literal.class)) {
			Literal lit = (Literal) node.as(Literal.class);
			return lit.getString();
		} else {
			return "";
		}
	}

	/**
	 * Converts the nodes accessible using the <code>it</code> iterator to a set
	 * of strings.
	 * 
	 * @param it
	 *            An ExtendedIterator to a list of {@link RDFNode}s.
	 * @return A {@link Set} of {@link String}s.
	 */
	private Set<String> convertStrings(NodeIterator it) {
		Set<String> set = new TreeSet<String>();
		while (it.hasNext()) {
			RDFNode node = (RDFNode) it.next();
			set.add(convertString(node));
		}

		return set;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof OntologyIndividualImpl) {
			return resource.getURI().equals(
					((OntologyIndividualImpl) obj).resource.getURI());
		} else {
			return false;
		}
	}

	/**
	 * Returns the value associated with this resource and the property called
	 * <code>propertyName</code>.
	 * 
	 * @param propertyName
	 *            A property of this resource.
	 * @return The value of the given property on this resource as a boolean.
	 */
	protected boolean getBooleanProperty(String propertyName) {
		return convertBoolean(resource
				.getPropertyValue(getDatatypeProperty(propertyName)));
	}

	/**
	 * Returns the data property whose name is given.
	 * 
	 * @param propertyName
	 *            The property name.
	 * @return An {@link DatatypeProperty}.
	 */
	private DatatypeProperty getDatatypeProperty(String propertyName) {
		DatatypeProperty property = resource.getOntModel().getDatatypeProperty(
				propertyName);
		if (property == null) {
			System.err.println("property " + propertyName + " does not exist");
			throw new NullPointerException();
		} else {
			return property;
		}
	}

	/**
	 * Returns the value associated with this resource and the property called
	 * <code>propertyName</code>.
	 * 
	 * @param propertyName
	 *            A property of this resource.
	 * @return The value of the given property on this resource as an
	 *         {@link Object}.
	 */
	protected Object getIndividualProperty(String propertyName) {
		return convertIndividual(resource
				.getPropertyValue(getObjectProperty(propertyName)));
	}

	/**
	 * Returns the value associated with this resource and the property called
	 * <code>propertyName</code>.
	 * 
	 * @param propertyName
	 *            A property of this resource.
	 * @return The value of the given property on this resource as an
	 *         <code>int</code>.
	 */
	protected int getIntegerProperty(String propertyName) {
		return convertInteger(resource
				.getPropertyValue(getDatatypeProperty(propertyName)));
	}

	/**
	 * Returns the object property whose name is given.
	 * 
	 * @param propertyName
	 *            The property name.
	 * @return An {@link ObjectProperty}.
	 */
	private ObjectProperty getObjectProperty(String propertyName) {
		ObjectProperty property = resource.getOntModel().getObjectProperty(
				propertyName);
		if (property == null) {
			System.err.println("property " + propertyName + " does not exist");
			throw new NullPointerException();
		} else {
			return property;
		}
	}

	/**
	 * Returns the unique URI of this resource.
	 * 
	 * @return This resource's URI.
	 */
	public String getResourceURI() {
		return resource.getURI();
	}

	/**
	 * Returns the value associated with this resource and the property called
	 * <code>propertyName</code>.
	 * 
	 * @param propertyName
	 *            A property of this resource.
	 * @return The value of the given property on this resource as a
	 *         {@link String}.
	 */
	protected String getStringProperty(String propertyName) {
		return convertString(resource
				.getPropertyValue(getDatatypeProperty(propertyName)));
	}

	@Override
	public int hashCode() {
		return getResourceURI().hashCode();
	}

	/**
	 * Lists all the string values of the given property of this resource.
	 * 
	 * @param propertyName
	 *            The property name.
	 * @return A {@link Set} of {@link String}s.
	 */
	protected Set<?> listIndividuals(String propertyName) {
		return convertIndividuals(resource
				.listPropertyValues(getObjectProperty(propertyName)));
	}

	/**
	 * Lists all the string values of the given property of this resource.
	 * 
	 * @param propertyName
	 *            The property name.
	 * @return A {@link Set} of {@link String}s.
	 */
	protected Set<String> listStrings(String propertyName) {
		return convertStrings(resource
				.listPropertyValues(getDatatypeProperty(propertyName)));
	}
}
