<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:graphml="http://graphml.graphdrawing.org/xmlns/1.0rc"
    xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="2.0">

    <xsl:output indent="yes" method="xml"/>

    <xsl:template match="text()"/>

    <!-- reads the layout in a file that has the same name as the source document,
        except with .layout extension. -->
    <xsl:param name="path"/>
    <xsl:variable name="file" select="replace($path, '(.+)[.].+', '$1.layout')"/>
    <xsl:variable name="layout" select="document(concat('file:///', $file))"/>

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
    <xsl:template match="graphml:data[@key = 'parameters']">
        <xsl:variable name="parameters" select="."/>
        <xsl:call-template name="addParameters">
            <xsl:with-param name="list" select="$parameters"/>
        </xsl:call-template>
    </xsl:template>

    <!-- Variable declarations -->
    <xsl:template match="graphml:data[@key = 'variables']">
        <xsl:variable name="variables" select="."/>
        <xsl:call-template name="addVariables">
            <xsl:with-param name="list" select="$variables" />
        </xsl:call-template>
    </xsl:template>

    <!-- node -->
    <xsl:template match="graphml:node[@kind = 'vertex']">
        <xsl:element name="vertex">
            <xsl:attribute name="type">Vertex</xsl:attribute>

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
                    <xsl:attribute name="value" select="graphml:data[@key = 'graph_desc']/text()"/>
                </xsl:element>

                <xsl:element name="parameter">
                    <xsl:attribute name="name">actual parameter</xsl:attribute>
                    <xsl:apply-templates select="graphml:data[@key = 'arguments']"/>
                </xsl:element>
            </xsl:element>
        </xsl:element>
    </xsl:template>
    
    <xsl:template name="addArguments">  
        <xsl:param name="list" />
        
        <xsl:variable name="newlist" select="concat(normalize-space($list), ',')" />
        <xsl:variable name="first" select="substring-before($newlist, ',')" />
        <xsl:variable name="remaining" select="substring-after($newlist, ',')" />
        <xsl:variable name="argumentName" select="substring-before($first, '=')" />
        <xsl:variable name="argumentValue" select="substring-after($first, '=')" />
        <xsl:element name="entry">
            <xsl:attribute name="key" select="$argumentName"/>
            <xsl:attribute name="value" select="$argumentValue"/>
        </xsl:element>
        <xsl:if test="$remaining!='' and $remaining!=','">
            <xsl:call-template name="addArguments">
                <xsl:with-param name="list" select="$remaining" />
            </xsl:call-template>
        </xsl:if>
    </xsl:template>
    
    <xsl:template name="addParameters">  
        <xsl:param name="list" />
        
        <xsl:variable name="newlist" select="concat(normalize-space($list), ',')" />
        <xsl:variable name="first" select="substring-before($newlist, ',')" />
        <xsl:variable name="remaining" select="substring-after($newlist, ',')" />
        <xsl:variable name="paramName" select="$first" />
        <xsl:element name="element">
            <xsl:attribute name="value" select="$paramName"/>
        </xsl:element>
        <xsl:if test="$remaining!='' and $remaining!=','">
            <xsl:call-template name="addParameters">
                <xsl:with-param name="list" select="$remaining" />
            </xsl:call-template>
        </xsl:if>
    </xsl:template>
    
    <xsl:template name="addVariables">  
        <xsl:param name="list" />
        
        <xsl:variable name="newlist" select="concat(normalize-space($list), ',')" />
        <xsl:variable name="first" select="substring-before($newlist, ',')" />
        <xsl:variable name="remaining" select="substring-after($newlist, ',')" />
        <xsl:variable name="variableName" select="substring-before($first, '=')" />
        <xsl:variable name="variableValue" select="substring-after($first, '=')" />
        <xsl:element name="entry">
            <xsl:attribute name="key" select="$variableName"/>
            <xsl:attribute name="value" select="$variableValue"/>
        </xsl:element>
        <xsl:if test="$remaining!='' and $remaining!=','">
            <xsl:call-template name="addVariables">
                <xsl:with-param name="list" select="$remaining" />
            </xsl:call-template>
        </xsl:if>
    </xsl:template>
        
    <!-- node parameter -->
    <xsl:template match="graphml:data[@key = 'arguments']">
        <xsl:variable name="arguments" select="."/>
        <xsl:call-template name="addArguments">
            <xsl:with-param name="list" select="$arguments" />
        </xsl:call-template>
    </xsl:template>

    <!-- input/output port -->
    <xsl:template match="graphml:node[@kind = 'port']">
        <xsl:element name="vertex">
            <xsl:attribute name="type" select="concat(@port_direction, ' port')"/>

            <xsl:call-template name="getVertexLayoutAttributes">
                <xsl:with-param name="vertexId" select="@id"/>
            </xsl:call-template>

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
