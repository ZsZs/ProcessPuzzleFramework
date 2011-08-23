package com.processpuzzle.application.domain;

import hu.itkodex.commons.persistence.AggregateRoot;

import java.util.Date;

import com.processpuzzle.fundamental_types.domain.GenericEntity;
import com.processpuzzle.fundamental_types.domain.TimePoint;
import com.processpuzzle.persistence.query.domain.DefaultIdentityExpression;

public abstract class ApplicationEvent extends GenericEntity<ApplicationEvent> implements AggregateRoot {
   protected static Application.Events eventName = null;
   protected String eventDescription = "";
   protected TimePoint occuredOn = null;
   
   ApplicationEvent(String desc) {
      occuredOn = new TimePoint( new Date( System.currentTimeMillis() ));
      eventDescription=desc;
   }
   protected ApplicationEvent(){}
   
   public @Override @SuppressWarnings("unchecked") DefaultIdentityExpression getDefaultIdentity() {
      // TODO Auto-generated method stub
      return null;
   }

//Public accessors
   public String toString() { return eventDescription; }

//Properties
   public Application.Events getName() { return eventName; }
   public TimePoint getOccuredOn() { return occuredOn; }
   
//Protected, private helper methods
   protected @Override void defineIdentityExpressions() {
      // TODO Auto-generated method stub      
   }
}
