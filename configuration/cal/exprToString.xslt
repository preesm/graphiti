<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:fn="http://www.w3.org/2005/xpath-functions"
    xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="2.0">

    <xsl:output indent="yes" method="xml"/>

    <xsl:template match="text()"/>

    <!-- Expressions -->
    <xsl:template match="Expr[@kind='Literal' and @literal-kind='Boolean']">
        <xsl:value-of select="@value"/>
    </xsl:template>

    <xsl:template match="Expr[@kind='Literal' and @literal-kind='Integer']">
        <xsl:value-of select="@value"/>
    </xsl:template>

    <xsl:template match="Expr[@kind='Literal' and @literal-kind='String']">
        <xsl:text>"</xsl:text>
        <xsl:value-of select="@value"/>
        <xsl:text>"</xsl:text>
    </xsl:template>

    <xsl:template match="Expr[@kind='Var']">
        <xsl:value-of select="@name"/>
    </xsl:template>

    <xsl:template match="Expr[@kind='BinOpSeq']">
        <xsl:param name="level" select="0"/>
        <xsl:if test="$level != 0">(</xsl:if>
        <xsl:apply-templates select="*">
            <xsl:with-param name="level" select="$level + 1"/>
        </xsl:apply-templates>
        <xsl:if test="$level != 0">)</xsl:if>
    </xsl:template>

    <xsl:template match="Op">
        <xsl:value-of select="@name"/>
    </xsl:template>

</xsl:stylesheet>
