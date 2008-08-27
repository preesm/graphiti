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
package net.sf.graphiti.ui.editpolicies;

import java.util.List;

import net.sf.graphiti.ui.commands.AddCommand;
import net.sf.graphiti.ui.commands.CreateCommand;
import net.sf.graphiti.ui.commands.MoveOrResizeCommand;
import net.sf.graphiti.ui.commands.OpenRefinementNewTabCommand;
import net.sf.graphiti.ui.editparts.GraphEditPart;
import net.sf.graphiti.ui.editparts.VertexEditPart;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.editpolicies.NonResizableEditPolicy;
import org.eclipse.gef.editpolicies.ResizableEditPolicy;
import org.eclipse.gef.editpolicies.XYLayoutEditPolicy;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.gef.requests.CreateRequest;
import org.eclipse.jface.viewers.StructuredSelection;

/**
 * This class provides the policy of the layout used in the editor view. Namely
 * it implements the <code>createChangeConstraintCommand</code> and
 * <code>getCreateCommand</code> methods to move and create a graph
 * respectively.
 * 
 * @author Samuel Beaussier
 * @author Nicolas Isch
 * @author Matthieu Wipliez
 */
public class LayoutPolicy extends XYLayoutEditPolicy {

	public Command getCommand(Request request) {
		if (REQ_OPEN.equals(request.getType())) {
			OpenRefinementNewTabCommand command = new OpenRefinementNewTabCommand();
			command.setSelection(new StructuredSelection(getHost()));
			return command;
		} else {
			return super.getCommand(request);
		}
	}

	private Command createAddCommand(EditPart child, Point moveDelta) {
		AddCommand add = new AddCommand();
		add.setChild(child.getModel());
		add.setMoveDelta(moveDelta);
		add.setParent(getHost().getModel());
		return add;
	}

	@Override
	protected Command createChangeConstraintCommand(EditPart child,
			Object constraint) {
		MoveOrResizeCommand command = null;

		if (child instanceof GraphEditPart || child instanceof VertexEditPart) {
			command = new MoveOrResizeCommand();
			command.setModel(child.getModel());
			command.setConstraint((Rectangle) constraint);
		}

		return command;
	}

	@Override
	protected EditPolicy createChildEditPolicy(EditPart child) {
		if (child instanceof GraphEditPart || child instanceof VertexEditPart) {
			return new ResizableEditPolicy();
		} else {
			return new NonResizableEditPolicy();
		}
	}

	@Override
	protected Command getAddCommand(Request req) {
		ChangeBoundsRequest request = (ChangeBoundsRequest) req;
		List<?> editParts = request.getEditParts();
		CompoundCommand command = new CompoundCommand();
		for (Object editPart : editParts) {
			command.add(createAddCommand((EditPart) editPart, request
					.getMoveDelta()));
		}
		return command.unwrap();
	}

	@Override
	protected Command getCreateCommand(CreateRequest request) {
		// if (createRequest.getExtendedData() != null) {
		// DragHelper dh = (DragHelper) createRequest.getExtendedData().get(
		// "DragHelper");
		// if (model instanceof Graph) {
		// if (((GraphGef) model).getDnd()) {
		//
		// if (dh.getDragScreenGraph() == null) {
		// GraphContainerModel container = ((GenericEditor) PlatformUI
		// .getWorkbench().getActiveWorkbenchWindow()
		// .getActivePage().getActiveEditor())
		// .getGraphContainer();
		//
		// DndCreateGraphCommand cmd = new DndCreateGraphCommand(
		// container, (GraphGef) model);
		// cmd
		// .setLocation(new Point(
		// ((Rectangle) getConstraintFor(createRequest)).x,
		// ((Rectangle) getConstraintFor(createRequest)).y));
		// cmd.findContainer(createRequest.getLocation());
		//
		// return cmd;
		// } else {
		// GraphContainerModel container = ((GenericEditor) PlatformUI
		// .getWorkbench().getActiveWorkbenchWindow()
		// .getActivePage().getActiveEditor())
		// .getGraphContainer();
		//
		// DndMoveGraphCommand cmd = new DndMoveGraphCommand(
		// container, (ScreenGraphModel) dh
		// .getDragScreenGraph().getModel(),
		// (GraphGef) dh.getData(), (GraphGef) model);
		// cmd
		// .setLocation(new Point(
		// ((Rectangle) getConstraintFor(createRequest)).x,
		// ((Rectangle) getConstraintFor(createRequest)).y));
		// cmd.findContainer(createRequest.getLocation());
		//
		// return cmd;
		// }
		// }
		// } else if (model instanceof ScreenGraphModel) {
		//
		// DndMoveScreenGraphCommand cmd = new DndMoveScreenGraphCommand(
		// dh.getName());
		// cmd.findDropEditPart(createRequest.getLocation());
		//
		// return cmd;
		// }
		// }

		Object newObject = request.getNewObject();
		CreateCommand command = new CreateCommand();

		command.setNewObject(newObject);
		command.setModel(getHost().getModel());
		command.setBounds((Rectangle) getConstraintFor(request));

		return command;
	}
}
