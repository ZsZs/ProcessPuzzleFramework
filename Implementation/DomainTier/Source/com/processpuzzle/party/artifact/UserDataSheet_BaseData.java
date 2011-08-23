package com.processpuzzle.party.artifact;

import com.processpuzzle.artifact.domain.CustomFormView;
import com.processpuzzle.artifact_type.domain.ArtifactViewType;
import com.processpuzzle.internalization.domain.ProcessPuzzleLocale;

public class UserDataSheet_BaseData extends CustomFormView<UserDataSheet> {

   public UserDataSheet_BaseData( UserDataSheet artifact, String viewName, ArtifactViewType type ) {
      super( artifact, viewName, type );
      // TODO Auto-generated constructor stub
   }

   @Override
   public void initializeView() {
   // TODO Auto-generated method stub

   }

   // ---getters

   public String getUserName() {
      return parentArtifact.getUser().getUserName();
   }

   public String getPassword() {
      return parentArtifact.getUser().getPassword();
   }

   public String getPreferredLocale() {
      ProcessPuzzleLocale prefLoc = parentArtifact.getUser().getPrefferedLocale();
      return prefLoc.getLanguage();
   }

   public String getLocation() {
      return parentArtifact.getUser().getLocation();
   }

   public String getLanguage() {
      return parentArtifact.getUser().getLanguage();
   }

   public String getCountry() {
      return parentArtifact.getUser().getCountry();
   }

   // ---setters

   public void setUserName( String userName ) {
      parentArtifact.getUser().changeUserName( userName );
   }

   public void setPassword( String password ) {
      parentArtifact.getUser().changePassword( password );
   }

   public void setLocation( String location ) {
      parentArtifact.getUser().setLocation( location );
   }

   public void setLanguage( String language ) {
      parentArtifact.getUser().getLanguage();
   }

   public void setCountry( String country ) {
      parentArtifact.getUser().setCountry( country );
   }

   public void setPreferredLocale( ProcessPuzzleLocale preferredLocale ) {
      parentArtifact.getUser().setPrefferedLocale( preferredLocale );
      // -ez nem biztos hogy jó! ki kell próbálni
   }
}
