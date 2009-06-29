package net.sf.graphiti.validator;

import org.eclipse.core.resources.IFile;

import net.sf.graphiti.model.Graph;
import net.sf.graphiti.model.IValidator;

public class IDLValidator implements IValidator{

	@Override
	public boolean validate(Graph graph, IFile file) {
		return true;
	}

}
