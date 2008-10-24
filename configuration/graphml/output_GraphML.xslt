<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns="http://graphml.graphdrawing.org/xmlns/1.0rc"
    xmlns:fn="http://www.w3.org/2005/xpath-functions"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="2.0">

    <xsl:output indent="yes" method="xml"/>

    <xsl:template match="text()"/>

    <!-- Top-level: graph -> graphml -->
    <xsl:template match="graph">
        <xsl:element name="graphml">
            <xsl:attribute name="xsi:schemaLocation"
                select="'http://graphml.graphdrawing.org/xmlns/1.0/graphml.xsd'"/>
        </xsl:element>
    </xsl:template>
</xsl:stylesheet>
