/*
Name: 
    - Country

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
import java.util.Iterator;
import java.util.Set;

import com.google.common.collect.ImmutableSortedSet;
import com.processpuzzle.commons.persistence.AggregateRoot;
import com.processpuzzle.fundamental_types.domain.GenericEntity;
import com.processpuzzle.persistence.query.domain.DefaultIdentityExpression;
import com.processpuzzle.persistence.query.domain.DefaultQueryContext;

public class Country extends GenericEntity<Country> implements AggregateRoot {
   private String name;
   private Set<Settlement> settlements = new HashSet<Settlement>();
   public static final String HUNGARY = "Hungary";

   // Constructors
   protected Country() {}

   Country( String name ) {
      super();
      this.name = name;
   }

   // Public accessors and mutators
   public void addSettlement( Settlement settlement ) throws AlreadyExistingSettlementInCountryException {
      if( settlements.contains( settlement )) throw new AlreadyExistingSettlementInCountryException( name, settlement.getName() );
      
      settlements.add( settlement );
      settlement.setCountry( this );
   }

   public boolean validateSettlement( String settlementName ) {
      for( Iterator<Settlement> iter = settlements.iterator(); iter.hasNext(); ) {
         Settlement settlement = (Settlement) iter.next();
         if( settlement.getName().equals( settlementName ) )
            return false;
      }
      return true;
   }

   // Properties
   @SuppressWarnings("unchecked") @Override
   public DefaultIdentityExpression<Country> getDefaultIdentity() { return null; }
   public String getName() { return name; }
   public Set<Settlement> getSettlements() { return ImmutableSortedSet.copyOf( settlements ); }
   public void setName( String name ) { this.name = name; }

   //Protected, private helper methods
   protected void defineIdentityExpressions() {
      DefaultQueryContext context = new DefaultQueryContext();
      defaultIdentity = new CountryIdentity( context );
      identities.add( defaultIdentity );
   }

}
