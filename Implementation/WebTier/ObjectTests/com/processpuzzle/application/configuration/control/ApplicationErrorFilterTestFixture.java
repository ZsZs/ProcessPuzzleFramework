package com.processpuzzle.application.configuration.control;

import com.processpuzzle.litest.template.FilterTestEnvironment;
import com.processpuzzle.litest.template.FilterTestFixture;

public class ApplicationErrorFilterTestFixture extends FilterTestFixture<ApplicationErrorFilter> {

   public ApplicationErrorFilterTestFixture( FilterTestEnvironment<ApplicationErrorFilter, ?> testEnvironment ) {
      super( testEnvironment );
   }

   @Override protected void configureBeforeSutInstantiation() {
   }

   @Override protected void configureAfterSutInstantiation() {
   }

   @Override protected ApplicationErrorFilter instantiateSUT() {
      return null;
   }

   @Override protected void releaseResources() {
   }
}
