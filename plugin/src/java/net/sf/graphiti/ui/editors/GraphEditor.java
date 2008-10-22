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
package net.sf.graphiti.ui.editors;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.EventObject;

import net.sf.graphiti.io.GenericGraphFileParser;
import net.sf.graphiti.io.GenericGraphFileWriter;
import net.sf.graphiti.model.Graph;
import net.sf.graphiti.ui.GraphitiPlugin;
import net.sf.graphiti.ui.actions.AutomaticallyLayoutAction;
import net.sf.graphiti.ui.actions.CopyAction;
import net.sf.graphiti.ui.actions.CutAction;
import net.sf.graphiti.ui.actions.ExportToGraphMLAction;
import net.sf.graphiti.ui.actions.OpenRefinementNewTabAction;
import net.sf.graphiti.ui.actions.PasteAction;
import net.sf.graphiti.ui.actions.SetRefinementAction;
import net.sf.graphiti.ui.editparts.EditPartFactoryImpl;
import net.sf.graphiti.ui.editparts.GraphEditPart;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.gef.ContextMenuProvider;
import org.eclipse.gef.DefaultEditDomain;
import org.eclipse.gef.GraphicalViewer;
import org.eclipse.gef.MouseWheelHandler;
import org.eclipse.gef.MouseWheelZoomHandler;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CommandStack;
import org.eclipse.gef.dnd.TemplateTransferDragSourceListener;
import org.eclipse.gef.editparts.ScalableFreeformRootEditPart;
import org.eclipse.gef.editparts.ZoomManager;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gef.ui.actions.ActionRegistry;
import org.eclipse.gef.ui.actions.PrintAction;
import org.eclipse.gef.ui.actions.SelectAllAction;
import org.eclipse.gef.ui.actions.ZoomInAction;
import org.eclipse.gef.ui.actions.ZoomOutAction;
import org.eclipse.gef.ui.palette.FlyoutPaletteComposite;
import org.eclipse.gef.ui.palette.PaletteViewer;
import org.eclipse.gef.ui.palette.PaletteViewerProvider;
import org.eclipse.gef.ui.parts.GraphicalEditorWithFlyoutPalette;
import org.eclipse.gef.ui.parts.SelectionSynchronizer;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.swt.SWT;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.IShowEditorInput;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.actions.WorkspaceModifyOperation;
import org.eclipse.ui.dialogs.SaveAsDialog;
import org.eclipse.ui.part.FileEditorInput;
import org.eclipse.ui.views.contentoutline.IContentOutlinePage;

public class GraphEditor extends GraphicalEditorWithFlyoutPalette implements
		IShowEditorInput {

	/**
	 * The editor ID
	 */
	public static final String ID = "net.sf.graphiti.ui.editors.GraphEditor";

	private Graph graph;

	private ZoomManager manager;

	private ThumbnailOutlinePage outlinePage;

	private PaletteRoot paletteRoot;

	private IStatus status;

	/**
	 * Create an editor
	 */
	public GraphEditor() {
		setEditDomain(new DefaultEditDomain(this));
		getPalettePreferences().setPaletteState(
				FlyoutPaletteComposite.STATE_PINNED_OPEN);
	}

	/**
	 * Automatically layout the graph edited with the given direction.
	 * 
	 * @param direction
	 *            The direction, one of:
	 *            <UL>
	 *            <LI>{@link org.eclipse.draw2d.PositionConstants#EAST}
	 *            <LI>{@link org.eclipse.draw2d.PositionConstants#SOUTH}
	 *            </UL>
	 */
	public void automaticallyLayout(int direction) {
		GraphEditPart doc = (GraphEditPart) getGraphicalViewer()
				.getRootEditPart().getContents();
		doc.automaticallyLayoutGraphs(direction);
	}

	@Override
	public void commandStackChanged(EventObject event) {
		firePropertyChange(PROP_DIRTY);
		super.commandStackChanged(event);
	}

	@Override
	protected void configureGraphicalViewer() {
		double[] zoomLevels;
		ArrayList<String> zoomContributions;

		super.configureGraphicalViewer();
		GraphicalViewer viewer = getGraphicalViewer();
		viewer.setEditPartFactory(new EditPartFactoryImpl());

		ScalableFreeformRootEditPart rootEditPart = new ScalableFreeformRootEditPart();
		viewer.setRootEditPart(rootEditPart);

		manager = rootEditPart.getZoomManager();
		getActionRegistry().registerAction(new ZoomInAction(manager));
		getActionRegistry().registerAction(new ZoomOutAction(manager));

		// List of possible zoom levels. 1 = 100%
		zoomLevels = new double[] { 0.1, 0.15, 0.25, 0.5, 0.75, 1.0, 1.5, 2.0 };
		manager.setZoomLevels(zoomLevels);

		// Predefined zoom levels
		zoomContributions = new ArrayList<String>();
		zoomContributions.add(ZoomManager.FIT_ALL);
		zoomContributions.add(ZoomManager.FIT_HEIGHT);
		zoomContributions.add(ZoomManager.FIT_WIDTH);
		manager.setZoomLevelContributions(zoomContributions);

		viewer.setProperty(MouseWheelHandler.KeyGenerator.getKey(SWT.CTRL),
				MouseWheelZoomHandler.SINGLETON);

		// Context menu
		ContextMenuProvider provider = new GraphEditorContextMenuProvider(
				viewer, getActionRegistry());
		viewer.setContextMenu(provider);
	}

	@Override
	@SuppressWarnings("unchecked")
	protected void createActions() {
		// create actions that will be used inside the editor, for instance in a
		// contextual menu
		super.createActions();

		ActionRegistry registry = getActionRegistry();
		Class<?> actions[] = { AutomaticallyLayoutAction.class,
				CopyAction.class, CutAction.class, ExportToGraphMLAction.class,
				OpenRefinementNewTabAction.class, PasteAction.class,
				PrintAction.class, SelectAllAction.class,
				SetRefinementAction.class };

		// Constructs all actions
		for (Class<?> clz : actions) {
			try {
				Constructor<?> ctor = clz.getConstructor(IWorkbenchPart.class);

				IAction action = (IAction) ctor.newInstance(this);
				registry.registerAction(action);
				getSelectionActions().add(action.getId());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	protected PaletteViewerProvider createPaletteViewerProvider() {
		return new PaletteViewerProvider(getEditDomain()) {
			@Override
			protected void configurePaletteViewer(PaletteViewer viewer) {
				super.configurePaletteViewer(viewer);
				viewer
						.addDragSourceListener(new TemplateTransferDragSourceListener(
								viewer));
			}
		};
	}

	@Override
	public void doSave(IProgressMonitor monitor) {
		IFile file = ((IFileEditorInput) getEditorInput()).getFile();
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		GenericGraphFileWriter writer = new GenericGraphFileWriter(graph);
		writer.write(out);
		try {
			file.setContents(new ByteArrayInputStream(out.toByteArray()), true,
					false, monitor);
			try {
				out.close();
			} catch (IOException e) {
				// Can never occur on a ByteArrayOutputStream
			}
			getCommandStack().markSaveLocation();
		} catch (CoreException e) {
			monitor.setCanceled(true);
			e.printStackTrace();
		}
	}

	@Override
	public void doSaveAs() {
		SaveAsDialog dialog = new SaveAsDialog(getSite().getWorkbenchWindow()
				.getShell());
		dialog.setOriginalFile(((IFileEditorInput) getEditorInput()).getFile());
		dialog.open();
		IPath path = dialog.getResult();

		if (path == null)
			return;

		IWorkspace workspace = ResourcesPlugin.getWorkspace();
		final IFile file = workspace.getRoot().getFile(path);

		WorkspaceModifyOperation op = new WorkspaceModifyOperation() {
			public void execute(final IProgressMonitor monitor)
					throws CoreException {
				ByteArrayOutputStream out = new ByteArrayOutputStream();
				GenericGraphFileWriter writer = new GenericGraphFileWriter(
						graph);
				writer.write(out);
				try {
					file.create(new ByteArrayInputStream(out.toByteArray()),
							true, monitor);
					try {
						out.close();
					} catch (IOException e) {
						// Can never occur on a ByteArrayOutputStream
					}
				} catch (CoreException e) {
					monitor.setCanceled(true);
					e.printStackTrace();
				}
			}
		};

		try {
			new ProgressMonitorDialog(getSite().getWorkbenchWindow().getShell())
					.run(false, true, op);
			setInput(new FileEditorInput((IFile) file));
			getCommandStack().markSaveLocation();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Executes the given command.
	 * 
	 * @param command
	 */
	public void executeCommand(Command command) {
		CommandStack stack = getEditDomain().getCommandStack();
		stack.execute(command);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Object getAdapter(Class type) {
		if (type == ZoomManager.class) {
			return ((ScalableFreeformRootEditPart) getGraphicalViewer()
					.getRootEditPart()).getZoomManager();
		} else if (type == IContentOutlinePage.class) {
			outlinePage = new ThumbnailOutlinePage(this);
			return outlinePage;
		} else {
			return super.getAdapter(type);
		}
	}

	/**
	 * Returns the contents of this editor.
	 * 
	 * @return The contents of this editor.
	 */
	public Graph getContents() {
		return graph;
	}

	@Override
	public GraphicalViewer getGraphicalViewer() {
		return super.getGraphicalViewer();
	}

	@Override
	protected PaletteRoot getPaletteRoot() {
		if (paletteRoot == null) {
			paletteRoot = GraphitiPalette.getPaletteRoot(graph);
		}
		return paletteRoot;
	}

	@Override
	public SelectionSynchronizer getSelectionSynchronizer() {
		return super.getSelectionSynchronizer();
	}

	/**
	 * Gives the current zoom factor
	 * 
	 * @return double
	 */
	public double getZoom() {
		return manager.getZoom();
	}

	@Override
	protected void initializeGraphicalViewer() {
		GraphicalViewer viewer = getGraphicalViewer();

		if (graph == null) {
			viewer.setContents(status);
		} else {
			viewer.setContents(graph);
			automaticallyLayout(PositionConstants.EAST);
		}
	}

	@Override
	public boolean isSaveAsAllowed() {
		return true;
	}

	@Override
	protected void setInput(IEditorInput input) {
		super.setInput(input);

		IFile file = ((IFileEditorInput) input).getFile();
		setPartName(file.getName());
		try {
			GenericGraphFileParser parser = new GenericGraphFileParser(
					GraphitiPlugin.getDefault().getConfigurations());
			graph = parser.parse(file);

			// Updates the palette
			getEditDomain().setPaletteRoot(getPaletteRoot());

			firePropertyChange(PROP_INPUT);
		} catch (Throwable exception) {
			status = new Status(Status.ERROR, GraphitiPlugin.PLUGIN_ID,
					"An error occurred while parsing the file", exception);
		}
	}

	/**
	 * Sets the zoom to see the entire width of the graph in editor
	 */
	public void setWidthZoom() {
		manager.setZoomAsText(ZoomManager.FIT_WIDTH);
	}

	@Override
	public void showEditorInput(IEditorInput editorInput) {
		setInput(editorInput);
		initializeGraphicalViewer();
	}
}
