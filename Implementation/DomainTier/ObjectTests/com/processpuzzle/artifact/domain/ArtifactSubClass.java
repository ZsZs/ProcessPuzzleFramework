/*
 * Created on Apr 12, 2006
 */
package com.processpuzzle.artifact.domain;

import com.processpuzzle.application.security.domain.User;
import com.processpuzzle.artifact.domain.Artifact;
import com.processpuzzle.artifact_type.domain.ArtifactType;
import com.processpuzzle.persistence.query.domain.DefaultIdentityExpression;

/**
 * @author zsolt.zsuffa
 */
public class ArtifactSubClass extends Artifact<ArtifactSubClass> {

   protected ArtifactSubClass() {}

   public ArtifactSubClass( String name, ArtifactType type, User responsible ) {
      super( name, type, responsible );
   }

   public String getAsXml() {
      return super.getAsXml( ArtifactSubClass.class, this );
   }

   @Override
   public DefaultIdentityExpression<ArtifactSubClass> getDefaultIdentity() {
      // TODO Auto-generated method stub
      return null;
   }

}