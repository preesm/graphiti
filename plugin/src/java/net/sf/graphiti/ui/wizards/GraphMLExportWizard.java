/**
 * 
 */
package net.sf.graphiti.ui.wizards;

import net.sf.graphiti.model.Graph;

import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.IExportWizard;
import org.eclipse.ui.IWorkbench;

/**
 * This class provides a wizard to export the graph being edited to a GraphML
 * file.
 * 
 * @author Matthieu Wipliez
 * 
 */
public class GraphMLExportWizard extends Wizard implements IExportWizard {

	private GraphMLExportWizardPage mainPage;

	private IStructuredSelection selection;

	/**
	 * Creates a new {@link GraphMLExportWizard}.
	 */
	public GraphMLExportWizard() {
		super();
		setWindowTitle("Export");
	}

	@Override
	public void addPages() {
		super.addPages();
		mainPage = new GraphMLExportWizardPage(selection);
		addPage(mainPage);
	}

	@Override
	public void init(IWorkbench workbench, IStructuredSelection selection) {
		this.selection = selection;
	}

	@Override
	public boolean performFinish() {
		// TODO: write the source file to GraphML.
		String fileName = mainPage.getDestinationFileName();
		Graph graph = mainPage.getGraph();

		System.out.println("Writing " + graph + " to " + fileName);

		return true;
	}

}
