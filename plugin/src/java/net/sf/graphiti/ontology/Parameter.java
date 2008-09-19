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
package net.sf.graphiti.ontology;

import java.util.Set;

/**
 * This class provides a parameter.
 * 
 * @author Jonathan Piat
 * @author Matthieu Wipliez
 * 
 */
public interface Parameter extends OntologyIndividual {

	/**
	 * Returns the type (vertex, graph, edge) this parameter applies to.
	 * 
	 * @return A {@link Type}.
	 */
	public Type appliesTo();

	/**
	 * Returns the name of this parameter.
	 * 
	 * @return A {@link String} representation of this parameter.
	 */
	public String hasName();

	/**
	 * Returns the parameter values that may be associated with this parameter.
	 * 
	 * @return A set of {@link ParameterValue}.
	 */
	public Set<ParameterValue> hasParameterValue();

	/**
	 * Returns the visual position of this parameter, if any.
	 * 
	 * @return The {@link Position} of this parameter, or <code>null</code>.
	 */
	public Position hasPosition();

	/**
	 * Returns the type of this parameter. This is mandatory.
	 * 
	 * @return A {@link DataType}.
	 */
	public DataType hasValueType();

}
