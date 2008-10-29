<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:fn="http://www.w3.org/2005/xpath-functions"
    xmlns:preesm="http://ietr-image.insa-rennes.fr/projects/Preesm"
    xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="2.0">

    <xsl:output indent="yes" method="xml"/>

    <xsl:template match="text()"/>
    
    <!-- writes the layout in a file that has the same name as the target document,
        except with .layout extension. -->
    <xsl:param name="path"/>
    <xsl:variable name="file" select="fn:replace($path, '(.+)[.].+', '$1.layout')"/>

    <!-- Top-level: graph -> XDF -->
    <xsl:template match="graph">
        
        <xsl:result-document href="$file" method="xml" indent="yes">
            <layout>
                
            </layout>
        </xsl:result-document>
        
        <xsl:element name="preesm:workflow">
        </xsl:element>
    </xsl:template>

</xsl:stylesheet>
