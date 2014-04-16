package com.processpuzzle.user.session.control;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNot.not;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.assertThat;
import static org.junit.Assume.assumeThat;

import java.io.FileNotFoundException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.processpuzzle.application.domain.Application;
import com.processpuzzle.application.security.domain.User;
import com.processpuzzle.litest.template.MockServletRunner;
import com.processpuzzle.user.session.control.HttpSessionUserSessionHolder;
import com.processpuzzle.user.session.control.UserRequestContextCreator;
import com.processpuzzle.user_session.domain.UserRequestContext;
import com.processpuzzle.user_session.domain.UserRequestManager;
import com.processpuzzle.user_session.domain.UserSession;
import com.processpuzzle.user_session.domain.UserSessionHolder;

public class UserRequestContextCreatorTest {
   private MockServletRunner runner;
   private Application application;
   private UserRequestContextCreator requestCreatorFilter;
   private UserRequestManager requestManager;

   @Before public void beforeEachTests() throws FileNotFoundException {
      runner = new MockServletRunner();
      runner.setUp();
      runner.setFilter( UserRequestContextCreator.class );      
      
      requestCreatorFilter = (UserRequestContextCreator) runner.getFilter(  UserRequestContextCreator.class );
      application = runner.getApplication();

      requestManager = UserRequestManager.getInstance();
   }
   
   @Test public void init_shouldFindApplicationInServletContext() {
      assertThat( requestCreatorFilter.getRequestManager(), notNullValue() );
      assertThat( requestCreatorFilter.getApplication(), equalTo( application ));
   }
   
   @Test public void doFilter_shouldCreateNewUserSessionIfThereIsNoOne() {
      assumeThat( requestManager.currentUserSession(), is( nullValue() ));      
      assumeThat( requestManager.currentRequestContext(), is( nullValue() ));      
      assumeThat( requestManager.getApplicationInContext(), is( nullValue() ));      
      assumeThat( requestManager.getApplicationContext(), is( nullValue() ));      

      //EXCERCISE:
      runner.doFilter();
      
      //VERIFY:
      assertThat( requestManager.currentUserSession(), notNullValue() );
      assertThat( requestManager.currentRequestContext(), notNullValue() );
      assertThat( requestManager.currentUser(), equalTo( runner.getAnonymousUser() ));
      assertThat( requestManager.getApplicationInContext(), equalTo( runner.getApplication() ));
      assertThat( requestManager.getApplicationContext(), equalTo( runner.getApplication().getContext() ));
      assertThat( (UserSession) runner.getSession().getAttribute( HttpSessionUserSessionHolder.USER_SESSION ), equalTo( requestManager.currentUserSession() ));

      //TEARDOWN:
      requestManager.destroySession( requestManager.currentUserSession() );
   }
   
   @Test public void doFilter_shouldReuseExistingUserSession() {
      //SETUP:
      UserRequestContext existingUserRequestContext = createNewUserSessionAndRequestContext();
      UserSession existingSession = existingUserRequestContext.getUserSession();
      User existingUser = runner.getMockUser();
      
      //EXCERCISE:
      runner.doFilter();
      
      //VERIFY:
      assertThat( requestManager.currentUserSession(), equalTo( existingSession ));
      assertThat( requestManager.currentUser(), equalTo( existingUser ));
   }

   @Test public void doFilter_shouldCreateNewRequestEvenIfExists() {
      //SETUP:
      UserRequestContext existingUserRequestContext = createNewUserSessionAndRequestContext();
      
      //EXCERCISE:
      runner.doFilter();
      
      //VERIFY:
      assertThat( requestManager.currentRequestContext(), not( equalTo( existingUserRequestContext )));
   }
   
   @After public void afterEachTests() {
      runner.tearDown();
      runner = null;
      requestManager = null;
   }

   //Private helper methods
   private UserRequestContext createNewUserSessionAndRequestContext() {
      UserSessionHolder sessionHolder = new HttpSessionUserSessionHolder( runner.getRequest() );
      requestManager.createSession( sessionHolder, runner.getMockUser(), application );
      UserRequestContext existingUserReqestContext = requestManager.createRequestContext( sessionHolder );
      return existingUserReqestContext;
   }
}
