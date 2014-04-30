package com.processpuzzle.application.domain;


import com.processpuzzle.litest.template.DefaultApplicationFixture;
import com.processpuzzle.sharedfixtures.domaintier.ApplicationOne;
import com.processpuzzle.sharedfixtures.domaintier.DomainTierTestConfiguration;

public class NotInstalledApplicationFixture extends DefaultApplicationFixture<Application> {
   
   public NotInstalledApplicationFixture() {
      super( DomainTierTestConfiguration.APPLICATION_CONFIGURATION_DESCRIPTOR_PATH );
   }

   @Override
   public void setUp() {
      super.setUp();
   }

   @Override
   protected Application instantiateSUT() {
      application = ApplicationFactory.create( ApplicationOne.class, configurationPath );
      return application;
   }

   @Override
   protected void configureAfterSutInstantiation() {
      // Suppress parent class functionality.
   }

   @Override
   protected void configureBeforeSutInstantiation() {
      // No need to do here.
   }

}
