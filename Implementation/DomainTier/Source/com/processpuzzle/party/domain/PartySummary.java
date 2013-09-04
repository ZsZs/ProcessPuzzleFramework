/*
Name: 
    - PartySummary 

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


import java.util.HashSet;
import java.util.Set;

import com.processpuzzle.commons.persistence.Entity;
import com.processpuzzle.fundamental_types.domain.GenericEntity;
import com.processpuzzle.fundamental_types.uniqueidentifier.domain.InvalidUniqueIdentifier;
import com.processpuzzle.persistence.query.domain.DefaultIdentityExpression;

public class PartySummary extends GenericEntity<PartySummary> implements Entity {
   private Set<PartyIdentifier> identifiers = new HashSet<PartyIdentifier>();
   private String name;
   private String address;
   private String telephoneNumber;
   private String faxNumber;
   private String emailAddress;
   private String webAddress;

   protected PartySummary() {}

   protected PartySummary( Party<?> party ) {
      fillInPartyProperties( party );
   }

   public String getAddress() {
      return address;
   }

   public String getEmailAddress() {
      return emailAddress;
   }

   public Set<PartyIdentifier> getIdentifiers() {
      return identifiers;
   }

   public void addIdentifier( PartyIdentifier identifier ) {
      identifiers.add( identifier );
   }

   public String getName() {
      return name;
   }

   public String getTelephoneNumber() {
      return telephoneNumber;
   }

   public String getFaxNumber() {
      return faxNumber;
   }

   public String getWebAddress() {
      return webAddress;
   }

   public @Override
   <I extends DefaultIdentityExpression<PartySummary>> I getDefaultIdentity() {
      // TODO Auto-generated method stub
      return null;
   }

   public void setAddress( String address ) {
      this.address = address;
   }

   public void setEmailAddress( String emailAddress ) {
      this.emailAddress = emailAddress;
   }

   public void setFaxNumber( String faxNumber ) {
      this.faxNumber = faxNumber;
   }

   public void setName( String name ) {
      this.name = name;
   }

   public void setTelephoneNumber( String telephoneNumber ) {
      this.telephoneNumber = telephoneNumber;
   }

   public void setWebAddress( String webAddress ) {
      this.webAddress = webAddress;
   }

   public @Override
   String toString() {
      String text = name;
      text += "\n" + address;
      text += "\n" + telephoneNumber;
      text += "\n" + emailAddress;
      return text;
   }

   @Override
   protected void defineIdentityExpressions() {
   // TODO Auto-generated method stub

   }

   // Private helper methods
   private void fillInPartyProperties( Party<?> party ) {
      if ( party.getId() != null ) {
         try {
         this.addIdentifier( new PartyIdentifier( party.getId().toString()) );
         } catch( InvalidUniqueIdentifier e) {
            // can not occur
         }
      }
      this.name = party.getPartyName().getName();
      this.address = party.getDefaultGeographicAddress() != null ? party.getDefaultGeographicAddress().toString() : "";
      this.emailAddress = party.getDefaultEmailAddress() != null ? party.getDefaultEmailAddress().getEmailAddress() : "";
      this.faxNumber = party.getFaxNumber() != null ? party.getFaxNumber() : "";
      this.telephoneNumber = party.getDefaultTelecomAddress() != null ? party.getDefaultTelecomAddress().toString() : "";
      // TODO complete
   }
}
