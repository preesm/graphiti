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
