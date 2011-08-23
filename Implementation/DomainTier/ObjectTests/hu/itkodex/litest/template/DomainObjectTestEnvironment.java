package hu.itkodex.litest.template;

import hu.itkodex.commons.generics.GenericTypeParameterInvestigator;
import hu.itkodex.commons.persistence.Entity;

import com.processpuzzle.sharedfixtures.domaintier.MockProcessPuzzleContext;
import com.processpuzzle.sharedfixtures.domaintier.RequestContextInMockedApplicationFixture;

public class DomainObjectTestEnvironment<S extends Entity, F extends DomainObjectTestFixture<S>> extends GenericTestEnvironment<S> {
   protected Class<F> domainObjectTestFixtureClass;
   private F domainObjectTestFixture;
   
   @SuppressWarnings("unchecked")
   public DomainObjectTestEnvironment( Class<F> domainObjectTestFixtureClass ) {
      super();
      this.domainObjectTestFixtureClass = domainObjectTestFixtureClass;
      this.sutClass = (Class<S>) GenericTypeParameterInvestigator.getTypeParameter( domainObjectTestFixtureClass, 0 );
   }
   
   //Properties
   public MockProcessPuzzleContext getApplicationContextFixture() {
      return getFixture( MockProcessPuzzleContext.class );
   }

   public F getDomainObjectTestFixture() {
      return domainObjectTestFixture;
   }

   //Protected, private helper methods.
   @Override
   protected void configureAfterSutInstantiation() {
      // TODO Auto-generated method stub
   }
   
   @Override
   protected void configureBeforeSutInstantiation() {
      // TODO Auto-generated method stub
   }

   @Override
   protected void defineComponentTypes() {
      componentTypes.add( RequestContextInMockedApplicationFixture.class );
      componentTypes.add( MockProcessPuzzleContext.class );
      componentTypes.add( domainObjectTestFixtureClass );
   }

   @Override
   protected void releaseResources() {
      // TODO Auto-generated method stub
   }
}
