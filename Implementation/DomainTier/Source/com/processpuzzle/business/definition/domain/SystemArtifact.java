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
