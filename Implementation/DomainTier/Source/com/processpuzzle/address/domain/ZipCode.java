/*
Name: 
    - ZipCode

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


import com.processpuzzle.commons.persistence.AggregateRoot;
import com.processpuzzle.fundamental_types.domain.GenericEntity;

public class ZipCode extends GenericEntity<ZipCode> implements Comparable<Object>, AggregateRoot {
   public static final int MAX_ZIP_VALUE = 9999;
   private Integer zipCode;
   private Settlement settlement;
   private ZipCodeIdentity defaultIdentity;

   // Constructors
   public ZipCode( Integer zipCode ) {
      if( validate( zipCode ) ){
         this.zipCode = zipCode;
         defaultIdentity = new ZipCodeIdentity();
      }else
         throw new ZipCodeConstraintViolationException( 0, zipCode.toString() );
   }

   protected ZipCode() {}
   
   //Public accessors and mutators
   public String asString() {
      return zipCode.toString();
   }

   public int compareTo( Object o ) {
      if( !(o instanceof ZipCode) )
         return -1;
      ZipCode z = (ZipCode) o;
      int c;
      if( (c = zipCode.compareTo( z.getZipCode() )) != 0 )
         return c;
      return c;
   }
   
   //Properties
   @SuppressWarnings( "unchecked" ) @Override public ZipCodeIdentity getDefaultIdentity() { return null; }
   public Settlement getSettlement() { return settlement; }
   public Integer getZipCode() { return zipCode; }
   public void setSettlement( Settlement settlement ) { this.settlement = settlement; }
   public void setZipCode( Integer zipCode ) {
      if( validate( zipCode ) ){
         this.zipCode = zipCode;
      }else
         throw new ZipCodeConstraintViolationException( 0, zipCode.toString() );
   }

   protected void defineIdentityExpressions() {
      defaultIdentity = new ZipCodeIdentity();
      identities.add( defaultIdentity );
   }


   private boolean validate( Integer zipCode ) {
      if( zipCode.intValue() < 0 )
         return false;
      if( zipCode.intValue() > MAX_ZIP_VALUE )
         return false;
      return true;
   }
}
