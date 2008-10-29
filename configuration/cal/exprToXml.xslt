<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="2.0">

    <xsl:output indent="yes" method="xml"/>

    <xsl:template match="text()"/>

    <xsl:template match="Atom/token[@name = 'IDENTIFIER']">
        <Expr kind="Var">
            <xsl:attribute name="name" select="text()"/>
        </Expr>
    </xsl:template>

    <xsl:template match="Atom/token[@name = 'NUMBER']">
        <Expr kind="Literal" literal-kind="Integer">
            <xsl:attribute name="value" select="text()"/>
        </Expr>
    </xsl:template>

    <xsl:template match="Atom/token[@name = 'STRING']">
        <xsl:variable name="textValue" select="text()"/>
        <Expr kind="Literal" literal-kind="String">
            <xsl:attribute name="value"
                select="substring($textValue, 2, string-length($textValue) - 2)"/>
        </Expr>
    </xsl:template>

    <xsl:template match="ExpressionRest">
        <Op>
            <xsl:attribute name="name" select="token/text()"/>
        </Op>
        <xsl:apply-templates/>
    </xsl:template>

    <xsl:template match="Expression">
        <xsl:choose>
            <xsl:when test="count(ExpressionRest) = 0">
                <!-- this expression is a single factor -->
                <xsl:apply-templates/>
            </xsl:when>
            <xsl:otherwise>
                <!-- this expression is a sequence of binary operations -->
                <Expr kind="BinOpSeq">
                    <xsl:apply-templates/>
                </Expr>
            </xsl:otherwise>
        </xsl:choose>
    </xsl:template>

    <xsl:template match="Factor">
        <xsl:apply-templates/>
    </xsl:template>

</xsl:stylesheet>
