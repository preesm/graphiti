package net.sf.graphiti.ontology.nodes;

import java.util.Set;

import net.sf.graphiti.ontology.OntologyNode;
import net.sf.graphiti.ontology.nodes.parameters.ParserFixedParameter;

/**
 * This class provides a parser node.
 * 
 * @author Jonathan Piat
 * @author Matthieu Wipliez
 * 
 */
public interface ParserNode extends OntologyNode {

	public Set<ParserParameterNode> hasAttributeNode();

	public Set<ParserNode> hasChildrenNode();

	public String hasName();
	
	public ParserNode hasPrecedenceNode();
	
	public Set<ParserFixedParameter> hasFixedParameter();

}
