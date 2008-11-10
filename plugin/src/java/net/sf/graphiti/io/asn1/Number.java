package net.sf.graphiti.io.asn1;

import java.lang.Integer;

public class Number {

	private int length;
	
	private int value;

	public Number(int length) {
		this.length = length;
	}
	
	public void setValue(String value, int radix) {
		this.value = Integer.parseInt(value, radix);
	}
	
}
