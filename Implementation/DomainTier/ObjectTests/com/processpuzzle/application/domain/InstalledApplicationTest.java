package com.processpuzzle.application.domain;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Ignore;
import org.junit.Test;

public class InstalledApplicationTest extends ApplicationTest {

   @Ignore
   @Test( expected = ReinstallationException.class )
   public void install_ThrowsExceptionWhenAlreadyInstalled() throws ApplicationException {
      assertThat( application.getInstallationStatus(), equalTo( Application.InstallationStatus.installed ) );
      assertThat( application.getExecutionStatus(), equalTo( Application.ExecutionStatus.stopped ) );

      application.install();
   }
}
