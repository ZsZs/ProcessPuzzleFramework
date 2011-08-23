package com.processpuzzle.artifact_management.control;

import com.processpuzzle.application.control.control.CommandDispatcher;

/**
 * @generatedBy CodePro at 2005.11.13. 6:28
 * @author zsolt.zsuffa
 */
public class SaveArtifactCommand extends ArtifactCommand {

	public void init(CommandDispatcher dispatcher) {
		super.init(dispatcher);
	}

	public String getName() {
		return "Save";
	}

	public String execute(CommandDispatcher dispatcher) throws Exception {
		if (save())
			return "xxx.jsp";
		else
			return "???.jsp";
	}

	protected boolean save() {
		if (checkTimeStamp()) {
			startTransaction();
			// todo implement save
			endTransaction();
			return true;
		} else
			return false;
	}

	private void startTransaction() {
	}

	private void endTransaction() {
	}

	private boolean checkTimeStamp() {
		return true;
	}

   protected void retrieveRequestParameters(CommandDispatcher dispatcher) {
      // TODO Auto-generated method stub
      
   }

   protected void retrieveResponseDocument() {
      // TODO Auto-generated method stub
      
   }
}