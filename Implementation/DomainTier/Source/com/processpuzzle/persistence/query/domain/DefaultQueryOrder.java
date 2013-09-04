/*
Name: 
    - DefaultQueryOrder

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

package com.processpuzzle.persistence.query.domain;


import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import com.processpuzzle.commons.persistence.query.OrderSpecifier;
import com.processpuzzle.commons.persistence.query.QueryOrder;
import com.processpuzzle.fundamental_types.domain.HashCodeUtil;

public class DefaultQueryOrder implements QueryOrder {
   private Set<OrderSpecifier> specifiers = new HashSet<OrderSpecifier>();
   DefaultQueryOrder() {}
   
   public void addOrderSpecifier( OrderSpecifier specifier ) {
      this.specifiers.add( specifier );
   }
   
//Public accessors
   public boolean equals( Object objectToCheck ) {
      if( !(objectToCheck instanceof DefaultQueryOrder )) return false;
      DefaultQueryOrder anotherQueryOrder = (DefaultQueryOrder) objectToCheck;
      return specifiers.equals( anotherQueryOrder.specifiers );
   }
   
   public DefaultQueryOrder clone() {
      DefaultQueryOrder clone = null;
      try {
         clone = (DefaultQueryOrder) super.clone();
      } catch (CloneNotSupportedException e) {
         new Error("Assertion error!"); //Should not happen.
      }
      clone.specifiers = new HashSet<OrderSpecifier>();
      clone.specifiers.addAll( this.specifiers );
      return clone;
   }
   
   public int hashCode() {
      int result = HashCodeUtil.SEED;
      result = HashCodeUtil.hash( result, specifiers );
      return result;
   }
   
//Collections accessors
   public Iterator<OrderSpecifier> specifiersIterator() { return specifiers.iterator(); }
   
//Properties
}
