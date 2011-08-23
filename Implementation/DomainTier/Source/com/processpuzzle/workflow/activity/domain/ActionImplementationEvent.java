package com.processpuzzle.workflow.activity.domain;


public class ActionImplementationEvent extends ActionLifecycleEvent {

   public ActionImplementationEvent(String name, Action<?> eventSource, ActionStatus nextStatus) {
      super(name, eventSource, nextStatus);
      
   }

   public ActionImplementationEvent() {
      super();
      
   }

   public ActionImplementationEvent(String name, Action<?> eventSource) {
      super(name, eventSource);
      
   }

   public ActionImplementationEvent(String name) {
      super(name);
      
   }


	
}
