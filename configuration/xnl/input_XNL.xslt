<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="2.0">

    <xsl:import href="../cal/exprToString.xslt"/>

    <xsl:output indent="yes" method="xml"/>

    <xsl:template match="text()"/>

    <!-- reads the layout in a file that has the same name as the source document,
        except with .layout extension. -->
    <xsl:param name="path"/>
    <xsl:variable name="file" select="replace($path, '(.+)[.].+', '$1.layout')"/>
    <xsl:variable name="layout" select="document(concat('file:/', $file))"/>

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

    <!-- Top-level: Network -> graph -->
    <xsl:template match="Network">
        <graph type="XML Network Language">
            <parameters>
				<parameter name="id" value="{QID/@name}"/>
                <parameter name="network parameter">
                    <xsl:apply-templates select="Decl[@kind = 'Parameter']"/>
                </parameter>
                <parameter name="network variable declaration">
                    <xsl:apply-templates select="Decl[@kind = 'Variable']"/>
                </parameter>
            </parameters>

            <vertices>
                <xsl:apply-templates select="Port"/>
                <xsl:apply-templates select="EntityDecl"/>
            </vertices>

            <edges>
                <xsl:apply-templates select="Connection"/>
            </edges>
        </graph>
    </xsl:template>

    <!-- Parameter declarations -->
    <xsl:template match="Decl[@kind = 'Parameter']">
        <element value="{@name}"/>
    </xsl:template>

    <!-- Variable declarations -->
    <xsl:template match="Decl[@kind = 'Variable']">
        <entry key="{@name}">
            <xsl:attribute name="value">
                <xsl:apply-templates select="Expr"/>
            </xsl:attribute>
        </entry>
    </xsl:template>

    <!-- Input/output ports -->
    <xsl:template match="Port">
        <vertex type="{concat(@kind, ' port')}">
            <xsl:call-template name="getVertexLayoutAttributes">
                <xsl:with-param name="vertexId" select="@name"/>
            </xsl:call-template>

            <parameters>
                <parameter name="id" value="{@name}"/>
            </parameters>
        </vertex>
    </xsl:template>

    <!-- Instances -->
    <xsl:template match="EntityDecl">
        <vertex type="Instance">
            <xsl:call-template name="getVertexLayoutAttributes">
                <xsl:with-param name="vertexId" select="@name"/>
            </xsl:call-template>

            <parameters>
                <parameter name="id" value="{@name}"/>
                <xsl:element name="parameter">
                    <xsl:attribute name="name">refinement</xsl:attribute>
                    <xsl:attribute name="value" select="EntityExpr/@name"/>
                </xsl:element>
                <xsl:element name="parameter">
                    <xsl:attribute name="name">instance parameter</xsl:attribute>
					<xsl:apply-templates select="EntityExpr/Arg"/>
				</xsl:element>
            </parameters>
        </vertex>
    </xsl:template>

    <!-- Parameter instantiations -->
    <xsl:template match="Arg">
        <entry key="{@name}">
            <xsl:attribute name="value">
                <xsl:apply-templates select="Expr"/>
            </xsl:attribute>
        </entry>
    </xsl:template>

    <!-- Connections -->
    <xsl:template match="Connection">
        <edge type="Connection">
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
            <parameters>
                <xsl:if test="@src != ''">
                    <parameter name="source port" value="{@src-port}"/>
                </xsl:if>
                <xsl:if test="@dst != ''">
                    <parameter name="target port" value="{@dst-port}"/>
                </xsl:if>
            </parameters>
        </edge>
    </xsl:template>

</xsl:stylesheet>
