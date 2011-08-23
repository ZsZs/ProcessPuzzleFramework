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
