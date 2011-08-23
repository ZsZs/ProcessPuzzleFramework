package com.processpuzzle.workflow.activity.domain;

import com.processpuzzle.fundamental_types.domain.GenericEntity;
import com.processpuzzle.persistence.query.domain.DefaultIdentityExpression;

public class ActionDependency extends GenericEntity<ActionDependency>{
   private ActionReference dependentAction;
   private ActionReference consequentAction;
   private boolean strict;

   public ActionDependency() {}

   public ActionDependency(ActionReference dependent, ActionReference consequent, boolean strict) {
      this.dependentAction = dependent;
      this.consequentAction = consequent;
      this.strict = strict;
   }

   public ActionDependency(ActionReference dependent, ActionReference consequent) {
      this.dependentAction = dependent;
      this.consequentAction = consequent;
   }

   public ActionReference getConsequentAction() {
      return consequentAction;
   }

   public void setConsequentAction(ActionReference consequentAction) {
      this.consequentAction = consequentAction;
   }

   public ActionReference getDependentAction() {
      return dependentAction;
   }

   public void setDependentAction(ActionReference dependentAction) {
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
   public <I extends DefaultIdentityExpression<ActionDependency>> I getDefaultIdentity() {
      // TODO Auto-generated method stub
      return null;
   }
}
