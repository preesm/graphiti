/**
 * 
 */
package net.sf.graphiti.ontology.nodes.impl;

import net.sf.graphiti.ontology.nodes.GraphNode;

import com.hp.hpl.jena.ontology.Individual;

/**
 * @author mwipliez
 * 
 */
public class GraphNodeImpl extends ParserRootNodeImpl implements GraphNode {

	public GraphNodeImpl(Individual individual) {
		super(individual);
	}

	public String toString() {
		return super.toString() + " | GraphNode";
	}

}
