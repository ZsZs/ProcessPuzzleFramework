package com.processpuzzle.workflow.activity.domain;

public class ActionAbandonmentEvent extends ActionLifecycleEvent {

   public ActionAbandonmentEvent() {
      super();
      
   }

   public ActionAbandonmentEvent(String name, Action<?> eventSource) {
      super(name, eventSource);
      
   }

   public ActionAbandonmentEvent(String name) {
      super(name);
      
   }

  

}
