package net.sf.graphiti.ontology.enums.impl;

import net.sf.graphiti.ontology.OntologyNodeImpl;
import net.sf.graphiti.ontology.enums.DataTypes;

import com.hp.hpl.jena.ontology.Individual;

public class DataTypesImpl extends OntologyNodeImpl implements DataTypes {

	public DataTypesImpl(Individual individual) {
		super(individual);
	}

}
