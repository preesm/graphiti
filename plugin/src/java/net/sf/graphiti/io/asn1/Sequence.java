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
public class Sequence extends Production {
	
	private List<Element> elements;
	
	public Sequence(String name) {
		super(name);
		elements = new ArrayList<Element>();
	}
	
	public void addElement(Element element) {
		elements.add(element);
	}
	
	public String toString() {
		return getName() + " ::= " + elements;
	}

}
