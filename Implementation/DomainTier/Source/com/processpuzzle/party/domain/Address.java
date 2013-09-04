/*
Name: 
    - Address 

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


import com.processpuzzle.commons.persistence.Entity;
import com.processpuzzle.fundamental_types.domain.GenericEntity;
import com.processpuzzle.fundamental_types.domain.TimePeriod;
import com.processpuzzle.persistence.query.domain.DefaultIdentityExpression;

public class Address extends GenericEntity<Address> implements Entity {
   private String usedFor;
   private String name;
   private TimePeriod valid;
   private boolean isDefault = false;

   public Address() {}

   public Address(boolean isDefault) {
      this.isDefault = isDefault;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public TimePeriod getValid() {
      return valid;
   }

   public void setValid(TimePeriod valid) {
      this.valid = valid;
   }

   public boolean getIsDefault() {
      return isDefault;
   }

   public void setIsDefault(boolean isDefault) {
      this.isDefault = isDefault;
   }

   public @Override <I extends DefaultIdentityExpression<Address>> I getDefaultIdentity() {
      // TODO Auto-generated method stub
      return null;
   }

   @Override
   protected void defineIdentityExpressions() {
      // TODO Auto-generated method stub
      
   }

   public String getUsedFor() {
      return usedFor;
   }

   public void setUsedFor( String usedFor ) {
      this.usedFor = usedFor;
   }

}
