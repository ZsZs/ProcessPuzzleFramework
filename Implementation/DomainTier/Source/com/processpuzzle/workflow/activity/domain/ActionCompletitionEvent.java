package com.processpuzzle.workflow.activity.domain;


public class ActionCompletitionEvent extends ActionLifecycleEvent {

   public ActionCompletitionEvent(String name, Action<?> eventSource, ActionStatus nextStatus) {
      super(name, eventSource, nextStatus);
      
   }

   public ActionCompletitionEvent() {
      super();
      
   }

   public ActionCompletitionEvent(String name, Action<?> eventSource) {
      super(name, eventSource);
      
   }

   public ActionCompletitionEvent(String name) {
      super(name);
      
   }

 
 
}
