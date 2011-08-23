package com.processpuzzle.workflow.activity.domain;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import com.processpuzzle.fundamental_types.domain.GenericEntity;
import com.processpuzzle.persistence.query.domain.DefaultIdentityExpression;
import com.processpuzzle.workflow.protocol.domain.ProtocolCallAction;

public class ActionReference extends GenericEntity<ActionReference> {
   private Action<?> action;
   private Plan<?> thePlan;
   private Set<ActionDependency> consequentActions = new HashSet<ActionDependency>();
   private Set<ActionDependency> dependentActions = new HashSet<ActionDependency>();
   private ProtocolCallAction protocolReference;

   public ActionReference( Action<?> action, Plan<?> pl, ProtocolCallAction pRef ) {
      this.action = action;
      this.thePlan = pl;
      this.protocolReference = pRef;
   }

   public ActionReference( Action<?> action, Plan<?> pl ) {
      this.action = action;
      this.thePlan = pl;
   }

   public ActionReference() {}

   public void addDependentAction( ActionReference actionRef ) {
      ActionDependency dependency = new ActionDependency( actionRef, this, actionRef.getAction().isMandatory() );
      dependentActions.add( dependency );
      actionRef.getConsequentActions().add( dependency );
   }

   public void addConsequentAction( ActionReference actionRef ) {
      ActionDependency dependency = new ActionDependency( this, actionRef, this.getAction().isMandatory() );
      consequentActions.add( dependency );
      actionRef.getDependentActions().add( dependency );
   }

   public Collection<Action<?>> dependentActions() {
      Collection<Action<?>> actions = new HashSet<Action<?>>();
      for( Iterator<ActionDependency> iter = dependentActions.iterator(); iter.hasNext(); ){
         ActionDependency dependency = iter.next();
         actions.add( dependency.getDependentAction().getAction() );
      }
      return actions;
   }

   public Collection<Action> consequentActions() {
      Collection<Action> actions = new HashSet<Action>();
      for( Iterator<ActionDependency> iter = consequentActions.iterator(); iter.hasNext(); ){
         ActionDependency dependency = iter.next();
         actions.add( dependency.getConsequentAction().getAction() );
      }
      return actions;
   }

   public Action<?> getAction() {
      return action;
   }

   public void setAction( Action<?> action ) {
      this.action = action;
   }

   public Plan<?> getThePlan() {
      return thePlan;
   }

   public void setThePlan( Plan<?> thePlan ) {
      this.thePlan = thePlan;
   }

   public Set<ActionDependency> getDependentActions() {
      return dependentActions;
   }

   public void setDependentActions( Set<ActionDependency> dependentActions ) {
      this.dependentActions = dependentActions;
   }

   public Set<ActionDependency> getConsequentActions() {
      return consequentActions;
   }

   public void setConsequentActions( Set<ActionDependency> consequentActions ) {
      this.consequentActions = consequentActions;
   }

   public ProtocolCallAction getProtocolReference() {
      return protocolReference;
   }

   public void setProtocolReference( ProtocolCallAction protocolReference ) {
      this.protocolReference = protocolReference;
   }

   @Override
   protected void defineIdentityExpressions() {
      // TODO Auto-generated method stub
      
   }

   @Override
   public <I extends DefaultIdentityExpression<ActionReference>> I getDefaultIdentity() {
      // TODO Auto-generated method stub
      return null;
   }
}
