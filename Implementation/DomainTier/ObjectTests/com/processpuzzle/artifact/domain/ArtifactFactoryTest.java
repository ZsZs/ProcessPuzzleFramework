package com.processpuzzle.artifact.domain;


import com.processpuzzle.application.security.domain.UserFactory;
import com.processpuzzle.commons.persistence.AggregateRoot;
import com.processpuzzle.litest.template.FactoryTestFixture;
import com.processpuzzle.litest.template.FactoryTestTemplate;
import com.processpuzzle.persistence.domain.EntityFactory;

public abstract class ArtifactFactoryTest<S extends EntityFactory<A>, F extends FactoryTestFixture<S,A>, A extends AggregateRoot> extends FactoryTestTemplate<S, F, A> {
   protected static UserFactory userFactory;

   protected ArtifactFactoryTest( String fixtureContainerConfigurationPath ) {
      super( fixtureContainerConfigurationPath );
      userFactory = applicationContext.getEntityFactory( UserFactory.class );
   }
}
