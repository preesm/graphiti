package net.sf.graphiti.ontology.nodes;

import java.util.Set;

/**
 * This class provides a parameter parser node.
 * 
 * @author Jonathan Piat
 * @author Matthieu Wipliez
 * 
 */
public interface ParserParameterNode extends ParserNode {

	public boolean isReference();

	public Set<ParserNode> isReferenceTo();

}
