package net.sf.graphiti.ontology.parameters;

import net.sf.graphiti.ontology.OntologyNode;
import net.sf.graphiti.ontology.enums.DataTypes;
import net.sf.graphiti.ontology.enums.Position;
import net.sf.graphiti.ontology.types.Type;

/**
 * This class provides a parameter.
 * 
 * @author Jonathan Piat
 * @author Matthieu Wipliez
 * 
 */
public interface Parameter extends OntologyNode {

	public Type appliesTo();

	public String hasName();

	public Position hasPosition();

	public DataTypes hasValueType();

}
