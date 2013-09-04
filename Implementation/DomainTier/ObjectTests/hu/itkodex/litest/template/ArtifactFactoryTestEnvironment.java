package hu.itkodex.litest.template;


import com.processpuzzle.artifact.domain.ArtifactFactory;
import com.processpuzzle.commons.generics.GenericTypeParameterInvestigator;
import com.processpuzzle.litest.template.GenericTestEnvironment;

public class ArtifactFactoryTestEnvironment<S extends ArtifactFactory<?>, F extends ArtifactFactoryTestFixture<S,?>> extends GenericTestEnvironment<S> {
   private Class<F> factoryTestFixtureClass;

   @SuppressWarnings("unchecked")
   public ArtifactFactoryTestEnvironment( Class<F> factoryTestFixtureClass ) {
      super();
      this.factoryTestFixtureClass = factoryTestFixtureClass;
      this.sutClass = (Class<S>) GenericTypeParameterInvestigator.getTypeParameter( factoryTestFixtureClass, 0 );
   }
   
   public DefaultApplicationFixture getApplicationFixture() { return getFixture( ConfigurableApplicationFixture.class ); }

   @Override
   protected void defineComponentTypes() {
      componentTypes.add( ConfigurableApplicationFixture.class );
      componentTypes.add( factoryTestFixtureClass );
   }

   @Override protected void configureAfterSutInstantiation() { }
   @Override protected void configureBeforeSutInstantiation() { }
   @Override protected void releaseResources() { }
}
