package com.processpuzzle.workflow.activity.domain;

public class ActionLifecycleEvent extends ActionEvent {

   public ActionLifecycleEvent(String name, Action<?> eventSource, ActionStatus nextStatus) {
      super(name, eventSource, nextStatus);
      
   }

   public ActionLifecycleEvent() {
      super();
      
   }

   public ActionLifecycleEvent(String name, Action<?> eventSource) {
      super(name, eventSource);
      
   }

   public ActionLifecycleEvent(String name) {
      super(name);
      
   }

}
