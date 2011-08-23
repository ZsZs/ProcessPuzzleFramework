package com.processpuzzle.application.artifact;

import com.processpuzzle.application.domain.Application;
import com.processpuzzle.application.security.domain.User;
import com.processpuzzle.artifact.domain.Artifact;
import com.processpuzzle.artifact_type.domain.ArtifactType;
import com.processpuzzle.persistence.query.domain.DefaultIdentityExpression;

public class ApplicationDataSheet extends Artifact<ApplicationDataSheet> {

   protected Application application = null;
   
   public ApplicationDataSheet() {
      super();
   }
   
   public ApplicationDataSheet(String name, ArtifactType type, User creator, Application application) {
      super(name, type, creator);
      this.application = application;
   }
   
   public ApplicationDataSheet(String identifier) {
      super();
   }
   
   public Application getApplication() {
      return application;
   }
   
   @Override
   public String getAsXml() {
      // TODO Auto-generated method stub
      return null;
   }

   @Override
   public DefaultIdentityExpression<ApplicationDataSheet> getDefaultIdentity() {
      // TODO Auto-generated method stub
      return null;
   }

}
