package com.processpuzzle.application.domain;

import com.processpuzzle.persistence.query.domain.DefaultIdentityExpression;

public class RestoreEvent extends ApplicationEvent {

   public RestoreEvent() {
      super();
      // TODO Auto-generated constructor stub
   }

   @Override
   public DefaultIdentityExpression<?> getDefaultIdentity() {
      // TODO Auto-generated method stub
      return null;
   }

}
