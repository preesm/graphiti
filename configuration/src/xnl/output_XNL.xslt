<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:grammar="java:net.sf.graphiti.io.GrammarTransformer"
    xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="2.0">

    <xsl:import href="../cal/exprToXml.xslt"/>

    <xsl:output indent="yes" method="xml"/>

    <xsl:template match="text()"/>

    <!-- writes the layout in a file that has the same name as the target document,
        except with .layout extension. -->
    <xsl:param name="path"/>
    <xsl:variable name="file" select="replace($path, '(.+)[.].+', '$1.layout')"/>

    <!-- Top-level: graph -> XNL -->
    <xsl:template match="graph">

        <!-- layout information -->
        <xsl:result-document href="file:///{$file}" method="xml" indent="yes">
            <xsl:element name="layout">
                <xsl:element name="vertices">
                    <xsl:for-each select="vertices/vertex">
                        <xsl:element name="vertex">
                            <xsl:attribute name="id"
                                select="parameters/parameter[@name = 'id']/@value"/>
                            <xsl:attribute name="x" select="@x"/>
                            <xsl:attribute name="y" select="@y"/>
                        </xsl:element>
                    </xsl:for-each>
                </xsl:element>
            </xsl:element>
        </xsl:result-document>

        <!-- graph -->
        <xsl:element name="Network">
            <xsl:apply-templates select="parameters/parameter[@name = 'id']"/>
            <xsl:apply-templates select="vertices/vertex[@type = 'Input port']"/>
            <xsl:apply-templates select="vertices/vertex[@type = 'Output port']"/>
            <xsl:apply-templates select="parameters/parameter[@name != 'id']"/>
            <xsl:apply-templates select="vertices/vertex[@type = 'Instance']"/>
            <xsl:apply-templates select="edges/edge"/>
        </xsl:element>
    </xsl:template>

    <xsl:template match="parameters/parameter[@name = 'id']">
        <xsl:element name="QID">
            <xsl:attribute name="name" select="@value"/>
            <xsl:element name="ID">
                <xsl:attribute name="name" select="@value"/>
            </xsl:element>
        </xsl:element>
    </xsl:template>


    <!-- Parameter declarations -->
    <xsl:template match="parameter[@name = 'network parameter']/element">
        <xsl:element name="Decl">
            <xsl:attribute name="kind">Parameter</xsl:attribute>
            <xsl:attribute name="name" select="@value"/>
        </xsl:element>
    </xsl:template>

    <!-- Variable declarations -->
    <xsl:template match="parameter[@name = 'network variable declaration']/entry">
        <xsl:element name="Decl">
            <xsl:attribute name="kind">Variable</xsl:attribute>
            <xsl:attribute name="name" select="@key"/>
            <xsl:variable name="gt" select="grammar:new('cal/expr.grammar')"/>
            <xsl:variable name="concreteTree" select="grammar:parseString($gt, @value)"/>
            <xsl:apply-templates select="$concreteTree"/>
        </xsl:element>
    </xsl:template>

    <!-- Input/output ports -->
    <xsl:template match="vertex[@type='Input port']">
        <xsl:element name="Port">
            <xsl:attribute name="kind">Input</xsl:attribute>
            <xsl:attribute name="name" select="parameters/parameter[@name = 'id']/@value"/>
        </xsl:element>
    </xsl:template>

    <xsl:template match="vertex[@type='Output port']">
        <xsl:element name="Port">
            <xsl:attribute name="kind">Output</xsl:attribute>
            <xsl:attribute name="name" select="parameters/parameter[@name = 'id']/@value"/>
        </xsl:element>
    </xsl:template>

    <!-- Instances -->
    <xsl:template match="vertex[@type='Instance']">
        <xsl:element name="EntityDecl">
            <xsl:attribute name="name" select="parameters/parameter[@name = 'id']/@value"/>
            <xsl:element name="EntityExpr">
                <xsl:attribute name="kind">Instantiation</xsl:attribute>
                <xsl:attribute name="name"
                    select="parameters/parameter[@name = 'refinement']/@value"/>
                <xsl:apply-templates select="parameters/parameter[@name = 'instance parameter']"/>
            </xsl:element>
        </xsl:element>
    </xsl:template>

    <!-- Parameter instantiations -->
    <xsl:template match="parameter[@name = 'instance parameter']/entry">
        <xsl:element name="Arg">
            <xsl:attribute name="name" select="@key"/>
            <xsl:variable name="gt" select="grammar:new('cal/expr.grammar')"/>
            <xsl:variable name="concreteTree" select="grammar:parseString($gt, @value)"/>
            <xsl:apply-templates select="$concreteTree"/>
        </xsl:element>
    </xsl:template>

    <!-- Connections -->
    <xsl:template match="edge">
        <xsl:element name="StructureStmt">
            <xsl:attribute name="kind" select="'Connection'"/>

            <xsl:call-template name="PortSpec">
                <xsl:with-param name="vertex" select="@source"/>
                <xsl:with-param name="port"
                    select="parameters/parameter[@name = 'source port']/@value"/>
            </xsl:call-template>

            <xsl:call-template name="PortSpec">
                <xsl:with-param name="vertex" select="@target"/>
                <xsl:with-param name="port"
                    select="parameters/parameter[@name = 'target port']/@value"/>
            </xsl:call-template>
        </xsl:element>
    </xsl:template>

    <!-- Create a PortSpec element -->
    <xsl:template name="PortSpec">
        <xsl:param name="vertex"/>
        <xsl:param name="port"/>

        <xsl:choose>
            <xsl:when test="$port != ''">
                <xsl:element name="PortSpec">
                    <xsl:attribute name="kind" select="'Entity'"/>
                    <xsl:element name="EntityRef">
                        <xsl:attribute name="name" select="$vertex"/>
                    </xsl:element>
                    <xsl:element name="PortRef">
                        <xsl:attribute name="name" select="$port"/>
                    </xsl:element>
                </xsl:element>
            </xsl:when>
            <xsl:otherwise>
                <xsl:element name="PortSpec">
                    <xsl:attribute name="kind" select="'Local'"/>
                    <xsl:element name="PortRef">
                        <xsl:attribute name="name" select="$vertex"/>
                    </xsl:element>
                </xsl:element>
            </xsl:otherwise>
        </xsl:choose>
    </xsl:template>

</xsl:stylesheet>
