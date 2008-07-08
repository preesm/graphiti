/**
 * 
 */
package net.sf.graphiti.ontology.nodes.impl;

import net.sf.graphiti.ontology.nodes.SkipNode;

import com.hp.hpl.jena.ontology.Individual;

/**
 * @author mwipliez
 * 
 */
public class SkipNodeImpl extends ParserNodeImpl implements SkipNode {

	public SkipNodeImpl(Individual individual) {
		super(individual);
	}

	public String toString() {
		return super.toString() + " | SkipNode";
	}


}
