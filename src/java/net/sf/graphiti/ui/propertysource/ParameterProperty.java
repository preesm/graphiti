/**
 * 
 */
package net.sf.graphiti.ui.propertysource;

/**
 * @author Matthieu Wipliez
 * 
 */
public class ParameterProperty implements IPropertyType {

	private String id;

	public ParameterProperty(String id) {
		this.id = id;
	}

	public boolean isAttribute() {
		return false;
	}

	public boolean isParameter() {
		return true;
	}

	public String getId() {
		return id;
	}
}
