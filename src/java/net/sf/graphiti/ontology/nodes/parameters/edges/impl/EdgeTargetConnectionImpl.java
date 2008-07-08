/**
 * 
 */
package net.sf.graphiti.ontology.nodes.parameters.edges.impl;

import net.sf.graphiti.ontology.nodes.impl.ParserParameterNodeImpl;
import net.sf.graphiti.ontology.nodes.parameters.edges.EdgeTargetConnection;

import com.hp.hpl.jena.ontology.Individual;

/**
 * @author mwipliez
 * 
 */
public class EdgeTargetConnectionImpl extends ParserParameterNodeImpl implements
		EdgeTargetConnection {

	public EdgeTargetConnectionImpl(Individual individual) {
		super(individual);
	}

}
