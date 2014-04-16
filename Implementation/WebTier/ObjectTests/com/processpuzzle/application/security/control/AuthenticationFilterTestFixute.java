package com.processpuzzle.application.security.control;

import com.processpuzzle.application.security.control.AuthenticationFilter;
import com.processpuzzle.litest.template.FilterTestEnvironment;
import com.processpuzzle.litest.template.FilterTestFixture;

public class AuthenticationFilterTestFixute extends FilterTestFixture<AuthenticationFilter> {

   public AuthenticationFilterTestFixute( FilterTestEnvironment<AuthenticationFilter, ?> testEnvironment ) {
      super( testEnvironment );
   }

   @Override protected void configureAfterSutInstantiation() {
   }

   @Override protected void configureBeforeSutInstantiation() {
   }

   @Override protected AuthenticationFilter instantiateSUT() {
      return null;
   }

   @Override protected void releaseResources() {
   }
}
