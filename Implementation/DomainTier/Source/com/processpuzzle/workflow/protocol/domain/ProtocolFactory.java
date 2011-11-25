/*
Name: 
    - ProtocolFactory 

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
