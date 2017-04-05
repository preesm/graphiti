/*******************************************************************************
 * Copyright or © or Copr. IETR/INSA - Rennes (%%DATE%%) :
 *
 * %%AUTHORS%%
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
package org.ietr.dftools.graphiti.validators;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;
import org.ietr.dftools.graphiti.model.Edge;
import org.ietr.dftools.graphiti.model.Graph;
import org.ietr.dftools.graphiti.model.IValidator;
import org.ietr.dftools.graphiti.model.ObjectType;
import org.ietr.dftools.graphiti.model.Vertex;

/**
 * This class implements a model validator.
 * 
 * @author Matthieu Wipliez
 * 
 */
public class DataflowValidator implements IValidator {

	private boolean checkInputPorts(Graph graph, IFile file) {
		boolean res = true;
		for (Vertex vertex : graph.vertexSet()) {
			Set<Edge> edges = graph.incomingEdgesOf(vertex);
			Map<String, Integer> countMap = new HashMap<String, Integer>();
			for (Edge edge : edges) {
				Object value = edge.getValue(ObjectType.PARAMETER_TARGET_PORT);
				if (value != null) {
					String tgt = (String) value;
					Integer inCount = countMap.get(tgt);
					if (inCount == null) {
						inCount = 0;
					}

					countMap.put(tgt, inCount + 1);
				}
			}

			for (Entry<String, Integer> count : countMap.entrySet()) {
				if (count.getValue() > 1) {
					res = false;
					String message = "The input port " + count.getKey()
							+ " of vertex "
							+ vertex.getValue(ObjectType.PARAMETER_ID)
							+ " has " + count.getValue() + " connections "
							+ "but should not have more than one connection";
					createMarker(file, message);
				}
			}
		}

		return res;
	}

	protected void createMarker(IFile file, String message) {
		try {
			IMarker marker = file.createMarker(IMarker.PROBLEM);
			marker.setAttribute(IMarker.SEVERITY, IMarker.SEVERITY_ERROR);
			marker.setAttribute(IMarker.MESSAGE, message);
		} catch (CoreException e) {
		}
	}

	@Override
	public boolean validate(Graph graph, IFile file) {
		return checkInputPorts(graph, file);
	}

}
