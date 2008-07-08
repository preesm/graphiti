package net.sf.graphiti.ontology.enums.impl;

import net.sf.graphiti.ontology.OntologyNodeImpl;
import net.sf.graphiti.ontology.enums.Shapes;
import net.sf.graphiti.ui.figure.shapes.ShapeCircle;
import net.sf.graphiti.ui.figure.shapes.ShapeHexagon;
import net.sf.graphiti.ui.figure.shapes.ShapeRoundedBox;
import net.sf.graphiti.ui.figure.shapes.ShapeTriangle;

import org.eclipse.draw2d.IFigure;

import com.hp.hpl.jena.ontology.Individual;

public class ShapesImpl extends OntologyNodeImpl implements Shapes {

	public ShapesImpl(Individual individual) {
		super(individual);
	}

	@Override
	public IFigure getShape() {
		if (this.getIndividualName().equals("Circle")) {
			return new ShapeCircle();
		} else if (this.getIndividualName().equals("Triangle")) {
			return new ShapeTriangle();
		} else if (this.getIndividualName().equals("Hexagon")) {
			return new ShapeHexagon();
		} else if (this.getIndividualName().equals("RoundedBox")) {
			return new ShapeRoundedBox();
		} else {
			return null;
		}
	}

}
