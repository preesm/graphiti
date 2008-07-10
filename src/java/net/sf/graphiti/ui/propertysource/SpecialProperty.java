/**
 * 
 */
package net.sf.graphiti.ui.propertysource;

/**
 * @author Matthieu Wipliez
 * 
 */
public class SpecialProperty implements IPropertyType {

	public boolean isAttribute() {
		return false;
	}

	public boolean isParameter() {
		return false;
	}

	public String getId() {
		return "special";
	}
}
