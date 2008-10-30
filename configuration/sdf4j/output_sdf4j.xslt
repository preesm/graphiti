<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns="http://graphml.graphdrawing.org/xmlns/1.0rc"
    xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="2.0">
    
    <xsl:import href="output_layout.xslt"/>

    <xsl:output indent="yes" method="xml"/>

    <xsl:template match="text()"/>

    <!-- writes the layout in a file that has the same name as the target document,
        except with .layout extension. -->
    <xsl:param name="path"/>
    <xsl:variable name="file" select="replace($path, '(.+)[.].+', '$1.layout')"/>

    <!-- Top-level: graph -> graph -->
    <xsl:template match="graph">
        
        <!-- layout information -->
        <xsl:result-document href="file:/{$file}" method="xml" indent="yes">
            <xsl:call-template name="setLayout"/>
        </xsl:result-document>

        <!-- graph -->
        <graphml>
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
        </graphml>
    </xsl:template>

    <!-- Parameter declarations -->
    <xsl:template match="parameter[@name = 'graph parameter']/element">
        <parameter name="{@value}"/>
    </xsl:template>

    <!-- Variable declarations -->
    <xsl:template match="parameter[@name = 'graph variable']/entry">
        <variable name="{@key}" value="{@value}"/>
    </xsl:template>

    <!-- node -->
    <xsl:template match="vertex[@type = 'Vertex']">
        <node kind="vertex" id="{parameters/parameter[@name = 'id']/@value}">
            <data key="graph_desc">
                <xsl:value-of select="parameters/parameter[@name = 'refinement']/@value"/>
            </data>

            <data key="arguments">
                <xsl:apply-templates select="parameters/parameter[@name = 'actual parameter']"/>
            </data>
        </node>
    </xsl:template>

    <!-- node parameter -->
    <xsl:template match="parameter[@name = 'actual parameter']/entry">
        <argument name="{@key}" value="{@value}"/>
    </xsl:template>

    <!-- input/output port -->
    <xsl:template match="vertex[@type = 'Input port' or @type = 'Output port']">
        <node id="{parameters/parameter[@name = 'id']/@value}" kind="port"
            port_direction="{replace(@type, '(Input|Output) port', '$1')}"/>
    </xsl:template>

    <!-- edge -->
    <xsl:template match="edge">
        <edge source="{@source}" target="{@target}"
            sourceport="{parameters/parameter[@name = 'source port']/@value}"
            targetport="{parameters/parameter[@name = 'target port']/@value}">
            <data key="edge_prod">
                <xsl:value-of select="parameters/parameter[@name = 'source production']/@value"/>
            </data>

            <data key="edge_delay">
                <xsl:value-of select="parameters/parameter[@name = 'delay']/@value"/>
            </data>

            <data key="edge_cons">
                <xsl:value-of select="parameters/parameter[@name = 'target consumption']/@value"/>
            </data>
        </edge>
    </xsl:template>

</xsl:stylesheet>
