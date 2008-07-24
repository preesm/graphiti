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

import net.sf.graphiti.ontology.elements.DocumentElement;

/**
 * This class provides access to an ontology element (owl:Ontology) and
 * annotation properties.
 * 
 * @author Matthieu Wipliez
 * 
 */
public interface OntologyElement {

	/**
	 * Returns the {@link DocumentElement} to start parsing with.
	 * 
	 * @return The {@link DocumentElement} to start parsing with.
	 */
	public DocumentElement getDocumentElement();

	/**
	 * Returns the file extensions that this ontology may be associated with.
	 * 
	 * @return The file extensions that this ontology may be associated with.
	 */
	public Set<String> getFileExtensions();

	/**
	 * Returns the file extensions that a vertex refinement may be associated
	 * with.
	 * 
	 * @return The file extensions that a vertex refinement may be associated
	 *         with.
	 */
	public Set<String> getRefinementFileExtensions();

}
