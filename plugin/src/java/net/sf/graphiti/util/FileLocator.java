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
package net.sf.graphiti.util;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.List;

import net.sf.graphiti.ui.GraphitiPlugin;
import net.sf.graphiti.ui.preferences.PreferenceConstants;

import org.eclipse.jface.preference.IPreferenceStore;

/**
 * This class provides a way to get an absolute file name from a file name
 * relative to the configuration folder. The configuration folder is set in the
 * plug-in preferences.
 * 
 * @author Matthieu Wipliez
 * 
 */
public class FileLocator {

	/**
	 * Returns the absolute file name from the given file name relative to the
	 * configuration folder.
	 * 
	 * @param fileName
	 *            A relative file name.
	 * @return An absolute {@link File}.
	 */
	public static synchronized File getFile(String fileName) {
		File file = new File(fileName);
		if (file.isAbsolute()) {
			return file;
		} else {
			GraphitiPlugin plugin = GraphitiPlugin.getDefault();
			if (plugin == null) {
				return file;
			} else {
				IPreferenceStore store = plugin.getPreferenceStore();
				String path = store.getString(PreferenceConstants.PATH);
				return new File(path, fileName);
			}
		}
	}

	/**
	 * Returns a list of absolute file names in the configuration folder that
	 * match the given file pattern.
	 * 
	 * @param filePattern
	 *            A regular expression.
	 * @return A list of absolute file names.
	 */
	public static synchronized List<String> listFiles(String filePattern) {
		GraphitiPlugin plugin = GraphitiPlugin.getDefault();
		if (plugin == null) {
			return new ArrayList<String>();
		} else {
			IPreferenceStore store = plugin.getPreferenceStore();
			String path = store.getString(PreferenceConstants.PATH);
			return listFiles(path, filePattern);
		}
	}

	/**
	 * Returns a list of absolute file names in the given folder that match the
	 * given pattern.
	 * 
	 * @param folder
	 *            An absolute folder name.
	 * @param filePattern
	 *            A regular expression.
	 * @return A list of absolute file names.
	 */
	public static synchronized List<String> listFiles(String folder,
			final String filePattern) {
		File file = new File(folder);
		File[] files = file.listFiles(new FileFilter() {

			@Override
			public boolean accept(File pathname) {
				String fileName = pathname.getName();
				return fileName.matches(filePattern);
			}

		});

		List<String> fileNames = new ArrayList<String>();
		if (files != null) {
			for (File child : files) {
				fileNames.add(child.getAbsolutePath());
			}
		}

		return fileNames;
	}

}
