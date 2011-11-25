/*
Name: 
    - LifecyclePhaseProtocol 

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
import java.util.Iterator;
import java.util.Set;

public class LifecyclePhaseProtocol extends CompositeProtocol {

	public LifecyclePhaseProtocol() {
		super();
	}

	public LifecyclePhaseProtocol(String name) {
		super(name);
	}

	public void addParentLifecycleProtocol(LifecycleProtocol lcp) {
		addCompositeProtocol(lcp);
	}

	public Set<?> getParentLifecycleProtocols() {
		return getCompositeProtocols();
	}

	public void setParentLifecycleProtocols(Set<?> lcps) {
		setCompositeProtocols(lcps);
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

	public void addWorflowDetail(WorkflowDetailProtocol wfd) {
		addStep(wfd);
	}

	public Set<WorkflowDetailProtocol> getWorkFlowDetails() {
		Set<WorkflowDetailProtocol> wfds = new HashSet<WorkflowDetailProtocol>();
		for (Iterator<?> iter = getProtocols().iterator(); iter.hasNext();) {
			Protocol pr = (Protocol) iter.next();
			if (pr instanceof WorkflowDetailProtocol) {
				WorkflowDetailProtocol wfd = (WorkflowDetailProtocol) pr;
				wfds.add(wfd);
			}
		}
		return wfds;
	}

	public void setActivities(Set<ActivityProtocol> activities) {
		for (ActivityProtocol protocol : activities) {
			addStep(protocol);
		}
	}

	public void setWorkFlowDetails(Set<WorkflowDetailProtocol> workFlowDetails) {
		for (WorkflowDetailProtocol protocol : workFlowDetails) {
			addStep(protocol);
		}
	}
}
