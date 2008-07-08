/**
 * 
 */
package net.sf.graphiti.ontology.nodes.parameters.edges.impl;

import net.sf.graphiti.ontology.nodes.impl.ParserParameterNodeImpl;
import net.sf.graphiti.ontology.nodes.parameters.edges.EdgeParameterNode;

import com.hp.hpl.jena.ontology.Individual;

/**
 * @author mwipliez
 * 
 */
public class EdgeParameterNodeImpl extends ParserParameterNodeImpl implements
		EdgeParameterNode {

	public EdgeParameterNodeImpl(Individual individual) {
		super(individual);
	}

}
