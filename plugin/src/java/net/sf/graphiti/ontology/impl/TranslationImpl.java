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
package net.sf.graphiti.ontology.impl;

import java.net.URL;

import net.sf.graphiti.grammar.GrammarTransformer;
import net.sf.graphiti.grammar.XsltTransformer;
import net.sf.graphiti.ontology.OntologyFactory;
import net.sf.graphiti.ontology.Parameter;
import net.sf.graphiti.ontology.Translation;
import net.sf.graphiti.ui.GraphitiPlugin;

import org.osgi.framework.Bundle;
import org.w3c.dom.Element;

import com.hp.hpl.jena.ontology.Individual;

/**
 * Implementation of Translation.
 * 
 * @author Matthieu Wipliez
 * 
 */
public class TranslationImpl extends XMLSchemaTypeImpl implements Translation {

	public TranslationImpl(Individual individual) {
		super(individual);
	}

	@Override
	public String getString(Element element) {
		Bundle bundle = GraphitiPlugin.getDefault().getBundle();
		String xsltFilename = getStringProperty(OntologyFactory
				.getPropertyTranslationHasXmlToStringXslt());
		URL url = bundle.getEntry("src/owl/" + xsltFilename);
		return new XsltTransformer(url).transformDomToString(element);
	}

	@Override
	public Parameter hasParameter() {
		return (Parameter) getIndividualProperty(OntologyFactory
				.getPropertyTranslationReferencesParameter());
	}

	@Override
	public void stringToXml(String input, Element parent) {
		Bundle bundle = GraphitiPlugin.getDefault().getBundle();
		String grammarFilename = getStringProperty(OntologyFactory
				.getPropertyTranslationHasGrammar());
		String xsltFilename = getStringProperty(OntologyFactory
				.getPropertyTranslationHasStringToXmlXslt());

		URL url = bundle.getEntry("src/owl/" + grammarFilename);
		try {
			Element element = new GrammarTransformer(url).parse(input);

			// transform DOM source to DOM result
			url = bundle.getEntry("src/owl/" + xsltFilename);
			new XsltTransformer(url).transformDomToDom(element, parent);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
