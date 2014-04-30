package com.processpuzzle.litest.template;


import com.processpuzzle.application.configuration.domain.ProcessPuzzleContext;
import com.processpuzzle.application.configuration.domain.PropertyContext;
import com.processpuzzle.application.configuration.domain.PropertyKeys;
import com.processpuzzle.application.domain.Application;
import com.processpuzzle.application.domain.ApplicationRepository;
import com.processpuzzle.litest.fixture.GenericTestFixture;
import com.processpuzzle.sharedfixtures.domaintier.ApplicationFixture;
import com.processpuzzle.sharedfixtures.domaintier.DomainTierTestConfiguration;
import com.processpuzzle.user_session.domain.StaticUserSessionHolder;
import com.processpuzzle.user_session.domain.UserRequestContext;
import com.processpuzzle.user_session.domain.UserRequestManager;
import com.processpuzzle.user_session.domain.UserSessionHolder;

public abstract class DefaultApplicationFixture<A extends Application> extends GenericTestFixture<A> implements ApplicationFixture<A> {
   public static final String CONFIGURATION_DESCRIPTOR_PATH_1 = "classpath:com/processpuzzle/sharedfixtures/domaintier/ApplicationOneDescriptor.xml";
   public static final String CONFIGURATION_DESCRIPTOR_PATH_2 = "classpath:com/processpuzzle/sharedfixtures/domaintier/ApplicationTwoDescriptor.xml";
   public static final String SYSTEM_ADMINISTRATION_ARTIFACT_TYPE_GROUP = "SystemAdministration";
   protected String configurationPath;
   protected ApplicationRepository applicationRepository;
   protected Application application;
   private UserRequestContext requestContext;
   private ProcessPuzzleContext applicationContext;

   public DefaultApplicationFixture( String configurationPath ) {
      super();
      this.configurationPath = configurationPath;
   }
   
   //Properties
   public Application getApplication() { return application; }
   public ProcessPuzzleContext getApplicationContext() { return applicationContext; }
   public String getApplicationName() { return application.getApplicationName(); }
   public ApplicationRepository getApplicationRepository() { return applicationRepository; }
   public UserRequestContext getUserRequestContext() { return requestContext; }
   

   //Protected, private helper methods.
   @Override
   protected void configureAfterSutInstantiation() {
      createNewUserRequest();    
      
      String applicationRepositoryStoragePath = determineApplicationRepositoryStoragePath();
      try {
         applicationRepository = ApplicationRepository.getInstance( applicationRepositoryStoragePath );
      } catch( InstantiationException e ) {
         e.printStackTrace();
      }         
   }

   //Protected, private helper methods
   protected void createNewUserRequest() {
      UserRequestManager requestManager = UserRequestManager.getInstance();
      UserSessionHolder sessionHolder = new StaticUserSessionHolder();
      requestContext = requestManager.createRequestContext( sessionHolder );
   }

   protected String determineApplicationRepositoryStoragePath() {
      String applicationStoragePath = null;
      applicationContext = UserRequestManager.getInstance().getApplicationContext();
      if( applicationContext != null ) {
         PropertyContext propertyContext = applicationContext.getPropertyContext();
         String key = PropertyKeys.APPLICATION_REPOSITORY_PATH.getDefaultKey();
         applicationStoragePath = propertyContext.getProperty( key );;
      }
      if( applicationStoragePath != null ) return applicationStoragePath;
      else return DomainTierTestConfiguration.APPLICATION_REPOSITORY_STORAGE_PATH;
   }

   @Override
   protected void releaseResources() {
      application = null;
      applicationRepository = null;
   }
}
