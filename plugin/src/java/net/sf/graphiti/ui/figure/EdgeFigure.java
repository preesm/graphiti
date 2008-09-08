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

import java.util.HashMap;
import java.util.List;

import net.sf.graphiti.model.Edge;
import net.sf.graphiti.model.Parameter;

import org.eclipse.draw2d.BendpointConnectionRouter;
import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.DelegatingLayout;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.PolylineConnection;
import org.eclipse.draw2d.PolylineDecoration;
import org.eclipse.draw2d.RoutingAnimator;
import org.eclipse.swt.graphics.Color;

/**
 * This class provides drawing for a dependency.
 * 
 * @author Samuel Beaussier
 * @author Nicolas Isch
 * @author Matthieu Wipliez
 */
public class EdgeFigure extends PolylineConnection {

	static public final int basicWidth = 1;

	static public final Color color = ColorConstants.black;

	static public final int selectWidth = 2;

	private HashMap<Parameter, Label> properties = new HashMap<Parameter, Label>();

	/**
	 * Creates the Figure associated to the connection
	 * 
	 * @param edge
	 *            The Edge model associated with this figure.
	 */
	public EdgeFigure(Edge edge) {
		properties = new HashMap<Parameter, Label>();

		// Sets Layout Manager
		this.setLayoutManager(new DelegatingLayout());
		if (edge != null) {
			if (edge.getValue("type") != null) {
				List<Parameter> parameters = edge.getDocumentConfiguration()
						.getEdgeParameters((String) edge.getValue("type"));
				for (Parameter parameter : parameters) {
					if (parameter.getPosition() != null) {
						Object value = edge.getValue(parameter.getName());
						if (value != null) {
							Label parameterLabel = new Label(value.toString());
							parameterLabel.setOpaque(true);
							Object locator = new PropertyLocator(this,
									parameter.getPosition());
							add(parameterLabel, locator);
							properties.put(parameter, parameterLabel);
						}
					}
				}
			}
		}

		setTargetDecoration(new PolylineDecoration());
		setLineWidth(1);
		setConnectionRouter(new BendpointConnectionRouter());
		addRoutingListener(RoutingAnimator.getDefault());
		this.setValid(true);

		// List<Bendpoint> bendPoints = new ArrayList<Bendpoint>();
		// RelativeBendpoint b1 = new RelativeBendpoint(this);
		// int n = (int) (50.0 * Math.random());
		// b1.setRelativeDimensions(new Dimension(0, 0), new Dimension(n, 0));
		// b1.setWeight(0.5f);
		// bendPoints.add(b1);
		// setRoutingConstraint(bendPoints);
	}

}
