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
