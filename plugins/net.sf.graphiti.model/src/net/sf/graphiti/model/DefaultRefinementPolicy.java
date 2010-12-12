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
package net.sf.graphiti.model;

import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;

/**
 * This class defines a default refinement policy.
 * 
 * @author Matthieu Wipliez
 * 
 */
public class DefaultRefinementPolicy implements IRefinementPolicy {

	@Override
	public boolean editRefinement(Vertex vertex) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getRefinement(Vertex vertex) {
		if (vertex != null) {
			Object refinement = vertex
					.getValue(ObjectType.PARAMETER_REFINEMENT);
			if (refinement instanceof String) {
				return (String) refinement;
			}
		}

		return null;
	}

	/**
	 * Returns the absolute path of the refinement of the given vertex.
	 * 
	 * @param vertex
	 *            a vertex
	 * @return the absolute path of the refinement of the given vertex
	 */
	protected IPath getRefinementAbsolutePath(Vertex vertex) {
		String refinement = getRefinement(vertex);
		if (refinement == null) {
			return null;
		}

		// get the path from the refinement
		IPath path = new Path(refinement);
		if (path.isAbsolute() == false) {
			IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
			IFile file = root.getFileForLocation(new Path(vertex.getParent()
					.getFileName()));
			path = file.getParent().getFullPath().append(path);
		}

		return path;
	}

	@Override
	public IFile getRefinementFile(Vertex vertex) {
		IPath path = getRefinementAbsolutePath(vertex);
		IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
		IResource resource = root.findMember(path);
		if (resource instanceof IFile) {
			return (IFile) resource;
		} else {
			return null;
		}
	}

	@Override
	public boolean isRefinable(Vertex vertex) {
		if (vertex != null) {
			List<Parameter> parameters = vertex.getParameters();
			for (Parameter parameter : parameters) {
				if (parameter.getName().equals(ObjectType.PARAMETER_REFINEMENT)) {
					return true;
				}
			}
		}

		return false;
	}

}
