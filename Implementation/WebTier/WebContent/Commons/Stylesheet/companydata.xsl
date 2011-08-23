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
			<fo:block margin-top="5mm" margin-bottom="10mm" text-align="center" font-weight="bold" font-size="14pt" font-family="Verdana">Vállalat részletei</fo:block>
 			<fo:block border-style="solid" border-width="0.5mm">
				<fo:block margin-top="10mm" margin-bottom="10mm">
					<fo:block margin-left="5mm" margin-bottom="3mm" font-weight="bold" font-size="12pt" font-family="Tahoma" text-align="center">--- Alapadatok ---</fo:block>
					<fo:block margin-left="5mm" margin-bottom="3mm" font-weight="bold" font-size="12pt" font-family="Tahoma">Cégnév: <fo:block space-before="-4.5mm" margin-left="100mm" margin-bottom="3mm" font-weight="normal" font-family="Verdana" font-size="10pt"><xsl:value-of  select="companyName"></xsl:value-of></fo:block></fo:block>
					<fo:block margin-left="5mm" margin-bottom="3mm" font-weight="bold" font-size="12pt" font-family="Tahoma">Cégjegyzékszám: <fo:block space-before="-4.5mm" margin-left="100mm" margin-bottom="3mm" font-weight="normal" font-family="Verdana" font-size="10pt"><xsl:value-of select="tradeRegisterNumber"></xsl:value-of></fo:block></fo:block>
					<fo:block margin-left="5mm" margin-bottom="3mm" font-weight="bold" font-size="12pt" font-family="Tahoma">Adószám: <fo:block space-before="-4.5mm" margin-left="100mm" margin-bottom="3mm" font-weight="normal" font-family="Verdana" font-size="10pt"><xsl:value-of select="taxNumber"></xsl:value-of></fo:block></fo:block>
					<fo:block margin-left="5mm" margin-bottom="3mm" font-weight="bold" font-size="12pt" font-family="Tahoma">Adminisztrátor neve: <fo:block space-before="-4.5mm" margin-left="100mm" margin-bottom="3mm" font-weight="normal" font-family="Verdana" font-size="10pt"><xsl:value-of select="administrator"></xsl:value-of></fo:block></fo:block>
					<fo:block margin-left="5mm" margin-bottom="3mm" font-weight="bold" font-size="12pt" font-family="Tahoma">Adminisztrátor telefonszám: <fo:block space-before="-4.5mm" margin-left="100mm" margin-bottom="3mm" font-weight="normal" font-family="Verdana" font-size="10pt"><xsl:value-of select="adminPhone"></xsl:value-of></fo:block></fo:block>
					<fo:block margin-left="5mm" margin-bottom="3mm" font-weight="bold" font-size="12pt" font-family="Tahoma" text-align="center">--- Elérhetőség ---</fo:block>
					<fo:block margin-left="5mm" margin-bottom="3mm" font-weight="bold" font-size="12pt" font-family="Tahoma">Helységnév: <fo:block space-before="-4.5mm" margin-left="100mm" margin-bottom="3mm" font-weight="normal" font-family="Verdana" font-size="10pt"><xsl:value-of select="settlement"></xsl:value-of></fo:block></fo:block>
					<fo:block margin-left="5mm" margin-bottom="3mm" font-weight="bold" font-size="12pt" font-family="Tahoma">Irányítószám: <fo:block space-before="-4.5mm" margin-left="100mm" margin-bottom="3mm" font-weight="normal" font-family="Verdana" font-size="10pt"><xsl:value-of select="zipCode"></xsl:value-of></fo:block></fo:block>
					<fo:block margin-left="5mm" margin-bottom="3mm" font-weight="bold" font-size="12pt" font-family="Tahoma">Utca: <fo:block space-before="-4.5mm" margin-left="100mm" margin-bottom="3mm" font-weight="normal" font-family="Verdana" font-size="10pt"><xsl:value-of select="street"></xsl:value-of></fo:block></fo:block>
					<fo:block margin-left="5mm" margin-bottom="3mm" font-weight="bold" font-size="12pt" font-family="Tahoma">Házszám: <fo:block space-before="-4.5mm" margin-left="100mm" margin-bottom="3mm" font-weight="normal" font-family="Verdana" font-size="10pt"><xsl:value-of select="buildingNumber"></xsl:value-of></fo:block></fo:block>
					<fo:block margin-left="5mm" margin-bottom="3mm" font-weight="bold" font-size="12pt" font-family="Tahoma">Telefonszám: <fo:block space-before="-4.5mm" margin-left="100mm" margin-bottom="3mm" font-weight="normal" font-family="Verdana" font-size="10pt"><xsl:value-of select="telecomAddress"></xsl:value-of></fo:block></fo:block>
					<fo:block margin-left="5mm" margin-bottom="3mm" font-weight="bold" font-size="12pt" font-family="Tahoma">Web: <fo:block space-before="-4.5mm" margin-left="100mm" margin-bottom="3mm" font-weight="normal" font-family="Verdana" font-size="10pt"><xsl:value-of select="webPageAddress"></xsl:value-of></fo:block></fo:block>
				</fo:block>
 			</fo:block>
 			<fo:block font-family="Courier" font-size="9pt">PDF was generated with ProcessPuzzle®</fo:block>
 			<fo:block font-family="Courier" font-size="9pt">Copyright © 2006-2008 IT Codex Ltd.</fo:block>
 			<fo:block font-family="Courier" font-size="9pt">All Rights Reserved</fo:block>
 		</fo:flow>
	</fo:page-sequence>
</xsl:template>

</xsl:stylesheet>
