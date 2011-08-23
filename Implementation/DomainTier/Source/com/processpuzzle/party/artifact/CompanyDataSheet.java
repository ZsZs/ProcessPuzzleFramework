package com.processpuzzle.party.artifact;

import java.util.Collection;

import com.processpuzzle.application.security.domain.User;
import com.processpuzzle.artifact_type.domain.ArtifactType;
import com.processpuzzle.party.domain.Address;
import com.processpuzzle.party.domain.Company;
import com.processpuzzle.party.domain.Party;
import com.processpuzzle.persistence.query.domain.DefaultIdentityExpression;

public class CompanyDataSheet extends OrganizationDataSheet<CompanyDataSheet, Company> {

   public void addAddress( Address address ) {
      getCompany().addAddress( address );
   }
   
   public CompanyDataSheet( String organizationName, ArtifactType type, User creator, Company company ) {
      super( organizationName, type, creator, company );
   }

   public String getAdministrator() {
      return super.formaInternationalizedText( party.getAdministrator() );
   }

   public String getAdminPhone() {
      return super.formaInternationalizedText( party.getAdminPhone() );
   }

   public Company getCompany() { 
      return getParty(); 
   }

   public String getCompanyName() {
      return party.getPartyName().getName();
   }
   
   public String getCompanyShortName() {
      return super.formaInternationalizedText( party.getShortName() );
   }

   @Override
   public DefaultIdentityExpression<CompanyDataSheet> getDefaultIdentity() {
      // TODO Auto-generated method stub
      return null;
   }
   
   public Collection<Party<?>> getOrganizationUnits() {
      return party.getPartiesByRelationshipType( "OrganizationHierarchy" );
   }

   public void setShortName( String shortName ) {
      getCompany().setShortName( shortName );
   }
   
   public String getTaxNumber() {
      return super.formaInternationalizedText( party.getTaxNumber() );
   }

   public String getTradeRegisterNumber() {
      return super.formaInternationalizedText( party.getTradeRegisterNumber() );
   }

   protected CompanyDataSheet() {
      super();
   }
}
