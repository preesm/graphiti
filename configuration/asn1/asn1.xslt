<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="2.0">

    <xsl:output indent="yes" method="xml"/>

    <xsl:template match="text()"/>

    <!-- pattern for hexadecimal -->
    <xsl:variable name="hexPattern">'([0-9a-fA-F]+)'H</xsl:variable>

    <!-- pattern for quoted string -->
    <xsl:variable name="strPattern">"(.+)"</xsl:variable>

    <!-- Top-level module definition -->
    <xsl:template match="ModuleDefinition">
        <definition>
            <xsl:apply-templates/>
        </definition>
    </xsl:template>
    
    <!-- production -->
    <xsl:template match="TypeAssignment">
        <production name="{IDENTIFIER_STRING[1]/text()}">
            <xsl:apply-templates select="Type"/>
        </production>
    </xsl:template>

    <!-- BIT STRING -->
    <xsl:template match="BitStringType">
        <bitString>
            <xsl:apply-templates select="ValueOrConstraintList/ConstraintList"/>
        </bitString>
    </xsl:template>

    <!-- CHOICE -->
    <xsl:template match="ChoiceType">
        <choice>
            <xsl:apply-templates select="ElementTypeList/ElementType"/>
        </choice>
    </xsl:template>

    <xsl:template match="ChoiceType/ElementTypeList/ElementType">
        <alternative>
            <xsl:if test="IDENTIFIER_STRING">
                <xsl:attribute name="name" select="IDENTIFIER_STRING/text()"/>
            </xsl:if>
            <xsl:apply-templates select="Type"/>
        </alternative>
    </xsl:template>

    <!-- DEFINED -->
    <xsl:template match="DefinedType">
        <type name="{IDENTIFIER_STRING/text()}">
            <xsl:apply-templates select="ValueOrConstraintList/ConstraintList"/>
        </type>
    </xsl:template>

    <!-- INTEGER -->
    <xsl:template match="IntegerType">
        <integer>
            <xsl:apply-templates select="ValueOrConstraintList/ConstraintList"/>
        </integer>
    </xsl:template>

    <!-- SEQUENCE -->
    <xsl:template match="SequenceType">
        <sequence>
            <xsl:apply-templates select="ElementTypeList/ElementType"/>
        </sequence>
    </xsl:template>

    <xsl:template match="SequenceType/ElementTypeList/ElementType">
        <element>
            <xsl:if test="IDENTIFIER_STRING">
                <xsl:attribute name="name" select="IDENTIFIER_STRING/text()"/>
            </xsl:if>
            <xsl:apply-templates select="Type"/>
        </element>
    </xsl:template>

    <!-- SEQUENCE OF -->
    <xsl:template match="SequenceOfType">
        <sequenceOf>
            <xsl:apply-templates select="ConstraintList/Constraint"/>
            <xsl:apply-templates select="Type"/>
        </sequenceOf>
    </xsl:template>

    <!-- ******************************************************** -->
    <!-- Constraints -->

    <xsl:template match="ConstraintList">
        <constraints>
            <xsl:apply-templates/>
        </constraints>
    </xsl:template>

    <xsl:template match="Constraint/SizeConstraint">
        <constraint type="size">
            <xsl:apply-templates select="ValueConstraintList"/>
        </constraint>
    </xsl:template>

    <xsl:template match="Constraint/ValueConstraint">
        <constraint type="value">
            <xsl:apply-templates select="LowerEndPoint/Value"/>
            <xsl:apply-templates select="ValueRange/UpperEndPoint/Value"/>
        </constraint>
    </xsl:template>

    <!-- ******************************************************** -->
    <!-- identifier and hexadecimal values -->

    <!-- identifier -->
    <xsl:template match="DefinedValue">
        <identifier value="{IDENTIFIER_STRING/text()}"/>
    </xsl:template>

    <!-- decimal number -->
    <xsl:template match="BuiltinValue/NumberValue">
        <number type="decimal" value="{NUMBER_STRING/text()}"/>
    </xsl:template>

    <!-- hexadecimal number -->
    <xsl:template match="BuiltinValue/HexadecimalValue">
        <xsl:variable name="value" select="replace(HEXADECIMAL_STRING/text(), $hexPattern, '$1')"/>
        <number type="hexadecimal" length="{string-length($value) * 4}" value="{$value}"/>
    </xsl:template>

    <!-- string value -->
    <xsl:template match="BuiltinValue/StringValue">
        <xsl:variable name="value" select="replace(QUOTED_STRING/text(), $strPattern, '$1')"/>
        <string length="{string-length($value) * 8}" value="{$value}"/>
    </xsl:template>

</xsl:stylesheet>
