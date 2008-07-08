/**
 * 
 */
package net.sf.graphiti.ontology.types.impl;

import net.sf.graphiti.ontology.types.GraphType;

import com.hp.hpl.jena.ontology.Individual;

/**
 * @author mwipliez
 * 
 */
public class GraphTypeImpl extends TypeImpl implements GraphType {

	public GraphTypeImpl(Individual individual) {
		super(individual);
	}

}
