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
package net.sf.graphiti.model;

/**
 * This class provides a representation of a vertex type.
 * 
 * @author Matthieu Wipliez
 * 
 */
public class VertexType extends AbstractType {

	/**
	 * String for the "height" attribute. Defines the vertex height.
	 */
	public static final String ATTRIBUTE_HEIGHT = "height";

	/**
	 * String for the "shape" attribute. Defines the vertex shape.
	 */
	public static final String ATTRIBUTE_SHAPE = "shape";

	/**
	 * String for the "width" attribute. Defines the vertex width.
	 */
	public static final String ATTRIBUTE_WIDTH = "width";

	/**
	 * String for the "id" parameter. Defines the vertex id.
	 */
	public static final String PARAMETER_ID = "id";

	/**
	 * String for the "refinement" parameter. Defines the vertex refinement.
	 */
	public static final String PARAMETER_REFINEMENT = "refinement";

	/**
	 * Creates a new vertex type with the given name.
	 * 
	 * @param name
	 *            The type name.
	 */
	public VertexType(String name) {
		super(name);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof VertexType) {
			return super.equals(obj);
		} else {
			return false;
		}
	}

	@Override
	public String toString() {
		return "vertex: " + super.toString();
	}

}
