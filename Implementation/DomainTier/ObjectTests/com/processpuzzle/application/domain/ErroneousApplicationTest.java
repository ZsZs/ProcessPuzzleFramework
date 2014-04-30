package com.processpuzzle.application.domain;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assume.assumeThat;

import org.junit.Test;

public class ErroneousApplicationTest extends ApplicationTest<Application, InstalledAndStoppedApplicationFixture> {

   @Override
   public void beforeEachTest() {
      super.beforeEachTest();
      
      assumeThat( sut.getInstallationStatus(), equalTo( Application.InstallationStatus.installed ) );
      assumeThat( sut.getExecutionStatus(), equalTo( Application.ExecutionStatus.stopped ) );

      // SETUP:
      sut.configurationDescriptorPath = "classpath:Dummy/Configuration.xml";
   }

   @Test( expected = ApplicationStartException.class )
   public void start_ThrowsExceptionWhenConfigurationIsInvalid() throws ApplicationException {
      sut.start();
   }
}
