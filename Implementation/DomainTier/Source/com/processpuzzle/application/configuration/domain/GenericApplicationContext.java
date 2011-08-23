package com.processpuzzle.application.configuration.domain;


public abstract class GenericApplicationContext implements ApplicationContext {
   protected boolean isConfigured = false;

   protected void setUp() {
      isConfigured = true;
   }
   
   protected void tearDown() {
      isConfigured = false;
   }
   
   public boolean isConfigured() { return isConfigured; }
}
