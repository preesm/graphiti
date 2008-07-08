package net.sf.graphiti.model;

public class SkipDOMNode extends DOMNode{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -37529046595717470L;
	private DOMNode trueNode ;
	
	public SkipDOMNode(DOMNode trueNode){
		this.trueNode = trueNode ;
	}
	
	public DOMNode getTrueNode(){
		return trueNode ;
	}

}
