package net.sf.graphiti.ui.figure;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.LineBorder;

public class PropertyTag extends Label {

	/**
	 * Creates a new StartTag
	 * 
	 * @param name
	 *            the text to display in this StartTag
	 */
	public PropertyTag(String name) {
		setText(name);
		setBorder(new LineBorder(1));
		setForegroundColor(ColorConstants.black);
		setBackgroundColor(ColorConstants.green);
		setOpaque(true);
	}

}
