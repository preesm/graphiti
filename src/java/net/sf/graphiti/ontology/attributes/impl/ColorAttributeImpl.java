/**
 * 
 */
package net.sf.graphiti.ontology.attributes.impl;

import net.sf.graphiti.ontology.OntologyFactory;
import net.sf.graphiti.ontology.attributes.ColorAttribute;
import net.sf.graphiti.ontology.enums.Colors;

import com.hp.hpl.jena.ontology.Individual;

/**
 * @author mwipliez
 * 
 */
public class ColorAttributeImpl extends FigureAttributeImpl implements
		ColorAttribute {

	public ColorAttributeImpl(Individual individual) {
		super(individual);
	}

	@Override
	public Colors hasColor() {
		return (Colors) getIndividualProperty(OntologyFactory
				.getPropertyColorAttributeHasColor());
	}

}
