/*
Name: 
    - SimpleActionDependency 

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
