package com.processpuzzle.application.domain;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.junit.Assume.*;
import static org.hamcrest.core.IsEqual.*;
import static org.hamcrest.core.Is.*;

import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.ResourceLoader;

import com.processpuzzle.application.domain.Application.ExecutionStatus;
import com.processpuzzle.application.domain.Application.InstallationStatus;
import com.processpuzzle.sharedfixtures.domaintier.DomainTierTestConfiguration;

public class ApplicationManagerTest {
   private static final String NEW_APPLICATION_NAME = InstallationTestApplication.class.getSimpleName();
   private static final String ALREADY_INSTALLED_APPLICATION_NAME = AlreadyInstalledApplication.class.getSimpleName();
   private static final String APPLICATION_REPOSITOTRY_STORAGE_PATH = DomainTierTestConfiguration.APPLICATION_REPOSITORY_STORAGE_PATH;
   private static final String CONFIGURATION_DESCRIPTOR_PATH = DomainTierTestConfiguration.APPLICATION_CONFIGURATION_DESCRIPTOR_PATH;
   private ResourceLoader resourceLoader;
   private ApplicationManager applicationManager;
   private Application newApplication;
   private Application alreadyInstalledApplication;

   @Before
   public void beforEachTests() throws InstantiationException {
      resourceLoader = new DefaultResourceLoader();
      applicationManager = new ApplicationManager( APPLICATION_REPOSITOTRY_STORAGE_PATH, resourceLoader );
   }

   @Ignore
   @Test
   public void install_InstallsApplication() throws ApplicationInstallationException, ApplicationUninstallationException {
      // EXCERCISE:
      assumeThat( applicationIsInRepository( NEW_APPLICATION_NAME ), is( false ) );
      newApplication = applicationManager.install( NEW_APPLICATION_NAME, InstallationTestApplication.class, CONFIGURATION_DESCRIPTOR_PATH );

      // VERIFY:
      assertThat( newApplication.getExecutionStatus(), equalTo( ExecutionStatus.running ) );
      assertThat( newApplication.getInstallationStatus(), equalTo( InstallationStatus.installed ) );
      assertThat( applicationIsInRepository( NEW_APPLICATION_NAME ), is( true ) );

      // TEAR DOWN:
      applicationManager.unInstall( newApplication );
      assumeThat( applicationIsInRepository( NEW_APPLICATION_NAME ), is( false ) );
   }

   @Ignore
   @Test
   public void install_OnlyStartsApplicationIfAlreadyInstalled() throws ApplicationInstallationException {
      // EXCERCISE:
      assumeThat( applicationIsInRepository( ALREADY_INSTALLED_APPLICATION_NAME ), is( true ) );
      assumeThat( applicationStatusInRepository( ALREADY_INSTALLED_APPLICATION_NAME ), equalTo( ExecutionStatus.stopped ) );
      alreadyInstalledApplication = applicationManager.install( ALREADY_INSTALLED_APPLICATION_NAME, AlreadyInstalledApplication.class,
            CONFIGURATION_DESCRIPTOR_PATH );

      // VERIFY:
      assertThat( alreadyInstalledApplication.getExecutionStatus(), equalTo( ExecutionStatus.running ) );
      assertThat( applicationStatusInRepository( ALREADY_INSTALLED_APPLICATION_NAME ), equalTo( ExecutionStatus.running ) );
   }

   @Ignore
   @Test
   public void start_UpdatesApplicationStatusInRepository() throws ApplicationException, ConfusingApplicationStatusException {
      // EXCERCISE:
      assumeThat( applicationStatusInRepository( ALREADY_INSTALLED_APPLICATION_NAME ), equalTo( ExecutionStatus.stopped ) );
      alreadyInstalledApplication = applicationManager.start( ALREADY_INSTALLED_APPLICATION_NAME );

      // VERIFY:
      assertThat( alreadyInstalledApplication.getExecutionStatus(), equalTo( ExecutionStatus.running ) );
      assertThat( applicationStatusInRepository( ALREADY_INSTALLED_APPLICATION_NAME ), equalTo( ExecutionStatus.running ) );
   }

   @Test( expected = ConfusingApplicationStatusException.class )
   public void start_ThrowsExceptionWhenApplicationIsNotInstalled() throws ApplicationException, ConfusingApplicationStatusException {
      applicationManager.start( NEW_APPLICATION_NAME );
   }

   @Ignore
   @Test
   public void stop_UpdatesApplicationStatusInReposiotry() throws ApplicationException, ConfusingApplicationStatusException {
      // EXCERCISE:
      alreadyInstalledApplication = applicationManager.start( ALREADY_INSTALLED_APPLICATION_NAME );
      applicationManager.stop( alreadyInstalledApplication );

      // VERIFY:
      assertThat( alreadyInstalledApplication.getExecutionStatus(), equalTo( ExecutionStatus.stopped ) );
      assertThat( applicationStatusInRepository( ALREADY_INSTALLED_APPLICATION_NAME ), equalTo( ExecutionStatus.stopped ) );
   }

   @After
   public void afterEachTests() {
      newApplication = null;
      alreadyInstalledApplication = null;
      applicationManager = null;
   }

   // Private helper methods
   private boolean applicationIsInRepository( String applicationName ) {
      ApplicationRepository applicationRepository = null;
      try{
         applicationRepository = ApplicationRepository.getInstance( APPLICATION_REPOSITOTRY_STORAGE_PATH, resourceLoader );
      }catch( InstantiationException e ){
         e.printStackTrace();
      }
      if( applicationRepository.findByName( applicationName ) != null )
         return true;
      else
         return false;
   }

   private ExecutionStatus applicationStatusInRepository( String applicationName ) {
      ApplicationRepository applicationRepository = null;
      try{
         applicationRepository = ApplicationRepository.getInstance( APPLICATION_REPOSITOTRY_STORAGE_PATH, resourceLoader );
      }catch( InstantiationException e ){
         e.printStackTrace();
      }
      Application application = applicationRepository.findByName( applicationName );
      return application.getExecutionStatus();
   }
}
