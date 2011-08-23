package com.processpuzzle.workflow.activity.domain;

import com.processpuzzle.fundamental_types.domain.GenericEntity;
import com.processpuzzle.fundamental_types.domain.TimePoint;
import com.processpuzzle.persistence.query.domain.DefaultIdentityExpression;

public class ActionEvent extends GenericEntity<ActionEvent>{
   protected String name;
   protected Action<?> eventSource;
   protected TimePoint occuredOn;
   protected ActionStatus nextStatus;

   public ActionEvent(String name, Action<?> eventSource, ActionStatus nextStatus) {
      this.name = name;
      this.eventSource = eventSource;
      this.nextStatus = nextStatus;
   }

   public ActionEvent(String name, Action<?> eventSource) {
      this.name = name;
      this.eventSource = eventSource;
   }

   public ActionEvent(String name) {
      this(name, null);
   }

   public ActionEvent() {
      this(null, null);
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public Action<?> getEventSource() {
      return eventSource;
   }

   public void setEventSource(Action<?> eventSource) {
      this.eventSource = eventSource;
   }

   public ActionStatus getNextStatus() {
      return nextStatus;
   }

   public void setNextStatus(ActionStatus nextStatus) {
      this.nextStatus = nextStatus;
   }

   public TimePoint getOccuredOn() {
      return occuredOn;
   }

   public void setOccuredOn(TimePoint occuredOn) {
      this.occuredOn = occuredOn;
   }

   @Override
   public <I extends DefaultIdentityExpression<ActionEvent>> I getDefaultIdentity() {
      // TODO Auto-generated method stub
      return null;
   }

   @Override
   protected void defineIdentityExpressions() {
      // TODO Auto-generated method stub
      
   }
}
