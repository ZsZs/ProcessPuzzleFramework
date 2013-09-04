package com.processpuzzle.application.domain;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assume.assumeThat;
import hu.itkodex.litest.template.DefaultApplicationFixture;

import org.junit.After;
import org.junit.Ignore;
import org.junit.Test;

import com.processpuzzle.application.configuration.domain.ProcessPuzzleContext;
import com.processpuzzle.application.security.domain.User;
import com.processpuzzle.application.security.domain.UserRepository;
import com.processpuzzle.litest.testcase.GenericTestSuite;
import com.processpuzzle.persistence.domain.DefaultUnitOfWork;
import com.processpuzzle.sharedfixtures.domaintier.DomainTierTestConfiguration;
import com.processpuzzle.user_session.domain.UserRequestManager;

public abstract class ApplicationTest extends GenericTestSuite<Application, DefaultApplicationFixture>{
   protected static final String DEFAULT_USER_NAME     = "ProcessPuzzle";
   protected static final String DEFAULT_USER_PASSWORD = "ProcessPuzzle";
   protected static DefaultApplicationFixture applicationFixture;
   protected Application application;
   protected UserRepository userRepository;

   public ApplicationTest() {
      super( DomainTierTestConfiguration.FIXTURE_CONTAINER_DEFINITION_PATH );
   }

   @Ignore
   @Test
   public void testStart() throws ApplicationException {
      //SETUP: implicit setup in beforeEachTests()
      assumeThat( application.getInstallationStatus(), equalTo( Application.InstallationStatus.installed ));
      assumeThat( application.getExecutionStatus(), equalTo( Application.ExecutionStatus.stopped ));
      
      //EXERCISE:
      application.start();
      ProcessPuzzleContext applicationContext = UserRequestManager.getInstance().getApplicationContext();
      userRepository = (UserRepository) applicationContext.getRepository(UserRepository.class);
      assertThat( application.getExecutionStatus(), equalTo( Application.ExecutionStatus.running ));

      //VERIFY:
      changeDefaultUserPassordTo( "bakfity" );
      assertEquals("start() doesn't reinitialize the database.", "bakfity", findDefaultUser().getPassword());
      
      //TEARDOWN:
      application.stop();
   }
   
   @Ignore
   @Test
   public void testStop() {
      //EXERCISE:
      application.stop();
      
      //VERIFY:
      ProcessPuzzleContext applicationContext = UserRequestManager.getInstance().getApplicationContext();
      assertThat( application.getExecutionStatus(), equalTo( Application.ExecutionStatus.stopped ));
      assertThat( "Stop tears down ProcessPuzzleContext.", applicationContext.isConfigured(), is( false ));
   }

   @Ignore
   @Test
   public void testLoginUser() throws ApplicationException {
      //SETUP: Implicit setup in 'beforeAllTests()'
      
      //EXERCISE:
      application.start();
      ProcessPuzzleContext applicationContext = UserRequestManager.getInstance().getApplicationContext();
      userRepository = (UserRepository) applicationContext.getRepository(UserRepository.class);
      
      //VERIFY:
      assertTrue("With the following account we can login.", application.loginUser("admin", "admin"));
      assertFalse("This account is unknown:", application.loginUser("bla", "bla"));
   }

   @After
   public void afterEachTests() throws Exception {
      userRepository = null;
      assumeThat( application.getInstallationStatus(), equalTo( Application.InstallationStatus.installed ));
      assumeThat( application.getExecutionStatus(), equalTo( Application.ExecutionStatus.stopped ));
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

      
   /*
    * @Ignore @Test public void loadSystemData () { //Application.loadSystemData (); UnitOfWork work = new UnitOfWork(true);
    * 
    * TestEntity anEntity = repository.findTestEntityByName(work, "test_entity_1"); assertNotNull("After loading test datas we can find the
    * 'test_entity_1' object.", anEntity); repository.deleteByName( work, "test_entity_1");
    * 
    * work.finish(); }
    * 
    * @Ignore @Test public void loadMigrationData () { //Application.loadMigrationData (); UnitOfWork work = new UnitOfWork(true);
    * 
    * TestEntity anEntity = repository.findTestEntityByName( work, "test_entity_2" ); assertNotNull("After loading test datas we can find the
    * 'test_entity_2' object.", anEntity); repository.deleteByName( work, "test_entity_2" );
    * 
    * work.finish(); }
    * 
    * @Ignore @Test public void loadTestData () { //Application.loadTestData (); UnitOfWork work = new UnitOfWork(true);
    * 
    * TestEntity anEntity = repository.findTestEntityByName(work, "test_entity_3"); assertNotNull("After loading test datas we can find the
    * 'test_entity_3' object.", anEntity); repository.deleteByName(work, "test_entity_3");
    * 
    * work.finish(); }
    */
}
