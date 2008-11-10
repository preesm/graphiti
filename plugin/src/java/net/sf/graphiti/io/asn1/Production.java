/**
 * 
 */
package net.sf.graphiti.io.asn1;

/**
 * @author Matthieu Wipliez
 *
 */
public abstract class Production {
	
	private String name;
	
	public Production(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

	public String toString() {
		return name;
	}
}
