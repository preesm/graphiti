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
package net.sf.graphiti.writer;

import java.util.Set;

import net.sf.graphiti.model.Configuration;
import net.sf.graphiti.model.Edge;
import net.sf.graphiti.model.Graph;
import net.sf.graphiti.model.Vertex;
import net.sf.graphiti.ontology.OntologyFactory;
import net.sf.graphiti.ontology.xmlDescriptions.xmlAttributes.XMLAttribute;
import net.sf.graphiti.ontology.xmlDescriptions.xmlSchemaTypes.XMLSchemaType;
import net.sf.graphiti.ontology.xmlDescriptions.xmlSchemaTypes.complexTypes.Choice;
import net.sf.graphiti.ontology.xmlDescriptions.xmlSchemaTypes.complexTypes.ComplexType;
import net.sf.graphiti.ontology.xmlDescriptions.xmlSchemaTypes.complexTypes.Sequence;
import net.sf.graphiti.ontology.xmlDescriptions.xmlSchemaTypes.elements.EdgeElement;
import net.sf.graphiti.ontology.xmlDescriptions.xmlSchemaTypes.elements.Element;
import net.sf.graphiti.ontology.xmlDescriptions.xmlSchemaTypes.elements.VertexElement;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.w3c.dom.Document;

/**
 * This class parses the given DOM document according to the schema defined in
 * the ontology.
 * 
 * @author Matthieu Wipliez
 * 
 */
public class SchemaWriter {

	private ContentWriter contentWriter;

	private Logger log;

	/**
	 * Creates a new schema writer for the given document.
	 * 
	 */
	public SchemaWriter(Configuration configuration, Document document) {
		contentWriter = new ContentWriter(configuration, document);
		log = Logger.getLogger(SchemaWriter.class);
		log.setLevel(Level.ALL);
	}

	/**
	 * Writes the graph this schema writer was constructed with to the given
	 * document using the given ontology document element.
	 * 
	 * @param ontDocElement
	 *            The ontology document element.
	 */
	public void write(XMLSchemaType ontDocElement, Graph graph) {
		if (ontDocElement.hasOntClass(OntologyFactory.getClassComplexType())) {
			writeComplexTypeOccurs((ComplexType) ontDocElement, graph);
		} else {
			writeElement((Element) ontDocElement, graph);
		}
		
		contentWriter.commit();
	}

	private void writeAttributes(Element ontElement, Object context) {
		Set<XMLAttribute> attributes = ontElement.hasAttributes();
		for (XMLAttribute ontAttribute : attributes) {
			contentWriter.writeAttribute(ontAttribute, context);
		}
	}

	private void writeChoice(Choice choice, Object context) {
		for (XMLSchemaType type : choice.hasElements()) {
			writeSchemaType(type, context);
		}
	}

	private void writeComplexType(ComplexType type, Object context) {
		if (type instanceof Sequence) {
			writeSequence((Sequence) type, context);
		} else if (type instanceof Choice) {
			writeChoice((Choice) type, context);
		} else {
			log.debug("parseComplexType: type = All");
		}
	}

	private void writeComplexTypeOccurs(ComplexType type, Object context) {
		int minOccurs = type.minOccurs();
		int maxOccurs = type.maxOccurs();

		// min occurs
		int i = 0;
		for (; i < minOccurs; i++) {
			writeComplexType(type, context);
		}

		// max occurs
		if (maxOccurs > -1) {
			for (; i < maxOccurs; i++) {
				writeComplexType(type, context);
			}
		} else {
			//TODO: other cases?
			System.out.println();
		}
	}

	private void writeElement(Element ontElement, Object context) {
		contentWriter.elementStart(ontElement, context);

		writeAttributes(ontElement, context);

		XMLSchemaType type = ontElement.hasSchemaType();
		if (type != null) {
			writeSchemaType(type, context);
		}

		contentWriter.elementEnd(ontElement, context);
	}

	private void writeElementOccurs(Element ontElement, Object context) {
		int minOccurs = ontElement.minOccurs();
		int maxOccurs = ontElement.maxOccurs();

		// min occurs
		int i;
		for (i = 0; i < minOccurs; i++) {
			writeElement(ontElement, context);
		}

		// max occurs
		if (maxOccurs > -1) {
			for (; i < maxOccurs; i++) {
				writeElement(ontElement, context);
			}
		} else {
			if (ontElement instanceof VertexElement) {
				Set<Vertex> vertices = ((Graph) context).vertexSet();
				for (Vertex vertex : vertices) {
					writeElement(ontElement, vertex);
				}
			} else if (ontElement instanceof EdgeElement) {
				Set<Edge> edges = ((Graph) context).edgeSet();
				for (Edge edge : edges) {
					writeElement(ontElement, edge);
				}
			} else {
				//TODO: other cases?
				System.out.println();
			}
		}
	}

	private void writeSchemaType(XMLSchemaType type, Object context) {
		if (type.hasOntClass(OntologyFactory.getClassComplexType())) {
			writeComplexTypeOccurs((ComplexType) type, context);
		} else {
			writeElementOccurs((Element) type, context);
		}
	}

	private void writeSequence(Sequence sequence, Object context) {
		for (XMLSchemaType type : sequence.hasElements()) {
			writeSchemaType(type, context);
		}
	}

}
