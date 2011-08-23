package com.processpuzzle.workflow.activity.domain;

import hu.itkodex.commons.persistence.AggregateRoot;

import com.processpuzzle.fundamental_types.domain.GenericEntity;
import com.processpuzzle.fundamental_types.domain.TimePoint;
import com.processpuzzle.persistence.query.domain.DefaultIdentityExpression;

public abstract class GenericAction extends GenericEntity<GenericAction> implements AggregateRoot {
   protected String statusKeyName;
   protected Activity parentSubAction;

   public GenericAction(Activity parentSubAction) {
      this.parentSubAction = parentSubAction;
   }

   public GenericAction() {}

   @Override
   public <I extends DefaultIdentityExpression<GenericAction>> I getDefaultIdentity() {
      // TODO Auto-generated method stub
      return null;
   }

   public Activity getParentSubAction() {
      return parentSubAction;
   }

   public void setParentSubAction(Activity parentSubAction) {
      this.parentSubAction = parentSubAction;
   }
   
   public String getStatusKeyName() {
      return statusKeyName;
   }

   public void setStatusKeyName(String statusKeyName) {
      this.statusKeyName = statusKeyName;
   }


   public abstract TimePoint getProjectedBegin();

   public abstract TimePoint getProjectedEnd();

   public abstract TimePoint getRealBegin();

   public abstract TimePoint getRealEnd();

   public abstract void setProjectedBegin(TimePoint projectedBegin);

   public abstract void setProjectedEnd(TimePoint projectedEnd);

   public abstract void setRealBegin(TimePoint realBegin);

   public abstract void setRealEnd(TimePoint realEnd);

   @Override
   protected void defineIdentityExpressions() {
      // TODO Auto-generated method stub
   }
}
