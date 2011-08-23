package hu.itkodex.litest.template;

import hu.itkodex.commons.generics.GenericTypeParameterInvestigator;

import com.processpuzzle.persistence.domain.EntityFactory;

public class FactoryTestEnvironment<S extends EntityFactory<?>, F extends FactoryTestFixture<S,?>> extends GenericTestEnvironment<S> {
   private Class<F> factoryTestFixtureClass;

   @SuppressWarnings("unchecked")
   public FactoryTestEnvironment( Class<F> factoryTestFixtureClass ) {
      super();
      this.factoryTestFixtureClass = factoryTestFixtureClass;
      this.sutClass = (Class<S>) GenericTypeParameterInvestigator.getTypeParameter( factoryTestFixtureClass, 0 );
   }
   
   public ConfigurableApplicationFixture getApplicationFixture() { return getFixture( ConfigurableApplicationFixture.class ); }

   @Override
   protected void defineComponentTypes() {
      componentTypes.add( ConfigurableApplicationFixture.class );
      componentTypes.add( factoryTestFixtureClass );
   }

   @Override
   protected void configureAfterSutInstantiation() {
   }

   @Override
   protected void configureBeforeSutInstantiation() {
   }

   @Override
   protected void releaseResources() {
   }
}
