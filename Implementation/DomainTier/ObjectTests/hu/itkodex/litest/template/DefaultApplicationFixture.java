package hu.itkodex.litest.template;

import hu.itkodex.litest.fixture.GenericTestFixture;

import com.processpuzzle.application.configuration.domain.ProcessPuzzleContext;
import com.processpuzzle.application.configuration.domain.PropertyContext;
import com.processpuzzle.application.configuration.domain.PropertyKeys;
import com.processpuzzle.application.domain.Application;
import com.processpuzzle.application.domain.ApplicationRepository;
import com.processpuzzle.sharedfixtures.domaintier.ApplicationFixture;
import com.processpuzzle.sharedfixtures.domaintier.DomainTierTestConfiguration;
import com.processpuzzle.user_session.domain.StaticUserSessionHolder;
import com.processpuzzle.user_session.domain.UserRequestContext;
import com.processpuzzle.user_session.domain.UserRequestManager;
import com.processpuzzle.user_session.domain.UserSessionHolder;

public abstract class DefaultApplicationFixture extends GenericTestFixture<Application> implements ApplicationFixture<Application> {
   public static final String CONFIGURATION_DESCRIPTOR_PATH_1 = "classpath:com/processpuzzle/sharedfixtures/domaintier/ApplicationOneDescriptor.xml";
   public static final String CONFIGURATION_DESCRIPTOR_PATH_2 = "classpath:com/processpuzzle/sharedfixtures/domaintier/ApplicationTwoDescriptor.xml";
   protected String configurationPath;
   protected ApplicationRepository applicationRepository;
   protected Application application;
   private UserRequestContext requestContext;
   private ProcessPuzzleContext applicationContext;

   protected DefaultApplicationFixture( String configurationPath ) {
      super();
      this.configurationPath = configurationPath;
   }
   
   //Properties
   public Application getApplication() { return application; }
   public ApplicationRepository getApplicationRepository() { return applicationRepository; }
   public ProcessPuzzleContext getApplicationContext() { return applicationContext; }
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
   protected Application instantiateSUT() {
      // TODO Auto-generated method stub
      return null;
   }

   @Override
   protected void releaseResources() {
      application = null;
      applicationRepository = null;
   }

}