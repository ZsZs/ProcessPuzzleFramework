/*
Name: 
    - Holding

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
 * Created on 2005.08.08.
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.processpuzzle.inventory.domain;

import com.processpuzzle.fundamental_types.domain.GenericEntity;
import com.processpuzzle.fundamental_types.quantity.domain.Quantity;
import com.processpuzzle.persistence.query.domain.DefaultIdentityExpression;

public class Holding extends GenericEntity<Holding> {
   private Integer id;
   private Quantity balance;
   private HoldingType type;
   private Location location;
   private InventoryEntry entry;
   private String name;

   public Holding() {}

   public Holding(String name, HoldingType type) {
      if ((name == null) || (name.equals("")))
         throw new IllegalArgumentException();
      this.name = name;
      this.type = type;
   }

   public Quantity getBalance() {
      return balance;
   }

   public void setBalance(Quantity balance) {
      this.balance = balance;
   }

   public InventoryEntry getEntry() {
      return entry;
   }

   public void setEntry(InventoryEntry entry) {
      this.entry = entry;
   }

   public Location getLocation() {
      return location;
   }

   public void setLocation(Location location) {
      this.location = location;
   }

   public HoldingType getType() {
      return type;
   }

   public void setType(HoldingType type) {
      this.type = type;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public Integer getId() {
      return id;
   }

   @Override
   protected void defineIdentityExpressions() {
      // TODO Auto-generated method stub
      
   }

   @Override
   public <I extends DefaultIdentityExpression<Holding>> I getDefaultIdentity() {
      // TODO Auto-generated method stub
      return null;
   }

}
