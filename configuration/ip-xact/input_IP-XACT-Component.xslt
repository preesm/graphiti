<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:fn="http://www.w3.org/2005/xpath-functions"
    xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="2.0"
    xmlns:spirit="http://www.spiritconsortium.org/XMLSchema/SPIRIT/1.4">
    
    <xsl:import href="../cal/exprToString.xslt"/>
    
    <xsl:output indent="yes" method="xml"/>
    
    <xsl:template match="text()"/>
    
    <!-- Top-level: design -->
    <xsl:template match="spirit:component">
        <xsl:element name="graph">
            <xsl:attribute name="type">Spirit IP-XACT component</xsl:attribute>
            
            <!-- VLNV of the design -->
            <xsl:element name="parameters">
                <xsl:apply-templates select="spirit:vendor"/>
                <xsl:apply-templates select="spirit:library"/>
                <xsl:apply-templates select="spirit:name"/>
                <xsl:apply-templates select="spirit:version"/>
            </xsl:element>
            
            <!-- hierarchical connections and component references -->
            <xsl:element name="vertices">
                <xsl:apply-templates select="spirit:componentInstances"/>
                <xsl:apply-templates select="spirit:hierConnections" mode="vertex"/>
            </xsl:element>
            
            <!-- Interconnections -->
            <xsl:element name="edges">
                <xsl:apply-templates select="spirit:interconnections"/>
                <xsl:apply-templates select="spirit:hierConnections" mode="edge"/>
            </xsl:element>
        </xsl:element>
    </xsl:template>
    
    <!-- template for the hierarchical connections -->
    <!-- Adding not only a hierarchical port but also  -->
    <!-- a connection to the associated component reference -->
    <xsl:template match="spirit:hierConnection" mode="vertex">
        <xsl:element name="vertex">
            <xsl:attribute name="type">hierConnection</xsl:attribute>
            <xsl:element name="parameters">
                <xsl:element name="parameter">
                    <xsl:attribute name="name">id</xsl:attribute>
                    <xsl:attribute name="value" select="@spirit:interfaceRef"/>
                </xsl:element>
            </xsl:element>
        </xsl:element>
    </xsl:template>
    
    <xsl:template match="spirit:hierConnection" mode="edge">
        <xsl:element name="edge">
            <xsl:attribute name="type">hierConnection</xsl:attribute>
            <xsl:attribute name="source" select="spirit:activeInterface[1]/@spirit:componentRef"/>
            <xsl:attribute name="target" select="@spirit:interfaceRef"/>
            <xsl:element name="parameters">
                <xsl:element name="parameter">
                    <xsl:attribute name="name">source port</xsl:attribute>
                    <xsl:attribute name="value"><xsl:value-of select="spirit:activeInterface[1]/@spirit:busRef"/></xsl:attribute>
                </xsl:element>
            </xsl:element>
        </xsl:element>
    </xsl:template>
    
    <!-- templates for the VLNV of the design -->
    <xsl:template match="spirit:vendor">
        <xsl:element name="parameter">
            <xsl:attribute name="name">vendor</xsl:attribute>
            <xsl:attribute name="value"><xsl:value-of select="."/></xsl:attribute>
        </xsl:element>
    </xsl:template>
    
    <xsl:template match="spirit:library">
        <xsl:element name="parameter">
            <xsl:attribute name="name">library</xsl:attribute>
            <xsl:attribute name="value"><xsl:value-of select="."/></xsl:attribute>
        </xsl:element>
    </xsl:template>
    
    <xsl:template match="spirit:name">
        <xsl:element name="parameter">
            <xsl:attribute name="name">name</xsl:attribute>
            <xsl:attribute name="value"><xsl:value-of select="."/></xsl:attribute>
        </xsl:element>
    </xsl:template>
    
    <xsl:template match="spirit:version">
        <xsl:element name="parameter">
            <xsl:attribute name="name">version</xsl:attribute>
            <xsl:attribute name="value"><xsl:value-of select="."/></xsl:attribute>
        </xsl:element>
    </xsl:template>
    
    <!-- template for the component instances -->
    <xsl:template match="spirit:componentInstance">
        <xsl:element name="vertex">
            <xsl:attribute name="type">componentInstance</xsl:attribute>
            <xsl:element name="parameters">
                <xsl:element name="parameter">
                    <xsl:attribute name="name">id</xsl:attribute>
                    <xsl:attribute name="value"><xsl:value-of select="spirit:instanceName"/></xsl:attribute>
                </xsl:element>
                <xsl:element name="parameter">
                    <xsl:attribute name="name">vendor</xsl:attribute>
                    <xsl:attribute name="value"><xsl:value-of select="spirit:componentRef/@spirit:vendor"/></xsl:attribute>
                </xsl:element>
                <xsl:element name="parameter">
                    <xsl:attribute name="name">library</xsl:attribute>
                    <xsl:attribute name="value"><xsl:value-of select="spirit:componentRef/@spirit:library"/></xsl:attribute>
                </xsl:element>
                <xsl:element name="parameter">
                    <xsl:attribute name="name">name</xsl:attribute>
                    <xsl:attribute name="value"><xsl:value-of select="spirit:componentRef/@spirit:name"/></xsl:attribute>
                </xsl:element>
                <xsl:element name="parameter">
                    <xsl:attribute name="name">version</xsl:attribute>
                    <xsl:attribute name="value"><xsl:value-of select="spirit:componentRef/@spirit:version"/></xsl:attribute>
                </xsl:element>
                <xsl:element name="parameter">
                    <xsl:attribute name="name">refinement</xsl:attribute>
                    <xsl:attribute name="value"/>
                </xsl:element>
            </xsl:element>
        </xsl:element>
    </xsl:template>
    
    <!-- template for the interconnections -->
    <xsl:template match="spirit:interconnection">
        <xsl:element name="edge">
            <xsl:attribute name="type">interconnection</xsl:attribute>
            <xsl:attribute name="source" select="spirit:activeInterface[1]/@spirit:componentRef"/>
            <xsl:attribute name="target" select="spirit:activeInterface[2]/@spirit:componentRef"/>
            <xsl:element name="parameters">
                <xsl:element name="parameter">
                    <xsl:attribute name="name">id</xsl:attribute>
                    <xsl:attribute name="value"><xsl:value-of select="spirit:name"/></xsl:attribute>
                </xsl:element>
                <xsl:element name="parameter">
                    <xsl:attribute name="name">source port</xsl:attribute>
                    <xsl:attribute name="value"><xsl:value-of select="spirit:activeInterface[1]/@spirit:busRef"/></xsl:attribute>
                </xsl:element>
                <xsl:element name="parameter">
                    <xsl:attribute name="name">target port</xsl:attribute>
                    <xsl:attribute name="value"><xsl:value-of select="spirit:activeInterface[2]/@spirit:busRef"/></xsl:attribute>
                </xsl:element>
            </xsl:element>
        </xsl:element>
    </xsl:template>
    
    
    <!-- Top-level: ip-xact -> graph -->
    <!--
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
    -->
</xsl:stylesheet>
