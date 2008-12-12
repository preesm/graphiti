<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns="http://graphml.graphdrawing.org/xmlns"
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
        <xsl:result-document href="file:///{$file}" method="xml" indent="yes">
            <xsl:call-template name="setLayout"/>
        </xsl:result-document>

        <!-- graph -->
        <graphml>
            <key attr.name="graph_desc" attr.type="string" for="node" id="graph_desc"/>

            <key attr.name="name" attr.type="string" for="node" id="name"/>
            <key attr.name="arguments" attr.type="string" for="node" id="arguments"/>
            <key attr.name="parameters" attr.type="string" for="graph" id="parameters"/>
            <key attr.name="variables" attr.type="string" for="graph" id="variables"/>
            <key attr.name="edge_prod" attr.type="int" for="edge" id="edge_prod">
                <desc>org.sdf4j.model.sdf.types.SDFNumericalEdgePropertyTypeFactory</desc>
            </key>
            <key attr.name="edge_delay" attr.type="int" for="edge" id="edge_delay">
                <desc>org.sdf4j.model.sdf.types.SDFNumericalEdgePropertyTypeFactory</desc>
            </key>
            <key attr.name="edge_cons" attr.type="int" for="edge" id="edge_cons">
                <desc>org.sdf4j.model.sdf.types.SDFNumericalEdgePropertyTypeFactory</desc>
            </key>
            <key attr.name="data_type" attr.type="string" for="edge" id="data_type">
                <desc>org.sdf4j.model.sdf.types.SDFTextualEdgePropertyTypeFactory</desc>
            </key>

            <graph edgedefault="directed">
                
                <xsl:apply-templates select="parameters/parameter[@name = 'graph parameter']"/>
                <xsl:apply-templates select="parameters/parameter[@name = 'graph variable']"/>

                <xsl:apply-templates select="vertices/vertex"/>
                <xsl:apply-templates select="edges/edge"/>
            </graph>
        </graphml>
    </xsl:template>

    <!-- Parameter declarations -->
    <xsl:template match="parameter[@name = 'graph parameter']">
        <xsl:if test="element">
            <data key="parameters">
                <xsl:call-template name="merge_text_value">
                    <xsl:with-param name="some-text" select="element"/>
                </xsl:call-template>
            </data>
        </xsl:if>
    </xsl:template>

    <!-- Variable declarations -->
    <xsl:template match="parameter[@name = 'graph variable']">
        <xsl:if test="entry">
        <data key="variables">
            <xsl:call-template name="merge_text_key_and_value">
                <xsl:with-param name="some-text" select="entry"/>
            </xsl:call-template>
        </data>
        </xsl:if>
    </xsl:template>

    <!-- node -->
    <xsl:template match="vertex[@type = 'Vertex']">
        <node kind="vertex" id="{parameters/parameter[@name = 'id']/@value}">
            <data key="graph_desc">
                <xsl:value-of select="parameters/parameter[@name = 'refinement']/@value"/>
            </data>
            
            <data key="name">
                <xsl:value-of select="parameters/parameter[@name = 'id']/@value"/>
            </data>

            <xsl:apply-templates select="parameters/parameter[@name = 'actual parameter']"/>

        </node>
    </xsl:template>
    
    <!-- merge text from multiple elements adding key and value for each -->
    <xsl:template name="merge_text_key_and_value">
        <xsl:param name="some-text"/>
        <xsl:choose>
            <xsl:when test="$some-text">
                <xsl:variable name="first-line" select="concat($some-text[1]/@key,'=',$some-text[1]/@value)"/>
                <xsl:variable name="other-lines">
                    <xsl:call-template name="merge_text_key_and_value">
                        <xsl:with-param name="some-text" select="$some-text[position() != 1]"/>
                    </xsl:call-template>
                </xsl:variable>
                <xsl:choose >
                    <xsl:when test="$other-lines!=''" >
                        <xsl:value-of select="concat($first-line, ',', $other-lines)"/>
                    </xsl:when>
                    <xsl:otherwise >
                        <xsl:value-of select="$first-line"/>
                    </xsl:otherwise>
                </xsl:choose> 
            </xsl:when>
            <xsl:otherwise></xsl:otherwise>
        </xsl:choose>
    </xsl:template>
    
    <!-- merge text from multiple elements adding key -->
    <xsl:template name="merge_text_value">
        <xsl:param name="some-text"/>
        <xsl:choose>
            <xsl:when test="$some-text">
                <xsl:variable name="first-line" select="$some-text[1]/@value"/>
                <xsl:variable name="other-lines">
                    <xsl:call-template name="merge_text_value">
                        <xsl:with-param name="some-text" select="$some-text[position() != 1]"/>
                    </xsl:call-template>
                </xsl:variable>
                <xsl:choose >
                    <xsl:when test="$other-lines!=''" >
                        <xsl:value-of select="concat($first-line, ',', $other-lines)"/>
                    </xsl:when>
                    <xsl:otherwise >
                        <xsl:value-of select="$first-line"/>
                    </xsl:otherwise>
                </xsl:choose> 
            </xsl:when>
            <xsl:otherwise></xsl:otherwise>
        </xsl:choose>
    </xsl:template>
    
    
    <!-- node parameter -->
    <xsl:template match="parameter[@name = 'actual parameter']">
        <xsl:if test="entry">
        <data key="arguments">
            <xsl:call-template name="merge_text_key_and_value">
                <xsl:with-param name="some-text" select="entry"/>
            </xsl:call-template>
        </data>
        </xsl:if>
    </xsl:template>

    <!-- input/output port -->
    <xsl:template match="vertex[@type = 'Input port' or @type = 'Output port']">
        <node id="{parameters/parameter[@name = 'id']/@value}" kind="port"
            port_direction="{replace(@type, '(Input|Output) port', '$1')}"/>
    </xsl:template>

    <!-- broadcast vertex -->
    <xsl:template match="vertex[@type = 'Broadcast']">
        <node id="{parameters/parameter[@name = 'id']/@value}" kind="Broadcast"/>
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
            
            <data key="data_type">
                <xsl:variable name="dataType" select="parameters/parameter[@name = 'data type']/@value"/>
                <xsl:value-of select="$dataType"/>
            </data>
        </edge>
    </xsl:template>

</xsl:stylesheet>
