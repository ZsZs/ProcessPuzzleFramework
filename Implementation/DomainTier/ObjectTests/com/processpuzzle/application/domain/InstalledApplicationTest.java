package com.processpuzzle.application.domain;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Ignore;
import org.junit.Test;

public class InstalledApplicationTest extends ApplicationTest<Application, ConfigurableApplicationFixture> {

   @Ignore
   @Test( expected = ReinstallationException.class )
   public void install_ThrowsExceptionWhenAlreadyInstalled() throws ApplicationException {
      assertThat( sut.getInstallationStatus(), equalTo( Application.InstallationStatus.installed ) );
      assertThat( sut.getExecutionStatus(), equalTo( Application.ExecutionStatus.stopped ) );

      sut.install();
   }
}
