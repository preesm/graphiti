package net.sf.graphiti.io.csd.test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class PrimitiveFields implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {
		PrimitiveFields obj = new PrimitiveFields();
		obj.aByteField = 33;
		obj.aCharField = 'X';
		obj.aDoubleField = Math.PI;
		obj.aFloatField = (float) Math.PI;
		obj.aIntField = 42;
		obj.aLongField = 1 << 32;
		obj.aShortField = 16384;
		obj.aZBooleanField = true;

		try {
			new ObjectOutputStream(new FileOutputStream(args[0]
					+ File.separator + obj.getClass().getSimpleName()))
					.writeObject(obj);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public byte aByteField;

	public char aCharField;

	public double aDoubleField;

	public float aFloatField;

	public int aIntField;

	public long aLongField;

	public short aShortField;

	public boolean aZBooleanField;

}
