/**
 * 
 */
package net.sf.graphiti.ontology.nodes.impl;

import net.sf.graphiti.ontology.nodes.ParserRootNode;

import com.hp.hpl.jena.ontology.Individual;

/**
 * This class is an implementation of a ParserRootNode.
 * 
 * @author Matthieu Wipliez
 * 
 */
public class ParserRootNodeImpl extends ParserNodeImpl implements
		ParserRootNode {

	public ParserRootNodeImpl(Individual individual) {
		super(individual);
	}


	public String toString() {
		return super.toString() + " | ParserRootNode";
	}

}
