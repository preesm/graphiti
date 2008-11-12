package net.sf.graphiti.io.asn1;

public class Constraint {

	public enum ConstraintType {
		Size, Value;
	}

	private Number lower;
	
	private int size;

	private String string;
	
	private ConstraintType type;

	private Number upper;

	private Number value;

	public Constraint(ConstraintType type) {
		this.type = type;
	}
	
	public ConstraintType getConstraintType() {
		return type;
	}
	
	public Number getValue() {
		return value;
	}
	
	public void setLowerBound(Number lower) {
		this.lower = lower;
	}
	
	public void setSize(int size) {
		this.size = size;
	}

	public void setString(String string) {
		this.string = string;
	}

	public void setUpperBound(Number upper) {
		this.upper = upper;
	}

	public void setValue(Number value) {
		this.value = value;
	}

}
