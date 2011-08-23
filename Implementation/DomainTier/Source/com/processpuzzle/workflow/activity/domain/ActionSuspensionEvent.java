package com.processpuzzle.workflow.activity.domain;

public class ActionSuspensionEvent extends ActionLifecycleEvent {

   public ActionSuspensionEvent() {
      super();

   }

   public ActionSuspensionEvent(String name, Action<?> eventSource) {
      super(name, eventSource);

   }

   public ActionSuspensionEvent(String name) {
      super(name);

   }

}
