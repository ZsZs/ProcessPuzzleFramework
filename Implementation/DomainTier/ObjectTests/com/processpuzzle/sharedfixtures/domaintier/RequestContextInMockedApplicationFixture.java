package com.processpuzzle.sharedfixtures.domaintier;

import static org.mockito.Mockito.*;


import org.mockito.Mock;

import com.processpuzzle.application.configuration.domain.PersistenceContext;
import com.processpuzzle.application.configuration.domain.ProcessPuzzleContext;
import com.processpuzzle.application.domain.Application;
import com.processpuzzle.application.security.domain.AnonymousUser;
import com.processpuzzle.application.security.domain.SystemAdministrator;
import com.processpuzzle.application.security.domain.UserRepository;
import com.processpuzzle.internalization.domain.ProcessPuzzleLocale;
import com.processpuzzle.litest.fixture.GenericTestFixture;
import com.processpuzzle.litest.fixture.TransientFreshFixture;
import com.processpuzzle.user_session.domain.StaticUserSessionHolder;
import com.processpuzzle.user_session.domain.UserRequestContext;
import com.processpuzzle.user_session.domain.UserRequestManager;
import com.processpuzzle.user_session.domain.UserSession;
import com.processpuzzle.user_session.domain.UserSessionHolder;

public class RequestContextInMockedApplicationFixture extends GenericTestFixture<UserRequestContext> implements TransientFreshFixture<UserRequestContext> {
   private static UserRequestManager requestManager;
   private static UserSessionHolder sessionHolder;
   @Mock private static UserRepository userRepository;
   @Mock private static AnonymousUser anonymousUser;
   @Mock private static SystemAdministrator systemAdministrator;
   @Mock private static Application application;
   @Mock private static ProcessPuzzleContext applicationContext;
   @Mock private static PersistenceContext persistenceContext;
   private UserRequestContext anonymousRequestContext;
   private UserRequestContext administratorRequestContext;
   private UserSession anonymousUserSession;
   private UserSession administratorSession;

   public RequestContextInMockedApplicationFixture() {
      super();
   }
   
   public UserSession getAnonymousUserSession() { return anonymousUserSession; }
   public UserSession getAdministratorSesssion() { return administratorSession; }
   public UserRequestContext getAnonymousUserRequestContext() { return anonymousRequestContext; }
   public UserRequestContext getAdministratorRequestContext() { return administratorRequestContext; }
   public Application getApplication() { return application; }
   public ProcessPuzzleContext getApplicationContext() { return applicationContext; }
   public UserSessionHolder getSessionHolder() { return sessionHolder; }
   public AnonymousUser getAnonymousUser() { return anonymousUser; }
   public SystemAdministrator getSystemAdministrator() { return systemAdministrator; }

   @Override
   protected void configureAfterSutInstantiation() {
   }

   @Override
   protected void configureBeforeSutInstantiation() {
      userRepository = mock( UserRepository.class );
      anonymousUser = mock( AnonymousUser.class );
      systemAdministrator = mock( SystemAdministrator.class );
      application = mock( Application.class );
      applicationContext = mock( ProcessPuzzleContext.class );
      persistenceContext = mock( PersistenceContext.class );
      
      when( anonymousUser.getPrefferedLocale() ).thenReturn( new ProcessPuzzleLocale( "hu", "HU" ) );
      when( application.getContext() ).thenReturn( applicationContext );
      when( applicationContext.getPersistenceContext() ).thenReturn( persistenceContext );
      when( persistenceContext.getRepositoryInstance( UserRepository.class )).thenReturn( userRepository );
      when( userRepository.findAnonymousUser() ).thenReturn( anonymousUser );
      when( userRepository.findSystemAdministrator() ).thenReturn( systemAdministrator );
      
      sessionHolder = new StaticUserSessionHolder();
      requestManager = UserRequestManager.getInstance();

      anonymousUserSession = requestManager.createSession( sessionHolder, anonymousUser, application );
      anonymousRequestContext = requestManager.createRequestContext( sessionHolder );
      
//      administratorSession = requestManager.createSession( sessionHolder, systemAdministrator );
//      administratorRequestContext = requestManager.createRequestContext( sessionHolder, application );
   }

   @Override
   protected UserRequestContext instantiateSUT() {
      return anonymousRequestContext;
   }

   @Override
   protected void releaseResources() {
      // TODO Auto-generated method stub
      
   }
}
