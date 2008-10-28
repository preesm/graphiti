<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:fn="http://www.w3.org/2005/xpath-functions"
    xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="2.0">
    
    <xsl:template match="XDF">
        <xsl:result-document href="file:///C:/a.xml" method="xml" indent="yes">
            <xsl:element name="toto"></xsl:element>
        </xsl:result-document>
        
        <xsl:result-document href="file:///C:/b.xml" method="xml" indent="yes">
            <xsl:element name="tata"></xsl:element>
        </xsl:result-document>
    </xsl:template>

</xsl:stylesheet>
