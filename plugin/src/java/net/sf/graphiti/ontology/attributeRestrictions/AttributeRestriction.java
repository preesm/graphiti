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
package net.sf.graphiti.ontology.attributeRestrictions;

import net.sf.graphiti.ontology.OntologyIndividual;
import net.sf.graphiti.ontology.elements.Element;

/**
 * This class provides an attribute restriction. An attribute restriction can be
 * used to distinguish elements by some of their attribute values, e.g.
 * &lt;Graph kind="directed"&gt; from &lt;Graph kind="undirected"&gt;. In this
 * example, hasName is "kind" and hasValue is "directed" or "undirected".
 * 
 * @author Jonathan Piat
 * @author Matthieu Wipliez
 * 
 */
public interface AttributeRestriction extends OntologyIndividual {

	/**
	 * Returns the name of the restricted attribute.
	 * 
	 * @return The name of the restricted attribute.
	 */
	public String hasName();

	/**
	 * Returns the value of the restricted attribute.
	 * 
	 * @return The value of the restricted attribute.
	 */
	public String hasValue();

	/**
	 * Returns the element the restricted attribute belongs to.
	 * 
	 * @return The element the restricted attribute belongs to.
	 */
	public Element ofElement();
}
