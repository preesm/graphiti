package net.sf.graphiti.ui.figure;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.LineBorder;
import org.eclipse.draw2d.SchemeBorder;
import org.eclipse.draw2d.XYLayout;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Insets;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.graphics.Color;

/**
 * Gives methods to draw a Graph.
 * 
 * @author Samuel Beaussier
 * @author Nicolas Isch
 */
public class GraphFigure extends Figure {

	private Label labelMulti;

	private Label labelName;

	private Label labelToolTip;

	/**
	 * Creates a figure that represents a Graph.
	 */
	public GraphFigure() {
		setBorder(new GraphBorder());
		setLayoutManager(new XYLayout());

		labelMulti = new Label();
		labelName = new Label();
		labelToolTip = new Label();

		// Tool tip
		labelToolTip.setForegroundColor(ColorConstants.black);
		// setToolTip(labelToolTip);

		// Name is placed top-left
		labelName.setForegroundColor(ColorConstants.black);
		add(labelName, new Rectangle(0, 0, -1, -1));

		// Number of repeats is top-right
		labelMulti.setForegroundColor(ColorConstants.black);
		add(labelMulti);

		// setOpaque(true);
	}

	public Dimension getPreferredSize(int w, int h) {
		Dimension prefSize = super.getPreferredSize(w, h);
		Dimension defaultSize = new Dimension(100, 50);
		prefSize.union(defaultSize);
		return prefSize;
	}

	/**
	 * @see org.eclipse.draw2d.Figure#paintFigure(Graphics)
	 */
	protected void paintFigure(Graphics graphics) {
		Rectangle rect = getBounds().getCopy();
		rect.crop(new Insets(2, 0, 2, 0));
		graphics.fillRectangle(rect);
	}

	/**
	 * Sets the border of this graph figure, which depends on the fact that it
	 * has children or not.
	 * 
	 * @param hasChildren
	 *            True if the model represented by this figure has children,
	 *            false otherwise.
	 */
	public void setBorder(boolean hasChildren) {
		if (hasChildren) {
			SchemeBorder border = new SchemeBorder(new SchemeBorder.Scheme(
					new Color[] { new Color(null, 128, 128, 128) },
					new Color[] { new Color(null, 128, 128, 128),
							new Color(null, 128, 128, 128) }));
			setBorder(border);
		} else {
			setBorder(new LineBorder(1));
		}
	}

	/**
	 * @param text
	 *            the text to set
	 */
	public void setName(String text) {
		if (text == null) {
			text = "";
		}
		labelName.setText(text);
		labelToolTip.setText(text);
	}

	/**
	 * Set the number of repetitions
	 * 
	 * @param i
	 */
	public void setNbRepeat(int i) {
		labelMulti.setText("x" + (i + 1));
		Dimension size = new Dimension(getBounds().width, getBounds().height);
		int nbRepeatSize = labelMulti.getTextBounds().width;
		setConstraint(labelMulti, new Rectangle(size.width - nbRepeatSize - 5,
				0, -1, -1));
	}

	@Override
	protected boolean useLocalCoordinates() {
		return true;
	}
}
