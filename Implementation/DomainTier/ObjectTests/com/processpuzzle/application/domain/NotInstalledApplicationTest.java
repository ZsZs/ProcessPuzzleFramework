package com.processpuzzle.application.domain;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assume.assumeThat;

import org.junit.Test;

import com.processpuzzle.application.configuration.domain.ProcessPuzzleContext;
import com.processpuzzle.application.security.domain.PredefinedUser;
import com.processpuzzle.application.security.domain.UserRepository;
import com.processpuzzle.user_session.domain.UserRequestManager;

public class NotInstalledApplicationTest extends ApplicationTest<Application, NotInstalledApplicationFixture> {
   private ProcessPuzzleContext applicationContext;

   @Override
   public void beforeEachTest() {
      super.beforeEachTest();
      
      assumeThat( sut.getExecutionStatus(), equalTo( Application.ExecutionStatus.stopped ) );
      assumeThat( sut.getInstallationStatus(), equalTo( Application.InstallationStatus.notInstalled ) );
      
      try{
         sut.install();
         applicationContext = UserRequestManager.getInstance().getApplicationContext();
         userRepository = (UserRepository) applicationContext.getRepository( UserRepository.class );
      }catch( ApplicationException e ){
         e.printStackTrace();
      }
   }

   public void afterEachTests() throws Exception {
      sut.unInstall();
      
      assertThat( sut.getExecutionStatus(), equalTo( Application.ExecutionStatus.stopped ) );
      assertThat( sut.getInstallationStatus(), equalTo( Application.InstallationStatus.notInstalled ) );
   }
   
   @Test
   public void testInstall() throws ApplicationException {
      // SETUP: Implicit setup.

      // EXERCISE: See beforeEachTest()

      // VERIFY:
      assumeThat( sut.getExecutionStatus(), equalTo( Application.ExecutionStatus.running ) );
      assertThat( "Application calls ProcessPuzzle.setUp() to initialize context.", applicationContext.isConfigured(), is( true ) );

      assertThat( "Installation defines 'Anonymous' user.", userRepository.findUserByName( PredefinedUser.ANONYMOUS.getUserName() ), not( nullValue() ) );
      assertThat( "Installation defines 'Administrator' user.", userRepository.findUserByName( PredefinedUser.SYSTEM_ADMINISTRATOR.getUserName() ), not( nullValue() ) );

      assertThat( "Installation defines default user.", sut.authenticateUser( DEFAULT_USER_NAME, DEFAULT_USER_PASSWORD ), not( nullValue() ) );

      assertTrue( "Application stores in a history what had happend.", sut.getHistorySize() >= 1 );
      
      //TEAD DOWN:
      sut.stop();
   }

}
