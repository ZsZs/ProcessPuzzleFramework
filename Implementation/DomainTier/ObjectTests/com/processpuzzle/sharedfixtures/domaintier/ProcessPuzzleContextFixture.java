package com.processpuzzle.sharedfixtures.domaintier;

import static org.mockito.Mockito.*;

import com.processpuzzle.application.security.domain.AnonymousUser;
import com.processpuzzle.application.security.domain.User;
import com.processpuzzle.application.configuration.domain.ProcessPuzzleContext;
import com.processpuzzle.application.domain.Application;
import com.processpuzzle.litest.fixture.GenericTestFixture;
import com.processpuzzle.litest.fixture.ImmutableSharedFixture;
import com.processpuzzle.user_session.domain.StaticUserSessionHolder;
import com.processpuzzle.user_session.domain.UserRequestContext;
import com.processpuzzle.user_session.domain.UserRequestManager;
import com.processpuzzle.user_session.domain.UserSession;
import com.processpuzzle.user_session.domain.UserSessionHolder;

public class ProcessPuzzleContextFixture extends GenericTestFixture<ProcessPuzzleContext> implements ImmutableSharedFixture<ProcessPuzzleContext>{
   protected static ProcessPuzzleContextFixture soleInstance;
   protected Class<? extends Application> applicationClass;
   protected ProcessPuzzleContext applicationContext;
   protected Application mockApplication;
   protected String configurationDescriptorPath;
   private boolean isConfigured = false;
   private UserRequestManager requestManager;
   private AnonymousUser anonymousUser;
   private UserSession userSession;
   private UserRequestContext requestContext;
   
   protected ProcessPuzzleContextFixture( String configurationDescriptorPath ) {
      this.configurationDescriptorPath = configurationDescriptorPath;
   }
   
   public static ProcessPuzzleContextFixture getInstance() {
      return getInstance( DomainTierTestConfiguration.APPLICATION_CONFIGURATION_DESCRIPTOR_PATH );
   }
   
   public static ProcessPuzzleContextFixture getInstance( String configurationDescriptorPath ) {
      if( soleInstance == null ) {
         soleInstance = new ProcessPuzzleContextFixture( configurationDescriptorPath );
      }
      
      return soleInstance;
   }
   
   //Properties
   public ProcessPuzzleContext getApplicationContext() { return applicationContext; }
   public User getUser() { return anonymousUser; }
   public UserRequestContext getUserRequestContex() { return requestContext; }
   public UserRequestManager getUserRequestManger() { return requestManager; }
   public UserSession getUserSession() { return userSession; }

   //Protected, private helper methods
   protected void createNewUserRequest() {
      requestManager = UserRequestManager.getInstance();
      UserSessionHolder sessionHolder = new StaticUserSessionHolder();
      anonymousUser = mock( AnonymousUser.class );
      userSession = requestManager.createSession( sessionHolder, anonymousUser, mockApplication );
      requestContext = requestManager.createRequestContext( sessionHolder );
   }
   
   protected ProcessPuzzleContext instantiateSUT() {
      return new ProcessPuzzleContext( mockApplication, configurationDescriptorPath );
   }

   @Override
   protected void configureAfterSutInstantiation() {
      // TODO Auto-generated method stub
      
   }

   @Override
   protected void configureBeforeSutInstantiation() {
      if( !isConfigured ) {
         mockApplication = mock( Application.class );
         applicationContext = instantiateSUT();
         when( mockApplication.getContext() ).thenReturn( applicationContext );
         applicationContext.setUp( Application.Action.install );
      
         createNewUserRequest();
         isConfigured = true;
      }
   }

   @Override
   protected void releaseResources() {
      applicationContext.tearDown( Application.Action.uninstall );
      applicationContext = null;
      mockApplication = null;
      isConfigured = false;
   }
}
