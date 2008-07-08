/**
 * 
 */
package net.sf.graphiti.ontology.nodes.impl;

import java.util.Set;

import net.sf.graphiti.ontology.OntologyFactory;
import net.sf.graphiti.ontology.OntologyNodeImpl;
import net.sf.graphiti.ontology.nodes.ParserNode;
import net.sf.graphiti.ontology.nodes.ParserParameterNode;
import net.sf.graphiti.ontology.nodes.parameters.ParserFixedParameter;

import com.hp.hpl.jena.ontology.Individual;

/**
 * @author mwipliez
 * 
 */
public class ParserNodeImpl extends OntologyNodeImpl implements ParserNode {

	public ParserNodeImpl(Individual individual) {
		super(individual);
	}

	@Override
	@SuppressWarnings("unchecked")
	public Set<ParserParameterNode> hasAttributeNode() {
		return (Set<ParserParameterNode>) listIndividuals(OntologyFactory
				.getPropertyParserNodeHasAttributeNode());
	}

	@Override
	@SuppressWarnings("unchecked")
	public Set<ParserNode> hasChildrenNode() {
		return (Set<ParserNode>) listIndividuals(OntologyFactory
				.getPropertyParserNodeHasChildNode());
	}

	@Override
	public String hasName() {
		return getStringProperty(OntologyFactory.getPropertyParserNodeHasName());
	}

	@Override
	public ParserNode hasPrecedenceNode() {
		return (ParserNode) getIndividualProperty(OntologyFactory.getPropertyParserNodeHasPrecedenceNode());
	}

	public String toString() {
		return super.toString() + " | ParserNode: hasName: " + hasName()
				+ ", hasAttributeNode: " + hasAttributeNode();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Set<ParserFixedParameter> hasFixedParameter() {
		return (Set<ParserFixedParameter>) listIndividuals(OntologyFactory.getPropertyParserNodeHassFixedParameter());
	}
}
