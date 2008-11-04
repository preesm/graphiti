<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:grammar="java:net.sf.graphiti.io.GrammarTransformer"
    xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="2.0">

    <xsl:output indent="yes" method="text"/>

    <xsl:template match="text()"/>

    <xsl:param name="path"/>

    <!-- Top-level: graph -> XNL -->
    <xsl:template match="Network">
        <xsl:text>network ...</xsl:text>
    </xsl:template>

</xsl:stylesheet>
