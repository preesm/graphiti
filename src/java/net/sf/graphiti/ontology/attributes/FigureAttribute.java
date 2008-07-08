package net.sf.graphiti.ontology.attributes;

import net.sf.graphiti.ontology.OntologyNode;
import net.sf.graphiti.ontology.types.Type;

/**
 * This class provides a figure attribute.
 * 
 * @author Jonathan Piat
 * @author Matthieu Wipliez
 * 
 */
public interface FigureAttribute extends OntologyNode {

	/**
	 * Returns the type this figure attribute applies to.
	 * 
	 * @return The type this figure attribute applies to.
	 */
	public Type appliesTo();

	public String hasType();
}
