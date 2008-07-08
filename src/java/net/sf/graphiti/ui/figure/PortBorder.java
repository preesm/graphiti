package net.sf.graphiti.ui.figure;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.LineBorder;
import org.eclipse.draw2d.geometry.Insets;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.Rectangle;

/**
 * This class provides a border for ports, extending LineBorder with the ability
 * to draw connectors.
 * 
 * @author Samuel Beaussier
 * @author Nicolas Isch
 * @author Matthieu Wipliez
 * 
 */
public class PortBorder extends LineBorder {

	private static PointList bottomConnector = new PointList();

	private static PointList connector = new PointList();

	private static Insets insets = new Insets(0, 0, 0, 0);

	static {
		connector.addPoint(0, 2);
		connector.addPoint(0, -1);
		connector.addPoint(1, -2);
		connector.addPoint(5, -2);
		connector.addPoint(5, 1);
		connector.addPoint(1, 1);

		bottomConnector.addPoint(-1, 2);
		bottomConnector.addPoint(-1, -1);
		bottomConnector.addPoint(-2, -2);
		bottomConnector.addPoint(-6, -2);
		bottomConnector.addPoint(-6, 1);
		bottomConnector.addPoint(-2, 1);
	}

	/**
	 * Creates a new PortBorder.
	 * @param direction The direction of the Port (input, output)
	 */
	public PortBorder(String direction) {
	}

	/**
	 * Draw the connectors on the rectangle rect.
	 * 
	 * @param graphics
	 *            the graphics
	 * @param rect
	 *            the rectangle reference
	 */
	private void drawConnectors(Graphics graphics, Rectangle rect) {
		int x1 = rect.x, y1, bottom = x1 + rect.width;
		graphics.setBackgroundColor(ColorConstants.blue);

		y1 = rect.y + rect.height / 2;

		// Draw the connectors
		graphics.setForegroundColor(ColorConstants.black);
		connector.translate(x1, y1);
		graphics.fillPolygon(connector);
		graphics.drawPolygon(connector);
		connector.translate(-x1, -y1);

		graphics.setForegroundColor(ColorConstants.black);
		bottomConnector.translate(bottom, y1);
		graphics.fillPolygon(bottomConnector);
		graphics.drawPolygon(bottomConnector);
		bottomConnector.translate(-bottom, -y1);
	}

	/**
	 * Returns insets of figure
	 * 
	 * @return insets
	 */
	public Insets getInsets(IFigure figure) {
		return insets;
	}

	public void paint(IFigure figure, Graphics graphics, Insets insets) {
		super.paint(figure, graphics, insets);
		drawConnectors(graphics, figure.getBounds().getCropped(insets));
	}
}
