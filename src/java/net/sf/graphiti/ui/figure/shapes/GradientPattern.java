/**
 * 
 */
package net.sf.graphiti.ui.figure.shapes;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Pattern;

/**
 * @author mwipliez
 * 
 */
public class GradientPattern {

	private Color backgroundColor;

	private Color fg;

	private Pattern pattern;

	/**
	 * Creates a new gradient pattern using the given backgound color.
	 * 
	 * @param backgroundColor
	 *            The pattern background color.
	 */
	public GradientPattern(Color backgroundColor) {
		this.backgroundColor = backgroundColor;
		fg = ColorConstants.lightGray;
	}

	public void restorePattern(Graphics graphics) {
		graphics.popState();
	}

	/**
	 * Sets the gradient pattern on the given graphics, using
	 * 
	 * @param bounds
	 * @param graphics
	 */
	public void setPattern(Rectangle bounds, Graphics graphics) {
		if (pattern == null) {
			pattern = new Pattern(fg.getDevice(), 0, 0, bounds.width,
					bounds.height, backgroundColor, 192, fg, 192);
		}

		graphics.pushState();
		graphics.setBackgroundPattern(pattern);
	}

}
