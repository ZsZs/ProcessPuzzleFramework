package com.processpuzzle.workflow.activity.domain;

import com.processpuzzle.fundamental_types.domain.TimePeriod;

public class SuspendedStatus extends ActionStatus {
	
	public SuspendedStatus(String name) {
		super(name);
	}
	
	public SuspendedStatus() {
		this(null);
	}

	public void suspend(Action<?> action, TimePeriod period) {
		
		throw new UnsupportedStateTransitionException();
	}
	
	

}
