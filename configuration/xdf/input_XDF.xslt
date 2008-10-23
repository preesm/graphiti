<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
    xmlns:fn="http://www.w3.org/2005/xpath-functions" version="2.0">

    <xsl:output indent="yes" method="xml"/>

    <xsl:template match="XDF">
        <xsl:element name="graph">
            <xsl:attribute name="type">XML Dataflow Network (XDF)</xsl:attribute>
            <xsl:element name="parameters">
                <xsl:element name="parameter">
                    <xsl:attribute name="name">id</xsl:attribute>
                    <xsl:value-of select="@name"/>
                </xsl:element>
                <xsl:apply-templates select="Decl"/>
            </xsl:element>
            <xsl:element name="vertices">
                <xsl:apply-templates select="Port"/>
                <xsl:apply-templates select="Instance"/>
            </xsl:element>
            <xsl:element name="edges">
                <xsl:apply-templates select="Connection"/>
            </xsl:element>
        </xsl:element>
    </xsl:template>

    <xsl:template match="Port">
        <xsl:element name="vertex">
            <xsl:attribute name="type"><xsl:value-of select="@kind"/> port</xsl:attribute>
            <xsl:element name="parameters">
                <xsl:element name="parameter">
                    <xsl:attribute name="name">id</xsl:attribute>
                    <xsl:value-of select="@name"/>
                </xsl:element>
            </xsl:element>
        </xsl:element>
    </xsl:template>

    <xsl:template match="Instance">
        <xsl:element name="vertex">
            <xsl:attribute name="type">Instance</xsl:attribute>
            <xsl:element name="parameters">
                <xsl:element name="parameter">
                    <xsl:attribute name="name">id</xsl:attribute>
                    <xsl:value-of select="@id"/>
                </xsl:element>
                <xsl:element name="parameter">
                    <xsl:attribute name="name">refinement</xsl:attribute>
                    <xsl:value-of select="Class/@name"/>
                </xsl:element>
            </xsl:element>
        </xsl:element>
    </xsl:template>

    <xsl:template match="text()"/>

</xsl:stylesheet>
