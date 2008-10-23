<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
    xmlns:fn="http://www.w3.org/2005/xpath-functions" version="2.0">

    <xsl:output indent="yes" method="xml"/>

    <xsl:template match="text()"/>

    <!-- Top-level: graph -> XDF -->
    <xsl:template match="graph">
        <xsl:element name="XDF">
            <xsl:attribute name="name" select="parameters/parameter[@name = 'id']/@value"/>
            <xsl:apply-templates select="parameters/parameter[@name != 'id']"/>
            <xsl:apply-templates select="vertices/vertex"/>
            <xsl:apply-templates select="edges/edge"/>
        </xsl:element>
    </xsl:template>

    <!-- Parameter declarations -->
    <xsl:template match="parameter[@name = 'network parameter']/element">
        <xsl:element name="Decl">
            <xsl:attribute name="kind">Param</xsl:attribute>
            <xsl:attribute name="name" select="@value"/>
        </xsl:element>
    </xsl:template>

    <!-- Variable declarations -->
    <xsl:template match="parameter[@name = 'network variable declaration']/entry">
        <xsl:element name="Decl">
            <xsl:attribute name="kind">Variable</xsl:attribute>
            <xsl:attribute name="name" select="@key"/>
            <xsl:element name="Expr">
                <xsl:attribute name="kind">Var</xsl:attribute>
                <xsl:value-of select="@value"/>
            </xsl:element>
        </xsl:element>
    </xsl:template>

    <!-- Input/output ports -->
    <xsl:template match="vertex[@type='Input Port']">
        <xsl:element name="Port">
            <xsl:attribute name="kind">Input</xsl:attribute>
            <xsl:attribute name="name" select="parameters/parameter[@name = 'id']/@value"/>
        </xsl:element>
    </xsl:template>
    
    <xsl:template match="vertex[@type='Output Port']">
        <xsl:element name="Port">
            <xsl:attribute name="kind">Output</xsl:attribute>
            <xsl:attribute name="name" select="parameters/parameter[@name = 'id']/@value"/>
        </xsl:element>
    </xsl:template>

    <!-- Instances -->
    <xsl:template match="vertex[@type='Instance']">
        <xsl:element name="Instance">
            <xsl:attribute name="id" select="parameters/parameter[@name = 'id']/@value"/>
            <xsl:element name="Class">
                <xsl:attribute name="name" select="parameters/parameter[@name = 'refinement']/@value"/>
            </xsl:element>
            <xsl:apply-templates select="parameters/parameter[@name = 'instance parameter']"/>
        </xsl:element>
    </xsl:template>

    <!-- Parameter instantiations -->
    <xsl:template match="parameter[@name = 'instance parameter']/entry">
        <xsl:element name="Parameter">
            <xsl:attribute name="name" select="@key"/>
            <xsl:element name="Expr">
                <xsl:attribute name="kind">Var</xsl:attribute>
                <xsl:value-of select="@value"/>
            </xsl:element>
        </xsl:element>
    </xsl:template>

    <!-- Connections -->
    <xsl:template match="edge">
        <xsl:element name="Connection">
            <xsl:choose>
                <xsl:when test="count(parameters/parameter[@name = 'source port']) = 1">
                    <xsl:attribute name="src" select="@source"></xsl:attribute>
                    <xsl:attribute name="src-port" select="parameters/parameter[@name = 'source port']/@value"/>
                </xsl:when>
                <xsl:otherwise>
                    <xsl:attribute name="src"/>
                    <xsl:attribute name="src-port" select="@source"/>
                </xsl:otherwise>
            </xsl:choose>
            <xsl:choose>
                <xsl:when test="count(parameters/parameter[@name = 'target port']) = 1">
                    <xsl:attribute name="dst" select="@target"></xsl:attribute>
                    <xsl:attribute name="dst-port" select="parameters/parameter[@name = 'target port']/@value"/>
                </xsl:when>
                <xsl:otherwise>
                    <xsl:attribute name="dst"/>
                    <xsl:attribute name="dst-port" select="@target"/>
                </xsl:otherwise>
            </xsl:choose>
        </xsl:element>
    </xsl:template>

</xsl:stylesheet>
