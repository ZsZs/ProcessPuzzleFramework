package com.processpuzzle.application.domain;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assume.assumeThat;

import org.junit.Ignore;
import org.junit.Test;

import com.processpuzzle.application.configuration.domain.ProcessPuzzleContext;
import com.processpuzzle.application.security.domain.PredefinedUser;
import com.processpuzzle.application.security.domain.UserRepository;
import com.processpuzzle.user_session.domain.UserRequestManager;

public class NotInstalledApplicationTest extends ApplicationTest {
   
   @Ignore
   @Test
   public void testInstall() throws ApplicationException {
      assumeThat( application.getExecutionStatus(), equalTo( Application.ExecutionStatus.stopped ) );
      assumeThat( application.getInstallationStatus(), equalTo( Application.InstallationStatus.notInstalled ) );
      // SETUP: Implicit setup.

      // EXERCISE:
      application.install();
      ProcessPuzzleContext applicationContext = UserRequestManager.getInstance().getApplicationContext();
      userRepository = (UserRepository) applicationContext.getRepository( UserRepository.class );

      // VERIFY:
      assumeThat( application.getExecutionStatus(), equalTo( Application.ExecutionStatus.running ) );

      assertThat( "Installation also starts the application", application.getExecutionStatus(), equalTo( Application.ExecutionStatus.running ) );

      assertThat( "Application calls ProcessPuzzle.setUp() to initialize context.", applicationContext.isConfigured(), is( true ) );

      // assertThat("Installation runs all the data loaders.", application.getDataLoaders(), hasItem( isLoaded() ));

      assertThat( "Installation defines 'Anonymous' user.", userRepository.findUserByName( PredefinedUser.ANONYMOUS.getUserName() ), not( nullValue() ) );
      assertThat( "Installation defines 'Administrator' user.", userRepository.findUserByName( PredefinedUser.SYSTEM_ADMINISTRATOR.getUserName() ),
            not( nullValue() ) );

      assertThat( "Installation defines default user.", application.authenticateUser( DEFAULT_USER_NAME, DEFAULT_USER_PASSWORD ), not( nullValue() ) );

      assertTrue( "Application stores in a history what had happend.", application.getHistorySize() >= 1 );

      assertEquals( "The number of DataLoaders the Application executed is:", 2, application.findApplicationEventsByType( Application.Events.dataload ).size() );
      assertThat( "Data loaders can injected with constructor parameters.", TestDataLoaderWithConstructorArguments.getConstructorArgumentOne(),
            equalTo( "Hello World!" ) );

      // TEARDOWN:
      application.stop();
      application.unInstall();
   }

   @Override
   public void afterEachTests() throws Exception {
      assertThat( application.getExecutionStatus(), equalTo( Application.ExecutionStatus.stopped ) );
      assertThat( application.getInstallationStatus(), equalTo( Application.InstallationStatus.notInstalled ) );
   }
}
