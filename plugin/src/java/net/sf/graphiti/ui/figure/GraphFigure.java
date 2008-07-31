/*
 * Copyright (c) 2008, IETR/INSA of Rennes
 * All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 * 
 *   * Redistributions of source code must retain the above copyright notice,
 *     this list of conditions and the following disclaimer.
 *   * Redistributions in binary form must reproduce the above copyright notice,
 *     this list of conditions and the following disclaimer in the documentation
 *     and/or other materials provided with the distribution.
 *   * Neither the name of the IETR/INSA of Rennes nor the names of its
 *     contributors may be used to endorse or promote products derived from this
 *     software without specific prior written permission.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT,
 * STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY
 * WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF
 * SUCH DAMAGE.
 */
package net.sf.graphiti.ui.figure;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.LineBorder;
import org.eclipse.draw2d.XYLayout;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Insets;
import org.eclipse.draw2d.geometry.Rectangle;

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
		setBorder(new LineBorder(1));
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
