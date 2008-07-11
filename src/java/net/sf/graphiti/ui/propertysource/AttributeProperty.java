/**
 * 
 */
package net.sf.graphiti.ui.propertysource;

/**
 * @author Matthieu Wipliez
 * 
 */
public class AttributeProperty implements IPropertyType {

	private String id;

	public AttributeProperty(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public boolean isAttribute() {
		return true;
	}

	public boolean isParameter() {
		return false;
	}
}
