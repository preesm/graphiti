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

import net.sf.graphiti.ontology.OntologyIndividual;

import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.util.iterator.ExtendedIterator;

/**
 * This class provides several methods to retrieve property values, and is the
 * base class of the ontology hierarchy.
 * 
 * @author Matthieu Wipliez
 * 
 */
public class OntologyIndividualImpl extends OntologyBaseImpl implements
		OntologyIndividual, Comparable<OntologyIndividualImpl> {

	protected OntologyIndividualImpl(Individual individual) {
		super(individual);
	}

	@Override
	public int compareTo(OntologyIndividualImpl o) {
		return getIndividualURI().compareTo(o.getIndividualURI());
	}

	/**
	 * Returns the individual local name.
	 * 
	 * @return The individual local name.
	 */
	protected String getIndividualLocalName() {
		return resource.getLocalName();
	}

	/**
	 * Returns the individual unique URI.
	 * 
	 * @return The individual unique URI.
	 */
	protected String getIndividualURI() {
		return resource.getURI();
	}

	@Override
	public boolean hasOntClass(String clasz) {
		OntClass ontClass = resource.getOntModel().getOntClass(clasz);

		// Tests all classes of this individual against ontClass
		ExtendedIterator it = ((Individual) resource).listOntClasses(false);
		boolean result = false;
		while (it.hasNext() && !result) {
			OntClass indOntClass = (OntClass) it.next();
			result = testOntClass(ontClass, indOntClass);
		}

		return result;
	}

	/**
	 * Tests if the given ontology class <code>ontClass</code> is the same as
	 * the class of this individual (<code>indOntClass</code>) or one of its
	 * parents.
	 * 
	 * @param ontClass
	 *            The class to test against.
	 * @param indOntClass
	 *            The class of this individual (or one of its parents).
	 * @return True if ontClass.equals(indOntClass), or if ontClass.equals(a
	 *         parent of indOntClass).
	 */
	private boolean testOntClass(OntClass ontClass, OntClass indOntClass) {
		boolean result = false;
		if (ontClass.equals(indOntClass)) {
			result = true;
		} else {
			ExtendedIterator itClass = indOntClass.listSuperClasses();
			while (itClass.hasNext() && !result) {
				OntClass parentOntClass = (OntClass) itClass.next();
				result = testOntClass(ontClass, parentOntClass);
			}
		}

		return result;
	}

	public String toString() {
		return getIndividualURI();
	}

}
