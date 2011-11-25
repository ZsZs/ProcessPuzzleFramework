/*
Name: 
    - CompanyDataSheet_PrintView

Description: 
    -  

Requires:
    - 
    
Provides:
    - 

Part of: ProcessPuzzle Framework, Domain and Business Model Ready Architecture. Provides content, workflow and social networking functionality. 
http://www.processpuzzle.com

ProcessPuzzle - Content and Workflow Management Integration Business Platform

Author(s): 
    - Zsolt Zsuffa

Copyright: (C) 2011 This program is free software: you can redistribute it and/or modify it under the terms of the 
GNU General Public License as published by the Free Software Foundation, either version 3 of the License, 
or (at your option) any later version.

This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License for more details.

You should have received a copy of the GNU General Public License along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/

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
