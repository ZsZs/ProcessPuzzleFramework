package com.processpuzzle.party.artifact;

import com.processpuzzle.artifact.domain.PropertyView;
import com.processpuzzle.artifact_type.domain.ArtifactViewType;

public class UserDataSheet_PropertyView extends PropertyView<UserDataSheet> {

   public UserDataSheet_PropertyView( UserDataSheet artifact, String name, ArtifactViewType viewType ) {
      super( artifact, name, viewType );
   }

   public String getUserName() {
      return parentArtifact.getUser().getUserName();
   }

   public String getPreferredLocale() {
      return parentArtifact.getPrefferedLocale();
   }

   public String getLocation() {
      return parentArtifact.getLocation();
   }

   public String getLanguage() {
      return parentArtifact.getLanguage();
   }

   public String getCountry() {
      return parentArtifact.getCountry();
   }

}
