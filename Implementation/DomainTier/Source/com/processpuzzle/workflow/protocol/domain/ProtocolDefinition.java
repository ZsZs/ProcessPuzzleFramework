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
