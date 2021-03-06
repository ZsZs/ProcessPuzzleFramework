package com.processpuzzle.litest.template;


import com.processpuzzle.application.configuration.domain.ProcessPuzzleContext;
import com.processpuzzle.artifact.domain.Artifact;
import com.processpuzzle.litest.fixture.TransientFreshFixture;
import com.processpuzzle.litest.template.GenericTemplatedFixture;
import com.processpuzzle.sharedfixtures.domaintier.MockProcessPuzzleContext;

public abstract class ArtifactTestFixture<S extends Artifact<S>> extends GenericTemplatedFixture<S> implements TransientFreshFixture<S> {
   protected MockProcessPuzzleContext mockApplicationContextFixture;
   protected ProcessPuzzleContext mockApplicationContext;
   
   protected ArtifactTestFixture( ArtifactTestEnvironment<S, ?> testEnvironment ) {
      super( testEnvironment );
      mockApplicationContextFixture = testEnvironment.getProcessPuzzleContextFixture();
      mockApplicationContext = mockApplicationContextFixture.getApplicationContext();
   }
   
   @Override protected void configureBeforeSutInstantiation() {
      mockApplicationContext = mockApplicationContextFixture.getApplicationContext();
   }

}
