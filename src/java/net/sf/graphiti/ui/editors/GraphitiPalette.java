package net.sf.graphiti.ui.editors;

import java.util.Set;

import net.sf.graphiti.model.DocumentConfiguration;
import net.sf.graphiti.model.GraphitiDocument;
import net.sf.graphiti.model.VertexCreationFactory;
import net.sf.graphiti.ontology.OntologyFactory;
import net.sf.graphiti.ontology.types.VertexType;
import net.sf.graphiti.ui.Activator;
import net.sf.graphiti.ui.figure.VertexFigure;

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.SWTGraphics;
import org.eclipse.gef.palette.ConnectionCreationToolEntry;
import org.eclipse.gef.palette.CreationToolEntry;
import org.eclipse.gef.palette.MarqueeToolEntry;
import org.eclipse.gef.palette.PaletteDrawer;
import org.eclipse.gef.palette.PaletteGroup;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gef.palette.PaletteSeparator;
import org.eclipse.gef.palette.SelectionToolEntry;
import org.eclipse.gef.palette.ToolEntry;
import org.eclipse.gef.requests.SimpleFactory;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.PaletteData;

/**
 * Creates the Palette on the GUI with all the tools and the appropriate icons
 * icons have to be in the same directory as the Model
 * 
 * @author Samuel Beaussier & Nicolas Isch
 * 
 */
public class GraphitiPalette {

	private GraphitiDocument document;

	/**
	 * Creates a new palette with the given document.
	 * 
	 * @param document
	 *            The graphiti document used to configure this palette. If
	 *            <code>document == null</code>, this palette will have no
	 *            vertex entries.
	 */
	public GraphitiPalette(GraphitiDocument document) {
		this.document = document;
	}

	/**
	 * Add the different vertex types.
	 * 
	 * @param toolGroup
	 *            The palette group.
	 */
	private void addVertexTypes(PaletteGroup toolGroup) {
		if (document != null) {
			DocumentConfiguration config = document.getDocumentConfiguration();
			OntologyFactory factory = config.getOntologyFactory();
			Set<VertexType> vertexTypes = factory.getVertexTypes();
			for (VertexType type : vertexTypes) {
				String typeStr = type.hasStringRepresentation();

				ImageDescriptor id = getImageDescriptorFromType(config, typeStr);

				ToolEntry tool = new CreationToolEntry(typeStr, "Create a new "
						+ typeStr,
						new VertexCreationFactory(document, typeStr), id, null);

				toolGroup.add(tool);
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
	private ImageDescriptor getImageDescriptorFromType(
			DocumentConfiguration config, String typeStr) {
		// Creates a new vertex figure
		VertexFigure figure = new VertexFigure(config, typeStr);

		// Gets width and height
		Integer width = (Integer) config.getVertexAttribute(typeStr, "width");
		Integer height = (Integer) config.getVertexAttribute(typeStr, "height");
		if (width == null || height == null) {
			width = 50;
			height = 50;
		}

		// Creates a new image of widthxheightx24 with RGB mask
		PaletteData palette = new PaletteData(0xFF0000, 0x00FF00, 0x0000FF);
		ImageData data = new ImageData(width, height, 24, palette);
		ImageDescriptor id = ImageDescriptor.createFromImageData(data);
		Image image = id.createImage();

		// Paints the figure on it
		GC gc = new GC(image);
		Graphics graphics = new SWTGraphics(gc);
		figure.paint(graphics);

		// Get the image data back, scaled to 16x16
		data = image.getImageData().scaledTo(16, 16);
		id = ImageDescriptor.createFromImageData(data);
		return id;
	}

	/**
	 * Gives the Palette Model created
	 * 
	 * @return paletteModel
	 */
	public PaletteRoot getPaletteRoot() {
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

		addVertexTypes(toolGroup);
		paletteModel.add(toolGroup);
		toolGroup.add(new PaletteSeparator());

		// Add connection tool
		PaletteDrawer depDrawer = new PaletteDrawer("Connections");
		ImageDescriptor id = ImageDescriptor.createFromImage(Activator
				.getImage("icons/dependency.gif"));
		tool = new ConnectionCreationToolEntry("Connection",
				"Create a connection", new SimpleFactory(null), id,
				ImageDescriptor.getMissingImageDescriptor());
		depDrawer.add(tool);
		paletteModel.add(depDrawer);

		return paletteModel;
	}
}
