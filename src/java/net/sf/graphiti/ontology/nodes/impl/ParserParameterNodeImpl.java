/**
 * 
 */
package net.sf.graphiti.ontology.nodes.impl;

import java.util.Set;

import net.sf.graphiti.ontology.OntologyFactory;
import net.sf.graphiti.ontology.nodes.ParserNode;
import net.sf.graphiti.ontology.nodes.ParserParameterNode;

import com.hp.hpl.jena.ontology.Individual;

/**
 * @author mwipliez
 * 
 */
public class ParserParameterNodeImpl extends ParserNodeImpl implements
		ParserParameterNode {

	public ParserParameterNodeImpl(Individual individual) {
		super(individual);
	}

	@Override
	public boolean isReference() {
		return getBooleanProperty(OntologyFactory
				.getPropertyParserParameterNodeIsReference());
	}

	@SuppressWarnings("unchecked")
	@Override
	public Set<ParserNode> isReferenceTo() {
		return (Set<ParserNode>) listIndividuals(OntologyFactory
				.getPropertyParserParameterNodeIsReferenceTo());
	}

	public String toString() {
		String res = super.toString() + ", isReference: " + isReference();
		if (isReference()) {
			// res += ", isReferenceTo: " + isReferenceTo().hasName();
		}

		return res;
	}

}
