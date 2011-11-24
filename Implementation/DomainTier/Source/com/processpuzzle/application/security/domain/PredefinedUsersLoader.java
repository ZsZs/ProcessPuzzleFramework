/*
Name: 
    - PredefinedUsersLoader

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
