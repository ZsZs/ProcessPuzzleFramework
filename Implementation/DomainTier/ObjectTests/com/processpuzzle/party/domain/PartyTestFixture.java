package com.processpuzzle.party.domain;

import hu.itkodex.litest.template.DefaultApplicationFixture;
import hu.itkodex.litest.template.DomainObjectTestEnvironment;
import hu.itkodex.litest.template.DomainObjectTestFixture;

import com.processpuzzle.application.configuration.domain.ProcessPuzzleContext;
import com.processpuzzle.application.domain.Application;

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
