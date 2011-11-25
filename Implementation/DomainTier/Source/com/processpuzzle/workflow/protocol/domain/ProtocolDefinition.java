/*
Name: 
    - ProtocolDefinition 

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

package com.processpuzzle.workflow.protocol.domain;

import java.util.HashSet;
import java.util.Set;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

public class ProtocolDefinition {
   public @XmlElementWrapper( name = "activities" ) @XmlElement( name = "activity" )
   Set<ActivityProtocol> activities = new HashSet<ActivityProtocol>();
   public @XmlElementWrapper( name = "workflowDetails" )
   @XmlElement( name = "workflowDetail" )
   Set<WorkflowDetailProtocol> workflowDetails = new HashSet<WorkflowDetailProtocol>();
   public @XmlElement( name = "lifecycleProtocol" )
   Set<LifecycleProtocol> lifecycles = new HashSet<LifecycleProtocol>();

   public void clearCollections() {
      activities.clear();
      workflowDetails.clear();
      lifecycles.clear();
   }

   // @XmlElement(name="lifecyclePhaseProtocol")
   // @XmlElementWrapper(name="lifecyclePhaseDefinition")
   // public Set<LifecyclePhaseProtocol> lifecyclePhases = new HashSet<LifecyclePhaseProtocol>();
   //	
   // @XmlElement(name="workflowDetailProtocol")
   // @XmlElementWrapper(name="workflowDetailDefinition")
   // public Set<WorkflowDetailProtocol> workflowDetails = new HashSet<WorkflowDetailProtocol>();

   // @XmlElement(name="lifecyclePhase")
   // public Set<LifecyclePhaseProtocol> ls = new HashSet<LifecyclePhaseProtocol>();
   //	
   // @XmlElement(name="workflowDetail")
   // public Set<WorkflowDetailProtocol> ws = new HashSet<WorkflowDetailProtocol>();
   //	
   // @XmlElement(name="activity")
   // public Set<ActivityProtocol> activity = new HashSet<ActivityProtocol>();

}
