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
package net.sf.graphiti.ui;

import java.util.HashSet;
import java.util.Set;

import net.sf.graphiti.model.Configuration;
import net.sf.graphiti.parsers.ConfigurationLoader;
import net.sf.graphiti.ui.preferences.PreferenceConstants;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.ILog;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.content.IContentType;
import org.eclipse.core.runtime.content.IContentTypeManager;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;

/**
 * The activator class controls the plug-in life cycle
 */
public class GraphitiPlugin extends AbstractUIPlugin {

	/**
	 * The shared instance.
	 */
	private static GraphitiPlugin plugin;

	/**
	 * The plug-in ID.
	 */
	public static final String PLUGIN_ID = "net.sf.graphiti";

	/**
	 * Returns the shared instance
	 * 
	 * @return the shared instance
	 */
	public static GraphitiPlugin getDefault() {
		return plugin;
	}

	/**
	 * Returns an image for the image file at the given plug-in relative path.
	 * 
	 * @param path
	 *            the path
	 * @return the image
	 */
	public static Image getImage(String path) {
		ImageRegistry ir = plugin.getImageRegistry();
		Image image = ir.get(path);
		if (image == null) {
			ImageDescriptor id = imageDescriptorFromPlugin(PLUGIN_ID, path);
			image = id.createImage();
			ir.put(path, image);
		}

		return image;
	}

	/**
	 * Returns an image descriptor for the image file at the given plug-in
	 * relative path
	 * 
	 * @param path
	 *            the path
	 * @return the image descriptor
	 */
	public static ImageDescriptor getImageDescriptor(String path) {
		return imageDescriptorFromPlugin(PLUGIN_ID, path);
	}

	/**
	 * The root of the document configuration tree built when the plug-in was
	 * activated.
	 */
	private Configuration configuration;

	/**
	 * The constructor
	 */
	public GraphitiPlugin() {
		plugin = this;
	}

	/**
	 * Associate file extensions defined in the ontologies to the
	 * org.eclipse.core.runtime.xml content type.
	 * 
	 * @param configuration
	 *            The configuration obtained from the ontologies.
	 */
	private void addExtensions(Configuration configuration) {
		// add all file extensions to a set (in case one extension is present
		// several times, such as .xml)
		Set<String> extensionSet = new HashSet<String>();
		for (Configuration config : configuration.getConfigurationList(true)) {
			String[] fileExts = config.getFileExtensions();
			for (String fileExt : fileExts) {
				extensionSet.add(fileExt);
			}
		}

		// add to content type manager
		IContentTypeManager mgr = Platform.getContentTypeManager();
		IContentType contentType = mgr
				.getContentType("org.eclipse.core.runtime.xml");

		for (String fileExt : extensionSet) {
			try {
				contentType.addFileSpec(fileExt,
						IContentType.FILE_EXTENSION_SPEC);
			} catch (CoreException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Returns the root of the document configuration tree.
	 * 
	 * @return A {@link Configuration} that is the root of the document
	 *         configuration tree.
	 */
	public Configuration getConfiguration() {
		return configuration;
	}

	/**
	 * Returns an {@link IStatus} with the ERROR level.
	 * 
	 * @param message
	 *            A message associated with the status.
	 * @return An {@link IStatus} with the ERROR level.
	 */
	public IStatus getErrorStatus(String message) {
		return getStatus(Status.ERROR, message);
	}

	private IStatus getStatus(int severity, String message) {
		Bundle bundle = getBundle();
		String pluginId = Long.toString(bundle.getBundleId());
		IStatus status = new Status(severity, pluginId, message);
		return status;
	}

	/**
	 * Returns an {@link IStatus} with the WARNING level.
	 * 
	 * @param message
	 *            A message associated with the status.
	 * @return An {@link IStatus} with the WARNING level.
	 */
	public IStatus getWarningStatus(String message) {
		return getStatus(Status.WARNING, message);
	}

	/**
	 * Logs the given <code>message</code> with the INFO status.
	 * 
	 * @param message
	 *            A {@link String} to be logged.
	 */
	public void logInfoStatus(String message) {
		ILog log = Platform.getLog(getBundle());
		log.log(getStatus(Status.INFO, message));
	}

	@Override
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;

		IPreferenceStore store = getPreferenceStore();
		if (store.isDefault(PreferenceConstants.PATH)) {
			IWorkbench workbench = PlatformUI.getWorkbench();
			Shell parent = workbench.getDisplay().getActiveShell();
			String message = "Please edit the Graphiti preferences to specify "
					+ "the folder containing the ontologies.";
			MessageDialog.openInformation(parent, "Graphiti configuration",
					message);
		} else {
			ConfigurationLoader loader = new ConfigurationLoader();
			configuration = loader.getConfiguration();
			addExtensions(configuration);
		}
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		plugin = null;
		super.stop(context);
	}
}
