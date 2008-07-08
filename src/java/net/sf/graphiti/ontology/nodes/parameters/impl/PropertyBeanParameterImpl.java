/**
 * 
 */
package net.sf.graphiti.ontology.nodes.parameters.impl;

import net.sf.graphiti.ontology.OntologyFactory;
import net.sf.graphiti.ontology.nodes.impl.ParserParameterNodeImpl;
import net.sf.graphiti.ontology.nodes.parameters.PropertyBeanParameter;
import net.sf.graphiti.ontology.parameters.Parameter;

import com.hp.hpl.jena.ontology.Individual;

/**
 * @author mwipliez
 * 
 */
public class PropertyBeanParameterImpl extends ParserParameterNodeImpl
		implements PropertyBeanParameter {

	public PropertyBeanParameterImpl(Individual individual) {
		super(individual);
	}

	@Override
	public Parameter hasParameter() {
		return (Parameter) getIndividualProperty(OntologyFactory
				.getPropertyPropertyBeanParameterHasParameter());
	}

	public String toString() {
		return super.toString() + " | PropertyBeanParameter: hasParameter: "
				+ hasParameter().hasName();
	}

}
