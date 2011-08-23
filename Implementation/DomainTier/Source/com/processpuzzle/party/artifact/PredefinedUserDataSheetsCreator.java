package com.processpuzzle.party.artifact;

import com.processpuzzle.application.configuration.domain.ProcessPuzzleContext;
import com.processpuzzle.application.resource.domain.HardCodedDataLoader;
import com.processpuzzle.application.security.domain.PredefinedUser;
import com.processpuzzle.application.security.domain.User;
import com.processpuzzle.application.security.domain.UserRepository;
import com.processpuzzle.persistence.domain.DefaultUnitOfWork;

public class PredefinedUserDataSheetsCreator extends HardCodedDataLoader {
   private ProcessPuzzleContext applicationContext;
   
   public PredefinedUserDataSheetsCreator( ProcessPuzzleContext applicationContext ) {
      super();
      this.applicationContext = applicationContext;
      this.resultInPersistentObjects = true;
   }
   

   public void loadData() {
      super.loadData();
      
      UserRepository repository = applicationContext.getRepository( UserRepository.class );
      DefaultUnitOfWork work = new DefaultUnitOfWork(true);
      User defaultUser = repository.findUserByName( work, applicationContext.getPropertyContext().getDefaultUserName() );
      User anonymousUser = repository.findUserByName( work, PredefinedUser.ANONYMOUS.getUserName());
      User administrator = repository.findUserByName( work, PredefinedUser.SYSTEM_ADMINISTRATOR.getUserName());
      
      UserDataSheetFactory userDataSheetFactory = applicationContext.getEntityFactory( UserDataSheetFactory.class );
      UserDataSheet defaultUserDataSheet = userDataSheetFactory.create( defaultUser );
      UserDataSheet anonymousUserDataSheet = userDataSheetFactory.create( anonymousUser );
      UserDataSheet administratorDataSheet = userDataSheetFactory.create( administrator );
        
      UserDataSheetRepository userDataSheetRepository = applicationContext.getRepository( UserDataSheetRepository.class );
      userDataSheetRepository.addUser( work, defaultUserDataSheet );
      userDataSheetRepository.addUser( work, anonymousUserDataSheet );
      userDataSheetRepository.addUser( work, administratorDataSheet );
      work.finish();
   }
}
