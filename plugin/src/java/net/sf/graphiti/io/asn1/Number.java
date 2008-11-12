package net.sf.graphiti.io.asn1;

import java.math.BigInteger;
import java.util.BitSet;

public class Number {
	
	private BitSet bits;

	private int nbits;
	
	/**
	 * Serial ID.
	 */
	private static final long serialVersionUID = 1L;

	public Number(int nbits) {
		this.nbits = nbits;
		bits = new BitSet(nbits);
	}
	
	public int intValue() {
		BigInteger number = new BigInteger(toString(), 2);
		return number.intValue();
	}
	
	public void setValue(String value, int radix) {
		BigInteger number = new BigInteger(value, radix);
		for (int i = 0; i < nbits; i++) {
			boolean n = number.testBit(i);
			bits.set(i, n);
		}
	}
	
	@Override
	public String toString() {
		String res = "";
		for (int i = nbits - 1; i >= 0; i--) {
			if (bits.get(i)) {
				res += "1";
			} else {
				res += "0";
			}
		}
		
		return res;
	}
	
}
