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
