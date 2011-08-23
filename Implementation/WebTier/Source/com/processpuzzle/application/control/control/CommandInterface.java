package com.processpuzzle.application.control.control;

public interface CommandInterface {

	public void init(CommandDispatcher dispatcher);

	public String getName();

	public String execute(CommandDispatcher dispatcher) throws Exception;
}