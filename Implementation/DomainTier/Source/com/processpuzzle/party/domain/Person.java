/*
Name: 
    - Person 

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

/*
 * Created on 2005.07.05.
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.processpuzzle.party.domain;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import com.processpuzzle.application.security.domain.User;
import com.processpuzzle.application.security.domain.UserFactory;
import com.processpuzzle.fundamental_types.domain.TimePoint;
import com.processpuzzle.party.partytype.domain.PartyType;
import com.processpuzzle.party.partytype.domain.Role;
import com.processpuzzle.persistence.query.domain.DefaultIdentityExpression;
import com.processpuzzle.persistence.query.domain.DefaultQueryContext;
import com.processpuzzle.user_session.domain.UserRequestManager;
import com.processpuzzle.util.domain.GeneralService;

public class Person extends Party<Person> implements Comparable<Person> {
   private Person manager;
   private String initials;
   private Integer status;
   private String assignment;
   private String gender;
   private TimePoint birthDate;
   private User systemUser;
   private Set<PersonName> otherNames = new HashSet<PersonName>();
   private Set<PersonGroup> personGroups = new HashSet<PersonGroup>();
   private Set<PersonRole> personRoles = new HashSet<PersonRole>();
   private UserFactory userFactory;

   Person( PersonName personName, PartyType personType, TimePoint birthDate ) {
      this( personName, personType, null, null, birthDate );
   }

   public Person( PersonName personName, PartyType personType, String userName, String password ) {
      this( personName, personType, userName, password, null );
   }

   public Person( PersonName personName, PartyType personType, String userName, String password, TimePoint birthDate ) {
      super( personName, personType );
      setBirthDate( birthDate );

      if ( userName != null ) {
         userFactory = UserRequestManager.getInstance().getApplicationContext().getEntityFactory( UserFactory.class );
         if( this.systemUser != null ){
            this.systemUser.changeUserName( userName );
         }else{
            this.systemUser = userFactory.createUser( userName, password );
         }
      }

//      if( personName != null && userName != null )
//         this.name = personName.getGivenName() + "_" + userName + "_" + personName.getFamilyName();
   }

   protected Person() {}

   public PersonName getPersonName() {
      return (PersonName) getPartyName();
   }

   public Set<PersonRole> getPersonRoles() {
      return personRoles;
   }

   public void setPersonRoles( Set<PersonRole> personRoles ) {
      this.personRoles = personRoles;
   }

   public String getAssignment() {
      return assignment;
   }

   public void addOtherName( PersonName aName ) {
      otherNames.add( aName );
   }

   public void setAssignment( String assignment ) {
      this.assignment = assignment;
   }

   public Set<PersonGroup> getPersonGroups() {
      return personGroups;
   }

   public void addPersonGroup( PersonGroup personGroup ) {
      this.personGroups.add( personGroup );
   }

   public void deletePersonGroup( PersonGroup personGroup ) {
      if( personGroup != null ){
         this.personGroups.remove( personGroup );
      }
   }

   public void setPersonGroups( Set<PersonGroup> personGroups ) {
      this.personGroups = personGroups;
   }

   public Person getManager() {
      return manager;
   }

   public void setManager( Person manager ) {
      this.manager = manager;
   }

   public String getInitials() {
      return initials;
   }

   public void setInitials( String initials ) {
      this.initials = initials;
   }

   public Integer getStatus() {
      return status;
   }

   public void setStatus( Integer status ) {
      this.status = status;
   }

   public Set getRoles() {
      Set<Role> roles = new HashSet<Role>();
      if( personRoles == null ){
         roles = null;
      }else{
         Iterator<PersonRole> it = personRoles.iterator();
         while( it.hasNext() ){
            roles.add( it.next().getRole() );
         }
      }
      return roles;
   }

   public void addPersonRole( PersonRole personRole ) {
      personRoles.add( personRole );
   }

   public Integer removeRole( Role role ) {
      Iterator<PersonRole> it = personRoles.iterator();
      boolean l = false;
      PersonRole personRole = null;

      while( (it.hasNext()) && (!(l)) ){
         personRole = (PersonRole) it.next();
         if( (personRole.getRole() != null) && (personRole.getRole().getId() != null) ){
            if( personRole.getRole().getId().equals( role.getId() ) ){
               l = true;
            }
         }else{
            if( personRole.getRole().getName().equals( role.getName() ) ){
               l = true;
            }
         }
      }

      PersonRole personRole1 = null;
      if( l ){
         it = role.getPersonRoles().iterator();
         l = false;
         personRole1 = null;

         while( (it.hasNext()) && (!(l)) ){
            personRole1 = (PersonRole) it.next();
            if( (personRole1.getId() != null) && (personRole1.getId().equals( personRole.getId() )) ){
               l = true;
            }

         }
      }
      if( l ){
         this.getPersonRoles().remove( personRole );
         role.getPersonRoles().remove( personRole1 );
      }

      return personRole.getId();
   }

   public Integer removeGroup( Group group ) {
      Integer personGroupId = null;

      PersonGroup personGroup = (PersonGroup) GeneralService.findCollectionItemByFieldName( personGroups, "grp.id", group.getId() );
      personGroupId = personGroup.getId();
      personGroups.remove( personGroup );
      group.getPersonGroups().remove(
            (PersonGroup) GeneralService.findCollectionItemByFieldName( group.getPersonGroups(), "person.id", this.getId() ) );

      return personGroupId;
   }

   public User getSystemUser() {
      return systemUser;
   }

   public void setSystemUser( User user ) {
      this.systemUser = user;
   }

   public String getGender() {
      return gender;
   }

   public void setGender( String gender ) {
      this.gender = gender;
   }

   public TimePoint getBirthDate() {
      return birthDate;
   }

   public void setBirthDate( TimePoint birthDate ) {
      this.birthDate = birthDate;
   }

   protected void defineIdentityExpressions() {
      DefaultQueryContext context = new DefaultQueryContext();
      context.addTextValueFor( "userNameValue", systemUser.getUserName() );
      defaultIdentity = new PersonUsernameIdentity( context );
      identities.add( defaultIdentity );
      
      context.addTextValueFor( "passwordValue", systemUser.getPassword() );
      identities.add( new PersonUsernameAndPasswordIdentity( context ) );
      
      context = new DefaultQueryContext();
      context.addIntegerValueFor( "systemUserIdValue", systemUser.getId() );
      identities.add( new PersonUserIdIdentity( context ) );
   }

   public int compareTo( Person person ) {
      return 0;
   }

    public <I extends DefaultIdentityExpression<Person>> I getDefaultIdentity() {
      // TODO Auto-generated method stub
      return null;
   }

}
