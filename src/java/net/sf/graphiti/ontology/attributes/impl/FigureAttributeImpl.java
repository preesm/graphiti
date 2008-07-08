/**
 * 
 */
package net.sf.graphiti.ontology.attributes.impl;

import net.sf.graphiti.ontology.OntologyFactory;
import net.sf.graphiti.ontology.OntologyNodeImpl;
import net.sf.graphiti.ontology.attributes.FigureAttribute;
import net.sf.graphiti.ontology.types.Type;

import com.hp.hpl.jena.ontology.Individual;

/**
 * @author mwipliez
 * 
 */
public class FigureAttributeImpl extends OntologyNodeImpl implements
		FigureAttribute {

	public FigureAttributeImpl(Individual individual) {
		super(individual);
	}

	@Override
	public Type appliesTo() {
		return (Type) getIndividualProperty(OntologyFactory
				.getPropertyFigureAttributeAppliesTo());
	}

	@Override
	public String hasType() {
		return getStringProperty(OntologyFactory
				.getPropertyFigureAttributeHasType());
	}

	public String toString() {
		return super.toString() + " | FigureAttribute: type = " + hasType()
				+ ", appliesTo: " + appliesTo();
	}
}
