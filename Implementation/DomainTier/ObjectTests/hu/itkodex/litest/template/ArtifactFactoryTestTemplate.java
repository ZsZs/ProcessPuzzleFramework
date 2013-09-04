package hu.itkodex.litest.template;

import org.junit.Test;

import com.processpuzzle.application.configuration.domain.ProcessPuzzleContext;
import com.processpuzzle.application.domain.Application;
import com.processpuzzle.artifact.domain.Artifact;
import com.processpuzzle.artifact.domain.ArtifactFactory;
import com.processpuzzle.litest.template.GenericTestTemplate;
import com.processpuzzle.persistence.domain.EntityIdentityCollitionException;

public abstract class ArtifactFactoryTestTemplate <S extends ArtifactFactory<A>, F extends ArtifactFactoryTestFixture<S, A>, A extends Artifact<?>> extends GenericTestTemplate<S,F,ArtifactFactoryTestEnvironment<S,F>>{
   protected Application application;
   protected ProcessPuzzleContext applicationContext;
   protected DefaultApplicationFixture applicationFixture;
   protected String configurationDescriptorPath;
   
   protected ArtifactFactoryTestTemplate( String fixtureContainerConfigurationPath ) {
      super( fixtureContainerConfigurationPath, ArtifactFactoryTestEnvironment.class );
   }
   
   @Test public abstract void create_ForSuccess();
   @Test (expected = EntityIdentityCollitionException.class ) public abstract void create_ForCollision();
}
