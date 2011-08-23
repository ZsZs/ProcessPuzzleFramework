package com.processpuzzle.application.domain;

import com.processpuzzle.persistence.query.domain.DefaultIdentityExpression;

public class BackupEvent extends ApplicationEvent {

   protected BackupEvent() {
      super();
      // TODO Auto-generated constructor stub
   }
   
   public BackupEvent(String desc){
	   super(desc);
   }

   @Override
   public DefaultIdentityExpression<?> getDefaultIdentity() {
      // TODO Auto-generated method stub
      return null;
   }

}
