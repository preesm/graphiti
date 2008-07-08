/**
 * 
 */
package net.sf.graphiti.ontology.parameters.impl;

import net.sf.graphiti.ontology.OntologyFactory;
import net.sf.graphiti.ontology.OntologyNodeImpl;
import net.sf.graphiti.ontology.enums.DataTypes;
import net.sf.graphiti.ontology.enums.Position;
import net.sf.graphiti.ontology.parameters.Parameter;
import net.sf.graphiti.ontology.types.Type;

import com.hp.hpl.jena.ontology.Individual;

/**
 * @author mwipliez
 * 
 */
public class ParameterImpl extends OntologyNodeImpl implements Parameter {

	public ParameterImpl(Individual individual) {
		super(individual);
	}

	@Override
	public Type appliesTo() {
		return (Type) getIndividualProperty(OntologyFactory
				.getPropertyParameterAppliesTo());
	}

	@Override
	public String hasName() {
		return getStringProperty(OntologyFactory.getPropertyParameterHasName());
	}

	@Override
	public Position hasPosition() {
		return (Position) getIndividualProperty(OntologyFactory
				.getPropertyParameterHasPosition());
	}

	@Override
	public DataTypes hasValueType() {
		return (DataTypes) getIndividualProperty(OntologyFactory
				.getPropertyParameterHasValueType());
	}

	public String toString() {
		return super.toString() + " | Parameter: hasName: " + hasName()
				+ ", hasValueType: " + hasValueType() + ", appliesTo: "
				+ appliesTo();
	}

}
