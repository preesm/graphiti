/**
 * 
 */
package net.sf.graphiti.ontology.nodes.parameters.impl;

import net.sf.graphiti.ontology.nodes.parameters.IdParameter;

import com.hp.hpl.jena.ontology.Individual;

/**
 * @author mwipliez
 * 
 */
public class IdParameterImpl extends PropertyBeanParameterImpl implements
		IdParameter {

	public IdParameterImpl(Individual individual) {
		super(individual);
	}

	public String toString() {
		return super.toString() + " | IdParameter";
	}
}
