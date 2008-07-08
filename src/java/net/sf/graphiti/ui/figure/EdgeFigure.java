package net.sf.graphiti.ui.figure;

import java.util.HashMap;
import java.util.List;

import net.sf.graphiti.model.Edge;
import net.sf.graphiti.model.ParameterProperty;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.DelegatingLayout;
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

	protected HashMap<ParameterProperty, PropertyTag> properties = new HashMap<ParameterProperty, PropertyTag>();

	/**
	 * Creates the Figure associated to the connection
	 * @param dep The Edge model associated with this figure.
	 */
	public EdgeFigure(Edge dep) {
		// Sets Layout Manager
		this.setLayoutManager(new DelegatingLayout());
		if (dep.getValue("type") != null) {
			List<ParameterProperty> properties = dep.getParentDocument()
					.getDocumentConfiguration().getEdgeParameters(
							(String) dep.getValue("type"));
			for (ParameterProperty property : properties) {
				if (property.getPosition() != null) {
					PropertyTag propertyLabel = new PropertyTag((String) dep
							.getValue(property.getName()));
					this.add(propertyLabel, new PropertyLocator(this, property
							.getPosition()));
					this.properties.put(property, propertyLabel);
				}
			}
		}
		setTargetDecoration(new PolylineDecoration());
		setLineWidth(1);
		addRoutingListener(RoutingAnimator.getDefault());
		this.setValid(true);
	}

}
