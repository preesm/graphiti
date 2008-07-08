package net.sf.graphiti.ontology.enums.impl;

import net.sf.graphiti.ontology.OntologyNodeImpl;
import net.sf.graphiti.ontology.enums.Colors;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.swt.graphics.Color;

import com.hp.hpl.jena.ontology.Individual;

public class ColorsImpl extends OntologyNodeImpl implements Colors {

	public static final Color[] colors = { ColorConstants.blue,
			ColorConstants.red, new Color(null, 251, 115, 203),
			new Color(null, 162, 162, 162) };

	public ColorsImpl(Individual individual) {
		super(individual);
	}

	@Override
	public Color getColor() {
		if (this.getIndividualName().equals("blue")) {
			return colors[0];
		} else if (this.getIndividualName().equals("red")) {
			return colors[1];
		} else if (this.getIndividualName().equals("pink")) {
			return colors[2];
		} else {
			return colors[3];
		}
	}

}
