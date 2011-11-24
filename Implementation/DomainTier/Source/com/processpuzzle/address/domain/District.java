/*
Name: 
    - District

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

package com.processpuzzle.address.domain;

import java.util.HashSet;
import java.util.Set;

import com.processpuzzle.fundamental_types.domain.GenericEntity;
import com.processpuzzle.persistence.query.domain.DefaultIdentityExpression;

public class District extends GenericEntity<District> implements Comparable<District> {
   private String name;
   private Set<ZipCode> zipCodes = new HashSet<ZipCode>();

   protected District() {}

   District( String name ) {
      this.name = name;
   }

   public void addZipCode( ZipCode zipCode ) {
      this.zipCodes.add( zipCode );
   }

   @SuppressWarnings("unchecked")
   @Override
   public DefaultIdentityExpression<District> getDefaultIdentity() {
      return null;
   }

   public int compareTo( District objectToCompare ) {
      if( !(objectToCompare instanceof District) ) return -1;
      
      District anotherDistrict = (District) objectToCompare;
      int nameComparisonResult;
      if(( nameComparisonResult = this.name.compareTo( anotherDistrict.getName() )) != 0 )
         return nameComparisonResult;
      return nameComparisonResult;
   }

   public String getName() {
      return name;
   }

   public void setName( String name ) {
      this.name = name;
   }

   public Set<ZipCode> getZipCodes() {
      return zipCodes;
   }

   @Override
   protected void defineIdentityExpressions() {
   // TODO Auto-generated method stub
   
   }
}
