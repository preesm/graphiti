/**
 * 
 */
package net.sf.graphiti.ontology.nodes.parameters.impl;

import net.sf.graphiti.ontology.nodes.parameters.DefaultParameter;

import com.hp.hpl.jena.ontology.Individual;

/**
 * @author mwipliez
 * 
 */
public class DefaultParameterImpl extends PropertyBeanParameterImpl implements
		DefaultParameter {

	public DefaultParameterImpl(Individual individual) {
		super(individual);
	}

	public String toString() {
		return super.toString() + " | DefaultParameter";
	}
}
