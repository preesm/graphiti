/**
 * Copyright or © or Copr. IETR/INSA - Rennes (2008 - 2018) :
 *
 * Antoine Morvan <antoine.morvan@insa-rennes.fr> (2017 - 2018)
 * Clément Guy <clement.guy@insa-rennes.fr> (2014)
 * Matthieu Wipliez <matthieu.wipliez@insa-rennes.fr> (2008 - 2011)
 *
 * This software is a computer program whose purpose is to help prototyping
 * parallel applications using dataflow formalism.
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
 */
package org.ietr.dftools.graphiti;

import java.util.Collection;
import java.util.Map;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.ietr.dftools.graphiti.io.ConfigurationParser;
import org.ietr.dftools.graphiti.model.Configuration;
import org.osgi.framework.BundleContext;

/**
 * The activator class controls the plug-in life cycle.
 */
public class GraphitiModelPlugin extends AbstractUIPlugin {

  /**
   * The shared instance.
   */
  private static GraphitiModelPlugin plugin;

  /**
   * The plug-in ID.
   */
  public static final String PLUGIN_ID = "org.ietr.graphiti.model";

  /**
   * Returns the shared instance.
   *
   * @return the shared instance
   */
  public static GraphitiModelPlugin getDefault() {
    return GraphitiModelPlugin.plugin;
  }

  /** map of configuration name to configuration. */
  private Map<String, Configuration> configurations;

  /**
   * Returns the configuration with the given name.
   *
   * @param name
   *          configuration name
   * @return the configuration
   */
  public Configuration getConfiguration(final String name) {
    return this.configurations.get(name);
  }

  /**
   * Returns the list of configurations.
   *
   * @return A reference to the {@link Configuration} list.
   */
  public Collection<Configuration> getConfigurations() {
    return this.configurations.values();
  }

  /**
   * Parses the configurations available and (re)loads them.
   *
   * @throws CoreException
   *           If the file formats cannot be added to Eclipse content type system.
   */
  public void loadConfigurations() throws CoreException {
    final ConfigurationParser parser = new ConfigurationParser();
    this.configurations = parser.getConfigurations();
  }

  /*
   * (non-Javadoc)
   *
   * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
   */
  @Override
  public void start(final BundleContext context) throws Exception {
    super.start(context);
    GraphitiModelPlugin.plugin = this;
    loadConfigurations();
  }

  /*
   * (non-Javadoc)
   *
   * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
   */
  @Override
  public void stop(final BundleContext context) throws Exception {
    GraphitiModelPlugin.plugin = null;
    super.stop(context);
  }

}
