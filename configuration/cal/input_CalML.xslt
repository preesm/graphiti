<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:fn="http://www.w3.org/2005/xpath-functions"
    xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="2.0">
    
    <xsl:import href="exprToString.xslt"/>

    <xsl:output indent="yes" method="xml"/>

    <xsl:template match="text()"/>

    <!-- Top-level: Actor -> graph -->
    <xsl:template match="Actor">
        <xsl:element name="graph">
            <xsl:attribute name="type">CAL actor (.cal)</xsl:attribute>

            <xsl:element name="parameters">
                <xsl:element name="parameter">
                    <xsl:attribute name="name">id</xsl:attribute>
                    <xsl:attribute name="value" select="@name"/>
                </xsl:element>

                <xsl:element name="parameter">
                    <xsl:attribute name="name">actor parameter</xsl:attribute>
                    <xsl:apply-templates select="Decl[@kind = 'Parameter']"/>
                </xsl:element>

                <xsl:element name="parameter">
                    <xsl:attribute name="name">actor variable declaration</xsl:attribute>
                    <xsl:apply-templates select="Decl[@kind = 'Variable']"/>
                </xsl:element>
            </xsl:element>

            <xsl:element name="vertices">
                <xsl:apply-templates select="Port"/>
            </xsl:element>
            
            <xsl:element name="edges"/>
        </xsl:element>
    </xsl:template>

    <!-- Parameter declarations -->
    <xsl:template match="Decl[@kind = 'Parameter']">
        <xsl:element name="element">
            <xsl:attribute name="value">
                <xsl:value-of select="@name"/>
            </xsl:attribute>
        </xsl:element>
    </xsl:template>

    <!-- Variable declarations -->
    <xsl:template match="Decl[@kind = 'Variable']">
        <xsl:element name="entry">
            <xsl:attribute name="key" select="@name"/>
            <xsl:attribute name="value">
                <xsl:apply-templates select="Expr"/>
            </xsl:attribute>
        </xsl:element>
    </xsl:template>

    <!-- Input/output ports -->
    <xsl:template match="Port">
        <xsl:element name="vertex">
            <xsl:attribute name="type"><xsl:value-of select="@kind"/> port</xsl:attribute>
            <xsl:element name="parameters">
                <xsl:element name="parameter">
                    <xsl:attribute name="name">id</xsl:attribute>
                    <xsl:attribute name="value" select="@name"/>
                </xsl:element>
            </xsl:element>
        </xsl:element>
    </xsl:template>

</xsl:stylesheet>
