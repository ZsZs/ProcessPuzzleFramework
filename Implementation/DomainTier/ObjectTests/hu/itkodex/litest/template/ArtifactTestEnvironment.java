package hu.itkodex.litest.template;

import com.processpuzzle.artifact.domain.Artifact;
import com.processpuzzle.sharedfixtures.domaintier.MockProcessPuzzleContext;
import com.processpuzzle.sharedfixtures.domaintier.RequestContextInMockedApplicationFixture;

public class ArtifactTestEnvironment<S extends Artifact<S>, F extends ArtifactTestFixture<S>> extends GenericTestEnvironment<S> {
   protected Class<F> artifactTestFixtureClass;

   public ArtifactTestEnvironment( Class<F> artifactTestFixtureClass ) {
      super();
      this.artifactTestFixtureClass = artifactTestFixtureClass;
   }
   
   @Override
   public S getSUT() {
      F fixture = this.getFixture( artifactTestFixtureClass );
      if( fixture != null ) return fixture.getSUT();
      else return null;
   }

   //Properties
   public MockProcessPuzzleContext getProcessPuzzleContextFixture() {
      return this.getFixture( MockProcessPuzzleContext.class );
   }
   
   public RequestContextInMockedApplicationFixture getRequestContextFixture() {
      return this.getFixture( RequestContextInMockedApplicationFixture.class );
   }
   
   @Override protected void configureAfterSutInstantiation() { }

   @Override protected void configureBeforeSutInstantiation() { }

   @Override
   protected void defineComponentTypes() {
      componentTypes.add( RequestContextInMockedApplicationFixture.class );
      componentTypes.add( MockProcessPuzzleContext.class );
      componentTypes.add( artifactTestFixtureClass );
   }

   @Override protected void releaseResources() {  }

}
