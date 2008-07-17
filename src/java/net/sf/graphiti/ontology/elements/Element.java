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
package net.sf.graphiti.ontology.elements;

import java.util.Set;

import net.sf.graphiti.ontology.OntologyNode;
import net.sf.graphiti.ontology.attributeRestrictions.AttributeRestriction;
import net.sf.graphiti.ontology.domAttributes.DOMAttribute;
import net.sf.graphiti.ontology.parameterValues.ParameterValue;

/**
 * This class provides the ontology-defined representation of a DOM element in
 * the input XML document.
 * 
 * @author Jonathan Piat
 * @author Matthieu Wipliez
 * 
 */
public interface Element extends OntologyNode {

	/**
	 * Returns a set of {@link AttributeRestriction} that may be associated with
	 * this {@link Element}.
	 * 
	 * @return A set of {@link AttributeRestriction} that may be associated with
	 *         this {@link Element}.
	 */
	public Set<AttributeRestriction> hasAttributeRestriction();

	/**
	 * Returns a set of {@link DOMAttribute} that may be associated with this
	 * {@link Element}.
	 * 
	 * @return A set of {@link DOMAttribute} that may be associated with this
	 *         {@link Element}.
	 */
	public Set<DOMAttribute> hasAttributes();

	/**
	 * Returns a set of {@link Element} that may be children of this
	 * {@link Element}.
	 * 
	 * @return A set of {@link Element} that may be children of this
	 *         {@link Element}.
	 */
	public Set<Element> hasElementChildren();

	/**
	 * Returns the name of this {@link Element}.
	 * 
	 * @return The name of this {@link Element}.
	 */
	public String hasName();

	/**
	 * Returns a set of {@link ParameterValue} that may be associated with this
	 * {@link Element}.
	 * 
	 * @return A set of {@link ParameterValue} that may be associated with this
	 *         {@link Element}.
	 */
	public Set<ParameterValue> hasParameterValues();

	/**
	 * Returns an {@link Element} that should appear before this one (if any,
	 * otherwise <code>null</code>).
	 * 
	 * @return An {@link Element} that should appear before this one (if any,
	 *         otherwise <code>null</code>).
	 */
	public Element hasPrecedenceElement();

}
