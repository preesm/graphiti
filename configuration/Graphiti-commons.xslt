<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:fn="http://www.w3.org/2005/xpath-functions"
    xmlns:graphiti="http://graphiti-editor.sourceforge.net/"
    xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="2.0">
    
    <xsl:function name="graphiti:getLayout">
        <xsl:param name="context"/>
        <xsl:variable name="base" select="fn:base-uri($context)"/>
        <!-- For some unknown reason (bug?), I cannot use \\. in the regexp below.
            However [.] works nicely. -->
        <xsl:variable name="file" select="fn:replace($base, '(.+)[.].+', '$1.layout')"/>
        <xsl:value-of select="document($file)/layout"/>
    </xsl:function>
    
    <xsl:function name="graphiti:getVertexLayoutAttributes">
        <xsl:param name="layout"/>
        <xsl:param name="vertexId"/>
        <xsl:if test="not(empty($layout))">
            <xsl:variable name="vertex" select="$layout/vertices/vertex[@id = $vertexId]"/>
            <xsl:if test="not(empty($vertex))">
                <xsl:attribute name="x" select="$vertex/@x"></xsl:attribute>
                <xsl:attribute name="y" select="$vertex/@y"></xsl:attribute>
            </xsl:if>
        </xsl:if>
    </xsl:function>

</xsl:stylesheet>
