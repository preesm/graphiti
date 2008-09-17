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

import java.util.Set;

import net.sf.graphiti.model.Configuration;
import net.sf.graphiti.model.EdgeCreationFactory;
import net.sf.graphiti.model.Graph;
import net.sf.graphiti.model.Vertex;
import net.sf.graphiti.model.VertexCreationFactory;
import net.sf.graphiti.ontology.OntologyFactory;
import net.sf.graphiti.ontology.enums.Shape;
import net.sf.graphiti.ontology.types.EdgeType;
import net.sf.graphiti.ontology.types.VertexType;
import net.sf.graphiti.ui.GraphitiPlugin;
import net.sf.graphiti.ui.figure.VertexFigure;
import net.sf.graphiti.ui.figure.shapes.IShape;

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.SWTGraphics;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.gef.palette.ConnectionCreationToolEntry;
import org.eclipse.gef.palette.CreationToolEntry;
import org.eclipse.gef.palette.MarqueeToolEntry;
import org.eclipse.gef.palette.PaletteDrawer;
import org.eclipse.gef.palette.PaletteGroup;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gef.palette.PaletteSeparator;
import org.eclipse.gef.palette.SelectionToolEntry;
import org.eclipse.gef.palette.ToolEntry;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.widgets.Display;

/**
 * Creates the Palette on the GUI with all the tools and the appropriate icons
 * icons have to be in the same directory as the Model
 * 
 * @author Samuel Beaussier & Nicolas Isch
 * 
 */
public class GraphitiPalette {

	/**
	 * Add the different edge types.
	 * 
	 * @param graph
	 *            The graph used to configure this palette.
	 * @param paletteGroup
	 *            The palette group.
	 */
	private static void addEdgeTypes(Graph graph, PaletteRoot paletteModel) {
		if (graph != null) {
			ImageDescriptor id = ImageDescriptor.createFromImage(GraphitiPlugin
					.getImage("icons/dependency.gif"));
			PaletteDrawer depDrawer = new PaletteDrawer("Connections");

			Configuration config = graph.getConfiguration();
			OntologyFactory factory = config.getOntologyFactory();
			Set<EdgeType> edgeTypes = factory.getEdgeTypes();
			for (EdgeType type : edgeTypes) {
				String typeStr = type.hasName();

				ToolEntry tool = new ConnectionCreationToolEntry(typeStr,
						"Create a new " + typeStr, new EdgeCreationFactory(
								typeStr), id, ImageDescriptor
								.getMissingImageDescriptor());

				depDrawer.add(tool);
			}

			// Add connection tool
			paletteModel.add(depDrawer);
		}
	}

	/**
	 * Add the different vertex types.
	 * 
	 * @param graph
	 *            The graph used to configure this palette.
	 * @param paletteGroup
	 *            The palette group.
	 */
	private static void addVertexTypes(Graph graph, PaletteGroup paletteGroup) {
		if (graph != null) {
			Configuration config = graph.getConfiguration();
			OntologyFactory factory = config.getOntologyFactory();
			Set<VertexType> vertexTypes = factory.getVertexTypes();
			for (VertexType type : vertexTypes) {
				String typeStr = type.hasName();

				ImageDescriptor id = getImageDescriptorFromType(config, typeStr);

				ToolEntry tool = new CreationToolEntry(typeStr, "Create a new "
						+ typeStr, new VertexCreationFactory(config, typeStr),
						id, null);

				paletteGroup.add(tool);
			}
		}
	}

	/**
	 * Returns a new image descriptor from the given document configuration and
	 * type.
	 * 
	 * @param config
	 *            The document configuration containing the ontology we want to
	 *            use.
	 * @param typeStr
	 *            A string representation of the vertex type.
	 * @return A new {@link ImageDescriptor}.
	 */
	private static ImageDescriptor getImageDescriptorFromType(
			Configuration config, String typeStr) {
		// attributes
		int width = (Integer) config.getVertexAttribute(typeStr,
				Vertex.ATTRIBUTE_WIDTH);
		int height = (Integer) config.getVertexAttribute(typeStr,
				Vertex.ATTRIBUTE_HEIGHT);
		Color color = (Color) config.getVertexAttribute(typeStr,
				Vertex.ATTRIBUTE_COLOR);
		IShape shape = ((Shape) config.getVertexAttribute(typeStr,
				Vertex.ATTRIBUTE_SHAPE)).getShape();

		// adjust width and height
		double ratio = (double) width / (double) height;
		width = 16;
		height = (int) ((double) width / ratio);

		// Creates a new vertex figure
		VertexFigure figure = new VertexFigure(null, new Dimension(width,
				height), color, shape);

		// Creates a new image of width x height on the current display
		Image image = new Image(Display.getCurrent(), width, height);

		// Paints the figure on it
		GC gc = new GC(image);
		Graphics graphics = new SWTGraphics(gc);
		figure.paint(graphics);

		// Get the image data back
		ImageData data = image.getImageData();
		ImageDescriptor id = ImageDescriptor.createFromImageData(data);

		// Disposes image (and GC btw) and SWT graphics
		image.dispose();
		graphics.dispose();

		return id;
	}

	/**
	 * Gets a palette root which is configured by the given {@link Graph}. If
	 * <code>graph == null</code>, <code>null</code> is returned.
	 * 
	 * @param graph
	 *            The graph used to configure this palette.
	 * @return A {@link PaletteRoot} or <code>null</code>.
	 */
	public static PaletteRoot getPaletteRoot(Graph graph) {
		if (graph == null) {
			return null;
		}

		PaletteRoot paletteModel = new PaletteRoot();
		PaletteGroup toolGroup = new PaletteGroup("Tools");

		// Add a selection tool to the group
		ToolEntry tool = new SelectionToolEntry();
		toolGroup.add(tool);
		paletteModel.setDefaultEntry(tool);

		// Add a marquee tool to the group
		toolGroup.add(new MarqueeToolEntry());

		// Add an unnamed separator to the group
		toolGroup.add(new PaletteSeparator());

		addVertexTypes(graph, toolGroup);
		paletteModel.add(toolGroup);
		toolGroup.add(new PaletteSeparator());
		addEdgeTypes(graph, paletteModel);

		return paletteModel;
	}
}
