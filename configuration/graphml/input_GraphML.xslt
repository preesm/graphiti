<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:graphml="http://graphml.graphdrawing.org/xmlns/1.0rc"
	xmlns:fn="http://www.w3.org/2005/xpath-functions"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="2.0">

	<xsl:output indent="yes" method="xml"/>

	<xsl:template match="text()"/>
	
	<xsl:template match="graphml">
		<xsl:apply-templates select="key"/>
		<xsl:apply-templates select="graph"/>
	</xsl:template>
	
	<xsl:template match="key">
	</xsl:template>
	
	<xsl:template match="graph">
		<xsl:element name="graph">
			<xsl:attribute name="type" select="'GraphML graph'"/>

			<xsl:element name="parameters">
				
			</xsl:element>

			<xsl:element name="vertices">
				<xsl:apply-templates select="node"/>
			</xsl:element>
		
			<xsl:element name="edges">
				<xsl:apply-templates select="edge"/>		
			</xsl:element>
			
		</xsl:element>
	</xsl:template>
	
	<!-- Instances -->
	
	<xsl:template match="node">
		<xsl:element name="vertex">
			<xsl:attribute name="type">Instance</xsl:attribute>
			<xsl:element name="parameters">
				<xsl:element name="parameter">
					<xsl:attribute name="name">id</xsl:attribute>
					<xsl:attribute name="value" select="@id"/>
				</xsl:element>
			</xsl:element>
		</xsl:element>
	</xsl:template>
	
	
	<xsl:template match="edge">
		<xsl:element name="edge">
			<xsl:attribute name="source" select="@source"/>
			<xsl:attribute name="target" select="@target"/>
			<xsl:attribute name="type">edge</xsl:attribute>
			
			<xsl:element name="parameters"/>
		</xsl:element>
	</xsl:template>




</xsl:stylesheet>
