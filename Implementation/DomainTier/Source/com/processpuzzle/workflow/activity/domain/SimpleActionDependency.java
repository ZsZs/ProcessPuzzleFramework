package com.processpuzzle.workflow.activity.domain;

import com.processpuzzle.fundamental_types.domain.GenericEntity;
import com.processpuzzle.persistence.query.domain.DefaultIdentityExpression;

public class SimpleActionDependency extends GenericEntity<SimpleActionDependency>{
   private Action<?> dependentAction;
   private Action<?> consequentAction;
   private boolean strict;

   public SimpleActionDependency(Action<?> dependent, Action<?> consequent, boolean strict) {
      this.dependentAction = dependent;
      this.consequentAction = consequent;
      this.strict = strict;
   }

   public SimpleActionDependency(Action<?> dependent, Action<?> consequent) {
      this.dependentAction = dependent;
      this.consequentAction = consequent;
   }

   public SimpleActionDependency() {}

   public Action<?> getConsequentAction() {
      return consequentAction;
   }

   public void setConsequentAction(Action<?> consequentAction) {
      this.consequentAction = consequentAction;
   }

   public Action<?> getDependentAction() {
      return dependentAction;
   }

   public void setDependentAction(Action<?> dependentAction) {
      this.dependentAction = dependentAction;
   }

   public boolean isStrict() {
      return strict;
   }

   public void setStrict(boolean strict) {
      this.strict = strict;
   }

   @Override
   protected void defineIdentityExpressions() {
      // TODO Auto-generated method stub
      
   }

   @Override
   public <I extends DefaultIdentityExpression<SimpleActionDependency>> I getDefaultIdentity() {
      // TODO Auto-generated method stub
      return null;
   }
}
