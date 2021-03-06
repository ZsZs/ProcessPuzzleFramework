/*
Name: 
    - WorkflowDetailProtocol 

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

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;


/**
 * @author zsolt.zsuffa
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 * @generated "UML to Java (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
 * @uml.annotations
 * derived_abstraction="platform:/resource/ObjectPuzzle%20Pre-Implementation%20Models/Enterprise%20IT%20Design%20Model.emx#_ULCk4T5fEdq3NJ6sg2oavA"
 */
public class WorkflowDetailProtocol extends CompositeProtocol {
	
    public WorkflowDetailProtocol(String name) {
        super(name);
    }
    
    public WorkflowDetailProtocol() {}
    
    public void addparentLifecyclePhase(LifecyclePhaseProtocol lcpp) {
    	addCompositeProtocol(lcpp);
    }
    
    public void addActivity(ActivityProtocol activity) {
    	addStep(activity);
    }

	public Set<ActivityProtocol> getActivities() {
		Set<ActivityProtocol> activities = new HashSet<ActivityProtocol>();
		for (Iterator<?> iter = getProtocols().iterator(); iter.hasNext();) {
			Protocol pr = (Protocol) iter.next();
			if (pr instanceof ActivityProtocol) {
				ActivityProtocol activity = (ActivityProtocol) pr;
				activities.add(activity);
			}
		}
		return activities;
	}
    
    public void setActivities(Set<ActivityProtocol> activities) {
    	for (ActivityProtocol protocol : activities) {
			addStep(protocol);
		}
    }

}
