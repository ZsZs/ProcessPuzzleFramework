/*
Name: 
    - CompanyDataSheet_PropertyView

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

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

import com.processpuzzle.artifact.domain.PropertyView;
import com.processpuzzle.artifact_type.domain.ArtifactViewType;
import com.processpuzzle.party.domain.OrganizationUnit;
import com.processpuzzle.party.domain.Party;
import com.processpuzzle.util.domain.GeneralService;

public class CompanyDataSheet_PropertyView extends PropertyView<CompanyDataSheet> {

   public CompanyDataSheet_PropertyView( CompanyDataSheet artifact, String viewName, ArtifactViewType viewType ) {
      super( artifact, viewName, viewType );
   }

   public String getAdministrator() {
      return parentArtifact.getAdministrator();
   }

   public String getAdminPhone() {
      return parentArtifact.getAdminPhone();
   }

   public String getBuildingNumber() {
      return parentArtifact.getBuildingNumber();
   }

   public String getCompanyName() {
      return parentArtifact.getCompanyName();
   }

   public String getCompanyShortName() {
      return parentArtifact.getCompanyShortName();
   }

   public String getCountry() {
      return parentArtifact.getCountry();
   }

   public String getData( String method, Map parameters ) {
      if( method.equals( "getOrganizationUnitsByCompanyName" ) ){
   
      }
      String responseXml = "<organization id=\"" + ((CompanyDataSheet) parentArtifact).getParty().getId().toString() + "\">";
      for( Iterator<Party<?>> iter = getOrganizationUnits().iterator(); iter.hasNext(); ){
         OrganizationUnit orgUnit = (OrganizationUnit) iter.next();
         responseXml += "<organizationUnit id=\"" + orgUnit.getId().toString() + "\">"
               + GeneralService.encodeXml( orgUnit.getPartyName().getName() ) + "</organizationUnit>";
      }
      responseXml += "</organization>";
      return responseXml;
   }

   public String getGeographicAddress() {
      return parentArtifact.getGeographicAddress();
   }

   public Collection<Party<?>> getOrganizationUnits() {
      return parentArtifact.getOrganizationUnits();
   }

   public String getSettlement() {
      return parentArtifact.getSettlement();
   }

   public String getStreet() {
      return parentArtifact.getStreet();
   }

   public String getTaxNumber() {
      return parentArtifact.getTaxNumber();
   }

   public String getTelecomAddress() {
      return parentArtifact.getTelecomAddress();
   }

   public String getTradeRegisterNumber() {
      return parentArtifact.getTradeRegisterNumber();
   }

   public String getWebPageAddress() {
      return parentArtifact.getWebPageAddress();
   }

   public String getZipCode() {
      return parentArtifact.getZipCodeAsText();
   }
}
