<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:graphml="http://graphml.graphdrawing.org/xmlns/1.0rc"
    xmlns:fn="http://www.w3.org/2005/xpath-functions"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="2.0">

    <xsl:output indent="yes" method="xml"/>

    <xsl:template match="text()"/>

    <!-- Top-level: graph -> graphml -->
    <xsl:template match="graph">
        <xsl:element name="graphml">
            <xsl:element name="key">
                <xsl:attribute name="attr.name">edge_prod</xsl:attribute>
                <xsl:attribute name="attr.type">int</xsl:attribute>
                <xsl:attribute name="for">edge</xsl:attribute>
                <xsl:attribute name="id">k0</xsl:attribute>
                <xsl:element name="desc" >production rate on the edge</xsl:element>
            </xsl:element>
            <xsl:element name="key">
                <xsl:attribute name="attr.name">edge_delay</xsl:attribute>
                <xsl:attribute name="attr.type">int</xsl:attribute>
                <xsl:attribute name="for">edge</xsl:attribute>
                <xsl:attribute name="id">k1</xsl:attribute>
                    <xsl:element name="desc" >delay on the edge</xsl:element>
            </xsl:element>
            <xsl:element name="key">
                <xsl:attribute name="attr.name">edge_cons</xsl:attribute>
                <xsl:attribute name="attr.type">int</xsl:attribute>
                <xsl:attribute name="for">edge</xsl:attribute>
                <xsl:attribute name="id">k2</xsl:attribute>
                    <xsl:element name="desc" >consumption rate on the edge</xsl:element>
            </xsl:element>
            <xsl:element name="key">
                <xsl:attribute name="attr.name">name</xsl:attribute>
                <xsl:attribute name="attr.type">int</xsl:attribute>
                <xsl:attribute name="for">edge</xsl:attribute>
                <xsl:attribute name="id">k3</xsl:attribute>
                    <xsl:element name="desc" >vertex name</xsl:element>
            </xsl:element>
            
            <xsl:element name="graph">
                <xsl:attribute name="edgedefault">directed</xsl:attribute>
                <xsl:apply-templates select="vertices"/>
                <xsl:apply-templates select="edges"/>
            </xsl:element>
        </xsl:element>
    </xsl:template>
    
    <!-- One vertex -->
    <xsl:template match="vertex[@type='Instance']">
        <xsl:element name="node">
            <xsl:attribute name="id" select="parameters/parameter[@name='refId']/@value"/>
            
            <xsl:element name="data" >
                <xsl:attribute name="key">k3</xsl:attribute>
                <xsl:value-of select="parameters/parameter[@name='id']/@value"/>
            </xsl:element>
            
        </xsl:element>
           
    </xsl:template>
    
    <!-- One edge -->
    <xsl:template match="edge[@type='edge']">
        <xsl:element name="edge">
            <xsl:attribute name="source" select="@source"/>
            <xsl:attribute name="target" select="@target"/>
            <xsl:element name="data" >
                <xsl:attribute name="key">k0</xsl:attribute>
                <xsl:value-of select="parameters/parameter[@name='edge_prod']/@value"/>
            </xsl:element>
            <xsl:element name="data" >
                <xsl:attribute name="key">k1</xsl:attribute>
                <xsl:value-of select="parameters/parameter[@name='edge_delay']/@value"/>
            </xsl:element>
            <xsl:element name="data" >
                <xsl:attribute name="key">k2</xsl:attribute>
                <xsl:value-of select="parameters/parameter[@name='edge_cons']/@value"/>
            </xsl:element>
        </xsl:element>
    </xsl:template>
    
</xsl:stylesheet>
