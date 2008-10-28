<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:fn="http://www.w3.org/2005/xpath-functions"
    xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="2.0">

    <xsl:import href="../cal/exprToString.xslt"/>

    <xsl:output indent="yes" method="xml"/>

    <xsl:template match="text()"/>

    <!-- Top-level: IP-XACT -> graph -->
    <xsl:template match="ip-xact">
        <xsl:element name="graph">
            <xsl:attribute name="type">Spirit IP-XACT architecture graph</xsl:attribute>
            
            <xsl:element name="parameters">
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

    <!-- Instances -->
    <xsl:template match="Instance">
        <xsl:element name="vertex">
            <xsl:attribute name="type">Instance</xsl:attribute>
            <xsl:element name="parameters">
                <xsl:element name="parameter">
                    <xsl:attribute name="name">id</xsl:attribute>
                    <xsl:attribute name="value" select="@id"/>
                </xsl:element>
                <xsl:element name="parameter">
                    <xsl:attribute name="name">refinement</xsl:attribute>
                    <xsl:attribute name="value" select="Class/@name"/>
                </xsl:element>
                <xsl:element name="parameter">
                    <xsl:attribute name="name">instance parameter</xsl:attribute>
                    <xsl:apply-templates select="Parameter"/>
                </xsl:element>
            </xsl:element>
        </xsl:element>
    </xsl:template>

    <!-- Connections -->
    <xsl:template match="Connection">
        <xsl:element name="edge">
            <xsl:attribute name="type">Connection</xsl:attribute>
            <xsl:attribute name="source" select="@src"/>
            <xsl:attribute name="target" select="@dst"/>
            <xsl:element name="parameters">
                <xsl:if test="@src != ''">
                    <xsl:element name="parameter">
                        <xsl:attribute name="name">source port</xsl:attribute>
                        <xsl:attribute name="value" select="@src-port"/>
                    </xsl:element>
                </xsl:if>
                <xsl:if test="@dst != ''">
                    <xsl:element name="parameter">
                        <xsl:attribute name="name">target port</xsl:attribute>
                        <xsl:attribute name="value" select="@dst-port"/>
                    </xsl:element>
                </xsl:if>
            </xsl:element>
        </xsl:element>
    </xsl:template>

</xsl:stylesheet>
