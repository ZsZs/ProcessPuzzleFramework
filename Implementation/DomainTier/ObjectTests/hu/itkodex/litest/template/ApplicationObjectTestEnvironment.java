package hu.itkodex.litest.template;

import com.processpuzzle.commons.generics.GenericTypeParameterInvestigator;
import com.processpuzzle.litest.fixture.GenericCompositeFixture;
import com.processpuzzle.sharedfixtures.domaintier.MockProcessPuzzleContext;
import com.processpuzzle.sharedfixtures.domaintier.RequestContextInMockedApplicationFixture;


public class ApplicationObjectTestEnvironment<F extends ApplicationObjectTestFixture<S>, S> extends GenericCompositeFixture<S> {
   protected Class<F> applicationObjectTestFixtureClass;
   private F applicationObjectTestFixture;
   
   @SuppressWarnings("unchecked")
   public ApplicationObjectTestEnvironment( Class<F> domainObjectTestFixtureClass ) {
      super();
      this.applicationObjectTestFixtureClass = domainObjectTestFixtureClass;
      this.sutClass = (Class<S>) GenericTypeParameterInvestigator.getTypeParameter( domainObjectTestFixtureClass, 0 );
   }
   

   //Properties
   public F getApplicationObjectTestFixture() {
      return applicationObjectTestFixture;
   }

   //Protected, private helper methods.
   @Override
   protected void defineComponentTypes() {
      componentTypes.add( RequestContextInMockedApplicationFixture.class );
      componentTypes.add( MockProcessPuzzleContext.class );
      componentTypes.add( applicationObjectTestFixtureClass );
   }

   @Override
   protected void configureAfterSutInstantiation() {
      // TODO Auto-generated method stub
      
   }

   @Override
   protected void configureBeforeSutInstantiation() {
      // TODO Auto-generated method stub
      
   }

   @Override
   protected void releaseResources() {
      // TODO Auto-generated method stub
      
   }

}
