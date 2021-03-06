/*
Name: 
    - ImplementedAction 

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
 */
package com.processpuzzle.workflow.activity.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.processpuzzle.fundamental_types.domain.TimePeriod;
import com.processpuzzle.fundamental_types.domain.TimePoint;
import com.processpuzzle.fundamental_types.quantity.domain.Quantity;
import com.processpuzzle.resource.resourcetype.domain.ResourceType;
import com.processpuzzle.util.domain.OPDomainStrings;

public class ImplementedAction extends GenericAction {
   private ProposedAction proposedAction = null;
   private CompletedAction completedAction = null;
   private Set<ResourceAllocation> usedResources = new HashSet<ResourceAllocation>();

   public ImplementedAction(Activity parentSubAction) {
      super(parentSubAction);
      statusKeyName = OPDomainStrings.ACTION_STATUS_IMPLEMENTED;
   }

   public ImplementedAction() {
      super();
   }

   public CompletedAction complete() {
      CompletedAction completedAction = ActionFactory.createCompletedFromImplemented( this, parentSubAction );
      CompletedStatus status = new CompletedStatus( OPDomainStrings.ACTION_STATUS_COMPLETED );
      parentSubAction.getPossibleLifecycleInstances().put( OPDomainStrings.ACTION_STATUS_COMPLETED, completedAction );
      ActionCompletitionEvent event = new ActionCompletitionEvent(OPDomainStrings.ACTION_EVENT_COMPLETITION, completedAction.getParentSubAction(), status);
      event.setOccuredOn(new TimePoint(new Date(System.currentTimeMillis())));
      parentSubAction.getActionEvents().add(event);
      parentSubAction.changeStatus(event);
      return completedAction;
   }

   public void transferState(ProposedAction from) {
      from.setImplementedAction(this);
      this.proposedAction = from;
   }

   public void useResouce(ResourceType resouce, Quantity quantity) {}

   public void addUsedResources( TemporalResourceAllocation temporalResourceAlloc ) {
      if ( usedResources.add(temporalResourceAlloc) ) {
         temporalResourceAlloc.setImplementedAction(this);
      }
   }

   public Set<ResourceAllocation> getUsedResources() { return usedResources; }

   public void setUsedResources(Set<ResourceAllocation> usedResources) { this.usedResources = usedResources; }

   public CompletedAction getCompletedAction() { return completedAction; }

   public void setCompletedAction(CompletedAction completed) { this.completedAction = completed; }

   public ProposedAction getProposedAction() { return proposedAction; }

   public void setProposedAction(ProposedAction proposed) { this.proposedAction = proposed; }

   public TimePeriod getTimePeriod() { return parentSubAction.timePeriod; }

   public TimePoint getProjectedBegin() { return proposedAction.getProjectedBegin(); }

   public void setProjectedBegin( TimePoint projectedBegin ) { proposedAction.setProjectedBegin( projectedBegin ); }

   public TimePoint getProjectedEnd() { return proposedAction.getProjectedEnd(); }

   public void setProjectedEnd( TimePoint projectedEnd ) { proposedAction.setProjectedEnd( projectedEnd ); }

   public TimePoint getRealBegin() {
      if (completedAction != null)
         return completedAction.getRealBegin();
      return null;
   }

   public void setRealBegin( TimePoint realBegin ) {
      if (completedAction != null) completedAction.setRealBegin( realBegin );
   }

   public TimePoint getRealEnd() {
      if (completedAction != null)
         return completedAction.getRealEnd();
      return null;
   }

   public void setRealEnd( TimePoint realEnd ) {
      if ( completedAction != null ) completedAction.setRealEnd( realEnd );
   }
}
