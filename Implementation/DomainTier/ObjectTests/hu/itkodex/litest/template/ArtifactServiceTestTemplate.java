package hu.itkodex.litest.template;

import com.processpuzzle.artifact.service.AbstractArtifactService;
import com.processpuzzle.litest.template.GenericTestTemplate;

public abstract class ArtifactServiceTestTemplate<S extends AbstractArtifactService, F extends ArtifactServiceTestFixture<S>> extends GenericTestTemplate<S, F, ArtifactServiceTestEnvironment<S, F>> {

   protected ArtifactServiceTestTemplate( String fixtureContainerConfigurationPath ) {
      super( fixtureContainerConfigurationPath, ArtifactServiceTestEnvironment.class );
   }
}
