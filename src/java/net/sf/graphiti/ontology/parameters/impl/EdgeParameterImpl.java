/**
 * 
 */
package net.sf.graphiti.ontology.parameters.impl;

import net.sf.graphiti.ontology.parameters.EdgeParameter;

import com.hp.hpl.jena.ontology.Individual;

/**
 * @author mwipliez
 * 
 */
public class EdgeParameterImpl extends ParameterImpl implements EdgeParameter {

	public EdgeParameterImpl(Individual individual) {
		super(individual);
	}

}
