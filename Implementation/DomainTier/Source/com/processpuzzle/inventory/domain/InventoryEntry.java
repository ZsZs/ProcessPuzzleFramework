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
