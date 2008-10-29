<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:fn="http://www.w3.org/2005/xpath-functions"
    xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="2.0">

    <xsl:import href="../cal/exprToString.xslt"/>

    <xsl:output indent="yes" method="xml"/>

    <xsl:template match="text()"/>

    <!-- reads the layout in a file that has the same name as the source document,
        except with .layout extension. -->
    <xsl:param name="path"/>
    <xsl:variable name="file" select="fn:replace($path, '(.+)[.].+', '$1.layout')"/>
    <xsl:variable name="layout" select="document($file)"/>

    <!-- returns two attributes x and y that contains the position of the vertex,
        if specified in $layout -->
    <xsl:template name="getVertexLayoutAttributes">
        <xsl:param name="vertexId"/>
        <xsl:if test="not(empty($layout))">
            <xsl:variable name="vertex" select="$layout/layout/vertices/vertex[@id = $vertexId]"/>
            <xsl:if test="not(empty($vertex))">
                <xsl:attribute name="x" select="$vertex/@x"/>
                <xsl:attribute name="y" select="$vertex/@y"/>
            </xsl:if>
        </xsl:if>
    </xsl:template>

    <!-- Top-level: XDF -> graph -->
    <xsl:template match="XDF">

        <xsl:element name="graph">
            <xsl:attribute name="type">XML Dataflow Network</xsl:attribute>

            <xsl:element name="parameters">
                <xsl:element name="parameter">
                    <xsl:attribute name="name">id</xsl:attribute>
                    <xsl:attribute name="value" select="@name"/>
                </xsl:element>

                <xsl:element name="parameter">
                    <xsl:attribute name="name">network parameter</xsl:attribute>
                    <xsl:apply-templates select="Decl[@kind = 'Param']"/>
                </xsl:element>

                <xsl:element name="parameter">
                    <xsl:attribute name="name">network variable declaration</xsl:attribute>
                    <xsl:apply-templates select="Decl[@kind = 'Variable']"/>
                </xsl:element>
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

    <!-- Parameter declarations -->
    <xsl:template match="Decl[@kind = 'Param']">
        <xsl:element name="element">
            <xsl:attribute name="value">
                <xsl:value-of select="@name"/>
            </xsl:attribute>
        </xsl:element>
    </xsl:template>

    <!-- Variable declarations -->
    <xsl:template match="Decl[@kind = 'Variable']">
        <xsl:element name="entry">
            <xsl:attribute name="key" select="@name"/>
            <xsl:attribute name="value">
                <xsl:apply-templates select="Expr"/>
            </xsl:attribute>
        </xsl:element>
    </xsl:template>

    <!-- Input/output ports -->
    <xsl:template match="Port">
        <xsl:element name="vertex">
            <xsl:attribute name="type" select="fn:concat(@kind, ' port')"/>

            <xsl:call-template name="getVertexLayoutAttributes">
                <xsl:with-param name="vertexId" select="@id"/>
            </xsl:call-template>

            <xsl:element name="parameters">
                <xsl:element name="parameter">
                    <xsl:attribute name="name">id</xsl:attribute>
                    <xsl:attribute name="value" select="@name"/>
                </xsl:element>
            </xsl:element>
        </xsl:element>
    </xsl:template>

    <!-- Instances -->
    <xsl:template match="Instance">
        <xsl:element name="vertex">
            <xsl:attribute name="type">Instance</xsl:attribute>

            <xsl:call-template name="getVertexLayoutAttributes">
                <xsl:with-param name="vertexId" select="@id"/>
            </xsl:call-template>

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

    <!-- Parameter instantiations -->
    <xsl:template match="Parameter">
        <xsl:element name="entry">
            <xsl:attribute name="key" select="@name"/>
            <xsl:attribute name="value">
                <xsl:apply-templates select="Expr"/>
            </xsl:attribute>
        </xsl:element>
    </xsl:template>

    <!-- Connections -->
    <xsl:template match="Connection">
        <xsl:element name="edge">
            <xsl:attribute name="type">Connection</xsl:attribute>
            <xsl:choose>
                <xsl:when test="@src = ''">
                    <xsl:attribute name="source" select="@src-port"/>
                </xsl:when>
                <xsl:otherwise>
                    <xsl:attribute name="source" select="@src"/>
                </xsl:otherwise>
            </xsl:choose>
            <xsl:choose>
                <xsl:when test="@dst = ''">
                    <xsl:attribute name="target" select="@dst-port"/>
                </xsl:when>
                <xsl:otherwise>
                    <xsl:attribute name="target" select="@dst"/>
                </xsl:otherwise>
            </xsl:choose>
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

</xsl:stylesheet>
