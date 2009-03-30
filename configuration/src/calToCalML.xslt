<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="2.0">

    <xsl:import href="exprToXml.xslt"/>

    <xsl:output indent="yes" method="xml"/>

    <xsl:template match="text()"/>

    <!-- XDF -->
    <xsl:template match="Actor">
        <Actor name="{ID/text()}">
            <xsl:apply-templates select="Import"/>
            <xsl:apply-templates select="Parameters"/>
            <xsl:choose>
                <xsl:when
                    test="PortDecls[1] &lt;&lt; DOUBLE_EQUAL_ARROW">
                    <xsl:apply-templates select="PortDecls[1]">
                        <xsl:with-param name="kind">Input</xsl:with-param>
                    </xsl:apply-templates>
                    <xsl:apply-templates select="PortDecls[2]">
                        <xsl:with-param name="kind">Output</xsl:with-param>
                    </xsl:apply-templates>
                </xsl:when>
                <xsl:otherwise>
                    <xsl:apply-templates select="PortDecls[1]">
                        <xsl:with-param name="kind">Output</xsl:with-param>
                    </xsl:apply-templates>
                </xsl:otherwise>
            </xsl:choose>
        </Actor>
    </xsl:template>

    <!-- imports -->
    <xsl:template match="Import/ImportRest">
        <Import>
            <xsl:choose>
                <xsl:when test="ALL">
                    <xsl:attribute name="kind">package</xsl:attribute>
                </xsl:when>
                <xsl:otherwise>
                    <xsl:attribute name="kind">single</xsl:attribute>
                </xsl:otherwise>
            </xsl:choose>
            <xsl:apply-templates/>
        </Import>
    </xsl:template>

    <!-- QID -->
    <xsl:template match="ImportRest/QualifiedId">
        <QID name="{string-join((ID | DOT)/text(), '')}">
            <xsl:apply-templates/>
        </QID>
    </xsl:template>

    <!-- ID -->
    <xsl:template match="ImportRest/QualifiedId/ID">
        <ID name="{text()}"/>
    </xsl:template>

    <!-- Parameter -->
    <xsl:template match="Parameter">
        <Decl kind="Parameter" name="{ID/text()}">
            <xsl:apply-templates select="Type"/>
        </Decl>
    </xsl:template>

    <!-- Type -->
    <xsl:template match="Type">
        <Type name="{ID/text()}"/>
    </xsl:template>

    <!-- Port -->
    <xsl:template match="PortDecl">
        <xsl:param name="kind"/>
        <Port kind="{$kind}" name="{ID/text()}">
            <xsl:apply-templates select="Type"/>
        </Port>
    </xsl:template>

</xsl:stylesheet>
