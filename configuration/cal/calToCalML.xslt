<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:fn="http://www.w3.org/2005/xpath-functions"
    xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="2.0">

    <xsl:import href="exprToXml.xslt"/>
    
    <xsl:output indent="yes" method="xml"/>
    
    <xsl:template match="text()"/>

    <!-- XDF -->
    <xsl:template match="Actor">
        <xsl:element name="Actor">
            <xsl:attribute name="name">
                <xsl:value-of select="token[fn:position() = 2 and @name='ID']/text()"/>
            </xsl:attribute>
            <xsl:apply-templates select="Import"/>
            <xsl:apply-templates select="Parameters"/>
            <xsl:choose>
                <xsl:when
                    test="PortDecls[fn:position() = 1] &lt;&lt; token[@name = 'DOUBLE_EQUAL_ARROW']">
                    <xsl:apply-templates select="PortDecls[fn:position() = 1]">
                        <xsl:with-param name="kind">Input</xsl:with-param>
                    </xsl:apply-templates>
                    <xsl:apply-templates select="PortDecls[fn:position() = 2]">
                        <xsl:with-param name="kind">Output</xsl:with-param>
                    </xsl:apply-templates>
                </xsl:when>
                <xsl:otherwise>
                    <xsl:apply-templates select="PortDecls[fn:position() = 1]">
                        <xsl:with-param name="kind">Output</xsl:with-param>
                    </xsl:apply-templates>
                </xsl:otherwise>
            </xsl:choose>
        </xsl:element>
    </xsl:template>

    <!-- imports -->
    <xsl:template match="Import/ImportRest">
        <xsl:element name="Import">
            <xsl:choose>
                <xsl:when test="token[@name = 'ALL']">
                    <xsl:attribute name="kind">package</xsl:attribute>
                </xsl:when>
                <xsl:otherwise>
                    <xsl:attribute name="kind">single</xsl:attribute>
                </xsl:otherwise>
            </xsl:choose>
            <xsl:apply-templates/>
        </xsl:element>
    </xsl:template>

    <!-- QID -->
    <xsl:template match="ImportRest/QualifiedId">
        <xsl:element name="QID">
            <xsl:attribute name="name" select="fn:string-join(token/text(), '')"/>
            <xsl:apply-templates/>
        </xsl:element>
    </xsl:template>

    <!-- ID -->
    <xsl:template match="ImportRest/QualifiedId/token[@name='ID']">
        <xsl:element name="ID">
            <xsl:attribute name="name" select="text()"/>
        </xsl:element>
    </xsl:template>

    <!-- Parameter -->
    <xsl:template match="Parameter">
        <xsl:element name="Decl">
            <xsl:attribute name="kind">Parameter</xsl:attribute>
            <xsl:attribute name="name">
                <xsl:value-of select="token[@name = 'ID']/text()"/>
            </xsl:attribute>
            <xsl:apply-templates select="Type"/>
        </xsl:element>
    </xsl:template>

    <!-- Type -->
    <xsl:template match="Type">
        <xsl:element name="Type">
            <xsl:attribute name="name">
                <xsl:value-of select="token[@name='ID']/text()"/>
            </xsl:attribute>
        </xsl:element>
    </xsl:template>

    <!-- Port -->
    <xsl:template match="PortDecl">
        <xsl:param name="kind"/>
        <xsl:element name="Port">
            <xsl:attribute name="kind">
                <xsl:value-of select="$kind"/>
            </xsl:attribute>
            <xsl:attribute name="name">
                <xsl:value-of select="token[@name='ID']/text()"/>
            </xsl:attribute>
            <xsl:apply-templates select="Type"/>
        </xsl:element>
    </xsl:template>

</xsl:stylesheet>
