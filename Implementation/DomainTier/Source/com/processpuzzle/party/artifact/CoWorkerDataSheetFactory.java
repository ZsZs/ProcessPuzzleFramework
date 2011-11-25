/*
Name: 
    - CoWorkerDataSheetFactory

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

import com.processpuzzle.application.security.domain.User;
import com.processpuzzle.artifact.domain.ArtifactFactory;
import com.processpuzzle.artifact_type.domain.ArtifactType;
import com.processpuzzle.party.domain.Person;
import com.processpuzzle.party.domain.PersonFactory;
import com.processpuzzle.persistence.domain.EntityIdentityCollitionException;
import com.processpuzzle.user_session.domain.UserRequestManager;

public class CoWorkerDataSheetFactory extends ArtifactFactory<CoWorkerDataSheet> {

   public CoWorkerDataSheetFactory() {
      super();
   }
   
   public CoWorkerDataSheet create( String artifactName, String familyName, String givenName, String userName, String password ) throws EntityIdentityCollitionException {
      ArtifactType type = findTypeFor( CoWorkerDataSheet.class );
      PersonFactory personFactory = applicationContext.getEntityFactory( PersonFactory.class );
      Person coWorker = personFactory.create( familyName, givenName, userName, password );
      User creator = UserRequestManager.getInstance().currentUser();
      return new CoWorkerDataSheet( creator, artifactName, type, coWorker );
  }  
}
