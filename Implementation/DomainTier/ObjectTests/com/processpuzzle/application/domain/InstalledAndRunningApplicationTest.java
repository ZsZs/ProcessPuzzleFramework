package com.processpuzzle.application.domain;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assume.assumeThat;

import org.junit.Test;

public class InstalledAndRunningApplicationTest extends ApplicationTest<Application, InstalledAndStoppedApplicationFixture> {

   @Override
   public void beforeEachTest() {
      super.beforeEachTest();
      try{
         sut.start();
      }catch( ApplicationException e ){
         e.printStackTrace();
      }
      
      assumeThat( sut.getInstallationStatus(), equalTo( Application.InstallationStatus.installed ));
      assumeThat( sut.getExecutionStatus(), equalTo( Application.ExecutionStatus.running ));
   }

   @Test( expected = ReinstallationException.class )
   public void install_whenApplicationAlreadyInstalled_throwsException() throws ApplicationException {
      sut.install();
   }
   
   @Test
   public void start_whenApplicationIsRunning_doesNothing() throws ApplicationException {
      sut.start();
      
      assertThat( sut.getExecutionStatus(), equalTo( Application.ExecutionStatus.running ));
   }

   @Test
   public void stop_setsExecutionStatusToStopped() {
      sut.stop();
      
      //VERIFY:
      assertThat( sut.getExecutionStatus(), equalTo( Application.ExecutionStatus.stopped ));
   }

   @Test public void stop_tearDownApplicationContext(){
      sut.stop();
      
      assertThat( sut.applicationContext, nullValue() );
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

   @Test
   public void loginUser() throws ApplicationException {
      //VERIFY:
      assertThat( sut.loginUser( "admin", "admin" ), is( true ));
      assertThat( sut.loginUser("bla", "bla"), is( false ));
   }
}
