package com.processpuzzle.application.configuration.domain;

import org.slf4j.Logger;

import com.processpuzzle.application.domain.Application;
import com.processpuzzle.application.domain.SingletonRegistry;

public class TestPersistentContext extends PersistentApplicationContext {

   public TestPersistentContext( Application application ) {
      super( application );
   }
   
   public static TestPersistentContext getInstance() {
      return (TestPersistentContext) SingletonRegistry.getInstance( TestPersistentContext.class );
   }
   
   //public mutator methods
   @Override public void setUp( Application.Action applicationAction ) {
      super.setUp( applicationAction );
   }

   //Properties
   public void setLog( Logger log ) { PersistentApplicationContext.logger = log; }
   
   
   //Protected, private helper methods
   @Override
   protected void setUpPersistentComponents() {
   // TODO Auto-generated method stub

   }

   @Override
   protected void setUpTransientComponents() {
   // TODO Auto-generated method stub

   }

   @Override
   protected void tearDownPersistentComponents() {
   // TODO Auto-generated method stub

   }

   @Override
   protected void tearDownTransientComponents() {
   // TODO Auto-generated method stub

   }

}
