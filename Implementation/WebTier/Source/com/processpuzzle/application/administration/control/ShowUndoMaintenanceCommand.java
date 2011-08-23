package com.processpuzzle.application.administration.control;

import com.processpuzzle.application.control.control.CommandDispatcher;
import com.processpuzzle.application.control.control.CommandInterface;

public class ShowUndoMaintenanceCommand implements CommandInterface{
	public static String COMMAND_NAME = "ShowUndoMaintenance";
	public ShowUndoMaintenanceCommand() {
		super();
	}

	public void init(CommandDispatcher dispatcher) {
	}

	public String getName() {
		return COMMAND_NAME;
	}

	public String execute(CommandDispatcher dispatcher) throws Exception {
		dispatcher.getServletContext().setAttribute("haltApplication", "false");
  	  	dispatcher.getRequest().setAttribute("messageKey", "undoMaintenance");
  	    dispatcher.getServletContext().setAttribute("maintainerUserId", null);
		return "/ProcessInstantiation/SystemAdministration/SystemAdministrationMessage.jsp";
	}

}
