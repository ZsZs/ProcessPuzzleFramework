/*
 * Created on 2005.08.08.
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.processpuzzle.inventory.domain;

import com.processpuzzle.fundamental_types.domain.GenericEntity;
import com.processpuzzle.persistence.query.domain.DefaultIdentityExpression;

public class Transfer extends GenericEntity<Transfer>{
   private InventoryEntry fromInventoryEntry;
   private InventoryEntry toInventoryEntry;

   public InventoryEntry getFromInventoryEntry() {
      return fromInventoryEntry;
   }

   public void setFromInventoryEntry( InventoryEntry fromInventoryEntry ) {
      this.fromInventoryEntry = fromInventoryEntry;
   }

   public InventoryEntry getToInventoryEntry() {
      return toInventoryEntry;
   }

   public void setToInventoryEntry( InventoryEntry toInventoryEntry ) {
      this.toInventoryEntry = toInventoryEntry;
   }

   @Override
   protected void defineIdentityExpressions() {
      // TODO Auto-generated method stub
      
   }

   @Override
   public <I extends DefaultIdentityExpression<Transfer>> I getDefaultIdentity() {
      // TODO Auto-generated method stub
      return null;
   }
}
