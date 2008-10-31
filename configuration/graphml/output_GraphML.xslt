<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:graphml="http://graphml.graphdrawing.org/xmlns/1.0rc"
    xmlns:fn="http://www.w3.org/2005/xpath-functions"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="2.0">

    <xsl:output indent="yes" method="xml"/>

    <xsl:template match="text()"/>

    <!-- Top-level: graph -> graphml -->
    <xsl:template match="graph">
        <xsl:element name="graphml">
            <xsl:element name="graph">
                <xsl:attribute name="edgedefault">directed</xsl:attribute>
                <xsl:apply-templates select="vertices"/>
                <xsl:apply-templates select="edges"/>
            </xsl:element>
        </xsl:element>
    </xsl:template>
    
    <!-- One vertex -->
    <xsl:template match="vertex[@type='Instance']">
        <xsl:element name="node">
            <xsl:attribute name="id" select="parameters/parameter[@name='id']/@value"/>
           </xsl:element>
    </xsl:template>
    
    <!-- One edge -->
    <xsl:template match="edge[@type='edge']">
        <xsl:element name="edge">
            <xsl:attribute name="source" select="@source"/>
            <xsl:attribute name="target" select="@target"/>
        </xsl:element>
    </xsl:template>
    
</xsl:stylesheet>
