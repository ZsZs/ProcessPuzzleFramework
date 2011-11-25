/*
Name: 
    - InventoryEntry

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
import com.processpuzzle.fundamental_types.domain.TimePoint;
import com.processpuzzle.persistence.query.domain.DefaultIdentityExpression;

public class InventoryEntry extends GenericEntity<InventoryEntry> {
   public TimePoint whenBooked;
   public TimePoint whenCharged;
   private Transfer transfer;
   private Holding holding;

   public Holding getHolding() {
      return holding;
   }

   public void setHolding(Holding holding) {
      this.holding = holding;
   }

   public Transfer getTransfer() {
      return transfer;
   }

   public void setTransfer(Transfer transfer) {
      this.transfer = transfer;
   }

   public TimePoint getWhenBooked() {
      return whenBooked;
   }

   public void setWhenBooked(TimePoint whenBooked) {
      this.whenBooked = whenBooked;
   }

   public TimePoint getWhenCharged() {
      return whenCharged;
   }

   public void setWhenCharged(TimePoint whenCharged) {
      this.whenCharged = whenCharged;
   }

   @Override
   protected void defineIdentityExpressions() {
      // TODO Auto-generated method stub
      
   }

   @Override
   public <I extends DefaultIdentityExpression<InventoryEntry>> I getDefaultIdentity() {
      // TODO Auto-generated method stub
      return null;
   }
}
