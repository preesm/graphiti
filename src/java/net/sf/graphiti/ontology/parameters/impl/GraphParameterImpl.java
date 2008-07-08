/**
 * 
 */
package net.sf.graphiti.ontology.parameters.impl;

import net.sf.graphiti.ontology.parameters.GraphParameter;

import com.hp.hpl.jena.ontology.Individual;

/**
 * @author mwipliez
 * 
 */
public class GraphParameterImpl extends ParameterImpl implements GraphParameter {

	public GraphParameterImpl(Individual individual) {
		super(individual);
	}

}
