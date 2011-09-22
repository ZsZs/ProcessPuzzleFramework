<?xml version="1.0" encoding="iso-8859-1"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

<xsl:template match="/">

 <xsl:for-each select="courses/course">
 
  <h2><xsl:value-of select="./@city" /></h2>
  <h3><xsl:value-of select="./@start_date" /></h3>

   <xsl:variable name="City"><xsl:value-of select="./@city" /></xsl:variable>
   <xsl:variable name="aPrice"><xsl:value-of select="./@price" /></xsl:variable>

   <xsl:variable name="bPrice"><xsl:value-of select="substring($aPrice,2,8)" /></xsl:variable>
   <xsl:variable name="cPrice"><xsl:value-of select="format-number($bPrice, '####.##')" /></xsl:variable>   


<form action="https://checkout.google.com/api/checkout/v2/checkoutForm/Merchant/400268406261072" id="BB_BuyButtonForm" method="post" name="BB_BuyButtonForm">
    <input name="item_name_1" type="hidden" value="{$City}"/>
    <input name="item_description_1" type="hidden" value="Certified ScrumMaster WorkShop"/>
    <input name="item_quantity_1" type="hidden" value="1"/>
    <input name="item_price_1" type="hidden" value="{$cPrice}"/>
    <input name="item_currency_1" type="hidden" value="USD"/>
    <input name="_charset_" type="hidden" value="utf-8"/>
    <input alt="" src="https://checkout.google.com/buttons/buy.gif?merchant_id=400268406261072&amp;w=121&amp;h=44&amp;style=white&amp;variant=text&amp;loc=en_US" type="image"/>
</form>

  	<xsl:for-each select="./trainers">
  		<xsl:value-of select="trainer" /> 
  	</xsl:for-each>	
 </xsl:for-each>

 </xsl:template>

</xsl:stylesheet>


