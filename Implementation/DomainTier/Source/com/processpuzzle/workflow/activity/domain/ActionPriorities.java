package com.processpuzzle.workflow.activity.domain;

public class ActionPriorities {

	public static final int HIGH = 0;
	public static final int MED = 1;
	public static final int LOW = 2;

   public static final String[] stateDescription = { "high", "med", "low" };

   public static String getStateDescription(int index) {
      return stateDescription[index];
   }

   public static String[] getStateDescriptions() {
      return stateDescription;

   }
	
}
