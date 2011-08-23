package com.processpuzzle.user_session.domain;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;
import hu.itkodex.litest.testcase.GenericTestSuite;

import org.junit.After;
import org.junit.Test;

import com.processpuzzle.application.domain.Application;
import com.processpuzzle.application.security.domain.AnonymousUser;
import com.processpuzzle.application.security.domain.SystemAdministrator;
import com.processpuzzle.sharedfixtures.domaintier.RequestContextInMockedApplicationFixture;

public class UserRequestManagerTest extends GenericTestSuite<UserRequestContext, RequestContextInMockedApplicationFixture>{
   private static final String containerConfigurationPath = null;
   //private static RequestContextInMockedApplicationFixture requestFixture;
   
   protected UserRequestManagerTest() {
      super( containerConfigurationPath );
   }

   @Test public void createSession() {
      assertThat( fixture.getAnonymousUserSession(), not( nullValue() ));
      assertThat( fixture.getSessionHolder().getSession(), equalTo( fixture.getAnonymousUserSession() ));
      assertThat( fixture.getAnonymousUserSession().getUser(), instanceOf( AnonymousUser.class ) );
   }
   
   @Test public void destroyUserSession(){
      //Implicitly tested in 'afterEachTests()'
   }
   
   @Test public void createUserRequest() {
      assertThat( fixture.getAnonymousUserRequestContext(), not( nullValue() ));
      assertThat( fixture.getAnonymousUserRequestContext().getUserSession(), equalTo( fixture.getAnonymousUserSession() ));
      assertThat( fixture.getAnonymousUserRequestContext().getApplication(), equalTo( fixture.getApplication() ));
   }
   
   @Test public void createAdministratorSession() {
      //SETUP:
      Application application = fixture.getApplication();
      UserSessionHolder sessionHolder = fixture.getSessionHolder();
      
      //EXCERCISE:
      UserSession administratorSession = UserRequestManager.getInstance().createAdministratorSession( sessionHolder, application );
      
      //VERIFY:
      assertThat( administratorSession.getUser(), instanceOf( SystemAdministrator.class ));
      assertThat( administratorSession.getApplication(), equalTo( application ));
   }

   @Test public void destroyUserRequest() {
      //Implicitily tested in 'afterEachTests()'
   }
   
   @Test public void getCurrentUserRequest() {
      assertThat( UserRequestManager.getInstance().currentRequestContext(), equalTo( fixture.getAnonymousUserRequestContext() ));
   }
   
   @Test public void getApplicationInContext() {
      assertThat( UserRequestManager.getInstance().getApplicationInContext(), equalTo( fixture.getApplication() ));
   }
   
   @Test public void getApplicationContext() {
      assertThat( UserRequestManager.getInstance().getApplicationContext(), equalTo( fixture.getApplicationContext() ));
   }
   
   @After public void afterEachTests() {
      UserRequestManager.getInstance().desctroyRequestContext( fixture.getAnonymousUserRequestContext() );
      UserRequestManager.getInstance().destroySession( fixture.getAnonymousUserSession() );
   }
}
