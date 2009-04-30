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
package net.sf.graphiti.model;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * 
 * @author Matthieu Wipliez
 * 
 */
public class FileFormat {

	public class Transformation {

		private String fileName;

		private String folder;

		private String name;

		private String startRule;

		private boolean xslt;

		private Transformation(String fileName) {
			xslt = true;
			this.fileName = fileName;
		}

		private Transformation(String folder, String name, String startRule) {
			xslt = false;
			this.folder = folder;
			this.name = name;
			this.startRule = startRule;
		}

		public String getFileName() {
			return fileName;
		}

		public String getFolder() {
			return folder;
		}

		public String getName() {
			return name;
		}

		public String getStartRule() {
			return startRule;
		}

		public boolean isXslt() {
			return xslt;
		}

	}

	private List<String> exports;

	private String extension;

	private List<Transformation> imports;

	private String type;

	public FileFormat(String extension, String type) {
		this.extension = extension;
		this.type = type;
		exports = new ArrayList<String>();
		imports = new ArrayList<Transformation>();
	}

	public void addExportTransformation(String fileName) {
		exports.add(fileName);
	}

	public void addImportGrammarTransformation(String folder, String name,
			String startRule) {
		imports.add(new Transformation(folder, name, startRule));
	}

	public void addImportXsltTransformation(String fileName) {
		imports.add(new Transformation(fileName));
	}

	public String getContentType() {
		return type;
	}

	public List<String> getExportTransformations() {
		return exports;
	}

	public String getFileExtension() {
		return extension;
	}

	public List<Transformation> getImportTransformations() {
		return imports;
	}

	public String toString() {
		return "*." + extension + ": " + type;
	}

}
