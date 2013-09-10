package com.processpuzzle.litest.template;


import com.processpuzzle.application.configuration.domain.ProcessPuzzleContext;
import com.processpuzzle.artifact.service.AbstractArtifactService;
import com.processpuzzle.litest.fixture.TransientFreshFixture;
import com.processpuzzle.litest.template.GenericTemplatedFixture;
import com.processpuzzle.sharedfixtures.domaintier.MockProcessPuzzleContext;

public abstract class ArtifactServiceTestFixture<S extends AbstractArtifactService> extends GenericTemplatedFixture<S> implements TransientFreshFixture<S> {
   protected MockProcessPuzzleContext mockApplicationContextFixture;
   protected ProcessPuzzleContext mockApplicationContext;

   protected ArtifactServiceTestFixture( ArtifactServiceTestEnvironment<S, ?> testEnvironment ) {
      super( testEnvironment );
      mockApplicationContextFixture = testEnvironment.getProcessPuzzleContextFixture();
      mockApplicationContext = mockApplicationContextFixture.getApplicationContext();
   }

   @Override protected void configureBeforeSutInstantiation() {
      mockApplicationContext = mockApplicationContextFixture.getApplicationContext();
   }

}
