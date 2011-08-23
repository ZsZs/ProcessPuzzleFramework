/*
 * Created on 2005.07.19.
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.processpuzzle.party.domain;

import com.processpuzzle.fundamental_types.domain.HashCodeUtil;

public class OrganizationName extends PartyName {

   public OrganizationName( String name ) {
      super( name );
   }

   public OrganizationName() {}
   
   public String getName() {
      return super.getName();
   }
   
   public void rename( String name ) {
      super.rename( name );
   }

   public int hashCode() {
      int result = HashCodeUtil.SEED;
      result = HashCodeUtil.hash( result, name );
      return result;
   }

   public boolean equals( Object objectToCheck ) {
      if( this == objectToCheck ) return true;
      if( !super.equals( objectToCheck ) ) return false;
      if( getClass() != objectToCheck.getClass() ) return false;

      OrganizationName anotherOrganizationName = (OrganizationName) objectToCheck;
      
      if( name == null &&  anotherOrganizationName.name != null ) return false;
      if( !name.equals( anotherOrganizationName.name ) ) return false;
      
      return true;
   }

   public @Override int compareTo( PartyName partyName ) {
      if( !(partyName instanceof OrganizationName) ) return -1;
      
      OrganizationName organizationName = (OrganizationName) partyName;
      return organizationName.getName().compareTo( name );
   }
}
