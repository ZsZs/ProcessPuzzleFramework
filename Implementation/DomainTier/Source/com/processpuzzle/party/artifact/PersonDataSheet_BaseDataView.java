package com.processpuzzle.party.artifact;

import java.util.List;

import com.processpuzzle.artifact.domain.CustomFormView;
import com.processpuzzle.artifact_type.domain.ArtifactViewType;

public class PersonDataSheet_BaseDataView extends CustomFormView<PersonDataSheet> {

   public PersonDataSheet_BaseDataView( PersonDataSheet personDataSheet, String viewName, ArtifactViewType type ) {
      super( personDataSheet, viewName, type );
   }

   public String getPrefix() {
      return parentArtifact.getPrefix();
   }

   public void setPrefix( String prefix ) {
      parentArtifact.setPrefix( prefix );
   }

   public String getFamilyName() {
      return parentArtifact.getFamilyName();
   }

   public String getGivenName() {
      return parentArtifact.getGivenName();
   }

   public List<String> getLocales() {
      return parentArtifact.getLocales();
   }

   public String getSelectLocale() {
      return parentArtifact.getPreferredLocale();
   }

   public String getPassword() {
      return (parentArtifact.getPassword() == null) ? "" : parentArtifact.getPassword();
   }

   public String getUserName() {
      return (parentArtifact.getUserName() == null) ? "" : parentArtifact.getUserName();
   }

   public void setFamilyName( String familyName ) {
      parentArtifact.setFamilyName( familyName );
   }

   public void setGivenName( String givenName ) {
      parentArtifact.setGivenName( givenName );
   }

   public void setPassword( String password ) {
      parentArtifact.setPassword( password );
   }

   public void setSelectLocale( String locale ) {
      parentArtifact.setPrefferredLocale( locale );
   }

   public void setUserName( String userName ) {
      parentArtifact.setUserName( userName );
   }

   public void performAction() {

   }

   @Override
   public void initializeView() {
   // TODO Auto-generated method stub

   }

}
