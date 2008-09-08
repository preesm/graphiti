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
import java.util.HashSet;
import java.util.Set;

import net.sf.graphiti.ontology.impl.OntologyElementImpl;
import net.sf.graphiti.ontology.impl.OntologyModelImpl;
import net.sf.graphiti.ontology.types.EdgeType;
import net.sf.graphiti.ontology.types.GraphType;
import net.sf.graphiti.ontology.types.VertexType;
import net.sf.graphiti.ontology.xmlDescriptions.xmlSchemaTypes.XMLSchemaType;

import com.hp.hpl.jena.ontology.OntDocumentManager;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.ontology.OntResource;
import com.hp.hpl.jena.ontology.Ontology;
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

	public static String getAnnotationPropertyHasDocumentElement() {
		return "http://net.sf.graphiti/basics.owl#hasDocumentElement";
	}

	public static String getAnnotationPropertyHasFileExtension() {
		return "http://net.sf.graphiti/basics.owl#hasFileExtension";
	}

	public static String getAnnotationPropertyRefinementHasFileExtension() {
		return "http://net.sf.graphiti/basics.owl#refinementHasFileExtension";
	}

	public static String getClassAll() {
		return "http://net.sf.graphiti/basics.owl#All";
	}

	public static String getClassAttributeRestriction() {
		return "http://net.sf.graphiti/basics.owl#AttributeRestriction";
	}

	public static String getClassChoice() {
		return "http://net.sf.graphiti/basics.owl#Choice";
	}

	public static String getClassColorAttribute() {
		return "http://net.sf.graphiti/basics.owl#ColorAttribute";
	}

	public static String getClassComplexType() {
		return "http://net.sf.graphiti/basics.owl#ComplexType";
	}

	public static String getClassDataType() {
		return "http://net.sf.graphiti/basics.owl#DataType";
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

	public static String getClassOrderedChoice() {
		return "http://net.sf.graphiti/basics.owl#OrderedChoice";
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

	public static String getClassSequence() {
		return "http://net.sf.graphiti/basics.owl#Sequence";
	}

	public static String getClassShapeAttribute() {
		return "http://net.sf.graphiti/basics.owl#ShapeAttribute";
	}

	public static String getClassShapes() {
		return "http://net.sf.graphiti/basics.owl#Shapes";
	}

	public static String getClassTextContentElement() {
		return "http://net.sf.graphiti/basics.owl#TextContentElement";
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

	public static String getClassXMLAttribute() {
		return "http://net.sf.graphiti/basics.owl#XMLAttribute";
	}

	public static String getClassXMLSchemaType() {
		return "http://net.sf.graphiti/basics.owl#XMLSchemaType";
	}

	public static String getIndividualFloatDataType() {
		return "http://net.sf.graphiti/basics.owl#Float";
	}

	public static String getIndividualIntegerDataType() {
		return "http://net.sf.graphiti/basics.owl#Integer";
	}

	public static String getIndividualListDataType() {
		return "http://net.sf.graphiti/basics.owl#List";
	}

	public static String getIndividualMapDataType() {
		return "http://net.sf.graphiti/basics.owl#Map";
	}

	public static String getIndividualShapeCircle() {
		return "http://net.sf.graphiti/basics.owl#Circle";
	}

	public static String getIndividualShapeHexagon() {
		return "http://net.sf.graphiti/basics.owl#Hexagon";
	}

	public static String getIndividualShapeRoundedBox() {
		return "http://net.sf.graphiti/basics.owl#RoundedBox";
	}

	public static String getIndividualShapeTriangle() {
		return "http://net.sf.graphiti/basics.owl#Triangle";
	}

	public static String getIndividualStringDataType() {
		return "http://net.sf.graphiti/basics.owl#String";
	}

	public static String getIndividualVertexRefinementDataType() {
		return "http://net.sf.graphiti/basics.owl#VertexRefinement";
	}

	public static String getPropertyAllHasElements() {
		return "http://net.sf.graphiti/basics.owl#all_hasElements";
	}

	public static String getPropertyAttributeRestrictionHasName() {
		return "http://net.sf.graphiti/basics.owl#attributeRestriction_hasName";
	}

	public static String getPropertyAttributeRestrictionHasParameterValue() {
		return "http://net.sf.graphiti/basics.owl#attributeRestriction_hasParameterValue";
	}

	public static String getPropertyAttributeRestrictionHasValue() {
		return "http://net.sf.graphiti/basics.owl#attributeRestriction_hasValue";
	}

	public static String getPropertyChoiceHasElements() {
		return "http://net.sf.graphiti/basics.owl#choice_hasElements";
	}

	public static String getPropertyColorAttributeHasBlue() {
		return "http://net.sf.graphiti/basics.owl#colorAttribute_hasBlue";
	}

	public static String getPropertyColorAttributeHasGreen() {
		return "http://net.sf.graphiti/basics.owl#colorAttribute_hasGreen";
	}

	public static String getPropertyColorAttributeHasRed() {
		return "http://net.sf.graphiti/basics.owl#colorAttribute_hasRed";
	}

	public static String getPropertyDOMAttributeHasName() {
		return "http://net.sf.graphiti/basics.owl#attribute_hasName";
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

	public static String getPropertyElementHasName() {
		return "http://net.sf.graphiti/basics.owl#element_hasName";
	}

	public static String getPropertyElementHasParameterValue() {
		return "http://net.sf.graphiti/basics.owl#element_hasParameterValue";
	}

	public static String getPropertyElementHasSchemaType() {
		return "http://net.sf.graphiti/basics.owl#element_hasSchemaType";
	}

	public static String getPropertyFigureAttributeAppliesTo() {
		return "http://net.sf.graphiti/basics.owl#figureAttribute_appliesTo";
	}

	public static String getPropertyOrderedChoiceHasElements() {
		return "http://net.sf.graphiti/basics.owl#orderedChoice_hasElements";
	}

	public static String getPropertyOtherAttributeReferencesParameter() {
		return "http://net.sf.graphiti/basics.owl#otherAttribute_referencesParameter";
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

	public static String getpropertySchemaTypeHasMaxOccurs() {
		return "http://net.sf.graphiti/basics.owl#schemaType_hasMaxOccurs";
	}

	public static String getpropertySchemaTypeHasMinOccurs() {
		return "http://net.sf.graphiti/basics.owl#schemaType_hasMinOccurs";
	}

	public static String getPropertySequenceHasHead() {
		return "http://net.sf.graphiti/basics.owl#sequence_hasHead";
	}

	public static String getPropertySequenceHasRest() {
		return "http://net.sf.graphiti/basics.owl#sequence_hasRest";
	}

	public static String getPropertyShapeAttributeHasShape() {
		return "http://net.sf.graphiti/basics.owl#shapeAttribute_hasShape";
	}

	public static String getPropertyTextContentElementReferencesParameter() {
		return "http://net.sf.graphiti/basics.owl#textContentElement_referencesParameter";
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

	private String modelURI;

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
			fillModelFromInputStream(in);
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
		model.read(in, null);

		// Sets modelURI
		modelURI = model.getNsPrefixURI("");
		int index = modelURI.indexOf('#');
		if (index != -1) {
			modelURI = modelURI.substring(0, index);
		}
	}

	/**
	 * Returns the document element.
	 * 
	 * @return A {@link XMLSchemaType}.
	 */
	public XMLSchemaType getDocumentElement() {
		Ontology ont = model.getOntology(modelURI);
		return getDocumentElement(ont);
	}

	private XMLSchemaType getDocumentElement(Ontology ont) {
		OntologyElement ontElement = new OntologyElementImpl(ont);
		XMLSchemaType docElement = ontElement.getDocumentElement();
		if (docElement == null) {
			ExtendedIterator it = ont.listImports();
			while (it.hasNext() && docElement == null) {
				Ontology ontParent = ((OntResource) it.next()).asOntology();
				docElement = getDocumentElement(ontParent);
			}
		}

		return docElement;
	}

	/**
	 * Returns all the instances of {@link EdgeType}.
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Set<EdgeType> getEdgeTypes() {
		OntologyModelImpl ontModel = new OntologyModelImpl(model);
		return (Set<EdgeType>) ontModel.listIndividuals(OntologyFactory
				.getClassEdgeType());
	}

	/**
	 * Returns the file extensions that this ontology may be associated with.
	 * 
	 * @return The file extensions that this ontology may be associated with.
	 */
	public Set<String> getFileExtensions() {
		Ontology ont = model.getOntology(modelURI);
		return getFileExtensions(ont);
	}

	/**
	 * Returns the file extensions declared by this ontology (including the ones
	 * declared by its parents, and their parents, etc.).
	 * 
	 * @param ont
	 *            The {@link Ontology}.
	 * @return A {@link Set} of strings.
	 */
	private Set<String> getFileExtensions(Ontology ont) {
		OntologyElement ontElement = new OntologyElementImpl(ont);
		Set<String> fileExts = ontElement.getFileExtensions();
		ExtendedIterator it = ont.listImports();
		while (it.hasNext()) {
			Ontology ontParent = ((OntResource) it.next()).asOntology();
			fileExts.addAll(getFileExtensions(ontParent));
		}

		return fileExts;
	}

	/**
	 * Returns all the instances of {@link GraphType}.
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Set<GraphType> getGraphTypes() {
		OntologyModelImpl ontModel = new OntologyModelImpl(model);
		return (Set<GraphType>) ontModel.listIndividuals(OntologyFactory
				.getClassGraphType());
	}

	/**
	 * Returns the URIs of ontologies imported by this ontology.
	 * 
	 * @return The URIs of ontologies imported by this ontology.
	 */
	public Set<String> getImports() {
		Ontology ont = model.getOntology(modelURI);
		Set<String> imports = new HashSet<String>();
		ExtendedIterator it = ont.listImports();
		while (it.hasNext()) {
			OntResource res = (OntResource) it.next();
			imports.add(res.getURI());
		}

		return imports;
	}

	/**
	 * Returns the http:// URL of the model this ontology factory is associated
	 * with.
	 * 
	 * @return The http:// URL of the model this ontology factory is associated
	 *         with.
	 */
	public String getModelURI() {
		return modelURI;
	}

	/**
	 * Returns the file extensions that a vertex refinement may be associated
	 * with.
	 * 
	 * @return The file extensions that a vertex refinement may be associated
	 *         with.
	 */
	public Set<String> getRefinementFileExtensions() {
		Ontology ont = model.getOntology(modelURI);
		return getRefinementFileExtensions(ont);
	}

	/**
	 * @see OntologyFactory#getFileExtensions(Ontology)
	 * @param ont
	 * @return
	 */
	private Set<String> getRefinementFileExtensions(Ontology ont) {
		OntologyElement ontElement = new OntologyElementImpl(ont);
		Set<String> fileExts = ontElement.getRefinementFileExtensions();
		ExtendedIterator it = ont.listImports();
		while (it.hasNext()) {
			Ontology ontParent = ((OntResource) it.next()).asOntology();
			fileExts.addAll(getRefinementFileExtensions(ontParent));
		}

		return fileExts;
	}

	/**
	 * Returns all the instances of {@link VertexType}.
	 * 
	 * @return A set of individuals.
	 */
	@SuppressWarnings("unchecked")
	public Set<VertexType> getVertexTypes() {
		OntologyModelImpl ontModel = new OntologyModelImpl(model);
		return (Set<VertexType>) ontModel.listIndividuals(OntologyFactory
				.getClassVertexType());
	}

}
