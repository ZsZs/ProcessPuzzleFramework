package com.processpuzzle.application.security.domain;

import com.processpuzzle.application.configuration.domain.ProcessPuzzleContext;
import com.processpuzzle.application.resource.domain.HardCodedDataLoader;
import com.processpuzzle.persistence.domain.DefaultUnitOfWork;

public class PredefinedUsersLoader extends HardCodedDataLoader {
   ProcessPuzzleContext applicationContext;
   
   public PredefinedUsersLoader() {
      super( );
      this.resultInPersistentObjects = true;
   }
   
   @Override
   public void loadData() {
      createPredefinedUsers();
      super.loadData();
   }

   //Properties
   public void setApplicationContext( ProcessPuzzleContext applicationContext ) {
      this.applicationContext = applicationContext;      
   }
   
   //Protected, private heleper methods
   private void createPredefinedUsers() {
      UserFactory userFactory = applicationContext.getEntityFactory( UserFactory.class );
      User defaultUser = userFactory.createUser( applicationContext.getPropertyContext().getDefaultUserName(), applicationContext.getPropertyContext().getDefaultUserName() );
      User anonymousUser = userFactory.createUser( PredefinedUser.ANONYMOUS.getUserName(), PredefinedUser.ANONYMOUS.getPassword() );
      User administrator = userFactory.createUser( PredefinedUser.SYSTEM_ADMINISTRATOR.getUserName(), PredefinedUser.SYSTEM_ADMINISTRATOR.getPassword() );
      
      UserRepository repository = (UserRepository) applicationContext.getRepository( UserRepository.class );
      
      DefaultUnitOfWork work = new DefaultUnitOfWork(true);
      repository.addUser( work, defaultUser );
      repository.addUser( work, anonymousUser );
      repository.addUser( work, administrator );
      work.finish();
   }
}
