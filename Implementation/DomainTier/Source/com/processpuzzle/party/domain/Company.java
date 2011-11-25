/*
Name: 
    - Company 

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

package com.processpuzzle.party.domain;

import com.processpuzzle.party.partytype.domain.PartyType;
import com.processpuzzle.persistence.query.domain.DefaultQueryContext;

public class Company extends Organization {
   private String tradeRegisterNumber;
   private String taxNumber;
   private String administrator;
   private String adminPhone;
   private String shortName;

   Company( OrganizationName companyName, PartyType partyType ) {
      super( companyName, partyType );
   }

   protected Company() {}

   public String getShortName() {
      return shortName;
   }

   public void setShortName( String shortName ) {
      this.shortName = shortName;
   }

   public String getTaxNumber() {
      return taxNumber;
   }

   public void setTaxNumber( String taxNumber ) {
      this.taxNumber = taxNumber;
   }

   public String getTradeRegisterNumber() {
      return tradeRegisterNumber;
   }

   public void setTradeRegisterNumber( String tradeRegisterNumber ) {
      this.tradeRegisterNumber = tradeRegisterNumber;
   }

   public String getAdministrator() {
      return administrator;
   }

   public void setAdministrator( String administrator ) {
      this.administrator = administrator;
   }

   public String getAdminPhone() {
      return adminPhone;
   }

   public void setAdminPhone( String adminPhone ) {
      this.adminPhone = adminPhone;
   }

   @Override
   protected void defineIdentityExpressions() {
      DefaultQueryContext context = new DefaultQueryContext();
      context.addTextValueFor( "nameValue", this.getName() );
//      defaultIdentity = new CompanyIdentity( context );
//      identities.add( defaultIdentity );
   }
}