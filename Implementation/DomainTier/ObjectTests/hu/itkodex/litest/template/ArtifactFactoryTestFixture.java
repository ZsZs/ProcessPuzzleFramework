package hu.itkodex.litest.template;

import hu.itkodex.commons.generics.GenericTypeParameterInvestigator;

import com.processpuzzle.application.configuration.domain.ProcessPuzzleContext;
import com.processpuzzle.artifact.domain.Artifact;
import com.processpuzzle.artifact.domain.ArtifactFactory;

public abstract class ArtifactFactoryTestFixture<S extends ArtifactFactory<A>, A extends Artifact<?>> extends GenericTemplatedFixture<S> {
   private ConfigurableApplicationFixture applicationFixture;
   protected Class<A> artifactClass;
   protected ProcessPuzzleContext applicationContext;
   protected S factory;

   @SuppressWarnings("unchecked")
   @Override
   protected void configureBeforeSutInstantiation() {
      artifactClass = (Class<A>) GenericTypeParameterInvestigator.getTypeParameter( this.getClass(), 1 );
      applicationContext = applicationFixture.getApplicationContext();
      factory = (S) applicationContext.getEntityFactory( sutClass );
   }

   @Override
   protected S instantiateSUT() {
      return factory;
   }

   protected ArtifactFactoryTestFixture( ArtifactFactoryTestEnvironment<S, ?> testEnvironment ) {
      super( testEnvironment );
      this.applicationFixture = testEnvironment.getApplicationFixture();
   }
}
