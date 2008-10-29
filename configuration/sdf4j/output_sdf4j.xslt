<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns="http://graphml.graphdrawing.org/xmlns/1.0rc"
    xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="2.0">

    <xsl:output indent="yes" method="xml"/>

    <xsl:template match="text()"/>

    <!-- Top-level: graph -> graph -->
    <xsl:template match="graph">
        <xsl:element name="graphml">
            <key attr.name="graph_desc" attr.type="string" for="node" id="graph_desc">
                <desc>java.lang.String</desc>
            </key>
            <key attr.name="arguments" for="node" id="arguments"/>
            <key attr.name="parameters" for="graph" id="parameters"/>
            <key attr.name="variables" for="graph" id="variables"/>
            <key attr.name="edge_prod" attr.type="int" for="edge" id="edge_prod">
                <desc>org.sdf4j.model.sdf.SDFDefaultEdgePropertyType</desc>
            </key>
            <key attr.name="edge_delay" attr.type="int" for="edge" id="edge_delay">
                <desc>org.sdf4j.model.sdf.SDFDefaultEdgePropertyType</desc>
            </key>
            <key attr.name="edge_cons" attr.type="int" for="edge" id="edge_cons">
                <desc>org.sdf4j.model.sdf.SDFDefaultEdgePropertyType</desc>
            </key>

            <graph edgedefault="directed">
                <data key="parameters">
                    <xsl:apply-templates select="parameters/parameter[@name = 'graph parameter']"/>
                </data>

                <data key="variables">
                    <xsl:apply-templates select="parameters/parameter[@name = 'graph variable']"/>
                </data>

                <xsl:apply-templates select="vertices/vertex"/>
                <xsl:apply-templates select="edges/edge"/>
            </graph>
        </xsl:element>
    </xsl:template>

    <!-- Parameter declarations -->
    <xsl:template match="parameter[@name = 'graph parameter']/element">
        <xsl:element name="parameter">
            <xsl:attribute name="name" select="@value"/>
        </xsl:element>
    </xsl:template>

    <!-- Variable declarations -->
    <xsl:template match="parameter[@name = 'graph variable']/entry">
        <xsl:element name="variable">
            <xsl:attribute name="name" select="@key"/>
            <xsl:attribute name="value" select="@value"/>
        </xsl:element>
    </xsl:template>

    <!-- node -->
    <xsl:template match="vertex[@type = 'Vertex']">
        <xsl:element name="node">
            <xsl:attribute name="kind" select="'vertex'"/>
            <xsl:attribute name="id" select="parameters/parameter[@name = 'id']/@value"/>

            <data key="graph_desc">
                <xsl:value-of select="parameters/parameter[@name = 'refinement']/@value"/>
            </data>

            <data key="arguments">
                <xsl:apply-templates select="parameters/parameter[@name = 'actual parameter']"/>
            </data>
        </xsl:element>
    </xsl:template>

    <!-- node parameter -->
    <xsl:template match="parameter[@name = 'actual parameter']/entry">
        <xsl:element name="argument">
            <xsl:attribute name="name" select="@key"/>
            <xsl:attribute name="value" select="@value"/>
        </xsl:element>
    </xsl:template>

    <!-- input/output port -->
    <xsl:template match="vertex[@type = 'Input port' or @type = 'Output port']">
        <xsl:element name="node">
            <xsl:attribute name="id" select="parameters/parameter[@name = 'id']/@value"/>
            <xsl:attribute name="kind" select="'port'"/>
            <xsl:attribute name="port_direction"
                select="replace(@type, '(Input|Output) port', '$1')"/>
        </xsl:element>
    </xsl:template>

    <!-- edge -->
    <xsl:template match="edge">
        <xsl:element name="edge">
            <xsl:attribute name="source" select="@source"/>
            <xsl:attribute name="target" select="@target"/>
            <xsl:attribute name="sourceport"
                select="parameters/parameter[@name = 'source port']/@value"/>
            <xsl:attribute name="targetport"
                select="parameters/parameter[@name = 'target port']/@value"/>

            <data key="edge_prod">
                <xsl:value-of select="parameters/parameter[@name = 'source production']/@value"/>
            </data>

            <data key="edge_delay">
                <xsl:value-of select="parameters/parameter[@name = 'delay']/@value"/>
            </data>

            <data key="edge_cons">
                <xsl:value-of select="parameters/parameter[@name = 'target consumption']/@value"/>
            </data>
        </xsl:element>
    </xsl:template>

</xsl:stylesheet>
