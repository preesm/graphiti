/**
 * 
 */
package net.sf.graphiti.ontology.nodes.parameters.impl;

import net.sf.graphiti.ontology.OntologyFactory;
import net.sf.graphiti.ontology.nodes.parameters.ConstantParameter;

import com.hp.hpl.jena.ontology.Individual;

/**
 * @author mwipliez
 * 
 */
public class ConstantParameterImpl extends PropertyBeanParameterImpl implements
		ConstantParameter {

	public ConstantParameterImpl(Individual individual) {
		super(individual);
	}

	@Override
	public String hasValue() {
		return getStringProperty(OntologyFactory
				.getPropertyConstantParameterHasValue());
	}

	public String toString() {
		return super.toString() + " | ConstantParameter: hasValue: "
				+ hasValue();
	}

}
