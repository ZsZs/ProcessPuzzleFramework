/*
Name: 
    - PersonDataSheet

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

import com.processpuzzle.application.configuration.domain.InternalizationContext;
import com.processpuzzle.application.security.domain.User;
import com.processpuzzle.artifact_type.domain.ArtifactType;
import com.processpuzzle.fundamental_types.textformat.domain.DateFormatSpecifier;
import com.processpuzzle.party.domain.Address;
import com.processpuzzle.party.domain.Person;
import com.processpuzzle.party.domain.PersonName;
import com.processpuzzle.persistence.query.domain.DefaultIdentityExpression;

public class PersonDataSheet extends PartyDataSheet<PersonDataSheet, Person> {

   public PersonDataSheet() {
      super();
   }

   public PersonDataSheet( String artifactName, ArtifactType type, User creator, Person person ) {
      super( artifactName, type, creator, person );
   }

   public void addAddress( Address address ) {
      getPerson().addAddress( address );
   }

   public String getBirthDate() {
      if( party.getBirthDate() != null ){
         DateFormatSpecifier dateFormat = applicationContext.getInternalizationContext().getDefaultLocale().getDateFormat();
         return dateFormat.format( party.getBirthDate().getValue() );
      }
      return notAvailableResponse();
   }

   @Override
   public DefaultIdentityExpression<PersonDataSheet> getDefaultIdentity() {
      // TODO Auto-generated method stub
      return null;
   }

   public String getFamilyName() {
      return ((PersonName) party.getPartyName()).getFamilyName();
   }

   public String getGivenName() {
      return ((PersonName) party.getPartyName()).getGivenName();
   }

   public String getPassword() {
      if( party.getSystemUser() != null )
         return party.getSystemUser().getPassword();
      else
         return null;
   }

   public Person getPerson() {
      return getParty();
   }

   public String getPersonName() {
      if( party.getPartyName() != null )
         return party.getPartyName().getName();
      else
         return notAvailableResponse();
   }

   public String getPreferredLocale() {
      if( party.getSystemUser() != null && party.getSystemUser().getPrefferedLocale() != null )
         return party.getSystemUser().getPrefferedLocale().getLanguage();
      else
         return notAvailableResponse();
   }

   public String getPrefix() {
      return ((PersonName) party.getPartyName()).getPrefix();
   }

   public String getUserName() {
      if( party.getSystemUser() != null )
         return party.getSystemUser().getUserName();
      else
         return null;
   }

   public void setFamilyName( String familyName ) {
      String givenName = party.getPersonName().getGivenName();
      String userName = party.getSystemUser().getUserName();
      this.renameName( givenName + "_" + userName + "_" + familyName );
      party.renameName( givenName + "_" + userName + "_" + familyName );
      party.getPersonName().setFamilyName( familyName );
   }

   public void setGivenName( String gName ) {
      String familyName = party.getPersonName().getFamilyName();
      String userName = party.getSystemUser().getUserName();
      this.renameName( gName + "_" + userName + "_" + familyName );
      party.renameName( gName + "_" + userName + "_" + familyName );
      party.getPersonName().setGivenName( gName );
   }

   public void setPassword( String password ) {
      party.getSystemUser().changePassword( password );
   }

   public void setPrefferredLocale( String locale ) {
      InternalizationContext internalizationContext = applicationContext.getInternalizationContext();
      party.getSystemUser().setPrefferedLocale( internalizationContext.findLocaleByLanguage( locale ) );
   }

   public void setPrefix( String prefix ) {
      ((PersonName) party.getPartyName()).setPrefix( prefix );
   }

   public void setUserName( String userName ) {
      String familyName = party.getPersonName().getFamilyName();
      String givenName = party.getPersonName().getGivenName();
      this.renameName( givenName + "_" + userName + "_" + familyName );
      party.renameName( givenName + "_" + userName + "_" + familyName );
      party.getSystemUser().changeUserName( userName );
   }
}
