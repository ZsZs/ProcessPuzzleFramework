package hu.itkodex.litest.template;

import hu.itkodex.commons.generics.GenericTypeParameterInvestigator;

import com.processpuzzle.application.configuration.domain.ProcessPuzzleContext;
import com.processpuzzle.artifact.domain.Artifact;
import com.processpuzzle.artifact.domain.ArtifactFactory;
import com.processpuzzle.artifact_type.domain.ArtifactTypeFactory;
import com.processpuzzle.artifact_type.domain.ArtifactTypeRepository;

public abstract class ArtifactFactoryTestFixture<S extends ArtifactFactory<A>, A extends Artifact<?>> extends GenericTemplatedFixture<S> {
   private ConfigurableApplicationFixture applicationFixture;
   protected ArtifactTypeFactory artifactTypeFactory;
   protected ArtifactTypeRepository artifactTypeRepository;
   protected Class<A> artifactClass;
   protected ProcessPuzzleContext applicationContext;
   protected S factory;

   @SuppressWarnings("unchecked")
   @Override
   protected void configureBeforeSutInstantiation() {
      artifactClass = (Class<A>) GenericTypeParameterInvestigator.getTypeParameter( this.getClass(), 1 );
      applicationContext = applicationFixture.getApplicationContext();
      lookUpArtifactTypeRepositoryAndFactory();
   }

   @Override
   protected S instantiateSUT() {
      factory = (S) applicationContext.getEntityFactory( sutClass );
      return factory;
   }

   protected ArtifactFactoryTestFixture( ArtifactFactoryTestEnvironment<S, ?> testEnvironment ) {
      super( testEnvironment );
      this.applicationFixture = testEnvironment.getApplicationFixture();
   }
   
   private void lookUpArtifactTypeRepositoryAndFactory(){
      artifactTypeFactory = applicationContext.getEntityFactory( ArtifactTypeFactory.class );
      artifactTypeRepository = applicationContext.getRepository( ArtifactTypeRepository.class );
   }
}
