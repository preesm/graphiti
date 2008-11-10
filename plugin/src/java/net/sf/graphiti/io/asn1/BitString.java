/**
 * 
 */
package net.sf.graphiti.io.asn1;

/**
 * @author Matthieu Wipliez
 * 
 */
public class BitString extends Element {
	
	private Number number;

	public BitString(String name, Number number) {
		super(name);
		this.number = number;
	}

}
