package com.processpuzzle.litest.template;

import com.processpuzzle.commons.persistence.Entity;
import com.processpuzzle.litest.template.GenericTestTemplate;

public abstract class DomainObjectTestTemplate<S extends Entity, F extends DomainObjectTestFixture<S>> extends GenericTestTemplate<S,F,DomainObjectTestEnvironment<S, F>> {
//   private Class<F> domainObjectFixtureClass;
   
   protected DomainObjectTestTemplate( String fixtureContainerConfigurationPath ) {
      super( fixtureContainerConfigurationPath, DomainObjectTestEnvironment.class );
   }
   
}
