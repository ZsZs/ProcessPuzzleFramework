<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
 xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
 xmlns:fo="http://www.w3.org/1999/XSL/Format">
    
<xsl:template match="/">
	<fo:root>
		<fo:layout-master-set>
	  		<fo:simple-page-master master-name="theMaster" page-width="297mm" page-height="210mm" margin-top="7mm" margin-bottom="5mm">
	  			<fo:region-body/>
	  		</fo:simple-page-master>
		</fo:layout-master-set>
		<xsl:apply-templates/>
	</fo:root>
</xsl:template>
    
 <xsl:template match="propertyviews">
 	<fo:page-sequence master-reference="theMaster">
 		<fo:flow flow-name="xsl-region-body">
 			<fo:block border-style="solid" border-width="0.5mm">
 			<fo:table>
				<fo:table-column column-width="146mm"/>
				<fo:table-column column-width="146mm"/>

				<fo:table-header>
				  <fo:table-row>
				  	<fo:table-cell>
				      <fo:block font-weight="bold" font-size="12pt" font-family="tahoma">Katalógus név</fo:block>
				    </fo:table-cell>
				    <fo:table-cell>
				      <fo:block font-weight="bold" font-size="12pt" font-family="tahoma">Katalógus státusz</fo:block>
				    </fo:table-cell>
				  </fo:table-row>
				</fo:table-header>
				<fo:table-body>
 				<xsl:for-each select="propertyview">
	 				<fo:table-row>
	 				<fo:table-cell>
				      <fo:block font-family="Verdana" font-size="10pt"><xsl:value-of select="catalogName"></xsl:value-of></fo:block>
				    </fo:table-cell>
				    <fo:table-cell>
				      <fo:block font-family="Verdana" font-size="10pt"><xsl:value-of select="catalogStatus"></xsl:value-of></fo:block>
				    </fo:table-cell>
				  	</fo:table-row>
	  			</xsl:for-each>
	  			</fo:table-body>
	  			</fo:table>
 			</fo:block>
 			<fo:block font-family="Courier" font-size="9pt">PDF was generated with ProcessPuzzle®</fo:block>
 			<fo:block font-family="Courier" font-size="9pt">Copyright © 2006-2008 IT Codex Ltd.</fo:block>
 			<fo:block font-family="Courier" font-size="9pt">All Rights Reserved</fo:block>
 		</fo:flow>
 	</fo:page-sequence>
   </xsl:template>
    
    
</xsl:stylesheet>
