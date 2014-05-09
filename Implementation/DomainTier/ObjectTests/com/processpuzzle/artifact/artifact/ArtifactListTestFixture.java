package com.processpuzzle.artifact.artifact;

import com.processpuzzle.artifact.domain.Artifact;
import com.processpuzzle.litest.template.ArtifactTestEnvironment;
import com.processpuzzle.litest.template.ArtifactTestFixture;

public abstract class ArtifactListTestFixture<L extends ArtifactList<A>, A extends Artifact<A>> extends ArtifactTestFixture<A>{

   protected ArtifactListTestFixture( ArtifactTestEnvironment<A, ArtifactListTestFixture<L,A>> testEnvironment ) {
      super( testEnvironment );
   }

}
