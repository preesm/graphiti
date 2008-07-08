/**
 * 
 */
package net.sf.graphiti.ontology.nodes.parameters.edges.impl;

import net.sf.graphiti.ontology.nodes.impl.ParserParameterNodeImpl;
import net.sf.graphiti.ontology.nodes.parameters.edges.EdgeSourceConnection;

import com.hp.hpl.jena.ontology.Individual;

/**
 * @author mwipliez
 * 
 */
public class EdgeSourceConnectionImpl extends ParserParameterNodeImpl implements
		EdgeSourceConnection {

	public EdgeSourceConnectionImpl(Individual individual) {
		super(individual);
	}

}
