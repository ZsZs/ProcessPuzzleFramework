/*
Name: 
    - UserDataSheet

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
import com.processpuzzle.artifact.domain.Artifact;
import com.processpuzzle.artifact_type.domain.ArtifactType;
import com.processpuzzle.persistence.query.domain.DefaultIdentityExpression;

public class UserDataSheet extends Artifact<UserDataSheet> {
   private User user;
   
   protected UserDataSheet() {
      super();
   }
   
   public UserDataSheet( String artifact, ArtifactType type, User creator, User user) {
      super(artifact, type, creator);
      this.user = user;
   }

   @Override
   public String getAsXml() {
      return null;
   }

   @Override
   public DefaultIdentityExpression<UserDataSheet> getDefaultIdentity() {
      // TODO Auto-generated method stub
      return null;
   }

   public String getCountry() {
      if( user.getCountry() != null ) return user.getCountry();
      else return notAvailableResponse();
   }

   public String getLanguage() {
      if( user.getLanguage() != null ) return user.getLanguage();
      else return notAvailableResponse();
   }

   public String getLocation() {
      if( user.getLocation() != null ) return user.getLocation();
      else return notAvailableResponse();
   }

   public String getPrefferedLocale() {
      if( user.getPrefferedLocale() != null ) return user.getPrefferedLocale().getLanguage();
      else return notAvailableResponse();
   }

   public User getUser() {
      return user;
   }
   
   public void setUser(User user) {
      this.user = user;
   }
}
