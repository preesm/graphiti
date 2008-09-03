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

import java.util.List;
import java.util.Map;

import net.sf.graphiti.ontology.OntologyFactory;
import net.sf.graphiti.ontology.enums.DataType;

import com.hp.hpl.jena.ontology.Individual;

/**
 * Implementation of DataType.
 * 
 * @author Matthieu Wipliez
 * 
 */
public class DataTypeImpl extends OntologyIndividualImpl implements DataType {

	public DataTypeImpl(Individual individual) {
		super(individual);
	}

	@Override
	public Class<?> getDataType() {
		if (OntologyFactory.getIndividualFloatDataType().equals(
				getIndividualURI())) {
			return Float.class;
		} else if (OntologyFactory.getIndividualIntegerDataType().equals(
				getIndividualURI())) {
			return Integer.class;
		} else if (OntologyFactory.getIndividualStringDataType().equals(
				getIndividualURI())) {
			return String.class;
		} else if (OntologyFactory.getIndividualVertexRefinementDataType()
				.equals(getIndividualURI())) {
			return String.class;
		} else if (OntologyFactory.getIndividualListDataType().equals(
				getIndividualURI())) {
			return List.class;
		} else if (OntologyFactory.getIndividualMapDataType().equals(
				getIndividualURI())) {
			return Map.class;
		} else {
			throw new NullPointerException();
		}
	}

}
