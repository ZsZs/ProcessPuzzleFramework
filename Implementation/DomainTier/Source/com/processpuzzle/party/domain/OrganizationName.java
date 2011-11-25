/*
Name: 
    - OrganizationName 

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
