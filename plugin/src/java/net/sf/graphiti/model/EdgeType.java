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
 * This class provides a representation of an edge type.
 * 
 * @author Matthieu Wipliez
 * 
 */
public class EdgeType extends AbstractType {

	/**
	 * String for the "directed" attribute.
	 */
	public static final String ATTRIBUTE_DIRECTED = "directed";

	/**
	 * String for the "source port" parameter. Defines the edge source port (if
	 * any).
	 */
	public static final String PARAMETER_SOURCE_PORT = "source port";

	/**
	 * String for the "target port" parameter. Defines the edge target port (if
	 * any).
	 */
	public static final String PARAMETER_TARGET_PORT = "target port";

	/**
	 * Creates a new edge type with the given name.
	 * 
	 * @param name
	 *            The type name.
	 */
	public EdgeType(String name) {
		super(name);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof EdgeType) {
			return super.equals(obj);
		} else {
			return false;
		}
	}

	@Override
	public String toString() {
		return "edge: " + super.toString();
	}

}
