/*******************************************************************************
 * Copyright or © or Copr. IETR/INSA - Rennes (2009 - 2017) :
 *
 * Antoine Morvan <antoine.morvan@insa-rennes.fr> (2017)
 * Clément Guy <clement.guy@insa-rennes.fr> (2014)
 * Matthieu Wipliez <matthieu.wipliez@insa-rennes.fr> (2009 - 2010)
 *
 * This software is a computer program whose purpose is to [describe
 * functionalities and technical features of your software].
 *
 * This software is governed by the CeCILL  license under French law and
 * abiding by the rules of distribution of free software.  You can  use, 
 * modify and/ or redistribute the software under the terms of the CeCILL
 * license as circulated by CEA, CNRS and INRIA at the following URL
 * "http://www.cecill.info". 
 *
 * As a counterpart to the access to the source code and  rights to copy,
 * modify and redistribute granted by the license, users are provided only
 * with a limited warranty  and the software's author,  the holder of the
 * economic rights,  and the successive licensors  have only  limited
 * liability. 
 *
 * In this respect, the user's attention is drawn to the risks associated
 * with loading,  using,  modifying and/or developing or reproducing the
 * software by the user in light of its specific status of free software,
 * that may mean  that it is complicated to manipulate,  and  that  also
 * therefore means  that it is reserved for developers  and  experienced
 * professionals having in-depth computer knowledge. Users are therefore
 * encouraged to load and test the software's suitability as regards their
 * requirements in conditions enabling the security of their systems and/or 
 * data to be ensured and,  more generally, to use and operate it in the 
 * same conditions as regards security. 
 *
 * The fact that you are presently reading this means that you have had
 * knowledge of the CeCILL license and that you accept its terms.
 *******************************************************************************/
package org.ietr.dftools.graphiti.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class provides an object type.
 * 
 * @author Matthieu Wipliez
 * 
 */
public class ObjectType implements Comparable<ObjectType> {

	/**
	 * String for the "color" attribute. Defines the color of a graph, a vertex,
	 * an edge.
	 */
	public static final String ATTRIBUTE_COLOR = "color";

	/**
	 * String for the "directed" attribute.
	 */
	public static final String ATTRIBUTE_DIRECTED = "directed";

	/**
	 * String for the "height" attribute. Defines the vertex height.
	 */
	public static final String ATTRIBUTE_HEIGHT = "height";

	/**
	 * String for the "shape" attribute. Defines the vertex shape.
	 */
	public static final String ATTRIBUTE_SHAPE = "shape";

	/**
	 * String for the "width" attribute. Defines the vertex width.
	 */
	public static final String ATTRIBUTE_WIDTH = "width";

	/**
	 * String for the "id" parameter. Defines the vertex id.
	 */
	public static final String PARAMETER_ID = "id";

	/**
	 * String for the "refinement" parameter. Defines the vertex refinement.
	 */
	public static final String PARAMETER_REFINEMENT = "refinement";

	/**
	 * String for the "source port" parameter. Defines the edge source port (if
	 * any).
	 */
	public static final String PARAMETER_SOURCE_PORT = "source port";

	/**
	 * String for the "target port" parameter. Defines the edge target port (if
	 * any).
	 */
	public static final String PARAMETER_TARGET_PORT = "target port";

	private Map<String, Object> attributes;

	private String name;

	private Map<String, Parameter> parameters;

	/**
	 * Creates a new abstract type with the given name.
	 * 
	 * @param name
	 *            The type name.
	 */
	public ObjectType(String name) {
		this.name = name;
		attributes = new HashMap<String, Object>();
		parameters = new HashMap<String, Parameter>();
	}

	/**
	 * Adds the given attribute to this type.
	 * 
	 * @param attributeName
	 *            The attribute name.
	 */
	public void addAttribute(String attributeName, Object value) {
		attributes.put(attributeName, value);
	}

	/**
	 * Adds the given parameter to this type.
	 * 
	 * @param parameter
	 *            A parameter.
	 */
	public void addParameter(Parameter parameter) {
		parameters.put(parameter.getName(), parameter);
	}

	@Override
	public int compareTo(ObjectType type) {
		return name.compareTo(type.name);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof ObjectType) {
			ObjectType type = (ObjectType) obj;
			return name.equals(type.name);
		} else {
			return false;
		}
	}

	/**
	 * Returns the type's attribute that has the given name.
	 * 
	 * @param name
	 *            The name of the attribute we're looking for.
	 * @return The relevant attribute.
	 */
	public Object getAttribute(String name) {
		return attributes.get(name);
	}

	/**
	 * Returns this type's name.
	 * 
	 * @return This type's name.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Returns this type's parameter that has the given name.
	 * 
	 * @param name
	 *            The name of the parameter we're looking for.
	 * @return The relevant parameter.
	 */
	public Parameter getParameter(String name) {
		return parameters.get(name);
	}

	/**
	 * Returns a copy of this type's parameters.
	 * 
	 * @return A {@link List} containing a copy of this type's parameters.
	 */
	public List<Parameter> getParameters() {
		return new ArrayList<Parameter>(parameters.values());
	}

	@Override
	public String toString() {
		return getName();
	}

}
