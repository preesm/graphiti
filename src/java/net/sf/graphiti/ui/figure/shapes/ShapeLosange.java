package net.sf.graphiti.ui.figure.shapes;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.Polygon;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.graphics.Color;

public class ShapeLosange extends Polygon implements IShape {
	
	private GradientPattern fill;

	private Label labelName;

	public ShapeLosange() {
		super();

		labelName = new Label();
		labelName.setForegroundColor(ColorConstants.black);
		this.add(labelName);
	}

	@Override
	public ConnectionAnchor getConnectionAnchor() {
		return new PolygonConnectionAnchor(this);
	}
	
	public void paintFigure(Graphics graphics) {
		if (fill == null) {
			fill = new GradientPattern(getBackgroundColor());
		}
		fill.setPattern(getBounds(), graphics);
		super.paintFigure(graphics);
		fill.restorePattern(graphics);
	}

	@Override
	public void setColor(Color color) {
		this.setBackgroundColor(color);
	}

	@Override
	public void setDimension(Dimension dim) {
		this.setStart(new Point(dim.width / 2, 0));
		this.addPoint(new Point(dim.width, dim.height / 2));
		this.addPoint(new Point(dim.width / 2, dim.height));
		this.addPoint(new Point(0, dim.height / 2));
		this.addPoint(new Point(dim.width / 2, 0));
		this.setFill(true);
	}

	@Override
	public void setName(String name) {
		labelName.setText(name);
		this.setConstraint(labelName,
				new Rectangle(this.getBounds().height / 2,
						this.getBounds().width / 2, -1, -1));
	}
}
