package hu.itkodex.litest.template;


import hu.itkodex.commons.generics.GenericTypeParameterInvestigator;
import hu.itkodex.litest.fixture.TestFixture;
import hu.itkodex.litest.testcase.GenericTestSuite;
import hu.itkodex.litest.testcase.NoSuchFixtureDefinitionException;

public abstract class ApplicationObjectTestTemplate<S, F extends ApplicationObjectTestFixture<S>> extends GenericTestSuite<S,F>{
   private Class<F> domainObjectFixtureClass;

   protected ApplicationObjectTestTemplate( String fixtureContainerConfigurationPath ) {
      super( fixtureContainerConfigurationPath, ApplicationObjectTestEnvironment.class );
   }

   @SuppressWarnings("unchecked") @Override
   public <B extends TestFixture<?>> B acquireFixture( Class<B> requiredType ) throws NoSuchFixtureDefinitionException {
      if( requiredType.equals( ApplicationObjectTestEnvironment.class ))
         return (B) createCompositeDomainFixture();
      else return super.acquireFixture( requiredType );
   }

   @SuppressWarnings("unchecked")
   public ApplicationObjectTestEnvironment<F, S> createCompositeDomainFixture() {
      domainObjectFixtureClass = (Class<F>) GenericTypeParameterInvestigator.getTypeParameter( getClass(), 1 );
      return new ApplicationObjectTestEnvironment<F, S>( domainObjectFixtureClass );
   }

}
