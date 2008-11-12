package net.sf.graphiti.io.asn1;

public class Constraint {

	public enum ConstraintType {
		Size, Value;
	}

	private BinaryNumber lower;
	
	private int size;

	private String string;
	
	private ConstraintType type;

	private BinaryNumber upper;

	private BinaryNumber value;

	public Constraint(ConstraintType type) {
		this.type = type;
	}
	
	public ConstraintType getConstraintType() {
		return type;
	}
	
	public BinaryNumber getValue() {
		return value;
	}
	
	public void setLowerBound(BinaryNumber lower) {
		this.lower = lower;
	}
	
	public void setSize(int size) {
		this.size = size;
	}

	public void setString(String string) {
		this.string = string;
	}

	public void setUpperBound(BinaryNumber upper) {
		this.upper = upper;
	}

	public void setValue(BinaryNumber value) {
		this.value = value;
	}

}
