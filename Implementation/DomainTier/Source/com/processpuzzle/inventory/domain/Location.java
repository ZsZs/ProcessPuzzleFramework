/*
 * Created on 2005.08.08.
 */
package com.processpuzzle.inventory.domain;

import com.processpuzzle.fundamental_types.domain.GenericEntity;
import com.processpuzzle.persistence.query.domain.DefaultIdentityExpression;

public class Location extends GenericEntity<Location> {
   private Holding holding;

   @Override
   public <I extends DefaultIdentityExpression<Location>> I getDefaultIdentity() {
      // TODO Auto-generated method stub
      return null;
   }

   public Integer getId() {
      return id;
   }

   public Holding getHolding() {
      return holding;
   }

   public void setHolding(Holding holding) {
      this.holding = holding;
   }

   protected Location() {
      super();
   }

   @Override
   protected void defineIdentityExpressions() {
      // TODO Auto-generated method stub
      
   }
}
