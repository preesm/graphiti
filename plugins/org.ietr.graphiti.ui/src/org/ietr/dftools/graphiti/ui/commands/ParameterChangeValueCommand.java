/*******************************************************************************
 * Copyright or © or Copr. IETR/INSA - Rennes (2008 - 2017) :
 *
 * Antoine Morvan <antoine.morvan@insa-rennes.fr> (2017)
 * Clément Guy <clement.guy@insa-rennes.fr> (2014)
 * Matthieu Wipliez <matthieu.wipliez@insa-rennes.fr> (2008 - 2011)
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
package org.ietr.dftools.graphiti.ui.commands;

import org.eclipse.gef.commands.Command;
import org.ietr.dftools.graphiti.model.AbstractObject;

/**
 * This class provides a command that changes the value of the currently
 * selected parameter.
 * 
 * @author Matthieu Wipliez
 * 
 */
public class ParameterChangeValueCommand extends Command {

	final private String label;

	/**
	 * Set by {@link #setValue(String, Object)}.
	 */
	private String name;

	/**
	 * The new value.
	 */
	private Object newValue;

	/**
	 * The old value.
	 */
	private Object oldValue;

	/**
	 * The property bean we're modifying.
	 */
	private AbstractObject source;

	/**
	 * Creates a new add parameter command.
	 * 
	 * @param newValue
	 *            The value.
	 */
	public ParameterChangeValueCommand(AbstractObject source, String label) {
		this.source = source;
		this.label = label;
	}

	@Override
	public void execute() {
		oldValue = source.setValue(name, newValue);
	}

	@Override
	public String getLabel() {
		return label;
	}

	/**
	 * Sets the value of the parameter whose name is given to the given
	 * value.
	 * 
	 * @param name
	 *            The parameter name.
	 * @param value
	 *            Its new value.
	 */
	public void setValue(String name, Object value) {
		this.name = name;
		this.newValue = value;
	}

	@Override
	public void undo() {
		source.setValue(name, oldValue);
	}

}
