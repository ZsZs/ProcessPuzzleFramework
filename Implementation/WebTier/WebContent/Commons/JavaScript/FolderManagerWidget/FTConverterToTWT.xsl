<?xml version="1.0" encoding="UTF-8" ?> 
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:fo="http://www.w3.org/1999/XSL/Format">
<xsl:output method="xml" indent="yes" encoding="UTF-8" /> 

<xsl:template match="/PackageStructure">
<TreeView>
	<RootNode visible="false">
		<xsl:for-each select="./ArtifactPackage">
			<xsl:call-template name="createTreeNode" />
	    	</xsl:for-each>
	</RootNode>
</TreeView>
</xsl:template>

<xsl:template name="createTreeNode">
	<TreeNode state="closed" type="folder" selectable="true">
	<xsl:attribute name="nodeId"><xsl:value-of select="@name"/></xsl:attribute>
	<xsl:attribute name="caption"><xsl:value-of select="@name"/></xsl:attribute>
	<xsl:for-each select="./ArtifactPackage">
		<xsl:call-template name="createTreeNode" />
    	</xsl:for-each>
	</TreeNode>
</xsl:template>

</xsl:stylesheet>