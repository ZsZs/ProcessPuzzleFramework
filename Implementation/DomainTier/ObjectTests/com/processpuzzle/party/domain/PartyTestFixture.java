package com.processpuzzle.party.domain;


import com.processpuzzle.application.configuration.domain.ProcessPuzzleContext;
import com.processpuzzle.application.domain.Application;
import com.processpuzzle.litest.template.DefaultApplicationFixture;
import com.processpuzzle.litest.template.DomainObjectTestEnvironment;
import com.processpuzzle.litest.template.DomainObjectTestFixture;

public class PartyTestFixture extends DomainObjectTestFixture<Party<?>> {
   protected static DefaultApplicationFixture applicationFixture;
   protected static Application application;
   protected static ProcessPuzzleContext applicationContext;
   protected static String configurationDescriptorPath;

   protected PartyTestFixture( DomainObjectTestEnvironment<Party<?>, ?> testEnvironment ) {
      super( testEnvironment );
   }

   @Override
   protected void configureAfterSutInstantiation() {
   }

   @Override
   protected void configureBeforeSutInstantiation() {
      super.configureBeforeSutInstantiation();
   }

   @Override
   protected Party<?> instantiateSUT() {
      return null;
   }

   @Override
   protected void releaseResources() {
   }
}
