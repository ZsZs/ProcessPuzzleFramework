/*
Name: 
    - User

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
 * Created on May 25, 2006
 */
package com.processpuzzle.application.security.domain;


import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import com.processpuzzle.commons.persistence.AggregateRoot;
import com.processpuzzle.fundamental_types.domain.GenericEntity;
import com.processpuzzle.internalization.domain.ProcessPuzzleLocale;
import com.processpuzzle.persistence.query.domain.DefaultQueryContext;

/**
 * @author zsolt.zsuffa
 */
public class User extends GenericEntity<User> implements AggregateRoot {
   private String userName;
   private String password;
   private ProcessPuzzleLocale preferredLocale;
   private String location;
   private String language;
   private String country;
   private Set<AccessRight> accessRights = new HashSet<AccessRight>();

   // Constructors
   public User( String userName, String password ) {
      this( userName, password, null );
   }

   User( String userName, String password, ProcessPuzzleLocale locale ) {
      this.userName = userName;
      this.password = password;
      this.preferredLocale = locale;
   }

   protected User() {} // Used by PersistenceProvider

   // Public accessors
   @Override
   @SuppressWarnings( "unchecked" )
   public UserIdentity getDefaultIdentity() {
      DefaultQueryContext context = new DefaultQueryContext();
      context.addTextValueFor( "nameValue", userName );
      context.addTextValueFor( "passwordValue", password );
      UserIdentity identity = new UserIdentity( context );
      return identity;
   }

   public AccessRight getRightFor( AccessControlledObject controlledObject ) {
      for( Iterator<AccessRight> iter = accessRights.iterator(); iter.hasNext(); ){
         AccessRight right = (AccessRight) iter.next();
         if( right.getControlledObjectId().equals( controlledObject.getId() ) )
            return right;
      }
      return null;
   }

   public AccessRight getRightByName( Integer id ) {
      for( Iterator<AccessRight> i = accessRights.iterator(); i.hasNext(); ){
         AccessRight right = (AccessRight) i.next();
         if( right.getControlledObjectId().equals( id ) )
            return right;
      }
      return null;
   }

   // Public collection accessors
   public Iterator<AccessRight> getAccessRightsIterator() {
      return accessRights.iterator();
   }

   // Public mutators
   public AccessRight addRightFor( AccessControlledObject object ) {
      if( getRightFor( object ) == null ){
         AccessRight right = new AccessRight( object );
         accessRights.add( right );
         return right;
      }else
         throw new AccessRightConstraintViolationException( 1, userName, object.getId() );
   }

   public AccessRight addRightFor( AccessControlledObject object, boolean canRead, boolean canCreate, boolean canWrite, boolean canDelete ) {
      if( getRightFor( object ) == null ){
         AccessRight right = new AccessRight( object, canRead, canCreate, canWrite, canDelete );
         accessRights.add( right );
         return right;
      }else
         throw new AccessRightConstraintViolationException( 1, userName, object.getId() );
   }

   public void addAccessRight( AccessRight right ) {
      if( getRightByName( right.getControlledObjectId() ) == null ){
         accessRights.add( right );
      }
   }

   public void changeUserName( String userName ) {
      this.userName = userName;
   }

   public void changePassword( String newPassword ) {
      this.password = newPassword;
   }

   // Properties
   public Integer getId() {
      return id;
   }

   public String getPassword() {
      return password;
   }

   public String getUserName() {
      return userName;
   }

   public ProcessPuzzleLocale getPrefferedLocale() {
      return preferredLocale;
   }

   public void setPrefferedLocale( ProcessPuzzleLocale prefferedLocale ) {
      this.preferredLocale = prefferedLocale;
   }

   public String getLocation() {
      return location;
   }

   public void setLocation( String location ) {
      this.location = location;
   }

   public String getLanguage() {
      return language;
   }

   public void setLanguage( String language ) {
      this.language = language;
   }

   public String getCountry() {
      return country;
   }

   public void setCountry( String country ) {
      this.country = country;
   }

   // Protected, private helper methods
   protected void defineIdentityExpressions() {
      defaultIdentity = new UserIdentity();
      identities.add( defaultIdentity );
   }

   // Obsolate methods
   // public Set getAccessRights() { return accessRights; }

   // public AccessRight getRightById(Integer id){
   // for (Iterator i=accessRights.iterator();i.hasNext();){
   // AccessRight right=(AccessRight)i.next();
   // if (right.getId()==id) return right;
   // }
   // return null;
   // }

}
