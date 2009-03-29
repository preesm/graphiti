<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="2.0">

    <xsl:import href="exprToXml.xslt"/>

    <xsl:output indent="yes" method="xml"/>

    <xsl:template match="text()"/>

    <!-- Type -->
    <xsl:template match="Type">
        <Type name="{ID/text()}">
            <xsl:apply-templates select="TypeRest/TypeAttrs"/>
        </Type>
    </xsl:template>

    <xsl:template match="TypeAttr[ID/text() = 'type']">
        <Entry name="type" kind="Type">
            <xsl:apply-templates select="TypeAttrRest/Type"/>
        </Entry>
    </xsl:template>

    <xsl:template match="TypeAttr[ID/text() = 'size']">
        <Entry name="size" kind="Expr">
            <xsl:apply-templates select="TypeAttrRest/Expression"/>
        </Entry>
    </xsl:template>

</xsl:stylesheet>
