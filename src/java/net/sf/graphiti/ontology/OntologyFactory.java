/**
 * 
 */
package net.sf.graphiti.ontology;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Set;

import net.sf.graphiti.ontology.nodes.ParserNode;
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

	public static String getClassColorAttribute() {
		return "http://org.ietr.age/basics.owl#ColorAttribute";
	}

	public static String getClassColors() {
		return "http://org.ietr.age/basics.owl#Colors";
	}

	public static String getClassConstantParameter() {
		return "http://org.ietr.age/basics.owl#ConstantParameter";
	}

	public static String getClassDataTypes() {
		return "http://org.ietr.age/basics.owl#DataTypes";
	}

	public static String getClassDefaultParameter() {
		return "http://org.ietr.age/basics.owl#DefaultParameter";
	}

	public static String getClassEdgeConnection() {
		return "http://org.ietr.age/basics.owl#EdgeConnection";
	}

	public static String getClassEdgeNode() {
		return "http://org.ietr.age/basics.owl#EdgeNode";
	}

	public static String getClassEdgeParameter() {
		return "http://org.ietr.age/basics.owl#EdgeParameter";
	}

	public static String getClassEdgeParameterNode() {
		return "http://org.ietr.age/basics.owl#EdgeParameterNode";
	}

	public static String getClassEdgeSourceConnection() {
		return "http://org.ietr.age/basics.owl#EdgeSourceConnection";
	}

	public static String getClassEdgeTargetConnection() {
		return "http://org.ietr.age/basics.owl#EdgeTargetConnection";
	}

	public static String getClassEdgeType() {
		return "http://org.ietr.age/basics.owl#EdgeType";
	}

	public static String getClassFigureAttribute() {
		return "http://org.ietr.age/basics.owl#FigureAttribute";
	}

	public static String getClassGraphNode() {
		return "http://org.ietr.age/basics.owl#GraphNode";
	}

	public static String getClassGraphParameter() {
		return "http://org.ietr.age/basics.owl#GraphParameter";
	}

	public static String getClassGraphType() {
		return "http://org.ietr.age/basics.owl#GraphType";
	}

	public static String getClassIdParameter() {
		return "http://org.ietr.age/basics.owl#IdParameter";
	}

	public static String getClassParameter() {
		return "http://org.ietr.age/basics.owl#Parameter";
	}

	public static String getClassParserNode() {
		return "http://org.ietr.age/basics.owl#ParserNode";
	}

	public static String getClassParserParameterNode() {
		return "http://org.ietr.age/basics.owl#ParserParameterNode";
	}

	public static String getClassParserRootNode() {
		return "http://org.ietr.age/basics.owl#ParserRootNode";
	}

	public static String getClassPosition() {
		return "http://org.ietr.age/basics.owl#Position";
	}

	public static String getClassPropertyBeanParameter() {
		return "http://org.ietr.age/basics.owl#PropertyBeanParameter";
	}

	public static String getClassShapeAttribute() {
		return "http://org.ietr.age/basics.owl#ShapeAttribute";
	}

	public static String getClassShapes() {
		return "http://org.ietr.age/basics.owl#Shapes";
	}
	
	public static String getClassParserFixedParameter() {
		return "http://org.ietr.age/basics.owl#ParserFixedParameter";
	}

	public static String getClassSkipNode() {
		return "http://org.ietr.age/basics.owl#SkipNode";
	}

	public static String getClassType() {
		return "http://org.ietr.age/basics.owl#Type";
	}

	public static String getClassVertexNode() {
		return "http://org.ietr.age/basics.owl#VertexNode";
	}

	public static String getClassVertexParameter() {
		return "http://org.ietr.age/basics.owl#VertexParameter";
	}

	public static String getClassVertexType() {
		return "http://org.ietr.age/basics.owl#VertexType";
	}

	public static String getPropertyColorAttributeHasColor() {
		return "http://org.ietr.age/basics.owl#colorAttribute_hasColor";
	}
	
	public static String getPropertyConstantParameterHasValue() {
		return "http://org.ietr.age/basics.owl#constantParameter_hasValue";
	}

	public static String getPropertyFigureAttributeAppliesTo() {
		return "http://org.ietr.age/basics.owl#figureAttribute_appliesTo";
	}

	public static String getPropertyFigureAttributeHasType() {
		return "http://org.ietr.age/basics.owl#figureAttribute_hasType";
	}

	public static String getPropertyParameterAppliesTo() {
		return "http://org.ietr.age/basics.owl#parameter_appliesTo";
	}

	public static String getPropertyParameterHasName() {
		return "http://org.ietr.age/basics.owl#parameter_hasName";
	}
	
	public static String getParserFixedParameterHasValue() {
		return "http://org.ietr.age/basics.owl#parserFixedParameter_hasValue";
	}

	public static String getPropertyParameterHasPosition() {
		return "http://org.ietr.age/basics.owl#parameter_hasPosition";
	}

	public static String getPropertyParameterHasValueType() {
		return "http://org.ietr.age/basics.owl#parameter_hasValueType";
	}

	public static String getPropertyParserNodeHasAttributeNode() {
		return "http://org.ietr.age/basics.owl#parserNode_hasAttributeNode";
	}

	public static String getPropertyParserNodeHasChildNode() {
		return "http://org.ietr.age/basics.owl#parserNode_hasChildNode";
	}

	public static String getPropertyParserNodeHasIdParameterNode() {
		return "http://org.ietr.age/basics.owl#parserNode_hasIdParameterNode";
	}
	
	public static String getPropertyParserNodeHassFixedParameter() {
		return "http://org.ietr.age/basics.owl#parserNode_hasFixedParameter";
	}

	public static String getPropertyParserNodeHasName() {
		return "http://org.ietr.age/basics.owl#parserNode_hasName";
	}

	public static String getPropertyParserNodeHasPrecedenceNode(){
		return "http://org.ietr.age/basics.owl#parserNode_hasPrecedenceNode";
	}
	
	public static String getPropertyParserParameterNodeHasValueType() {
		return "http://org.ietr.age/basics.owl#parserNode_hasValueType";
	}

	public static String getPropertyParserParameterNodeIsReference() {
		return "http://org.ietr.age/basics.owl#parserParameterNode_isReference";
	}

	public static String getPropertyParserParameterNodeIsReferenceTo() {
		return "http://org.ietr.age/basics.owl#parserParameterNode_isReferenceTo";
	}

	public static String getPropertyParserParameterNodeReferencesIn() {
		return "http://org.ietr.age/basics.owl#parserParameterNode_referencesIn";
	}

	public static String getPropertyPropertyBeanParameterHasParameter() {
		return "http://org.ietr.age/basics.owl#propertyBeanParameter_isParameter";
	}

	public static String getPropertyShapeAttributeHasShape() {
		return "http://org.ietr.age/basics.owl#shapeAttribute_hasShape";
	}

	public static String getPropertyTypeHasFigureAttributes() {
		return "http://org.ietr.age/basics.owl#type_hasFigureAttributes";
	}

	public static String getPropertyTypeHasParameters() {
		return "http://org.ietr.age/basics.owl#type_hasParameters";
	}

	public static String getPropertyTypeHasStringRepresentation() {
		return "http://org.ietr.age/basics.owl#type_hasStringRepresentation";
	}

	private OntModel model;

	private String path;

	/**
	 * Creates a new ontology factory.
	 * 
	 * @param url
	 *            The ontology file URL.
	 * @param monitor
	 *            The progress monitor.
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
		model.read(in, "http://org.ietr.age#");
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
	 * @return A set of ParserNode.
	 */
	@SuppressWarnings("unchecked")
	public Set<ParserNode> getParserRootNodes() {
		OntClass vertex = model.getOntClass(OntologyFactory
				.getClassParserRootNode());
		ExtendedIterator it = model.listIndividuals(vertex);
		return (Set<ParserNode>) OntologyNodeImpl.convertIndividuals(it);
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

}
