package com.processpuzzle.workflow.activity.domain;

import com.processpuzzle.fundamental_types.domain.TimePeriod;

public class CompletedStatus extends ImplementedStatus{
	
	public CompletedStatus(String name) {
		super(name);
	}
	
	public CompletedStatus() {
		this(null);
	}

	public void suspend(Action<?> action, TimePeriod period) {
		throw new UnsupportedStateTransitionException();
	}

}
