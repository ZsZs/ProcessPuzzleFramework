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
			<fo:block margin-top="5mm" margin-bottom="10mm" text-align="center" font-weight="bold" font-size="14pt" font-family="Verdana">Megrendelés részletei</fo:block>
 			<fo:block border-style="solid" border-width="0.5mm">
				<fo:block margin-top="10mm" margin-bottom="10mm">
					<fo:block margin-left="5mm" margin-bottom="3mm" font-weight="bold" font-size="12pt" font-family="Tahoma" text-align="center">--- Alapadatok ---</fo:block>
					<fo:block margin-left="5mm" margin-bottom="3mm" font-weight="bold" font-size="12pt" font-family="Tahoma">Azonosító: <fo:block space-before="-4.5mm" margin-left="100mm" margin-bottom="3mm" font-weight="normal" font-family="Verdana" font-size="10pt"><xsl:value-of  select="orderId"></xsl:value-of></fo:block></fo:block>
					<fo:block margin-left="5mm" margin-bottom="3mm" font-weight="bold" font-size="12pt" font-family="Tahoma">Megrendelő: <fo:block space-before="-4.5mm" margin-left="100mm" margin-bottom="3mm" font-weight="normal" font-family="Verdana" font-size="10pt"><xsl:value-of select="purchaser"></xsl:value-of></fo:block></fo:block>
					<fo:block margin-left="5mm" margin-bottom="3mm" font-weight="bold" font-size="12pt" font-family="Tahoma">Szállítási utasítások: <fo:block space-before="-4.5mm" margin-left="100mm" margin-bottom="3mm" font-weight="normal" font-family="Verdana" font-size="10pt"><xsl:value-of select="deliveryInstructions"></xsl:value-of></fo:block></fo:block>
					<fo:block margin-left="5mm" margin-bottom="3mm" font-weight="bold" font-size="12pt" font-family="Tahoma">Megrendelés dátuma: <fo:block space-before="-4.5mm" margin-left="100mm" margin-bottom="3mm" font-weight="normal" font-family="Verdana" font-size="10pt"><xsl:value-of select="dateCreated"></xsl:value-of></fo:block></fo:block>
					<fo:block margin-left="5mm" margin-bottom="3mm" font-weight="bold" font-size="12pt" font-family="Tahoma">Kapcsolattartó személy: <fo:block space-before="-4.5mm" margin-left="100mm" margin-bottom="3mm" font-weight="normal" font-family="Verdana" font-size="10pt"><xsl:value-of select="orderInitiator"></xsl:value-of></fo:block></fo:block>
					<fo:block margin-left="5mm" margin-bottom="3mm" font-weight="bold" font-size="12pt" font-family="Tahoma">Telefon: <fo:block space-before="-4.5mm" margin-left="100mm" margin-bottom="3mm" font-weight="normal" font-family="Verdana" font-size="10pt"><xsl:value-of select="telephone"></xsl:value-of></fo:block></fo:block>
					<fo:block margin-left="5mm" margin-bottom="3mm" font-weight="bold" font-size="12pt" font-family="Tahoma">Fax: <fo:block space-before="-4.5mm" margin-left="100mm" margin-bottom="3mm" font-weight="normal" font-family="Verdana" font-size="10pt"><xsl:value-of select="fax"></xsl:value-of></fo:block></fo:block>
					<fo:block margin-left="5mm" margin-bottom="3mm" font-weight="bold" font-size="12pt" font-family="Tahoma">E-mail: <fo:block space-before="-4.5mm" margin-left="100mm" margin-bottom="3mm" font-weight="normal" font-family="Verdana" font-size="10pt"><xsl:value-of select="email"></xsl:value-of></fo:block></fo:block>
					<fo:block margin-left="5mm" margin-bottom="3mm" font-weight="bold" font-size="12pt" font-family="Tahoma">Szállítási feltételek: <fo:block space-before="-4.5mm" margin-left="100mm" margin-bottom="3mm" font-weight="normal" font-family="Verdana" font-size="10pt"><xsl:value-of select="termsAndConditions"></xsl:value-of></fo:block></fo:block>
					<fo:block margin-left="5mm" margin-bottom="3mm" font-weight="bold" font-size="12pt" font-family="Tahoma">Felkínált vásárlási ár: <fo:block space-before="-4.5mm" margin-left="100mm" margin-bottom="3mm" font-weight="normal" font-family="Verdana" font-size="10pt"><xsl:value-of select="offeredPurchasePrice"></xsl:value-of></fo:block></fo:block>
					<fo:block margin-left="5mm" margin-bottom="3mm" font-weight="bold" font-size="12pt" font-family="Tahoma">Végösszeg: <fo:block space-before="-4.5mm" margin-left="100mm" margin-bottom="3mm" font-weight="normal" font-family="Verdana" font-size="10pt"><xsl:value-of select="total"></xsl:value-of></fo:block></fo:block>
				</fo:block>
 			</fo:block>
 			<!-- -->
		<fo:block border-style="solid" border-width="0.5mm">
 			<fo:table>
				<fo:table-column column-width="30mm"/>
				<fo:table-column column-width="30mm"/>
				<fo:table-column column-width="30mm"/>
				<fo:table-column column-width="30mm"/>
				<fo:table-column column-width="30mm"/>
				<fo:table-column column-width="30mm"/>
				<fo:table-column column-width="30mm"/>

				<fo:table-header>
					<fo:table-row>
					  	<fo:table-cell>
					      <fo:block font-weight="bold" font-size="12pt" font-family="tahoma">Azonosító</fo:block>
					    </fo:table-cell>
					    <fo:table-cell>
					      <fo:block font-weight="bold" font-size="12pt" font-family="tahoma">Megnevezés</fo:block>
					    </fo:table-cell>
					    <fo:table-cell>
					      <fo:block font-weight="bold" font-size="12pt" font-family="tahoma">Gyártókód</fo:block>
					    </fo:table-cell>
					    <fo:table-cell>
					      <fo:block font-weight="bold" font-size="12pt" font-family="tahoma">Vámtarifaszám</fo:block>
					    </fo:table-cell>
					    <fo:table-cell>
					      <fo:block font-weight="bold" font-size="12pt" font-family="tahoma">Mennyiség</fo:block>
					    </fo:table-cell>
					    <fo:table-cell>
					      <fo:block font-weight="bold" font-size="12pt" font-family="tahoma">Egységár</fo:block>
					    </fo:table-cell>
						<fo:table-cell>
							<fo:block font-weight="bold" font-size="12pt" font-family="tahoma">Ár</fo:block>
						</fo:table-cell>
					</fo:table-row>
				</fo:table-header>
			<fo:table-body>
 		<xsl:for-each select="propertyview">
			<fo:table-row>
	 			<fo:table-cell>
					<fo:block font-family="Verdana" font-size="10pt"><xsl:value-of select="*"></xsl:value-of></fo:block>
						</fo:table-cell>
					<fo:table-cell>
						<fo:block font-family="Verdana" font-size="10pt"><xsl:value-of select="*"></xsl:value-of></fo:block>
				    </fo:table-cell>
				    <fo:table-cell>
				    	<fo:block font-family="Verdana" font-size="10pt"><xsl:value-of select="*"></xsl:value-of></fo:block>
				    </fo:table-cell>
				    <fo:table-cell>
				    	<fo:block font-family="Verdana" font-size="10pt"><xsl:value-of select="*"></xsl:value-of></fo:block>
				    </fo:table-cell>
				    <fo:table-cell>
				    	<fo:block font-family="Verdana" font-size="10pt"><xsl:value-of select="*"></xsl:value-of></fo:block>
				    </fo:table-cell>
				    <fo:table-cell>
				    	<fo:block font-family="Verdana" font-size="10pt"><xsl:value-of select="*"></xsl:value-of></fo:block>
				    </fo:table-cell>
				    <fo:table-cell>
				    	<fo:block font-family="Verdana" font-size="10pt"><xsl:value-of select="*"></xsl:value-of></fo:block>
				    </fo:table-cell>
			  	</fo:table-row>
  		</xsl:for-each>
	  		</fo:table-body>
	  	</fo:table>
 	</fo:block>
			<!--  -->
 			<fo:block font-family="Courier" font-size="9pt">PDF was generated with ProcessPuzzle®</fo:block>
 			<fo:block font-family="Courier" font-size="9pt">Copyright © 2006-2007 IT Codex Ltd.</fo:block>
 			<fo:block font-family="Courier" font-size="9pt">All Rights Reserved</fo:block>
 		</fo:flow>
	</fo:page-sequence>
</xsl:template>

</xsl:stylesheet>
