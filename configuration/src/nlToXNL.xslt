<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="2.0">

    <xsl:import href="exprToXml.xslt"/>

    <xsl:output indent="yes" method="xml"/>

    <xsl:template match="text()"/>

    <!-- XDF -->
    <xsl:template match="Network">
        <Network>
            <xsl:apply-templates select="QualifiedId"/>
            
            <xsl:choose>
                <xsl:when test="PortDecls[1] &lt;&lt; DOUBLE_EQUAL_ARROW">
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
            
            <xsl:apply-templates select="VarDeclSection"/>
            <xsl:apply-templates select="Parameters"/>
            <xsl:apply-templates select="EntitySection"/>
            <xsl:apply-templates select="StructureSection"/>
        </Network>
    </xsl:template>

    <!-- QID -->
    <xsl:template match="QualifiedId">
        <QID name="{string-join((ID | DOT)/text(), '')}">
            <xsl:apply-templates/>
        </QID>
    </xsl:template>

    <!-- ID -->
    <xsl:template match="QualifiedId/ID">
        <ID name="{text()}"/>
    </xsl:template>

    <!-- Variable -->
    <xsl:template match="VarDecl">
        <Decl kind="Variable" name="{ID[1]/text()}">
            <xsl:apply-templates select="Type"/>
            <xsl:apply-templates select="Expression"/>
        </Decl>
    </xsl:template>

    <!-- Parameter -->
    <xsl:template match="Parameter">
        <Decl kind="Parameter" name="{ID[1]/text()}">
            <xsl:apply-templates select="Type"/>
        </Decl>
    </xsl:template>

    <!-- Type -->
    <xsl:template match="Type">
        <Type name="{ID[1]/text()}"/>
    </xsl:template>

    <!-- Port -->
    <xsl:template match="PortDecl">
        <xsl:param name="kind"/>
        <Port kind="{$kind}" name="{ID[1]/text()}">
            <xsl:apply-templates select="Type"/>
        </Port>
    </xsl:template>

    <!-- EntityDecl -->
    <xsl:template match="EntityDecl">
        <EntityDecl name="{ID[1]/text()}">
            <xsl:apply-templates select="EntityExpr"/>
        </EntityDecl>
    </xsl:template>

    <!-- EntityExpr -->
    <xsl:template match="EntityExpr">
        <EntityExpr kind="Instantiation" name="{ID[1]/text()}">
            <xsl:apply-templates select="EntityPars"/>
        </EntityExpr>
    </xsl:template>

    <!-- EntityPar -->
    <xsl:template match="EntityPar">
        <Arg name="{ID[1]/text()}">
            <xsl:apply-templates select="Expression"/>
        </Arg>
    </xsl:template>
    
    <!-- StructureStmt -->
    <xsl:template match="StructureStmt">
        <StructureStmt kind="Connection">
            <xsl:apply-templates select="Connector[1]"/>
            <xsl:apply-templates select="Connector[2]"/>
        </StructureStmt>
    </xsl:template>
    
    <!-- Connector -->
    <xsl:template match="Connector">
        <xsl:choose>
            <xsl:when test="count(ID) = 1">
                <PortSpec kind="Local">
                    <PortRef name="{ID[1]/text()}"/>
                </PortSpec>
            </xsl:when>
            <xsl:otherwise>
                <PortSpec kind="Entity">
                    <EntityRef name="{ID[1]/text()}"/>
                    <PortRef name="{ID[2]/text()}"/>
                </PortSpec>
            </xsl:otherwise>
        </xsl:choose>
    </xsl:template>

</xsl:stylesheet>
