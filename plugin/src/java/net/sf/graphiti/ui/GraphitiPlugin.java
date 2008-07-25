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

import net.sf.graphiti.model.DocumentConfiguration;
import net.sf.graphiti.parsers.OntologyLoader;

import org.eclipse.core.runtime.ILog;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.swt.graphics.Image;
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
	private DocumentConfiguration configuration;

	/**
	 * The constructor
	 */
	public GraphitiPlugin() {
		plugin = this;
	}

	/**
	 * Returns the root of the document configuration tree.
	 * 
	 * @return A {@link DocumentConfiguration} that is the root of the document
	 *         configuration tree.
	 */
	public DocumentConfiguration getConfiguration() {
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext
	 * )
	 */
	@Override
	public void start(BundleContext context) throws Exception {
		super.start(context);
		OntologyLoader ontLoader = new OntologyLoader(context);
		configuration = ontLoader.getConfiguration();
		plugin = this;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext
	 * )
	 */
	@Override
	public void stop(BundleContext context) throws Exception {
		plugin = null;
		super.stop(context);
	}
}
