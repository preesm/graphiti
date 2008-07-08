/**
 * 
 */
package net.sf.graphiti.ui.editparts;

import java.util.List;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.XYLayout;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.draw2d.graph.CompoundDirectedGraph;
import org.eclipse.draw2d.graph.CompoundDirectedGraphLayout;

/**
 * This class provides a basic graph layout.
 * 
 * @author Matthieu Wipliez
 */
public class GraphLayoutManager extends XYLayout {

	private GraphitiDocumentEditPart part;

	public GraphLayoutManager(GraphitiDocumentEditPart part) {
		this.part = part;
	}

	@SuppressWarnings("unchecked")
	protected Dimension calculatePreferredSize(IFigure container, int wHint,
			int hHint) {
		container.validate();
		List children = container.getChildren();
		Rectangle result = new Rectangle().setLocation(container
				.getClientArea().getLocation());
		for (int i = 0; i < children.size(); i++)
			result.union(((IFigure) children.get(i)).getBounds());
		result.resize(container.getInsets().getWidth(), container.getInsets()
				.getHeight());
		return result.getSize();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.draw2d.LayoutManager#layout(org.eclipse.draw2d.IFigure)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void layout(IFigure container) {
		try{
		CompoundDirectedGraph graph = new CompoundDirectedGraph();

		part.addNodes(graph.nodes);	
		part.addEdges(graph.edges);

		CompoundDirectedGraphLayout layout = new CompoundDirectedGraphLayout();
		layout.visit(graph);
		}catch(Exception e){
			e.printStackTrace();
		}
		part.updateFigures();

		// Rectangle r = container.getClientArea();
		// List children = container.getChildren();
		// for (int i = 0; i < children.size(); i++) {
		// IFigure child = (IFigure) children.get(i);
		// child.setBounds(r);
		// }
	}
}
