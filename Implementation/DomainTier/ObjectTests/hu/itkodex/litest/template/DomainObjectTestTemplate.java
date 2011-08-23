package hu.itkodex.litest.template;

import hu.itkodex.commons.persistence.Entity;

public abstract class DomainObjectTestTemplate<S extends Entity, F extends DomainObjectTestFixture<S>> extends GenericTestTemplate<S,F,DomainObjectTestEnvironment<S, F>> {
//   private Class<F> domainObjectFixtureClass;
   
   protected DomainObjectTestTemplate( String fixtureContainerConfigurationPath ) {
      super( fixtureContainerConfigurationPath, DomainObjectTestEnvironment.class );
   }
   
}
