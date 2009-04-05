<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="2.0">

    <xsl:import href="exprToXml.xslt"/>
    <xsl:import href="typeToXml.xslt"/>

    <xsl:output indent="yes" method="xml"/>

    <xsl:template match="text()"/>

    <!-- XDF -->
    <xsl:template match="Network">
        <Network>
            <xsl:apply-templates select="QualifiedId"/>
            <xsl:apply-templates select="Parameter"/>
            <xsl:apply-templates select="Inputs">
                <xsl:with-param name="kind">Input</xsl:with-param>
            </xsl:apply-templates>
            <xsl:apply-templates select="Outputs">
                <xsl:with-param name="kind">Output</xsl:with-param>
            </xsl:apply-templates>
            
            <xsl:apply-templates select="VarDecl"/>
            <xsl:apply-templates select="EntityDecl"/>
            <xsl:apply-templates select="StructureStmt"/>
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
        <Decl kind="Variable" name="{Var/text()}">
            <xsl:apply-templates select="Type"/>
            <xsl:apply-templates select="Expression"/>
        </Decl>
    </xsl:template>

    <!-- Parameter -->
    <xsl:template match="Parameter">
        <Decl kind="Parameter" name="{Var/text()}">
            <xsl:apply-templates select="Type"/>
        </Decl>
    </xsl:template>

    <!-- Port -->
    <xsl:template match="PortDecl">
        <xsl:param name="kind"/>
        <Port kind="{$kind}" name="{Var/text()}">
            <xsl:apply-templates select="Type"/>
        </Port>
    </xsl:template>

    <!-- EntityDecl -->
    <xsl:template match="EntityDecl">
        <EntityDecl name="{Var/text()}">
            <xsl:apply-templates select="EntityExpr"/>
        </EntityDecl>
    </xsl:template>

    <!-- EntityExpr -->
    <xsl:template match="EntityExpr">
        <EntityExpr kind="Instantiation" name="{Var/text()}">
            <xsl:apply-templates select="EntityPar"/>
        </EntityExpr>
    </xsl:template>

    <!-- EntityPar -->
    <xsl:template match="EntityPar">
        <Arg name="{Var/text()}">
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
            <xsl:when test="count(Var) = 1">
                <PortSpec kind="Local">
                    <PortRef name="{Var[1]/text()}"/>
                </PortSpec>
            </xsl:when>
            <xsl:otherwise>
                <PortSpec kind="Entity">
                    <EntityRef name="{Var[1]/text()}"/>
                    <PortRef name="{Var[2]/text()}"/>
                </PortSpec>
            </xsl:otherwise>
        </xsl:choose>
    </xsl:template>

</xsl:stylesheet>
