package hu.itkodex.litest.template;

import hu.itkodex.litest.fixture.TransientFreshFixture;

import com.processpuzzle.application.configuration.domain.ProcessPuzzleContext;
import com.processpuzzle.artifact.domain.Artifact;
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
