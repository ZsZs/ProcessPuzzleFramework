/*
 * Created on 2005.07.19.
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.processpuzzle.workflow.activity.domain;

import com.processpuzzle.fundamental_types.quantity.domain.Quantity;
import com.processpuzzle.inventory.domain.Holding;
import com.processpuzzle.persistence.query.domain.DefaultIdentityExpression;

public class ConsumableResourceAllocation extends SpecificResourceAllocation {
   private Holding holding;

   public ConsumableResourceAllocation( Holding theHolding, Quantity theQuantity ) {
      super( theHolding.getType(), theQuantity );
      this.holding = theHolding;
   }

   public ConsumableResourceAllocation() {}

   @Override
   public <I extends DefaultIdentityExpression<ResourceAllocation>> I getDefaultIdentity() {
      // TODO Auto-generated method stub
      return null;
   }

   public Holding getHolding() {
      return holding;
   }

   public void setHolding( Holding holding ) {
      this.holding = holding;
   }

   @Override
   protected void defineIdentityExpressions() {
      // TODO Auto-generated method stub
      
   }

}
