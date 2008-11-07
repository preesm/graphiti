<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="2.0">

    <xsl:output indent="yes" method="xml"/>

    <xsl:template match="text()"/>

    <xsl:template match="TypeAssignment">
        <production name="{IDENTIFIER_STRING[1]/text()}">
            <xsl:apply-templates select="Type"/>
        </production>
    </xsl:template>

    <!-- BIT STRING -->
    <xsl:template match="BitStringType">
        <integer>
            <xsl:apply-templates select="ValueOrConstraintList/ConstraintList/Constraint"/>
        </integer>
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
        <defined name="{IDENTIFIER_STRING/text()}"/>
    </xsl:template>

    <!-- INTEGER -->
    <xsl:template match="IntegerType">
        <integer>
            <xsl:apply-templates select="ValueOrConstraintList/ConstraintList/Constraint"/>
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
    <xsl:template match="SizeConstraint">
        <size>
            <xsl:apply-templates select="ValueConstraintList/ValueConstraint"/>
        </size>
    </xsl:template>

    <xsl:template match="DefinedValue">
        <identifier value="{IDENTIFIER_STRING/text()}"/>
    </xsl:template>

    <xsl:template match="BuiltinValue/NumberValue">
        <number value="{NUMBER_STRING/text()}"/>
    </xsl:template>
    
    <xsl:variable name="pattern">'([0-9a-fA-F]+)'H</xsl:variable>

    <xsl:template match="BuiltinValue/HexadecimalValue">
        <hexadecimal value="{replace(HEXADECIMAL_STRING/text(), $pattern, '$1')}"/>
    </xsl:template>

</xsl:stylesheet>
