package com.processpuzzle.party.artifact;

import com.processpuzzle.artifact.domain.PrintView;
import com.processpuzzle.artifact_type.domain.ArtifactViewType;

public class CompanyDataSheet_PrintView extends PrintView<CompanyDataSheet> {

   public CompanyDataSheet_PrintView( CompanyDataSheet artifact, String name, ArtifactViewType type ) {
      super( artifact, name, type );
   }

   @Override
   public String buildXml() {
      StringBuffer outputXml = new StringBuffer();
      CompanyDataSheet_PropertyView propertyView = (CompanyDataSheet_PropertyView) parentArtifact.getPropertyView();
      outputXml.append( "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" );
      outputXml.append( "<!DOCTYPE chars SYSTEM \"" + getCharacterEntitiesDtd() + "\">" );
      outputXml.append( "<propertyview>" );
      outputXml.append( "<companyName>" + propertyView.getCompanyName() + "</companyName>" );
      outputXml.append( "<tradeRegisterNumber>" + propertyView.getTradeRegisterNumber() + "</tradeRegisterNumber>" );
      outputXml.append( "<taxNumber>" + propertyView.getTaxNumber() + "</taxNumber>" );
      outputXml.append( "<administrator>" + propertyView.getAdministrator() + "</administrator>" );
      outputXml.append( "<adminPhone>" + propertyView.getAdminPhone() + "</adminPhone>" );
      outputXml.append( "<settlement>" + propertyView.getSettlement() + "</settlement>" );
      outputXml.append( "<zipCode>" + propertyView.getZipCode() + "</zipCode>" );
      outputXml.append( "<street>" + propertyView.getStreet() + "</street>" );
      outputXml.append( "<buildingNumber>" + propertyView.getBuildingNumber() + "</buildingNumber>" );
      outputXml.append( "<telecomAddress>" + propertyView.getTelecomAddress() + "</telecomAddress>" );
      outputXml.append( "<webPageAddress>" + propertyView.getWebPageAddress() + "</webPageAddress>" );
      outputXml.append( "</propertyview>" );
      return outputXml.toString();
   }

}
