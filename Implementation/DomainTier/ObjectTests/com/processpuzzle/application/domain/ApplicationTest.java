package com.processpuzzle.application.domain;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assume.assumeThat;

import org.junit.After;
import org.junit.Test;

import com.processpuzzle.application.configuration.domain.ProcessPuzzleContext;
import com.processpuzzle.application.security.domain.User;
import com.processpuzzle.application.security.domain.UserRepository;
import com.processpuzzle.litest.template.DefaultApplicationFixture;
import com.processpuzzle.litest.testcase.GenericTestSuite;
import com.processpuzzle.persistence.domain.DefaultUnitOfWork;
import com.processpuzzle.sharedfixtures.domaintier.DomainTierTestConfiguration;
import com.processpuzzle.user_session.domain.UserRequestManager;

public abstract class ApplicationTest<A extends Application, F extends DefaultApplicationFixture<A>> extends GenericTestSuite<A, F>{
   protected static final String DEFAULT_USER_NAME     = "ProcessPuzzle";
   protected static final String DEFAULT_USER_PASSWORD = "ProcessPuzzle";
   protected F applicationFixture;
   protected UserRepository userRepository;

   public ApplicationTest() {
      super( DomainTierTestConfiguration.FIXTURE_CONTAINER_DEFINITION_PATH );
   }

   @Test
   public void testStart() throws ApplicationException {
      //SETUP: implicit setup in beforeEachTests()
      assumeThat( sut.getInstallationStatus(), equalTo( Application.InstallationStatus.installed ));
      assumeThat( sut.getExecutionStatus(), equalTo( Application.ExecutionStatus.stopped ));
      
      //EXERCISE:
      sut.start();
      ProcessPuzzleContext applicationContext = UserRequestManager.getInstance().getApplicationContext();
      userRepository = (UserRepository) applicationContext.getRepository(UserRepository.class);
      assertThat( sut.getExecutionStatus(), equalTo( Application.ExecutionStatus.running ));

      //VERIFY:
      changeDefaultUserPassordTo( "bakfity" );
      assertEquals("start() doesn't reinitialize the database.", "bakfity", findDefaultUser().getPassword());
      
      //TEARDOWN:
      sut.stop();
   }
   
   @Test
   public void testStop() {
      //EXERCISE:
      sut.stop();
      
      //VERIFY:
      ProcessPuzzleContext applicationContext = UserRequestManager.getInstance().getApplicationContext();
      assertThat( sut.getExecutionStatus(), equalTo( Application.ExecutionStatus.stopped ));
      assertThat( "Stop tears down ProcessPuzzleContext.", applicationContext.isConfigured(), is( false ));
   }

   @Test
   public void testLoginUser() throws ApplicationException {
      //SETUP: Implicit setup in 'beforeAllTests()'
      
      //EXERCISE:
      sut.start();
      ProcessPuzzleContext applicationContext = UserRequestManager.getInstance().getApplicationContext();
      userRepository = (UserRepository) applicationContext.getRepository(UserRepository.class);
      
      //VERIFY:
      assertTrue("With the following account we can login.", sut.loginUser("admin", "admin"));
      assertFalse("This account is unknown:", sut.loginUser("bla", "bla"));
   }

   @After
   public void afterEachTests() throws Exception {
      userRepository = null;
      assumeThat( sut.getInstallationStatus(), equalTo( Application.InstallationStatus.installed ));
      assumeThat( sut.getExecutionStatus(), equalTo( Application.ExecutionStatus.stopped ));
   }

   private User findDefaultUser() {
      DefaultUnitOfWork work = new DefaultUnitOfWork(true);
      User defaultUser = userRepository.findUserByName(work, DEFAULT_USER_NAME);
      work.finish();
      return defaultUser;
   }

   private void changeDefaultUserPassordTo(String newPassword) {
      User defaultUser = findDefaultUser();
      defaultUser.changePassword(newPassword);
      DefaultUnitOfWork work = new DefaultUnitOfWork(true);
      userRepository.update(work, defaultUser);
      work.finish();
   }

}
