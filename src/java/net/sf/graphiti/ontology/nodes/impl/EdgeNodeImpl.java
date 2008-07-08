/**
 * 
 */
package net.sf.graphiti.ontology.nodes.impl;

import net.sf.graphiti.ontology.nodes.EdgeNode;

import com.hp.hpl.jena.ontology.Individual;

/**
 * @author mwipliez
 * 
 */
public class EdgeNodeImpl extends ParserNodeImpl implements EdgeNode {

	public EdgeNodeImpl(Individual individual) {
		super(individual);
	}

	public String toString() {
		return super.toString() + " | EdgeNode";
	}

}
