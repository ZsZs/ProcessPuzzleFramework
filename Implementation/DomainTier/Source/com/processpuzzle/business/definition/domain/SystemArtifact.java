/*
Name: 
    - SystemArtifact

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

package com.processpuzzle.business.definition.domain;

import com.processpuzzle.artifact.domain.RootArtifactFolder;

public enum SystemArtifact {
   WORKFLOWS_FOLDER( "Workflows" ),
   ARTIFACTS_FOLDER( "Artifacts" ),
   ROLES_FOLDER( "Roles" ),
   CONTENT_FOLDER( "Content" ),
   SYSTEM_ADMINISTRATION_FOLDER( "SystemAdministration" ),
   ARTIFACT_LIST_NAME( "ArtifactList" ),
   ORGANIZATION_LIST_NAME( "OrganizationList" ),
   COMPANY_LIST_NAME( "CompanyList" ),
   PERSON_LIST_NAME( "PersonList" ),
   MESSAGE_WALL( "MessageWall" );
   
   SystemArtifact( String name ) {
      this.name = name;
   }
   
   private String name;
   
   public String getName() { return name; }
   public String getPath() { 
      if( this == WORKFLOWS_FOLDER || this == ARTIFACTS_FOLDER || this == ROLES_FOLDER || this == CONTENT_FOLDER )
         return RootArtifactFolder.ROOT_ARTIFACT_FOLDER_NAME + RootArtifactFolder.PATH_SEPARATOR + getName();
      else if( this == SYSTEM_ADMINISTRATION_FOLDER ) {
         return RootArtifactFolder.ROOT_ARTIFACT_FOLDER_NAME 
         + RootArtifactFolder.PATH_SEPARATOR
         + ARTIFACTS_FOLDER.getName()
         + RootArtifactFolder.PATH_SEPARATOR
         + SYSTEM_ADMINISTRATION_FOLDER.getName();
      } else 
         return RootArtifactFolder.ROOT_ARTIFACT_FOLDER_NAME 
              + RootArtifactFolder.PATH_SEPARATOR
              + ARTIFACTS_FOLDER.getName()
              + RootArtifactFolder.PATH_SEPARATOR
              + SYSTEM_ADMINISTRATION_FOLDER.getName()
              + RootArtifactFolder.PATH_SEPARATOR
              + getName();
   }
}
