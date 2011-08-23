<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
 xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
 xmlns:fo="http://www.w3.org/1999/XSL/Format">

<xsl:template match="/">
  <fo:root>
  	<fo:layout-master-set>
  		<fo:simple-page-master master-name="theMaster">
  			<fo:region-body/>
  		</fo:simple-page-master>
  	</fo:layout-master-set>
  	<xsl:apply-templates/>
  </fo:root>
 </xsl:template>
 
 <xsl:template match="propertyviews">
 	<fo:page-sequence master-reference="theMaster">
 		<fo:flow flow-name="xsl-region-body">
 			<fo:block>
 			<fo:table>
				<fo:table-column column-width="50mm"/>
				<fo:table-column column-width="35mm"/>
				<fo:table-column column-width="35mm"/>
				<fo:table-column column-width="35mm"/>
				<fo:table-column column-width="35mm"/>
				<fo:table-column column-width="35mm"/>
				<fo:table-column column-width="35mm"/>
				<fo:table-column column-width="35mm"/>
				<fo:table-column column-width="35mm"/>
				<fo:table-header>
				  <fo:table-row>
				  	<fo:table-cell>
				      <fo:block font-weight="bold">Cím</fo:block>
				    </fo:table-cell>
				  	<fo:table-cell>
				      <fo:block font-weight="bold">Helyrajzi szám</fo:block>
				    </fo:table-cell>
				    <fo:table-cell>
				      <fo:block font-weight="bold">Elhelyezkedés</fo:block>
				    </fo:table-cell>
				    <fo:table-cell>
				      <fo:block font-weight="bold">Műszaki állapot</fo:block>
				    </fo:table-cell>
				    <fo:table-cell>
				      <fo:block font-weight="bold">Adás-vétel időpontja</fo:block>
				    </fo:table-cell>
				    <fo:table-cell>
				      <fo:block font-weight="bold">Építés éve</fo:block>
				    </fo:table-cell>
				    <fo:table-cell>
				      <fo:block font-weight="bold">Alapterület</fo:block>
				    </fo:table-cell>
				    <fo:table-cell>
				      <fo:block font-weight="bold">Redukált terület</fo:block>
				    </fo:table-cell>
				    <fo:table-cell>
				      <fo:block font-weight="bold">Érték</fo:block>
				    </fo:table-cell>
				    <fo:table-cell>
				      <fo:block font-weight="bold">Típus</fo:block>
				    </fo:table-cell>
				  </fo:table-row>
				</fo:table-header>
				<fo:table-body>
 				<xsl:for-each select="propertyview">
	 				<fo:table-row>
	 				<fo:table-cell>
				      <fo:block><xsl:value-of select="address"></xsl:value-of></fo:block>
				    </fo:table-cell>
	 				<fo:table-cell>
				      <fo:block><xsl:value-of select="topographicalNumber"></xsl:value-of></fo:block>
				    </fo:table-cell>
				    <fo:table-cell>
				      <fo:block><xsl:value-of select="locationType"></xsl:value-of></fo:block>
				    </fo:table-cell>
				    <fo:table-cell>
				      <fo:block><xsl:value-of select="technicalState"></xsl:value-of></fo:block>
				    </fo:table-cell>
				    <fo:table-cell>
				      <fo:block><xsl:value-of select="purchaseDate"></xsl:value-of></fo:block>
				    </fo:table-cell>
				    <fo:table-cell>
				      <fo:block><xsl:value-of select="buildDate"></xsl:value-of></fo:block>
				    </fo:table-cell>
				    <fo:table-cell>
				      <fo:block><xsl:value-of select="floorSpace"></xsl:value-of></fo:block>
				    </fo:table-cell>
				    <fo:table-cell>
				      <fo:block><xsl:value-of select="reducedFloorSpace"></xsl:value-of></fo:block>
				    </fo:table-cell>
				    <fo:table-cell>
				      <fo:block><xsl:value-of select="value"></xsl:value-of></fo:block>
				    </fo:table-cell>
				    <fo:table-cell>
				      <fo:block><xsl:value-of select="type"></xsl:value-of></fo:block>
				    </fo:table-cell>
				  	</fo:table-row>
	  			</xsl:for-each>
	  			</fo:table-body>
	  			</fo:table>
 			</fo:block>
 		</fo:flow>
 	</fo:page-sequence>
 </xsl:template>
  
</xsl:stylesheet>