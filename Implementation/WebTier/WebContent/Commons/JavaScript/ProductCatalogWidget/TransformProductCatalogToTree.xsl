<?xml version="1.0" encoding="utf-8"?> 
<xsl:stylesheet 
	version = "1.0" 
	xmlns:xsl = "http://www.w3.org/1999/XSL/Transform" 
	xmlns:fo = "http://www.w3.org/1999/XSL/Format">

	<xsl:output method="xml" indent="yes" encoding="UTF-8" /> 
	
	<xsl:template match="/actionResponse">
		<TreeView>
			<RootNode visible="false" image="images/catalog.gif" selectable="true">
				<xsl:for-each select="catalog/rootCategory">
					<xsl:call-template name="createTreeNode" />
		    	</xsl:for-each>
			</RootNode>
		</TreeView>
	</xsl:template>

	<xsl:template name="createTreeNode">
		<TreeNode state="closed" type="folder" image="images/compositeCategory.gif" selectable="true">
			<xsl:attribute name="nodeId"><xsl:value-of select="@id"/></xsl:attribute>
			<xsl:attribute name="name"><xsl:value-of select="./name"/></xsl:attribute>
			<xsl:attribute name="caption"><xsl:value-of select="./name"/></xsl:attribute>
			<xsl:attribute name="entryType">CompositeCategory</xsl:attribute>
			
			<xsl:for-each select="compositeCategory">
				<xsl:call-template name="createTreeNode" />
		    </xsl:for-each>
			
			<xsl:for-each select="productGroup">
				<TreeNode type="page" image="images/productgroup.gif" selectable="true">
					<xsl:attribute name="nodeId"><xsl:value-of select="@id"/></xsl:attribute>
					<xsl:attribute name="name"><xsl:value-of select="./name"/></xsl:attribute>
					<xsl:attribute name="caption"><xsl:value-of select="./name"/></xsl:attribute>
					<xsl:attribute name="entryType">ProductGroup</xsl:attribute>
				</TreeNode>
		    </xsl:for-each>
	
		</TreeNode>
	</xsl:template>
</xsl:stylesheet><!-- Stylus Studio meta-information - (c) 2004-2008. Progress Software Corporation. All rights reserved.

<metaInformation>
	<scenarios>
		<scenario default="yes" name="Catalog" userelativepaths="yes" externalpreview="no" url="..\..\..\..\..\..\..\Temp\Energometall\TestCatalog.xml" htmlbaseurl="" outputurl="" processortype="internal" useresolver="yes" profilemode="0" profiledepth=""
		          profilelength="" urlprofilexml="" commandline="" additionalpath="" additionalclasspath="" postprocessortype="none" postprocesscommandline="" postprocessadditionalpath="" postprocessgeneratedext="" validateoutput="no" validator="internal"
		          customvalidator="">
			<advancedProp name="sInitialMode" value=""/>
			<advancedProp name="bXsltOneIsOkay" value="true"/>
			<advancedProp name="bSchemaAware" value="true"/>
			<advancedProp name="bXml11" value="false"/>
			<advancedProp name="iValidation" value="0"/>
			<advancedProp name="bExtensions" value="true"/>
			<advancedProp name="iWhitespace" value="0"/>
			<advancedProp name="sInitialTemplate" value=""/>
			<advancedProp name="bTinyTree" value="true"/>
			<advancedProp name="bWarnings" value="true"/>
			<advancedProp name="bUseDTD" value="false"/>
			<advancedProp name="iErrorHandling" value="fatal"/>
		</scenario>
		<scenario default="no" name="Scenario1" userelativepaths="yes" externalpreview="no" url="" htmlbaseurl="" outputurl="" processortype="internal" useresolver="yes" profilemode="0" profiledepth="" profilelength="" urlprofilexml="" commandline=""
		          additionalpath="" additionalclasspath="" postprocessortype="none" postprocesscommandline="" postprocessadditionalpath="" postprocessgeneratedext="" validateoutput="no" validator="internal" customvalidator="">
			<advancedProp name="sInitialMode" value=""/>
			<advancedProp name="bXsltOneIsOkay" value="true"/>
			<advancedProp name="bSchemaAware" value="true"/>
			<advancedProp name="bXml11" value="false"/>
			<advancedProp name="iValidation" value="0"/>
			<advancedProp name="bExtensions" value="true"/>
			<advancedProp name="iWhitespace" value="0"/>
			<advancedProp name="sInitialTemplate" value=""/>
			<advancedProp name="bTinyTree" value="true"/>
			<advancedProp name="bWarnings" value="true"/>
			<advancedProp name="bUseDTD" value="false"/>
			<advancedProp name="iErrorHandling" value="fatal"/>
		</scenario>
	</scenarios>
	<MapperMetaTag>
		<MapperInfo srcSchemaPathIsRelative="yes" srcSchemaInterpretAsXML="no" destSchemaPath="" destSchemaRoot="" destSchemaPathIsRelative="yes" destSchemaInterpretAsXML="no">
			<SourceSchema srcSchemaPath="TestCatalog.xml" srcSchemaRoot="TreeView" AssociatedInstance="" loaderFunction="document" loaderFunctionUsesURI="no"/>
		</MapperInfo>
		<MapperBlockPosition>
			<template match="/">
				<block path="TreeView/RootNode/xsl:for-each" x="318" y="72"/>
				<block path="TreeView/RootNode/xsl:for-each/TreeNode/xsl:for-each" x="318" y="126"/>
				<block path="TreeView/RootNode/xsl:for-each/TreeNode/xsl:for-each/TreeNode/xsl:for-each" x="318" y="180"/>
			</template>
		</MapperBlockPosition>
		<TemplateContext></TemplateContext>
		<MapperFilter side="target"></MapperFilter>
	</MapperMetaTag>
</metaInformation>
-->