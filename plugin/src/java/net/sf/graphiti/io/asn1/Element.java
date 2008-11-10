/**
 * 
 */
package net.sf.graphiti.io.asn1;


/**
 * @author Matthieu Wipliez
 * 
 */
public class Element {

	private String name;

	public Element(String name) {
		if (name.isEmpty()) {
			this.name = "<empty>";
		} else {
			this.name = name;
		}
	}

	public String toString() {
		return name;
	}

}
