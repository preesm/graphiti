<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
  version="1.0">

<xsl:output indent="yes" method="xml"/>

<xsl:template match="/">
<testroot>
    <xsl:call-template name="output-tokens">
			<xsl:with-param name="list">1,2,3,4,5</xsl:with-param>
			<xsl:with-param name="delimiter">,</xsl:with-param>
    </xsl:call-template>
</testroot></xsl:template>

<xsl:template name="output-tokens">
    <xsl:param name="list" />
    <xsl:param name="delimiter" />
    <xsl:variable name="newlist">
		<xsl:choose>
			<xsl:when test="contains($list, $delimiter)"><xsl:value-of select="normalize-space($list)" /></xsl:when>
			
			<xsl:otherwise><xsl:value-of select="concat(normalize-space($list), $delimiter)"/></xsl:otherwise>
		</xsl:choose>
	</xsl:variable>
    <xsl:variable name="first" select="substring-before($newlist, $delimiter)" />
    <xsl:variable name="remaining" select="substring-after($newlist, $delimiter)" />
    <num><xsl:value-of select="$first" /></num>
    <xsl:if test="$remaining">
        <xsl:call-template name="output-tokens">
            <xsl:with-param name="list" select="$remaining" />
			<xsl:with-param name="delimiter"><xsl:value-of select="$delimiter"/></xsl:with-param>
        </xsl:call-template>
    </xsl:if>
</xsl:template>
</xsl:stylesheet>


