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

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Set;

import net.sf.graphiti.ontology.elements.Element;
import net.sf.graphiti.ontology.impl.OntologyNodeImpl;
import net.sf.graphiti.ontology.types.EdgeType;
import net.sf.graphiti.ontology.types.VertexType;

import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntDocumentManager;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.ontology.OntDocumentManager.ReadHook;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.util.iterator.ExtendedIterator;

/**
 * This class loads an ontology, and provides a few request on it.
 * 
 * @author Matthieu Wipliez
 * 
 */
public class OntologyFactory {

	private class MyReadHook implements ReadHook {

		@Override
		public void afterRead(Model model, String source, OntDocumentManager odm) {
		}

		@Override
		public String beforeRead(Model model, String source,
				OntDocumentManager odm) {
			try {
				URL url = new URL(source);
				File file = new File(url.getPath());
				file = new File(path, file.getName());
				return file.toString();
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
			return source;
		}
	}

	public static String getClassAttributeRestriction() {
		return "http://net.sf.graphiti/basics.owl#AttributeRestriction";
	}

	public static String getClassColorAttribute() {
		return "http://net.sf.graphiti/basics.owl#ColorAttribute";
	}

	public static String getClassColors() {
		return "http://net.sf.graphiti/basics.owl#Colors";
	}

	public static String getClassDataTypes() {
		return "http://net.sf.graphiti/basics.owl#DataTypes";
	}

	public static String getClassDocumentElement() {
		return "http://net.sf.graphiti/basics.owl#DocumentElement";
	}

	public static String getClassDOMAttribute() {
		return "http://net.sf.graphiti/basics.owl#DOMAttribute";
	}

	public static String getClassEdgeAttribute() {
		return "http://net.sf.graphiti/basics.owl#EdgeAttribute";
	}

	public static String getClassEdgeConnection() {
		return "http://net.sf.graphiti/basics.owl#EdgeConnection";
	}

	public static String getClassEdgeElement() {
		return "http://net.sf.graphiti/basics.owl#EdgeElement";
	}

	public static String getClassEdgeParameter() {
		return "http://net.sf.graphiti/basics.owl#EdgeParameter";
	}

	public static String getClassEdgeSourceConnection() {
		return "http://net.sf.graphiti/basics.owl#EdgeSourceConnection";
	}

	public static String getClassEdgeTargetConnection() {
		return "http://net.sf.graphiti/basics.owl#EdgeTargetConnection";
	}

	public static String getClassEdgeType() {
		return "http://net.sf.graphiti/basics.owl#EdgeType";
	}

	public static String getClassElement() {
		return "http://net.sf.graphiti/basics.owl#Element";
	}

	public static String getClassFigureAttribute() {
		return "http://net.sf.graphiti/basics.owl#FigureAttribute";
	}

	public static String getClassGraphElement() {
		return "http://net.sf.graphiti/basics.owl#GraphElement";
	}

	public static String getClassGraphParameter() {
		return "http://net.sf.graphiti/basics.owl#GraphParameter";
	}

	public static String getClassGraphType() {
		return "http://net.sf.graphiti/basics.owl#GraphType";
	}

	public static String getClassTextContentElement() {
		return "http://net.sf.graphiti/basics.owl#TextContentElement";
	}

	public static String getClassOtherAttribute() {
		return "http://net.sf.graphiti/basics.owl#OtherAttribute";
	}

	public static String getClassParameter() {
		return "http://net.sf.graphiti/basics.owl#Parameter";
	}

	public static String getClassParameterValue() {
		return "http://net.sf.graphiti/basics.owl#ParameterValue";
	}

	public static String getClassPosition() {
		return "http://net.sf.graphiti/basics.owl#Position";
	}

	public static String getClassShapeAttribute() {
		return "http://net.sf.graphiti/basics.owl#ShapeAttribute";
	}

	public static String getClassShapes() {
		return "http://net.sf.graphiti/basics.owl#Shapes";
	}

	public static String getClassSkipElement() {
		return "http://net.sf.graphiti/basics.owl#SkipElement";
	}

	public static String getClassType() {
		return "http://net.sf.graphiti/basics.owl#Type";
	}

	public static String getClassVertexElement() {
		return "http://net.sf.graphiti/basics.owl#VertexElement";
	}

	public static String getClassVertexParameter() {
		return "http://net.sf.graphiti/basics.owl#VertexParameter";
	}

	public static String getClassVertexType() {
		return "http://net.sf.graphiti/basics.owl#VertexType";
	}
	
	public static String getIndividualFloatDataType() {
		return "http://net.sf.graphiti/basics.owl#Float";
	}
	
	public static String getIndividualIntegerDataType() {
		return "http://net.sf.graphiti/basics.owl#Integer";
	}
	
	public static String getIndividualStringDataType() {
		return "http://net.sf.graphiti/basics.owl#String";
	}
	
	public static String getIndividualVertexRefinementDataType() {
		return "http://net.sf.graphiti/basics.owl#VertexRefinement";
	}

	public static String getPropertyAttributeRestrictionHasName() {
		return "http://net.sf.graphiti/basics.owl#attributeRestriction_hasName";
	}

	public static String getPropertyAttributeRestrictionHasValue() {
		return "http://net.sf.graphiti/basics.owl#attributeRestriction_hasValue";
	}

	public static String getPropertyAttributeRestrictionOfElement() {
		return "http://net.sf.graphiti/basics.owl#attributeRestriction_ofElement";
	}

	public static String getPropertyColorAttributeHasColor() {
		return "http://net.sf.graphiti/basics.owl#colorAttribute_hasColor";
	}

	public static String getPropertyDOMAttributeHasName() {
		return "http://net.sf.graphiti/basics.owl#attribute_hasName";
	}

	public static String getPropertyDOMAttributeIsReference() {
		return "http://net.sf.graphiti/basics.owl#attribute_isReference";
	}

	public static String getPropertyDOMAttributeIsReferenceTo() {
		return "http://net.sf.graphiti/basics.owl#attribute_isReferenceTo";
	}

	public static String getPropertyElementHasAttributeRestriction() {
		return "http://net.sf.graphiti/basics.owl#element_hasAttributeRestriction";
	}

	public static String getPropertyElementHasAttributes() {
		return "http://net.sf.graphiti/basics.owl#element_hasAttributes";
	}

	public static String getPropertyElementHasElementChildren() {
		return "http://net.sf.graphiti/basics.owl#element_hasElementChildren";
	}

	public static String getPropertyElementHasName() {
		return "http://net.sf.graphiti/basics.owl#element_hasName";
	}

	public static String getPropertyElementHasParameterValue() {
		return "http://net.sf.graphiti/basics.owl#element_hasParameterValue";
	}

	public static String getPropertyElementHasPrecedenceElement() {
		return "http://net.sf.graphiti/basics.owl#element_hasPrecedenceElement";
	}

	public static String getPropertyFigureAttributeAppliesTo() {
		return "http://net.sf.graphiti/basics.owl#figureAttribute_appliesTo";
	}

	public static String getPropertyInfoElementReferencesParameter() {
		return "http://net.sf.graphiti/basics.owl#infoElement_referencesParameter";
	}

	public static String getPropertyParameterAppliesTo() {
		return "http://net.sf.graphiti/basics.owl#parameter_appliesTo";
	}

	public static String getPropertyParameterHasName() {
		return "http://net.sf.graphiti/basics.owl#parameter_hasName";
	}

	public static String getPropertyParameterHasPosition() {
		return "http://net.sf.graphiti/basics.owl#parameter_hasPosition";
	}

	public static String getPropertyParameterHasValueType() {
		return "http://net.sf.graphiti/basics.owl#parameter_hasValueType";
	}

	public static String getPropertyParameterValueHasValue() {
		return "http://net.sf.graphiti/basics.owl#parameterValue_hasValue";
	}

	public static String getPropertyParameterValueOfParameter() {
		return "http://net.sf.graphiti/basics.owl#parameterValue_ofParameter";
	}

	public static String getPropertyPropertyBeanParameterHasParameter() {
		return "http://net.sf.graphiti/basics.owl#propertyBeanParameter_isParameter";
	}

	public static String getPropertyShapeAttributeHasShape() {
		return "http://net.sf.graphiti/basics.owl#shapeAttribute_hasShape";
	}

	public static String getPropertyTypeHasFigureAttributes() {
		return "http://net.sf.graphiti/basics.owl#type_hasFigureAttributes";
	}

	public static String getPropertyTypeHasName() {
		return "http://net.sf.graphiti/basics.owl#type_hasName";
	}

	public static String getPropertyTypeHasParameters() {
		return "http://net.sf.graphiti/basics.owl#type_hasParameters";
	}

	private OntModel model;

	private String path;

	/**
	 * Creates a new ontology factory.
	 * 
	 * @param url
	 *            The ontology file URL.
	 */
	public OntologyFactory(String url) {
		File file = new File(url);
		path = file.getParent();

		try {
			InputStream in = new FileInputStream(url);
			readOwl(in);
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Fills the model in from the given input stream.
	 * 
	 * @param in
	 *            The input stream.
	 */
	private void fillModelFromInputStream(InputStream in) {
		OntModelSpec spec = OntModelSpec.OWL_DL_MEM;
		model = ModelFactory.createOntologyModel(spec);
		model.getDocumentManager().setReadHook(new MyReadHook());
		model.read(in, "http://net.sf.graphiti#");
	}

	/**
	 * Returns all the instances of EdgeType.
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Set<EdgeType> getEdgeTypes() {
		OntClass edges = model.getOntClass(OntologyFactory.getClassEdgeType());
		ExtendedIterator it = model.listIndividuals(edges);
		return (Set<EdgeType>) OntologyNodeImpl.convertIndividuals(it);
	}

	/**
	 * Returns all the parser root nodes.
	 * 
	 * @return A set of OntologyElement.
	 */
	@SuppressWarnings("unchecked")
	public Set<Element> getParserRootNodes() {
		OntClass vertex = model.getOntClass(OntologyFactory
				.getClassDocumentElement());
		ExtendedIterator it = model.listIndividuals(vertex);
		return (Set<Element>) OntologyNodeImpl.convertIndividuals(it);
	}

	/**
	 * Returns all the instances of VertexType.
	 * 
	 * @return A set of individuals.
	 */
	@SuppressWarnings("unchecked")
	public Set<VertexType> getVertexTypes() {
		OntClass vertex = model.getOntClass(OntologyFactory
				.getClassVertexType());
		ExtendedIterator it = model.listIndividuals(vertex);
		return (Set<VertexType>) OntologyNodeImpl.convertIndividuals(it);
	}

	/**
	 * 
	 * @param owlInputStream
	 */
	private void readOwl(InputStream owlInputStream) {
		fillModelFromInputStream(owlInputStream);
	}

	public static String getIndividualColorBlue() {
		return "http://net.sf.graphiti/basics.owl#blue";
	}

	public static String getIndividualColorRed() {
		return "http://net.sf.graphiti/basics.owl#red";
	}

	public static String getIndividualColorPink() {
		return "http://net.sf.graphiti/basics.owl#pink";
	}

	public static String getIndividualShapeCircle() {
		return "http://net.sf.graphiti/basics.owl#Circle";
	}

	public static String getIndividualShapeTriangle() {
		return "http://net.sf.graphiti/basics.owl#Triangle";
	}

	public static String getIndividualShapeHexagon() {
		return "http://net.sf.graphiti/basics.owl#Hexagon";
	}

	public static String getIndividualShapeRoundedBox() {
		return "http://net.sf.graphiti/basics.owl#RoundedBox";
	}

}
