/**
 * 
 */
package net.sf.graphiti.ontology.nodes.parameters.edges.impl;

import net.sf.graphiti.ontology.nodes.impl.ParserParameterNodeImpl;
import net.sf.graphiti.ontology.nodes.parameters.edges.EdgeConnection;

import com.hp.hpl.jena.ontology.Individual;

/**
 * @author mwipliez
 * 
 */
public class EdgeConnectionImpl extends ParserParameterNodeImpl implements
		EdgeConnection {

	public EdgeConnectionImpl(Individual individual) {
		super(individual);
	}

}
