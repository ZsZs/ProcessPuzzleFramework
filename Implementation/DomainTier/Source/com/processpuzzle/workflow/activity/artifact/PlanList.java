/*
 * Created on Sep 7, 2006
 */
package com.processpuzzle.workflow.activity.artifact;

import com.processpuzzle.application.security.domain.User;
import com.processpuzzle.artifact.artifact.ArtifactList;
import com.processpuzzle.artifact_type.domain.ArtifactType;

public class PlanList extends ArtifactList<PlanList> {

   protected PlanList() {
      super();
   }

   public PlanList( String name, ArtifactType type, User creator ) {
      super( name, type, creator );
   }

   @Override
   protected void defineIdentityExpressions() {
      defaultIdentity = new PlanListIdentity();
      identities.add( defaultIdentity );
   }
}
