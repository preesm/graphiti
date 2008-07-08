/**
 * 
 */
package net.sf.graphiti.ontology.parameters.impl;

import net.sf.graphiti.ontology.parameters.VertexParameter;

import com.hp.hpl.jena.ontology.Individual;

/**
 * @author mwipliez
 * 
 */
public class VertexParameterImpl extends ParameterImpl implements
		VertexParameter {

	public VertexParameterImpl(Individual individual) {
		super(individual);
	}

}
