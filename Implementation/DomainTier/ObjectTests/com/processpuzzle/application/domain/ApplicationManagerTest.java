package com.processpuzzle.application.domain;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assume.assumeThat;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.ResourceLoader;

import com.processpuzzle.application.domain.Application.ExecutionStatus;
import com.processpuzzle.application.domain.Application.InstallationStatus;
import com.processpuzzle.commons.persistence.RepositoryResultSet;
import com.processpuzzle.litest.template.DefaultApplicationFixture;
import com.processpuzzle.sharedfixtures.domaintier.DomainTierTestConfiguration;

public class ApplicationManagerTest {
   private static final String NEW_APPLICATION_NAME = InstallationTestApplication.class.getSimpleName();
   private static final String APPLICATION_REPOSITOTRY_STORAGE_PATH = DomainTierTestConfiguration.APPLICATION_REPOSITORY_STORAGE_PATH;
   private static final String CONFIGURATION_DESCRIPTOR_PATH = DomainTierTestConfiguration.APPLICATION_CONFIGURATION_DESCRIPTOR_PATH;
   private Application alreadyInstalledApplication;
   private ApplicationManager applicationManager;
   private ApplicationRepository applicationRepository;
   private DefaultApplicationFixture<Application> installedApplicationFixture;
   private Application newApplication;
   private ResourceLoader resourceLoader;

   @Before
   public void beforEachTests() throws InstantiationException {
      resourceLoader = new DefaultResourceLoader();
      applicationManager = new ApplicationManager( APPLICATION_REPOSITOTRY_STORAGE_PATH, resourceLoader );
      
      installedApplicationFixture = new InstalledAndStoppedApplicationFixture();
      installedApplicationFixture.setUp();
      
      instantiateApplicationRepository();
   }

   @After
   public void afterEachTests() {
      installedApplicationFixture.tearDown();
      deleteAllApplicationsFromRepository();
      newApplication = null;
      alreadyInstalledApplication = null;
      applicationManager = null;
   }
   
   @Ignore @Test
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

   @Test
   public void install_OnlyStartsApplicationIfAlreadyInstalled() throws ApplicationInstallationException {
      // EXCERCISE:
      assumeThat( applicationIsInRepository( installedApplicationFixture.getApplicationName() ), is( true ) );
      assumeThat( applicationStatusInRepository( installedApplicationFixture.getApplicationName() ), equalTo( ExecutionStatus.stopped ) );
      alreadyInstalledApplication = applicationManager.install( installedApplicationFixture.getApplicationName(), AlreadyInstalledApplication.class, CONFIGURATION_DESCRIPTOR_PATH );

      // VERIFY:
      assertThat( alreadyInstalledApplication.getExecutionStatus(), equalTo( ExecutionStatus.running ) );
      assertThat( applicationStatusInRepository( installedApplicationFixture.getApplicationName() ), equalTo( ExecutionStatus.running ) );
   }

   @Test
   public void start_UpdatesApplicationStatusInRepository() throws ApplicationException, ConfusingApplicationStatusException {
      // EXCERCISE:
      assumeThat( applicationStatusInRepository( installedApplicationFixture.getApplicationName() ), equalTo( ExecutionStatus.stopped ) );
      alreadyInstalledApplication = applicationManager.start( installedApplicationFixture.getApplicationName() );

      // VERIFY:
      assertThat( alreadyInstalledApplication.getExecutionStatus(), equalTo( ExecutionStatus.running ) );
      assertThat( applicationStatusInRepository( installedApplicationFixture.getApplicationName() ), equalTo( ExecutionStatus.running ) );
   }

   @Test( expected = ConfusingApplicationStatusException.class )
   public void start_ThrowsExceptionWhenApplicationIsNotInstalled() throws ApplicationException, ConfusingApplicationStatusException {
      applicationManager.start( NEW_APPLICATION_NAME );
   }

   @Test
   public void stop_UpdatesApplicationStatusInReposiotry() throws ApplicationException, ConfusingApplicationStatusException {
      // EXCERCISE:
      alreadyInstalledApplication = applicationManager.start( installedApplicationFixture.getApplicationName() );
      applicationManager.stop( alreadyInstalledApplication );

      // VERIFY:
      assertThat( alreadyInstalledApplication.getExecutionStatus(), equalTo( ExecutionStatus.stopped ) );
      assertThat( applicationStatusInRepository( installedApplicationFixture.getApplicationName() ), equalTo( ExecutionStatus.stopped ) );
   }

   // Private helper methods
   private boolean applicationIsInRepository( String applicationName ) {
      if( applicationRepository.findByName( applicationName ) != null )
         return true;
      else
         return false;
   }

   private ExecutionStatus applicationStatusInRepository( String applicationName ) {
      Application application = applicationRepository.findByName( applicationName );
      return application.getExecutionStatus();
   }

   private void deleteAllApplicationsFromRepository() {
      RepositoryResultSet<Application> applications = applicationRepository.findAll( null );
      for( Application application : applications ){
         applicationRepository.delete( application );
      }
   }

   private void instantiateApplicationRepository() {
      try{
         applicationRepository = ApplicationRepository.getInstance( APPLICATION_REPOSITOTRY_STORAGE_PATH, resourceLoader );
      }catch( InstantiationException e ){
         e.printStackTrace();
      }
   }
   
   protected void openHsqlManager() {
      org.hsqldb.util.DatabaseManagerSwing.main(new String[] { "--url",  "jdbc:hsqldb:mem:mymemdb", "--noexit" });
   }
}
