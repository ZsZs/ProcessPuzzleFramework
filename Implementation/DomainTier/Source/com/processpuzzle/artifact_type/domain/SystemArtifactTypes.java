package com.processpuzzle.artifact_type.domain;

public enum SystemArtifactTypes {
   ARTIFACT("Artifact"), 
   ARTIFACT_FOLDER( "ArtifactFolder" ), 
   PERSON_LIST( "PersonList" ), 
   COMPANY_LIST( "CompanyList" );
   
   SystemArtifactTypes( String name ) {
      this.name = name;
   }
   private String name;
   
   public String getName() { return name; }
}
