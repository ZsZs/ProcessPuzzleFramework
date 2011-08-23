/*
 * Created on 2005.07.19.
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.processpuzzle.workflow.activity.domain;

import java.util.Date;

import com.processpuzzle.fundamental_types.domain.GenericEntity;
import com.processpuzzle.fundamental_types.domain.TimePeriod;
import com.processpuzzle.persistence.query.domain.DefaultIdentityExpression;

public abstract class ActionStatus extends GenericEntity<ActionStatus> {
   protected String name;
   protected ActionStatus subStatus;

   @Override
   public <I extends DefaultIdentityExpression<ActionStatus>> I getDefaultIdentity() {
      // TODO Auto-generated method stub
      return null;
   }

   public ActionStatus(String name) {
      this.name = name;
   }

   public ActionStatus() {
      this(null);
   }

   public void suspend(Action<?> action, TimePeriod period) {
      action.setSuspension(new Suspension(action.getStatus(), period));
      action.setStatus(new SuspendedStatus());
   }

   public AbandonedAction abandone(Action<?> action, Date effective, String cause) {
      AbandonedAction abandonedAction = new AbandonedAction((Activity) action);
      abandonedAction.setEffective(effective);
      abandonedAction.setCause(cause);
      action.setStatus(new AbandonedStatus());
      return abandonedAction;
   }

   public void reactivate(Action<?> action) {

   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public ActionStatus getSubStatus() {
      return subStatus;
   }

   public void changeSubStatus(ActionStatus subStatus) {
      this.subStatus = subStatus;
   }

   @Override
   protected void defineIdentityExpressions() {
      // TODO Auto-generated method stub
      
   }

}
