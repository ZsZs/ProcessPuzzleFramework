/*
Name: 
    - GenericAction 

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


import com.processpuzzle.commons.persistence.AggregateRoot;
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
