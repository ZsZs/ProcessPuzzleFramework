/*
Name: 
    - DefaultAttributeFilter 

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


import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

import com.processpuzzle.commons.persistence.query.AggregateFunction;
import com.processpuzzle.commons.persistence.query.AttributeFilter;
import com.processpuzzle.commons.persistence.query.AttributeFilterExpression;
import com.processpuzzle.commons.persistence.query.AttributeSelector;
import com.processpuzzle.fundamental_types.domain.HashCodeUtil;

public class DefaultAttributeFilter implements AttributeFilter {
   private Set<AttributeFilterExpression> filterExpressions = new LinkedHashSet<AttributeFilterExpression>();
   
//Constructors
   DefaultAttributeFilter() {}
   
//Public accessors
   @Override
   public DefaultAttributeFilter clone() {
      DefaultAttributeFilter clone;
      try {
         clone = (DefaultAttributeFilter) super.clone();
      } catch (CloneNotSupportedException e) {
         throw new Error("Assertion failure."); //It should not happen.
      }
      clone.filterExpressions = new LinkedHashSet<AttributeFilterExpression>();
      clone.filterExpressions.addAll( this.filterExpressions );
      return clone;
   }
   
   @Override
   public boolean equals( Object object ) {
      if( !(object instanceof DefaultAttributeFilter) ) return false; 
      DefaultAttributeFilter anotherAttributeFilter = (DefaultAttributeFilter) object;
      return filterExpressions.equals( anotherAttributeFilter.filterExpressions );
   }
   
   public int hashCode() {
      int result = HashCodeUtil.SEED;
      result = HashCodeUtil.hash( result, filterExpressions );
      return result;
   }
   
//Public mutators
   public void addAttributeSelector( AttributeSelector selector ) {
      filterExpressions.add( selector );
   }
   
   public void addAggregateFunction( AggregateFunction function ) {
      filterExpressions.add( function );
   }
   
   public Iterator<AttributeFilterExpression> attributesIterator() {return filterExpressions.iterator();}
}
