package hu.itkodex.litest.template;

import hu.itkodex.commons.generics.GenericTypeParameterInvestigator;
import hu.itkodex.commons.persistence.Entity;

import com.processpuzzle.application.configuration.domain.ProcessPuzzleContext;
import com.processpuzzle.artifact.domain.ArtifactFactory;

public abstract class ArtifactFactoryTestFixture<S extends ArtifactFactory<?>> extends GenericTemplatedFixture<S> {
   private ConfigurableApplicationFixture applicationFixture;
   protected ProcessPuzzleContext applicationContext;
   protected S factory;

   @SuppressWarnings("unchecked")
   @Override
   protected void configureBeforeSutInstantiation() {
      Class<? extends Entity> entityClass = (Class<? extends Entity>) GenericTypeParameterInvestigator.getTypeParameter( this.getClass(), 1 );
      applicationContext = applicationFixture.getApplicationContext();
      factory = (S) applicationContext.getEntityFactoryByEntityClass( entityClass );
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
