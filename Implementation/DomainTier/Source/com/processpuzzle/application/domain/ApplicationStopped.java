package com.processpuzzle.application.domain;

import com.processpuzzle.persistence.query.domain.DefaultIdentityExpression;

public class ApplicationStopped extends ApplicationEvent {

   public ApplicationStopped() {
      super();
      // TODO Auto-generated constructor stub
   }

   @Override
   public DefaultIdentityExpression<?> getDefaultIdentity() {
      // TODO Auto-generated method stub
      return null;
   }

}
