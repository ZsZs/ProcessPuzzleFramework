package com.processpuzzle.application.domain;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assume.assumeThat;

import org.junit.Test;

public class ErroneousApplicationTest extends ApplicationTest<Application, InstalledAndStoppedApplicationFixture> {

   @Override
   public void beforeAllTests() {
      super.beforeAllTests();
      
      assumeThat( sut.getInstallationStatus(), equalTo( Application.InstallationStatus.installed ) );
      assumeThat( sut.getExecutionStatus(), equalTo( Application.ExecutionStatus.stopped ) );

      // SETUP:
      sut.configurationDescriptorPath = "classpath:Dummy/Configuration.xml";
   }

   @Test( expected = ApplicationStartException.class )
   public void start_ThrowsExceptionWhenConfigurationIsInvalid() throws ApplicationException {
      sut.start();
   }

   @Override
   public void testStart() throws ApplicationException {
   // Prevent running superclass test with erroneous application configuration.
   }

   @Override
   public void testStop() {
   // Prevent running superclass test with erroneous application configuration.
   }

   @Override
   public void testLoginUser() throws ApplicationException {
   // Prevent running superclass test with erroneous application configuration.
   }   
}
