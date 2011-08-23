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
