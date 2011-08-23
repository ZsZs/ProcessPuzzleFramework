package com.processpuzzle.artifact_management.control;

import com.processpuzzle.application.control.control.CommandDispatcher;
import com.processpuzzle.application.control.control.CommandInterface;

/**
 * Instances of this class represent "New" commands.
 * @author zsolt.zsuffa
 */
public class NewArtifactCommand implements CommandInterface {

	public void init(CommandDispatcher dispatcher) {
	}

	public String getName() {
		return "New";
	}

	public String execute(CommandDispatcher dispatcher) throws Exception {
	   if (create()) {
	      return "xxx.jsp";
	   }
	   else return "???.jsp";
	}
	
	protected boolean create() {
	   return true;
	}
}