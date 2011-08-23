package com.processpuzzle.party.artifact;

import com.processpuzzle.artifact.domain.PropertyView;
import com.processpuzzle.artifact_type.domain.ArtifactViewType;

public class PersonDataSheet_PropertyView extends PropertyView<PersonDataSheet> {

   public PersonDataSheet_PropertyView() {
      super( null, null, null );
   }

   public PersonDataSheet_PropertyView( PersonDataSheet artifact, String name, ArtifactViewType type ) {
      super( artifact, name, type );
   }

   public String getBirthDate() {
      return parentArtifact.getBirthDate();
   }

   public String getEmailAddress() {
      return parentArtifact.getEmailAddress();
   }
   
   public String getFamilyName() {
      return parentArtifact.getFamilyName();
   }

   public String getGeographicAddress() {
      return parentArtifact.getGeographicAddress();
   }
   
   public String getGivenName() {
      return parentArtifact.getGivenName();
   }
   
   public String getPersonName() {
      return parentArtifact.getPersonName();
   }
   
   public String getPrefix() {
      return parentArtifact.getPrefix();
   }
   
   public String getTelecomAddress() {
      return parentArtifact.getTelecomAddress();
   }
}
