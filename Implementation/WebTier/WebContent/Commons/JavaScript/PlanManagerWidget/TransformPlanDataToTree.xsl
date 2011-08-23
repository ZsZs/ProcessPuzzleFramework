<?xml version="1.0" encoding="UTF-8"?> 
<xsl:stylesheet 
	version = "1.0" 
	xmlns:xsl = "http://www.w3.org/1999/XSL/Transform" 
	xmlns:fo = "http://www.w3.org/1999/XSL/Format"
	xmlns:pp="http://www.processpuzzle.com/type_definition/project_planning">

	<xsl:output method="xml" indent="yes" encoding="UTF-8" /> 
	
	<xsl:template match="/actionResponse">
		<TreeView>
			<RootNode visible="false" image="images/lifecycle.gif" selectable="true">
				<xsl:for-each select="pp:plan/pp:subActions/pp:plan">
					<xsl:call-template name="createTreeNode" />
		    	</xsl:for-each>

				<xsl:for-each select="pp:plan/pp:subActions/pp:activity">
					<TreeNode type="page" image="images/activity.gif" selectable="true">
						<xsl:attribute name="nodeId">Action:<xsl:value-of select="@id"/></xsl:attribute>
						<xsl:attribute name="name"><xsl:value-of select="./name"/></xsl:attribute>
						<xsl:attribute name="caption"><xsl:value-of select="./name"/></xsl:attribute>
					</TreeNode>
		    	</xsl:for-each>
			</RootNode>
		</TreeView>
	</xsl:template>
	
	<xsl:template name="createTreeNode">
		<TreeNode state="closed" type="folder" image="images/workflowdetail.gif" selectable="true">
			<xsl:attribute name="nodeId">Plan:<xsl:value-of select="@id"/></xsl:attribute>
			<xsl:attribute name="name"><xsl:value-of select="./name"/></xsl:attribute>
			<xsl:attribute name="caption"><xsl:value-of select="./name"/></xsl:attribute>
			
			<xsl:for-each select="./pp:subActions/pp:plan">
				<xsl:call-template name="createTreeNode" />
		    </xsl:for-each>
			
			<xsl:for-each select="./pp:subActions/pp:activity">
				<TreeNode type="page" image="images/activity.gif" selectable="true">
					<xsl:attribute name="nodeId">Action:<xsl:value-of select="@id"/></xsl:attribute>
					<xsl:attribute name="name"><xsl:value-of select="./name"/></xsl:attribute>
					<xsl:attribute name="caption"><xsl:value-of select="./name"/></xsl:attribute>
				</TreeNode>
		    </xsl:for-each>
	
		</TreeNode>
	</xsl:template>
</xsl:stylesheet>

<!-- Stylus Studio meta-information - (c) 2004-2006. Progress Software Corporation. All rights reserved.
<metaInformation>
<scenarios ><scenario default="no" name="Test Plan data to TreeWidget xml" userelativepaths="yes" externalpreview="no" url="TestPlanHierarchy.xml" htmlbaseurl="" outputurl="" processortype="internal" useresolver="yes" profilemode="0" profiledepth="" profilelength="" urlprofilexml="" commandline="" additionalpath="" additionalclasspath="" postprocessortype="none" postprocesscommandline="" postprocessadditionalpath="" postprocessgeneratedext="" validateoutput="no" validator="internal" customvalidator=""/><scenario default="yes" name="Test with xml retrieved from Pantokrator" userelativepaths="yes" externalpreview="no" url="..\..\..\..\..\..\..\Temp\TestPlanHierarchy.xml" htmlbaseurl="" outputurl="" processortype="internal" useresolver="yes" profilemode="0" profiledepth="" profilelength="" urlprofilexml="" commandline="" additionalpath="" additionalclasspath="" postprocessortype="none" postprocesscommandline="" postprocessadditionalpath="" postprocessgeneratedext="" validateoutput="no" validator="internal" customvalidator=""/></scenarios><MapperMetaTag><MapperInfo srcSchemaPathIsRelative="yes" srcSchemaInterpretAsXML="no" destSchemaPath="" destSchemaRoot="" destSchemaPathIsRelative="yes" destSchemaInterpretAsXML="no" ><SourceSchema srcSchemaPath="PlanDataSheet.xsd" srcSchemaRoot="PlanDataSheet" AssociatedInstance="" loaderFunction="document" loaderFunctionUsesURI="no"/></MapperInfo><MapperBlockPosition><template match="/PackageStructure"><block path="TreeView/RootNode/xsl:for&#x2D;each" x="278" y="6"/><block path="TreeView/RootNode/xsl:for&#x2D;each/xsl:call&#x2D;template" x="228" y="36"/></template><template match="/actionResponse"></template></MapperBlockPosition><TemplateContext></TemplateContext><MapperFilter side="source"></MapperFilter></MapperMetaTag>
</metaInformation>
-->