package org.ietr.graphiti.ui.test;

import org.ietr.dftools.graphiti.ui.figure.PortAnchorReferenceManager;
import org.junit.Assert;
import org.junit.Test;

public class PortAnchorReferenceManagerTest {

	@Test
	public void testPortAnchorReferenceManager() {
		final PortAnchorReferenceManager portAnchorReferenceManager = new PortAnchorReferenceManager(null, null, false);
		Assert.assertNotNull(portAnchorReferenceManager);
	}
}
