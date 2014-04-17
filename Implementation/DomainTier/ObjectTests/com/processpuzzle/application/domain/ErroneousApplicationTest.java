package com.processpuzzle.application.domain;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Ignore;
import org.junit.Test;

import com.processpuzzle.litest.fixture.TestFixture;
import com.processpuzzle.litest.testcase.NoSuchFixtureDefinitionException;

public class ErroneousApplicationTest extends ApplicationTest {

   @Ignore
   @Test( expected = ApplicationStartException.class )
   public void start_ThrowsExceptionWhenConfigurationIsInvalid() throws ApplicationException {
      assertThat( application.getInstallationStatus(), equalTo( Application.InstallationStatus.installed ) );
      assertThat( application.getExecutionStatus(), equalTo( Application.ExecutionStatus.stopped ) );

      // SETUP:
      application.configurationDescriptorPath = "Dummy Configuration";

      // EXERCISE:
      application.start();
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
