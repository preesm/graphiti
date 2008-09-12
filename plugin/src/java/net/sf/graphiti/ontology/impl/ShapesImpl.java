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
package net.sf.graphiti.ontology.impl;

import net.sf.graphiti.ontology.OntologyFactory;
import net.sf.graphiti.ontology.enums.Shapes;
import net.sf.graphiti.ui.figure.shapes.ShapeCircle;
import net.sf.graphiti.ui.figure.shapes.ShapeHexagon;
import net.sf.graphiti.ui.figure.shapes.ShapeRoundedBox;
import net.sf.graphiti.ui.figure.shapes.ShapeTriangle;

import org.eclipse.draw2d.IFigure;

import com.hp.hpl.jena.ontology.Individual;

/**
 * Implementation of Shapes.
 * 
 * @author Matthieu Wipliez
 * 
 */
public class ShapesImpl extends OntologyIndividualImpl implements Shapes {

	public ShapesImpl(Individual individual) {
		super(individual);
	}

	@Override
	public IFigure getShape() {
		if (OntologyFactory.getIndividualShapeCircle().equals(getResourceURI())) {
			return new ShapeCircle();
		} else if (OntologyFactory.getIndividualShapeTriangle().equals(
				getResourceURI())) {
			return new ShapeTriangle();
		} else if (OntologyFactory.getIndividualShapeHexagon().equals(
				getResourceURI())) {
			return new ShapeHexagon();
		} else if (OntologyFactory.getIndividualShapeRoundedBox().equals(
				getResourceURI())) {
			return new ShapeRoundedBox();
		} else {
			throw new NullPointerException();
		}
	}

}
