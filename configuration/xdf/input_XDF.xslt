<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
    xmlns:fn="http://www.w3.org/2005/xpath-functions" version="2.0">

    <xsl:output indent="yes" method="xml"/>

    <xsl:template match="text()"/>

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

    <xsl:template match="Connection">
        <xsl:element name="edge">
            <xsl:attribute name="type">Connection</xsl:attribute>
            <xsl:choose>
                <xsl:when test="@src = ''">
                    <xsl:attribute name="source" select="@src-port"/>
                </xsl:when>
                <xsl:otherwise>
                    <xsl:attribute name="source" select="@src"/>
                </xsl:otherwise>
            </xsl:choose>
            <xsl:choose>
                <xsl:when test="@dst = ''">
                    <xsl:attribute name="target" select="@dst-port"/>
                </xsl:when>
                <xsl:otherwise>
                    <xsl:attribute name="target" select="@dst"/>
                </xsl:otherwise>
            </xsl:choose>
            <xsl:element name="parameters">
                <xsl:if test="@src != ''">
                    <xsl:element name="parameter">
                        <xsl:attribute name="name">source port</xsl:attribute>
                        <xsl:value-of select="@src-port"/>
                    </xsl:element>
                </xsl:if>
                <xsl:if test="@dst != ''">
                    <xsl:element name="parameter">
                        <xsl:attribute name="name">target port</xsl:attribute>
                        <xsl:value-of select="@dst-port"/>
                    </xsl:element>
                </xsl:if>
            </xsl:element>
        </xsl:element>
    </xsl:template>

</xsl:stylesheet>
