package com.processpuzzle.workflow.protocol.domain;

import com.processpuzzle.fundamental_types.domain.ExceptionHelper;
import com.processpuzzle.fundamental_types.domain.ProcessPuzzleProgrammingException;

public class IllegalDependencyException extends ProcessPuzzleProgrammingException {
	private static final long serialVersionUID = -5823954294605977639L;
    private static String defaultMessagePattern;
	
	public IllegalDependencyException(String msg) {
		super(ExceptionHelper.defineMessage(IllegalDependencyException.class, new Object[] {msg}, defaultMessagePattern));
	}

	public IllegalDependencyException() {
		super(ExceptionHelper.defineMessage(IllegalDependencyException.class, new Object[] {}, defaultMessagePattern));
	}

}
