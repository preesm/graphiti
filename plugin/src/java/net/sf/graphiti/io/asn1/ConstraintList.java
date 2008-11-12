package net.sf.graphiti.io.asn1;

import java.util.ArrayList;
import java.util.NoSuchElementException;

import net.sf.graphiti.io.asn1.Constraint.ConstraintType;

public class ConstraintList extends ArrayList<Constraint> {

	/**
	 * Serial ID.
	 */
	private static final long serialVersionUID = 1L;

	public ConstraintList() {
		super();
	}

	public Constraint getFirstSizeConstraint() {
		for (Constraint constraint : this) {
			if (constraint.getConstraintType() == ConstraintType.Size) {
				return constraint;
			}
		}
		
		return null;
	}

	public Constraint getFirstValueConstraint() {
		for (Constraint constraint : this) {
			if (constraint.getConstraintType() == ConstraintType.Value) {
				return constraint;
			}
		}
		
		return null;
	}

}
