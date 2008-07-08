package net.sf.graphiti.ontology.nodes.parameters.impl;

import net.sf.graphiti.ontology.OntologyFactory;
import net.sf.graphiti.ontology.nodes.impl.ParserParameterNodeImpl;
import net.sf.graphiti.ontology.nodes.parameters.ParserFixedParameter;

import com.hp.hpl.jena.ontology.Individual;

public class ParserFixedParameterImpl extends ParserParameterNodeImpl implements ParserFixedParameter{

	public ParserFixedParameterImpl(Individual individual) {
		super(individual);
	}

	@Override
	public String hasValue() {
		return getStringProperty(OntologyFactory
				.getParserFixedParameterHasValue());
	}

}
