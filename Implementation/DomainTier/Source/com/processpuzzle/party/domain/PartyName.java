/*
Name: 
    - PartyName

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

package com.processpuzzle.party.domain;

import java.util.Date;

import com.processpuzzle.fundamental_types.domain.GenericEntity;
import com.processpuzzle.persistence.query.domain.DefaultIdentityExpression;

public class PartyName extends GenericEntity<PartyName> implements Comparable<PartyName> {
   protected String name;
   protected String toUse;
   protected Date validFrom;
   protected Date validTo;

   public PartyName() {}

   public PartyName(String name) {
      this.name = name;
   }

   public String getName() {
      return name;
   }
   
   public void rename(String name) {
      setName( name );
   }

   public String getToUse() {
      return toUse;
   }

   public void setToUse(String toUse) {
      this.toUse = toUse;
   }

   public Date getValidFrom() {
      return validFrom;
   }

   public void setValidFrom(Date validFrom) {
      this.validFrom = validFrom;
   }

   public Date getValidTo() {
      return validTo;
   }

   public void setValidTo(Date validTo) {
      this.validTo = validTo;
   }

   private void setName(String name) {
      this.name = name;
   }

   public int compareTo( PartyName partyName ) {
      int c;
      if ((c = partyName.getName().compareTo(getName())) != 0)
         return c;
      return 0;
   }

   @Override
   public <I extends DefaultIdentityExpression<PartyName>> I getDefaultIdentity() {
      // TODO Auto-generated method stub
      return null;
   }

   @Override
   protected void defineIdentityExpressions() {
      // TODO Auto-generated method stub
      
   }
}
