/*
Name: 
    - WorkflowManager 

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
import java.util.Date;
import java.util.Iterator;

import com.processpuzzle.fundamental_types.domain.TimePeriod;
import com.processpuzzle.fundamental_types.domain.TimePoint;
import com.processpuzzle.party.domain.Person;
import com.processpuzzle.party.domain.Project;
import com.processpuzzle.util.domain.OPDomainStrings;
import com.processpuzzle.workflow.protocol.domain.ActivityProtocol;

public class WorkflowManager {

   public static Action<?> nextStepFirst(ProcessPlan processPlan, Activity nextAction, Plan<?> plan) {
      Collection<?> members = null;
      Project project = processPlan.getProject();
      ActivityProtocol activity = (ActivityProtocol) nextAction.getProtocol();
      
      String name = activity.getPerformerRole().getName();
      
      members = project.getMembersByRole(name);
      if (members != null && members.size() > 0) {
         nextAction.setStatus(new ImplementableStatus(OPDomainStrings.ACTION_STATUS_IMPLEMENTABLE));

         for (Iterator<?> memberIterator = members.iterator(); memberIterator.hasNext();) {
            Person member = (Person) memberIterator.next();
            nextAction.setTimePeriod( new TimePeriod( new TimePoint( new Date(System.currentTimeMillis())), null));
            nextAction.allocateAssetTemporally( member, null);
         }
      } else {
         nextAction.setStatus(new WaitingForResourceStatus(OPDomainStrings.ACTION_STATUS_WAITING_FOR_RESOURCE));
      }
      return nextAction;
   }

   public static Collection<?> nextStep(ProcessPlan processPlan, Activity previousAction, Plan<?> plan) {
      ((ImplementedAction) previousAction.getCurrentInstance()).complete();
      Collection<?> consequentActions = previousAction.simpleconsequentActions();
      for (Iterator<?> iter = consequentActions.iterator(); iter.hasNext();) {
         Action<?> consequentAction = (Action<?>) iter.next();
         boolean dependentsComplete = true;
         for (Iterator<?> iterator = consequentAction.simpledependentActions().iterator(); iterator.hasNext();) {
            Action<?> dependentAction = (Action<?>) iterator.next();
            if (!(dependentAction.getStatus() instanceof CompletedStatus)) {
               dependentsComplete = false;
               break;
            }
         }

         if (dependentsComplete) {
            if (consequentAction.getProtocol() instanceof ActivityProtocol) {

               String role = ((ActivityProtocol) consequentAction.getProtocol()).getPerformerRole().getName();
               Collection<?> members = processPlan.getProject().getMembersByRole(role);
               if (members != null && members.size() > 0) {
                  consequentAction.setStatus(new ImplementableStatus(OPDomainStrings.ACTION_STATUS_IMPLEMENTABLE));

                  for (Iterator<?> memberIterator = members.iterator(); memberIterator.hasNext();) {
                     Person member = (Person) memberIterator.next();
                     consequentAction.allocateAssetTemporally(member, null);
                  }
               } else {
                  consequentAction.setStatus(new WaitingForResourceStatus(OPDomainStrings.ACTION_STATUS_WAITING_FOR_RESOURCE));
               }
            }
         }
      }
      return consequentActions;
   }

}
