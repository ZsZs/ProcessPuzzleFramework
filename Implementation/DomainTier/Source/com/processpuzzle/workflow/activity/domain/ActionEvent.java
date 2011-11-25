/*
Name: 
    - ActionEvent 

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
import com.processpuzzle.fundamental_types.domain.TimePoint;
import com.processpuzzle.persistence.query.domain.DefaultIdentityExpression;

public class ActionEvent extends GenericEntity<ActionEvent>{
   protected String name;
   protected Action<?> eventSource;
   protected TimePoint occuredOn;
   protected ActionStatus nextStatus;

   public ActionEvent(String name, Action<?> eventSource, ActionStatus nextStatus) {
      this.name = name;
      this.eventSource = eventSource;
      this.nextStatus = nextStatus;
   }

   public ActionEvent(String name, Action<?> eventSource) {
      this.name = name;
      this.eventSource = eventSource;
   }

   public ActionEvent(String name) {
      this(name, null);
   }

   public ActionEvent() {
      this(null, null);
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public Action<?> getEventSource() {
      return eventSource;
   }

   public void setEventSource(Action<?> eventSource) {
      this.eventSource = eventSource;
   }

   public ActionStatus getNextStatus() {
      return nextStatus;
   }

   public void setNextStatus(ActionStatus nextStatus) {
      this.nextStatus = nextStatus;
   }

   public TimePoint getOccuredOn() {
      return occuredOn;
   }

   public void setOccuredOn(TimePoint occuredOn) {
      this.occuredOn = occuredOn;
   }

   @Override
   public <I extends DefaultIdentityExpression<ActionEvent>> I getDefaultIdentity() {
      // TODO Auto-generated method stub
      return null;
   }

   @Override
   protected void defineIdentityExpressions() {
      // TODO Auto-generated method stub
      
   }
}
