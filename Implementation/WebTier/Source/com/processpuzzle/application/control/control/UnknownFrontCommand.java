/*
 * Created on Jan 23, 2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.processpuzzle.application.control.control;

import javax.servlet.http.HttpServletRequest;

/**
 * @author zsolt.zsuffa
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class UnknownFrontCommand implements CommandInterface {

	private String errorDescription = "The requested front command is unknown.";

	public UnknownFrontCommand() {
		super();
	}

	public UnknownFrontCommand(String name) {
		errorDescription = "The requested front command: '" + name
				+ "' is unknown.";
	}

	public void init(CommandDispatcher dispatcher) {
	}

	public String getName() {
		return "UnknownFrontCommand";
	}

	public String execute(CommandDispatcher dispatcher) throws Exception {
		HttpServletRequest request = dispatcher.getRequest();
		request.setAttribute("error", errorDescription);
		return "/FrontController/UnknownCommandError.jsp";
	}
}
