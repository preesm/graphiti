package net.sf.graphiti.ui.figure;

import net.sf.graphiti.model.DocumentConfiguration;
import net.sf.graphiti.model.Vertex;
import net.sf.graphiti.ontology.enums.Shapes;
import net.sf.graphiti.ui.figure.shapes.IShape;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.XYLayout;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.graphics.Color;

/**
 * This class provides a figure for vertices.
 * 
 * @author Samuel Beaussier
 * @author Nicolas Isch
 * @author Matthieu Wipliez
 */
public class VertexFigure extends Figure {

	private ConnectionAnchor anchor;

	private Label labelName;

	private Label labelToolTip;

	private IFigure shape;

	/**
	 * Creates a new VertexFigure using the given document configuration and
	 * vertex type.
	 * 
	 * @param config
	 * @param vertexType
	 */
	public VertexFigure(DocumentConfiguration config, String vertexType) {
		// Get bounds
		if (config.getVertexAttribute(vertexType, "height") != null
				&& config.getVertexAttribute(vertexType, "width") != null) {
			int width = (Integer) config
					.getVertexAttribute(vertexType, "width");
			int height = (Integer) config.getVertexAttribute(vertexType,
					"height");
			bounds = new Rectangle(0, 0, width, height);
		} else {
			bounds = new Rectangle(0, 0, 50, 50);
		}

		// TODO: what to do? height, width or parameter_size?
		// Rectangle bounds = (Rectangle)
		// vertex.getValue(Vertex.PARAMETER_SIZE);

		// Get color
		Color color = ColorConstants.gray;
		if (config.getVertexAttribute(vertexType, Vertex.ATTRIBUTE_COLOR) != null) {
			color = (Color) config.getVertexAttribute(vertexType,
					Vertex.ATTRIBUTE_COLOR);
		}

		// Get shape
		if (config.getVertexAttribute(vertexType, Vertex.ATTRIBUTE_SHAPE) != null) {
			shape = ((Shapes) config.getVertexAttribute(vertexType,
					Vertex.ATTRIBUTE_SHAPE)).getShape();
			((IShape) shape).setColor(color);
		}

		// Creates labels
		labelName = new Label();
		labelToolTip = new Label();

		// Sets Layout Manager
		XYLayout layout = new XYLayout();
		setLayoutManager(layout);

		// Sets the tool tip and adds it so that it is visible
		labelToolTip.setForegroundColor(ColorConstants.black);
		setToolTip(labelToolTip);
		add(labelToolTip);

		// Adds label name
		labelName.setForegroundColor(ColorConstants.black);
		add(labelName);

		setBorder(null);

		if (shape != null) {
			((IShape) shape).setDimension(new Dimension(bounds.width,
					bounds.height));
			this.add(this.shape, new Rectangle(0, 0, bounds.width,
					bounds.height));
			anchor = ((IShape) this.shape).getConnectionAnchor();
		}

	}

	/**
	 * 
	 * @return anchor
	 */
	public ConnectionAnchor getConnectionAnchor() {
		return anchor;
	}

	/**
	 * Returns the size of this VertexFigure.
	 * 
	 * @return The size of this VertexFigure.
	 */
	public Dimension getPreferredSize(int w, int h) {
		Rectangle rectangle = labelName.getTextBounds();
		int x = rectangle.width + 10;
		int y = rectangle.height + 2;
		Dimension prefSize = new Dimension(x, y);
		Dimension defaultSize = new Dimension(50, 50);
		prefSize.union(defaultSize);
		return prefSize;
	}

	/**
	 * The name to set
	 * 
	 * @param text
	 */
	public void setName(String text) {
		if (shape != null) {
			((IShape) this.shape).setName(text);
		} else {
			labelName.setText(text);
			this.setConstraint(labelName, new Rectangle(
					this.getBounds().height / 2, this.getBounds().width / 2,
					-1, -1));
		}
		// labelToolTip.setText(text);
		// setConstraint(labelToolTip, new Rectangle(0, 0, -1, -1));
	}

	@Override
	protected boolean useLocalCoordinates() {
		return true;
	}
}
