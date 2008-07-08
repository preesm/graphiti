package net.sf.graphiti.ontology.types;

import java.util.Set;

import net.sf.graphiti.ontology.OntologyNode;
import net.sf.graphiti.ontology.attributes.FigureAttribute;
import net.sf.graphiti.ontology.parameters.Parameter;

/**
 * This class provides a type.
 * 
 * @author Jonathan Piat
 * @author Matthieu Wipliez
 * 
 */
public interface Type extends OntologyNode {

	public Set<FigureAttribute> hasFigureAttributes();

	public Set<Parameter> hasParameters();

	public String hasStringRepresentation();
}
