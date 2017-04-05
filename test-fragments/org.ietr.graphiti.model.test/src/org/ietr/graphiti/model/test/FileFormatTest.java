package org.ietr.graphiti.model.test;

import org.ietr.dftools.graphiti.model.FileFormat;
import org.junit.Assert;
import org.junit.Test;

public class FileFormatTest {

	@Test
	public void testConstruct() {
		final FileFormat fileFormat = new FileFormat("txt", "Text");
		Assert.assertTrue("Text".equals(fileFormat.getContentType()));
	}

}
