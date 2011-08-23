package com.processpuzzle.application.security.control;

import com.processpuzzle.application.security.control.AuthenticationFilter;

import hu.itkodex.litest.template.FilterTestEnvironment;
import hu.itkodex.litest.template.FilterTestFixture;

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
