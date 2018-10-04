package org.ietr.dftools.graphiti.ui.editparts;

import java.beans.PropertyChangeListener;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.ui.views.properties.IPropertySource;
import org.eclipse.ui.views.properties.tabbed.ITabbedPropertySheetPageContributor;
import org.ietr.dftools.graphiti.model.AbstractObject;
import org.ietr.dftools.graphiti.model.Graph;
import org.ietr.dftools.graphiti.ui.properties.ModelPropertySource;
import org.ietr.dftools.graphiti.ui.properties.PropertiesConstants;

/**
 *
 * @author anmorvan
 *
 */
public abstract class AbstractGraphitiEditPart extends AbstractGraphicalEditPart
    implements PropertyChangeListener, ITabbedPropertySheetPageContributor {

  /*
   * (non-Javadoc)
   *
   * @see org.eclipse.gef.editparts.AbstractGraphicalEditPart#deactivate()
   */
  @Override
  public void deactivate() {
    super.deactivate();
    ((Graph) getModel()).removePropertyChangeListener(this);
  }

  /*
   * (non-Javadoc)
   *
   * @see org.eclipse.gef.editparts.AbstractGraphicalEditPart#getAdapter(java.lang.Class)
   */
  @Override
  @SuppressWarnings("rawtypes")
  public Object getAdapter(final Class adapter) {
    if (adapter == IPropertySource.class) {
      return new ModelPropertySource((AbstractObject) getModel());
    }
    return super.getAdapter(adapter);
  }

  /*
   * (non-Javadoc)
   *
   * @see org.eclipse.ui.views.properties.tabbed.ITabbedPropertySheetPageContributor#getContributorId()
   */
  @Override
  public String getContributorId() {
    return PropertiesConstants.CONTRIBUTOR_ID;
  }

}
