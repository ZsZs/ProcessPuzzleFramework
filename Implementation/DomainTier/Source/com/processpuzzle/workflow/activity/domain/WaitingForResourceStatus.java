package com.processpuzzle.workflow.activity.domain;

public class WaitingForResourceStatus extends ActionStatus {

   public WaitingForResourceStatus() {
      this(null);
   }

   public WaitingForResourceStatus(String name) {
      super(name);
   }
   

}
