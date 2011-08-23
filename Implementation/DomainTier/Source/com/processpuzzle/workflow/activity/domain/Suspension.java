package com.processpuzzle.workflow.activity.domain;

import com.processpuzzle.fundamental_types.domain.GenericEntity;
import com.processpuzzle.fundamental_types.domain.TimePeriod;
import com.processpuzzle.persistence.query.domain.DefaultIdentityExpression;

public class Suspension extends GenericEntity<Suspension>{
   private ActionStatus originalState;
   private TimePeriod period;

   public Suspension( ActionStatus originalState, TimePeriod period ) {
      this.originalState = originalState;
      this.period = period;
   }

   public Suspension() {}

   public TimePeriod getPeriod() {
      return period;
   }

   public void setPeriod( TimePeriod period ) {
      this.period = period;
   }

   public ActionStatus getOriginalState() {
      return originalState;
   }

   public void setOriginalState( ActionStatus originalState ) {
      this.originalState = originalState;
   }

   @Override
   public <I extends DefaultIdentityExpression<Suspension>> I getDefaultIdentity() {
      // TODO Auto-generated method stub
      return null;
   }

   @Override
   protected void defineIdentityExpressions() {
      // TODO Auto-generated method stub
      
   }

}
