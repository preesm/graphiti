package net.sf.graphiti.ontology.enums.impl;

import net.sf.graphiti.ontology.OntologyNodeImpl;
import net.sf.graphiti.ontology.enums.Position;

import com.hp.hpl.jena.ontology.Individual;

public class PositionImpl extends OntologyNodeImpl implements Position {

	public PositionImpl(Individual individual) {
		super(individual);
		// TODO Auto-generated constructor stub
	}

	public String toString() {
		return this.getIndividualName();
	}

}
