package net.sf.graphiti.model;

import net.sf.graphiti.ontology.parameters.Parameter;

public class ParameterProperty {

	protected String name;
	protected ParameterPosition parameterPosition;

	public ParameterProperty(Parameter ontParam) {
		name = ontParam.hasName();
		if (ontParam.hasPosition() != null) {
			parameterPosition = ParameterPosition
					.createFromOntPosition(ontParam.hasPosition());
		} else {
			parameterPosition = null;
		}
	}

	public ParameterProperty(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public ParameterPosition getPosition() {
		return parameterPosition;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPosition(String position) {
		parameterPosition = ParameterPosition.createFromOntString(position);
	}

}
