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
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.EventObject;

import net.sf.graphiti.model.GraphitiDocument;
import net.sf.graphiti.parsers.GenericGraphFileParser;
import net.sf.graphiti.ui.Activator;
import net.sf.graphiti.ui.actions.CopyAction;
import net.sf.graphiti.ui.actions.CutAction;
import net.sf.graphiti.ui.actions.PasteAction;
import net.sf.graphiti.ui.editparts.EditPartFactoryImpl;
import net.sf.graphiti.ui.editparts.GraphEditPart;
import net.sf.graphiti.ui.editparts.GraphitiDocumentEditPart;
import net.sf.graphiti.writer.GenericGraphFileWriter;
import net.sf.graphiti.writer.GenericGraphFileWriterBis;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.draw2d.FigureCanvas;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.ContextMenuProvider;
import org.eclipse.gef.DefaultEditDomain;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.GraphicalViewer;
import org.eclipse.gef.MouseWheelHandler;
import org.eclipse.gef.MouseWheelZoomHandler;
import org.eclipse.gef.RootEditPart;
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
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.swt.SWT;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.actions.WorkspaceModifyOperation;
import org.eclipse.ui.dialogs.SaveAsDialog;
import org.eclipse.ui.part.FileEditorInput;

public class GraphEditor extends GraphicalEditorWithFlyoutPalette {

	/**
	 * The editor ID
	 */
	public static final String ID = "net.sf.graphiti.ui.editors.GraphEditor";

	private GraphitiDocument document;

	private ZoomManager manager;

	// protected ThumbnailOutlinePage outlinePage;

	/**
	 * Create an editor
	 */
	public GraphEditor() {
		setEditDomain(new DefaultEditDomain(this));
		getPalettePreferences().setPaletteState(
				FlyoutPaletteComposite.STATE_PINNED_OPEN);
	}

	@Override
	public void commandStackChanged(EventObject event) {
		// The dirty property implies a star near the editor name
		firePropertyChange(IEditorPart.PROP_DIRTY);
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
		super.createActions(); // adds undo, redo, delete,
		// select-all, save, etc actions
		ActionRegistry registry = getActionRegistry();

		IAction copyAction = new CopyAction(this);
		registry.registerAction(copyAction);
		getSelectionActions().add(copyAction.getId());

		IAction cutAction = new CutAction(this);
		registry.registerAction(cutAction);
		getSelectionActions().add(cutAction.getId());

		IAction pasteAction = new PasteAction(this);
		registry.registerAction(pasteAction);
		getSelectionActions().add(pasteAction.getId());

		IAction selectAllAction = new SelectAllAction(this);
		registry.registerAction(selectAllAction);
		getSelectionActions().add(selectAllAction.getId());

		IAction printAction = new PrintAction(this);
		registry.registerAction(printAction);
		getSelectionActions().add(printAction.getId());

		// IAction routeAllAction = new RouteAction(this);
		// registry.registerAction(routeAllAction);
		// getSelectionActions().add(routeAllAction.getId());
		//
		// IAction orpAllAction = new OrphanizeAction(this);
		// registry.registerAction(orpAllAction);
		// getSelectionActions().add(orpAllAction.getId());
		//
		// IAction changeLayoutManagerAction = new AutomaticLayoutAction(this);
		// registry.registerAction(changeLayoutManagerAction);
		// getSelectionActions().add(changeLayoutManagerAction.getId());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seeorg.eclipse.gef.ui.parts.GraphicalEditorWithFlyoutPalette#
	 * createPaletteViewerProvider()
	 */
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
		GenericGraphFileWriterBis writer = new GenericGraphFileWriterBis(
				document);
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
						document);
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
	 * finds the container from its location in the editor
	 * 
	 * @param p
	 * @return The GraphEditPart at the Point p
	 */
	public GraphEditPart findScreenGraph(Point p) {
		EditPartViewer epv = getRootEditPart().getViewer();
		EditPart ep = epv.findObjectAt(p);

		while (ep != null && !(ep instanceof GraphEditPart)) {
			ep = ep.getParent();
		}

		return (GraphEditPart) ep;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Object getAdapter(Class type) {
		if (type == ZoomManager.class) {
			return ((ScalableFreeformRootEditPart) getGraphicalViewer()
					.getRootEditPart()).getZoomManager();
		}
		// else if (type == IContentOutlinePage.class) {
		// outlinePage = new ThumbnailOutlinePage();
		// return outlinePage;
		// }
		else {
			return super.getAdapter(type);
		}
	}

	public FigureCanvas getCanvas() {
		return (FigureCanvas) getGraphicalViewer().getControl();
	}

	public GraphicalViewer getGraphicalViewer() {
		return super.getGraphicalViewer();
	}

	@Override
	protected PaletteRoot getPaletteRoot() {
		GraphitiPalette palette = new GraphitiPalette(document);
		return palette.getPaletteRoot();
	}

	public RootEditPart getRootEditPart() {
		return getGraphicalViewer().getRootEditPart();
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
		// viewer.addDropTargetListener(new DropTargetListener(
		// getGraphicalViewer()));

		// Color test = PreesmColors.algorithm;
		// getGraphicalViewer().getControl().setBackground(test);
		viewer.setContents(document);
		GraphitiDocumentEditPart doc = (GraphitiDocumentEditPart) getRootEditPart()
				.getContents();
		doc.automaticallyLayoutGraphs();
	}

	@Override
	public boolean isSaveAsAllowed() {
		return true;
	}

	public void refreshGraphicalEditor() {
		getGraphicalViewer().getContents().refresh();
	}

	protected void setInput(IEditorInput input) {
		superSetInput(input);

		IFile file = ((IFileEditorInput) input).getFile();
		try {
			GenericGraphFileParser parser = new GenericGraphFileParser(
					Activator.getConfigurations());
			document = parser.parse(file);

			// Updates the palette
			getEditDomain().setPaletteRoot(getPaletteRoot());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Sets the zoom to see the entire width of the graph in editor
	 */
	public void setWidthZoom() {
		manager.setZoomAsText(ZoomManager.FIT_WIDTH);
	}

	protected void superSetInput(IEditorInput input) {
		// The workspace never changes for an editor. So, removing and re-adding
		// the resourceListener is not necessary. But it is being done here for
		// the sake of proper implementation. Plus, the resourceListener needs
		// to be added to the workspace the first time around.
		if (getEditorInput() != null) {
			// IFile file = ((IFileEditorInput) getEditorInput()).getFile();
			//file.getWorkspace().removeResourceChangeListener(resourceListener)
			// ;
		}

		super.setInput(input);

		if (getEditorInput() != null) {
			IFile file = ((IFileEditorInput) getEditorInput()).getFile();
			// file.getWorkspace().addResourceChangeListener(resourceListener);
			setPartName(file.getName());
		}
	}
}
