package com.processpuzzle.user_session.domain;

import static org.hamcrest.core.IsNot.not;
import static org.hamcrest.core.IsNull.nullValue;
import static org.hamcrest.core.IsSame.sameInstance;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import org.mockito.MockitoAnnotations.Mock;

import com.processpuzzle.application.domain.Application;
import com.processpuzzle.application.security.domain.User;

public class UserSessionManagerTest {
   @Mock
   private static Application application;
   @Mock
   private static User mockUser;
   private static UserSession userSession;
   private UserSessionManager sessionManager;
   private UserRequestContextFactory requestContextFactory;
   private UserRequestContext newRequestContext;

   @BeforeClass
   public static void beforeAllTests() {
      MockitoAnnotations.initMocks( DefaultRequestContextTest.class );

      // userSession = StaticUserSessionHolder.getUserSession( mockUser );
   }

   @Before
   public void beforeEachTests() {
      requestContextFactory = new DefaultUserRequestContextFactory();
      sessionManager = new DefaultUserSessionManager( application, requestContextFactory );
      newRequestContext = sessionManager.createNewUserRequestContext( userSession );
   }

   @Test
   public void getUserRequestContextFactory() {
      assertThat( sessionManager.getUserRequestContextFactory(), sameInstance( requestContextFactory ) );
   }

   @Test
   public void createNewUserRequestContext() {
      assertThat( newRequestContext, not( nullValue() ) );
   }

   @Test
   public void getCurrentRequestContext() {
      assertThat( sessionManager.getCurrentUserRequestContext(), sameInstance( newRequestContext ) );
   }

   @Ignore
   @Test
   public void getCurrentUser() {
      assertThat( sessionManager.getCurrentUser(), sameInstance( mockUser ) );
   }
}
