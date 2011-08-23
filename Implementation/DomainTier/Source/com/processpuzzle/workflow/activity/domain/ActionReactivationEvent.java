package com.processpuzzle.workflow.activity.domain;

public class ActionReactivationEvent extends ActionLifecycleEvent {

   public ActionReactivationEvent() {
      super();
      
   }

   public ActionReactivationEvent(String name, Action<?> eventSource) {
      super(name, eventSource);
      
   }

   public ActionReactivationEvent(String name) {
      super(name);
      
   }

     

}
