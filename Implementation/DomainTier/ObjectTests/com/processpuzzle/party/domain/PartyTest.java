package com.processpuzzle.party.domain;

import com.processpuzzle.litest.template.DomainObjectTestTemplate;

public class PartyTest extends DomainObjectTestTemplate<Party<?>, PartyTestFixture>{

   protected PartyTest( String fixtureContainerConfigurationPath ) {
      super( fixtureContainerConfigurationPath );
   }
}
