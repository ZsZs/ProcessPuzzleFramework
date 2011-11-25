/*
Name: 
    - ProposedAction 

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

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.processpuzzle.fundamental_types.domain.TimePeriod;
import com.processpuzzle.fundamental_types.domain.TimePoint;
import com.processpuzzle.util.domain.OPDomainStrings;

public class ProposedAction extends GenericAction {
   private ImplementedAction implementedAction;
   private boolean implementable = false;
   private Set<ResourceAllocation> bookedResources = new HashSet<ResourceAllocation>();

   protected ProposedAction() {
      super();
   }
   
   public ProposedAction(Activity parentSubAction) {
      super(parentSubAction);
      statusKeyName = OPDomainStrings.ACTION_STATUS_PROPOSED;
   }   

   public ImplementedAction implement() {
      ImplementedAction iAction = ActionFactory.createImplementedFromProposed(this, parentSubAction);
      ActionStatus iStatus = new ImplementedStatus(OPDomainStrings.ACTION_STATUS_IMPLEMENTED);
      parentSubAction.getPossibleLifecycleInstances().put(OPDomainStrings.ACTION_STATUS_IMPLEMENTED, iAction);
      ActionImplementationEvent event = new ActionImplementationEvent(OPDomainStrings.ACTION_EVENT_IMPLEMETATION, iAction.getParentSubAction(), iStatus);
      event.setOccuredOn(new TimePoint(new Date(System.currentTimeMillis())));
      parentSubAction.getActionEvents().add(event);
      parentSubAction.changeStatus(event);
      return iAction;
   }

   public ImplementedAction getImplementedAction() {
      return implementedAction;
   }

   public void setImplementedAction(ImplementedAction implementation) {
      this.implementedAction = implementation;
   }

   public boolean isImplementable() {
      return implementable;
   }

   public void canStart(boolean implementable) {
      this.implementable = implementable;
   }

   public TimePeriod getTimePeriod() { return parentSubAction.timePeriod; }

   public Set<ResourceAllocation> getBookedResources() { return bookedResources; }

   public TimePoint getProjectedBegin() { return new TimePoint(new Date(System.currentTimeMillis())); }

   public TimePoint getProjectedEnd() { return new TimePoint(new Date(System.currentTimeMillis())); }

   public TimePoint getRealBegin() {
      if ( implementedAction != null )  return implementedAction.getRealBegin();
      return null;
   }

   public TimePoint getRealEnd() {
      if (implementedAction != null) implementedAction.getRealEnd();
      return null;
   }

   public void setBookedResources(Set<ResourceAllocation> bookedResources) { this.bookedResources = bookedResources; }

   public void setProjectedBegin(TimePoint projectedBegin) { parentSubAction.setProjectedBegin( projectedBegin ); }

   public void setProjectedEnd(TimePoint projectedEnd) { parentSubAction.setProjectedEnd( projectedEnd ); }

   public void setRealBegin( TimePoint realBegin ) {
      if (implementedAction == null) implement();
      implementedAction.setRealBegin( realBegin );
   }

   public void setRealEnd( TimePoint realEnd ) {
      if (implementedAction == null) implement();
      implementedAction.setRealEnd( realEnd );
   }
}
