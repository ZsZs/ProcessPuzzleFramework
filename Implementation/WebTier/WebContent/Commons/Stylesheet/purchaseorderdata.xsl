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
 		
				<fo:block margin-bottom="3mm" font-weight="bold" font-size="12pt" font-family="Tahoma" text-align="center">--- Alapadatok ---</fo:block>
				
				<fo:table margin-top="15mm" margin-bottom="15mm">
				    <fo:table-column column-width="85mm" />
				    <fo:table-column column-width="85mm"/>
					<fo:table-header  >
					   <fo:table-row margin-top="2mm" margin-bottom="2mm">
					        <fo:table-cell font-weight="bold" font-size="12pt" font-family="Tahoma" padding-top="4pt" padding-bottom="4pt">
					            <fo:block>Azonosító: </fo:block>
					        </fo:table-cell>
					        <fo:table-cell font-weight="normal" font-family="Verdana" font-size="10pt" padding-top="4pt" padding-bottom="4pt">
					            <fo:block><xsl:value-of  select="orderId"></xsl:value-of></fo:block>
					        </fo:table-cell>
					    </fo:table-row>
					</fo:table-header>
					<fo:table-body >
				        <fo:table-row margin-top="2mm" margin-bottom="2mm">
				            <fo:table-cell font-weight="bold" font-size="12pt" font-family="Tahoma" padding-top="4pt" padding-bottom="4pt">
				                <fo:block>Vásárló: </fo:block>
				            </fo:table-cell>
				            <fo:table-cell font-weight="normal" font-family="Verdana" font-size="10pt" padding-top="4pt" padding-bottom="4pt">
				                <fo:block><xsl:value-of select="purchaser"></xsl:value-of></fo:block>
				            </fo:table-cell>   
				        </fo:table-row>
				        <fo:table-row margin-top="2mm" margin-bottom="2mm">
				            <fo:table-cell padding-top="4pt" padding-bottom="4pt">
				                <fo:block font-weight="bold" font-size="12pt" font-family="Tahoma">Szállítási cím: </fo:block>
				            </fo:table-cell>
				            <fo:table-cell font-weight="normal" font-family="Verdana" font-size="10pt" padding-top="4pt" padding-bottom="4pt">
				                <fo:block><xsl:value-of select="deliveryInstructions"></xsl:value-of></fo:block>
				            </fo:table-cell>   
				        </fo:table-row>
				         <fo:table-row margin-top="2mm" margin-bottom="2mm">
				            <fo:table-cell padding-top="4pt" padding-bottom="4pt" >
				                <fo:block font-weight="bold" font-size="12pt" font-family="Tahoma">Megrendelés dátuma: </fo:block>
				            </fo:table-cell>
				            <fo:table-cell font-weight="normal" font-family="Verdana" font-size="10pt" padding-top="4pt" padding-bottom="4pt">
				                <fo:block><xsl:value-of select="dateCreated"></xsl:value-of></fo:block>
				            </fo:table-cell>   
				        </fo:table-row>
				        <fo:table-row margin-top="2mm" margin-bottom="2mm">
				            <fo:table-cell padding-top="4pt" padding-bottom="4pt">
				                <fo:block font-weight="bold" font-size="12pt" font-family="Tahoma">Kapcsolattartó személy: </fo:block>
				            </fo:table-cell>
				            <fo:table-cell font-weight="normal" font-family="Verdana" font-size="10pt" padding-top="4pt" padding-bottom="4pt">
				                <fo:block><xsl:value-of select="contactPerson"></xsl:value-of></fo:block>
				            </fo:table-cell>   
				        </fo:table-row>
				        <fo:table-row margin-top="2mm" margin-bottom="2mm">
				            <fo:table-cell padding-top="4pt" padding-bottom="4pt">
				                <fo:block font-weight="bold" font-size="12pt" font-family="Tahoma">Telefon: </fo:block>
				            </fo:table-cell>
				            <fo:table-cell font-weight="normal" font-family="Verdana" font-size="10pt" padding-top="4pt" padding-bottom="4pt">
				                <fo:block><xsl:value-of select="telephone"></xsl:value-of></fo:block>
				            </fo:table-cell>   
				        </fo:table-row>
				        <fo:table-row margin-top="2mm" margin-bottom="2mm">
				            <fo:table-cell padding-top="4pt" padding-bottom="4pt">
				                <fo:block font-weight="bold" font-size="12pt" font-family="Tahoma">Fax: </fo:block>
				            </fo:table-cell>
				            <fo:table-cell font-weight="normal" font-family="Verdana" font-size="10pt" padding-top="4pt" padding-bottom="4pt">
				                <fo:block><xsl:value-of select="fax"></xsl:value-of></fo:block>
				            </fo:table-cell>   
				        </fo:table-row>
				         <fo:table-row margin-top="2mm" margin-bottom="2mm">
				            <fo:table-cell padding-top="4pt" padding-bottom="4pt">
				                <fo:block font-weight="bold" font-size="12pt" font-family="Tahoma">Email: </fo:block>
				            </fo:table-cell>
				            <fo:table-cell font-weight="normal" font-family="Verdana" font-size="10pt" padding-top="4pt" padding-bottom="4pt">
				                <fo:block><xsl:value-of select="email"></xsl:value-of></fo:block>
				            </fo:table-cell>   
				        </fo:table-row>
				         <fo:table-row margin-top="2mm" margin-bottom="2mm">
				            <fo:table-cell padding-top="4pt" padding-bottom="4pt">
				                <fo:block font-weight="bold" font-size="12pt" font-family="Tahoma">Megjegyzés: </fo:block>
				            </fo:table-cell>
				            <fo:table-cell font-weight="normal" font-family="Verdana" font-size="10pt" padding-top="4pt" padding-bottom="4pt">
				                <fo:block><xsl:value-of select="termsAndConditions"></xsl:value-of></fo:block>
				            </fo:table-cell>   
				        </fo:table-row>
				         <fo:table-row margin-top="2mm" margin-bottom="2mm">
				            <fo:table-cell padding-top="4pt" padding-bottom="4pt">
				                <fo:block font-weight="bold" font-size="12pt" font-family="Tahoma">Végösszeg: </fo:block>
				            </fo:table-cell>
				            <fo:table-cell font-weight="normal" font-family="Verdana" font-size="10pt" padding-top="4pt" padding-bottom="4pt">
				                <fo:block><xsl:value-of select="total"></xsl:value-of></fo:block>
				            </fo:table-cell>   
				        </fo:table-row>
					</fo:table-body>
				</fo:table>
	
			<fo:block margin-bottom="3mm" padding-top="12pt" padding-bottom="12pt" font-weight="bold" font-size="12pt" font-family="Tahoma" text-align="center">--- Megrendelés tételei ---</fo:block>
			
			<fo:table margin-bottom="4mm">
				<fo:table-column column-width="30mm"/>
				<fo:table-column column-width="25mm"/>
				<fo:table-column column-width="22mm"/>
				<fo:table-column column-width="22mm"/>
				<fo:table-column column-width="30mm"/>
				<fo:table-column column-width="20mm"/>
				<fo:table-column column-width="20mm"/>
				<fo:table-column column-width="20mm"/>

				<fo:table-header>
					<fo:table-row>
					  	<fo:table-cell padding-bottom="4pt">
					      <fo:block font-weight="bold" font-size="10pt" font-family="tahoma">Tételazonosító</fo:block>
					    </fo:table-cell>
					    <fo:table-cell padding-bottom="4pt">
					      <fo:block font-weight="bold" font-size="10pt" font-family="tahoma">Cikkszám</fo:block>
					    </fo:table-cell>
					    <fo:table-cell padding-bottom="4pt">
					      <fo:block font-weight="bold" font-size="10pt" font-family="tahoma">Megnevezés</fo:block>
					    </fo:table-cell>
					    <fo:table-cell padding-bottom="4pt">
					      <fo:block font-weight="bold" font-size="10pt" font-family="tahoma">Gyártókód</fo:block>
					    </fo:table-cell>
					    <fo:table-cell padding-bottom="4pt">
					      <fo:block font-weight="bold" font-size="10pt" font-family="tahoma">Vámtarifaszám</fo:block>
					    </fo:table-cell>
					    <fo:table-cell padding-bottom="4pt">
					      <fo:block font-weight="bold" font-size="10pt" font-family="tahoma">Mennyiség</fo:block>
					    </fo:table-cell>
					    <fo:table-cell padding-bottom="4pt">
					      <fo:block font-weight="bold" font-size="10pt" font-family="tahoma">Egységár</fo:block>
					    </fo:table-cell>
					    <fo:table-cell padding-bottom="4pt">
					      <fo:block font-weight="bold" font-size="10pt" font-family="tahoma">Ár</fo:block>
					    </fo:table-cell>
					</fo:table-row>
				</fo:table-header>
				<fo:table-body>
 				<xsl:for-each select="orderLine">
					<fo:table-row>
		 				<fo:table-cell padding-bottom="3pt">
					      <fo:block font-family="Verdana" font-size="8pt"><xsl:value-of select="orderLineId"></xsl:value-of></fo:block>
					    </fo:table-cell>
					    <fo:table-cell padding-bottom="3pt">
					      <fo:block font-family="Verdana" font-size="8pt"><xsl:value-of select="itemIdentifier"></xsl:value-of></fo:block>
					    </fo:table-cell>
					    <fo:table-cell padding-bottom="3pt">
					      <fo:block font-family="Verdana" font-size="8pt"><xsl:value-of select="designation"></xsl:value-of></fo:block>
					    </fo:table-cell>
					    <fo:table-cell padding-bottom="3pt">
					      <fo:block font-family="Verdana" font-size="8pt"><xsl:value-of select="manufacturerCode"></xsl:value-of></fo:block>
					    </fo:table-cell>
		 				<fo:table-cell padding-bottom="3pt">
					      <fo:block font-family="Verdana" font-size="8pt"><xsl:value-of select="customsTariffNumber"></xsl:value-of></fo:block>
					    </fo:table-cell>
					    <fo:table-cell padding-bottom="3pt">
					      <fo:block font-family="Verdana" font-size="8pt"><xsl:value-of select="quantity"></xsl:value-of></fo:block>
					    </fo:table-cell>
					    <fo:table-cell padding-bottom="3pt">
					      <fo:block font-family="Verdana" font-size="8pt"><xsl:value-of select="unitPrice"></xsl:value-of></fo:block>
					    </fo:table-cell>
					    <fo:table-cell padding-bottom="3pt">
					      <fo:block font-family="Verdana" font-size="8pt"><xsl:value-of select="price"></xsl:value-of></fo:block>
					    </fo:table-cell>   
					</fo:table-row>
	  			</xsl:for-each>
	  			</fo:table-body>
			</fo:table>
				
 			<fo:block padding-top="12pt" font-family="Courier" font-size="9pt">PDF was generated with ProcessPuzzle®</fo:block>
 			<fo:block font-family="Courier" font-size="9pt">Copyright © 2006-2008 IT Codex Ltd.</fo:block>
 			<fo:block font-family="Courier" font-size="9pt">All Rights Reserved</fo:block>
 		</fo:flow>
	</fo:page-sequence>
	
</xsl:template>

</xsl:stylesheet>
