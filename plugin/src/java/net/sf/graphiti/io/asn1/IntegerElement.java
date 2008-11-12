/**
 * 
 */
package net.sf.graphiti.io.asn1;

/**
 * @author Matthieu Wipliez
 * 
 */
public class IntegerElement extends Element {
	
	private ConstraintList constraints;

	public IntegerElement(String name) {
		super(name);
	}
	
	public void setConstraints(ConstraintList constraints) {
		this.constraints = constraints;
	}

}
