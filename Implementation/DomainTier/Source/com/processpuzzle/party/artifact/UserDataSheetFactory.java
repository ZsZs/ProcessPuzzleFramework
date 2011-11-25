/*
Name: 
    - UserDataSheetFactory

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
import com.processpuzzle.application.security.domain.UserFactory;
import com.processpuzzle.artifact.domain.ArtifactFactory;
import com.processpuzzle.artifact_type.domain.ArtifactType;
import com.processpuzzle.internalization.domain.ProcessPuzzleLocale;

public class UserDataSheetFactory extends ArtifactFactory<UserDataSheet> {

   public UserDataSheet create( String userName, String password ) {
      try{
         return create( userName, password, null );
      }catch( Exception e ){
         return null;
      }
   }

   public UserDataSheet create( String userName, String password, ProcessPuzzleLocale preferredLocale ) {
      // create User
      UserFactory userFactory = applicationContext.getEntityFactory( UserFactory.class );
      if ( preferredLocale == null ) {
         preferredLocale = applicationContext.getDefaultLocale();
      }
      User user = userFactory.createUser( userName, password, preferredLocale );

      // create UserDS
      ArtifactType dataSheetType = super.findTypeFor( UserDataSheet.class );
      return super.create( userName, dataSheetType, user );
   }
   
   public UserDataSheet create( User user ) {
      // create UserDS
      ArtifactType dataSheetType = super.findTypeFor( UserDataSheet.class );
      return super.create( user.getUserName(), dataSheetType, user );
      
   }

}
