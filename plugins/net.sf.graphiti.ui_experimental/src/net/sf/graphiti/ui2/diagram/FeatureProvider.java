package net.sf.graphiti.ui2.diagram;

import net.sf.graphiti.ui2.features.AddVertexFeature;
import net.sf.graphiti.ui2.features.CreateVertexFeature;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.graphiti.dt.IDiagramTypeProvider;
import org.eclipse.graphiti.features.IAddFeature;
import org.eclipse.graphiti.features.ICreateFeature;
import org.eclipse.graphiti.features.context.IAddContext;
import org.eclipse.graphiti.ui.features.DefaultFeatureProvider;

public class FeatureProvider extends DefaultFeatureProvider {

	public FeatureProvider(IDiagramTypeProvider dtp) {
		super(dtp);
	}

	@Override
	public IAddFeature getAddFeature(IAddContext context) {
		// is object for add request a EClass or EReference?
		if (context.getNewObject() instanceof EClass) {
			return new AddVertexFeature(this);
		}
		// else if (context.getNewObject() instanceof EReference) {
		// return new TutorialAddEReferenceFeature(this);
		// }
		return super.getAddFeature(context);
	}

	@Override
	public ICreateFeature[] getCreateFeatures() {
		return new ICreateFeature[] { new CreateVertexFeature(this) };
	}

}
