/**
 * 
 */
package net.sf.graphiti.ontology.attributes.impl;

import net.sf.graphiti.ontology.OntologyFactory;
import net.sf.graphiti.ontology.attributes.ShapeAttribute;
import net.sf.graphiti.ontology.enums.Shapes;

import com.hp.hpl.jena.ontology.Individual;

/**
 * @author mwipliez
 * 
 */
public class ShapeAttributeImpl extends FigureAttributeImpl implements
		ShapeAttribute {

	public ShapeAttributeImpl(Individual individual) {
		super(individual);
	}

	@Override
	public Shapes hasShape() {
		return (Shapes) getIndividualProperty(OntologyFactory
				.getPropertyShapeAttributeHasShape());
	}

}
