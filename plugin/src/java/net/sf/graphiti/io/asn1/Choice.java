/**
 * 
 */
package net.sf.graphiti.io.asn1;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Matthieu Wipliez
 *
 */
public class Choice extends Production {
	
	private List<Element> alternatives;
	
	public Choice(String name) {
		super(name);
		alternatives = new ArrayList<Element>();
	}

	public void addAlternative(Element alternative) {
		alternatives.add(alternative);
	}
	
	public String toString() {
		return getName() + " ::= " + alternatives;
	}
	
}
