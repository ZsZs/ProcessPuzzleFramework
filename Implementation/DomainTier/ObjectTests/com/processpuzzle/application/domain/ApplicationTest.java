package com.processpuzzle.application.domain;

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
   protected ApplicationRepository applicationRepository;
   protected UserRepository userRepository;
   protected ProcessPuzzleContext applicationContext;

   public ApplicationTest() {
      super( DomainTierTestConfiguration.FIXTURE_CONTAINER_DEFINITION_PATH );
   }

   @Override
   public void beforeEachTest() {
      super.beforeEachTest();
      try{
         determineApplicationRepository();
      }catch( InstantiationException e ){
         e.printStackTrace();
      }
   }

   //Protected, private helper methods
   protected void changeDefaultUserPassordTo(String newPassword) {
      User defaultUser = findDefaultUser();
      defaultUser.changePassword(newPassword);
      DefaultUnitOfWork work = new DefaultUnitOfWork(true);
      userRepository.update(work, defaultUser);
      work.finish();
   }

   protected void determineApplicationContext() {
      applicationContext = UserRequestManager.getInstance().getApplicationContext();
   }
   
   protected void determineApplicationRepository() throws InstantiationException{
      applicationRepository = ApplicationRepository.getInstance( DomainTierTestConfiguration.APPLICATION_REPOSITORY_STORAGE_PATH );      
   }

   protected void determineUserRepository() {
      determineApplicationContext();
      userRepository = (UserRepository) applicationContext.getRepository(UserRepository.class);
   }
   
   protected User findDefaultUser() {
      DefaultUnitOfWork work = new DefaultUnitOfWork(true);
      User defaultUser = userRepository.findUserByName(work, DEFAULT_USER_NAME);
      work.finish();
      return defaultUser;
   }

}
