/*
 * Created on 2005.07.19.
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.processpuzzle.workflow.protocol.domain;

import com.processpuzzle.party.partyrelationshiptype.domain.PartyRoleType;

public class ProtocolFactory {
    
    public static LifecycleProtocol createLifecycleProtocol(String theName){
        LifecycleProtocol theLifeCycle = new LifecycleProtocol(theName);
        return theLifeCycle;
    }

    public static LifecyclePhaseProtocol createLifecyclePhaseProtocol(String theName){
    	LifecyclePhaseProtocol lifecycpePhaseProtocol = new LifecyclePhaseProtocol(theName);
        return lifecycpePhaseProtocol;
    }

	public static WorkflowDetailProtocol createWorkflowDetailProtocol(String name) {
		WorkflowDetailProtocol detail = new WorkflowDetailProtocol(name);
		return detail;
	}
		
	public static ActivityProtocol createActivityProtocol(String theName, PartyRoleType theRole){
	    ActivityProtocol theActivity = new ActivityProtocol(theName, theRole);
	    return theActivity;
	}
	
	public static ActivityProtocol createActivityProtocol(String theName){
	    ActivityProtocol theActivity = new ActivityProtocol(theName);
	    return theActivity;
	}

//	public static Protocol createProtocol(String name) {
//		Protocol protocol = new Protocol(name);
//		return protocol;
//	}

	public static LifecycleProtocol createLifecycle(String name) {
		LifecycleProtocol lcp = new LifecycleProtocol(name);
		return lcp;
	}
   
    
}
