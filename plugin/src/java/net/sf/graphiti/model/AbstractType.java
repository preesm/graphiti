package net.sf.graphiti.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class AbstractType {

	private Map<String, Object> attributes;

	private String name;

	private Map<String, Parameter> parameters;

	protected AbstractType(String name) {
		this.name = name;
	}

	public Object getAttribute(String name) {
		return attributes.get(name);
	}

	public String getName() {
		return name;
	}

	public Parameter getParameter(String name) {
		return parameters.get(name);
	}

	public List<Parameter> getParameters() {
		return new ArrayList<Parameter>(parameters.values());
	}

}
