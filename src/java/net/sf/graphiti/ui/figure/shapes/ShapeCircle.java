package net.sf.graphiti.ui.figure.shapes;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.Ellipse;
import org.eclipse.draw2d.EllipseAnchor;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.XYLayout;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.graphics.Color;

public class ShapeCircle extends Ellipse implements IShape {
	
	private GradientPattern fill;

	private Label labelName;

	public ShapeCircle() {
		super();

		this.setLayoutManager(new XYLayout());
		labelName = new Label();
		labelName.setForegroundColor(ColorConstants.black);
		this.add(labelName);
		this.setFill(true);
	}

	@Override
	public ConnectionAnchor getConnectionAnchor() {
		return new EllipseAnchor(this);
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
		setSize(dim);
	}

	@Override
	public void setName(String name) {
		labelName.setText(name);
		int hDecal = 5;
		int wDecal = (name.length() * 5) / 2;
		Rectangle bounds = this.getParent().getBounds();
		this.setConstraint(labelName, new Rectangle(
				(bounds.width / 2) - wDecal, (bounds.height / 2) - hDecal, -1,
				-1));
	}

}
