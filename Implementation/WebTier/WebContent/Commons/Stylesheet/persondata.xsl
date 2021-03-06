<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
 xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
 xmlns:fo="http://www.w3.org/1999/XSL/Format">
 
<xsl:template match="/">
 <fo:root>
 	<fo:layout-master-set>
 		<fo:simple-page-master master-name="theMaster" page-width="210mm" page-height="297mm" margin-top="7mm" margin-bottom="5mm" margin-left="10mm" margin-right="10mm">
 			<fo:region-body/>
 		</fo:simple-page-master>
 	</fo:layout-master-set>
 	<xsl:apply-templates/>
 </fo:root>
</xsl:template>

<xsl:template match="propertyview">
	<fo:page-sequence master-reference="theMaster">
 		<fo:flow flow-name="xsl-region-body">
			<fo:block margin-top="5mm" margin-bottom="10mm" text-align="center" font-weight="bold" font-size="14pt" font-family="Verdana">Személy részletei</fo:block>
 			<fo:block border-style="solid" border-width="0.5mm">
				<fo:block margin-top="10mm" margin-bottom="10mm">
					<fo:block margin-left="5mm" margin-bottom="3mm" font-weight="bold" font-size="12pt" font-family="Tahoma" text-align="center">--- Alapadatok ---</fo:block>
					<fo:block margin-left="5mm" margin-bottom="3mm" font-weight="bold" font-size="12pt" font-family="Tahoma">Előtag: <fo:block space-before="-4.5mm" margin-left="100mm" margin-bottom="3mm" font-weight="normal" font-family="Verdana" font-size="10pt"><xsl:value-of  select="prefix"></xsl:value-of></fo:block></fo:block>
					<fo:block margin-left="5mm" margin-bottom="3mm" font-weight="bold" font-size="12pt" font-family="Tahoma">Vezetéknév: <fo:block space-before="-4.5mm" margin-left="100mm" margin-bottom="3mm" font-weight="normal" font-family="Verdana" font-size="10pt"><xsl:value-of select="familyName"></xsl:value-of></fo:block></fo:block>
					<fo:block margin-left="5mm" margin-bottom="3mm" font-weight="bold" font-size="12pt" font-family="Tahoma">Keresztnév: <fo:block space-before="-4.5mm" margin-left="100mm" margin-bottom="3mm" font-weight="normal" font-family="Verdana" font-size="10pt"><xsl:value-of select="givenName"></xsl:value-of></fo:block></fo:block>
					<fo:block margin-left="5mm" margin-bottom="3mm" font-weight="bold" font-size="12pt" font-family="Tahoma">Születési dátum: <fo:block space-before="-4.5mm" margin-left="100mm" margin-bottom="3mm" font-weight="normal" font-family="Verdana" font-size="10pt"><xsl:value-of  select="birthDate"></xsl:value-of></fo:block></fo:block>
					<fo:block margin-left="5mm" margin-bottom="3mm" font-weight="bold" font-size="12pt" font-family="Tahoma">Cím: <fo:block space-before="-4.5mm" margin-left="100mm" margin-bottom="3mm" font-weight="normal" font-family="Verdana" font-size="10pt"><xsl:value-of select="geographicAddress"></xsl:value-of></fo:block></fo:block>
					<fo:block margin-left="5mm" margin-bottom="3mm" font-weight="bold" font-size="12pt" font-family="Tahoma">Telefon: <fo:block space-before="-4.5mm" margin-left="100mm" margin-bottom="3mm" font-weight="normal" font-family="Verdana" font-size="10pt"><xsl:value-of select="telecomAddress"></xsl:value-of></fo:block></fo:block>
					<fo:block margin-left="5mm" margin-bottom="3mm" font-weight="bold" font-size="12pt" font-family="Tahoma">Email: <fo:block space-before="-4.5mm" margin-left="100mm" margin-bottom="3mm" font-weight="normal" font-family="Verdana" font-size="10pt"><xsl:value-of select="emailAddress"></xsl:value-of></fo:block></fo:block>
				</fo:block>
 			</fo:block>
 			<fo:block font-family="Courier" font-size="9pt">PDF was generated with ProcessPuzzle®</fo:block>
 			<fo:block font-family="Courier" font-size="9pt">Copyright © 2006-2008 IT Codex Ltd.</fo:block>
 			<fo:block font-family="Courier" font-size="9pt">All Rights Reserved</fo:block>
 		</fo:flow>
	</fo:page-sequence>
</xsl:template>

</xsl:stylesheet>
