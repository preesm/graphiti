/**
 * 
 */
package net.sf.graphiti.ontology.nodes.impl;

import net.sf.graphiti.ontology.nodes.VertexNode;

import com.hp.hpl.jena.ontology.Individual;

/**
 * @author mwipliez
 * 
 */
public class VertexNodeImpl extends ParserNodeImpl implements VertexNode {

	public VertexNodeImpl(Individual individual) {
		super(individual);
	}

	public String toString() {
		return super.toString() + " | VertexNode";
	}

}
