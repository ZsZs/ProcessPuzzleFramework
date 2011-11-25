/*
Name: 
    - ActionStatus 

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
