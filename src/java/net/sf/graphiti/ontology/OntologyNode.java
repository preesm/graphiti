/**
 * 
 */
package net.sf.graphiti.ontology;

/**
 * @author mwipliez
 * 
 */
public interface OntologyNode {

	/**
	 * Returns true if this ontology node class is <code>clasz</code>. Note
	 * that this method also tests parent classes of this node against
	 * <code>clasz</code>.
	 * 
	 * @param clasz
	 *            An ontology class URI.
	 * @return True if this ontology node class is <code>clasz</code>.
	 */
	public boolean hasOntClass(String clasz);

}
