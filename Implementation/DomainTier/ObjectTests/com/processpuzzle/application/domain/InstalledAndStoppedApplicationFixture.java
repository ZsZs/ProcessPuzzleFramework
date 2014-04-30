package com.processpuzzle.application.domain;


import com.processpuzzle.application.domain.Application;
import com.processpuzzle.application.domain.ApplicationException;
import com.processpuzzle.application.domain.ApplicationFactory;
import com.processpuzzle.litest.template.DefaultApplicationFixture;
import com.processpuzzle.sharedfixtures.domaintier.ApplicationTwo;
import com.processpuzzle.sharedfixtures.domaintier.DomainTierTestConfiguration;

public class InstalledAndStoppedApplicationFixture extends DefaultApplicationFixture<Application> {
   
   public InstalledAndStoppedApplicationFixture() {
      super( DomainTierTestConfiguration.APPLICATION_CONFIGURATION_DESCRIPTOR_PATH );
   }
   
   //Protected, private helper methods
   @Override
   protected Application instantiateSUT() {
      application = ApplicationFactory.create( ApplicationTwo.class, configurationPath );
      
      try {
         application.install();
      } catch( ApplicationException e ) {
         e.printStackTrace();
      }
      
      return application;
   }

   @Override
   protected void configureAfterSutInstantiation() {
      super.configureAfterSutInstantiation();
      application.stop();
      applicationRepository.add( application );
   }

   @Override
   protected void configureBeforeSutInstantiation() {
      //Nothing to do here in this case.
   }

   @Override
   protected void releaseResources() {
      application.unInstall();
      applicationRepository.delete( application );
      super.releaseResources();
   }
}
