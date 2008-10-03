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
package net.sf.graphiti.parsers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sf.graphiti.model.Configuration;
import net.sf.graphiti.model.Vertex;
import net.sf.graphiti.ontology.ColorAttribute;
import net.sf.graphiti.ontology.DimensionAttribute;
import net.sf.graphiti.ontology.EdgeParameter;
import net.sf.graphiti.ontology.EdgeType;
import net.sf.graphiti.ontology.FigureAttribute;
import net.sf.graphiti.ontology.GraphParameter;
import net.sf.graphiti.ontology.GraphType;
import net.sf.graphiti.ontology.OntologyFactory;
import net.sf.graphiti.ontology.Parameter;
import net.sf.graphiti.ontology.Shape;
import net.sf.graphiti.ontology.ShapeAttribute;
import net.sf.graphiti.ontology.VertexParameter;
import net.sf.graphiti.ontology.VertexType;
import net.sf.graphiti.util.FileLocator;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.swt.graphics.Color;

/**
 * This class loads the ontologies present in the src/owl folder and creates a
 * configuration from them.
 * 
 * @author Matthieu Wipliez
 * 
 */
public class ConfigurationLoader {

	private Configuration configuration;

	/**
	 * Creates a new ontology loader in the given bundle context.
	 * 
	 * @param context
	 *            The GraphitiPlugin#start bundle context.
	 * @throws CoreException
	 *             Whenever something bad happens.
	 */
	public ConfigurationLoader() {
		configuration = loadOntologies();
	}

	/**
	 * Fills the configuration with parameters of the given {@link EdgeType}.
	 * 
	 * @param config
	 *            The {@link Configuration}.
	 * @param edgeType
	 *            An {@link EdgeType}.
	 */
	private void fillEdgeType(Configuration config, EdgeType edgeType) {
		String edgeTypeName = edgeType.hasName();
		Set<Parameter> parameters = edgeType.hasParameters();
		for (Parameter parameter : parameters) {
			if (parameter.hasOntClass(OntologyFactory.getClassEdgeParameter())) {
				net.sf.graphiti.model.Parameter param = new net.sf.graphiti.model.Parameter(
						(EdgeParameter) parameter);
				config.addEdgeParameter(edgeTypeName, param);
			}
		}
	}

	/**
	 * Fills the configuration with parameters of the given {@link GraphType}.
	 * 
	 * @param config
	 *            The {@link Configuration}.
	 * @param graphType
	 *            An {@link GraphType}.
	 */
	private void fillGraphType(Configuration config, GraphType graphType) {
		String graphTypeName = graphType.hasName();
		Set<Parameter> parameters = graphType.hasParameters();
		for (Parameter parameter : parameters) {
			if (parameter.hasOntClass(OntologyFactory.getClassGraphParameter())) {
				net.sf.graphiti.model.Parameter param = new net.sf.graphiti.model.Parameter(
						(GraphParameter) parameter);
				config.addGraphParameter(graphTypeName, param);
			}
		}
	}

	/**
	 * Fills the configuration with the given {@link Parameter} of the given
	 * {@link VertexType}.
	 * 
	 * @param config
	 *            The {@link Configuration}.
	 * @param vertexType
	 *            A {@link VertexType}.
	 * @param parameter
	 *            A {@link Parameter}.
	 */
	private void fillVertexParameter(Configuration config, String vertexType,
			Parameter parameter) {
		if (parameter.hasOntClass(OntologyFactory.getClassVertexParameter())) {
			net.sf.graphiti.model.Parameter param = new net.sf.graphiti.model.Parameter(
					(VertexParameter) parameter);
			config.addVertexParameter(vertexType, param);
		} else if (parameter.hasOntClass(OntologyFactory
				.getClassEdgeParameter())) {
			net.sf.graphiti.model.Parameter param = new net.sf.graphiti.model.Parameter(
					(EdgeParameter) parameter);
			config.addEdgeParameter(vertexType, param);
		} else if (parameter.hasOntClass(OntologyFactory
				.getClassGraphParameter())) {
			net.sf.graphiti.model.Parameter param = new net.sf.graphiti.model.Parameter(
					(GraphParameter) parameter);
			config.addGraphParameter(vertexType, param);
		}
	}

	/**
	 * Fills the configuration with parameters of the given {@link VertexType}.
	 * 
	 * @param config
	 *            The {@link Configuration}.
	 * @param vertexType
	 *            A {@link VertexType}.
	 */
	private void fillVertexType(Configuration config, VertexType vertexType) {
		String vertexTypeName = vertexType.hasName();
		Set<FigureAttribute> attributes = vertexType.hasFigureAttributes();
		for (FigureAttribute attribute : attributes) {
			if (attribute.hasOntClass(OntologyFactory.getClassColorAttribute())) {
				Color color = ((ColorAttribute) attribute).hasColor();
				config.setVertexAttribute(vertexTypeName,
						Vertex.ATTRIBUTE_COLOR, color);
			} else if (attribute.hasOntClass(OntologyFactory
					.getClassShapeAttribute())) {
				Shape shape = ((ShapeAttribute) attribute).hasShape();
				config.setVertexAttribute(vertexTypeName,
						Vertex.ATTRIBUTE_SHAPE, shape);
			} else if (attribute.hasOntClass(OntologyFactory
					.getClassDimensionAttribute())) {
				DimensionAttribute dimAttr = (DimensionAttribute) attribute;
				config.setVertexAttribute(vertexTypeName,
						Vertex.ATTRIBUTE_WIDTH, dimAttr.hasWidth());
				config.setVertexAttribute(vertexTypeName,
						Vertex.ATTRIBUTE_HEIGHT, dimAttr.hasHeight());
			}
		}

		Set<Parameter> parameters = vertexType.hasParameters();
		for (Parameter parameter : parameters) {
			fillVertexParameter(config, vertexTypeName, parameter);
		}
	}

	/**
	 * Returns a reference to the configuration tree. Please note that this
	 * method merely returns the root of the configuration tree created by
	 * ConfigurationLoader constructor.
	 * 
	 * @return A reference to the configuration tree root.
	 */
	public Configuration getConfiguration() {
		return configuration;
	}

	/**
	 * Enumerates *.owl files in the bundle, and calls loadOntology on each of
	 * them.
	 * 
	 * @return A tree of document configurations, one per ontology. The
	 *         {@link Configuration} returned is the root.
	 * @throws CoreException
	 */
	private Configuration loadOntologies() {
		// Get all *.owl files
		List<String> ontologyFiles = FileLocator.listFiles("[^.]*\\.owl");

		// Loads all ontology files in ontologyFiles
		Map<String, Configuration> configurations = new HashMap<String, Configuration>(
				ontologyFiles.size());
		for (String file : ontologyFiles) {
			Configuration config = loadOntology(file);
			configurations.put(config.getOntologyFactory().getModelURI(),
					config);
		}

		// Builds the tree.
		for (Configuration config : configurations.values()) {
			OntologyFactory factory = config.getOntologyFactory();
			Set<String> imports = factory.getImports();
			for (String importURI : imports) {
				Configuration parent = configurations.get(importURI);
				config.addParent(parent);
			}
		}

		// Gets the root.
		Configuration configuration = null;
		for (Configuration config : configurations.values()) {
			if (config.isRoot()) {
				configuration = config;
				break;
			}
		}

		return configuration;
	}

	/**
	 * Loads the ontology in the given file, creates a document configuration,
	 * fills it in with the ontology, and returns it.
	 * 
	 * @param file
	 *            The file that contains the ontology.
	 * @return The document configuration created and filled in.
	 * @throws CoreException
	 */
	private Configuration loadOntology(String file) {
		System.out.println("Loading ontology: " + file);
		Configuration config = new Configuration(file);
		OntologyFactory factory = config.getOntologyFactory();

		// graph types.
		Set<GraphType> graphTypes = factory.getGraphTypes();
		for (GraphType type : graphTypes) {
			fillGraphType(config, type);
		}

		// vertex types.
		Set<VertexType> vertexTypes = factory.getVertexTypes();
		for (VertexType type : vertexTypes) {
			fillVertexType(config, type);
		}

		// edge types.
		Set<EdgeType> edgeTypes = factory.getEdgeTypes();
		for (EdgeType type : edgeTypes) {
			fillEdgeType(config, type);
		}

		// file extensions.
		config.setFileExtensions(factory.getFileExtensions());
		config.setRefinementFileFormats(factory.getRefinementFileExtensions());

		return config;
	}

}
