package com.processpuzzle.workflow.activity.domain;

import java.util.Date;

public class ProposedStatus extends ActionStatus {
	
	public ProposedStatus(String name) {
		super(name);
	}
	
	public ProposedStatus() {
		this(null);
	}
	
	public AbandonedAction abandone(Action<?> action, Date effective, String cause) {
		AbandonedAction abandonedAction = super.abandone(action, effective, cause);
//		ProposedAction proposedAction = (ProposedAction) action;			
//		abandonedAction.setProposedAction(proposedAction);
		return abandonedAction;
	}

}
