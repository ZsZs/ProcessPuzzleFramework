package com.processpuzzle.application.domain;

import com.processpuzzle.persistence.query.domain.DefaultIdentityExpression;

public class ApplicationStartedEvent extends ApplicationEvent {
   protected String eventDescription = "Application started";
   
   ApplicationStartedEvent() {
      super();
   }

   @Override
   public DefaultIdentityExpression<?> getDefaultIdentity() {
      // TODO Auto-generated method stub
      return null;
   }
}
