package com.processpuzzle.artifact_management.control;

import com.processpuzzle.application.control.control.CommandDispatcher;
import com.processpuzzle.application.control.control.CommandInterface;

public class ShowToDoListCommand implements CommandInterface {

	public String execute(CommandDispatcher dispatcher) throws Exception {
		// TODO Auto-generated method stub
		return "/ProcessInstantiation/ProjectManagement/PersonalToDoList.jsp";
	}

	public String getName() {
		// TODO Auto-generated method stub
		return "ShowToDoList";
	}

	public void init(CommandDispatcher dispatcher) {
		// TODO Auto-generated method stub

	}

}
