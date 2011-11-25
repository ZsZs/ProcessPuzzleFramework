/*
Name: 
    - ActionReference 

Description: 
    -  

Requires:
    - 
    
Provides:
    - 

Part of: ProcessPuzzle Framework, Domain and Business Model Ready Architecture. Provides content, workflow and social networking functionality. 
http://www.processpuzzle.com

ProcessPuzzle - Content and Workflow Management Integration Business Platform

Author(s): 
    - Zsolt Zsuffa

Copyright: (C) 2011 This program is free software: you can redistribute it and/or modify it under the terms of the 
GNU General Public License as published by the Free Software Foundation, either version 3 of the License, 
or (at your option) any later version.

This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License for more details.

You should have received a copy of the GNU General Public License along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/

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
