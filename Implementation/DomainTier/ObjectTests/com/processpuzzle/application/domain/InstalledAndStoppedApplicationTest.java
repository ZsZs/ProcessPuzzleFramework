package com.processpuzzle.application.domain;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assume.assumeThat;

import org.junit.Test;

public class InstalledAndStoppedApplicationTest extends ApplicationTest<Application, InstalledAndStoppedApplicationFixture> {

   @Override
   public void beforeEachTest() {
      super.beforeEachTest();
      assumeThat( sut.getInstallationStatus(), equalTo( Application.InstallationStatus.installed ));
      assumeThat( sut.getExecutionStatus(), equalTo( Application.ExecutionStatus.stopped ));
      assumeThat( sut.applicationContext, nullValue());
   }

   @Test( expected = ReinstallationException.class )
   public void install_whenApplicationAlreadyInstalled_throwsException() throws ApplicationException {
      assertThat( sut.getInstallationStatus(), equalTo( Application.InstallationStatus.installed ) );
      assertThat( sut.getExecutionStatus(), equalTo( Application.ExecutionStatus.stopped ) );

      sut.install();
   }

   @Test
   public void start_setsExecutionStatusToRunning() throws ApplicationException {
      sut.start();
      determineUserRepository();

      assertThat( sut.getExecutionStatus(), equalTo( Application.ExecutionStatus.running ));
      
      //TEARDOWN:
      sut.stop();
   }

   @Test
   public void start_configuresApplicationsContext() throws ApplicationException {
      sut.start();
      determineUserRepository();

      assertThat( sut.applicationContext.isConfigured(), is( true ));
      
      //TEARDOWN:
      sut.stop();
   }
   
   @Test
   public void stop_whenApplicationIsStopped_doesNothing() throws ApplicationException {
      sut.stop();
      
      assertThat( sut.getExecutionStatus(), equalTo( Application.ExecutionStatus.stopped ));
   }
   
   @Test
   public void unInstall_setsExecutionStatusToStopped() throws ApplicationException {
      sut.unInstall();
      
      assertThat( sut.getExecutionStatus(), equalTo( Application.ExecutionStatus.stopped ));
   }
   
   @Test public void unInstall_setsInstallationStatusToUnInstalled() throws ApplicationException {
      sut.unInstall();
      
      assertThat( sut.getInstallationStatus(), equalTo( Application.InstallationStatus.notInstalled ));
   }
}
