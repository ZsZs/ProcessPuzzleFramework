/*
Name: 
    - CompanyDataSheet

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
