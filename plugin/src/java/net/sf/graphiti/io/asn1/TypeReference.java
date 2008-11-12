/**
 * 
 */
package net.sf.graphiti.io.asn1;

/**
 * @author Matthieu Wipliez
 * 
 */
public class TypeReference extends Element {
	
	private ConstraintList constraints;
	
	private String reference;

	public TypeReference(String name) {
		super(name);
	}
	
	public void setReference(String reference) {
		this.reference = reference;
	}

	public void setConstraints(ConstraintList constraints) {
		this.constraints = constraints;
	}

}
