/*
Name: 
    - PartyDataSheetRepository

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
import com.processpuzzle.commons.persistence.PersistenceStrategy;
import com.processpuzzle.commons.persistence.RepositoryResultSet;
import com.processpuzzle.persistence.domain.DefaultUnitOfWork;
import com.processpuzzle.persistence.domain.GenericRepository;

public class PartyDataSheetRepository extends GenericRepository<PartyDataSheet<?,?>> {

   public PartyDataSheetRepository( PersistenceStrategy strategy, ProcessPuzzleContext applicationContext ) {
      super( strategy, applicationContext );
   }

   public RepositoryResultSet<PartyDataSheet<?,?>> findAll() {
      DefaultUnitOfWork work = new DefaultUnitOfWork( true );
      RepositoryResultSet<PartyDataSheet<?,?>> results = findAll( work );
      work.finish();
      return results;
   }
   
   //comment out because ant javac failed as I can see we dont use it
   /*   @SuppressWarnings("unchecked") 
   public RepositoryResultSet<PartyDataSheet<?,?>> findAll( UnitOfWork work ) {
      return super.findAll( work, (Class<? extends PartyDataSheet<?, ?>>) PartyDataSheet.class );
   }*/
}
