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

import java.util.Iterator;
import java.util.NoSuchElementException;

import net.sf.graphiti.ontology.OntologyFactory;
import net.sf.graphiti.ontology.xmlDescriptions.xmlSchemaTypes.XMLSchemaType;
import net.sf.graphiti.ontology.xmlDescriptions.xmlSchemaTypes.complexTypes.Sequence;

import com.hp.hpl.jena.ontology.Individual;

/**
 * Implementation of Sequence.
 * 
 * @author Matthieu Wipliez
 * 
 */
public class SequenceImpl extends ComplexTypeImpl implements Sequence {

	/**
	 * An {@link Iterable} on {@link XMLSchemaType} elements of this
	 * {@link Sequence}.
	 * 
	 * @author Matthieu Wipliez
	 * 
	 */
	private class SequenceIterable implements Iterable<XMLSchemaType> {

		private SequenceImpl sequence;

		/**
		 * Creates a new {@link SequenceIterable} on this {@link SequenceImpl}.
		 * 
		 * @param sequence
		 *            The sequence.
		 */
		public SequenceIterable(SequenceImpl sequence) {
			this.sequence = sequence;
		}

		@Override
		public Iterator<XMLSchemaType> iterator() {
			return new SequenceIterator(sequence);
		}

	}

	/**
	 * An {@link Iterator} on {@link XMLSchemaType} elements of this
	 * {@link Sequence}.
	 * 
	 * @author Matthieu Wipliez
	 * 
	 */
	private class SequenceIterator implements Iterator<XMLSchemaType> {

		private SequenceImpl rest;

		/**
		 * Creates a new {@link SequenceIterator} on the given
		 * {@link SequenceImpl}. This initializes {@link #rest} with the value
		 * of <code>sequence</code>. Calls to {@link #next()} will modify
		 * {@link #rest} value.
		 * 
		 * @param sequence
		 *            The sequence.
		 */
		public SequenceIterator(SequenceImpl sequence) {
			this.rest = sequence;
		}

		@Override
		public boolean hasNext() {
			// The hasNext test is very simple: since the ontology states that a
			// Sequence must have a head, and may have a rest, we consider that
			// when it does not have a rest, it is finished.
			return (rest != null);
		}

		@Override
		public XMLSchemaType next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}

			// Gets the head (see hasNext comment).
			XMLSchemaType head = (XMLSchemaType) rest
					.getIndividualProperty(OntologyFactory
							.getPropertySequenceHasHead());

			// Gets the rest.
			rest = (SequenceImpl) rest.getIndividualProperty(OntologyFactory
					.getPropertySequenceHasRest());
			
			// Returns the head value.
			return head;
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}

	}

	public SequenceImpl(Individual individual) {
		super(individual);
	}

	@Override
	public Iterable<XMLSchemaType> hasElements() {
		return new SequenceIterable(this);
	}

}
