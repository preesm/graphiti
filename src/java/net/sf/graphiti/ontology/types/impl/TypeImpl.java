/**
 * 
 */
package net.sf.graphiti.ontology.types.impl;

import java.util.Set;

import net.sf.graphiti.ontology.OntologyFactory;
import net.sf.graphiti.ontology.OntologyNodeImpl;
import net.sf.graphiti.ontology.attributes.FigureAttribute;
import net.sf.graphiti.ontology.parameters.Parameter;
import net.sf.graphiti.ontology.types.Type;

import com.hp.hpl.jena.ontology.Individual;

/**
 * @author mwipliez
 * 
 */
public class TypeImpl extends OntologyNodeImpl implements Type {

	public TypeImpl(Individual individual) {
		super(individual);
	}

	@Override
	@SuppressWarnings("unchecked")
	public Set<FigureAttribute> hasFigureAttributes() {
		return (Set<FigureAttribute>) listIndividuals(OntologyFactory
				.getPropertyTypeHasFigureAttributes());
	}

	@Override
	@SuppressWarnings("unchecked")
	public Set<Parameter> hasParameters() {
		return (Set<Parameter>) listIndividuals(OntologyFactory
				.getPropertyTypeHasParameters());
	}

	@Override
	public String hasStringRepresentation() {
		return getStringProperty(OntologyFactory
				.getPropertyTypeHasStringRepresentation());
	}

	public String toString() {
		return super.toString() + " | Type: hasStringRepresentation: "
				+ hasStringRepresentation() + ", hasParameters: "
				+ hasParameters() + ", hasFigureAttributes:"
				+ hasFigureAttributes();
	}
}
