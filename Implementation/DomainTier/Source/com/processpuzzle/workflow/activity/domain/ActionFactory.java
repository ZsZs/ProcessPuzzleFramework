/*
Name: 
    - ActionFactory 

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

import java.util.Iterator;

import com.processpuzzle.workflow.protocol.domain.CompositeProtocol;
import com.processpuzzle.workflow.protocol.domain.LifecycleProtocol;
import com.processpuzzle.workflow.protocol.domain.Protocol;

public class ActionFactory {

   public static Activity createSubAction(String name, Protocol protocol) {
      Activity gAction = new Activity(name);
      gAction.setProtocol(protocol);
      return gAction;
   }

   public static Plan<?> createPlan(String name) {
      if (name.equals(""))
         throw new IllegalArgumentException();
      Plan<?> plan = new Plan(name);
      return plan;
   }

   public static Plan<?> createPlan(String string, Protocol protocol) {
      Plan<?> plan = createPlan(string);
      plan.setProtocol(protocol);
      return plan;
   }

   public static ProcessPlan createProcessPlan(String name) {
      if (name.equals(""))
         throw new IllegalArgumentException();
      return new ProcessPlan(name);
   }

   public static ProcessPlan createProcessPlan(String name, Protocol protocol) {
      ProcessPlan processPlan = createProcessPlan(name);
      processPlan.setProtocol(protocol);
      return processPlan;
   }

   public static ImplementedAction createImplementedFromProposed(ProposedAction pAction, Activity parentSubAction) {
      ImplementedAction iAction = new ImplementedAction(parentSubAction);
      iAction.transferState(pAction);
      return iAction;
   }

   public static CompletedAction createCompletedFromImplemented(ImplementedAction iAction, Activity subAction) {
      CompletedAction cAction = new CompletedAction(subAction);
      cAction.transferState(iAction);
      return cAction;
   }

   public static Plan<?> createProcessByProtocol(String name, LifecycleProtocol lcp) {
      Plan<?> pplan = createPlan(name, lcp);
      for (Iterator<?> iter = lcp.getCompositeProtocols().iterator(); iter.hasNext();) {
         CompositeProtocol protocol = (CompositeProtocol) iter.next();
         Plan<?> plan = new Plan(protocol.getName(), protocol);
         pplan.addSubAction(plan);
         for (Iterator<?> iterator1 = protocol.getCompositeProtocols().iterator(); iterator1.hasNext();) {
            Protocol prot = (Protocol) iterator1.next();
            Activity gAction = new Activity(prot.getName(), prot); 
            plan.addSubAction(gAction);
         }

      }
      return pplan;
   }

   public static ProposedAction createProposedAction(String string) {
      return new ProposedAction();
   }

   public static ImplementedAction createImplementedAction(String string) {
      return null;
   }
}
