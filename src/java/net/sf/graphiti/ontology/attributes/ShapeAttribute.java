package net.sf.graphiti.ontology.attributes;

import net.sf.graphiti.ontology.enums.Shapes;

/**
 * This class provides a shape attribute.
 * 
 * @author Jonathan Piat
 * @author Matthieu Wipliez
 * 
 */
public interface ShapeAttribute extends FigureAttribute {

	public Shapes hasShape();

}
