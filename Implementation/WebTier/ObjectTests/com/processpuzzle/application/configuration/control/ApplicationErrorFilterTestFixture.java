package com.processpuzzle.application.configuration.control;

import hu.itkodex.litest.template.FilterTestEnvironment;
import hu.itkodex.litest.template.FilterTestFixture;

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
