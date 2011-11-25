/*
Name: 
    - PredefinedUserDataSheetsCreator

Description: 
    -  

Requires:
    - 
    
Provides:
    - 

Part of: ProcessPuzzle Framework, Domain and Business Model Ready Architecture. Provides content, workflow and social networking functionality. 
http://www.processpuzzle.com

ProcessPuzzle - Content and Workflow Management Integration Business Platform

Author(s): 
    - Zsolt Zsuffa

Copyright: (C) 2011 This program is free software: you can redistribute it and/or modify it under the terms of the 
GNU General Public License as published by the Free Software Foundation, either version 3 of the License, 
or (at your option) any later version.

This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License for more details.

You should have received a copy of the GNU General Public License along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/

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
