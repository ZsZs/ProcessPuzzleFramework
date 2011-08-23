package com.processpuzzle.sharedfixtures.domaintier;

import com.processpuzzle.application.configuration.domain.ProcessPuzzleContext;
import com.processpuzzle.application.domain.Application;
import com.processpuzzle.application.security.domain.User;
import com.processpuzzle.application.security.domain.UserRepository;
import com.processpuzzle.user_session.domain.StaticUserSessionHolder;
import com.processpuzzle.user_session.domain.UserRequestManager;
import com.processpuzzle.user_session.domain.UserSessionHolder;

public class TestUserRequestFixture {
   private static TestUserRequestFixture soleInstance;
   private static boolean isConfigured = false;
   private Application application;
   private ProcessPuzzleContext applicationContext;
   private boolean saveUser;
   
   protected TestUserRequestFixture( Application application, boolean saveUser ) {
      super();
      this.application = application;
      this.applicationContext = application.getContext();
   }
   
   public static TestUserRequestFixture getInstance(  Application application ) {
      return getInstance( application, false );
   }
   
   public static TestUserRequestFixture getInstance(  Application application, boolean saveUser ) {
      if( !isConfigured ) {
         soleInstance = new TestUserRequestFixture( application, saveUser );
      }
      
      return soleInstance;
   }

   public void setUp() {
      User testUser = new User( "ProcessPuzzleTestUser", "ProcessPuzzle" );
      
      if( saveUser ) {
         UserRepository userRepository = applicationContext.getRepository( UserRepository.class );
         userRepository.add( testUser );         
      }
      
      UserRequestManager requestManager = UserRequestManager.getInstance();
      UserSessionHolder sessionHolder = new StaticUserSessionHolder();
      requestManager.createSession( sessionHolder, testUser, application );
      requestManager.createRequestContext( sessionHolder );
      
      isConfigured = true;
   }
   
   public void tearDown() {
      isConfigured = false;
   }
   
}
