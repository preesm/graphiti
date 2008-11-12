/**
 * 
 */
package net.sf.graphiti.io.asn1;

/**
 * @author Matthieu Wipliez
 * 
 */
public class SequenceOf extends Production {

	private int size;

	private TypeReference type;

	public SequenceOf(String name) {
		super(name);
	}

	public void setType(TypeReference type) {
		this.type = type;
	}

	public void setSize(int size) {
		this.size = size;
	}

}
