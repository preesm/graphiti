package net.sf.graphiti.ui.figure.shapes;

import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.swt.graphics.Color;

/**
 * This interface defines a IShape. A shape has a name and a connection anchor.
 * 
 * @author Jonathan Piat
 * @author Matthieu Wipliez
 * 
 */
public interface IShape {

	/**
	 * Returns the connection anchor associated with this shape. The connection
	 * anchor may be a predefined eclipse implementation or a custom one.
	 * 
	 * @return A ConnectionAnchor object.
	 */
	public ConnectionAnchor getConnectionAnchor();

	public void setColor(Color color);

	public void setDimension(Dimension dim);

	/**
	 * Sets the name of this shape.
	 * 
	 * @param name
	 *            The new name of this shape.
	 */
	public void setName(String name);

}
