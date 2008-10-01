<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet
    xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
    xmlns:fn="http://www.w3.org/2005/xpath-functions"
    version="2.0">
    
    <xsl:output method="xml"/>
    
    <xsl:template match="Atom/token[@name = 'IDENTIFIER']">
        <xsl:element name="Expr">
            <xsl:attribute name="kind">Var</xsl:attribute>
            <xsl:attribute name="name">
                <xsl:value-of select="text()"/>
            </xsl:attribute>
        </xsl:element>
    </xsl:template>
    
    <xsl:template match="Atom/token[@name = 'NUMBER']">
        <xsl:element name="Expr">
            <xsl:attribute name="kind">Literal</xsl:attribute>
            <xsl:attribute name="literal-kind">Integer</xsl:attribute>
            <xsl:attribute name="value">
                <xsl:value-of select="text()"/>
            </xsl:attribute>
        </xsl:element>
    </xsl:template>
    
    <xsl:template match="Atom/token[@name = 'STRING']">
        <xsl:variable name="textValue" select="text()"/>
        <xsl:element name="Expr">
            <xsl:attribute name="kind">Literal</xsl:attribute>
            <xsl:attribute name="literal-kind">String</xsl:attribute>
            <xsl:attribute name="value">
                <xsl:value-of select="fn:substring($textValue, 2, fn:string-length($textValue) - 2)"/>
            </xsl:attribute>
        </xsl:element>
    </xsl:template>
    
    <xsl:template match="ExpressionRest">
        <xsl:element name="Op">
            <xsl:attribute name="name">
                <xsl:value-of select="token/text()"/>
            </xsl:attribute>
        </xsl:element>
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