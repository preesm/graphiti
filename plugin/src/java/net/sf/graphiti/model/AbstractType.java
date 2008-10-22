package net.sf.graphiti.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class AbstractType implements Comparable<AbstractType> {

	private Map<String, Object> attributes;

	private String name;

	private Map<String, Parameter> parameters;

	protected AbstractType(String name) {
		this.name = name;
	}

	@Override
	public int compareTo(AbstractType type) {
		return name.compareTo(type.name);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof AbstractType) {
			AbstractType type = (AbstractType) obj;
			return name.equals(type.name);
		} else {
			return false;
		}
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
