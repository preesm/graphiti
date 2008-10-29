<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:graphml="http://graphml.graphdrawing.org/xmlns/1.0rc"
    xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="2.0">

    <xsl:output indent="yes" method="xml"/>

    <xsl:template match="text()"/>

    <!-- Top-level: graph -> graph -->
    <xsl:template match="graphml:graph[position() = 1 and @edgedefault = 'directed']">
        <xsl:element name="graph">
            <xsl:attribute name="type">SDF Graph</xsl:attribute>

            <xsl:element name="parameters">
                <xsl:element name="parameter">
                    <xsl:attribute name="name">graph parameter</xsl:attribute>
                    <xsl:apply-templates select="graphml:data[@key = 'parameters']"/>
                </xsl:element>

                <xsl:element name="parameter">
                    <xsl:attribute name="name">graph variable</xsl:attribute>
                    <xsl:apply-templates select="graphml:data[@key = 'variables']"/>
                </xsl:element>
            </xsl:element>

            <xsl:element name="vertices">
                <xsl:apply-templates select="graphml:node"/>
            </xsl:element>

            <xsl:element name="edges">
                <xsl:apply-templates select="graphml:edge"/>
            </xsl:element>
        </xsl:element>
    </xsl:template>

    <!-- Parameter declarations -->
    <xsl:template match="graphml:data[@key = 'parameters']/graphml:parameter">
        <xsl:element name="element">
            <xsl:attribute name="value">
                <xsl:value-of select="@name"/>
            </xsl:attribute>
        </xsl:element>
    </xsl:template>

    <!-- Variable declarations -->
    <xsl:template match="graphml:data[@key = 'variables']/graphml:variable">
        <xsl:element name="entry">
            <xsl:attribute name="key" select="@name"/>
            <xsl:attribute name="value" select="@value"/>
        </xsl:element>
    </xsl:template>

    <!-- node -->
    <xsl:template match="graphml:node[@kind = 'vertex']">
        <xsl:element name="vertex">
            <xsl:attribute name="type">Vertex</xsl:attribute>
            <xsl:element name="parameters">
                <xsl:element name="parameter">
                    <xsl:attribute name="name">id</xsl:attribute>
                    <xsl:attribute name="value">
                        <xsl:value-of select="@id"/>
                    </xsl:attribute>
                </xsl:element>

                <xsl:element name="parameter">
                    <xsl:attribute name="name">refinement</xsl:attribute>
                    <xsl:attribute name="value">
                        <xsl:value-of select="graphml:data[@key = 'graph_desc']/text()"/>
                    </xsl:attribute>
                </xsl:element>

                <xsl:element name="parameter">
                    <xsl:attribute name="name">actual parameter</xsl:attribute>
                    <xsl:apply-templates select="graphml:data[@key = 'arguments']"/>
                </xsl:element>
            </xsl:element>
        </xsl:element>
    </xsl:template>

    <!-- node parameter -->
    <xsl:template match="graphml:data[@key = 'arguments']/graphml:argument">
        <xsl:element name="entry">
            <xsl:attribute name="key" select="@name"/>
            <xsl:attribute name="value" select="@value"/>
        </xsl:element>
    </xsl:template>

    <!-- input/output port -->
    <xsl:template match="graphml:node[@kind = 'port']">
        <xsl:element name="vertex">
            <xsl:attribute name="type" select="concat(@port_direction, ' port')"/>
            <xsl:element name="parameters">
                <xsl:element name="parameter">
                    <xsl:attribute name="name">id</xsl:attribute>
                    <xsl:attribute name="value" select="@id"/>
                </xsl:element>
            </xsl:element>
        </xsl:element>
    </xsl:template>

    <!-- edge -->
    <xsl:template match="graphml:edge">
        <xsl:element name="edge">
            <xsl:attribute name="type">Dataflow edge</xsl:attribute>
            <xsl:attribute name="source" select="@source"/>
            <xsl:attribute name="target" select="@target"/>
            <xsl:element name="parameters">
                <xsl:element name="parameter">
                    <xsl:attribute name="name">source port</xsl:attribute>
                    <xsl:attribute name="value" select="@sourceport"/>
                </xsl:element>

                <xsl:element name="parameter">
                    <xsl:attribute name="name">target port</xsl:attribute>
                    <xsl:attribute name="value" select="@targetport"/>
                </xsl:element>

                <xsl:element name="parameter">
                    <xsl:attribute name="name">source production</xsl:attribute>
                    <xsl:attribute name="value" select="graphml:data[@key = 'edge_prod']/text()"/>
                </xsl:element>

                <xsl:element name="parameter">
                    <xsl:attribute name="name">target consumption</xsl:attribute>
                    <xsl:attribute name="value" select="graphml:data[@key = 'edge_cons']/text()"/>
                </xsl:element>

                <xsl:element name="parameter">
                    <xsl:attribute name="name">delay</xsl:attribute>
                    <xsl:attribute name="value" select="graphml:data[@key = 'edge_delay']/text()"/>
                </xsl:element>
            </xsl:element>
        </xsl:element>
    </xsl:template>

</xsl:stylesheet>
