package net.sf.graphiti.ontology.nodes.parameters;

import net.sf.graphiti.ontology.nodes.ParserParameterNode;
import net.sf.graphiti.ontology.parameters.Parameter;

/**
 * This class provides an edge parameter node.
 * 
 * @author Jonathan Piat
 * @author Matthieu Wipliez
 * 
 */
public interface PropertyBeanParameter extends ParserParameterNode {

	public Parameter hasParameter();
}
