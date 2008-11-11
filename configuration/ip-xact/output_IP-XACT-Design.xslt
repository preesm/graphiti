<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:fn="http://www.w3.org/2005/xpath-functions"
    xmlns:grammar="java:net.sf.graphiti.io.GrammarTransformer"
    xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="2.0"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" 
    xsi:schemaLocation="http://www.spiritconsortium.org/XMLSchema/SPIRIT/1.4 http://www.spiritconsortium.org/XMLSchema/SPIRIT/1.4/index.xsd" 
    xmlns:spirit="http://www.spiritconsortium.org/XMLSchema/SPIRIT/1.4">
    
    <xsl:import href="../cal/exprToXml.xslt"/>
    
    <xsl:output indent="yes" method="xml"/>
    
    <xsl:template match="text()"/>
    
    <!-- Top-level: graph -> ip-xact -->
    <xsl:template match="graph">
        <xsl:element name="spirit:design">
            <xsl:apply-templates select="parameters" mode="vlnv"/>
            <xsl:element name="spirit:componentInstances">
                <xsl:apply-templates select="vertices/vertex[@type = 'componentInstance']"/>
                <xsl:apply-templates select="vertices/vertex[@type != 'componentInstance' and @type != 'hierConnection']"/>
            </xsl:element>    
            
            <xsl:element name="spirit:interconnections">
                <xsl:apply-templates select="edges/edge[@type = 'interconnection' or @type = 'directedConnection']"/>
            </xsl:element>
            
            <xsl:element name="spirit:hierConnections">
                <xsl:apply-templates select="vertices/vertex[@type = 'hierConnection']"/>
            </xsl:element>
        </xsl:element>
    </xsl:template>
    
    <!-- Design parameter declarations -->
    <xsl:template match="parameter" mode="vlnv">
        <xsl:element name="spirit:{@name}">
            <xsl:value-of select="@value"/>
        </xsl:element>
    </xsl:template>
    
    <!-- Component instances -->
    <xsl:template match="vertex[@type='componentInstance']">
        <xsl:element name="spirit:componentInstance">
            <xsl:element name="spirit:instanceName">
                <xsl:value-of select="parameters/parameter[@name = 'id']/@value"/>
            </xsl:element>
            <xsl:element name="spirit:componentRef">
                <xsl:attribute name="spirit:library" select="parameters/parameter[@name = 'library']/@value"/>
                <xsl:attribute name="spirit:name" select="parameters/parameter[@name = 'name']/@value"/>
                <xsl:attribute name="spirit:vendor" select="parameters/parameter[@name = 'vendor']/@value"/>
                <xsl:attribute name="spirit:version" select="parameters/parameter[@name = 'version']/@value"/>
            </xsl:element>
            <xsl:element name="spirit:configurableElementValues">
                <xsl:if test="parameters/parameter[@name = 'componentType']/@value!=''">
                    <xsl:element name="spirit:configurableElementValue">
                        <xsl:attribute name="spirit:referenceId">componentType</xsl:attribute>
                        <xsl:value-of select="parameters/parameter[@name = 'componentType']/@value"/>
                    </xsl:element>
                </xsl:if>
                <xsl:element name="spirit:configurableElementValue">
                    <xsl:attribute name="spirit:referenceId">refinement</xsl:attribute>
                    <xsl:value-of select="parameters/parameter[@name = 'refinement']/@value"/>
                </xsl:element>
            </xsl:element>
        </xsl:element>
    </xsl:template>
    
    <!-- Component instances -->
    <xsl:template match="vertex[@type != 'componentInstance' and @type != 'hierConnection']">
        <xsl:element name="spirit:componentInstance">
            <xsl:element name="spirit:instanceName">
                <xsl:value-of select="parameters/parameter[@name = 'id']/@value"/>
            </xsl:element>
            <xsl:element name="spirit:componentRef">
                <xsl:attribute name="spirit:library" select="parameters/parameter[@name = 'library']/@value"/>
                <xsl:attribute name="spirit:name" select="parameters/parameter[@name = 'name']/@value"/>
                <xsl:attribute name="spirit:vendor" select="parameters/parameter[@name = 'vendor']/@value"/>
                <xsl:attribute name="spirit:version" select="parameters/parameter[@name = 'version']/@value"/>
            </xsl:element>
            <xsl:element name="spirit:configurableElementValues">
                <xsl:element name="spirit:configurableElementValue">
                    <xsl:attribute name="spirit:referenceId">componentType</xsl:attribute>
                    <xsl:value-of select="@type"/>
                </xsl:element>
                <xsl:element name="spirit:configurableElementValue">
                    <xsl:attribute name="spirit:referenceId">refinement</xsl:attribute>
                    <xsl:value-of select="parameters/parameter[@name = 'refinement']/@value"/>
                </xsl:element>
                <!-- Specific medium parameters -->
                <xsl:if test="@type='medium'">
                    <xsl:element name="spirit:configurableElementValue">
                        <xsl:attribute name="spirit:referenceId">medium_invDataRate</xsl:attribute>
                        <xsl:value-of select="parameters/parameter[@name = 'medium_invDataRate']/@value"/>
                    </xsl:element>
                    <xsl:element name="spirit:configurableElementValue">
                        <xsl:attribute name="spirit:referenceId">medium_overhead</xsl:attribute>
                        <xsl:value-of select="parameters/parameter[@name = 'medium_overhead']/@value"/>
                    </xsl:element>
                </xsl:if>
            </xsl:element>
        </xsl:element>
    </xsl:template>
    
    <!-- interconnections -->
    <xsl:template match="edge[@type='interconnection']">
        <xsl:element name="spirit:interconnection">
            <xsl:element name="spirit:name">
                <xsl:value-of select="parameters/parameter[@name = 'id']/@value"/>
            </xsl:element>
            <xsl:element name="spirit:activeInterface">
                <xsl:attribute name="spirit:busRef" select="parameters/parameter[@name = 'source port']/@value"/>
                <xsl:attribute name="spirit:componentRef" select="@source"/>
            </xsl:element>
            <xsl:element name="spirit:activeInterface">
                <xsl:attribute name="spirit:busRef" select="parameters/parameter[@name = 'target port']/@value"/>
                <xsl:attribute name="spirit:componentRef" select="@target"/>
            </xsl:element>
        </xsl:element>
    </xsl:template>
    
    <!-- hierarchical connections -->
    <xsl:template match="vertex[@type = 'hierConnection']">
        <xsl:element name="spirit:hierConnection">
            <xsl:variable name="id" select="./parameters/parameter[@name = 'id']/@value"/>
            <xsl:attribute name="spirit:interfaceRef" select="$id"/>
            <xsl:element name="spirit:activeInterface">
                <xsl:variable name="hierEdge" select="//edges/edge[@type = 'hierConnection' and @target = $id][1]"/>
                <xsl:attribute name="spirit:busRef" select="$hierEdge/parameters/parameter[@name = 'source port']/@value"/>
                <xsl:attribute name="spirit:componentRef" select="$hierEdge/@source"/>
            </xsl:element>
        </xsl:element>
    </xsl:template>
    
    <!-- directed connections -->
    <xsl:template match="edge[@type = 'directedConnection']">
        <xsl:element name="spirit:interconnection">
            <xsl:element name="spirit:name">
                <xsl:value-of select="parameters/parameter[@name = 'id']/@value"/>
            </xsl:element>
            <xsl:element name="spirit:displayName">directed</xsl:element>
            <xsl:element name="spirit:activeInterface">
                <xsl:attribute name="spirit:busRef" select="parameters/parameter[@name = 'source port']/@value"/>
                <xsl:attribute name="spirit:componentRef" select="@source"/>
            </xsl:element>
            <xsl:element name="spirit:activeInterface">
                <xsl:attribute name="spirit:busRef" select="parameters/parameter[@name = 'target port']/@value"/>
                <xsl:attribute name="spirit:componentRef" select="@target"/>
            </xsl:element>
        </xsl:element>
    </xsl:template>
    
</xsl:stylesheet>
